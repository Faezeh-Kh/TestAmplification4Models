package org.imt.tdl.mutation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.etsi.mts.tdl.ComponentInstanceRole;
import org.etsi.mts.tdl.GateReference;
import org.etsi.mts.tdl.Interaction;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.imt.k3tdl.interpreter.TestDescriptionAspect;
import org.imt.tdl.amplification.dsl.amplifier.MutationAnalysis;
import org.imt.tdl.testResult.TDLTestCaseResult;
import org.imt.tdl.testResult.TDLTestResultUtil;
import org.imt.tdl.utilities.PathHelper;

import wodel.dsls.WodelUtils;
import wodel.utils.exceptions.MetaModelNotFoundException;

public class MutationScoreCalculator {
	
	Package testSuite;
	List<TestDescription> testCases = new ArrayList<>();
	
	MutationAnalysis mutationAnalysisSpec;

	private static String KILLED = "killed";
	private static String ALIVE = "alive";
	
	private HashMap<String, String> mutant_status = new HashMap<>();
	private HashMap<TestDescription, Long> testCase_executionTime = new HashMap<>();
	private HashMap<TestDescription, Integer> testCase_numOfAssertions = new HashMap<>();

	public HashMap<String, List<String>> testCase_killedMutant = new HashMap<>();
	
	int numOfMutants;
	
	double seedMutationScore;
	int seedNumOfKilledMutants;
	
	int currentNumOfKilledMutants;
	double currentMutationScore;
	
	double timeoutFactor;
	int timeoutConstant;

	PathHelper pathHelper;
	Path runtimeWorkspacePath;
	Path seedModelPath;
	IProject mutantsProject;
	
	public MutationScoreCalculator(MutationAnalysis mutationAnalysisSpec, Package testSuite) {
		this.testSuite = testSuite;
		testCases = testSuite.getPackagedElement().stream().filter(p -> p instanceof TestDescription).
				map(p -> (TestDescription) p).collect(Collectors.toList());
		
		pathHelper = new PathHelper(testSuite);
		pathHelper.findModelAndDSLPathOfTestSuite();
		seedModelPath = pathHelper.getModelUnderTestPath();
		runtimeWorkspacePath = pathHelper.getRuntimeWorkspacePath();
		
		this.mutationAnalysisSpec = mutationAnalysisSpec;
		findMutants();
		//default values of pitest tool
		timeoutFactor = 1.25;
		timeoutConstant = 4000;
	}
	
	public String runTestSuiteOnOriginalModel() {
		System.out.println("Run test suite on the original model");
		for (TestDescription testCase:testCases) {
			String result = runTestCaseOnOriginalModel(testCase);
			if (result == TDLTestResultUtil.FAIL) {
				return TDLTestResultUtil.FAIL;
			}
		}
		return TDLTestResultUtil.PASS;
	}
	
	public String runTestCaseOnOriginalModel (TestDescription testCase) {
		String modelPath = seedModelPath.toString().replace("\\", "/");
		long start = System.currentTimeMillis();
		TDLTestCaseResult result = TestDescriptionAspect.executeTestCase(testCase, modelPath);
		long stop = System.currentTimeMillis();
		if (result.getValue() != TDLTestResultUtil.PASS) {
			return TDLTestResultUtil.FAIL;
		}
		testCase_executionTime.put(testCase, (stop-start));
		testCase_numOfAssertions.put(testCase, getNumOfAssertions(testCase));
		return TDLTestResultUtil.PASS;
	}
	
	public double calculateInitialMutationScore() {
		System.out.println("\nCalculating the mutation score of the input test suite");
		testCases.forEach(t -> runTestCaseOnAliveMutants(t));
		updateMutationScore();
		seedNumOfKilledMutants = currentNumOfKilledMutants;
		seedMutationScore = currentMutationScore;
		System.out.println("The mutation score of the input test suite is: " + seedMutationScore);
		printMutationAnalysisResult();
		return seedMutationScore;
	}
	
