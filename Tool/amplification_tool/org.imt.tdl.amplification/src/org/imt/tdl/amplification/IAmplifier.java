package org.imt.tdl.amplification;

import org.etsi.mts.tdl.Package;

public interface IAmplifier {

	void runAmplification(Package tdlTestSuite) throws AmplificationRuntimeException;

}
