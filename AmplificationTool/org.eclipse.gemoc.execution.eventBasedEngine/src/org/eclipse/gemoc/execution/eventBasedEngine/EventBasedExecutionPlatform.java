/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.engine.commons.DefaultExecutionPlatform;
import org.eclipse.gemoc.executionframework.event.manager.GenericEventManager;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public class EventBasedExecutionPlatform extends DefaultExecutionPlatform {

	public EventBasedExecutionPlatform(LanguageDefinitionExtension _languageDefinition,
			EventBasedRunConfiguration _runConfiguration, Resource _executedResource) throws CoreException {
		super(_languageDefinition, _runConfiguration);
		addEngineAddon(new GenericEventManager(_languageDefinition.getName(), _executedResource,
				_runConfiguration.getImplementationRelationships(), _runConfiguration.getSubtypingRelationships()));
	}
}
