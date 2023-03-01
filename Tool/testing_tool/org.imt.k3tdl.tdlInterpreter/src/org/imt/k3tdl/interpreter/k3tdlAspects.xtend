package org.imt.k3tdl.interpreter

import fr.inria.diverse.k3.al.annotationprocessor.Aspect


import fr.inria.diverse.k3.al.annotationprocessor.InitializeModel
import fr.inria.diverse.k3.al.annotationprocessor.Main
import fr.inria.diverse.k3.al.annotationprocessor.Step
import java.nio.file.Paths
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.etsi.mts.tdl.Package
import org.etsi.mts.tdl.TestConfiguration
import org.etsi.mts.tdl.TestDescription
import org.imt.tdl.configuration.EngineFactory
import org.imt.tdl.coverage.computation.TDLCoverageUtil
import org.imt.tdl.coverage.computation.TDLTestCaseCoverage
import org.imt.tdl.coverage.computation.TDLTestSuiteCoverage
import org.imt.tdl.testResult.TDLTestCaseResult
import org.imt.tdl.testResult.TDLTestResultUtil
import org.imt.tdl.testResult.TDLTestSuiteResult
import org.imt.tdl.utilities.DSLProcessor
import org.imt.tdl.utilities.PathHelper

import static extension org.imt.k3tdl.interpreter.PackageAspect.*
import static extension org.imt.k3tdl.interpreter.BehaviourDescriptionAspect.*
import static extension org.imt.k3tdl.interpreter.TestConfigurationAspect.*
import static extension org.imt.k3tdl.interpreter.TestDescriptionAspect.*

@Aspect(className = Package)
class PackageAspect {
	
	public static PathHelper pathHelper;
	public static DSLProcessor dslProcessor;
	
	List<TestDescription> testcases = new ArrayList<TestDescription>
	TDLTestSuiteResult testSuiteResult = new TDLTestSuiteResult
	TDLTestSuiteCoverage testSuiteCoverage = new TDLTestSuiteCoverage
	
	@Step
	@InitializeModel
	def void initializeModel(EList<String> args){
		for (Object o : _self.packagedElement.filter[p | p instanceof TestDescription]){
			_self.testcases.add(o as TestDescription)
		}
	}
	@Step
	@Main
	def void main(){
		if (_self.testcases.size == 0){
			println("There is no test case in the package " + _self.name + "to be executed")
		}
		else{
			PackageAspect.pathHelper = new PathHelper(_self)
			_self.executeTestSuite()
		}
	}
	
	def TDLTestSuiteResult executeTestSuite(){
		_self.testSuiteResult.testSuite = _self
		_self.testSuiteCoverage.testSuite = _self
		for (TestDescription testCase:_self.testcases) {
			val TDLTestCaseResult testCaseResult = testCase.executeTestCase()
			_self.testSuiteResult.addResult(testCaseResult)
			//for coverage, only considering passed and failed test cases
			if (testCaseResult.value != TDLTestResultUtil.INCONCLUSIVE){
				_self.testSuiteCoverage.addTCCoverage(testCase.testCaseCoverage)
			}
			println()
		}
		
		TDLTestResultUtil.instance.setTestSuiteResult = _self.testSuiteResult		
		TDLCoverageUtil.instance.testSuiteCoverage = _self.testSuiteCoverage
		TDLCoverageUtil.instance.DSLPath = pathHelper.DSLPath
		println("Test suite execution has been finished successfully.")
		return _self.testSuiteResult
	}
}

@Aspect (className = TestDescription)
class TestDescriptionAspect{
	
	public EngineFactory launcher = new EngineFactory
	public TDLTestCaseResult testCaseResult = new TDLTestCaseResult
	public TDLTestCaseCoverage testCaseCoverage = new TDLTestCaseCoverage
	
	@Step
	def TDLTestCaseResult executeTestCase(){
		if (!_self.launcher.launcherIsTuned){
			_self.activateConfiguration()
		}
		return _self.runTestAndReturnResult
	}
	
