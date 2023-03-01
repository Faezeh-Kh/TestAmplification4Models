package org.imt.tdl.coverage.dslSpecific;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.imt.tdl.coverage.computation.TDLCoverageUtil;
import org.imt.tdl.coverage.computation.TDLTestCaseCoverage;
import org.imt.tdl.coverage.report.TestCoverageReport;
import org.imt.tdl.coverage.utilities.OCLInterpreter;

import DSLSpecificCoverage.Branch;
import DSLSpecificCoverage.BranchSpecification;
import DSLSpecificCoverage.ExplicitBranch;
import DSLSpecificCoverage.ImplicitBranch;

public class DSLSpecificBranchCoverage {

	private DSLSpecificCoverageExecutor dslSpecificCoverageExecutor;
	private TDLTestCaseCoverage testCaseCoverage;
	private HashMap<BranchSpecification, LinkedHashSet<EObject>> branchingRule_contextObjects;
	
	private HashMap<EObject, ArrayList<EObject>> branchingRoot_branches = new HashMap<>();
	private TestCoverageReport tcBranchCoverageReport;
	
	public DSLSpecificBranchCoverage(DSLSpecificCoverageExecutor executor) {
		dslSpecificCoverageExecutor = executor;
		testCaseCoverage = executor.getTestCaseCoverage();
		branchingRule_contextObjects = executor.getBranchingRule_contextObjects();
	}
	
	public TestCoverageReport runBranchCoverageComputation() {
		tcBranchCoverageReport = new TestCoverageReport(TDLCoverageUtil.BRANCHCOVERAGE);
		tcBranchCoverageReport.setCoverableClasses(dslSpecificCoverageExecutor.getCoverableClasses());

		OCLInterpreter oclLauncher = new OCLInterpreter();
		//for every branching root, find its branches
		for(Entry<BranchSpecification, LinkedHashSet<EObject>> rule_contextObjects:branchingRule_contextObjects.entrySet()) {
			for (EObject branchingRoot:rule_contextObjects.getValue()) {
				//because of the inheritance relationship, an object might be already added by other rules
				if (branchingRoot_branches.get(branchingRoot) == null) {
					branchingRoot_branches.put(branchingRoot, new ArrayList<>());
				}
				List<String> branchesCoverageStatus = new ArrayList<>();
				for (Branch branch:rule_contextObjects.getKey().getBranches()) {
					if (branch instanceof ExplicitBranch) {
						String query2getBranch = ((ExplicitBranch) branch).getOCLQuery();
						ArrayList<EObject> queryResult = oclLauncher.runQuery(branchingRoot, query2getBranch);
						if (queryResult != null && !queryResult.isEmpty()) {
							branchingRoot_branches.get(branchingRoot).addAll(queryResult);
							queryResult.forEach(branchObject -> branchesCoverageStatus.add(
									getExplicitBranchCoverage(branchingRoot, branchObject)));
						}	
					}
					else if(branch instanceof ImplicitBranch) {
						branchingRoot_branches.get(branchingRoot).add((ImplicitBranch) branch);
						branchesCoverageStatus.add(computeImplicitBranchCoverage(branchingRoot));
					}
				}
				//
				tcBranchCoverageReport.getObjects().add(branchingRoot);
				if (branchesCoverageStatus.stream().allMatch(c -> c == TDLCoverageUtil.COVERED)) {
					tcBranchCoverageReport.getObjectCoverageStatus().add(TDLCoverageUtil.COVERED);
				}
				else if (branchesCoverageStatus.stream().allMatch(c -> c == TDLCoverageUtil.NOT_COVERED)) {
					tcBranchCoverageReport.getObjectCoverageStatus().add(TDLCoverageUtil.NOT_COVERED);
				}
				else if (branchesCoverageStatus.stream().anyMatch(c -> c == TDLCoverageUtil.NOSTATUS)) {
					tcBranchCoverageReport.getObjectCoverageStatus().add(TDLCoverageUtil.NOSTATUS);
				}
				else {
					tcBranchCoverageReport.getObjectCoverageStatus().add(TDLCoverageUtil.PARTLY_COVERED);
				}
				TestCoverageReport branchesReport = new TestCoverageReport("");
				branchesReport.getObjects().addAll(branchingRoot_branches.get(branchingRoot));
				branchesReport.getObjectCoverageStatus().addAll(branchesCoverageStatus);
				tcBranchCoverageReport.addChildrenReport(branchingRoot, branchesReport);
			}			
		}
		tcBranchCoverageReport.computeCoveragePercentageBasedOnChildren();
		tcBranchCoverageReport.printCoverageResult(testCaseCoverage.getTestCaseName());
		
		return tcBranchCoverageReport;
	}

