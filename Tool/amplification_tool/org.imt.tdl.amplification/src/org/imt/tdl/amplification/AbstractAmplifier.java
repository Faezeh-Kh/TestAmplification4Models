package org.imt.tdl.amplification;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.etsi.mts.tdl.Package;
import org.imt.tdl.amplification.dsl.amplifier.Configuration;
import org.imt.tdl.amplification.dsl.amplifier.ElementCoverage;
import org.imt.tdl.amplification.dsl.amplifier.MutationAnalysis;
import org.imt.tdl.amplification.dsl.amplifier.TestModificationOperator;
import org.imt.tdl.amplification.filtering.FilterByCoverage;
import org.imt.tdl.amplification.filtering.FilterByMutationScore;
import org.imt.tdl.amplification.filtering.ITestSelector;
import org.imt.tdl.utilities.PathHelper;

public abstract class AbstractAmplifier implements IAmplifier{

	ArrayList<TestModificationOperator> modifiers;
	
	double maxSelectionScore;
	ITestSelector testSelector;
	
	int numNewTests = 0;
	
	protected void defaultSetup() {
		//default: consider all modifiers
		modifiers = null;
		//set default selector: coverage is the default selector
		maxSelectionScore = 100;
		testSelector = new FilterByCoverage(maxSelectionScore);
	}
	
	
	protected void customSetup (Configuration amplifierConfiguration) {
		//finding the considered test modifiers
		modifiers = new ArrayList<>();
		amplifierConfiguration.getOperators().forEach(o -> modifiers.add(o));

		//finding the considered selection criterion
		maxSelectionScore = amplifierConfiguration.getApproach().getSelectionScoreThreshold();
		if (amplifierConfiguration.getFiltering() instanceof ElementCoverage) {
			testSelector = new FilterByCoverage(maxSelectionScore);
		}
		else if (amplifierConfiguration.getFiltering() instanceof MutationAnalysis) {
			testSelector = new FilterByMutationScore(
					(MutationAnalysis) amplifierConfiguration.getFiltering(), maxSelectionScore);
		}
	}

	protected void saveAmplifiedTestCases(Package tdlTestSuite) {
		PathHelper pathHelper = new PathHelper(tdlTestSuite);
		Resource testSuiteRes = tdlTestSuite.eResource();
		String sourcePath = testSuiteRes.getURI().toString();
		String tdlan2OutputPath = sourcePath.substring(0, sourcePath.lastIndexOf("/")+1) + pathHelper.getTestSuiteFileName() + "_amplified.tdlan2";
		Resource tdlan2TestSuiteRes = (new ResourceSetImpl()).createResource(URI.createURI(tdlan2OutputPath));
		//all the new elements are in the testSuiteRes
		tdlan2TestSuiteRes.getContents().addAll(EcoreUtil.copyAll(testSuiteRes.getContents()));
//		String xmiOutputPath = sourcePath.substring(0, sourcePath.lastIndexOf("/")+1) + pathHelper.getTestSuiteFileName() + "_amplified.xmi";
//		Resource xmiTestSuiteRes = (new ResourceSetImpl()).createResource(URI.createURI(xmiOutputPath));
//		//all the new elements are in the testSuiteRes
//		xmiTestSuiteRes.getContents().addAll(EcoreUtil.copyAll(testSuiteRes.getContents()));
//		
		try {
			tdlan2TestSuiteRes.save(null);
//			xmiTestSuiteRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		testSuiteRes.unload();
		tdlan2TestSuiteRes.unload();
//		xmiTestSuiteRes.unload();
	}
}
