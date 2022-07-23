package org.imt.k3tdl.k3dsa

import fr.inria.diverse.k3.al.annotationprocessor.Aspect
import java.util.ArrayList
import java.util.HashMap
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.etsi.mts.tdl.DataInstanceUse
import org.etsi.mts.tdl.DataUse
import org.etsi.mts.tdl.GateInstance
import org.etsi.mts.tdl.GateType
import org.etsi.mts.tdl.LiteralValueUse
import org.imt.tdl.configuration.EngineFactory
import org.imt.tdl.testResult.TDLTestResultUtil

import static extension org.imt.k3tdl.k3dsa.DataInstanceAspect.*
import static extension org.imt.k3tdl.k3dsa.DataInstanceUseAspect.*
import static extension org.imt.k3tdl.k3dsa.DataTypeAspect.*
import static extension org.imt.k3tdl.k3dsa.LiteralValueUseAspect.*

@Aspect(className=GateType)
class GateTypeAspect {
}

@Aspect(className=GateInstance)
class GateInstanceAspect {
	public String MUTPath = ""
	public String DSLPath = ""
	
	Object receivedOutput = null
	Object expectedOutput = null
	
	EngineFactory gateLauncher
	
	final static String RUN_MODEL = "runModel"
	final static String RUN_MODEL_ASYNC = "runModelAsynchronous"
	final static String STOP_EXECUTION = "stopModelExecution"
	final static String RESET_MODEL = "resetModel"
	final static String GET_MODEL = "getModelState"
	final static String OCL_TYPE = "OCL"
	final static String OCL_GATE = "oclGate";
	final static String ACCEPTED_EVENT = "ACCEPTED";
	final static String EXPOSED_EVENT = "EXPOSED";
	
	def void setLauncher(EngineFactory launcher) {
		_self.gateLauncher = launcher;
	}

	def String assertArgument(DataUse argument) {
		//if the argument is a string
		if (argument instanceof LiteralValueUse){			
			var expected = (argument as LiteralValueUse).value
			expected = expected.substring(1, expected.length - 1) // remove the quotation marks
			_self.expectedOutput = expected;
			//when asserting ocl query validation result:
			//if the expected result is String, use the labeled result of validation 
			if (_self.name.equals(OCL_GATE)){
				_self.receivedOutput = _self.gateLauncher.OCLResultAsString
			}
			if (_self.receivedOutput !== null) {
				if (_self.gateLauncher.OCLResultAsString.size==1){
					val String result = _self.gateLauncher.OCLResultAsString.get(0)
					_self.receivedOutput = result.subSequence(1, result.length-1)
				}
				if (_self.receivedOutput.toString.equals(_self.expectedOutput.toString)){
					return TDLTestResultUtil.PASS + ": The expected data is equal to the current data"
				}else{
					return TDLTestResultUtil.FAIL + ": The expected data is: " + _self.expectedOutput.toString + 
						", but the current data is: " + _self.receivedOutput.toString;
				}
			} else if (_self.receivedOutput === null) {
				return TDLTestResultUtil.FAIL + ": The expected data is: " + _self.expectedOutput.toString + 
						", but the current data is: null";
			} else {
				return TDLTestResultUtil.FAIL + ": The expected data is: " + _self.expectedOutput.toString + 
						", but the current data is: " + _self.receivedOutput.toString;
			}
		}
		//if the argument is an element/a list of elements
		else if (argument instanceof DataInstanceUse){
			val arg = argument as DataInstanceUse
			var Resource MUTResource = null;
			if (_self.receivedOutput instanceof Resource){
				MUTResource = _self.receivedOutput as Resource//the MUTResource is the received output
			}
			if (_self.name.equals(OCL_GATE)){
				MUTResource = _self.gateLauncher.MUTResource//the MUT objects are the received output
			}
			else if ((arg.item === null || arg.item.size <= 0) 
				&& arg.dataInstance.dataType.isExposedEvent(_self.DSLPath)){
				//the message is an event conforming to the behavioral interface of the DSL
				val eventParameters = _self.getEventParameters(arg, EXPOSED_EVENT)
				if (eventParameters === null){
					return TDLTestResultUtil.INCONCLUSIVE + ": The event parameters cannot be neither found in the model under test nor created. \n"+
					"There must be a syntactical problem in the test data"
				}
				return _self.gateLauncher.executeDSLSpecificCommand(EXPOSED_EVENT,arg.dataInstance.validName, eventParameters)
			}	
			var String status = null
			var ArrayList<EObject> matchedMUTElements = new ArrayList<EObject>();
			if (arg.item !== null && arg.item.size > 0){//there is a list of objects in the expected output
				for (i : 0 ..<arg.item.size){
					val DataInstanceUse data = (arg.item.get(i) as DataInstanceUse)
					val EObject matchedObject = data.getMatchedMUTElement(MUTResource as Resource, true, _self.DSLPath)		
					if (matchedObject === null){
						return data.dataInstance.info //the info must contains FAIL information			
					}else{
						matchedMUTElements.add(matchedObject)
						status = data.dataInstance.info
					}	
				}
			}else{//there is only one object in the expected output
				val DataInstanceUse data = (arg as DataInstanceUse)
				val EObject matchedObject = data.getMatchedMUTElement(MUTResource as Resource, true, _self.DSLPath)
				if (matchedObject === null){
					return data.dataInstance.info //the info must contains FAIL information
				}else{
					matchedMUTElements.add(matchedObject)
					status = data.dataInstance.info	
				}
			}
			if(_self.name.equals(OCL_GATE)){
				val Object[] receivedObjects = _self.gateLauncher.OCLResultAsObject
				if (receivedObjects.elementsEqual(matchedMUTElements)){
					return TDLTestResultUtil.PASS + ": The expected data is equal to the current data"
				}else{
					var expectedData = TDLTestResultUtil.getInstance.getDataAsString(matchedMUTElements)
					return TDLTestResultUtil.FAIL + ": The expected data is: " + expectedData + 
						", but the current data is: " + _self.gateLauncher.OCLResultAsString;
				}
			}else{
				return status
			}
		}
	}