	//check if the branchObject is captured after branchingRoot
	private String getExplicitBranchCoverage(EObject branchingRoot, EObject branchObject) {
		List<EObject> allCoveredObjects = dslSpecificCoverageExecutor.getObjectsCapturedByTrace_extended();
		int firstRootIndexInTrace = allCoveredObjects.indexOf(branchingRoot);
		if (firstRootIndexInTrace == -1) {
			//the branching root is not covered, so its branches are also not-covered
			return  TDLCoverageUtil.NOT_COVERED;
		}
		int lastIndex = allCoveredObjects.size();
		if (allCoveredObjects.subList(firstRootIndexInTrace, lastIndex).stream()
				.anyMatch(o -> o == branchObject)) {
			return TDLCoverageUtil.COVERED;
		}
		return  TDLCoverageUtil.NOT_COVERED;
	}

	private String computeImplicitBranchCoverage(EObject branchingRoot) {
		List<EObject> allCoveredObjects = dslSpecificCoverageExecutor.getObjectsCapturedByTrace_extended();
		int firstRootIndexInTrace = allCoveredObjects.indexOf(branchingRoot);
		if (firstRootIndexInTrace == -1) {
			//the branching root is not covered, so its branches are also not-covered
			return  TDLCoverageUtil.NOT_COVERED;
		} 
		//if there is no covered explicit branch, the implicit branch is covered
		if (!branchingRoot_branches.get(branchingRoot).stream()
				.anyMatch(xb -> getExplicitBranchCoverage(branchingRoot, xb) == TDLCoverageUtil.COVERED)) {
			//if no explicit branch is covered, then the implicit branch is covered
			return TDLCoverageUtil.COVERED;
		}
		int index1 = allCoveredObjects.indexOf(branchingRoot);
		int lastIndex = allCoveredObjects.lastIndexOf(branchingRoot);
		if (index1 == lastIndex) {
			//if the branching root is captured once and the previous if does not return anything
			//it means the explicit branch is covered and the implicit branch is not covered
			return TDLCoverageUtil.NOT_COVERED;
		}
		//check if between two observations of the branching root or after the last observation, there is no explicit branch, then the implicit branch is covered
		for (int index2 = index1 + 1; index2 < allCoveredObjects.size(); index2++) {
			EObject capturedObject = allCoveredObjects.get(index2);
			//if there is an element between two occurrences of the branching root or after the last occurrence
			if (capturedObject == branchingRoot) {
				if (index2 > index1 + 1) {//if there are objects in between
					boolean isExplicitBranchCaptured = false;
					for (int i = index1 + 1; i < index2; i++) {
						EObject objectInBetween = allCoveredObjects.get(i);
						isExplicitBranchCaptured = branchingRoot_branches.get(branchingRoot).stream()
								.anyMatch(xb -> xb == objectInBetween);
						if (isExplicitBranchCaptured) {
							break;
						}
					}
					//if there is no explicit branch element between two occurrences of the branching root, the implicit branch is covered
					if (!isExplicitBranchCaptured) {
						return TDLCoverageUtil.COVERED;
					}
				}
				index1 = index2;
			}
			if (index2 == allCoveredObjects.size()-1) {
				//if we reached the end of the list, there is either one element after the last occurrence
				//of the branching root or the last element is a branching root
				if (!branchingRoot_branches.get(branchingRoot).stream().anyMatch(xb -> xb == capturedObject)) {
					return TDLCoverageUtil.COVERED;
				}
			}
		}
		return TDLCoverageUtil.NOT_COVERED;
	}

	public HashMap<BranchSpecification, LinkedHashSet<EObject>> getBranchingRule_contextObjects() {
		return branchingRule_contextObjects;
	}

	public HashMap<EObject, ArrayList<EObject>> getBranchingRoot_branches() {
		return branchingRoot_branches;
	}

	public TestCoverageReport getTcBranchCoverageReport() {
		return tcBranchCoverageReport;
	}

	public List<EObject> getBranchObjects() {
		return tcBranchCoverageReport.getObjects();
	}

	public List<String> getTcBranchCoverageStatus() {
		return tcBranchCoverageReport.getObjectCoverageStatus();
	}

	public double getTcBranchCoveragePercentage() {
		return tcBranchCoverageReport.getCoveragePercentage();
	}
}
