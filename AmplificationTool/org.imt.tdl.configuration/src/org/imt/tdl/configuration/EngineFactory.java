package org.imt.tdl.configuration;

import java.util.ArrayList;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Trace;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.imt.tdl.eventBasedEngine.IEventBasedExecutionEngine;
import org.imt.tdl.eventBasedEngine.K3EventManagerLauncher;
import org.imt.tdl.oclInterpreter.OCLInterpreter;
import org.imt.tdl.sequentialEngine.ALEEngineLauncher;
import org.imt.tdl.sequentialEngine.IExecutionEngine;
import org.imt.tdl.sequentialEngine.ISequentialExecutionEngine;
import org.imt.tdl.sequentialEngine.JavaEngineLauncher;

public class EngineFactory{
	
	private String DSLPath;
	private String MUTPath;
	public boolean launcherIsTuned = false;
	
	private ISequentialExecutionEngine sequentialEngineLauncher;
	private OCLInterpreter oclLauncher;
	private IEventBasedExecutionEngine eventManagerLauncher;
	
	public final static String GENERIC = "Generic";
	public final static String DSL_SPECIFIC = "reactiveGate";
	public final static String OCL = "OCL";

	public void setUp(String commandType) throws CoreException, EngineContextException {
		if (commandType.equals(GENERIC)) {
			String engineType = this.getEngineType();
			if (engineType=="ale") {
				sequentialEngineLauncher = new ALEEngineLauncher();	
			}else if(engineType=="k3") {
				sequentialEngineLauncher = new JavaEngineLauncher();
			}
			sequentialEngineLauncher.setUp(MUTPath, DSLPath);
		}else if(commandType.equals(DSL_SPECIFIC)) {
			this.eventManagerLauncher = new K3EventManagerLauncher();
			this.eventManagerLauncher.setUp(MUTPath, DSLPath);
			if (!this.eventManagerLauncher.isEngineStarted()) {
				IDebugTarget[] debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
				if (debugTargets.length > 0) {
					//we are in the Debug mode, so debug the model under test
					this.eventManagerLauncher.launchModelDebugger();
				}else {
					this.eventManagerLauncher.startEngine();
				}
			}
		}else if (commandType.equals(OCL)) {
			if (oclLauncher == null) {
				oclLauncher = new OCLInterpreter();
				oclLauncher.setUp();
			}
		}
		launcherIsTuned = true;
	}
	
	public String executeModel(Boolean sync) throws CoreException, EngineContextException {
		if (sync) {
			IDebugTarget[] debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
			if (debugTargets.length > 0) {
				//we are in the Debug mode, so debug the model under test
				sequentialEngineLauncher.launchModelDebugger();
				return "PASS: Debugging of the model under test launched successfully";
			}else {
				//we are in the Run mode, so run the model under test
				return sequentialEngineLauncher.executeModelSynchronous();
			}
		}
		return sequentialEngineLauncher.executeModelAsynchronous();
	}

	public String stopAsyncExecution() {
		return sequentialEngineLauncher.stopAsynchronousExecution();
	}
	
	public String executeOCLCommand (String query){
		//send the query without quotation marks
		return oclLauncher.runQuery(getMUTResource(), query.substring(1, query.length()-1));
	}
	
	public String executeDSLSpecificCommand(String eventType, String eventName, Map<String, Object> parameters) {		
		switch (eventType) {
		case "ACCEPTED":
			return this.eventManagerLauncher.processAcceptedEvent(eventName, parameters);
		case "EXPOSED":
			return this.eventManagerLauncher.assertExposedEvent(eventName, parameters);
		case "STOP":
			return this.eventManagerLauncher.sendStopEvent();
		default:
			break;
		}
		return "FAIL";
	}
	
	public Trace<Step<?>, TracedObject<?>, State<?, ?>> getExecutionTrace() {
		if (this.getActiveEngine() instanceof ISequentialExecutionEngine) {
			return sequentialEngineLauncher.getExecutionTrace();
		}
		else if (this.getActiveEngine() instanceof IEventBasedExecutionEngine) {
			return this.eventManagerLauncher.getExecutionTrace();
		}
		return null;
	}
	
	private Resource dslRes = null;
	
	private String getEngineType() {
		Dsl dsl = (Dsl)dslRes.getContents().get(0);
		if (dsl.getEntry("k3") != null) {
			return "k3";
		}else if (dsl.getEntry("ale") != null) {
			return "ale";
		}
		return null;
	}
	
	private boolean dslHasInterface() {
		Resource dslRes = (new ResourceSetImpl()).getResource(URI.createURI(DSLPath), true);
		Dsl dsl = (Dsl)dslRes.getContents().get(0);
		if (dsl.getEntry("behavioralInterface") != null) {
			return true;
		}
		return false;
	}
	
	public void setDSLPath (String DSLPath) {
		this.DSLPath = DSLPath;
		dslRes = (new ResourceSetImpl()).getResource(URI.createURI(this.DSLPath), true);
	}
	
	public void setMUTPath (String MUTPath) {
		this.MUTPath = MUTPath;
	}
	
	public void setMUTResource(Resource MUTResource) {
		sequentialEngineLauncher.setModelResource(MUTResource);
	}
	
	public Resource getMUTResource() {
		if (this.getActiveEngine() instanceof ISequentialExecutionEngine) {
			return sequentialEngineLauncher.getModelResource();
		}
		else if (this.getActiveEngine() instanceof IEventBasedExecutionEngine) {
			return this.eventManagerLauncher.getModelResource();
		}
		return (new ResourceSetImpl()).getResource(URI.createURI(MUTPath), true);
	}
	
	public IExecutionEngine getActiveEngine() {
		if (!dslHasInterface() && sequentialEngineLauncher != null) {
			return sequentialEngineLauncher;
		}
		else if (dslHasInterface() && this.eventManagerLauncher != null) {
			return this.eventManagerLauncher;
		}
		return null;
	}
	
	public ArrayList<EObject> getOCLResultAsObject() {
		return oclLauncher.getResultAsObject();
	}
	
	public ArrayList<String> getOCLResultAsString(){
		return oclLauncher.getResultAsString();
	}

	public void disposeResources() {
		if (this.getActiveEngine() instanceof ISequentialExecutionEngine) {
			sequentialEngineLauncher.disposeResources();
		}
		else if (this.getActiveEngine() instanceof IEventBasedExecutionEngine) {
			this.eventManagerLauncher.disposeResources();
		}
	}
}