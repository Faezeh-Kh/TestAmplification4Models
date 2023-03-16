package org.imt.tdl.amplification.testmodifier;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.etsi.mts.tdl.Message;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.tdlFactory;
import org.imt.tdl.amplification.dsl.amplifier.ApplicationPolicy;
import org.imt.tdl.amplification.dsl.amplifier.impl.TestModificationOperatorImpl;

public abstract class AbstractTestModificationRunner extends TestModificationOperatorImpl implements ITestModificationRunner{
	
	TestDescription tdlTestCase;
	
	//default policy is ALL
	ApplicationPolicy policy = ApplicationPolicy.ALL;
	int maxOccurrence = 1;
	
	boolean applyAllModifiers;
	
	int numOfNewTests;
	List<TestDescription> generatedTestsByModification = new ArrayList<>();
	
	protected TestDescription copyTdlTestCase(TestDescription testCase, int id, String modificationOperator) {
		TestDescription copyTdlTestCase = tdlFactory.eINSTANCE.createTestDescription();
		copyTdlTestCase.setName(testCase.getName()  + "_" + id + "_" + modificationOperator);
		copyTdlTestCase.setTestConfiguration(testCase.getTestConfiguration());
		copyTdlTestCase.setBehaviourDescription(EcoreUtil.copy(testCase.getBehaviourDescription()));
		return copyTdlTestCase;
	}
	
	protected Message copyTdlMessage (Message message) {
		Message copyMessage = tdlFactory.eINSTANCE.createMessage();
		copyMessage.setSourceGate(message.getSourceGate());
		copyMessage.getTarget().addAll(EcoreUtil.copyAll(message.getTarget()));
		copyMessage.setArgument(EcoreUtil.copy(message.getArgument()));
		copyMessage.setIsTrigger(message.isIsTrigger());
		copyMessage.setTimeLabel(EcoreUtil.copy(message.getTimeLabel()));
		return copyMessage;
	}

	public int getNumOfNewTests() {
		return numOfNewTests;
	}

	public void setNumOfNewTests(int numOfNewTests) {
		this.numOfNewTests = numOfNewTests;
	}
}
