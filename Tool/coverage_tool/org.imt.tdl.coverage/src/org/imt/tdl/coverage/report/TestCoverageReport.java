package org.imt.tdl.coverage.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.imt.tdl.coverage.computation.TDLCoverageUtil;

public class TestCoverageReport{

	private String reportTitle;
	private Set<String> coverableClasses;
	private List<EObject> objects;
	private List<String> objectCoverageStatus;
	
	private HashMap<Integer, TestCoverageReport> parentIndex_childrenReport;
	
	private int numOfCoveredObjs;
	private int numOfCoverableElements;
	private int numOfNotCoverableElements;
	private double coveragePercentage;
	
	public TestCoverageReport(String title) {
		reportTitle = title;
		coverableClasses = new HashSet<>();
		objects = new ArrayList<>();
		objectCoverageStatus = new ArrayList<>();
	}
	
	//The initial coverage status for all objects is not-covered
	public void setInitialCoverageStatus() {
		if (objects != null) {
			objects.forEach(o -> objectCoverageStatus.add(TDLCoverageUtil.NOT_COVERED));
		}
	}
		
	public String getObjectCoverage(EObject object) {
		int objectIndex = objects.indexOf(object);
		if (objectIndex != -1){
			return objectCoverageStatus.get(objectIndex);
		}
		return null;
	}
	
	public void setObjectCoverage(EObject object, String coverageStatus) {
		int objectIndex = objects.indexOf(object);
		if (objectIndex != -1) {
			if (objectCoverageStatus.get(objectIndex) != TDLCoverageUtil.COVERED){
				objectCoverageStatus.set(objectIndex, coverageStatus);
			}
		}
	}
	
	public void setObjectNotCoverable(EObject object) {
		int objectIndex = objects.indexOf(object);
		if (objectIndex != -1){
			objectCoverageStatus.set(objectIndex, TDLCoverageUtil.NOSTATUS);
		}
	}
	
	//if not-covered eobjects are of the not-coverable types, set their status as not-coverable
	public void findNotCoverableObjects() {
		for (int i=0; i<objectCoverageStatus.size(); i++) {
			if (objectCoverageStatus.get(i) == TDLCoverageUtil.NOT_COVERED &&
					!coverableClasses.contains(objects.get(i).eClass().getName())) {
				objectCoverageStatus.set(i, TDLCoverageUtil.NOSTATUS);
			}
		}
	}
	
	public double computeCoveragePercentage() {
		numOfCoveredObjs = 0;
		numOfCoverableElements = 0;
		numOfNotCoverableElements = 0;
		
		for (String coverage:objectCoverageStatus) {
			if (coverage == TDLCoverageUtil.NOSTATUS) {
				numOfNotCoverableElements++;
			}
			else if (coverage == TDLCoverageUtil.COVERED) {
				numOfCoveredObjs++;
			}
		}
		numOfCoverableElements = objectCoverageStatus.size() - numOfNotCoverableElements;
		coveragePercentage = (double)(numOfCoveredObjs*100)/numOfCoverableElements;
		try {
			BigDecimal bd = new BigDecimal(coveragePercentage).setScale(2, RoundingMode.HALF_UP);
			coveragePercentage = bd.doubleValue();
		}catch (NumberFormatException e) {
			System.out.println("NumberFormatException:" + coveragePercentage);
		}
		return coveragePercentage;
	}
	
	public double computeCoveragePercentageBasedOnChildren() {
		numOfCoveredObjs = 0;
		numOfCoverableElements = 0;
		numOfNotCoverableElements = 0;
		int numOfChildrens = 0;
		
		for (Entry<Integer, TestCoverageReport> entry:parentIndex_childrenReport.entrySet()) {
			numOfChildrens += entry.getValue().getObjects().size();
			for (String coverage:entry.getValue().getObjectCoverageStatus()) {
				if (coverage == TDLCoverageUtil.NOSTATUS) {
					numOfNotCoverableElements++;
				}
				else if (coverage == TDLCoverageUtil.COVERED) {
					numOfCoveredObjs++;
				}
			}
		}
		numOfCoverableElements = numOfChildrens - numOfNotCoverableElements;
		coveragePercentage = (double)(numOfCoveredObjs*100)/numOfCoverableElements;
		try {
			BigDecimal bd = new BigDecimal(coveragePercentage).setScale(2, RoundingMode.HALF_UP);
			coveragePercentage = bd.doubleValue();
		}catch (NumberFormatException e) {
			System.out.println("NumberFormatException:" + coveragePercentage);
		}
		return coveragePercentage;
	}
	
