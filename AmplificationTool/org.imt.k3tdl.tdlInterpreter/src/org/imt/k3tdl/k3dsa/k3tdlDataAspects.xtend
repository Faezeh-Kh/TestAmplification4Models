package org.imt.k3tdl.k3dsa

import fr.inria.diverse.k3.al.annotationprocessor.Aspect
import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod
import java.util.ArrayList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.TransactionalEditingDomain
import org.eclipse.emf.transaction.util.TransactionUtil
import org.eclipse.gemoc.dsl.Dsl
import org.etsi.mts.tdl.DataInstance
import org.etsi.mts.tdl.DataInstanceUse
import org.etsi.mts.tdl.DataType
import org.etsi.mts.tdl.DataUse
import org.etsi.mts.tdl.LiteralValueUse
import org.etsi.mts.tdl.Member
import org.etsi.mts.tdl.MemberAssignment
import org.etsi.mts.tdl.ParameterBinding
import org.etsi.mts.tdl.SimpleDataInstance
import org.etsi.mts.tdl.SpecialValueUse
import org.etsi.mts.tdl.StaticDataUse
import org.etsi.mts.tdl.StructuredDataInstance
import org.etsi.mts.tdl.StructuredDataType
import org.imt.tdl.testResult.TDLTestResultUtil

import static extension org.imt.k3tdl.k3dsa.DataInstanceAspect.*
import static extension org.imt.k3tdl.k3dsa.DataInstanceUseAspect.*
import static extension org.imt.k3tdl.k3dsa.DataTypeAspect.*
import static extension org.imt.k3tdl.k3dsa.DataUseAspect.*
import static extension org.imt.k3tdl.k3dsa.MemberAspect.*
import static extension org.imt.k3tdl.k3dsa.MemberAssignmentAspect.*
import static extension org.imt.k3tdl.k3dsa.ParameterBindingAspect.*
import static extension org.imt.k3tdl.k3dsa.StaticDataUseAspect.*
import static extension org.imt.k3tdl.k3dsa.StructuredDataInstanceAspect.*

@Aspect (className = DataType)
class DataTypeAspect{
	def boolean isDynamicType() {
		if (_self instanceof StructuredDataType){
			val StructuredDataType type = _self as StructuredDataType
			//check if the type has a 'dynamic' or 'aspect' annotation itself
			for (i : 0 ..<type.annotation.size){
				val annotation = type.annotation.get(i).key.name.toString
				if (annotation.contains("dynamic") || annotation.contains("aspect")) {
					return true;
				}
			}
			//check if as least one of the members of type has a 'dynamic' or 'aspect' annotation
			for (i : 0 ..<type.member.size){
				val Member m = type.member.get(i)
				for (j : 0 ..<m.annotation.size){
					val annotation = m.annotation.get(j).key.name.toString
					if (annotation.contains("dynamic") || annotation.contains("aspect")) {
						return true;
					}
				}
			}
		}
		return false;
	}
	def boolean isConcreteEcoreType(String DSLPath) {
		var dslRes = (new ResourceSetImpl()).getResource(URI.createURI(DSLPath), true);
		var dsl = dslRes.getContents().get(0) as Dsl;
		if (dsl.getEntry("ecore") !== null) {
			var metamodelPath = dsl.getEntry("ecore").getValue().replaceFirst("resource", "plugin");
			var metamodelRes = (new ResourceSetImpl()).getResource(URI.createURI(metamodelPath), true);
			var metamodelRootElement = metamodelRes.getContents().get(0) as EPackage;
			return metamodelRootElement.EClassifiers.exists[c | c.name.equals(_self.getValidName) && !c.eClass.abstract]
		}
		return false;
	}
	def boolean isAcceptedEvent(String DSLPath) {
		val annotation = _self.annotation.stream.filter(a | a.key.name.equals("AcceptedEvent")).findFirst
		return annotation.isPresent
//		var dslRes = (new ResourceSetImpl()).getResource(URI.createURI(DSLPath), true);
//		var dsl = dslRes.getContents().get(0) as Dsl;
//		if (dsl.getEntry("behavioralInterface") !== null) {
//			var interfacePath = dsl.getEntry("behavioralInterface").getValue().replaceFirst("resource", "plugin");
//			var interfaceRes = (new ResourceSetImpl()).getResource(URI.createURI(interfacePath), true);
//			var BehavioralInterface interfaceRootElement= interfaceRes.getContents().get(0) as BehavioralInterface;
//			return interfaceRootElement.events.exists[e | e.name.equals(_self.getValidName) && e.type.getName().equals("ACCEPTED") ]
//		}
//		return false;
	}
	def boolean isExposedEvent(String DSLPath) {
		val annotation = _self.annotation.stream.filter(a | a.key.name.equals("ExposedEvent")).findFirst
		return annotation.isPresent
//		var dslRes = (new ResourceSetImpl()).getResource(URI.createURI(DSLPath), true);
//		var dsl = dslRes.getContents().get(0) as Dsl;
//		if (dsl.getEntry("behavioralInterface") !== null) {
//			var interfacePath = dsl.getEntry("behavioralInterface").getValue().replaceFirst("resource", "plugin");
//			var interfaceRes = (new ResourceSetImpl()).getResource(URI.createURI(interfacePath), true);
//			var BehavioralInterface interfaceRootElement= interfaceRes.getContents().get(0) as BehavioralInterface;
//			return interfaceRootElement.events.exists[e | e.name.equals(_self.getValidName) && e.type.getName().equals("EXPOSED")]
//		}
//		return false;
	}
	
