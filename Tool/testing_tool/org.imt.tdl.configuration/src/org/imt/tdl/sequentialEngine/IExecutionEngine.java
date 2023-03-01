package org.imt.tdl.sequentialEngine;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Trace;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.imt.tdl.observer.ModelExecutionObserver;

public interface IExecutionEngine {
	
	public void setUp(String MUTPath, String DSLPath);
	public void launchModelDebugger();
	public void setModelResource(Resource resource);
	public Resource getModelResource();
	public Trace<Step<?>, TracedObject<?>, State<?,?>> getExecutionTrace();
	public void attach (ModelExecutionObserver observer);
}
