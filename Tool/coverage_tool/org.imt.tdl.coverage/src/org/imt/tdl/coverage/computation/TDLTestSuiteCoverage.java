package org.imt.tdl.coverage.computation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EObject;
import org.etsi.mts.tdl.Package;
import org.imt.tdl.coverage.report.ObjectCoverageStatus;
import org.imt.tdl.coverage.report.TestCoverageReport;

public class TDLTestSuiteCoverage {

	private Package testSuite;
	private List<TDLTestCaseCoverage> tcCoverages;

	private List<TestCoverageReport> coverageReports;
	private List<List<ObjectCoverageStatus>> tsCoverageInfos;
	private HashMap<ObjectCoverageStatus, List<ObjectCoverageStatus>> info_childrenInfos;
	
	public TDLTestSuiteCoverage() {
		tcCoverages = new ArrayList<>();
		coverageReports = new ArrayList<>();
	}
	
	//for every test case of the test suite, add its coverage to the list
	public void addTCCoverage(TDLTestCaseCoverage tcCoverage) {
		tcCoverages.add(tcCoverage);
	}
	
	//Calculating coverage of the test suite based on its test cases coverage
	public void calculateTSCoverage() {
		//for each test case, calculate coverage using the generic tool
		//if the DSL provides a dsl-specific coverage tool, call its methods: ignoring model objects, retrieving coverage rules
		for (TDLTestCaseCoverage tcCoverageObj : tcCoverages) {
			if (TDLCoverageUtil.getInstance().getDslSpecificCoverageExtension() != null) {
				TDLCoverageUtil.getInstance().getDslSpecificCoverageExtension().ignoreModelObjects(tcCoverageObj.getMUTResource());
			}
			tcCoverageObj.calculateTCCoverage();
			tcCoverageObj.getCoverageReports().forEach(report -> updateTestSuiteReport(report));
		}
		for (TestCoverageReport report:coverageReports) {
			if (report.getParent_childrenReport() == null) {
				report.computeCoveragePercentage();
			}
			else {
				report.updateCoverageStatusBasedOnChildren();
				report.computeCoveragePercentageBasedOnChildren();
			}
			report.printCoverageResult(getTestSuiteName());
		}
		createCoverageInfos();
	}
	
	private void updateTestSuiteReport(TestCoverageReport tcCoverageReport) {
		TestCoverageReport tsCoverageReport;
		//if there is no report or no report with the same title, create a new report
		if (coverageReports.isEmpty() || !coverageReports.stream()
				.anyMatch(r -> r.getReportTitle().equals(tcCoverageReport.getReportTitle()))) {
			tsCoverageReport = new TestCoverageReport(tcCoverageReport.getReportTitle());
			tsCoverageReport.setObjects(tcCoverageReport.getObjects());
			tsCoverageReport.setObjectCoverageStatus(tcCoverageReport.getObjectCoverageStatus());
			if (tcCoverageReport.getParent_childrenReport() != null) {
				tsCoverageReport.setParent_childrenReport(tcCoverageReport.getParent_childrenReport());
			}
			coverageReports.add(tsCoverageReport);
		}else {
			tsCoverageReport = coverageReports.stream()
					.filter(r -> r.getReportTitle().equals(tcCoverageReport.getReportTitle()))
					.findFirst().get();
			for (int i=0; i<tcCoverageReport.getObjectCoverageStatus().size(); i++) {
				String tcCoverage = tcCoverageReport.getObjectCoverageStatus().get(i);
				String tsCoverage = tsCoverageReport.getObjectCoverageStatus().get(i);
				if (tcCoverage == TDLCoverageUtil.COVERED && tsCoverage != TDLCoverageUtil.COVERED) {
					tsCoverageReport.getObjectCoverageStatus().set(i, TDLCoverageUtil.COVERED);
				}
			}
			if (tsCoverageReport.getParent_childrenReport() != null) {
				for (Entry<Integer, TestCoverageReport> parent_childrenReport:tsCoverageReport.getParent_childrenReport().entrySet()) {
					int parentIndex = parent_childrenReport.getKey();
					TestCoverageReport tsChildrenReport = parent_childrenReport.getValue();
					TestCoverageReport tcChildrenReport = tcCoverageReport.getParent_childrenReport().get(parentIndex);
					for (int i=0; i<tcChildrenReport.getObjectCoverageStatus().size(); i++) {
						String tcChildCoverage = tcChildrenReport.getObjectCoverageStatus().get(i);
						String tsChildCoverage = tsChildrenReport.getObjectCoverageStatus().get(i);
						if (tcChildCoverage == TDLCoverageUtil.COVERED && tsChildCoverage != TDLCoverageUtil.COVERED) {
							tsChildrenReport.getObjectCoverageStatus().set(i, TDLCoverageUtil.COVERED);
						}
					}
				}
			}
		}
	}
	
