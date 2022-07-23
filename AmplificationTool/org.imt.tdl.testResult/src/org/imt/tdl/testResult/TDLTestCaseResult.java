package org.imt.tdl.testResult;

import java.util.ArrayList;
import java.util.List;

import org.etsi.mts.tdl.TestDescription;

public class TDLTestCaseResult {
	
	private TestDescription testCase;

	private String value;
	
	private String description;
	
	private List<TDLMessageResult> tdlMessageResults;
	
	private int messageNumber = 0;
	
	public TDLTestCaseResult() {
		this.value = TDLTestResultUtil.PASS;
		this.tdlMessageResults = new ArrayList<TDLMessageResult>();
	}
	
	public TestDescription getTestCase() {
		return testCase;
	}

	public void setTestCase(TestDescription testCase) {
		this.testCase = testCase;
	}
	
	public String getTestCaseName() {
		return this.testCase.getName();
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return this.description;
	}
	
	public List<TDLMessageResult> getTdlMessages() {
		return this.tdlMessageResults;
	}
	public List<TDLMessageResult> getFailedTdlMessages() {
		List<TDLMessageResult> failedTdlMessages = new ArrayList<>();
		for (TDLMessageResult tdlMessage : this.tdlMessageResults) {
			if (tdlMessage.getFailure() == true) {
				failedTdlMessages.add(tdlMessage);
			}
		}
		return failedTdlMessages;
	}
	public int getNumOfPassedtdlMessages() {
		int passed = 0;
		for (TDLMessageResult tdlMessage : tdlMessageResults) {
			if (tdlMessage.getValue() == TDLTestResultUtil.PASS) {
				passed++;
			}
		}
		return passed;
	}
	
	public int getNumOfFailures() {
		int failures = 0;
		for (TDLMessageResult tdlMessage : tdlMessageResults) {
			if (tdlMessage.getFailure() == true) {
				failures++;
			}
		}
		return failures;
	}

	public void addTdlMessage(TDLMessageResult messageResult) {
		this.messageNumber++;
		messageResult.setTdlMessageId("Message#" + this.messageNumber);
		this.tdlMessageResults.add(messageResult);
	}
	
}
