package org.imt.tdl.coverage.computation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.trace.commons.model.trace.SequentialStep;
import org.eclipse.gemoc.trace.commons.model.trace.SmallStep;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Trace;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.coverage.dslSpecific.DSLSpecificCoverageExecutor;
import org.imt.tdl.coverage.report.TestCoverageReport;

import DSLSpecificCoverage.DomainSpecificCoverage;

public class TDLTestCaseCoverage {
	
	private TestDescription testCase;
	private Resource MUTResource;
	
	private Trace<?, ?, ?> trace;
	private List<EObject> objectsCapturedByTrace;
	
	private List<TestCoverageReport> coverageReports;
	private TestCoverageReport tcCoverageByTraceReport;
	
	public TDLTestCaseCoverage() {
		objectsCapturedByTrace = new ArrayList<>();
		coverageReports = new ArrayList<>();
	}
	
	public void calculateTCCoverage () {	
		tcCoverageByTraceReport = new TestCoverageReport(TDLCoverageUtil.TRACEBASEDCOVERAGE);
		tcCoverageByTraceReport.setCoverableClasses(TDLCoverageUtil.getInstance().getCoverableClasses());
		tcCoverageByTraceReport.setObjects(listModelEObjects());
		tcCoverageByTraceReport.setInitialCoverageStatus();
		
		//calculating the coverage of the test case based on the model execution trace
		Step<?> rootStep = trace.getRootStep();
		calculateCoverageBasedOnTrace(rootStep);
		tcCoverageByTraceReport.findNotCoverableObjects();
		tcCoverageByTraceReport.computeCoveragePercentage();
		tcCoverageByTraceReport.printCoverageResult(getTestCaseName());
		coverageReports.add(tcCoverageByTraceReport);
		
		//if there are dsl-specific coverage rule sets, run them
		List<DomainSpecificCoverage> dslSpecificCoverages = TDLCoverageUtil.getInstance().getDslSpecificCoverages();
		if (dslSpecificCoverages != null) {
			for (DomainSpecificCoverage dsc:dslSpecificCoverages) {
				DSLSpecificCoverageExecutor executor = new DSLSpecificCoverageExecutor(this, dsc);
				executor.runDSLSpecificCoverage();
				coverageReports.addAll(executor.getTcCoverageReports());
			}
		}
	}

	private List<EObject> listModelEObjects() {
		//list objects of the MUTResource of the test case and set their initial status as NOT-COVERED
		List<EObject> allModelObjects = new ArrayList<>();
		TreeIterator<EObject> modelContents = MUTResource.getAllContents();
		while (modelContents.hasNext()) {
			allModelObjects.add(modelContents.next());
		}
		return allModelObjects;
	}
	
	private void calculateCoverageBasedOnTrace(Object rootStep) {
		//System.out.println("Execution Trace:");
		if (rootStep instanceof SmallStep<?>) {
			SmallStep<?> smallStep = (SmallStep<?>) rootStep;
			if (smallStep.getMseoccurrence() != null) {
				EObject object = smallStep.getMseoccurrence().getMse().getCaller();
				objectsCapturedByTrace.add(object);
				tcCoverageByTraceReport.setObjectCoverage(object, TDLCoverageUtil.COVERED);
			}
		}
		else if (rootStep instanceof SequentialStep<?, ?>) {
			SequentialStep<?, ?> seqStep = (SequentialStep<?, ?>) rootStep;
			if (seqStep.getMseoccurrence() != null) {
				EObject object = seqStep.getMseoccurrence().getMse().getCaller();
				objectsCapturedByTrace.add(object);
				tcCoverageByTraceReport.setObjectCoverage(object, TDLCoverageUtil.COVERED);
			}
			seqStep.getSubSteps().forEach(s -> calculateCoverageBasedOnTrace(s));
		}
	}
	
	public Trace<?, ?, ?> getTrace() {
		return trace;
	}

	public void setTrace(Trace<?, ?, ?> trace) {
		this.trace = trace;
	}

	public Resource getMUTResource() {
		return MUTResource;
	}

	public void setMUTResource(Resource MUTResource) {
		this.MUTResource = MUTResource;
	}

	public TestDescription getTestCase() {
		return testCase;
	}
	
	public void setTestCase(TestDescription testCase) {
		this.testCase = testCase;
	}
	
	public String getTestCaseName() {
		return testCase.getName();
	}
	
	public List<EObject> getAllModelObjects() {
		return tcCoverageByTraceReport.getObjects();
	}

	public List<EObject> getObjectsCapturedByTrace() {
		return objectsCapturedByTrace;
	}

	public List<String> getTcObjectCoverageStatusByTrace() {
		return tcCoverageByTraceReport.getObjectCoverageStatus();
	}

	public double getTcCoveragePercentageByTrace() {
		return tcCoverageByTraceReport.getCoveragePercentage();
	}

	public List<TestCoverageReport> getCoverageReports() {
		return coverageReports;
	}

	public TestCoverageReport getTcCoverageRepot(String reportTitle) {
		try {
			return coverageReports.stream()
					.filter(r -> r.getReportTitle().equals(reportTitle))
					.findFirst().get();
		}catch (NoSuchElementException e) {
			return null;
		}
	}
	
}
