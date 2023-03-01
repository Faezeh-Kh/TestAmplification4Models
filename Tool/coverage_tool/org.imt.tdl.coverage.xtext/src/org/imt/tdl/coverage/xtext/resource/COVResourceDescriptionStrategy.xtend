package org.imt.tdl.coverage.xtext.resource

import com.google.inject.Inject
import java.util.HashMap
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.EObjectDescription
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy
import org.eclipse.xtext.scoping.impl.ImportUriResolver
import org.eclipse.xtext.util.IAcceptor
import DSLSpecificCoverage.Import
import DSLSpecificCoverage.DomainSpecificCoverage

// transitive imports management inspired from
// https://blogs.itemis.com/en/in-five-minutes-to-transitive-imports-within-a-dsl-with-xtext
class COVResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {
		
	public val static String USER_KEY_IMPORT_URIS = "IMPORT_URIS";
	public val static String SEPARATOR_CHAR = ",";	
	
	@Inject
	ImportUriResolver uriResolver

	override boolean createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor) {
		if (eObject instanceof DomainSpecificCoverage) {
			this.createEObjectDescriptionForDomainSpecificCoverage(eObject, acceptor)
			return true
		} else {
			super.createEObjectDescriptions(eObject, acceptor)
		}
	}

	def void createEObjectDescriptionForDomainSpecificCoverage(DomainSpecificCoverage model,
		IAcceptor<IEObjectDescription> acceptor) {

		val uris = newArrayList()
		model.imports.forEach [ Import import |
			if (import.importURI !== null) {
				val resolvedURI = uriResolver.apply(import)
				uris.add(resolvedURI)
			}
		]
		val userData = new HashMap<String, String>
		userData.put(USER_KEY_IMPORT_URIS, uris.join(SEPARATOR_CHAR))
		acceptor.accept(EObjectDescription.create(QualifiedName.create(model.eResource.URI.toString), model, userData))
	}
}