	def String getValidName(){
		var tdlName = _self.name
		if (_self.name.startsWith("_")){
			return tdlName.substring(1)
		}
		return tdlName
	}
}
@Aspect (className = DataInstance)
class DataInstanceAspect{
	public String info = null
	def EObject getMatchedMUTElement(ArrayList<EObject> rootElement, Resource MUTResource, boolean isAssertion, String DSLPath){
		
	}
	
	def String getValidName(){
		var tdlName = _self.name
		if (_self.name.startsWith("_")){
			tdlName = tdlName.substring(1)
		}
		if (_self instanceof StructuredDataInstance){
			val sDataInstance = _self as StructuredDataInstance
			if (sDataInstance.memberAssignment.size>0){
				val ma = sDataInstance.memberAssignment.findFirst[ma | ma.member.name.equals("_name")]
				if (ma !== null && ma.memberSpec instanceof LiteralValueUse){
					tdlName = (ma.memberSpec as LiteralValueUse).value
				}
			}
		}
		return tdlName
	}
}
@Aspect (className = SimpleDataInstance)
class SimpleDataInstanceAspect extends DataInstanceAspect{
	@OverrideAspectMethod
	def EObject getMatchedMUTElement(ArrayList<EObject> rootElement, Resource MUTResource, boolean isAssertion, String DSLPath){
		println("The " + _self.name + " element cannot be found since it has no identifier")
		_self.info = TDLTestResultUtil.FAIL + ": The " + _self.name + " element cannot be found since it has no identifier"
		return null;
	}
}
@Aspect (className = StructuredDataInstance)
class StructuredDataInstanceAspect extends DataInstanceAspect{
	public ArrayList<EObject> matchedElements = new ArrayList
	@OverrideAspectMethod
	def EObject getMatchedMUTElement(ArrayList<EObject> rootElement, Resource MUTResource, boolean isAssertion, String DSLPath){
		//find matched elements based on the memberAssignments of dataInstance
		_self.matchedElements.clear
		for (i:0 ..<rootElement.size){	
			var boolean memberFound = true	
			try{
				_self.memberAssignment.forEach[memberAssign |
					_self.info = memberAssign.isMatchedMember(rootElement.get(i), MUTResource, isAssertion, DSLPath)
					if(_self.info.contains(TDLTestResultUtil.FAIL)){
						throw new InterruptedException()
					}
				]
			}catch (InterruptedException e) {
			    memberFound = false
			}
			if (memberFound){
				_self.matchedElements.add(rootElement.get(i))
			}			
		}
		if (_self.matchedElements.empty){
			return null
		}
		return _self.matchedElements.get(0)
	}

