package org.imt.tdl.amplification;

import org.etsi.mts.tdl.Package;
import org.imt.tdl.amplification.dsl.amplifier.Configuration;

public class SearchBasedAmplifier extends AbstractAmplifier{
	
	//default constructor
	public SearchBasedAmplifier() {
		instantiateTestSelector();
	}
	
	//constructor based on configuration file
	public SearchBasedAmplifier(Configuration amplifierConfiguration) {
		instantiateTestSelector(amplifierConfiguration);
	}

	@Override
	public void runAmplification(Package tdlTestSuite) {
		// TODO Auto-generated method stub
		
	}

}
