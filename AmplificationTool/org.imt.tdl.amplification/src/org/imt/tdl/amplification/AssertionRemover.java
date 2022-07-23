package org.imt.tdl.amplification;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.etsi.mts.tdl.ComponentInstanceRole;
import org.etsi.mts.tdl.GateReference;
import org.etsi.mts.tdl.Interaction;
import org.etsi.mts.tdl.TestDescription;

public class AssertionRemover {

	int numOfAssertions;
	
	public void removeAssertionsFromTestCase(TestDescription tdlTestCase) {
		//removing all the tdl messages which are sent from gates of the SUT component
		TreeIterator<EObject> iterator = tdlTestCase.getBehaviourDescription().getBehaviour().eAllContents();
		List<EObject> assertions = new ArrayList<>();
		while (iterator.hasNext()) {
			EObject eobject = iterator.next();
			if (eobject instanceof Interaction) {
				GateReference sourceGate = ((Interaction) eobject).getSourceGate();
				if (sourceGate.getComponent().getRole() == ComponentInstanceRole.SUT) {
					assertions.add(eobject);
				}
			}
		}
		numOfAssertions = assertions.size();
		EcoreUtil.deleteAll(assertions, false);
	}
	
	public int getNumOfAssertions() {
		return numOfAssertions;
	}
}
