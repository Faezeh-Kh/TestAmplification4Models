package org.imt.tdl.amplification.filtering;

import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.amplification.AmplificationRuntimeException;

public interface ITestSelector {

	double calculateInitialScore (Package testSuite) throws AmplificationRuntimeException;
	boolean testCaseImprovesScore (TestDescription testCase);
	double getCurrentScore();
	void generateOverallScoreReport (StringBuilder stringBuilder);
	void generateAmplifiedTestcaseScoreReport (TestDescription testCase, StringBuilder stringBuilder);
}
