package org.imt.tdl.observer;

import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.imt.tdl.sequentialEngine.IExecutionEngine;

public abstract class ModelExecutionObserver {
	protected IExecutionEngine modelExecutionEngine;
	public abstract void update(EventOccurrence e);
}
