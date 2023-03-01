package org.imt.tdl.eventBasedEngine;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLThreadAdapter;
import org.eclipse.gemoc.dsl.debug.ide.launch.AbstractDSLLaunchConfigurationDelegate;
import org.eclipse.gemoc.dsl.debug.impl.ThreadImpl;
import org.eclipse.gemoc.execution.eventBasedEngine.EventBasedExecutionEngine;
import org.eclipse.gemoc.execution.eventBasedEngine.EventBasedRunConfiguration;
import org.eclipse.gemoc.execution.sequential.javaengine.ui.Activator;
import org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher.GemocSourceLocator;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.EventParameter;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.event.manager.EventManagerUtils;
import org.eclipse.gemoc.executionframework.event.manager.GenericEventManager;
import org.eclipse.gemoc.executionframework.event.manager.IEventManagerListener;
import org.eclipse.gemoc.executionframework.event.model.event.EventFactory;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.Value;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Trace;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.gemoc.traceaddon.GenericTraceEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.imt.gemoc.engine.custom.launcher.CustomEventBasedLauncher;
import org.imt.tdl.observer.ModelExecutionObserver;
import org.imt.tdl.testResult.TDLTestResultUtil;
import org.imt.tdl.utilities.DSLProcessor;

public class K3EventManagerLauncher implements IEventBasedExecutionEngine{
	
	
	private String DSLPath;
	DSLProcessor dslProcessor;
	
	private String MUTPath;
	private Resource MUTResource = null;
	
	private ILaunchConfiguration launchConf;
	private EventBasedRunConfiguration runConf;
	private EventBasedExecutionEngine executionEngine = null;
	Job executionEngineJob;
	
	private CustomEventBasedLauncher launcher;
	private boolean isDebugMode = false;
	private GenericEventManager eventManager = null;
	private LinkedTransferQueue<EventOccurrence> eventOccurrences = new LinkedTransferQueue<EventOccurrence>();
	
	//observer is used for assertion generation component of test amplifier
	private List<ModelExecutionObserver> observers = new ArrayList<>();
	
	// progress monitor used during launch; useful for operations that wish to
	// contribute to the progress bar
	protected IProgressMonitor launchProgressMonitor = null;

	private final ILaunchConfigurationType launchType = DebugPlugin.getDefault().getLaunchManager()
			.getLaunchConfigurationType("org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher");
	
	@Override
	public void setUp(String MUTPath, String DSLPath){
		this.MUTPath = MUTPath.replace("\\", "/");
		this.DSLPath = "platform:/plugin/" + DSLPath.replace("\\", "/");;
		dslProcessor = new DSLProcessor(Paths.get(DSLPath));
		final String languageName = dslProcessor.getDSLName();
		final String implemRelId = dslProcessor.getImplemRelId();
		final String subtypeRelId = dslProcessor.getSubtypeRelId();
		final Set<String> implRelIds = new HashSet<>();
		implRelIds.add(implemRelId);
		final Set<String> subtypeRelIds =  new HashSet<>();
		subtypeRelIds.add(subtypeRelId);
		
		try {
			launchConf = getLaunchConfiguration(this.MUTPath, languageName, implRelIds, subtypeRelIds);
			runConf = new EventBasedRunConfiguration(launchConf);
		}catch (CoreException e) {
			e.printStackTrace();
		}
		launcher = new CustomEventBasedLauncher();
	}
	