	def String setMatchedMUTElement(EObject matchedObject, Resource MUTResource, String DSLPath){
		var String status = ""
		for (i : 0 ..<_self.memberAssignment.size){
			var memberAssig = _self.memberAssignment.get(i)
			if (memberAssig.member.dataType.isDynamicType
				|| memberAssig.member.isDynamicMember){
					status = memberAssig.setMatchedMember(matchedObject, MUTResource, DSLPath)
			}
			else if (memberAssig instanceof DataInstanceUse){
				val data = memberAssig.memberSpec as DataInstanceUse
				if (memberAssig.hasItems){
					for (j : 0 ..<data.item.size){
						val item = data.item.get(j) as DataInstanceUse
						val rootObject = item.getMatchedMUTElement(MUTResource, false, DSLPath)
						status = (data.item.get(j) as DataInstanceUse).setMatchedMUTElement(rootObject, MUTResource, DSLPath)
						if (status.contains(TDLTestResultUtil.FAIL)){
							return status
						}
					}
				}else{
					val rootObject = data.getMatchedMUTElement(MUTResource, false, DSLPath)
					status = data.setMatchedMUTElement(rootObject, MUTResource, DSLPath)
					if (status.contains(TDLTestResultUtil.FAIL)){
						return status
					}
				}
			}
			if (status.contains(TDLTestResultUtil.FAIL)){
				return status
			}
		}
		return status
	}
}
@Aspect (className = DataInstanceUse)
class DataInstanceUseAspect extends StaticDataUseAspect{
	def EObject getMatchedMUTElement(Resource MUTResource, boolean isAssertion, String DSLPath){
		var ArrayList<EObject> rootElement = new ArrayList
		//if data type is abstract return null
		if (!_self.dataInstance.dataType.isConcreteEcoreType(DSLPath)){
			println("The " + _self.dataInstance.name + " element is abstract")
			_self.dataInstance.info = TDLTestResultUtil.FAIL + ": The " + _self.dataInstance.name + " element is abstract"
			return null;
		}
		val dataTypeName = _self.dataInstance.dataType.validName
		rootElement.add(MUTResource.getContents().get(0))
		if (!rootElement.get(0).eClass.name.equals(dataTypeName)){
			var container = rootElement.get(0)
			rootElement.remove(0)
			rootElement.addAll(container.eAllContents.filter[object | object.eClass.name.equals(dataTypeName)].toList)
		}
		return _self.getMatchedMUTElement(rootElement, MUTResource, isAssertion, DSLPath)
	}
	
	def EObject getMatchedMUTElement(ArrayList<EObject> rootElement, Resource MUTResource, boolean isAssertion, String DSLPath){
		var ArrayList<EObject> containers = new ArrayList
		var EObject matchedElement = null
		if (_self.dataInstance instanceof StructuredDataInstance){
			//some attributes are set as member assignments for dataInstance
			//so find the matched element based on the dataInstance
			val dataIns = _self.dataInstance as StructuredDataInstance
			if (dataIns.memberAssignment.size>0){//if some values are assigned to the members of data instance
				matchedElement = dataIns.getMatchedMUTElement(rootElement, MUTResource, isAssertion, DSLPath)
				if (matchedElement === null){
					_self.dataInstance.info = TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + dataIns.name
					return null
				}
			}	
			containers = dataIns.matchedElements
		}
		//when the program reach to this line, it means the values assigned as parameter bindings has to be checked
		//therefore, all the elements identified as root must be checked to find the matched element
		//find matched elements based on the parameter bindings of dataInstanceUse
		for (i:0 ..<containers.size){
			_self.dataInstance.info = _self.isMatchedParametrizedElement(containers.get(i), MUTResource, isAssertion, DSLPath)
			if (_self.dataInstance.info.contains(TDLTestResultUtil.PASS)){
					return containers.get(i)
			}
		}
		return null
	}
	
