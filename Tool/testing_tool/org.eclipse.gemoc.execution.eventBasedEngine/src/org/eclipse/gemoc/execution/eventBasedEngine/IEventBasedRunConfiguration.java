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

import org.eclipse.gemoc.executionframework.event.manager.IImplementationRelationship;
import org.eclipse.gemoc.executionframework.event.manager.ISubtypingRelationship;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;

public interface IEventBasedRunConfiguration extends IRunConfiguration {

	// parameters that are specific to EventBasedExecutionEngine
	public static final String IMPL_REL_IDS = "IMPL_REL_IDS";
	public static final String SUBTYPE_REL_IDS = "SUBTYPE_REL_IDS";
	public static final String START_EVENT = "LAUNCH_START_EVENT";
	public static final String START_EVENT_OCCURRENCE_ARGS = "LAUNCH_START_EVENT_ARGS";
	public static final String WAIT_FOR_EVENT = "LAUNCH_WAIT_FOR_EVENT";
	
	List<EventOccurrence> getInitialScenario();
	
	boolean getWaitForEvent();
	
	List<IImplementationRelationship> getImplementationRelationships();
	
	List<ISubtypingRelationship> getSubtypingRelationships();
}
