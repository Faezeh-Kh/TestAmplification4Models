package org.imt.tdl.amplification.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.etsi.mts.tdl.Annotation;
import org.etsi.mts.tdl.DataInstance;
import org.etsi.mts.tdl.DataInstanceUse;
import org.etsi.mts.tdl.DataType;
import org.etsi.mts.tdl.DataUse;
import org.etsi.mts.tdl.LiteralValueUse;
import org.etsi.mts.tdl.Member;
import org.etsi.mts.tdl.MemberAssignment;
import org.etsi.mts.tdl.ParameterBinding;
import org.etsi.mts.tdl.SimpleDataInstance;
import org.etsi.mts.tdl.SimpleDataType;
import org.etsi.mts.tdl.StaticDataUse;
import org.etsi.mts.tdl.StructuredDataInstance;
import org.etsi.mts.tdl.StructuredDataType;

public class EObject2TDLConverter {
	
	org.etsi.mts.tdl.Package tdlTestSuite;
	org.etsi.mts.tdl.tdlFactory tdlFactory = org.etsi.mts.tdl.tdlFactory.eINSTANCE;
	
	public EObject2TDLConverter (org.etsi.mts.tdl.Package testSuite) {
		this.tdlTestSuite = testSuite;
	}
	
	public DataInstance convertEObject2TDLData(EObject eobject){
		//if there is an existing tdl instance for the eobject, use it otherwise create it
		StructuredDataInstance eobjectTdlInstance = findMatchedTDLInstance(eobject);
		if (eobjectTdlInstance == null) {
			eobjectTdlInstance = createNewTDLInstance4EObject(eobject);
		}
		return eobjectTdlInstance;
	}
	
	public DataUse convertEObject2TDLDataUse(EObject eobject){
		//if there is an existing tdl instance for the eobject, use it
		StructuredDataInstance eobjectTdlInstance = findMatchedTDLInstance(eobject);
		if (eobjectTdlInstance == null) {
			eobjectTdlInstance = createNewTDLInstance4EObject(eobject);
		}
		DataInstanceUse dataUse = tdlFactory.createDataInstanceUse();
		dataUse.setDataInstance(eobjectTdlInstance);
		createParameterBinding4dynamicFeatures(eobject, dataUse);
		return dataUse;
	}


	private void createParameterBinding4dynamicFeatures(EObject eobject, DataInstanceUse dataUse) {
		List<Member> dynamicMembers = ((StructuredDataType) dataUse.getDataInstance().getDataType()).allMembers().stream().
				filter(m -> isDynamicMember(m)).toList();
		for (Member m:dynamicMembers) {
			Optional<EStructuralFeature> feature = eobject.eClass().getEAllStructuralFeatures().stream().
				filter(f -> f.getName().equals(getDSLCompatibleName(m.getName()))).findFirst();
			//if there is a feature corresponding to the dynamic member which has a value, create a parameter binding for it
			if (feature.isPresent() && eobject.eGet(feature.get()) != null) {
				ParameterBinding parameterBinding = tdlFactory.createParameterBinding();
				parameterBinding.setParameter(m);
				parameterBinding.setDataUse(convertFeatureValue2TDLData(eobject, feature.get()));
				dataUse.getArgument().add(parameterBinding);
			}	
		}
	}

	private StructuredDataInstance createNewTDLInstance4EObject (EObject eobject) {
		String eobjectTypeName = eobject.eClass().getName();
		StructuredDataType eobjectTdlType = (StructuredDataType) getTDLDataType(eobjectTypeName);
		StructuredDataInstance eobjectTdlInstance = tdlFactory.createStructuredDataInstance();
		eobjectTdlInstance.setDataType(eobjectTdlType);
		//if the object has a name, use its name for the created tdl instance
		if (getEObjectName(eobject) != null) {
			String name = getValidName(getEObjectName(eobject));
			eobjectTdlInstance.setName(name);
		}else {
			String name = getValidName(eobjectTypeName.toLowerCase() + "_" + (int) Math.random());
			eobjectTdlInstance.setName(name);
		}
		//for each mandatory feature of the eobject that has a value, create a member assignment with corresponding tdl value
		List<EStructuralFeature> valuedEFeatures = eobject.eClass().getEAllStructuralFeatures().stream().
				filter(f -> (f.getName().equals("name") || f.getLowerBound()>0) & 
						eobject.eGet(f) != null &
						eobject.eGet(f) != f.getDefaultValue()).toList();
		for (EStructuralFeature efeature:valuedEFeatures) {
			Optional<Member> mOptional = eobjectTdlType.allMembers().stream().
					filter(m -> getDSLCompatibleName(m.getName()).equals(efeature.getName())).findFirst();
			if (mOptional.isPresent()) {
				MemberAssignment ma = tdlFactory.createMemberAssignment();
				ma.setMember(mOptional.get());
				ma.setMemberSpec(convertFeatureValue2TDLData(eobject, efeature));
				eobjectTdlInstance.getMemberAssignment().add(ma);
			}
		}
		tdlTestSuite.getPackagedElement().add(eobjectTdlInstance);
		return eobjectTdlInstance;
	}

