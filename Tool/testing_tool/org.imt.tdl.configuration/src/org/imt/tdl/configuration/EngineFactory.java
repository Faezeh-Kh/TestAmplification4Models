package org.imt.tdl.configuration;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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
import org.imt.tdl.utilities.DSLProcessor;

public class EngineFactory{
	
	private Path MUTPath;
	private Path DSLPath;
	private DSLProcessor dslProcessor;
	
	public boolean launcherIsTuned = false;
	private ISequentialExecutionEngine sequentialEngineLauncher;
	private OCLInterpreter oclLauncher;
	private IEventBasedExecutionEngine eventManagerLauncher;
	
	public final static String GENERIC = "Generic";
	public final static String DSL_SPECIFIC = "reactiveGate";
	public final static String OCL = "OCL";

	public void setUp(String commandType) throws CoreException, EngineContextException {
		if (commandType.equals(GENERIC)) {
			String engineType = getEngineType();
			sequentialEngineLauncher = engineType=="k3" ? new JavaEngineLauncher() : new ALEEngineLauncher();
			sequentialEngineLauncher.setUp(MUTPath.toString(), DSLPath.toString());
		}else if(commandType.equals(DSL_SPECIFIC)) {
			eventManagerLauncher = new K3EventManagerLauncher();
			eventManagerLauncher.setUp(MUTPath.toString(), DSLPath.toString());
			if (!eventManagerLauncher.isEngineStarted()) {
				IDebugTarget[] debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
				if (debugTargets.length > 0) {
					//we are in the Debug mode, so debug the model under test
					eventManagerLauncher.launchModelDebugger();
				}else {
					eventManagerLauncher.startEngine();
				}
			}
		}else if (commandType.equals(OCL)) {
			if (oclLauncher == null) {
				oclLauncher = new OCLInterpreter();
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
	
	public void terminateExecution() {
		if (getActiveEngine() instanceof ISequentialExecutionEngine) {
			sequentialEngineLauncher.stopAsynchronousExecution();
		}
		else if (getActiveEngine() instanceof IEventBasedExecutionEngine) {
			eventManagerLauncher.sendStopEvent(0);//stop engine immediately
		}
	}
	public String executeOCLCommand (EObject context, String query){
		//send the query without quotation marks
		return oclLauncher.runQuery(context, query);
	}
	
	public String executeDSLSpecificCommand(String eventType, String eventName, Map<String, Object> parameters) {		
		switch (eventType) {
		case "ACCEPTED":
			return eventManagerLauncher.processAcceptedEvent(eventName, parameters);
		case "EXPOSED":
			return eventManagerLauncher.assertExposedEvent(eventName, parameters);
		case "STOP":
			return eventManagerLauncher.sendStopEvent(10);//stop engine after a while
		default:
			break;
		}
		return "FAIL";
	}
	
	public Trace<Step<?>, TracedObject<?>, State<?, ?>> getExecutionTrace() {
		if (getActiveEngine() instanceof ISequentialExecutionEngine) {
			return sequentialEngineLauncher.getExecutionTrace();
		}
		else if (getActiveEngine() instanceof IEventBasedExecutionEngine) {
			return eventManagerLauncher.getExecutionTrace();
		}
		return null;
	}
	
	private String getEngineType() {
		return dslProcessor.getDSLSemanticsLanguage();
	}
	
	public void setDSLPath (Path DSLPath) {
		this.DSLPath = DSLPath;
		dslProcessor = new DSLProcessor(DSLPath);
	}
	
	public void setMUTPath (Path MUTPath) {
		this.MUTPath = MUTPath;
	}
	
	public void setMUTResource(Resource MUTResource) {
		sequentialEngineLauncher.setModelResource(MUTResource);
	}
	
	public Resource getMUTResource() {
		IExecutionEngine activeEngine = getActiveEngine();
		if (activeEngine instanceof ISequentialExecutionEngine) {
			return sequentialEngineLauncher.getModelResource();
		}
		else if (activeEngine instanceof IEventBasedExecutionEngine) {
			return eventManagerLauncher.getModelResource();
		}
		return (new ResourceSetImpl()).getResource(URI.createURI(MUTPath.toString()), true);
	}
	
	public IExecutionEngine getActiveEngine() {
		if (dslProcessor.dslHasBehavioralInterface() && eventManagerLauncher != null) {
			return eventManagerLauncher;
		}
		else if (sequentialEngineLauncher != null) {
			return sequentialEngineLauncher;
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
		if (getActiveEngine() instanceof ISequentialExecutionEngine) {
			sequentialEngineLauncher.disposeResources();
		}
		else if (getActiveEngine() instanceof IEventBasedExecutionEngine) {
			eventManagerLauncher.disposeResources();
		}
	}
}