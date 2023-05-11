package org.imt.k3tdl.interpreter

import fr.inria.diverse.k3.al.annotationprocessor.Aspect

import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod
import java.util.ArrayList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.TransactionalEditingDomain
import org.eclipse.emf.transaction.util.TransactionUtil
import org.etsi.mts.tdl.DataInstance
import org.etsi.mts.tdl.DataInstanceUse
import org.etsi.mts.tdl.DataType
import org.etsi.mts.tdl.DataUse
import org.etsi.mts.tdl.LiteralValueUse
import org.etsi.mts.tdl.Member
import org.etsi.mts.tdl.MemberAssignment
import org.etsi.mts.tdl.Package
import org.etsi.mts.tdl.ParameterBinding
import org.etsi.mts.tdl.SimpleDataInstance
import org.etsi.mts.tdl.SpecialValueUse
import org.etsi.mts.tdl.StaticDataUse
import org.etsi.mts.tdl.StructuredDataInstance
import org.etsi.mts.tdl.StructuredDataType
import org.imt.k3tdl.interpreter.utilities.EObjectFinderUtilities
import org.imt.tdl.testResult.TDLTestResultUtil

import static extension org.imt.k3tdl.interpreter.DataInstanceAspect.*
import static extension org.imt.k3tdl.interpreter.DataInstanceUseAspect.*
import static extension org.imt.k3tdl.interpreter.DataTypeAspect.*
import static extension org.imt.k3tdl.interpreter.DataUseAspect.*
import static extension org.imt.k3tdl.interpreter.MemberAspect.*
import static extension org.imt.k3tdl.interpreter.MemberAssignmentAspect.*
import static extension org.imt.k3tdl.interpreter.ParameterBindingAspect.*
import static extension org.imt.k3tdl.interpreter.StructuredDataInstanceAspect.*

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
	
	
	def boolean isConcreteEcoreType() {
		val annotation = _self.annotation.stream.filter(
			a | a.key.name.equals("abstract")
		).findFirst
		return !annotation.isPresent
	}
	
	def boolean isAcceptedEvent() {
		val annotation = _self.annotation.stream.filter(
			a | a.key.name.equals("AcceptedEvent")
		).findFirst
		return annotation.isPresent
	}
	
	def boolean isExposedEvent() {
		val annotation = _self.annotation.stream.filter(
			a | a.key.name.equals("ExposedEvent")
		).findFirst
		return annotation.isPresent
	}
	
	def String getValidName(){
		if (_self.name.startsWith("_")){
			return _self.name.substring(1)
		}
		return _self.name
	}
}
@Aspect (className = DataInstance)
class DataInstanceAspect{
	public String info = null
	
	def ArrayList<EObject> getMatchedMUTElement(ArrayList<EObject> rootElement, boolean isAssertion, Resource MUTResource){
		
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
	def ArrayList<EObject> getMatchedMUTElement(ArrayList<EObject> rootElement, boolean isAssertion, Resource MUTResource){
		println("The " + _self.name + " element cannot be found since it has no identifier")
		_self.info = TDLTestResultUtil.FAIL + ": The " + _self.name + " element cannot be found since it has no identifier"
		return null;
	}
}
@Aspect (className = StructuredDataInstance)
class StructuredDataInstanceAspect extends DataInstanceAspect{
	
	@OverrideAspectMethod
	def ArrayList<EObject> getMatchedMUTElement(ArrayList<EObject> rootElement, boolean isAssertion, Resource MUTResource){
		//find matched elements based on the memberAssignments of dataInstance
		var ArrayList<EObject> matchedElements = new ArrayList
		for (i:0 ..<rootElement.size){	
			var boolean memberFound = true	
			try{
				_self.memberAssignment.forEach[memberAssign |
					_self.info = memberAssign.isMatchedMember(rootElement.get(i), isAssertion, MUTResource)
					if(_self.info.contains(TDLTestResultUtil.FAIL)){
						throw new InterruptedException()
					}
				]
			}catch (InterruptedException e) {
			    memberFound = false
			}
			if (memberFound){
				matchedElements.add(rootElement.get(i))
			}			
		}
		return matchedElements
	}