	def String isMatchedParametrizedElement(EObject rootElement, Resource MUTResource, boolean isAssertion, String DSLPath){
		//find matched element based on the parameter bindings of dataInstance
		for (i : 0 ..<_self.argument.size){
			val parameterBinding = _self.argument.get(i);
			val status = parameterBinding.isMatchedParameter(rootElement, MUTResource, isAssertion, DSLPath)
			if (status.contains(TDLTestResultUtil.FAIL)){
				return status;
			}		
		}
		return TDLTestResultUtil.PASS;
	}

	def String setMatchedMUTElement(Resource MUTResource, String DSLPath){
		//the second parameter is isAssertion that has to be set as false
		//so only static elements will be matched to then set the values of its dynamic features
		var EObject matchedObject = _self.getMatchedMUTElement(MUTResource, false, DSLPath)
		if (matchedObject === null){
			println("There is no matched object in the model under test")
			return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.dataInstance.name
		}
		_self.setMatchedMUTElement(matchedObject, MUTResource, DSLPath)
	}
	
	def String setMatchedMUTElement(EObject matchedObject, Resource MUTResource, String DSLPath){
		var String status = "";
		if (_self.dataInstance instanceof StructuredDataInstance){			
			//some attributes are set as member assignments for dataInstance
			//so find the matched element based on the dataInstance
			val dataIns = _self.dataInstance as StructuredDataInstance
			status = dataIns.setMatchedMUTElement(matchedObject, MUTResource, DSLPath)
			if (status.contains(TDLTestResultUtil.FAIL)){
				return status
			}
		}
		//find matched elements based on the parameter bindings of dataInstanceUse
		for (i : 0 ..<_self.argument.size){
			var parameterBinding = _self.argument.get(i);
			if (parameterBinding.parameter.dataType.isDynamicType
				|| (parameterBinding.parameter as Member).isDynamicMember){
				status = parameterBinding.setMatchedParameter(matchedObject, MUTResource, DSLPath);
			}
			else if (parameterBinding.dataUse instanceof DataInstanceUse){
				val data = parameterBinding.dataUse as DataInstanceUse
				if (parameterBinding.hasItems){
					for (j : 0 ..<data.item.size){
						val item = data.item.get(j) as DataInstanceUse
						val rootObject = item.getMatchedMUTElement(MUTResource, false, DSLPath)
						status = (data.item.get(j) as DataInstanceUse).setMatchedMUTElement(rootObject, MUTResource, DSLPath)
						if (status.contains(TDLTestResultUtil.FAIL)){
							return status
						}
					}
				}else{
					val rootObject = data.getMatchedMUTElement(MUTResource, false, DSLPath)
					status = data.setMatchedMUTElement(rootObject, MUTResource, DSLPath)
					if (status.contains(TDLTestResultUtil.FAIL)){
						return status
					}
				}
			}
			if (status.contains(TDLTestResultUtil.FAIL)){
				return status
			}			
		}
		return status
	}
	
	def EObject createEObject(Resource MUTResource, boolean isAssertion, String DSLPath){
		val ModelEObjectCreator objectCreator = new ModelEObjectCreator
		val eobject = objectCreator.createEObject(_self, MUTResource, isAssertion, DSLPath)
		return eobject
	}
	
