package org.imt.tdl.amplification.testmodifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.emf.ecore.EObject;
import org.etsi.mts.tdl.DataInstanceUse;
import org.etsi.mts.tdl.DataType;
import org.etsi.mts.tdl.DataUse;
import org.etsi.mts.tdl.LiteralValueUse;
import org.etsi.mts.tdl.ParameterBinding;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.amplification.dsl.amplifier.BooleanValueModifier;
import org.imt.tdl.amplification.dsl.amplifier.NumericValueModifier;
import org.imt.tdl.amplification.dsl.amplifier.PrimitiveDataModifier;
import org.imt.tdl.amplification.dsl.amplifier.StringValueModifier;

public class PrimitiveValueModificationRunner extends AbstractTestModificationRunner{

	List<PrimitiveDataModifier> modifiers;

	List<LiteralValueUse> boolLiterals = new ArrayList<LiteralValueUse>();
	List<LiteralValueUse> stringLiterals = new ArrayList<LiteralValueUse>();
	List<LiteralValueUse> intLiterals = new ArrayList<LiteralValueUse>();
	List<LiteralValueUse> floatLiterals = new ArrayList<LiteralValueUse>();
	
	private static String BOOLMODIFICATION = "BooleanModification";
	private static String STRINGMODIFICATION = "StringModification";
	private static String INTMODIFICATION = "IntegerModification";
	private static String FLOATMODIFICATION = "FloatModification";
	
	public PrimitiveValueModificationRunner() {
		applyAllModifiers = true;
	}
	
	public PrimitiveValueModificationRunner(List<PrimitiveDataModifier> modifiers) {
		this.modifiers = modifiers;
	}
	
	@Override
	public List<TestDescription> generateTests(TestDescription testCase) {
		tdlTestCase = testCase;
		Iterator<EObject> iterator = testCase.eAllContents();
		while (iterator.hasNext()) {
			EObject object = iterator.next();
			if (object instanceof DataInstanceUse) {
				findLiteralFeaturesOfData((DataInstanceUse) object);
			}
		}
		modifyLiteralData();
		return generatedTestsByModification;
	}
	
	private void findLiteralFeaturesOfData (DataInstanceUse dataInstanceUse){
		//finding literals that their value is directly used in the test case
		//(ignoring memberAssignments and only considering parameterBindings)
		for (ParameterBinding pb : dataInstanceUse.getArgument()) {
			DataUse parameterValue = pb.getDataUse();
			if (!pb.getParameter().getName().equals("_name")) {
				if (parameterValue instanceof LiteralValueUse) {
					classifyLiteralBasedOnType ((LiteralValueUse) parameterValue, pb.getParameter().getDataType());
				}else if (parameterValue instanceof DataInstanceUse) {
					findLiteralFeaturesOfData((DataInstanceUse) parameterValue);
				}
			}
		}
	}
	
	private void classifyLiteralBasedOnType (LiteralValueUse literal, DataType type) {
		String typeName = type.getName();
		if (typeName.equals("EBooleanObject") || typeName.equals("EBoolean")) {
			boolLiterals.add(literal);
		}
		else if (typeName.equals("EString")) {
			stringLiterals.add(literal);
		}
		else if (typeName.equals("EIntegerObject") || typeName.equals("EInt")) {
			intLiterals.add(literal);
		}
		else if (typeName.equals("EFloatObject") || typeName.equals("EFloat")) {
			floatLiterals.add(literal);
		}
	}
	
	private String getLiteralValue (LiteralValueUse literalValue) {
		String value = literalValue.getValue();
		if (value.startsWith("\"") || value.startsWith("'")){
			return value.substring(1, value.length()-1);//remove quotation marks
    	}
		return value;
	}
	
	private void modifyLiteralData() {
		if (modifiers == null) {
			//default configuration: run all modifiers
			if (boolLiterals.size()>0) {
				modifyBooleanData(null);
			}
			else if (stringLiterals.size()>0) {
				modifyStringData(null);
			}
			else if (intLiterals.size()>0) {
				modifyIntegerData(null);
			}
			else if (floatLiterals.size()>0) {
				modifyFloatData(null);
			}
		}
		else {
			for (PrimitiveDataModifier modifier:modifiers) {
				policy = modifier.getPolicy();
				if (modifier instanceof BooleanValueModifier && boolLiterals.size()>0) {
					modifyBooleanData((BooleanValueModifier) modifier);
				}
				else if (modifier instanceof StringValueModifier && stringLiterals.size()>0) {
					modifyStringData((StringValueModifier) modifier);
				}
				else if (modifier instanceof NumericValueModifier) {
					if (intLiterals.size()>0) {
						modifyIntegerData ((NumericValueModifier) modifier);
					}
					if (floatLiterals.size()>0) {
						modifyFloatData((NumericValueModifier) modifier);
					}
				}
			}
		}
	}
	