	def EObject getMatchedMUTElementByContainer (ArrayList<EObject> equalEObjectsByFeature){
		val tdlDataContainer = _self.findContainerOfTdlData
		if (tdlDataContainer === null){ 
			return null
		}
		var ArrayList<EObject> equalContainers = new ArrayList
		var EObject matchedEObject = null
 		for (i : 0 ..<equalEObjectsByFeature.size){
 			matchedEObject = equalEObjectsByFeature.get(i)
 			val eobjectContainer = matchedEObject.eContainer
 			if (tdlDataContainer.equals2eobject(eobjectContainer)){
 				equalContainers.add(eobjectContainer)
 				EObjectFinderUtilities.instance.matchedEObjectsByContainer.add(matchedEObject)
 			}
 			else{
 				matchedEObject = null
 			}
 		}
 		if (equalContainers.empty){
 			return null
 		}
 		else if (equalContainers.size==1){
 			return EObjectFinderUtilities.instance.getInitialMatchedEObject(matchedEObject)
 		}
 		else{
 			val tdlUpperContainer = tdlDataContainer.findContainerOfTdlData
 			var ArrayList<EObject> eobjects2check = new ArrayList
 			for (i : 0 ..<equalContainers.size){
 				eobjects2check.add(equalContainers.get(i).eContainer)
 			}
 			return tdlUpperContainer.getMatchedMUTElementByContainer(eobjects2check)
 		}
	}
	
	//in the root tdl package, find a structuredDataInstance that has a memberAssignment with memberSpec = _self.dataInstance
	def StructuredDataInstance findContainerOfTdlData (){
		val tdlPackage = _self.eContainer as Package
		val tdlDataContainer = tdlPackage.packagedElement.findFirst[p | 
			p instanceof StructuredDataInstance
			&& (p as StructuredDataInstance).memberAssignment.exists[ma | 
				ma.member.annotation.exists[a | a.key.name.equals('containment')]
				&& ma.memberSpec instanceof DataInstanceUse 
				&& _self.pointing2data(ma.memberSpec as DataInstanceUse)
			]
		]
		if (tdlDataContainer === null) {
			return null
		}
		return tdlDataContainer as StructuredDataInstance
	}
	
	def boolean pointing2data(DataInstanceUse memberSpec){
		if (memberSpec.dataInstance !== null){
			return (memberSpec.dataInstance == _self)? true : false;
		}else{
			return (memberSpec.item.exists[item | 
						item instanceof DataInstanceUse
						&& (item as DataInstanceUse).dataInstance == _self
					])? true: false;
		}
	}
	
	def boolean equals2eobject (EObject eobject){
		//each memberAssignment of the _self must equal to the value of the matched feature of the eobject
		try{
			_self.memberAssignment.forEach[ma |
			_self.info = ma.isMatchedMember(eobject)
				if(_self.info.contains(TDLTestResultUtil.FAIL)){
					throw new InterruptedException()
				}
			]
		}catch(InterruptedException e){
			return false
		}
		return true
	}
	