	public void runTestCaseOnAllMutants(TestDescription testCase) {
		//using default values for timeout from pitest tool
		//for timeoutConstant, it is calculated based on the waiting times used in the event manager:
		//at the first of configuration and for each assertion 5000 waiting time
		int timeoutConstant = testCase_numOfAssertions.get(testCase) * 5000 + 5000;
		long timeout = (long) (testCase_executionTime.get(testCase) * timeoutFactor + timeoutConstant);
		
		//run the test case only on alive mutants
		for (String mutant:mutant_status.keySet()) {
			String mutantPath = mutant.replace("\\", "/");
			TDLTestCaseResult result = null;
			final Runnable testRunner = new Thread() {
				  @Override 
				  public void run() { 
					  TestDescriptionAspect.executeTestCase(testCase, mutantPath);
				  }
				};

			final ExecutorService executor = Executors.newSingleThreadExecutor();
			@SuppressWarnings("rawtypes")
			final Future future = executor.submit(testRunner);
			executor.shutdown(); // This does not cancel the already-scheduled task.
			try { 
			  future.get(timeout, TimeUnit.MILLISECONDS);
			  //if there is no exception, get the result
			  result = TestDescriptionAspect.testCaseResult(testCase);
			}
			catch (InterruptedException ie) { 
				ie.printStackTrace();
			}
			catch (ExecutionException ee) { 
				ee.printStackTrace();
			}
			catch (TimeoutException te) { 
				//te.printStackTrace();
				System.out.println("TimeoutException -> There is an infinite loop in the mutant");
				future.cancel(true);
				TestDescriptionAspect.launcher(testCase).disposeResources();
			}
			if (!executor.isTerminated()) {
			    executor.shutdownNow(); // If you want to stop the code that hasn't finished
			}
			if (result == null || result.getValue() == TDLTestResultUtil.FAIL) {				
				keepTestCaseKilledMutantMapping(testCase.getName(), mutant);
				if (mutant_status.get(mutant) != KILLED) {
					mutant_status.replace(mutant, KILLED);
					currentNumOfKilledMutants++;
				}	
			}
		}
	}
	
	public void runTestCaseOnAliveMutants(TestDescription testCase) {
		Set<String> aliveMutants = new HashSet<>();
		if (currentNumOfKilledMutants == 0) {
			aliveMutants = mutant_status.keySet();
		}
		else {
			aliveMutants = mutant_status.keySet().stream().filter(mutant -> mutant_status.get(mutant) == ALIVE).collect(Collectors.toSet());
		}
		//using default values for timeout from pitest tool
		//for timeoutConstant, it is calculated based on the waiting times used in the event manager:
		//at the first of configuration and for each assertion 5000 waiting time
		int timeoutConstant = testCase_numOfAssertions.get(testCase) * 5000 + 5000;
		long timeout = (long) (testCase_executionTime.get(testCase) * timeoutFactor + timeoutConstant);
		
		//run the test case only on alive mutants
		for (String mutant:aliveMutants) {
			String mutantPath = mutant.replace("\\", "/");
			System.out.println("Running test case " + testCase.getName() + " on mutant " + mutantPath);
			TDLTestCaseResult result = null;
			final Runnable testRunner = new Thread() {
				  @Override 
				  public void run() { 
					  TestDescriptionAspect.executeTestCase(testCase, mutantPath);
				  }
				};

			final ExecutorService executor = Executors.newSingleThreadExecutor();
			@SuppressWarnings("rawtypes")
			final Future future = executor.submit(testRunner);
			executor.shutdown(); // This does not cancel the already-scheduled task.
			try { 
			  future.get(timeout, TimeUnit.MILLISECONDS);
			  //if there is no exception, get the result
			  result = TestDescriptionAspect.testCaseResult(testCase);
			}
			catch (InterruptedException ie) { 
				ie.printStackTrace();
			}
			catch (ExecutionException ee) { 
				ee.printStackTrace();
			}
			catch (TimeoutException te) { 
				//te.printStackTrace();
				System.out.println("TimeoutException -> There is an infinite loop in the mutant");
				future.cancel(true);
				TestDescriptionAspect.launcher(testCase).disposeResources();
			}
			if (!executor.isTerminated()) {
			    executor.shutdownNow(); // If you want to stop the code that hasn't finished
			}
			if (result == null || result.getValue() == TDLTestResultUtil.FAIL) {
				mutant_status.replace(mutant, KILLED);
				keepTestCaseKilledMutantMapping(testCase.getName(), mutant);
				currentNumOfKilledMutants++;
			}
		}
	}
	