	@OverrideAspectMethod
	def String assertEquals(Resource MUTResource, Object featureValue, Boolean isAssertion, String DSLPath){
		var ArrayList<EObject> rootObjects = new ArrayList
		if (featureValue instanceof EList){
			rootObjects.addAll(featureValue)
		}else if(featureValue instanceof EObject){
			rootObjects.add(featureValue as EObject)
		}
		
		val ArrayList<EObject> matchedObjects = new ArrayList
		var String expectedData = "";
		var String mutData = "";
		if (_self.item !== null && _self.item.size > 0){//there are several instances of data
			mutData = TDLTestResultUtil.getInstance.getDataAsString(featureValue as EList<?>)
			for (i : 0 ..<_self.item.size){
				val matchedObject = (_self.item.get(i) as DataInstanceUse).getMatchedMUTElement(rootObjects, MUTResource, isAssertion, DSLPath)			
				matchedObjects.add(matchedObject)
				expectedData += ((_self.item.get(i) as DataInstanceUse).dataInstance.name + ", ")
			}
			expectedData = "[" + expectedData.substring(0, expectedData.length-2) + "]"
			if (mutData.equals("[]") && !expectedData.isNullOrEmpty){
				return TDLTestResultUtil.FAIL + ": The expected data is: " + expectedData + ", but the current data is NULL";
			}
			if (isAssertion){
				if ((featureValue as EList<?>).equals(matchedObjects)){
					return TDLTestResultUtil.PASS + ": The expected data is equal to the current data"
				}
			}else{
				if ((featureValue as EList<?>).containsAll(matchedObjects)){
					return TDLTestResultUtil.PASS + ": The expected data contains the current data"
				}
			}		
			return TDLTestResultUtil.FAIL + ": The expected data is: " + expectedData + ", but the current data is: " + mutData;
		}else{//there is just one data instance
			val matchedObject = _self.getMatchedMUTElement(rootObjects, MUTResource, isAssertion, DSLPath)
			if (matchedObject === null){
				expectedData =  _self.dataInstance.name
			}else{
				expectedData = "[" + TDLTestResultUtil.getInstance.eObjectLabelProvider(matchedObject as EObject) + "]"
			}
			if (featureValue instanceof EList){
				if (matchedObject !== null && (featureValue as EList<?>).contains(matchedObject)){
					return TDLTestResultUtil.PASS + ": The expected data contains in the current data"
				}
				mutData = "[" + TDLTestResultUtil.getInstance.getDataAsString(featureValue as EList<?>) + "]"
			}else if (featureValue instanceof EObject){
				if (matchedObject !== null && (featureValue as EObject).equals(matchedObject)){
					return TDLTestResultUtil.PASS + ": The expected data is equal to the current data"
				}
				mutData = "[" + TDLTestResultUtil.getInstance.eObjectLabelProvider(featureValue as EObject) + "]"
			}
			return TDLTestResultUtil.FAIL + ": The expected data is: " + expectedData + ", but the current data is: " + mutData;
		}
	}
	
