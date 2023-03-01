package org.imt.k3tdl.interpreter

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.TransactionalEditingDomain
import org.eclipse.emf.transaction.util.TransactionUtil
import org.etsi.mts.tdl.DataInstanceUse
import org.etsi.mts.tdl.DataUse
import org.etsi.mts.tdl.LiteralValueUse
import org.etsi.mts.tdl.MemberAssignment
import org.etsi.mts.tdl.StructuredDataInstance

import static org.imt.k3tdl.interpreter.DataTypeAspect.*

import static extension org.imt.k3tdl.interpreter.DataInstanceUseAspect.*

class ModelEObjectCreator {
	
	Resource MUTResource;
	EPackage rootEPackage;
	boolean isAssertion;
	String DSLPath;
	
	static String EINT = "EInt"
	static String EINTOBJECT = "EIntegerObject"
	static String EBOOLEAN = "EBoolean"
	static String EBOOLOBJECT = "EBooleanObject"
	
	def EObject createEObject(DataInstanceUse TDLObject, Resource MUTResource, boolean isAssertion){
		this.MUTResource = MUTResource
		this.isAssertion = isAssertion
		this.DSLPath = DSLPath
		//create an EObject using the factory of its EClass
		rootEPackage = MUTResource.contents.get(0).eClass.EPackage
		return createEObject(TDLObject);
	}
	
	def EObject createEObject(DataInstanceUse TDLObject){
		//create an EObject using the factory of its EClass
		val String eclassName = getValidName(TDLObject.dataInstance.dataType)
		val EClass eobjectType = rootEPackage.getEClassifier(eclassName) as EClass
		if (eobjectType === null){
			return null;
		}
		var EObject newEObject = rootEPackage.EFactoryInstance.create(eobjectType)
		//assign the value of its attributes
		if (setEObjectFeatures(TDLObject, newEObject, eobjectType)){
			return newEObject
		}
		return null
	}
	
	def boolean setEObjectFeatures(DataInstanceUse TDLObject, EObject newEObject, EClass eobjectType){
		var result = true
		if (TDLObject.dataInstance instanceof StructuredDataInstance && 
			(TDLObject.dataInstance as StructuredDataInstance).memberAssignment.size>0){//check the member assignments
			val StructuredDataInstance dataInstance = TDLObject.dataInstance as StructuredDataInstance
			for (MemberAssignment memberAssignment: dataInstance.memberAssignment){
				val eStructuralFeature = eobjectType.getEStructuralFeature(getValidName(memberAssignment.member.name))
				if (eStructuralFeature !== null){
					val memberValue = memberAssignment.memberSpec
					result = setFeatureValue(newEObject, eStructuralFeature, getTdlValues(memberValue))
					if (!result){
						return result
					}
				}
			}
		}
		for (i : 0 ..<TDLObject.argument.size){//check the parameter bindings
			val parameterBinding = TDLObject.argument.get(i)
			val eStructuralFeature = eobjectType.getEStructuralFeature(getValidName(parameterBinding.parameter.name))
			if (eStructuralFeature !== null){
				val parameterValue = parameterBinding.dataUse
				result = setFeatureValue(newEObject, eStructuralFeature, getTdlValues(parameterValue))
				if (!result){
					return result
				}
			}
		}
		return result
	}
	
	def List<DataUse> getTdlValues (DataUse dataUse){
		var List<DataUse> tdlValues = new ArrayList
		if (dataUse instanceof DataInstanceUse){
			val dataInstanceUse = dataUse as DataInstanceUse
			if (dataInstanceUse.item=== null || dataInstanceUse.item.size <= 0){
				tdlValues.add(dataInstanceUse)
			}else{
				for (i:0 ..<dataInstanceUse.item.size){
					tdlValues.add(dataInstanceUse.item.get(i))
				}
			}
		}else if (dataUse instanceof LiteralValueUse){
			tdlValues.add(dataUse)
		}
		return tdlValues
	}
	
