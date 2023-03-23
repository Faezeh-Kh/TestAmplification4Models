/*
 * generated by Xtext 2.27.0
 */
package org.imt.tdl.amplification.dsl.xtext.scoping;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.imt.tdl.amplification.dsl.amplifier.AmplifierPackage;
import org.imt.tdl.amplification.dsl.amplifier.Configuration;
import org.imt.tdl.amplification.dsl.amplifier.GeneratedOperator;
import org.imt.tdl.amplification.dsl.amplifier.MutationAnalysis;
import org.imt.tdl.amplification.dsl.amplifier.MutationOperatorType;
/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
public class AmplifierScopeProvider extends AbstractAmplifierScopeProvider {
	@Override
	public IScope getScope(EObject context, EReference reference) {
		if (reference.equals(AmplifierPackage.eINSTANCE.getConfiguration_Metamodel())) {
			Collection<EPackage> allPackages = EPackage.Registry.INSTANCE.values()
												.stream()
												.filter(EPackage.class::isInstance)
												.map(EPackage.class::cast)
												.collect(Collectors.toCollection(BasicEList::new));
			
			return Scopes.scopeFor(allPackages);			
		}
		else if (reference.equals(AmplifierPackage.eINSTANCE.getMutationOperatorType_Scope())) {
			Collection<EClass> allClasses = (
					(Configuration)
						((MutationAnalysis)
							((GeneratedOperator)
									((MutationOperatorType) context)
								.eContainer())
						.eContainer())
					.eContainer())
					.getMetamodel().getEClassifiers().stream()
					.filter(EClass.class::isInstance)
					.map(EClass.class::cast)
					.collect(Collectors.toCollection(BasicEList::new));
			return Scopes.scopeFor(allClasses);
		}
		return super.getScope(context, reference);
	}
}
