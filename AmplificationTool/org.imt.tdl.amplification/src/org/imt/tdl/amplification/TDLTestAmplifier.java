package org.imt.tdl.amplification;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.tdlFactory;
import org.imt.tdl.mutation.MutationRuntimeException;
import org.imt.tdl.mutation.MutationScoreCalculator;
import org.imt.tdl.mutation.utilities.PathHelper;
import org.imt.tdl.testResult.TDLTestResultUtil;

public class TDLTestAmplifier {
	
	Resource testSuiteRes;
	Package tdlTestSuite;
	
	MutationScoreCalculator scoreCalculator;
	int initialNumOfKilledMutants;
	double initialMutationScore;
	
	int numOfIteration;
	int numNewTests;
	Map<Integer, List<TestDescription>> iteration_ampTests = new HashMap<>();
	
	public void amplifyTestSuite(IFile testSuiteFile) throws MutationRuntimeException {
		PathHelper.getInstance().setTestSuiteFile(testSuiteFile);
		testSuiteRes = PathHelper.getInstance().getTestSuiteResource();
		tdlTestSuite = PathHelper.getInstance().getTestSuite();
		
		//calculating the mutation score of the manually-written test suite (i.e., the input test suite)
		scoreCalculator = new MutationScoreCalculator(tdlTestSuite);
		initialNumOfKilledMutants = 0;
		initialMutationScore = 0;
		
		if (!scoreCalculator.noMutantsExists) {
			String result = scoreCalculator.runTestSuiteOnOriginalModel();
			if (result == TDLTestResultUtil.FAIL) {
				String message = "Amplification Stopped: The manually-written test suite is failed on the original model under test.";
				System.out.println(message);
				throw (new MutationRuntimeException(message));
			}
			initialMutationScore = scoreCalculator.calculateInitialMutationScore();
			initialNumOfKilledMutants = scoreCalculator.getNumOfKilledMutants();
		}
		
		//perform test amplification if the mutation score of the input test suite is not 100%
		if (initialMutationScore < 1) {
			numOfIteration = 0;
			numNewTests = 0;
			
			List<TestDescription> initialTdlTestCases = tdlTestSuite.getPackagedElement().stream().
					filter(p -> p instanceof TestDescription).map(t -> (TestDescription) t).collect(Collectors.toList());
			
			//run test amplification on the given test cases
			amplifyTestCases(initialTdlTestCases);
			/*
			 * In DSpot, the used stop-criterion is a number of iteration (default = 3). 
			 * Our stop-criterion is a combination of number of iteration and reaching to 100% mutation score
			 * at each iteration, the previously amplified tests are amplified
			 */
			while (numOfIteration<3 && scoreCalculator.getOverallMutationScore()<1) {
				amplifyTestCases(iteration_ampTests.get(numOfIteration-1));
			}
			
			System.out.println("\nTest Amplification has been performed successfully.");
			if (!scoreCalculator.noMutantsExists) {
				String outputFilePath = PathHelper.getInstance().getWorkspacePath() + "/"
							+ PathHelper.getInstance().getTestSuiteProjectName() + "/" 
							+ PathHelper.getInstance().getTestSuiteFileName() + 
							"_amplificationReport.txt";
				printAmplificationResult(outputFilePath);
			}
			
			if (numNewTests > 0) {
				System.out.println("\nPhase (4): Saving new test cases");
				saveAmplifiedTestCases();
			}
		}else {
			System.out.println("As the initial mutation score is 100% there is no need for test amplification");
		}
	}

