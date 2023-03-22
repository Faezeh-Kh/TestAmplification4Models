package org.imt.tdl.amplification.filtering;

import java.util.ArrayList;
import java.util.List;

import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.imt.k3tdl.interpreter.PackageAspect;
import org.imt.k3tdl.interpreter.TestDescriptionAspect;
import org.imt.tdl.amplification.AmplificationRuntimeException;

import coverage.computation.TDLCoverageUtil;
import coverage.computation.TDLTestCaseCoverage;
import coverage.computation.TDLTestSuiteCoverage;

public class FilterByCoverage implements ITestSelector{
	
	double coveragePercentageThreshold;
	
	int numOfCoveredElements;

	private List<String> tsObjectCoverageStatus;
	
	public FilterByCoverage (double threshold) {
		coveragePercentageThreshold = threshold;
		tsObjectCoverageStatus = new ArrayList<>();
	}

	@Override
	public double calculateInitialScore(Package testSuite) throws AmplificationRuntimeException {
		PackageAspect.initializeModel(testSuite, null);
		PackageAspect.main(testSuite);
		TDLCoverageUtil.getInstance().runCoverageComputation();
		TDLTestSuiteCoverage tsCoverageRunner = TDLCoverageUtil.getInstance().getTestSuiteCoverage();
		tsObjectCoverageStatus = tsCoverageRunner.getTsObjectCoverageStatus();
		numOfCoveredElements = (int) tsObjectCoverageStatus.stream()
				.filter(c -> c == TDLCoverageUtil.COVERED).count();
		return tsCoverageRunner.getTsCoveragePercentage();
	}

	@Override
	public boolean testCaseImprovesScore(TestDescription testCase) {
		TDLTestCaseCoverage coverageRunner = TestDescriptionAspect.testCaseCoverage(testCase);
		coverageRunner.calculateTCCoverage();
		List<String> tcObjectCoverageStatus = coverageRunner.getTcObjectCoverageStatus();
		boolean scoreImproved = false;
		for (int i=0; i<tsObjectCoverageStatus.size();i++) {
			if (tsObjectCoverageStatus.get(i) != TDLCoverageUtil.COVERED 
					&& tcObjectCoverageStatus.get(i) == TDLCoverageUtil.COVERED) {
				numOfCoveredElements++;
				scoreImproved = true;
			}
		}
		return scoreImproved;
	}

	@Override
	public double getCurrentScore() {
		return (numOfCoveredElements/tsObjectCoverageStatus.size())*100;
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
