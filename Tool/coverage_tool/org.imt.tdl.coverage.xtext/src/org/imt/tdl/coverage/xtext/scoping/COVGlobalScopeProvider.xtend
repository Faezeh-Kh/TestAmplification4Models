package org.imt.tdl.coverage.xtext.scoping

import com.google.common.base.Splitter


import com.google.inject.Inject
import com.google.inject.Provider
import java.util.LinkedHashSet
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.resource.IResourceDescription
import org.eclipse.xtext.scoping.impl.ImportUriGlobalScopeProvider
import org.eclipse.xtext.util.IResourceScopeCache
import org.imt.tdl.coverage.xtext.resource.COVResourceDescriptionStrategy
import DSLSpecificCoverage.DSLSpecificCoveragePackage

class COVGlobalScopeProvider extends ImportUriGlobalScopeProvider{
	
	
	static final Splitter SPLITTER = Splitter.on(org.imt.tdl.coverage.xtext.resource.COVResourceDescriptionStrategy.SEPARATOR_CHAR);

	@Inject
	IResourceDescription.Manager descriptionManager;

	@Inject
	IResourceScopeCache cache;
	
	override protected getImportedUris(Resource resource) {
		return cache.get(COVGlobalScopeProvider.getSimpleName(), resource,
			new Provider<LinkedHashSet<URI>>() {
				override get() {
					val uniqueImportURIs = collectImportUris(resource, new LinkedHashSet<URI>(5))

					val uriIter = uniqueImportURIs.iterator()
					while (uriIter.hasNext()) {
						if (!EcoreUtil2.isValidUri(resource, uriIter.next()))
							uriIter.remove()
					}
					return uniqueImportURIs
				}
			});

	}
	
	def LinkedHashSet<URI> collectImportUris(Resource resource, LinkedHashSet<URI> uniqueImportURIs) {
		val resourceDescription = descriptionManager.getResourceDescription(resource)
		val models = resourceDescription.getExportedObjectsByType(DSLSpecificCoveragePackage.Literals.DOMAIN_SPECIFIC_COVERAGE)

		models.forEach [
			val userData = getUserData(COVResourceDescriptionStrategy.USER_KEY_IMPORT_URIS)
			if (!userData.isNullOrEmpty) {
				SPLITTER.split(userData).forEach [ uri |
					var includedUri = URI.createURI(uri)
					if (!resource.URI.isRelative) {
						includedUri = includedUri.resolve(resource.URI)
					}
					if (uniqueImportURIs.add(includedUri) && EcoreUtil2.isValidUri(resource, includedUri)) {
						collectImportUris(resource.getResourceSet().getResource(includedUri, true), uniqueImportURIs)
					}
				]
			}
		]
		uniqueImportURIs
	}
	 
}