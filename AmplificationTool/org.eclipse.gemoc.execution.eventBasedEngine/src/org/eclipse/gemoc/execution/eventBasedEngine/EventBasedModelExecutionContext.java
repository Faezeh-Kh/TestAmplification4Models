/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.eventBasedEngine;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.gemoc.executionframework.engine.commons.AbstractModelExecutionContext;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.trace.commons.model.trace.MSEModel;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtensionPoint;

public class EventBasedModelExecutionContext extends
		AbstractModelExecutionContext<EventBasedRunConfiguration, EventBasedExecutionPlatform, LanguageDefinitionExtension> {

	public EventBasedModelExecutionContext(EventBasedRunConfiguration runConfiguration, ExecutionMode executionMode)
			throws EngineContextException {
		super(runConfiguration, executionMode);
	}

	@Override
	protected LanguageDefinitionExtension getLanguageDefinitionExtension(String languageName)
			throws EngineContextException {
		LanguageDefinitionExtension languageDefinition = LanguageDefinitionExtensionPoint
				.findDefinition(_runConfiguration.getLanguageName());
		if (languageDefinition == null) {
			String message = "Cannot find sequential xdsml definition for the language "
					+ _runConfiguration.getLanguageName() + ", please verify that is is correctly deployed.";
			EngineContextException exception = new EngineContextException(message);
			throw exception;
		}
		return languageDefinition;
	}

	@Override
	public MSEModel getMSEModel() {
		return null;
	}

	@Override
	protected EventBasedExecutionPlatform createExecutionPlatform() throws CoreException {
		return new EventBasedExecutionPlatform(_languageDefinition, _runConfiguration, _resourceModel);
	}

}