	private void modifyBooleanData(BooleanValueModifier modifier) {
		//TODO: apply based on policy
		for (LiteralValueUse boolLiteral:boolLiterals) {
			String initialValue = getLiteralValue(boolLiteral);
			if (initialValue.equals("true")) {
				boolLiteral.setValue("\"false\"");
			}
			else if (initialValue.equals("false")) {
				boolLiteral.setValue("\"true\"");
			}
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, BOOLMODIFICATION));
			boolLiteral.setValue("\""+ initialValue + "\"");
		}
	}

	/*four operators: 
	 * 1. add a random char, 
	 * 2. remove a random char, 
	 * 3. replace a random char and 
	 * 4. replace the string by a fully random string of the same size
	 */
	private void modifyStringData(StringValueModifier modifier) {
		//TODO: apply based on policy
		for (LiteralValueUse stringLiteral:stringLiterals) {
			String initialValue = getLiteralValue(stringLiteral);
			StringBuilder sb = new StringBuilder(initialValue);
			Random rand = new Random();
			int randomIndex = rand.nextInt(sb.length());
			// 1. add a random char
			sb.append(RandomStringUtils.randomAlphanumeric(1));
			stringLiteral.setValue(sb.toString());
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, STRINGMODIFICATION));
			//2. remove a random char
			sb = new StringBuilder(initialValue);
			sb.deleteCharAt(randomIndex);
			stringLiteral.setValue(sb.toString());
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, STRINGMODIFICATION));
			//3. replace a random char
			sb = new StringBuilder(initialValue);
			sb.replace(randomIndex, randomIndex + 1, RandomStringUtils.randomAlphanumeric(1));
			stringLiteral.setValue(sb.toString());
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, STRINGMODIFICATION));
			//4. replace the string by a fully random string of the same size
			stringLiteral.setValue(RandomStringUtils.randomAlphanumeric(initialValue.length()));
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, STRINGMODIFICATION));
			//5. replace the string by an empty string (based on pitest tool)
			stringLiteral.setValue("\"\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, STRINGMODIFICATION));
			
			stringLiteral.setValue("\""+ initialValue + "\"");
		}
	}

	/* five operators for numeric values: 
	 * plus 1, minus 1, multiply by 2, divide by 2, and replacement by an existing literal of the same type
	 */
	private void modifyIntegerData(NumericValueModifier modifier) {
		//TODO: apply based on policy
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		for (LiteralValueUse intLiteral:intLiterals) {
			String initialValue = getLiteralValue(intLiteral);
			int value = Integer.parseInt(initialValue);
			//1. value = 1
			intLiteral.setValue("\"" + (1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			//2. value = 0
			intLiteral.setValue("\"" + (0) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			//3. value = -1
			intLiteral.setValue("\"" + (-1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			//4. negating value
			intLiteral.setValue("\"" + (-value) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			//5. value plus 1
			intLiteral.setValue("\"" + (value + 1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			//6. value minus 1
			intLiteral.setValue("\"" + (value - 1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			//7. value multiply by 2
			intLiteral.setValue("\"" + (value * 2) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			//8. value divide by 2
			intLiteral.setValue("\"" + (value / 2) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			//9. replacement by an existing literal of the same type
			List<LiteralValueUse> otherValues = intLiterals.stream()
					.filter(i -> i != intLiteral).collect(Collectors.toList());
			for (LiteralValueUse otherValue: otherValues) {
				intLiteral.setValue(otherValue.getValue());
				generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, INTMODIFICATION));
			}
			intLiteral.setValue("\""+ initialValue + "\"");
		}
	}

	private void modifyFloatData(NumericValueModifier modifier) {
		//TODO: apply based on policy
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		for (LiteralValueUse floatLiteral:floatLiterals) {
			String initialValue = getLiteralValue(floatLiteral);
			float value = Float.parseFloat(initialValue);
			//1. value = 1
			floatLiteral.setValue("\"" + (1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			//2. value = 0
			floatLiteral.setValue("\"" + (0) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			//3. value = -1
			floatLiteral.setValue("\"" + (-1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			//4. negating value
			floatLiteral.setValue("\"" + (-value) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			//5. value plus 1
			floatLiteral.setValue("\"" + (value + 1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			//6. value minus 1
			floatLiteral.setValue("\"" + (value - 1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			//7. value multiply by 2
			floatLiteral.setValue("\"" + (value * 2) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			//8. value divide by 2
			floatLiteral.setValue("\"" + (float)(value / 2) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			//9. replacement by an existing literal of the same type
			List<LiteralValueUse> otherValues = floatLiterals.stream()
					.filter(i -> i != floatLiteral).collect(Collectors.toList());
			for (LiteralValueUse otherValue: otherValues) {
				floatLiteral.setValue(otherValue.getValue());
				generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, FLOATMODIFICATION));
			}
			
			floatLiteral.setValue("\"" + initialValue + "\"");
		}
	}
}
