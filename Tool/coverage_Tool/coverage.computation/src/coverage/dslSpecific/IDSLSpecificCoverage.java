package coverage.dslSpecific;

import org.eclipse.emf.ecore.resource.Resource;

import DSLSpecificCoverage.DomainSpecificCoverage;

public interface IDSLSpecificCoverage {
	
	public DomainSpecificCoverage getDomainSpecificCoverage();
	public void ignoreModelObjects(Resource MUTResource);
	
}