	@OverrideAspectMethod
	def String updateData(Resource MUTResource, EObject object, EStructuralFeature matchedFeature, String DSLPath){
		if (_self.item !== null && _self.item.size > 0){//there are several instances of data
			val TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(object);
			try{
				domain.getCommandStack().execute(new RecordingCommand(domain) {
			        override protected doExecute() {
			        	var ArrayList<EObject> rootElements = new ArrayList
						rootElements.add(object)
			        	val ArrayList<EObject> matchedObjects = new ArrayList
						for (i : 0 ..<_self.item.size){
							val item = _self.item.get(i) as DataInstanceUse
							val matchedObject = item.getMatchedMUTElement(rootElements, MUTResource, false, DSLPath)			
							if (matchedObject === null){
								println("There is no " + item.dataInstance.name + " property in the MUT")
							}else{
								_self.setMatchedMUTElement(matchedObject, MUTResource, DSLPath)
								matchedObjects.add(matchedObject)
							}							
						}
						if (matchedObjects.size == _self.item.size){
							object.eSet(matchedFeature, matchedObjects)
						}		        											
			        }
		   		});
	   		}catch(IllegalArgumentException e){
				println("FAIL: The new value cannot be set for the " + matchedFeature.name + " property of the MUT")
				return TDLTestResultUtil.FAIL + ": The new value cannot be set for the " + matchedFeature.name + " property of the MUT"
			}
			catch(NullPointerException e){
				var ArrayList<EObject> rootElements = new ArrayList
				rootElements.add(object)
	        	val ArrayList<EObject> matchedObjects = new ArrayList
				for (i : 0 ..<_self.item.size){
					val item = _self.item.get(i) as DataInstanceUse
					val matchedObject = item.getMatchedMUTElement(rootElements, MUTResource, false, DSLPath)			
					if (matchedObject === null){
						println("There is no " + item.dataInstance.name + " property in the MUT")
					}else{
						_self.setMatchedMUTElement(matchedObject, MUTResource, DSLPath)
						matchedObjects.add(matchedObject)
					}							
				}
				if (matchedObjects.size == _self.item.size){
					object.eSet(matchedFeature, matchedObjects)
				}
			}
		}else{//there is just one data instance
			val matchedObject = _self.getMatchedMUTElement(MUTResource, false, DSLPath)
			if (matchedObject === null){
				println("There is no " + _self.dataInstance.name + " property in the MUT")
				return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.dataInstance.name
			}
			try{
				val TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(object);
				domain.getCommandStack().execute(new RecordingCommand(domain) {
			        override protected doExecute() {
			        	var ArrayList<EObject> rootElements = new ArrayList
						rootElements.add(object)
			        	val matchedObject = _self.getMatchedMUTElement(MUTResource, false, DSLPath)
			        	_self.setMatchedMUTElement(matchedObject, MUTResource, DSLPath)
			        	if (matchedFeature.many){
			        		val ArrayList<EObject> matchedObjects = new ArrayList
			        		matchedObjects.add(matchedObject)
			        		object.eSet(matchedFeature, matchedObjects)
			        	}else{
			        		object.eSet(matchedFeature, matchedObject)
			        	}										
			        }
		   		});
	   		}catch(IllegalArgumentException e){
				println("New value cannot be set for the " + matchedFeature.name + " property of the MUT")
				return TDLTestResultUtil.FAIL + ": New value cannot be set for the " + matchedFeature.name + " property of the MUT"
			}catch(NullPointerException e){
				var ArrayList<EObject> rootElements = new ArrayList
				rootElements.add(object)
	        	_self.setMatchedMUTElement(matchedObject, MUTResource, DSLPath)
	        	if (matchedFeature.many){
	        		val ArrayList<EObject> matchedObjects = new ArrayList
	        		matchedObjects.add(matchedObject)
	        		object.eSet(matchedFeature, matchedObjects)
	        	}else{
	        		object.eSet(matchedFeature, matchedObject)
	        	}
			}
		}
		return TDLTestResultUtil.PASS + ": New value is set for the " + matchedFeature.name + " property of the MUT"
	}
}
@Aspect (className = MemberAssignment)
class MemberAssignmentAspect{
	def String isMatchedMember(EObject rootElement, Resource MUTResource, Boolean isAssertion, String DSLPath){
		val EStructuralFeature matchedFeature = _self.member.getMatchedFeature(rootElement)	
		if (matchedFeature === null){
			println("There is no " + _self.member.name + " property in the MUT")
			return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.member.name
		}
		val featureValue = rootElement.eGet(matchedFeature)
		if (!isAssertion && _self.member.isDynamicMember){
			return TDLTestResultUtil.PASS
		}
		//Assert the data instances of the member assignment
		if (_self.memberSpec instanceof DataInstanceUse){
			return _self.memberSpec.assertEquals(MUTResource, featureValue, isAssertion, DSLPath)
		}
		return _self.memberSpec.assertEquals(featureValue)
	} 
	
	def String setMatchedMember(EObject rootElement, Resource MUTResource, String DSLPath){
		val EStructuralFeature matchedFeature = _self.member.getMatchedFeature(rootElement)	
		if (_self.memberSpec instanceof DataInstanceUse){
			return _self.memberSpec.updateData(MUTResource, rootElement, matchedFeature, DSLPath)
		}
		return _self.memberSpec.updateData(rootElement, matchedFeature)
	} 
	
