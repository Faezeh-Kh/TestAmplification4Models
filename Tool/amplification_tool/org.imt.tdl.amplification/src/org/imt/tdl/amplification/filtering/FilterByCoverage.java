package org.imt.tdl.amplification.filtering;

import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.amplification.AmplificationRuntimeException;

public class FilterByCoverage implements ITestSelector{
	
	double coveragePercentageThreshold;
	double currentCoveragePercentage;
	
	public FilterByCoverage (double threshold) {
		coveragePercentageThreshold = threshold;
	}

	@Override
	public double calculateInitialScore(Package testSuite) throws AmplificationRuntimeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean testCaseImprovesScore(TestDescription testCase) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getCurrentScore() {
		return currentCoveragePercentage;
	}

	@Override
	public void generateOverallScoreReport(StringBuilder stringBuilder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateAmplifiedTestcaseScoreReport(TestDescription testCase, StringBuilder stringBuilder) {
		// TODO Auto-generated method stub
		
	}

}
