package org.imt.tdl.amplification.testmodifier;

import java.util.List;

import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.amplification.dsl.amplifier.impl.TestModificationOperatorImpl;

public interface ITestModificationRunner {
	public List<TestDescription> generateTests(TestDescription testCase);
}