	def String setMatchedMUTElement(EObject matchedObject, Resource MUTResource){
		var String status = ""
		for (i : 0 ..<_self.memberAssignment.size){
			var memberAssig = _self.memberAssignment.get(i)
			if (memberAssig.member.dataType.isDynamicType
				|| memberAssig.member.isDynamicMember){
					status = memberAssig.setMatchedMember(matchedObject, MUTResource)
			}
			else if (memberAssig instanceof DataInstanceUse){
				val data = memberAssig.memberSpec as DataInstanceUse
				if (memberAssig.hasItems){
					for (j : 0 ..<data.item.size){
						val item = data.item.get(j) as DataInstanceUse
						val rootObject = item.getMatchedMUTElement(MUTResource, false)
						status = (data.item.get(j) as DataInstanceUse).setMatchedMUTElement(rootObject, MUTResource)
						if (status.contains(TDLTestResultUtil.FAIL)){
							return status
						}
					}
				}else{
					val rootObject = data.getMatchedMUTElement(MUTResource, false)
					status = data.setMatchedMUTElement(rootObject, MUTResource)
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
	
	def EObject getMatchedMUTElement(Resource MUTResource, boolean isAssertion){
		var ArrayList<EObject> rootElement = new ArrayList
		//if data type is abstract return null
		if (!_self.dataInstance.dataType.isConcreteEcoreType){
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
		return _self.getMatchedMUTElement(rootElement, isAssertion, MUTResource)
	}
	
	def EObject getMatchedMUTElement(ArrayList<EObject> rootElement, boolean isAssertion, Resource MUTResource){
		var ArrayList<EObject> matchedElements = new ArrayList
		if (_self.dataInstance instanceof StructuredDataInstance){
			//some attributes are set as member assignments for dataInstance
			//so find the matched element based on the dataInstance
			val dataIns = _self.dataInstance as StructuredDataInstance
			if (dataIns.memberAssignment.size>0){//if some values are assigned to the members of data instance
				matchedElements = dataIns.getMatchedMUTElement(rootElement, isAssertion, MUTResource)
				if (matchedElements.empty){
					_self.dataInstance.info = TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + dataIns.name
					return null
				}
			}	
		}
		//when the program reach to this line, it means the values assigned as parameter bindings has to be checked
		//therefore, all the elements identified as root must be checked to find the matched element
		//find matched elements based on the parameter bindings of dataInstanceUse
		var ArrayList<EObject> equalEObjectsByFeatures = new ArrayList
		for (i:0 ..<matchedElements.size){
			_self.dataInstance.info = _self.isMatchedParametrizedElement(matchedElements.get(i), isAssertion, MUTResource)
			if (_self.dataInstance.info.contains(TDLTestResultUtil.PASS)){
					equalEObjectsByFeatures.add(matchedElements.get(i))
			}
		}
		if(equalEObjectsByFeatures.empty){
			return null
		}
		else if(equalEObjectsByFeatures.size == 1){
			return equalEObjectsByFeatures.get(0)
		}
		//at this point, all the features of the elements are the same and we should check their containers
		//in the context of TDL, we should find a DataInstance with memberAssignment that its memberSpec is self
		//then the DataInstance is the container of tdl element
		EObjectFinderUtilities.instance.matchedEObjectsByContainer.clear
		return _self.getMatchedMUTElementByContainer(equalEObjectsByFeatures)
	}
	
	def String isMatchedParametrizedElement(EObject rootElement, boolean isAssertion, Resource MUTResource){
		//find matched element based on the parameter bindings of dataInstance
		for (i : 0 ..<_self.argument.size){
			val parameterBinding = _self.argument.get(i)
			val status = parameterBinding.isMatchedParameter(rootElement, isAssertion, MUTResource)
			if (status.contains(TDLTestResultUtil.FAIL)){
				return status
			}		
		}
		return TDLTestResultUtil.PASS
	}
	
	def EObject getMatchedMUTElementByContainer (ArrayList<EObject> equalEObjectsByContainer){
		return (_self.dataInstance as StructuredDataInstance).getMatchedMUTElementByContainer(equalEObjectsByContainer)
	}

	def String setMatchedMUTElement(Resource MUTResource){
		//the second parameter is isAssertion that has to be set as false
		//so only static elements will be matched to then set the values of its dynamic features
		var EObject matchedObject = _self.getMatchedMUTElement(MUTResource, false)
		if (matchedObject === null){
			println("There is no matched object in the model under test")
			return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.dataInstance.name
		}
		_self.setMatchedMUTElement(matchedObject, MUTResource)
	}
	
	def String setMatchedMUTElement(EObject matchedObject, Resource MUTResource){
		var String status = "";
		if (_self.dataInstance instanceof StructuredDataInstance){			
			//some attributes are set as member assignments for dataInstance
			//so find the matched element based on the dataInstance
			val dataIns = _self.dataInstance as StructuredDataInstance
			status = dataIns.setMatchedMUTElement(matchedObject, MUTResource)
			if (status.contains(TDLTestResultUtil.FAIL)){
				return status
			}
		}
		//find matched elements based on the parameter bindings of dataInstanceUse
		for (i : 0 ..<_self.argument.size){
			var parameterBinding = _self.argument.get(i);
			if (parameterBinding.parameter.dataType.isDynamicType
				|| (parameterBinding.parameter as Member).isDynamicMember){
				status = parameterBinding.setMatchedParameter(matchedObject, MUTResource);
			}
			else if (parameterBinding.dataUse instanceof DataInstanceUse){
				val data = parameterBinding.dataUse as DataInstanceUse
				if (parameterBinding.hasItems){
					for (j : 0 ..<data.item.size){
						val item = data.item.get(j) as DataInstanceUse
						val rootObject = item.getMatchedMUTElement(MUTResource, false)
						status = (data.item.get(j) as DataInstanceUse).setMatchedMUTElement(rootObject, MUTResource)
						if (status.contains(TDLTestResultUtil.FAIL)){
							return status
						}
					}
				}else{
					val rootObject = data.getMatchedMUTElement(MUTResource, false)
					status = data.setMatchedMUTElement(rootObject, MUTResource)
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
	
	def EObject createEObject(Resource MUTResource, boolean isAssertion){
		val ModelEObjectCreator objectCreator = new ModelEObjectCreator
		val eobject = objectCreator.createEObject(_self, MUTResource, isAssertion)
		return eobject
	}
	
	@OverrideAspectMethod
	def String assertEquals(Object featureValue, Boolean isAssertion, Resource MUTResource){
		var ArrayList<EObject> rootObjects = new ArrayList
		if (featureValue instanceof EList){
			rootObjects.addAll(featureValue)
		}else if(featureValue instanceof EObject){
			rootObjects.add(featureValue as EObject)
		}
		
		val ArrayList<EObject> matchedObjects = new ArrayList
		var String expectedData = "";
		var String mutData = "";
		if (!_self.item.nullOrEmpty){//there are several instances of data
			mutData = TDLTestResultUtil.getInstance.getDataAsString(featureValue as EList<?>)
			for (i : 0 ..<_self.item.size){
				val matchedObject = (_self.item.get(i) as DataInstanceUse).getMatchedMUTElement(rootObjects, isAssertion, MUTResource)			
				matchedObjects.add(matchedObject)
				expectedData += ((_self.item.get(i) as DataInstanceUse).dataInstance.name + ", ")
			}
			expectedData = "[" + expectedData.substring(0, expectedData.length-2) + "]"
			if (mutData.equals("[]") && !expectedData.isNullOrEmpty){
				return TDLTestResultUtil.FAIL + ": The expected data is: " + expectedData + ", but the current data is NULL";
			}
//			if (isAssertion){
//				if ((featureValue as EList<?>).equals(matchedObjects)){
//					return TDLTestResultUtil.PASS + ": The expected data is equal to the current data"
//				}
//			}else{
				if ((featureValue as EList<?>).containsAll(matchedObjects)){
					return TDLTestResultUtil.PASS + ": The expected data contains the current data"
				}
//			}		
			return TDLTestResultUtil.FAIL + ": The expected data is: " + expectedData + ", but the current data is: " + mutData;
		}else{//there is just one data instance
			val matchedObject = _self.getMatchedMUTElement(rootObjects, isAssertion, MUTResource)
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
	def String updateData(EObject object, EStructuralFeature matchedFeature, Resource MUTResource){
		if (!_self.item.nullOrEmpty){//there are several instances of data
			val TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(object);
			try{
				domain.getCommandStack().execute(new RecordingCommand(domain) {
			        override protected doExecute() {
			        	var ArrayList<EObject> rootElements = new ArrayList
						rootElements.add(object)
			        	val ArrayList<EObject> matchedObjects = new ArrayList
						for (i : 0 ..<_self.item.size){
							val item = _self.item.get(i) as DataInstanceUse
							val matchedObject = item.getMatchedMUTElement(rootElements, false, MUTResource)			
							if (matchedObject === null){
								println("There is no " + item.dataInstance.name + " property in the MUT")
							}else{
								_self.setMatchedMUTElement(matchedObject, MUTResource)
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
					val matchedObject = item.getMatchedMUTElement(rootElements, false, MUTResource)			
					if (matchedObject === null){
						println("There is no " + item.dataInstance.name + " property in the MUT")
					}else{
						_self.setMatchedMUTElement(matchedObject, MUTResource)
						matchedObjects.add(matchedObject)
					}							
				}
				if (matchedObjects.size == _self.item.size){
					object.eSet(matchedFeature, matchedObjects)
				}
			}
		}else{//there is just one data instance
			val matchedObject = _self.getMatchedMUTElement(MUTResource, false)
			if (matchedObject === null){
				//println("There is no " + _self.dataInstance.name + " property in the MUT")
				return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.dataInstance.name
			}
			try{
				val TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(object);
				domain.getCommandStack().execute(new RecordingCommand(domain) {
			        override protected doExecute() {
			        	var ArrayList<EObject> rootElements = new ArrayList
						rootElements.add(object)
			        	val matchedObject = _self.getMatchedMUTElement(MUTResource, false)
			        	_self.setMatchedMUTElement(matchedObject, MUTResource)
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
	        	_self.setMatchedMUTElement(matchedObject, MUTResource)
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
	
	def String isMatchedMember(EObject rootElement, Boolean isAssertion, Resource MUTResource){
		val EStructuralFeature matchedFeature = _self.member.getMatchedFeature(rootElement)	
		if (matchedFeature === null){
			//println("There is no " + _self.member.name + " property in the MUT")
			return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.member.name
		}
		val featureValue = rootElement.eGet(matchedFeature)
		if (!isAssertion && _self.member.isDynamicMember){
			return TDLTestResultUtil.PASS
		}
		//Assert the data instances of the member assignment
		if (_self.memberSpec instanceof DataInstanceUse){
			return _self.memberSpec.assertEquals(featureValue, isAssertion, MUTResource)
		}
		return _self.memberSpec.assertEquals(featureValue)
	} 
	
	def String isMatchedMember(EObject eobject){
		val EStructuralFeature matchedFeature = _self.member.getMatchedFeature(eobject)	
		if (matchedFeature === null){
			//println("There is no " + _self.member.name + " property in the MUT")
			return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.member.name
		}
		val featureValue = eobject.eGet(matchedFeature)
		//Assert the value of the member assignment
		if (_self.memberSpec instanceof DataInstanceUse){
			val memberValue = _self.memberSpec as DataInstanceUse
			var boolean result = true
			if (memberValue.dataInstance !== null){
				val tdlValue = memberValue.dataInstance as StructuredDataInstance
				if (featureValue instanceof EObject){
					//there is only one value
					result = tdlValue.equals2eobject(featureValue as EObject)
					return result ? TDLTestResultUtil.PASS: TDLTestResultUtil.FAIL
				}
				else if (featureValue instanceof EList){
					//if there is only one tdlValue but a list of featureValues,
					//there must be at least one featureValue == tdlValue
					result = (featureValue as EList<EObject>).exists[o | tdlValue.equals2eobject(o)]
					return result ? TDLTestResultUtil.PASS: TDLTestResultUtil.FAIL
				} 
			}
			else if (!memberValue.item.isEmpty && featureValue instanceof EList){
				//there is a list of values
				val EList<EObject> featureValues = featureValue as EList<EObject>
				for(i:0..<memberValue.item.size){
					val memberItemValue = (memberValue.item.get(i) as DataInstanceUse).dataInstance as StructuredDataInstance					
					//there must be at least one featureValue == memberItemValue
					result = featureValues.exists[o | memberItemValue.equals2eobject(o)]
					if (!result){
						return TDLTestResultUtil.FAIL
					}
				}
				return TDLTestResultUtil.PASS
			}
		}
		return _self.memberSpec.assertEquals(featureValue)
	} 
	
	def String setMatchedMember(EObject rootElement, Resource MUTResource){
		val EStructuralFeature matchedFeature = _self.member.getMatchedFeature(rootElement)	
		if (_self.memberSpec instanceof DataInstanceUse){
			return _self.memberSpec.updateData(rootElement, matchedFeature, MUTResource)
		}
		return _self.memberSpec.updateData(rootElement, matchedFeature)
	} 
	
	def Boolean hasItems(){
		val data = _self.memberSpec as DataInstanceUse
		if (!data.item.nullOrEmpty){
			return true
		}
		return false
	}
}
@Aspect (className = ParameterBinding)
class ParameterBindingAspect{
	
	def String isMatchedParameter(EObject rootElement, Boolean isAssertion, Resource MUTResource){
		val EStructuralFeature matchedFeature = (_self.parameter as Member).getMatchedFeature(rootElement) 
		if (matchedFeature === null){
			//println("There is no " + _self.parameter.name + " property in the MUT")
			return TDLTestResultUtil.FAIL + ": There is no MUT element matched with " + _self.parameter.name
		}
		val featureValue = rootElement.eGet(matchedFeature)
		if (!isAssertion && (_self.parameter as Member).isDynamicMember){
			return TDLTestResultUtil.PASS
		}
		if (_self.dataUse instanceof DataInstanceUse){
			return _self.dataUse.assertEquals(featureValue, isAssertion, MUTResource)
		}
		return _self.dataUse.assertEquals(featureValue)
	} 
	
	def String setMatchedParameter(EObject rootElement, Resource MUTResource){
		val EStructuralFeature matchedFeature = (_self.parameter as Member).getMatchedFeature(rootElement)
		if (_self.dataUse instanceof DataInstanceUse){
			return _self.dataUse.updateData(rootElement, matchedFeature, MUTResource)
		}
		return  _self.dataUse.updateData(rootElement, matchedFeature)
	} 
	
	def Boolean hasItems(){
		val data = _self.dataUse as DataInstanceUse
		if (!data.item.nullOrEmpty){
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
	def String assertEquals(Object featureValue, Boolean isAssertion, Resource MUTResource){
		return "";
	}
	def String updateData(EObject object, EStructuralFeature matchedFeature){
		return "";
	}
	def String updateData(EObject object, EStructuralFeature matchedFeature, Resource MUTResource){
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
	def String assertEquals(Object featureValue, Boolean isAssertion, Resource MUTResource){
		return "";
	}
	@OverrideAspectMethod
	def String updateData(EObject object, EStructuralFeature matchedFeature){
		return "";
	}
	@OverrideAspectMethod
	def String updateData(EObject object, EStructuralFeature matchedFeature, Resource MUTResource){
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
		else if (featureValue === null){
			return TDLTestResultUtil.FAIL + ": The expected data is: " + parameterValue + ", but the current data is NULL"
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