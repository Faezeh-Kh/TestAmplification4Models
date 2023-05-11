package org.imt.tdl.amplification;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.etsi.mts.tdl.Package;
import org.imt.tdl.amplification.dsl.amplifier.Configuration;
import org.imt.tdl.amplification.dsl.amplifier.ElementCoverage;
import org.imt.tdl.amplification.dsl.amplifier.MutationAnalysis;
import org.imt.tdl.amplification.dsl.amplifier.TestModificationOperator;
import org.imt.tdl.amplification.dsl.amplifier.TestSelectionCriterion;
import org.imt.tdl.amplification.filtering.FilterByCoverage;
import org.imt.tdl.amplification.filtering.FilterByMutationScore;
import org.imt.tdl.amplification.filtering.ITestSelector;
import org.imt.tdl.utilities.PathHelper;

public abstract class AbstractAmplifier implements IAmplifier{

	ArrayList<TestModificationOperator> modifiers;
	
	List<ITestSelector> testSelectors = new ArrayList<>();
	
	int totalNumNewTests = 0;
	
	protected void defaultSetup() {
		//default: consider all modifiers
		modifiers = null;
		//set default selector: coverage is the default selector
		testSelectors.add(new FilterByCoverage(100));
	}
	
	
	protected void customSetup (Configuration amplifierConfiguration) {
		//finding the considered test modifiers
		modifiers = new ArrayList<>();
		amplifierConfiguration.getOperators().forEach(o -> modifiers.add(o));

		//finding the considered selection criterion
		for (TestSelectionCriterion selector:amplifierConfiguration.getFiltering()) {
			if (selector instanceof ElementCoverage) {
				double maxSelectionScore = ((ElementCoverage) selector).getCoveragePercentageThreshold();
				testSelectors.add(new FilterByCoverage(maxSelectionScore));
			}
			else if (selector instanceof MutationAnalysis) {
				double maxSelectionScore = ((MutationAnalysis) selector).getMutationScoreThreshold();
				testSelectors.add(new FilterByMutationScore(
						(MutationAnalysis) selector, maxSelectionScore));
			}
		}
	}

	protected void saveAmplifiedTestCases(Package tdlTestSuite) {
		PathHelper pathHelper = new PathHelper(tdlTestSuite);
		Resource testSuiteRes = tdlTestSuite.eResource();
		String sourcePath = testSuiteRes.getURI().toString();
		String folderName = "";
		if (testSelectors.size()>1) {
			folderName = "amplification-result-both";
		}
		else if (testSelectors.get(0) instanceof FilterByCoverage) {
			folderName = "amplification-result-coverage";
		}
		else if (testSelectors.get(0) instanceof FilterByMutationScore) {
			folderName = "amplification-result-mutation";
		}
		String outputPath = sourcePath.substring(0, sourcePath.lastIndexOf("/")+1)
				//+ folderName + "/"
				+ pathHelper.getTestSuiteFileName() + "_amplified.tdlan2";
		Resource tdlan2TestSuiteRes = (new ResourceSetImpl()).createResource(URI.createURI(outputPath));
		//all the new elements are in the testSuiteRes
		tdlan2TestSuiteRes.getContents().addAll(EcoreUtil.copyAll(testSuiteRes.getContents()));

		try {
			tdlan2TestSuiteRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		testSuiteRes.unload();
		tdlan2TestSuiteRes.unload();
	}
}