	private StaticDataUse convertFeatureValue2TDLData(EObject eobject, EStructuralFeature efeature) {
		//if the feature value is a literal, create LiteralValueUse
		if (efeature.getEType().getName().equals("EBooleanObject") || efeature.getEType().getName().equals("EBoolean")) {
			if (efeature.isMany()){//several values must be created for the feature
				@SuppressWarnings("unchecked")
				List<Boolean> eValues = (List<Boolean>) eobject.eGet(efeature);
				DataInstanceUse tdlValues = tdlFactory.createDataInstanceUse();
				for (Boolean eValue:eValues) {
					LiteralValueUse tdlValue = tdlFactory.createLiteralValueUse();
					tdlValue.setValue("\"" + eValue.toString() + "\"");
					tdlValues.getItem().add(tdlValue);
				}
				return tdlValues;
			}else {
				boolean eValue = (boolean) eobject.eGet(efeature);
				LiteralValueUse tdlValue = tdlFactory.createLiteralValueUse();
				tdlValue.setValue("\"" + eValue + "\"");
				return tdlValue;
			}
		}
		else if (efeature.getEType().getName().equals("EIntegerObject") || efeature.getEType().getName().equals("EInt")) {
			if (efeature.isMany()){//several values must be created for the feature
				@SuppressWarnings("unchecked")
				List<Integer> eValues = (List<Integer>) eobject.eGet(efeature);
				DataInstanceUse tdlValues = tdlFactory.createDataInstanceUse();
				for (Integer eValue:eValues) {
					LiteralValueUse tdlValue = tdlFactory.createLiteralValueUse();
					tdlValue.setValue("\"" + eValue.toString() + "\"");
					tdlValues.getItem().add(tdlValue);
				}
				return tdlValues;
			}else {
				int eValue = (int) eobject.eGet(efeature);
				LiteralValueUse tdlValue = tdlFactory.createLiteralValueUse();
				tdlValue.setValue("\"" + eValue + "\"");
				return tdlValue;
			}
		}
		else if (efeature.getEType().getName().equals("EFloatObject") || efeature.getEType().getName().equals("EFloat")) {
			if (efeature.isMany()){//several values must be created for the feature
				@SuppressWarnings("unchecked")
				List<Float> eValues = (List<Float>) eobject.eGet(efeature);
				DataInstanceUse tdlValues = tdlFactory.createDataInstanceUse();
				for (Float eValue:eValues) {
					LiteralValueUse tdlValue = tdlFactory.createLiteralValueUse();
					tdlValue.setValue("\"" + eValue.toString() + "\"");
					tdlValues.getItem().add(tdlValue);
				}
				return tdlValues;
			}else {
				float eValue = (float) eobject.eGet(efeature);
				LiteralValueUse tdlValue = tdlFactory.createLiteralValueUse();
				tdlValue.setValue("\"" + eValue + "\"");
				return tdlValue;
			}
		}
		else if (efeature.getEType().getName().equals("EString")) {
			if (efeature.isMany()){//several values must be created for the feature
				@SuppressWarnings("unchecked")
				List<String> eValues = (List<String>) eobject.eGet(efeature);
				DataInstanceUse tdlValues = tdlFactory.createDataInstanceUse();
				for (String eValue:eValues) {
					LiteralValueUse tdlValue = tdlFactory.createLiteralValueUse();
					tdlValue.setValue("\"" + eValue.toString() + "\"");
					tdlValues.getItem().add(tdlValue);
				}
				return tdlValues;
			}else {
				String eValue = (String) eobject.eGet(efeature);
				LiteralValueUse tdlValue = tdlFactory.createLiteralValueUse();
				tdlValue.setValue("\"" + eValue + "\"");
				return tdlValue;
			}
		}
		else {//feature value is a reference to another object
			if (efeature.isMany()){//several values must be created for the object
				@SuppressWarnings("unchecked")
				List<EObject> eValues = (List<EObject>) eobject.eGet(efeature);
				DataInstanceUse tdlValues = tdlFactory.createDataInstanceUse();
				for (EObject eValue:eValues) {
					StructuredDataInstance tdlInstance = findMatchedTDLInstance(eValue);
					//if there is no tdl instance for the eobject, create it and add it to the test suite
					if (tdlInstance == null) {
						tdlInstance = createNewTDLInstance4EObject(eValue);
					}
					DataInstanceUse tdlValue = tdlFactory.createDataInstanceUse();
					tdlValue.setDataInstance(tdlInstance);
					createParameterBinding4dynamicFeatures(eobject, tdlValue);
					tdlValues.getItem().add(tdlValue);
				}
				return tdlValues;
			}else {
				EObject eValue = (EObject) eobject.eGet(efeature);
				StructuredDataInstance tdlInstance = findMatchedTDLInstance(eValue);
				//if there is no tdl instance for the eobject, create it and add it to the test suite
				if (tdlInstance == null) {
					tdlInstance = createNewTDLInstance4EObject(eValue);
				}
				DataInstanceUse tdlValue = tdlFactory.createDataInstanceUse();
				tdlValue.setDataInstance(tdlInstance);
				createParameterBinding4dynamicFeatures(eobject, tdlValue);
				return tdlValue;
			}
		}
	}
	