	def boolean setFeatureValue (EObject newEObject, EStructuralFeature feature, List<DataUse> featureTdlValues){
		if (featureTdlValues.size == 0){
			return setEObjectFeatureValue (newEObject, feature, feature.defaultValue)
		}
		//all the values must be from the same type:
		//(1) if they are dataInstanceUse, it means they are references to EObjects of the model under test
		else if (featureTdlValues.get(0) instanceof DataInstanceUse){
			var List<EObject> featureValues = new ArrayList
			for (DataUse tdlValue : featureTdlValues){
				val EObject featureValue = (tdlValue as DataInstanceUse).getMatchedMUTElement(MUTResource, isAssertion)
				if (featureValue !== null){//if the EObject found in the model, add it as a value for the feature
					featureValues.add(featureValue)
				}else{//if the EObject is not found in the model, we must create it and then add it as a value for the feature
					featureValues.add(createEObject(tdlValue as DataInstanceUse))
				}
			}
			if (!featureValues.empty){
				if (feature.isMany){//the feature value is a list of eobjects
					return setEObjectFeatureValue (newEObject, feature, featureValues)
				}else{//the feature value is only one eobject
					return setEObjectFeatureValue (newEObject, feature, featureValues.get(0))
				}
			}else{
				return false
			}
		}
		//if they are LiteralValueUse, it means they are primitives
		else if (featureTdlValues.get(0) instanceof LiteralValueUse){
			if (feature.EType.name.equals(EINT) || feature.EType.name.equals(EINTOBJECT)){
				if (!feature.isMany){//a single integer must be set as the value
					var featureValue = getLiteralValue(featureTdlValues.get(0) as LiteralValueUse)
					return setEObjectFeatureValue (newEObject, feature, Integer.parseInt(featureValue))
				}else{//a list of integers must be set as the value
					var List<Integer> featureValues = new ArrayList
					for (DataUse tdlValue : featureTdlValues){
						var featureValue = getLiteralValue(tdlValue as LiteralValueUse)
			        	featureValues.add(Integer.parseInt(featureValue))
					}
					return setEObjectFeatureValue (newEObject, feature, featureValues)
				}
			} else if (feature.EType.name.equals(EBOOLEAN) || feature.EType.name.equals(EBOOLOBJECT)){//TODO: must be tested
				if (!feature.isMany){//a single boolean must be set as the value
					var featureValue = getLiteralValue(featureTdlValues.get(0) as LiteralValueUse)
					return setEObjectFeatureValue (newEObject, feature, Boolean.parseBoolean(featureValue))
				}else{//a list of booleans must be set as the value
					var List<Boolean> featureValues = new ArrayList
					for (DataUse tdlValue : featureTdlValues){
						var featureValue = getLiteralValue(tdlValue as LiteralValueUse)
			        	featureValues.add(Boolean.parseBoolean(featureValue))
					}
					return setEObjectFeatureValue (newEObject, feature, featureValues)
				}
			} else {
				if (!feature.isMany){//a single string must be set as the value
					var featureValue = getLiteralValue(featureTdlValues.get(0) as LiteralValueUse)
					return setEObjectFeatureValue (newEObject, feature, featureValue)
				}else{//a list of strings must be set as the value
					var List<String> featureValues = new ArrayList
					for (DataUse tdlValue : featureTdlValues){
						var featureValue = getLiteralValue(tdlValue as LiteralValueUse)
			        	featureValues.add(featureValue)
					}
					return setEObjectFeatureValue (newEObject, feature, featureValues)
				}
			} 
		}else{
			return false
		}
	}
	
	def boolean setEObjectFeatureValue(EObject object, EStructuralFeature feature, Object value) {
		try{
			val TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(object);
			domain.getCommandStack().execute(new RecordingCommand(domain) {
		        override protected doExecute() {
		        	object.eSet(feature, value)										
		        }
	   		});
	   		return true
   		}catch(IllegalArgumentException e){
			println("IllegalArgumentException: New value cannot be set for the " + feature.name + " property of the MUT")
			return false
		}catch(NullPointerException e){
			try{
				object.eSet(feature, value)
				return true
			}
			catch(IllegalStateException e2){
				println("IllegalStateException: New value cannot be set for the " + feature.name + " property of the MUT")
				return false
			}
		}
	}
	
	def String getLiteralValue(LiteralValueUse literalValue){
		var featureValue = literalValue.value
		if (featureValue.startsWith("\"") || featureValue.startsWith("'")){
    		featureValue = featureValue.substring(1, featureValue.length-1)//remove quotation marks
    	}
    	return featureValue
	}
	
	def String getValidName(String name){
		if (name.startsWith("_")){
			return name.substring(1)
		}
		return name
	}
}