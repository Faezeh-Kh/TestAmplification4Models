/*
 * generated by Xtext 2.26.0
 */
package coverage.dsl.xtext.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import coverage.dsl.xtext.COVRuntimeModule;
import coverage.dsl.xtext.COVStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class COVIdeSetup extends COVStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new COVRuntimeModule(), new COVIdeModule()));
	}
	
}