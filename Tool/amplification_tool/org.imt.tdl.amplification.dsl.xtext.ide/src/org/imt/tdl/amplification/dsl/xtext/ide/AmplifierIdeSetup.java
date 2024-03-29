/*
 * generated by Xtext 2.27.0
 */
package org.imt.tdl.amplification.dsl.xtext.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.util.Modules2;
import org.imt.tdl.amplification.dsl.xtext.AmplifierRuntimeModule;
import org.imt.tdl.amplification.dsl.xtext.AmplifierStandaloneSetup;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class AmplifierIdeSetup extends AmplifierStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new AmplifierRuntimeModule(), new AmplifierIdeModule()));
	}
	
}