	private void keepTestCaseKilledMutantMapping(String testCaseName, String mutantPath) {
		List<String> killedMutants = testCase_killedMutant.get(testCaseName);
		if (killedMutants == null) {
			killedMutants = new ArrayList<>();
			killedMutants.add(mutantPath);
			testCase_killedMutant.put(testCaseName, killedMutants);
		}
		else {
			killedMutants.add(mutantPath);
			testCase_killedMutant.replace(testCaseName, killedMutants);
		}
	}

	public boolean testCaseImprovesMutationScore (TestDescription testCase) {
		int pastNumOfKilledMutants = currentNumOfKilledMutants;
		runTestCaseOnAliveMutants(testCase);
		if (currentNumOfKilledMutants > pastNumOfKilledMutants) {
			double previousScore = currentMutationScore;
			updateMutationScore();
			System.out.println("The test case " + testCase.getName() + " has improved the mutation score by: " + (currentMutationScore - previousScore));
			System.out.println("- previous mutation score: " + previousScore);
			System.out.println("- new mutation score: " + currentMutationScore + "\n");
			return true;
		}
		return false;
	}

	private void findMutants() {
		String modelProjectName = seedModelPath.getParent().toString().substring(1);
		mutantsProject =  ResourcesPlugin.getWorkspace().getRoot().getProject(modelProjectName);
		File modelFolder = new File(mutantsProject.getLocation() + "/mutants");
		//if there is no mutant, generate mutants
		if (modelFolder.listFiles() == null || modelFolder.listFiles().length == 0) {
			String inputPath = mutantsProject.getLocation().toString();
			String outputPath = inputPath + "/mutants";
			String eclipseHomePath = "c:/labtop/gemoc_studio";
			String wodelProjectPath = "";
			String mutatorFilePath = mutationAnalysisSpec.getMutationOperators().get(0).getPathToMutationOperatorsFile();
			
			//if there is no mutation operator, generate the operators first
			//TODO: Generate operators based on the configuration file
			if (mutatorFilePath == null || mutatorFilePath.isEmpty()) {
				wodelProjectPath = "? how to find the path? should we add it to .dsl file?";
				String metamodelPath = wodelProjectPath + "? how to find path to ecore file";
				String eclipseCompilerName = "GemocStudioc";
				try {
					WodelUtils.generateMutationOperators(metamodelPath, inputPath, wodelProjectPath);
					WodelUtils.compileWodelProject(wodelProjectPath, eclipseHomePath, eclipseCompilerName);
				} catch (MetaModelNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				mutatorFilePath = "?";
			}
			
			//get location of workspace (java.io.File)
			String wodelProjectName = Paths.get(mutatorFilePath).getName(0).toString();
			wodelProjectPath = Platform.getBundle(wodelProjectName).getLocation();
			wodelProjectPath = wodelProjectPath.substring(wodelProjectPath.indexOf("C:/"), wodelProjectPath.length()-1);
			
			String currentPluginPath = Platform.getBundle("org.imt.tdl.mutation").getLocation();
			currentPluginPath = currentPluginPath.substring(currentPluginPath.indexOf("C:/"), currentPluginPath.length()-1);
			
			WodelUtils.generateMutants(inputPath, outputPath, currentPluginPath, wodelProjectPath, eclipseHomePath);
		}

		for (File file : modelFolder.listFiles()) {
			mutantsPathsHelper(modelProjectName, file);
		}
	}
	
	private void mutantsPathsHelper(String projectName, File file) {
		if (file.isFile() && file.getName().endsWith(".model")) {
			String filePath = file.getPath();
			if (runtimeWorkspacePath == null) {
				String path = filePath.substring(0, filePath.lastIndexOf(projectName)-1);
				runtimeWorkspacePath = Paths.get(path);
			}		
			//get the relative path of the file
			if (!filePath.equals(seedModelPath.toString())){
				filePath = filePath.replace(runtimeWorkspacePath.toString(), "");
				mutant_status.put(filePath, ALIVE);
				numOfMutants++;
			}
		}
		else if (file.isDirectory()){
			for (File innerFile : file.listFiles()) {
				mutantsPathsHelper(projectName, innerFile);
			}
		}
	}
	
	public void updateMutationScore() {
		currentMutationScore = (double) currentNumOfKilledMutants/numOfMutants;
	}
	
	public void printMutationAnalysisResult() {
		//saving results into a .txt file
		String outputFilePath = pathHelper.getRuntimeWorkspacePath() + "/"
				+ pathHelper.getTestSuiteProjectName() + "/" 
				+ pathHelper.getTestSuiteFileName() + 
				"_mutationReport.txt";
		StringBuilder sb = new StringBuilder();
		sb.append("Number of generated mutants: " + numOfMutants + "\n");
		sb.append("Number of killed mutants: " + currentNumOfKilledMutants + "\n");
		sb.append("--------------------------------------------------\n");
		for (String testCase:testCase_killedMutant.keySet()) {
			sb.append("Original test case: " + testCase + "\n");
			int j = 1;
			for (String mutant:testCase_killedMutant.get(testCase)) {
				sb.append("Killed mutant " + (j++) + ": " + mutant + "\n");
			}
		}
		if (currentNumOfKilledMutants < numOfMutants) {
			sb.append("--------------------------------------------------\n");
			sb.append("Number of alive mutants: " + (numOfMutants - currentNumOfKilledMutants) + "\n");
			int j = 1;
			for (String mutant:getAliveMutants()) {
				sb.append("Alive mutant " + (j++) + ": " + mutant + "\n");
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
	
	public Path getRuntimeWorkspacePath() {
		return runtimeWorkspacePath;
	}
	
	public int getNumOfMutants() {
		return numOfMutants;
	}
	
	public double getSeedMutationScore() {
		return seedMutationScore;
	}

	public int getSeedNumOfKilledMutants() {
		return seedNumOfKilledMutants;
	}

	public void setNumOfKilledMutants(int numOfKilledMutants) {
		this.currentNumOfKilledMutants = numOfKilledMutants;
	}
	
	public int getNumOfKilledMutants() {
		return currentNumOfKilledMutants;
	}
	
	public double getCurrentMutationScore() {
		return currentMutationScore;
	}
	
	public double getTestCaseMutationScore(TestDescription testCase) {
		int numOfKilledMutants = testCase_killedMutant.get(testCase).size();
		double mutationScore = (double) numOfKilledMutants/numOfMutants;
		return mutationScore;
	}
	
	public Set<String> getAliveMutants(){
		return mutant_status.keySet().stream().filter(mutant -> mutant_status.get(mutant) == ALIVE).collect(Collectors.toSet());
	}
	
	public void setTestCase_numOfAssertions(TestDescription testCase, int numOfAssertions) {
		testCase_numOfAssertions.put(testCase, numOfAssertions);
	}
	
	public boolean mutantsExists() {
		return numOfMutants > 0 ? true : false;
	}
	
	public int getNumOfAssertions(TestDescription tdlTestCase){
		TreeIterator<EObject> iterator = tdlTestCase.getBehaviourDescription().getBehaviour().eAllContents();
		int numOfAssertions = 0;
		while (iterator.hasNext()) {
			EObject eobject = iterator.next();
			if (eobject instanceof Interaction) {
				GateReference sourceGate = ((Interaction) eobject).getSourceGate();
				if (sourceGate.getComponent().getRole() == ComponentInstanceRole.SUT) {
					numOfAssertions++;
				}
			}
		}
		return numOfAssertions;
	}
}
