package org.imt.tdl.testResult;

import java.util.ArrayList;
import java.util.List;

import org.etsi.mts.tdl.Package;

public class TDLTestSuiteResult {
	
	private Package testSuite;
	private List<TDLTestCaseResult> testCaseResults;
	
	public TDLTestSuiteResult() {
		this.testCaseResults = new ArrayList<>();
	}
	public Package getTestSuite() {
		return testSuite;
	}
	public void setTestSuite(Package testSuite) {
		this.testSuite = testSuite;
	}
	public String getTestSuiteName() {
		return testSuite.getName();
	}
	public void addResult(TDLTestCaseResult result) {
		this.testCaseResults.add(result);
	}
	public List<TDLTestCaseResult> getTestCaseResults() {
		return testCaseResults;
	}
	
	public int getNumOfPassedTestCases() {
		int numPassedTests = 0;
		for (TDLTestCaseResult result : testCaseResults) {
			if (result.getValue() == TDLTestResultUtil.PASS) {
				numPassedTests++;
			}
		}
		return numPassedTests;
	}
	
	public int getNumOfFailedTestCases() {
		int numFailedTests = 0;
		for (TDLTestCaseResult result : testCaseResults) {
			if (result.getValue() == TDLTestResultUtil.FAIL) {
				numFailedTests++;
			}
		}
		return numFailedTests;
	}
	
	public int getNumOfInconclusiveTestCases() {
		int num = 0;
		for (TDLTestCaseResult result : testCaseResults) {
			if (result.getValue() == TDLTestResultUtil.INCONCLUSIVE) {
				num++;
			}
		}
		return num;
	}
	
	public String getTestSuiteResultValue() {
		if (getNumOfPassedTestCases() == this.testCaseResults.size()) {
			return TDLTestResultUtil.PASS;
		}
		return TDLTestResultUtil.FAIL;
	}
}
