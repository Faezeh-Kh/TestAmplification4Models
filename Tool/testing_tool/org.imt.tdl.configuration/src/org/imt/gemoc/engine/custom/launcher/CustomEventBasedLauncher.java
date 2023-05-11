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
package org.imt.gemoc.engine.custom.launcher;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;
import org.eclipse.gemoc.commons.eclipse.ui.ViewHelper;
import org.eclipse.gemoc.execution.eventBasedEngine.EventBasedExecutionEngine;
import org.eclipse.gemoc.execution.eventBasedEngine.EventBasedModelExecutionContext;
import org.eclipse.gemoc.execution.eventBasedEngine.EventBasedRunConfiguration;
import org.eclipse.gemoc.execution.sequential.javaengine.ui.Activator;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.ui.launcher.AbstractSequentialGemocLauncher;
import org.eclipse.gemoc.executionframework.ui.views.engine.EnginesStatusView;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public class CustomEventBasedLauncher
		extends AbstractSequentialGemocLauncher<EventBasedModelExecutionContext, EventBasedRunConfiguration> {

	public final static String TYPE_ID = Activator.PLUGIN_ID + ".launcher";
	
	@Override
	public IExecutionEngine<EventBasedModelExecutionContext> createExecutionEngine(
			EventBasedRunConfiguration runConfiguration, ExecutionMode executionMode)
			throws CoreException, EngineContextException {
		EventBasedExecutionEngine executionEngine = new EventBasedExecutionEngine();
		EventBasedModelExecutionContext executioncontext = new EventBasedModelExecutionContext(runConfiguration,
				executionMode);
		executioncontext.getExecutionPlatform().getModelLoader().setProgressMonitor(launchProgressMonitor);
		executioncontext.initializeResourceModel();
		executionEngine.initialize(executioncontext);
		return executionEngine;
	}

	@Override
	protected String getLaunchConfigurationTypeID() {
		return TYPE_ID;
	}

	@Override
	protected String getDebugJobName(ILaunchConfiguration configuration, EObject firstInstruction) {
		return "Gemoc debug job";
	}

	@Override
	protected String getPluginID() {
		return Activator.PLUGIN_ID;
	}

	@Override
	public String getModelIdentifier() {
		return Activator.DEBUG_MODEL_ID;
	}

	@Override
	protected void prepareViews() {
		ViewHelper.retrieveView(EnginesStatusView.ID);
	}

	@Override
	protected EventBasedRunConfiguration parseLaunchConfiguration(ILaunchConfiguration configuration)
			throws CoreException {
		return new EventBasedRunConfiguration(configuration);
	}

	@Override
	protected MessagingSystem getMessagingSystem() {
		return Activator.getDefault().getMessaggingSystem();
	}

	@Override
	protected void setDefaultsLaunchConfiguration(ILaunchConfigurationWorkingCopy configuration) {

	}

	@Override
	protected void error(String message, Exception e) {
		Activator.error(message, e);
	}
}
