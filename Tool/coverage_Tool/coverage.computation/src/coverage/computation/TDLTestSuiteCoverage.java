package coverage.computation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.etsi.mts.tdl.Package;

public class TDLTestSuiteCoverage {

	private Package testSuite;
	private List<TDLTestCaseCoverage> tcCoverages;

	private List<EObject> modelObjects;
	private List<String> tsObjectCoverageStatus;
	
	double tsCoveragePercentage;
	int numOfCoveredObjs;
	int numOfNotCoverableElements;
	
	public List<ObjectCoverageStatus> coverageOfModelObjects;
	private ObjectCoverageStatus overallResult;
	
	public TDLTestSuiteCoverage() {
		this.tcCoverages = new ArrayList<>();
		this.modelObjects = new ArrayList<>();
		this.tsObjectCoverageStatus = new ArrayList<>();
		this.tsCoveragePercentage = 0;
		this.numOfCoveredObjs= 0 ;
		this.numOfNotCoverableElements = 0;
		this.coverageOfModelObjects = new ArrayList<>();
		this.overallResult = new ObjectCoverageStatus();
	}
	//for every test case of the test suite, add its coverage to the list
	public void addTCCoverage(TDLTestCaseCoverage tcCoverage) {
		this.tcCoverages.add(tcCoverage);
	}
	public List<TDLTestCaseCoverage> getTCCoverages() {
		return this.tcCoverages;
	}
	
	//Calculating coverage of the test suite based on its test cases coverage
	public void calculateTSCoverage() {
		//for each test case, calculate coverage using the generic tool
		//if the DSL provides a dsl-specific coverage tool, call its methods: ignoring model objects, retrieving coverage rules
		for (TDLTestCaseCoverage tcCoverageObj : this.tcCoverages) {
			if (TDLCoverageUtil.getInstance().getDslSpecificCoverageExtension() != null) {
				TDLCoverageUtil.getInstance().getDslSpecificCoverageExtension().ignoreModelObjects(tcCoverageObj.getMUTResource());
			}
			tcCoverageObj.calculateTCCoverage();
			overallResult.getCoverage().add(tcCoverageObj.getTcCoveragePercentage() + "");
			
			//if it is the first test case, copy the whole test case object coverage status for the test suite
			if (tsObjectCoverageStatus.size() == 0) {
				modelObjects.addAll(tcCoverageObj.getModelObjects());
				tsObjectCoverageStatus.addAll(tcCoverageObj.getTcObjectCoverageStatus());
			}else {
				for (int i=0; i<tcCoverageObj.getTcObjectCoverageStatus().size(); i++) {
					String tcCoverage = tcCoverageObj.getTcObjectCoverageStatus().get(i);
					if (tcCoverage == TDLCoverageUtil.COVERED & tsObjectCoverageStatus.get(i) != TDLCoverageUtil.COVERED) {
						tsObjectCoverageStatus.set(i, TDLCoverageUtil.COVERED);
					}
				}
			}
		}
		if (tsObjectCoverageStatus.size() != 0) {
			countNumOfElements();
			//System.out.println("\n" + "Model size (n. of EObjects): " + this.modelObjects.size() + "\n");
			calculateCoveragePercentage();
		}
	}
	
	private void countNumOfElements() {
		numOfCoveredObjs = 0;
		numOfNotCoverableElements = 0;
		for (String coverage:tsObjectCoverageStatus) {
			if (coverage == TDLCoverageUtil.NOT_TRACED) {
				numOfNotCoverableElements++;
			}
			else if (coverage == TDLCoverageUtil.COVERED) {
				numOfCoveredObjs++;
			}
		}
	}
	
	public void calculateCoveragePercentage() {
		int numOfCoverableElements = tsObjectCoverageStatus.size() - numOfNotCoverableElements;
		tsCoveragePercentage = (double)(numOfCoveredObjs*100)/numOfCoverableElements;
		BigDecimal bd = new BigDecimal(tsCoveragePercentage).setScale(2, RoundingMode.HALF_UP);
		tsCoveragePercentage = bd.doubleValue();
		overallResult.getCoverage().add(tsCoveragePercentage + "");
		setCoverageInfos();
		System.out.println("Test suite coverage: " + 
				numOfCoveredObjs + "/" + numOfCoverableElements + " = " + tsCoveragePercentage +"%");
	
	}

	public void setCoverageInfos() {
		List<EObject> modelObjects = this.modelObjects;
		//for each model object, the coverage information must be set
		for (int i=0; i<modelObjects.size(); i++) {
			ObjectCoverageStatus objectCoverage = new ObjectCoverageStatus();
			objectCoverage.setModelObject(modelObjects.get(i));
			objectCoverage.setMetaclass(modelObjects.get(i).eClass());
			for (TDLTestCaseCoverage tcCoverageObj : tcCoverages) {
				String tcCoverage = tcCoverageObj.getTcObjectCoverageStatus().get(i);
				objectCoverage.getCoverage().add(tcCoverage);
			}
			String tsCoverage = tsObjectCoverageStatus.get(i);
			objectCoverage.getCoverage().add(tsCoverage);
			coverageOfModelObjects.add(objectCoverage);
		}
		//add the overall result as the last row of the info array
		overallResult.setMetaclass(null);
		overallResult.setModelObject(null);
		coverageOfModelObjects.add(this.overallResult);
	}
	
	public List<ObjectCoverageStatus> getCoverageOfModelObjects(){
		return this.coverageOfModelObjects;
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
	public List<EObject> getModelObjects() {
		return modelObjects;
	}
	public List<TDLTestCaseCoverage> getTcCoverages() {
		return tcCoverages;
	}
	public double getTsCoveragePercentage() {
		return tsCoveragePercentage;
	}
	
	public List<String> getTsObjectCoverageStatus() {
		return tsObjectCoverageStatus;
	}
}