	public void printCoverageResult(String reportOwner) {
		System.out.println(reportOwner + "-" + reportTitle + ": " + 
				numOfCoveredObjs + "/" 
				+ numOfCoverableElements+ " = " 
				+ coveragePercentage +"%");
	}

	public void addChildrenReport(EObject parent, TestCoverageReport childrenReport) {
		if (parentIndex_childrenReport == null) {
			parentIndex_childrenReport = new HashMap<>();
		}
		int parentIndex = objects.indexOf(parent);
		parentIndex_childrenReport.put(parentIndex, childrenReport);
	}
	
	public void updateCoverageStatusBasedOnChildren() {
		for (Entry<Integer, TestCoverageReport> parentIndex_childrenReport_entry: parentIndex_childrenReport.entrySet()) {
			int objectIndex = parentIndex_childrenReport_entry.getKey();
			//if all children are covered, the parent is also covered
			if (parentIndex_childrenReport_entry.getValue().getObjectCoverageStatus().stream()
					.allMatch(c -> c == TDLCoverageUtil.COVERED)) {
				objectCoverageStatus.set(objectIndex, TDLCoverageUtil.COVERED);
			}
			//if all children are not-covered, the parent is also not-covered
			else if (parentIndex_childrenReport_entry.getValue().getObjectCoverageStatus().stream()
					.allMatch(c -> c == TDLCoverageUtil.NOT_COVERED)) {
				objectCoverageStatus.set(objectIndex, TDLCoverageUtil.NOT_COVERED);
			}
			else {
				//there are both covered and not-covered children, so the parent is partly-covered
				objectCoverageStatus.set(objectIndex, TDLCoverageUtil.PARTLY_COVERED);
			}
		}
	}
	
	public TestCoverageReport getChildrenReport(EObject parent) {
		int parentIndex = objects.indexOf(parent);
		return parentIndex_childrenReport.get(parentIndex);
	}
	
	public HashMap<Integer, TestCoverageReport> getParent_childrenReport() {
		return parentIndex_childrenReport;
	}

	public void setParent_childrenReport(HashMap<Integer, TestCoverageReport> parent_childrenReport) {
		if (this.parentIndex_childrenReport == null) {
			this.parentIndex_childrenReport = new HashMap<>();
		}
		for (Entry<Integer, TestCoverageReport> entry:parent_childrenReport.entrySet()) {
			int index = entry.getKey();
			TestCoverageReport copyChildrenReport = deepCloneTestCoverageReport(entry.getValue());
			this.parentIndex_childrenReport.put(index, copyChildrenReport);
		}
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public int getNumOfCoveredObjs() {
		return numOfCoveredObjs;
	}

	public int getNumOfCoverableElements() {
		return numOfCoverableElements;
	}

	public int getNumOfNotCoverableElements() {
		return numOfNotCoverableElements;
	}

	public Set<String> getCoverableClasses() {
		return coverableClasses;
	}
	public void setCoverableClasses(Set<String> coverableClasses) {
		this.coverableClasses.addAll(coverableClasses);
	}
	public List<EObject> getObjects() {
		return objects;
	}
	public void setObjects(List<EObject> modelObjects) {
		objects.addAll(modelObjects);
	}
	public List<String> getObjectCoverageStatus() {
		return objectCoverageStatus;
	}
	public void setObjectCoverageStatus(List<String> objectCoverageStatus) {
		this.objectCoverageStatus.addAll(objectCoverageStatus);
	}
	public double getCoveragePercentage() {
		return coveragePercentage;
	}
	public void setCoveragePercentage(double coveragePercentage) {
		this.coveragePercentage = coveragePercentage;
	}	
	
	public TestCoverageReport deepCloneTestCoverageReport(TestCoverageReport report){
		TestCoverageReport copiedReport = new TestCoverageReport(report.getReportTitle());
		copiedReport.coverableClasses = report.coverableClasses;
		copiedReport.objects = report.objects;
		copiedReport.objectCoverageStatus = new ArrayList<>(report.objectCoverageStatus);
		if (report.getParent_childrenReport() != null) {
			copiedReport.setParent_childrenReport(report.getParent_childrenReport());
		}
		copiedReport.numOfCoveredObjs = report.numOfCoveredObjs;
		copiedReport.numOfCoverableElements = report.numOfCoverableElements;
		copiedReport.numOfNotCoverableElements = report.numOfNotCoverableElements;
		copiedReport.coveragePercentage = report.coveragePercentage;
		return copiedReport;
    }
}