	def Boolean hasItems(){
		val data = _self.memberSpec as DataInstanceUse
		if (data.item !== null && data.item.size >0){
			return true
		}
		return false
	}
}
@Aspect (className = ParameterBinding)
class ParameterBindingAspect{
	
	def String isMatchedParameter(EObject rootElement, Resource MUTResource, Boolean isAssertion, String DSLPath){
		val EStructuralFeature matchedFeature = (_self.parameter as Member).getMatchedFeature(rootElement) 
		if (matchedFeature === null){
			println("There is no " + _self.parameter.name + " property in the MUT")
			return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.parameter.name
		}
		val featureValue = rootElement.eGet(matchedFeature)
		if (!isAssertion && (_self.parameter as Member).isDynamicMember){
			return TDLTestResultUtil.PASS
		}
		if (_self.dataUse instanceof DataInstanceUse){
			return _self.dataUse.assertEquals(MUTResource, featureValue, isAssertion, DSLPath)
		}
		return _self.dataUse.assertEquals(featureValue)
	} 
	
	def String setMatchedParameter(EObject rootElement, Resource MUTResource, String DSLPath){
		val EStructuralFeature matchedFeature = (_self.parameter as Member).getMatchedFeature(rootElement)
		if (_self.dataUse instanceof DataInstanceUse){
			return _self.dataUse.updateData(MUTResource, rootElement, matchedFeature, DSLPath)
		}
		return  _self.dataUse.updateData(rootElement, matchedFeature)
	} 
	
	def Boolean hasItems(){
		val data = _self.dataUse as DataInstanceUse
		if (data.item !== null && data.item.size >0){
			return true
		}
		return false
	}
}
@Aspect (className = Member)
class MemberAspect{
	
	def EStructuralFeature getMatchedFeature(EObject rootElement){
		val EStructuralFeature matchedFeature = rootElement.eClass.EAllStructuralFeatures.
				findFirst[f | f.name.equals(_self.validName)]
		return matchedFeature	
	}
	
	def String getValidName(){
		var tdlName = _self.name
		if (_self.name.startsWith("_")){
			return tdlName.substring(1)
		}
		return tdlName
	}
	
	def boolean isDynamicMember() {
		for (j : 0 ..<_self.annotation.size){
			val annotation = _self.annotation.get(j).key.name.toString
			if (annotation.contains("dynamic") || annotation.contains("aspect")) {
				return true
			}
		}
		return false
	}
}
@Aspect (className = DataUse)
class DataUseAspect{
	def String assertEquals(Object featureValue){
		return "";
	}
	def String assertEquals(Resource MUTResource, Object featureValue, Boolean isAssertion, String DSLPath){
		return "";
	}
	def String updateData(EObject object, EStructuralFeature matchedFeature){
		return "";
	}
	def String updateData(Resource MUTResource, EObject object, EStructuralFeature matchedFeature, String DSLPath){
		return "";
	}
}
@Aspect (className = StaticDataUse)
class StaticDataUseAspect extends DataUseAspect{
	@OverrideAspectMethod
	def String assertEquals(Object featureValue){
		return "";
	}
	@OverrideAspectMethod
	def String assertEquals(Resource MUTResource, Object featureValue, Boolean isAssertion, String DSLPath){
		return "";
	}
	@OverrideAspectMethod
	def String updateData(EObject object, EStructuralFeature matchedFeature){
		return "";
	}
	@OverrideAspectMethod
	def String updateData(Resource MUTResource, EObject object, EStructuralFeature matchedFeature, String DSLPath){
		return "";
	}
}
@Aspect (className = LiteralValueUse)
class LiteralValueUseAspect extends StaticDataUseAspect{
	def Object getPrimitiveValue(String primitiveTypeName){
		var String parameterValue = _self.value
		if (parameterValue.startsWith("\"") || parameterValue.startsWith("'")){
	        parameterValue = parameterValue.substring(1, parameterValue.length-1)//remove quotation marks
	    }
		if (primitiveTypeName.equals("EInt") || primitiveTypeName.equals("EIntegerObject")){
			return Integer.parseInt(parameterValue)
		}
		else if (primitiveTypeName.equals("EBoolean") || primitiveTypeName.equals("EBooleanObject")){
			return Boolean.parseBoolean(parameterValue)
		}
		else if (primitiveTypeName.equals("EString")){
			return parameterValue
		}
		return null;
	}
	@OverrideAspectMethod
	def String assertEquals(Object featureValue){
		var String parameterValue = _self.value
		if (parameterValue.startsWith("\"") || parameterValue.startsWith("'")){
	        parameterValue = parameterValue.substring(1, parameterValue.length-1)//remove quotation marks
	    }
		if (featureValue === null && (parameterValue == "null" || parameterValue.isNullOrEmpty)){
			return TDLTestResultUtil.PASS + ": The expected data is equal to the current data"
		}
		else if (featureValue.toString.equals(parameterValue)){
			return TDLTestResultUtil.PASS + ": The expected data is equal to the current data"
		}
		return TDLTestResultUtil.FAIL + ": The expected data is: " + parameterValue + ", but the current data is: " + featureValue
	}

