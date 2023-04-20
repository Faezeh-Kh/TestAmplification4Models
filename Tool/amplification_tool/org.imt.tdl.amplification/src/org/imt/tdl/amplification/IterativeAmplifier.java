package org.imt.tdl.amplification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.tdlFactory;
import org.imt.k3tdl.interpreter.TestDescriptionAspect;
import org.imt.tdl.amplification.dsl.amplifier.Configuration;
import org.imt.tdl.amplification.dsl.amplifier.Iterative;
import org.imt.tdl.amplification.filtering.FilterByCoverage;
import org.imt.tdl.amplification.filtering.FilterByMutationScore;
import org.imt.tdl.amplification.filtering.ITestSelector;
import org.imt.tdl.amplification.testmodifier.AssertionGenerator;
import org.imt.tdl.amplification.testmodifier.AssertionRemover;
import org.imt.tdl.amplification.testmodifier.TestGenerator;
import org.imt.tdl.testResult.TDLTestCaseResult;
import org.imt.tdl.testResult.TDLTestResultUtil;
import org.imt.tdl.utilities.PathHelper;

public class IterativeAmplifier extends AbstractAmplifier{
	
	int maxIterations;
	int currentIteration = 0;
	
	Map<Integer, List<TestDescription>> iteration_ampTests = new HashMap<>();
	
	//default constructor
	public IterativeAmplifier() {
		maxIterations = 3;
		super.defaultSetup();
	}
	
	//constructor based on configuration file
	public IterativeAmplifier (Configuration amplifierConfiguration) {
		Iterative iapproach = (Iterative) amplifierConfiguration.getApproach();
		maxIterations = iapproach.getNumOfIterations();
		super.customSetup(amplifierConfiguration);
	}

	@Override
	public void runAmplification(Package tdlTestSuite) throws AmplificationRuntimeException{
		List<TestDescription> initialTdlTestCases = tdlTestSuite.getPackagedElement().stream()
				.filter(p -> p instanceof TestDescription)
				.map(t -> (TestDescription) t)
				.collect(Collectors.toList());
		long startTime = System.nanoTime();
		for (ITestSelector testSelector:testSelectors) {
			double initialScore = testSelector.calculateInitialScore(tdlTestSuite);
			double maxSelectionScore = testSelector.getScoreThreshold();
			if (initialScore == -1) {
				String message = "There is no mutants, so the initial score cannot be computed and the amplication stops";
				throw (new AmplificationRuntimeException(message));
			}
			else if (initialScore == 100.00) {
				String message = "As the initial score is 100% there is no need for test amplification";
				throw (new AmplificationRuntimeException(message));
			}
			else if (maxSelectionScore > 0 && initialScore == maxSelectionScore) {
				String message = "As the initial score is the maximum expected score, there is no need for test amplification";
				throw (new AmplificationRuntimeException(message));
			}
			//run test amplification on the given test cases
			amplifyTestCases(initialTdlTestCases, testSelector, maxSelectionScore);

			//considering number of iterations && max score
			if (maxIterations > 0 && maxSelectionScore > 0.0) {
				while (!iteration_ampTests.get(currentIteration-1).isEmpty() 
						&& (currentIteration<maxIterations && testSelector.getCurrentScore()<maxSelectionScore)) {
					//at each iteration, the previously amplified tests are amplified
					amplifyTestCases(iteration_ampTests.get(currentIteration-1), testSelector, maxSelectionScore);
				}
			}
			else {
				//considering number of iterations || max score
				while (!iteration_ampTests.get(currentIteration-1).isEmpty() 
						&& (currentIteration<maxIterations || testSelector.getCurrentScore()<maxSelectionScore)) {
					//at each iteration, the previously amplified tests are amplified
					amplifyTestCases(iteration_ampTests.get(currentIteration-1), testSelector, maxSelectionScore);
				}
			}
			printAmplificationResult(tdlTestSuite, testSelector);
		}
		if (numNewTests > 0) {
			System.out.println("\nPhase (4): Saving new test cases");
			super.saveAmplifiedTestCases(tdlTestSuite);
		}
		long stopTime = System.nanoTime();
		System.out.println("Execution time: " + (stopTime - startTime));
	}
	
