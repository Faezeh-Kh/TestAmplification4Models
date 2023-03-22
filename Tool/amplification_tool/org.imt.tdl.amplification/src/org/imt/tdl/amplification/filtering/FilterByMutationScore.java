package org.imt.tdl.amplification.filtering;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.amplification.AmplificationRuntimeException;
import org.imt.tdl.amplification.dsl.amplifier.MutationAnalysis;
import org.imt.tdl.mutation.MutationScoreCalculator;
import org.imt.tdl.testResult.TDLTestResultUtil;

public class FilterByMutationScore implements ITestSelector{

	MutationAnalysis mutationAnalysis;
	double mutationScoreThreshold;
	
	MutationScoreCalculator scoreCalculator;
	private HashMap<String, List<String>> originalTestCase_killedMutant = new HashMap<>();
	
	public FilterByMutationScore (MutationAnalysis mutationAnalysis, double threshold) {
		this.mutationAnalysis = mutationAnalysis;
		mutationScoreThreshold = threshold;
	}

	@Override
	public double calculateInitialScore(Package testSuite) throws AmplificationRuntimeException{
		double initialScore = -1;
		scoreCalculator = new MutationScoreCalculator(mutationAnalysis.getMutantGenerator(), testSuite);
		if (scoreCalculator.mutantsExists()) {
			String result = scoreCalculator.runTestSuiteOnOriginalModel();
			if (result == TDLTestResultUtil.FAIL) {
				String message = "Amplification Stopped: The manually-written test suite is failed on the original model under test.";
				System.out.println(message);
				throw (new AmplificationRuntimeException(message));
			}
			initialScore = scoreCalculator.calculateInitialMutationScore();
			originalTestCase_killedMutant.putAll(scoreCalculator.testCase_killedMutant);
		}
		return initialScore;
	}

	@Override
	public boolean testCaseImprovesScore(TestDescription testCase){
		String testRunResult = scoreCalculator.runTestCaseOnOriginalModel(testCase);
		if (testRunResult != TDLTestResultUtil.PASS) {
			return false;
		}
		//check whether the new test case improves the mutation score, if there is any mutant
		else if (scoreCalculator.mutantsExists()){
			return scoreCalculator.testCaseImprovesMutationScore(testCase);
		}
		return false;
	}

	@Override
	public void generateOverallScoreReport(StringBuilder stringBuilder) {
		if (scoreCalculator.mutantsExists()) {
			stringBuilder.append("Total number of mutants: " + scoreCalculator.getNumOfMutants() + "\n");
			stringBuilder.append("- initial number of killed mutants: " + scoreCalculator.getSeedNumOfKilledMutants() + "\n");
			stringBuilder.append("- initial mutation score : " + (scoreCalculator.getSeedMutationScore() * 100) + "%" + "\n");
			stringBuilder.append("- number of mutants killed by improved test cases: " + (scoreCalculator.getNumOfKilledMutants()-scoreCalculator.getSeedNumOfKilledMutants())+ "\n");
			stringBuilder.append("- total number of killed mutants: " + scoreCalculator.getNumOfKilledMutants() + "\n");
			stringBuilder.append("- final mutation score : " + (scoreCalculator.getCurrentMutationScore() * 100) + "%" + "\n");
			stringBuilder.append("=> improvement in the mutation score : " + (scoreCalculator.getCurrentMutationScore() - scoreCalculator.getSeedMutationScore())*100 + "%" + "\n");
			stringBuilder.append("--------------------------------------------------\n");
			System.out.println(stringBuilder);
			//reporting list of alive mutants
			Set<String> aliveMutants = scoreCalculator.getAliveMutants();
			if (aliveMutants.size()>0) {
				stringBuilder.append("List of Alive mutants:\n");
				int j = 1;
				for (String mutant:aliveMutants) {
					stringBuilder.append("Alive mutant " + (j++) + ": " + mutant+ "\n");
				}
			}
			stringBuilder.append("--------------------------------------------------\n");
			//reporting mutants killed by the original test suite
			for (String testCase : originalTestCase_killedMutant.keySet()) {
				stringBuilder.append("Original test case: " + testCase+ "\n");
				int j = 1;
				for (String mutant:originalTestCase_killedMutant.get(testCase)) {
					stringBuilder.append("Killed mutant " + (j++) + ": " + mutant+ "\n");
				}
			}
			stringBuilder.append("--------------------------------------------------\n");
		}
		
	}

	@Override
	public void generateAmplifiedTestcaseScoreReport(TestDescription testCase, StringBuilder stringBuilder) {
		stringBuilder.append("Amplified Test Case: " + testCase.getName()+"\n");
		int j = 1;
		for (String mutant:scoreCalculator.testCase_killedMutant.get(testCase.getName())) {
			stringBuilder.append("Killed mutant " + (j++) + ": " + mutant+ "\n");
		}
		stringBuilder.append("\n");
	}
	
	@Override
	public double getCurrentScore() {
		return scoreCalculator.getCurrentMutationScore()*100;
	}
}
