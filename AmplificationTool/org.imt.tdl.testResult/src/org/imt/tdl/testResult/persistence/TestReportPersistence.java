package org.imt.tdl.testResult.persistence;

import java.io.File;


import java.io.IOException;
import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.etsi.mts.tdl.Behaviour;
import org.etsi.mts.tdl.CompoundBehaviour;
import org.etsi.mts.tdl.Message;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.PackageableElement;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.TDLTestReport.TDLTestReportFactory;
import org.imt.tdl.TDLTestReport.TestCaseResult;
import org.imt.tdl.TDLTestReport.TestMessageResult;
import org.imt.tdl.TDLTestReport.TestSuiteResult;
import org.imt.tdl.TDLTestReport.Verdicts;
import org.imt.tdl.testResult.TDLMessageResult;
import org.imt.tdl.testResult.TDLTestCaseResult;
import org.imt.tdl.testResult.TDLTestResultUtil;

public class TestReportPersistence implements IEngineAddon{
	
	@Override
	public void engineStopped(IExecutionEngine<?> engine) {
		IExecutionContext<?, ?, ?> _executionContext = null;
		Resource testSutieResource = null;
		if (_executionContext == null) {
			_executionContext = engine.getExecutionContext();
			testSutieResource = getCopyOfTestSuite(_executionContext);
		}
	   //create test result according to the TDLTestReport.ecore structure
	   Package copiedTestSuite = (Package) testSutieResource.getContents().get(0);
	   TestSuiteResult testSuiteResult = TDLTestReportFactory.eINSTANCE.createTestSuiteResult();
	   testSuiteResult.setTestSuite(copiedTestSuite);
	   for (TDLTestCaseResult tcResultObject : TDLTestResultUtil.getInstance().getTestSuiteResult().getTestCaseResults()) {
		   String testCaseName = tcResultObject.getTestCaseName();
		   Optional<PackageableElement> optionalTC = copiedTestSuite.getPackagedElement().stream().filter(p -> p instanceof TestDescription).
			filter(t -> t.getName().equals(testCaseName)).findFirst();
		   if (optionalTC.isPresent()) {
			   TestDescription copiedTestCase = (TestDescription) optionalTC.get();
			   TestCaseResult testCaseResult = TDLTestReportFactory.eINSTANCE.createTestCaseResult();
			   testCaseResult.setTestCase(copiedTestCase);
			   String testCaseVerdict = tcResultObject.getValue();
			   if (testCaseVerdict == TDLTestResultUtil.PASS) {
				   testCaseResult.setValue(Verdicts.PASS);
			   }else if (testCaseVerdict == TDLTestResultUtil.FAIL) {
				   testCaseResult.setValue(Verdicts.FAIL);
			   }else if (testCaseVerdict == TDLTestResultUtil.INCONCLUSIVE) {
				   testCaseResult.setValue(Verdicts.INCONCLUSIVE);
			   }
			   testCaseResult.setDescription(tcResultObject.getDescription());
			   for (int i=0; i<tcResultObject.getTdlMessages().size(); i++) {
				   TDLMessageResult messageResultObject = tcResultObject.getTdlMessages().get(i);
				   TestMessageResult testMessageResult = TDLTestReportFactory.eINSTANCE.createTestMessageResult();
				   testMessageResult.setMessage(findEquivalentTDLMessage(copiedTestCase, i));
				   String testMsgVerdict = messageResultObject.getValue();
				   if (testMsgVerdict == TDLTestResultUtil.PASS) {
					   testMessageResult.setValue(Verdicts.PASS);
				   }else if (testMsgVerdict == TDLTestResultUtil.FAIL) {
					   testMessageResult.setValue(Verdicts.FAIL);
				   }else if (testMsgVerdict == TDLTestResultUtil.INCONCLUSIVE) {
					   testMessageResult.setValue(Verdicts.INCONCLUSIVE);
				   }
				   testMessageResult.setDescription(messageResultObject.getDescription());
				   testMessageResult.setTdlMessageId(messageResultObject.getTdlMessageId());
				   testCaseResult.getTestMessageResults().add(testMessageResult);
			   }
			   testSuiteResult.getTestCaseResults().add(testCaseResult);
		   }
	   }
	   
	   //create a resource for the test result
	   URI testReportURI = URI.createURI(
				_executionContext.getWorkspace().getExecutionPath().toString() + "/testReport.xmi", false);
	   Resource testResultResource = (new ResourceSetImpl()).createResource(testReportURI);
	   testResultResource.getContents().add(testSuiteResult);
	   //saving resources
	   try {
			testResultResource.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
		
	private Resource getCopyOfTestSuite(IExecutionContext<?, ?, ?> _executionContext) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String projectName = _executionContext.getWorkspace().getProjectPath().toString().substring(1);
		IProject[] projects = root.getProjects();
		IProject testSuiteProject = null;
		for (int i=0; i<projects.length; i++) {
			if (projects[i].getName().equals(projectName)) {
				testSuiteProject = projects[i];
			}
		}
		String testSuiteProjectAbsolutePath = testSuiteProject.getLocation().toString().replace("/" + projectName, "");
		String copiedModelFolderPath = testSuiteProjectAbsolutePath + _executionContext.getWorkspace().getExecutionPath().toString();
		File modelFile = new File(copiedModelFolderPath);
		for (File file: modelFile.listFiles()) {
			if (file.getName().endsWith(".tdlan2")) {
				String modelPath = file.getPath().replace(testSuiteProjectAbsolutePath.replaceAll("/", "\\\\"), "").replaceAll("\\\\", "/");
				return (new ResourceSetImpl()).getResource(URI.createURI(modelPath), true);
			}
		}
		return null;
	}

	private Message findEquivalentTDLMessage(TestDescription copiedTestCase, int index) {
		EList<Behaviour> behaviors =  ((CompoundBehaviour) copiedTestCase.getBehaviourDescription().getBehaviour()).getBlock().getBehaviour();
		Object[] messages = behaviors.stream().filter(b -> b instanceof Message).toArray();
		if (messages.length > index && messages[index] instanceof Message) {
			return (Message) messages[index];
		}
		return null;
	}
}