	private void amplifyTestCases(List<TestDescription> tdlTestCases, ITestSelector testSelector, double maxSelectionScore) {
		Package tdlTestSuite = (Package) tdlTestCases.get(0).eContainer();
		List<TestDescription> amplifiedTests = new ArrayList<>();
		for (TestDescription testCase: tdlTestCases) {
			TestDescription copyTdlTestCase = tdlFactory.eINSTANCE.createTestDescription();
			copyTdlTestCase.setName(testCase.getName());
			copyTdlTestCase.setTestConfiguration(testCase.getTestConfiguration());
			copyTdlTestCase.setBehaviourDescription(EcoreUtil.copy(testCase.getBehaviourDescription()));
			
			System.out.println("\nPhase (1): Removing assertions from the test case");
			AssertionRemover assertionRemover = new AssertionRemover();
			assertionRemover.removeAssertionsFromTestCase(copyTdlTestCase);

			List<TestDescription> TMP = new ArrayList<>();
			System.out.println("Phase (2): Modifying test input Data to generate new test cases");
			TestGenerator testGenerator = new TestGenerator(tdlTestSuite);
			TMP.addAll(testGenerator.generateNewTestsByInputModification(copyTdlTestCase, modifiers));
			System.out.println("Done: #of generated test cases by input modification = " + TMP.size());
			
			System.out.println("\nPhase (3): Running new tests and generating assertions");
			for (TestDescription newTestCase : TMP) {
				tdlTestSuite.getPackagedElement().add(newTestCase);
				AssertionGenerator assertionGenerator = new AssertionGenerator();
				boolean result = assertionGenerator.generateAssertionsForTestCase(newTestCase);
				//check whether the assertion can be generated for the new test case
				if (!result) {
					tdlTestSuite.getPackagedElement().remove(newTestCase);
				}
				else {
					//ignore the new test case does not improve the score
					amplifiedTests.add(newTestCase);
					if (!testSelector.testCaseImprovesScore(newTestCase)) {
						tdlTestSuite.getPackagedElement().remove(newTestCase);
						amplifiedTests.remove(newTestCase);
					}
				}
				//if the score reached the max score, break the loop
				if (testSelector.getCurrentScore() >= maxSelectionScore) {
					break;
				}
			}	
			//if the score reached the max score, break the loop
			if (testSelector.getCurrentScore() >= maxSelectionScore) {
				break;
			}
		}
		numNewTests += amplifiedTests.size();
		iteration_ampTests.put(currentIteration, new ArrayList<>());
		iteration_ampTests.get(currentIteration).addAll(amplifiedTests);
		currentIteration++;
	}
	
	public String runTestCase (TestDescription testCase) {
		TestDescriptionAspect.activateConfiguration(testCase);
		TDLTestCaseResult result = TestDescriptionAspect.executeTestCase(testCase);
		if (result.getValue() != TDLTestResultUtil.PASS) {
			return TDLTestResultUtil.FAIL;
		}
		return TDLTestResultUtil.PASS;
	}
	
	protected void printAmplificationResult(Package tdlTestSuite, ITestSelector testSelector) {
		StringBuilder sb = new StringBuilder();
		testSelector.generateOverallScoreReport(sb);
		sb.append("Total number of test cases improving selection score: " + numNewTests + "\n");
		
		//saving results into a .txt file
		for (int iteration = 1; iteration <= iteration_ampTests.keySet().size(); iteration++) {				
			List<TestDescription> amplifiedTests = iteration_ampTests.get(iteration-1);
			if (amplifiedTests.size()>0) {
				sb.append("iteration " + iteration + ": " + amplifiedTests.size() + " generated test cases\n");
				amplifiedTests.forEach(t -> testSelector.generateAmplifiedTestcaseScoreReport(t, sb));
			}	
		}
		PathHelper pathHelper = new PathHelper(tdlTestSuite);
		pathHelper.findModelAndDSLPathOfTestSuite();
		String folderName = "";
		if (testSelector instanceof FilterByCoverage) {
			folderName = "amplification-result-coverage";
		}
		else if (testSelector instanceof FilterByMutationScore) {
			folderName = "amplification-result-mutation";
		}
		String outputFilePath = pathHelper.getRuntimeWorkspacePath() + "/"
				+ pathHelper.getTestSuiteProjectName() + "/" 
				+ folderName + "/";
		Path filePath = Paths.get(outputFilePath);
		try {
			Files.createDirectories(filePath);
			filePath = Paths.get(filePath + "/" + pathHelper.getTestSuiteFileName() + 
				"_amplificationReport.txt");
			Files.writeString(filePath,sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