	@OverrideAspectMethod
	def String updateData(EObject object, EStructuralFeature matchedFeature){
		val TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(object);
		try{
			domain.getCommandStack().execute(new RecordingCommand(domain) {
	        override protected doExecute() {
	        	var parameterValue = _self.value
	        	if (parameterValue.startsWith("\"") || parameterValue.startsWith("'")){
	        		parameterValue = parameterValue.substring(1, parameterValue.length-1)//remove quotation marks
	        	}
				if (matchedFeature.EType.name.equals("EInt") || matchedFeature.EType.name.equals("EIntegerObject")){
					object.eSet(matchedFeature, Integer.parseInt(parameterValue));
				} else if (matchedFeature.EType.name.equals("EBoolean")|| matchedFeature.EType.name.equals("EBooleanObject")){
					object.eSet(matchedFeature, Boolean.parseBoolean(parameterValue));
				} else {
					object.eSet(matchedFeature, parameterValue);
				}     										
	        }
   			});
		}
		catch(IllegalArgumentException e){
			println("FAIL: The new value cannot be set for the " + matchedFeature.name + " property of the MUT")
			return TDLTestResultUtil.FAIL + ": The new value cannot be set for the " + matchedFeature.name + " property of the MUT"
		}
		catch(NullPointerException e){//there is no editing domain, so set the value directly
			var parameterValue = _self.value
        	if (parameterValue.startsWith("\"") || parameterValue.startsWith("'")){
        		parameterValue = parameterValue.substring(1, parameterValue.length-1)//remove quotation marks
        	}
			if (matchedFeature.EType.name.equals("EInt") || matchedFeature.EType.name.equals("EIntegerObject")){
				object.eSet(matchedFeature, Integer.parseInt(parameterValue));
			} else if (matchedFeature.EType.name.equals("EBoolean")|| matchedFeature.EType.name.equals("EBooleanObject")){
				object.eSet(matchedFeature, Boolean.parseBoolean(parameterValue));
			} else {
				object.eSet(matchedFeature, parameterValue);
			} 
		}
		
		return TDLTestResultUtil.PASS + ": New value is set for the " + matchedFeature.name + " property of the MUT"
	}
}
@Aspect (className = SpecialValueUse)
class SpecialValueUseAspect extends StaticDataUseAspect{
	@OverrideAspectMethod
	def String assertEquals(Object featureValue){
		//the value is ('?' or '*' or 'omit') that should be ignored
		return TDLTestResultUtil.PASS;
	}
	@OverrideAspectMethod
	def String updateData(EObject object, EStructuralFeature matchedFeature){
		//the value is ('?' or '*' or 'omit') that should be ignored
		return TDLTestResultUtil.PASS;
	}
}