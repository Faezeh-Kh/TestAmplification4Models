package org.imt.tdl.sequentialEngine;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.commons.GenericModelExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;

@SuppressWarnings("rawtypes")
public class CustomModelExecutionContext extends GenericModelExecutionContext {

	@SuppressWarnings("unchecked")
	public CustomModelExecutionContext(IRunConfiguration runConfiguration, ExecutionMode executionMode)
			throws EngineContextException {
		super(runConfiguration, executionMode);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public void setResourceModel(Resource modifiedResource) {
		_resourceModel = modifiedResource;
	}

	public boolean modelInitialized() {
		return _resourceModel == null ? false : true;
	}
}
