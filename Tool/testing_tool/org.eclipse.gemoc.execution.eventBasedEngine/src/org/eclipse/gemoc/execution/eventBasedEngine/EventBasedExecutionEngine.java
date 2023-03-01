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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.gemoc.executionframework.engine.core.AbstractCommandBasedSequentialExecutionEngine;
import org.eclipse.gemoc.executionframework.engine.core.EngineStoppedException;
import org.eclipse.gemoc.executionframework.event.manager.GenericEventManager;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.trace.commons.model.trace.Step;

import com.google.common.collect.Streams;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager;
import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand;
import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry;

public class EventBasedExecutionEngine extends
		AbstractCommandBasedSequentialExecutionEngine<EventBasedModelExecutionContext, EventBasedRunConfiguration>
		implements IStepManager {

	private GenericEventManager eventManager = null;

	private boolean running = false;

	@Override
	public String engineKindName() {
		return "GEMOC Sequential Event-Based Execution Engine";
	}

	@Override
	protected void executeEntryPoint() {
		StepManagerRegistry.getInstance().registerManager(EventBasedExecutionEngine.this);
		try {
			eventManager = getAddonsTypedBy(GenericEventManager.class).stream().findFirst().orElse(null);
			if (eventManager != null) {
				final List<EventOccurrence> initialScenario = getExecutionContext().getRunConfiguration()
						.getInitialScenario();
				final boolean waitForEvent = getExecutionContext().getRunConfiguration().getWaitForEvent();
				initialScenario.forEach(e -> {
//					convertEventToExecutedResource(e, getExecutionContext().getResourceModel());
					eventManager.processEventOccurrence(e);
				});
				if (waitForEvent) {
					// We wait for new events to process until we receive a stop event (TODO?).
					// If a start event has been queued, it will be processed first.
					// We then wait for further events.
					while (running) {
						eventManager.waitForCallRequests();
						eventManager.processCallRequests();
					}
				} else {
					// The start event has been queued, so we process it
					// and let the execution unfold from there.
					eventManager.processCallRequests();
				}
			}
		} catch (EngineStoppedException stopException) {
			throw stopException;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			StepManagerRegistry.getInstance().unregisterManager(EventBasedExecutionEngine.this);
		}
	}

	@Override
	protected void beforeStart() {
		super.beforeStart();
		running = true;
	}

	@Override
	protected void performStop() {
		running = false;
		super.performStop();
	}

	@Override
	protected void notifyAboutToExecuteLogicalStep(Step<?> l) {
		Streams.stream(getExecutionContext().getExecutionPlatform().getEngineAddons())
				// .sorted((a1, a2) -> a1.getNotificationPriority().beforeStep -
				// a2.getNotificationPriority().beforeStep)
				.forEach(a -> {
					try {
						a.aboutToExecuteStep(this, l);
					} catch (EngineStoppedException ese) {
						// Activator.getDefault().debug("Addon (" + addon.getClass().getSimpleName() +
						// "@" + addon.hashCode() + ") has received stop command with message : " +
						// ese.getMessage());
						stop();
						throw ese; // do not continue to execute anything, forward exception
					} catch (Exception e) {
						// addonError(addon, e);
					}
				});
	}

	@Override
	protected void notifyLogicalStepExecuted(Step<?> l) {
		Streams.stream(getExecutionContext().getExecutionPlatform().getEngineAddons())
				// .sorted((a1, a2) -> a1.getNotificationPriority().afterStep -
				// a2.getNotificationPriority().afterStep)
				.forEach(a -> {
					try {
						a.stepExecuted(this, l);
					} catch (EngineStoppedException ese) {
						stop();
					} catch (Exception e) {
					}
				});
	}

	@Override
	public void executeStep(Object caller, final StepCommand command, String className, String methodName) {
		executeOperation(caller, new Object[0], className, methodName, new Runnable() {
			@Override
			public void run() {
				command.execute();
			}
		});
	}
	
	@Override
	public void executeStep(Object caller, Object[] parameters, StepCommand command, String className, String methodName) {
		executeOperation(caller, parameters, className, methodName, new Runnable() {
			@Override
			public void run() {
				command.execute();
			}
		});
	}

	@Override
	public boolean canHandle(Object caller) {
		if (caller instanceof EObject) {
			EObject eObj = (EObject) caller;
			org.eclipse.emf.transaction.TransactionalEditingDomain editingDomain = getEditingDomain(eObj);
			return editingDomain == this.editingDomain;
		}
		return false;
	}

	private static TransactionalEditingDomain getEditingDomain(EObject o) {
		return getEditingDomain(o.eResource().getResourceSet());
	}

	private static InternalTransactionalEditingDomain getEditingDomain(ResourceSet rs) {
		TransactionalEditingDomain edomain = org.eclipse.emf.transaction.TransactionalEditingDomain.Factory.INSTANCE
				.getEditingDomain(rs);
		if (edomain instanceof InternalTransactionalEditingDomain)
			return (InternalTransactionalEditingDomain) edomain;
		else
			return null;
	}

	@Override
	protected void initializeModel() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void prepareEntryPoint(EventBasedModelExecutionContext executionContext) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void prepareInitializeModel(EventBasedModelExecutionContext executionContext) {
		// TODO Auto-generated method stub
	}
}