	def String sendArgument2sut(DataUse argument) {
		if (argument instanceof DataInstanceUse) {
			var arg = (argument as DataInstanceUse)
			if (arg.item !== null && arg.item.size > 0){
				return _self.setModelState(arg);
			}else if (arg.dataInstance.dataType.isConcreteEcoreType(_self.DSLPath)){//request for setting the model state
				return _self.setModelState(arg);
			}else if (arg.dataInstance.name == RUN_MODEL) {
				//println("--Start MUT Execution synchronous:")
				return _self.gateLauncher.executeModel(true)
			}else if (arg.dataInstance.name == RUN_MODEL_ASYNC) {
				//println("--Start MUT Execution Asynchronous:")
				return _self.gateLauncher.executeModel(false)
			}else if (arg.dataInstance.name == STOP_EXECUTION) {
				//println("--Stop Asynchronous MUT Execution")
				return _self.gateLauncher.stopAsyncExecution
			}else if (arg.dataInstance.name == RESET_MODEL) {
				_self.gateLauncher.MUTResource = 
					(new ResourceSetImpl()).getResource(URI.createURI(_self.MUTPath), true)
				return TDLTestResultUtil.PASS + ": The MUT is reset to its initial state"
			}else if (arg.dataInstance.name == GET_MODEL) {
				_self.receivedOutput = _self.gateLauncher.MUTResource
				return TDLTestResultUtil.PASS + ": The current state of the MUT is retrieved"
			}else if (arg.dataInstance.dataType.name == OCL_TYPE) {
				// extracting the query from the argument and sending for validation
				var query = argument.argument.get(0).dataUse as LiteralValueUse
				return _self.gateLauncher.executeOCLCommand(query.value)				
			}else if (arg.dataInstance.dataType.isAcceptedEvent(_self.DSLPath)){
				//the message is an event conforming to the behavioral interface of the DSL
				val eventParameters = _self.getEventParameters(arg, ACCEPTED_EVENT)
				if (eventParameters === null){
					return TDLTestResultUtil.FAIL + ": The event parameters cannot be neither found in the model under test nor created. \n"+
					"There must be a syntactical problem in the test data"
				}
				return _self.gateLauncher.executeDSLSpecificCommand(ACCEPTED_EVENT, arg.dataInstance.validName, eventParameters)
			}
			return TDLTestResultUtil.FAIL + ": Cannot send data to the MUT"
		}
		return TDLTestResultUtil.FAIL + ": Cannot send data to the MUT"
	}
	
	def String setModelState(DataInstanceUse arg){
		//get the current MUTResource
		var MUTResource = _self.gateLauncher.MUTResource;
		var String status = null
		if (arg.item !== null && arg.item.size > 0){
			for (i : 0 ..<arg.item.size){
				status = (arg.item.get(i) as DataInstanceUse).setMatchedMUTElement(MUTResource, _self.DSLPath)
				if (status.contains(TDLTestResultUtil.FAIL)){
					return status
				}
			}
		}else{
			status = arg.setMatchedMUTElement(MUTResource, _self.DSLPath);
		}
		return status
	}
	//retrieve a map of the parameter name and its corresponding model element
	def Map<String, Object> getEventParameters(DataInstanceUse event, String eventType){
		var Map<String, Object> parameters = new HashMap;
		for (i : 0 ..<event.argument.size){//the parameterBindings of the event
			val argName = event.argument.get(i).parameter.name
			var Object argValue = null
			if (event.argument.get(i).dataUse instanceof DataInstanceUse){//the parameter is an eobject
				val DataInstanceUse argTdlValue = event.argument.get(i).dataUse as DataInstanceUse
				/*
				 * if the object is conforming to a the xDSL's runtime state definition or has runtime data
				 * (its type or one of its features is annotated as 'dynamic' or 'aspect'),
				 * the object will not be found in the model under test and must be created
				 */
				if (event.dataInstance.dataType.isAcceptedEvent(_self.DSLPath)){
					argValue = argTdlValue.getMatchedMUTElement(_self.gateLauncher.MUTResource, true, _self.DSLPath)
					if (argValue === null){
						argValue = argTdlValue.createEObject(_self.gateLauncher.MUTResource, true, _self.DSLPath)
					}
				}
				else{//is an exposed event
					//when EObjects have dynamic features, the value of their features may change several times
					//so we cannot retrieve the object from the engine.resource, but we should create it using a new resource
					val resource = (new ResourceSetImpl()).getResource(URI.createURI(_self.MUTPath), true);
					argValue = argTdlValue.createEObject(resource, true, _self.DSLPath)
				}
				
				if (argValue === null){
					return null
				}
			}
			else if(event.argument.get(i).dataUse instanceof LiteralValueUse){
				val String tdlParameterTypeName = event.argument.get(i).parameter.dataType.name
				val LiteralValueUse argTdlValue = event.argument.get(i).dataUse as LiteralValueUse
				argValue = argTdlValue.getPrimitiveValue(tdlParameterTypeName)
			}		
			//put the name of the parameter along with its value
			parameters.put(argName, argValue)
		}
		return parameters
	}
}