	private StructuredDataInstance findMatchedTDLInstance (EObject eobject) {
		//only eobjects with name property can be used in TDL
		if (getEObjectName(eobject) == null) {
			return null;
		}
		String eobjectName = getEObjectName(eobject);
		String eobjectTypeName = eobject.eClass().getName();
		StructuredDataType eobjectTdlType = (StructuredDataType) getTDLDataType(eobjectTypeName);
		List<StructuredDataInstance> dataInstances = tdlTestSuite.getPackagedElement().
				stream().filter(p -> p instanceof StructuredDataInstance).
				map(p -> (StructuredDataInstance) p).
				filter(di -> di.getDataType() == eobjectTdlType).toList();
		for (StructuredDataInstance di: dataInstances) {
			Optional<MemberAssignment> maOptional = di.getMemberAssignment().stream().filter(ma -> ma.getMember().getName().equals("_name")).findFirst();
			if (maOptional.isPresent()) {
				String tdlName = getLiteralValue ((LiteralValueUse) maOptional.get().getMemberSpec());
				if (tdlName.equals(eobjectName)) {
					return di;
				}
			}
		}
		return null;
	}
	
	public DataUse convertBoolean2TDLData(boolean boolValue){
		SimpleDataType tdlBooleanType = (SimpleDataType) getTDLDataType("EBoolean");
		SimpleDataInstance tdlBooleanInstance = tdlFactory.createSimpleDataInstance();
		tdlBooleanInstance.setDataType(tdlBooleanType);
		tdlBooleanInstance.setName("" + boolValue);
		tdlTestSuite.getPackagedElement().add(tdlBooleanInstance);
		DataInstanceUse messageArg = tdlFactory.createDataInstanceUse();
		messageArg.setDataInstance(tdlBooleanInstance);
		return messageArg;
	}
	
	public DataUse convertInteger2TDLData(int intValue){
		SimpleDataType tdlIntType = (SimpleDataType) getTDLDataType("EInt");
		SimpleDataInstance tdlIntInstance = tdlFactory.createSimpleDataInstance();
		tdlIntInstance.setDataType(tdlIntType);
		tdlIntInstance.setName("" + intValue);
		tdlTestSuite.getPackagedElement().add(tdlIntInstance);
		DataInstanceUse messageArg = tdlFactory.createDataInstanceUse();
		messageArg.setDataInstance(tdlIntInstance);
		return messageArg;
	}
	