	//this method is called from other codes (not GEMOC engine)
	def TDLTestCaseResult executeTestCase(String MUTPath){
		if (pathHelper === null){
			pathHelper = new PathHelper(_self.eContainer as Package)
		}
		_self.activateConfiguration(MUTPath)
		return _self.runTestAndReturnResult
	}
	
	/*
	 * the activateConfiguration methods are useful to configure test case before executing it
	 * e.g, in the test amplification tool, first a test configuration is created 
	 * then an observer is attached to the model execution engine
	 * and afterwards, the test case is executed
	 */
	def void activateConfiguration(){
		if (pathHelper === null){
			pathHelper = new PathHelper(_self.eContainer as Package)
		}
		pathHelper.findModelAndDSLPathOfTestCase(_self)
		_self.testConfiguration.activateConfiguration(_self.launcher)
	}
	
	def void activateConfiguration(String MUTPath){
		if (pathHelper === null){
			pathHelper = new PathHelper(_self.eContainer as Package)
		}
		pathHelper.findModelAndDSLPathOfTestCase(_self)
		pathHelper.modelUnderTestPath = Paths.get(MUTPath)
		
		_self.launcher = new EngineFactory
		_self.testCaseResult = new TDLTestCaseResult
		_self.testCaseCoverage = new TDLTestCaseCoverage
		
		_self.testConfiguration.activateConfiguration(_self.launcher)
	}
	
	def TDLTestCaseResult runTestAndReturnResult(){
		println("Start test case execution: " + _self.name)
		_self.testCaseResult.testCase = _self
		_self.behaviourDescription.callBehavior()
		val modelExecutionResult = _self.testConfiguration.stopModelExecutionEngine(_self.launcher)
		if (modelExecutionResult !== null && modelExecutionResult.contains(TDLTestResultUtil.FAIL)){
			_self.testCaseResult.value = TDLTestResultUtil.FAIL
			_self.testCaseResult.description = modelExecutionResult.substring(modelExecutionResult.indexOf(":")+1)
		}
		
		if (_self.testCaseResult.value != TDLTestResultUtil.INCONCLUSIVE){
			//save the model execution trace and the MUTResource related to this test case if its result is not INCONCLUSIVE
			_self.testCaseCoverage.testCase = _self
			_self.testCaseCoverage.trace = _self.launcher.executionTrace
	    	_self.testCaseCoverage.MUTResource = _self.launcher.MUTResource
		}
		println("Test case "+ _self.name + ": " + _self.testCaseResult.value)
		return _self.testCaseResult
	}
}

@Aspect (className = TestConfiguration)
class TestConfigurationAspect{

	def void activateConfiguration(EngineFactory launcher){
		val dslPath = PackageAspect.pathHelper.DSLPath
		PackageAspect.dslProcessor = new DSLProcessor(dslPath)
		dslProcessor.loadDSLMetaclasses
		
		launcher.DSLPath = dslPath
		launcher.MUTPath = PackageAspect.pathHelper.modelUnderTestPath
		_self.setUpLauncher(launcher)
	}
	
	final static String GENERIC_GATE = "genericGate";
	final static String REACTIVE_GATE = "reactiveGate";
	final static String OCL_GATE = "oclGate";
	
	def void setUpLauncher (EngineFactory launcher){
		if (_self.connection.exists[c|c.endPoint.exists[g|g.gate.name.equals(GENERIC_GATE)]]) {
			launcher.setUp(EngineFactory.GENERIC);
		}
		if (_self.connection.exists[c|c.endPoint.exists[g|g.gate.name.equals(REACTIVE_GATE)]]) {
			launcher.setUp(EngineFactory.DSL_SPECIFIC);
		}
		if (_self.connection.exists[c|c.endPoint.exists[g|g.gate.name.equals(OCL_GATE)]]){
			launcher.setUp(EngineFactory.OCL);
		}
	}
	
	def String stopModelExecutionEngine(EngineFactory launcher){
		if (_self.connection.exists[c|c.endPoint.exists[g|g.gate.name.equals(REACTIVE_GATE)]]) {
			return launcher.executeDSLSpecificCommand("STOP", null, null);
		}
	}
}
class TDLRuntimeException extends Exception {
	new(String message) {
		super(message)
	}					
}