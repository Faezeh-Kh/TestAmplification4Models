package org.imt.tdl.amplification.testmodifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.emf.ecore.EObject;
import org.etsi.mts.tdl.DataInstanceUse;
import org.etsi.mts.tdl.DataType;
import org.etsi.mts.tdl.DataUse;
import org.etsi.mts.tdl.LiteralValueUse;
import org.etsi.mts.tdl.ParameterBinding;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.amplification.dsl.amplifier.ApplicationPolicy;
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
		generateTestsByLiteralModification();
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
	
	private void generateTestsByLiteralModification() {
		if (modifiers == null) {
			//default configuration: run all modifiers
			if (boolLiterals.size()>0) {
				generateTestsByBooleanModification();
			}
			else if (stringLiterals.size()>0) {
				generateTestsByStringModification();
			}
			else if (intLiterals.size()>0) {
				generateTestsByIntegerModification();
			}
			else if (floatLiterals.size()>0) {
				generateTestsByFloatModification();
			}
		}
		else {
			for (PrimitiveDataModifier modifier:modifiers) {
				policy = modifier.getPolicy();
				maxOccurrence = modifier.getMaxOccurrence();
				if (modifier instanceof BooleanValueModifier && boolLiterals.size()>0) {
					generateTestsByBooleanModification();
				}
				else if (modifier instanceof StringValueModifier && stringLiterals.size()>0) {
					generateTestsByStringModification();
				}
				else if (modifier instanceof NumericValueModifier) {
					if (intLiterals.size()>0) {
						generateTestsByIntegerModification();
					}
					if (floatLiterals.size()>0) {
						generateTestsByFloatModification();
					}
				}
			}
		}
	}

	private void generateTestsByBooleanModification() {
		generateTestsByLiteralModification(new NegateBooleanValue(), boolLiterals, BOOLMODIFICATION);
	}

	/*five string operators: 
	 * 1. add a random char, 
	 * 2. remove a random char, 
	 * 3. replace a random char and 
	 * 4. replace the string by a fully random string of the same size
	 * 5. set string as empty
	 */
	private void generateTestsByStringModification() {
		generateTestsByLiteralModification(new AddRandomChar(), stringLiterals, STRINGMODIFICATION);
		generateTestsByLiteralModification(new RemoveRandomChar(), stringLiterals, STRINGMODIFICATION);
		generateTestsByLiteralModification(new ReplaceRandomChar(), stringLiterals, STRINGMODIFICATION);
		generateTestsByLiteralModification(new ReplaceString(), stringLiterals, STRINGMODIFICATION);
		generateTestsByLiteralModification(new EmptyString(), stringLiterals, STRINGMODIFICATION);
	}
	
	/* nine operators for numeric values: 
	 * 1, 0, −1, -n, n+1, n−1, n×2, n÷2, or with another existing value
	 */
	private void generateTestsByIntegerModification() {
		generateTestsByLiteralModification(new NumericEqualOne(), intLiterals, INTMODIFICATION);
		generateTestsByLiteralModification(new NumericEqualZero(), intLiterals, INTMODIFICATION);
		generateTestsByLiteralModification(new NumericEqualMinusOne(), intLiterals, INTMODIFICATION);
		generateTestsByLiteralModification(new IntNegateValue(), intLiterals, INTMODIFICATION);
		generateTestsByLiteralModification(new IntPlusOne(), intLiterals, INTMODIFICATION);
		generateTestsByLiteralModification(new IntMinusOne(), intLiterals, INTMODIFICATION);
		generateTestsByLiteralModification(new IntMultiplyTwo(), intLiterals, INTMODIFICATION);
		generateTestsByLiteralModification(new IntDevideTwo(), intLiterals, INTMODIFICATION);
		generateTestsByLiteralModification(new IntChangeWithExisting(), intLiterals, INTMODIFICATION);
	}

	private void generateTestsByFloatModification() {
		generateTestsByLiteralModification(new NumericEqualOne(), floatLiterals, FLOATMODIFICATION);
		generateTestsByLiteralModification(new NumericEqualZero(), floatLiterals, FLOATMODIFICATION);
		generateTestsByLiteralModification(new NumericEqualMinusOne(), floatLiterals, FLOATMODIFICATION);
		generateTestsByLiteralModification(new FloatNegateValue(), floatLiterals, FLOATMODIFICATION);
		generateTestsByLiteralModification(new FloatPlusOne(), floatLiterals, FLOATMODIFICATION);
		generateTestsByLiteralModification(new FloatMinusOne(), floatLiterals, FLOATMODIFICATION);
		generateTestsByLiteralModification(new FloatMultiplyTwo(), floatLiterals, FLOATMODIFICATION);
		generateTestsByLiteralModification(new FloatDevideTwo(), floatLiterals, FLOATMODIFICATION);
		generateTestsByLiteralModification(new FloatChangeWithExisting(), floatLiterals, FLOATMODIFICATION);
	}
	
	private void generateTestsByLiteralModification(ITestDataModifier modifier, List<LiteralValueUse> literals, String modifierType) {
		if (maxOccurrence > literals.size()) {
			maxOccurrence = literals.size();
		}
		String newTestId = numOfNewTests + "_" + modifierType + "_" + policy;
		HashMap<LiteralValueUse, String> literal_initialValue = new HashMap<>();
		if (policy == ApplicationPolicy.ALL) {//modify all values to generate a test case
			for (LiteralValueUse literal: literals) {
				literal_initialValue.put(literal, getLiteralValue(literal));
				modifier.modifyData(literal);
			}
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, newTestId));
			numOfNewTests++;
			//retrieve the initial values for the original test case
			literals.forEach(l -> l.setValue("\""+ literal_initialValue.get(l) + "\""));
		}
		else if (policy == ApplicationPolicy.ONE) {//modify a random value to generate a test case
			Random random = new Random();
			int randomIndex = random.nextInt(literals.size());
			LiteralValueUse literal = literals.get(randomIndex);
			String initialValue = getLiteralValue(literal);
			modifier.modifyData(literal);
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, newTestId));
			numOfNewTests++;
			literal.setValue("\""+ initialValue + "\"");
		}
		else if (policy == ApplicationPolicy.EACH) {//generate a test case by modifying each value
			for (LiteralValueUse literal: literals) {
				String initialValue = getLiteralValue(literal);
				modifier.modifyData(literal);
				generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, newTestId));
				numOfNewTests++;
				//retrieve the initial values for the original test case
				literals.forEach(l -> l.setValue("\""+ initialValue + "\""));
			}
		}
		else if (policy == ApplicationPolicy.FIRST || policy == ApplicationPolicy.FIXED) {//modify the first values (up to maxOccurrence) to generate a test
			for (int i=0; i<maxOccurrence; i++) {
				LiteralValueUse literal = literals.get(i);
				literal_initialValue.put(literal, getLiteralValue(literal));
				modifier.modifyData(literal);
			}
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, newTestId));
			numOfNewTests++;
			for (int i=0; i<maxOccurrence; i++) {
				LiteralValueUse literal = literals.get(i);
				literal.setValue("\""+ literal_initialValue.get(literal) + "\"");
			}
		}
		else if (policy == ApplicationPolicy.LAST) {//modify the last values (up to maxOccurrence) to generate a test
			for (int i=maxOccurrence-1; i>=0; i--) {
				LiteralValueUse literal = literals.get(i);
				literal_initialValue.put(literal, getLiteralValue(literal));
				modifier.modifyData(literal);
			}
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, newTestId));
			numOfNewTests++;
			for (int i=maxOccurrence-1; i>=0; i--) {
				LiteralValueUse literal = literals.get(i);
				literal.setValue("\""+ literal_initialValue.get(literal) + "\"");
			}
		}
	}
	
	//Boolean Modification Operators
	class NegateBooleanValue implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			if (value.equals("true")) {
				tdlLiteralValue.setValue("\"false\"");
			}
			else if (value.equals("false")) {
				tdlLiteralValue.setValue("\"true\"");
			}
		}
	}

	//String Modification Operators
	class AddRandomChar implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			StringBuilder sb = new StringBuilder(value);
			sb.append(RandomStringUtils.randomAlphanumeric(1));
			tdlLiteralValue.setValue(sb.toString());
		}
	}
	class RemoveRandomChar implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			StringBuilder sb = new StringBuilder(value);
			Random rand = new Random();
			int randomIndex = rand.nextInt(sb.length());
			sb.deleteCharAt(randomIndex);
			tdlLiteralValue.setValue(sb.toString());
		}
	}
	class ReplaceRandomChar implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			StringBuilder sb = new StringBuilder(value);
			Random rand = new Random();
			int randomIndex = rand.nextInt(sb.length());
			sb.replace(randomIndex, randomIndex + 1, RandomStringUtils.randomAlphanumeric(1));
			tdlLiteralValue.setValue(sb.toString());
		}
	}
	class ReplaceString implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			tdlLiteralValue.setValue(RandomStringUtils.randomAlphanumeric(value.length()));
		}
	}
	class EmptyString implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			tdlLiteralValue.setValue("\"\"");
		}
	}

	//Numeric Modification Operators
	class NumericEqualOne implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			tdlLiteralValue.setValue("\"" + (1) + "\"");
		}
	}
	class NumericEqualZero implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			tdlLiteralValue.setValue("\"" + (0) + "\"");
		}
	}
	class NumericEqualMinusOne implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			tdlLiteralValue.setValue("\"" + (-1) + "\"");
		}
	}
	class IntNegateValue implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			int intValue = Integer.parseInt(value);
			tdlLiteralValue.setValue("\"" + (-intValue) + "\"");
		}
	}
	class IntPlusOne implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			int intValue = Integer.parseInt(value);
			tdlLiteralValue.setValue("\"" + (intValue + 1) + "\"");
		}
	}
	class IntMinusOne implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			int intValue = Integer.parseInt(value);
			tdlLiteralValue.setValue("\"" + (intValue - 1) + "\"");
		}
	}
	class IntMultiplyTwo implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			int intValue = Integer.parseInt(value);
			tdlLiteralValue.setValue("\"" + (intValue * 2) + "\"");
		}
	}
	class IntDevideTwo implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			int intValue = Integer.parseInt(value);
			tdlLiteralValue.setValue("\"" + (intValue / 2) + "\"");
		}
	}
	class IntChangeWithExisting implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			LiteralValueUse otherValue = intLiterals.stream()
					.filter(i -> i != tdlLiteralValue).findAny().get();
			tdlLiteralValue.setValue(otherValue.getValue());
		}
	}
	class FloatNegateValue implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			float floatValue = Float.parseFloat(value);
			tdlLiteralValue.setValue("\"" + (-floatValue) + "\"");
		}
	}
	class FloatPlusOne implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			float floatValue = Float.parseFloat(value);
			tdlLiteralValue.setValue("\"" + (floatValue + 1) + "\"");
		}
	}
	class FloatMinusOne implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			float floatValue = Float.parseFloat(value);
			tdlLiteralValue.setValue("\"" + (floatValue - 1) + "\"");
		}
	}
	class FloatMultiplyTwo implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			float floatValue = Float.parseFloat(value);
			tdlLiteralValue.setValue("\"" + (floatValue * 2) + "\"");
		}
	}
	class FloatDevideTwo implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			String value = getLiteralValue(tdlLiteralValue);
			float floatValue = Float.parseFloat(value);
			tdlLiteralValue.setValue("\"" + (floatValue / 2) + "\"");
		}
	}
	class FloatChangeWithExisting implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			LiteralValueUse tdlLiteralValue = (LiteralValueUse) data;
			LiteralValueUse otherValue = floatLiterals.stream()
					.filter(i -> i != tdlLiteralValue).findAny().get();
			tdlLiteralValue.setValue(otherValue.getValue());
		}
	}
}