package org.imt.tdl.amplification.configuration;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.etsi.mts.tdl.Package;
import org.imt.tdl.amplification.AmplificationRuntimeException;
import org.imt.tdl.amplification.IAmplifier;
import org.imt.tdl.amplification.IterativeAmplifier;
import org.imt.tdl.amplification.dsl.amplifier.Configuration;
import org.imt.tdl.amplification.dsl.amplifier.Iterative;
import org.imt.tdl.utilities.DSLProcessor;
import org.imt.tdl.utilities.PathHelper;

public class AmplifierConfiguration {
	
	PathHelper pathHelper;
	
	Resource testSuiteRes;
	Package tdlTestSuite;
	
	IAmplifier amplifier;
	
	public void configureAmplifier(IFile testSuiteFile) {
		pathHelper = new PathHelper(testSuiteFile);
		testSuiteRes = pathHelper.getTestSuiteResource();
		tdlTestSuite = pathHelper.getTestSuite();
		pathHelper.findModelAndDSLPathOfTestSuite();
		
		DSLProcessor dslProcessor = new DSLProcessor(pathHelper.getDSLName());
		if (dslProcessor.dslHasAmplifierConfig()) {
			URI uri = URI.createPlatformPluginURI(dslProcessor.getPath2AmplifierConfig(), false);
			Resource configRes = (new ResourceSetImpl()).getResource(uri, true);
			Configuration amplifierConfigObject = (Configuration) configRes.getContents().get(0);
			//check the approach
			if (amplifierConfigObject.getApproach() instanceof Iterative) {
				amplifier = new IterativeAmplifier(amplifierConfigObject);
			}
		}
		else {
			//run amplifier using the default settings: iterative approach
			amplifier = new IterativeAmplifier();
		}
		try {
			amplifier.runAmplification(tdlTestSuite);
		} catch (AmplificationRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