	private void amplifyTestCases(List<TestDescription> tdlTestCases) {
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
			TDLTestInputDataAmplification IAmplifier = new TDLTestInputDataAmplification(tdlTestSuite);
			TMP.addAll(IAmplifier.generateNewTestsByInputModification(copyTdlTestCase));
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
					//check whether the new test case is passed on the original model
					amplifiedTests.add(newTestCase);
					String testRunResult = scoreCalculator.runTestCaseOnOriginalModel(newTestCase);
					if (testRunResult != TDLTestResultUtil.PASS) {
						tdlTestSuite.getPackagedElement().remove(newTestCase);
						amplifiedTests.remove(newTestCase);
					}
					//check whether the new test case improves the mutation score, if there is any mutants
					else if (!scoreCalculator.noMutantsExists){
						boolean improvement = scoreCalculator.testCaseImprovesMutationScore(newTestCase);
						if (!improvement) {
							tdlTestSuite.getPackagedElement().remove(newTestCase);
							amplifiedTests.remove(newTestCase);
						}
					}
				}
				//if all the mutants are killed by now, break the loop
				if (scoreCalculator.getNumOfMutants() == scoreCalculator.getNumOfKilledMutants()) {
					break;
				}
			}	
			//if all the mutants are killed by now, break the loop
			if (scoreCalculator.getNumOfMutants() == scoreCalculator.getNumOfKilledMutants()) {
				break;
			}
		}
		numNewTests += amplifiedTests.size();
		iteration_ampTests.put(numOfIteration, new ArrayList<>());
		iteration_ampTests.get(numOfIteration).addAll(amplifiedTests);
		numOfIteration++;
	}

	private void saveAmplifiedTestCases() {
		String sourcePath = testSuiteRes.getURI().toString();
		String extension = ".tdlan2";
		if (sourcePath.endsWith(".xmi")) {
			extension = ".xmi";
		}
		
		String outputPath = sourcePath.substring(0, sourcePath.lastIndexOf("/")+1) + PathHelper.getInstance().getTestSuiteFileName() + "_amplified" + extension;
		Resource newTestSuiteRes = (new ResourceSetImpl()).createResource(URI.createURI(outputPath));
		//all the new elements are in the testSuiteRes
		newTestSuiteRes.getContents().addAll(EcoreUtil.copyAll(testSuiteRes.getContents()));
		try {
			newTestSuiteRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		testSuiteRes.unload();
		newTestSuiteRes.unload();
	}

	private void printAmplificationResult(String outputFilePath) {
		StringBuilder sb = new StringBuilder();
		sb.append("Total number of mutants: " + scoreCalculator.getNumOfMutants() + "\n");
		sb.append("- initial number of killed mutants: " + initialNumOfKilledMutants + "\n");
		sb.append("- initial mutation score : " + (initialMutationScore * 100) + "%" + "\n");
		sb.append("Total number of test cases improving mutation score: " + numNewTests + "\n");
		sb.append("- number of mutants killed by improved test cases: " + (scoreCalculator.getNumOfKilledMutants()-initialNumOfKilledMutants)+ "\n");
		sb.append("- total number of killed mutants: " + scoreCalculator.getNumOfKilledMutants() + "\n");
		sb.append("- final mutation score : " + (scoreCalculator.getOverallMutationScore() * 100) + "%" + "\n");
		sb.append("=> improvement in the mutation score : " + (scoreCalculator.getOverallMutationScore() - initialMutationScore)*100 + "%" + "\n");
		sb.append("--------------------------------------------------\n");
		System.out.println(sb);
		
		//saving results into a .txt file
		for (int iteration = 1; iteration <= iteration_ampTests.keySet().size(); iteration++) {				
			List<TestDescription> amplifiedTests = iteration_ampTests.get(iteration-1);
			if (amplifiedTests.size()>0) {
				sb.append("iteration " + iteration + ": " + amplifiedTests.size() + " generated test cases\n");
				for (int i=0; i<amplifiedTests.size(); i++) {
					TestDescription amplifiedTest = amplifiedTests.get(i);
					sb.append("Amplified Test Case: " + amplifiedTest.getName()+"\n");
					int j = 1;
					for (String mutant:scoreCalculator.testCase_killedMutant.get(amplifiedTest.getName())) {
						sb.append("Killed mutant " + (j++) + ": " + mutant+ "\n");
					}
					scoreCalculator.testCase_killedMutant.remove(amplifiedTest.getName());
					sb.append("\n");
				}
			}	
		}
		sb.append("--------------------------------------------------\n");
		for (String testCase:scoreCalculator.testCase_killedMutant.keySet()) {
			sb.append("Original test case: " + testCase+ "\n");
			int j = 1;
			for (String mutant:scoreCalculator.testCase_killedMutant.get(testCase)) {
				sb.append("Killed mutant " + (j++) + ": " + mutant+ "\n");
			}
		}
		sb.append("--------------------------------------------------\n");
		Set<String> aliveMutants = scoreCalculator.getAliveMutants();
		if (aliveMutants.size()>0) {
			sb.append("List of Alive mutants:\n");
			int j = 1;
			for (String mutant:aliveMutants) {
				sb.append("Alive mutant " + (j++) + ": " + mutant+ "\n");
			}
		}
		try {
			Path filePath = Paths.get(outputFilePath);
			Files.writeString(filePath,sb);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
