package org.imt.tdl.amplification.testmodifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.amplification.dsl.amplifier.EventSequenceModifier;
import org.imt.tdl.amplification.dsl.amplifier.PrimitiveDataModifier;
import org.imt.tdl.amplification.dsl.amplifier.TestModificationOperator;

public class TestGenerator {
	 
	Package tdlTestSuite;
	TestDescription tdlTestCase;

	int numOfNewTests = 0;
	List<TestDescription> generatedTestsByModification = new ArrayList<>();
	
	public List<TestDescription> generateNewTestsByInputModification (TestDescription testCase
			, ArrayList<TestModificationOperator> modifiers) {
		tdlTestCase = testCase;
		runModifiers(modifiers);
		return generatedTestsByModification;
	}

	private void runModifiers(ArrayList<TestModificationOperator> modifiers) {
		PrimitiveValueModificationRunner runner4primitives = null;
		EventSequenceModificationRunner runner4events = null;
		if (modifiers == null) {
			runner4primitives = new PrimitiveValueModificationRunner();
			runner4events = new EventSequenceModificationRunner();
		}
		else {
			if (modifiers.stream().anyMatch(m -> m instanceof PrimitiveDataModifier)) {
				List<PrimitiveDataModifier> modifiers4primitives = modifiers.stream()
						.filter(PrimitiveDataModifier.class::isInstance)
						.map(PrimitiveDataModifier.class::cast)
						.collect(Collectors.toList());
				runner4primitives = new PrimitiveValueModificationRunner(modifiers4primitives);
			}
			if (modifiers.stream().anyMatch(m -> m instanceof EventSequenceModifier)) {
				List<EventSequenceModifier> modifiers4events = modifiers.stream()
						.filter(EventSequenceModifier.class::isInstance)
						.map(EventSequenceModifier.class::cast)
						.collect(Collectors.toList());
				runner4events = new EventSequenceModificationRunner(modifiers4events);
			}
		}
		if (runner4primitives != null) {
			runner4primitives.setNumOfNewTests(numOfNewTests);
			generatedTestsByModification.addAll(runner4primitives.generateTests(tdlTestCase));
			numOfNewTests += runner4primitives.getNumOfNewTests();
		}
		if (runner4events != null) {
			runner4events.setNumOfNewTests(numOfNewTests);
			generatedTestsByModification.addAll(runner4events.generateTests(tdlTestCase));
			numOfNewTests += runner4events.getNumOfNewTests();
		}
	}
}