	public DataUse convertFloat2TDLData(float floatValue){
		SimpleDataType tdlFloatType = (SimpleDataType) getTDLDataType("EFloat");
		SimpleDataInstance tdlFloatInstance = tdlFactory.createSimpleDataInstance();
		tdlFloatInstance.setDataType(tdlFloatType);
		tdlFloatInstance.setName("" + floatValue);
		tdlTestSuite.getPackagedElement().add(tdlFloatInstance);
		DataInstanceUse messageArg = tdlFactory.createDataInstanceUse();
		messageArg.setDataInstance(tdlFloatInstance);
		return messageArg;
	}
	
	public DataUse convertString2TDLData(String stringValue){
		SimpleDataType tdlStringType = (SimpleDataType) getTDLDataType("EString");
		SimpleDataInstance tdlStringInstance = tdlFactory.createSimpleDataInstance();
		tdlStringInstance.setDataType(tdlStringType);
		tdlStringInstance.setName(getValidName(stringValue));
		tdlTestSuite.getPackagedElement().add(tdlStringInstance);
		DataInstanceUse messageArg = tdlFactory.createDataInstanceUse();
		messageArg.setDataInstance(tdlStringInstance);
		return messageArg;
	}
	
	private DataType getTDLDataType (String typeName) {
		Optional<DataType> dtOptional = tdlTestSuite.getPackagedElement().stream().filter(p -> p instanceof DataType).
			map(p -> (DataType) p).filter(t -> getDSLCompatibleName(t.getName()).equals(typeName)).findFirst();
		if (dtOptional.isPresent()) {
			return dtOptional.get();
		}
		return null;
	}
	
	private boolean isDynamicMember(Member member) {
		for (Annotation annotation : member.getAnnotation()){
			String annotationTitle = annotation.getKey().getName();
			if (annotationTitle.equals("dynamic") || annotationTitle.equals("aspect")) {
				return true;
			}
		}
		return false;
	}
	private String getDSLCompatibleName(String name){
		if (name.startsWith("_")){
			return name.substring(1);
		}
		return name;
	}
	
	private String getEObjectName (EObject eobject) {
		if (eobject.eClass().getEStructuralFeature("name") != null) {
			EStructuralFeature nameFeature = eobject.eClass().getEStructuralFeature("name");
			if (eobject.eGet(nameFeature)!= null) {
				return eobject.eGet(nameFeature).toString();
			}
		}
		return null;
	}
	
	private String getLiteralValue (LiteralValueUse literalValue) {
		String value = literalValue.getValue();
		if (value.startsWith("\"") || value.startsWith("'")){
			return value.substring(1, value.length()-1);//remove quotation marks
    	}
		return value;
	}
	
	private String getValidName (String name){
		String[] tokenNames = {"Package", "{", "}", "with", "perform", "action", "(", ",", ")", "on", "test", "objectives", ":", ";", "name", "time", "label", "constraints", "Action", "alternatively", "or", "Annotation", "*", "?", "=", "assert", "otherwise", "set", "verdict", "to", "->", "[", "]", "times", "repeat", "break", "Note", "create", "of", "type", "bind", "Component", "Type", "having", "if", "else", "connect", "as", "Map", "in", ".", "new", "containing", "Use", "Signature", "Collection", "default", "+", "-", "/", "mod", ">", "<", ">=", "<=", "==", "!=", "and", "xor", "not", "size", "Import", "all", "from", "Function", "returns", "instance", "returned", "Predefined", "gate", "Gate", "accepts", "sends", "triggers", "calls", "responds", "response", "interrupt", "optional", "mapped", "omit", "argument", "optionally", "run", "parallel", "parameter", "every", "component", "is", "quiet", "for", "terminate", "where", "it", "assigned", "Test", "Configuration", "Description", "Implementation", "uses", "configuration", "execute", "bindings", "Objective", "description", "Time", "out", "timer", "start", "stop", "variable", "waits", "extends", "SUT", "Tester", "Message", "Procedure", "In", "Out", "Exception", "last", "previous", "first"};
		String result = name;
		if (result.contains("$")){
			result = result.substring(0, result.indexOf("$"));
		}
		if (result.contains(".")){
			result = result.replace(".", "_");
		}
		if (Arrays.asList(tokenNames).contains(result)){
			result = "_" + result;
		}
		return result;
	}
}