	public void createCoverageInfos() {
		tsCoverageInfos = new ArrayList<>();
		for (TestCoverageReport tsReport:coverageReports) {
			List<ObjectCoverageStatus> coverageInfo4Objects = new ArrayList<>();
			ObjectCoverageStatus percentageInfo = new ObjectCoverageStatus();
			percentageInfo.setMetaclass(null);
			percentageInfo.setModelObject(null);
			//for each model object, the coverage information must be set
			for (int objectIndex=0; objectIndex<tsReport.getObjects().size(); objectIndex++) {
				coverageInfo4Objects.add(createObjectCoverageStatus(tsReport, objectIndex));
			}
			//add the overall result as the last row of the info array
			for (TDLTestCaseCoverage tcCoverageObj : tcCoverages) {
				TestCoverageReport tcReport = tcCoverageObj.getTcCoverageRepot(tsReport.getReportTitle());
				percentageInfo.getCoverage().add(tcReport.getCoveragePercentage()+"");
			}
			percentageInfo.getCoverage().add(tsReport.getCoveragePercentage()+"");
			coverageInfo4Objects.add(percentageInfo);
			tsCoverageInfos.add(coverageInfo4Objects);
		}
	}
	
	private ObjectCoverageStatus createObjectCoverageStatus(TestCoverageReport tsReport, int objectIndex) {
		ObjectCoverageStatus objectCoverageInfo = new ObjectCoverageStatus();
		EObject modelObject = tsReport.getObjects().get(objectIndex);
		objectCoverageInfo.setModelObject(modelObject);
		objectCoverageInfo.setMetaclass(modelObject.eClass());
		for (TDLTestCaseCoverage tcCoverageObj : tcCoverages) {
			TestCoverageReport tcReport = tcCoverageObj.getTcCoverageRepot(tsReport.getReportTitle());
			String tcCoverage = tcReport.getObjectCoverageStatus().get(objectIndex);
			objectCoverageInfo.getCoverage().add(tcCoverage);
		}
		String tsCoverage = tsReport.getObjectCoverageStatus().get(objectIndex);
		objectCoverageInfo.getCoverage().add(tsCoverage);
		
		if (tsReport.getParent_childrenReport() != null 
				&& tsReport.getParent_childrenReport().get(objectIndex) != null) {
			if (info_childrenInfos == null) {
				info_childrenInfos = new HashMap<>();
			}
			TestCoverageReport tsChildrenReport = tsReport.getParent_childrenReport().get(objectIndex);
			for (int childrenIndex = 0; childrenIndex < tsChildrenReport.getObjects().size(); childrenIndex++) {
				ObjectCoverageStatus childObjectCoverageInfo = new ObjectCoverageStatus();
				EObject childrenObject = tsChildrenReport.getObjects().get(childrenIndex);
				childObjectCoverageInfo.setModelObject(childrenObject);
				childObjectCoverageInfo.setMetaclass(childrenObject.eClass());
				for (TDLTestCaseCoverage tcCoverageObj : tcCoverages) {
					TestCoverageReport tcReport = tcCoverageObj.getTcCoverageRepot(tsReport.getReportTitle());
					TestCoverageReport tcChildrenReport = tcReport.getParent_childrenReport().get(objectIndex);
					String tcChildCoverage = tcChildrenReport.getObjectCoverageStatus().get(childrenIndex);
					childObjectCoverageInfo.getCoverage().add(tcChildCoverage);
				}
				String tsChildCoverage = tsChildrenReport.getObjectCoverageStatus().get(childrenIndex);
				childObjectCoverageInfo.getCoverage().add(tsChildCoverage);
				if (info_childrenInfos.get(objectCoverageInfo) == null) {
					info_childrenInfos.put(objectCoverageInfo, new ArrayList<>());
				}
				info_childrenInfos.get(objectCoverageInfo).add(childObjectCoverageInfo);
			}
		}
		return objectCoverageInfo;
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

	public List<TDLTestCaseCoverage> getTcCoverages() {
		return tcCoverages;
	}

	public List<TestCoverageReport> getCoverageReports() {
		return coverageReports;
	}

	public List<List<ObjectCoverageStatus>> getTsCoverageInfos() {
		return tsCoverageInfos;
	}

	public boolean isCoverageComputed() {
		return coverageReports.isEmpty()? false:true;
	}
	
	public TestCoverageReport getTsCoverageRepot(String reportTitle) {
		try {
			return coverageReports.stream()
					.filter(r -> r.getReportTitle().startsWith(reportTitle)).findFirst().get();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public List<ObjectCoverageStatus> getTsCoverageInfo(String reportTitle) {
		try {
			return tsCoverageInfos.get(coverageReports.indexOf(getTsCoverageRepot(reportTitle)));
		}catch (Exception e) {
			return getTsCoverageInfo(TDLCoverageUtil.TRACEBASEDCOVERAGE);
		}
	}

	public HashMap<ObjectCoverageStatus, List<ObjectCoverageStatus>> getInfo_childrenInfos() {
		return info_childrenInfos;
	}
}