	@Override
	public String processAcceptedEvent(String eventName, Map<String, Object> parameters) {
		EventOccurrence eventOccurrence = createEventOccurance(EventOccurrenceType.ACCEPTED, eventName, parameters);	
		if (eventOccurrence == null) {
			return "FAIL: There is an issue in the format of the event occurrence";
		}
		if (isDebugMode) {
			ThreadImpl testDebugger = (ThreadImpl) testCaseDebugThread.getTarget();
			if (testDebugger.getState().toString() == "STEPPING_OVER") {
				//Skip the breakpoint
				if (modelDebugThread == null) {
					getModelDebugger();
				}
				eventManager.processEventOccurrence(eventOccurrence);
				try {
					modelDebugThread.resume();
				} catch (DebugException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				eventManager.processEventOccurrence(eventOccurrence);
			}
			while (executionEngine.getRunningStatus() == RunStatus.Running) {
				try {
					testCaseDebugThread.suspend();
				} catch (DebugException e) {
					e.printStackTrace();
				}
			}
		}else if (executionEngine == null || eventManager == null){
			startEngine();
			try {
				eventManager.processEventOccurrence(eventOccurrence);
			}
			catch (NullPointerException e) {
				return "FAIL: There is an issue with the execution engine";
			}	
		}else {
			eventManager.processEventOccurrence(eventOccurrence);
		}
		return "PASS";
	}
	
	private void getModelDebugger() {
		IDebugTarget[] debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
		try {
			for (int i=debugTargets.length -1 ; i>=0; i--) {
				if ((DSLThreadAdapter)debugTargets[i].getThreads()[0] == testCaseDebugThread) {
					i--;
				}else {
					modelDebugThread = (DSLThreadAdapter)debugTargets[i].getThreads()[0];
				}
			}
		} catch (DebugException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public String assertExposedEvent(String eventName, Map<String, Object> parameters) {
		try {
			EventOccurrence receivedEventOccurrence = eventOccurrences.poll(5000, TimeUnit.MILLISECONDS);
			EventOccurrence expectedEventOccurrence = createEventOccurance(EventOccurrenceType.EXPOSED, eventName, parameters);
			if (expectedEventOccurrence == null) {
				return "FAIL: The expected event occurrence does not match to the interface or its parameters does not exist in the MUT";
			}
			if (receivedEventOccurrence == null) {
				return "FAIL: There is no received event occurrence from the MUT";
			}
			if (equalEventOccurrences(receivedEventOccurrence, expectedEventOccurrence)) {
				return "PASS";
			}
			return "FAIL: The expected event is not received from MUT \nThe received event is:\n" + 
				eventOccurenceToString(receivedEventOccurrence);

		} catch (InterruptedException e) {
			return e.getMessage();
		}
	}
	
	private ILaunchConfiguration getLaunchConfiguration(String MUTPath, String languageName, Set<String> implRelIds, Set<String> subtypeRelIds) throws CoreException {
		ILaunchConfigurationWorkingCopy configurationWorkingCopy = launchType.newInstance(null, "event_basedTesting");
		configurationWorkingCopy.setAttribute(AbstractDSLLaunchConfigurationDelegate.RESOURCE_URI, MUTPath);
		configurationWorkingCopy.setAttribute(EventBasedRunConfiguration.LAUNCH_SELECTED_LANGUAGE, languageName);
		configurationWorkingCopy.setAttribute(EventBasedRunConfiguration.WAIT_FOR_EVENT, true);
		configurationWorkingCopy.setAttribute(EventBasedRunConfiguration.IMPL_REL_IDS, implRelIds);
		configurationWorkingCopy.setAttribute(EventBasedRunConfiguration.SUBTYPE_REL_IDS, subtypeRelIds);
		configurationWorkingCopy.setAttribute(EventBasedRunConfiguration.LAUNCH_BREAK_START, true);
		configurationWorkingCopy.setAttribute(EventBasedRunConfiguration.DEBUG_MODEL_ID, Activator.DEBUG_MODEL_ID);
		
		//enabling trace addon
		configurationWorkingCopy.setAttribute("Generic MultiDimensional Data Trace", true);
		configurationWorkingCopy.setAttribute("org.eclipse.gemoc.trace.gemoc.addon_booleanOption", false);
		configurationWorkingCopy.setAttribute("org.eclipse.gemoc.trace.gemoc.addon_equivClassComputing_booleanOption", false);
		configurationWorkingCopy.setAttribute("org.eclipse.gemoc.trace.gemoc.addon_saveTraceOnEngineStop_booleanOption", false);
		configurationWorkingCopy.setAttribute("org.eclipse.gemoc.trace.gemoc.addon_saveTraceOnStep_booleanOption", false);
				
		return configurationWorkingCopy;
	}

	@Override
	public void startEngine() {
		try {
			executionEngine = (EventBasedExecutionEngine) launcher.createExecutionEngine(runConf, ExecutionMode.Run);
		}catch (CoreException | EngineContextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String PLUGIN_ID = "org.eclipse.gemoc.execution.sequential.javaengine.ui"; 		
		executionEngineJob = new Job("GEMOC Event-based Engine") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				executionEngine.startSynchronous();
				return new Status(IStatus.OK, PLUGIN_ID, "Execution started");
			}
		};
		final TransferQueue<Object> queue = new LinkedTransferQueue<>();
		this.executionEngine.getExecutionContext().getExecutionPlatform().addEngineAddon(new IEngineAddon() {
			@Override
			public void engineInitialized(IExecutionEngine<?> executionEngine) {
				queue.add(new Object());
			}
		});
		executionEngineJob.schedule();
		addEventManagerListener(queue);
	}
	
	private DSLThreadAdapter testCaseDebugThread;
	private DSLThreadAdapter modelDebugThread;
	
	@Override
	public void launchModelDebugger() {
		IDebugTarget[] debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
		try {
			//get the thread running the test case debugger to suspend it during model debugging
			testCaseDebugThread = (DSLThreadAdapter)debugTargets[0].getThreads()[0];
		} catch (DebugException e) {
			e.printStackTrace();
		}
		
		isDebugMode = true;
		Launch debugLaunch = new Launch(launchConf, ILaunchManager.DEBUG_MODE, new GemocSourceLocator());
		DebugPlugin.getDefault().getLaunchManager().addLaunch(debugLaunch);	
		try{
			launcher.launch(launchConf, ILaunchManager.DEBUG_MODE, debugLaunch, new NullProgressMonitor());
			executionEngine = (EventBasedExecutionEngine) launcher.getExecutionEngine();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final TransferQueue<Object> queue = new LinkedTransferQueue<>();
		executionEngine.getExecutionContext().getExecutionPlatform().addEngineAddon(new IEngineAddon() {
			@Override
			public void engineInitialized(IExecutionEngine<?> executionEngine) {
				queue.add(new Object());
			}
		});
		addEventManagerListener(queue);
	}
	
	private void addEventManagerListener(TransferQueue<Object> queue) {
		try {
			if (queue.poll(5000, TimeUnit.MILLISECONDS) != null) {
				eventManager = (GenericEventManager) executionEngine.getAddonsTypedBy(GenericEventManager.class).stream().findFirst().orElse(null);
				eventManager.addListener(new IEventManagerListener() {
					@Override
					public void eventReceived(EventOccurrence e) {
						eventOccurrences.add(e);
						notifyAllObservers(e);
					}
					@Override
					public Set<BehavioralInterface> getBehavioralInterfaces() {
						return eventManager.getBehavioralInterfaces();
					}
				});
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String sendStopEvent(int waitingTime) {
		String result = "PASS";
		int i=0;
		while (executionEngine.getRunningStatus() != RunStatus.WaitingForEvent && i < waitingTime) {
			try{
				Thread.sleep(500);
				i++;
			}
			catch(InterruptedException ex){
			    Thread.currentThread().interrupt();
			}
		}
		try {
			executionEngine.stop();
			if (eventOccurrences.size()>0) {
				StringBuilder sb = new StringBuilder();
				sb.append("FAIL:There are " + eventOccurrences.size() +" extra received events:\n");
				eventOccurrences.stream().forEach(e -> sb.append(eventOccurenceToString(e) + ", "));
				result = sb.toString().endsWith(", ") ? sb.substring(0, sb.length()-2) : sb.toString();
			}
			if (executionEngineJob.cancel()) {
				executionEngineJob.getThread().interrupt();
			}
		}
		catch (NullPointerException e) {
			//e.printStackTrace();
		}
		return result;
	}
	
	private EventOccurrence createEventOccurance(EventOccurrenceType eventType, String eventName, Map<String, Object> parameters) {
		BehavioralInterface bInterface = getBehavioralInterfaceRootElement(dslProcessor.getPath2BehavioralInterface());
		Event event = null;
		for (int i=0; i<bInterface.getEvents().size();i++) {
			if (bInterface.getEvents().get(i).getName().equals(eventName)) {
				event = bInterface.getEvents().get(i);
				break;
			}
		}
		if (event == null) {
			return null;
		}
		EventOccurrence eventOccurance = EventFactory.eINSTANCE.createEventOccurrence();
		eventOccurance.setEvent(event);
		eventOccurance.setType(eventType);
		Iterator<String> paramNames = parameters.keySet().iterator();
		for (int i = 0; i < parameters.size(); i++) {
			String paramName = paramNames.next();
			EventParameter parameter = null;
			for (int j = 0; j < event.getParams().size(); j++) {
				parameter = event.getParams().get(j);
				if (parameter.getName().equals(paramName)) {
					break;
				}
			}
			if (parameter == null) {
				return null;
			}
			EventOccurrenceArgument argument = EventFactory.eINSTANCE.createEventOccurrenceArgument();
			argument.setParameter(parameter);
			if (parameters.get(paramName) == null) {
				return null;
			}
			argument.setValue((Value) EventManagerUtils.convertObjectToValue(parameters.get(paramName)));
			eventOccurance.getArgs().add(argument);
		}
		return eventOccurance;
	}
	
	private Boolean equalEventOccurrences(EventOccurrence e1, EventOccurrence e2) {
		if (!e1.getEvent().getName().equals(e2.getEvent().getName())) {
			return false;
		}
		if (!e1.getType().equals(e2.getType())){
			return false;
		}
		if (e1.getArgs().size() != e2.getArgs().size()) {
			return false;
		}
		for (int i=0; i<e1.getArgs().size(); i++) {
			EventOccurrenceArgument e1Arg = e1.getArgs().get(i);
			EventOccurrenceArgument e2Arg = e2.getArgs().get(i);
			if (!e1Arg.getParameter().getName().equals(e2Arg.getParameter().getName())) {
				return false;
			}
			if (!e1Arg.getParameter().getType().equals(e2Arg.getParameter().getType())) {
				return false;
			}
			EObject value1;
			EObject value2;
			switch (e1Arg.getValue().eClass().getClassifierID()) {
			case ValuePackage.SINGLE_REFERENCE_VALUE:
				value1 = ((SingleReferenceValue) e1Arg.getValue()).getReferenceValue();
				value2 = ((SingleReferenceValue) e2Arg.getValue()).getReferenceValue();
				if (EcoreUtil.equals(value1, value2)) {
					return true;
				}
				String svalue1 = value1.toString();
				String svalue2 = value2.toString();
				if (svalue1.contains("(") && svalue2.contains("(")) {
					svalue1 = svalue1.replace(svalue1.substring(svalue1.indexOf("@"), svalue1.indexOf("(")), "_");
					svalue2 = svalue2.replace(svalue2.substring(svalue2.indexOf("@"), svalue2.indexOf("(")), "_");
				}else {
					svalue1 = svalue1.substring(0, svalue1.indexOf("@"));
					svalue2 = svalue2.substring(0, svalue2.indexOf("@"));
				}
				if (svalue1.equals(svalue2)) {
					return true;
				}
				break;
			case ValuePackage.SINGLE_OBJECT_VALUE:
				value1 = ((SingleObjectValue) e1Arg.getValue()).getObjectValue();
				value2 = ((SingleObjectValue) e2Arg.getValue()).getObjectValue();
				if (value1.toString().equals(value2.toString())) {
					return true;
				}
				break;
			case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE:
				if (((BooleanAttributeValue) e1Arg.getValue()).isAttributeValue()
						== ((BooleanAttributeValue) e2Arg.getValue()).isAttributeValue()){
					return true;
				}
				break;
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE:
				if (((BooleanObjectAttributeValue) e1Arg.getValue()).getAttributeValue()
						== ((BooleanObjectAttributeValue) e2Arg.getValue()).getAttributeValue()){
					return true;
				}
				break;
			case ValuePackage.INTEGER_ATTRIBUTE_VALUE:
				if (((IntegerAttributeValue) e1Arg.getValue()).getAttributeValue()
						== ((IntegerAttributeValue) e2Arg.getValue()).getAttributeValue()){
					return true;
				}
				break;
			case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE:
				if (((IntegerObjectAttributeValue) e1Arg.getValue()).getAttributeValue()
						== ((IntegerObjectAttributeValue) e2Arg.getValue()).getAttributeValue()){
					return true;
				}
				break;
			case ValuePackage.FLOAT_ATTRIBUTE_VALUE:
				if (((FloatAttributeValue) e1Arg.getValue()).getAttributeValue()
						== ((FloatAttributeValue) e2Arg.getValue()).getAttributeValue()){
					return true;
				}
				break;
			case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE:
				if (((FloatObjectAttributeValue) e1Arg.getValue()).getAttributeValue()
						== ((FloatObjectAttributeValue) e2Arg.getValue()).getAttributeValue()){
					return true;
				}
				break;
			case ValuePackage.STRING_ATTRIBUTE_VALUE:
				if (((StringAttributeValue) e1Arg.getValue()).getAttributeValue()
						.equals(((StringAttributeValue) e2Arg.getValue()).getAttributeValue())){
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	private String eventOccurenceToString (EventOccurrence occurrence) {
		String result = occurrence.getEvent().getName();
		if (occurrence.getArgs().size()>0) {
			result += " (";
		}
		for (int i=0; i<occurrence.getArgs().size(); i++) {
			EventOccurrenceArgument arg = occurrence.getArgs().get(i);
			EObject value;
			switch (arg.getValue().eClass().getClassifierID()) {
			case ValuePackage.SINGLE_REFERENCE_VALUE:
				value = ((SingleReferenceValue) arg.getValue()).getReferenceValue();
				String label = TDLTestResultUtil.getInstance().eObjectLabelProvider(value);
				result += label.substring(label.lastIndexOf(":")+1);
				break;
			case ValuePackage.SINGLE_OBJECT_VALUE:
				value = ((SingleObjectValue) arg.getValue()).getObjectValue();
				label = TDLTestResultUtil.getInstance().eObjectLabelProvider(value);
				result += label.substring(label.lastIndexOf(":")+1);
				break;
			case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE:
				result += ((BooleanAttributeValue) arg.getValue()).isAttributeValue();
				break;
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE:
				result += ((BooleanObjectAttributeValue) arg.getValue()).getAttributeValue().toString();
				break;
			case ValuePackage.INTEGER_ATTRIBUTE_VALUE:
				result += ((IntegerAttributeValue) arg.getValue()).getAttributeValue();
				break;
			case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE:
				result += ((IntegerObjectAttributeValue) arg.getValue()).getAttributeValue().toString();
				break;
			case ValuePackage.FLOAT_ATTRIBUTE_VALUE:
				result += ((FloatAttributeValue) arg.getValue()).getAttributeValue();
				break;
			case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE:
				result += ((FloatObjectAttributeValue) arg.getValue()).getAttributeValue().toString();
				break;
			case ValuePackage.STRING_ATTRIBUTE_VALUE:
				result += ((StringAttributeValue) arg.getValue()).getAttributeValue();
				break;
			}
			if (i < occurrence.getArgs().size()-1) {
				result += ", ";
			}
		}
		if (occurrence.getArgs().size()>0) {
			result += ")";
		}
		return result;
	}
	
	private BehavioralInterface getBehavioralInterfaceRootElement(String interfacePath) {
		interfacePath = interfacePath.replaceFirst("resource", "plugin");
		Resource interfaceRes = (new ResourceSetImpl()).getResource(URI.createURI(interfacePath), true);
		BehavioralInterface interfaceRootElement = (BehavioralInterface) interfaceRes.getContents().get(0);
		return interfaceRootElement;
	}
	
	public void setModelResource(Resource resource) {
		MUTResource = resource;
	}
	
	public Resource getModelResource() {
		if (executionEngine == null) {
			MUTResource = (new ResourceSetImpl()).getResource(URI.createURI(MUTPath), true);
		}
		else {
			MUTResource = executionEngine.getExecutionContext().getResourceModel();
		}
		return MUTResource;
	}

	@Override
	public Boolean isEngineStarted() {
		return (executionEngine != null && eventManager != null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Trace<Step<?>, TracedObject<?>, State<?, ?>> getExecutionTrace() {
		return (Trace<Step<?>, TracedObject<?>, State<?, ?>>) executionEngine.getAddon(GenericTraceEngineAddon.class).getTrace();
	}
	
	public void attach (ModelExecutionObserver observer) {
		observers.add(observer);
	}
	
	public void notifyAllObservers(EventOccurrence e){
      for (ModelExecutionObserver observer : observers) {
         observer.update(e);
      }
   }

	@Override
	public void disposeResources() {
		try {
			MUTResource.unload();
			executionEngine.dispose();
			executionEngineJob.cancel();
			executionEngineJob.getThread().interrupt();
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
	} 
}
