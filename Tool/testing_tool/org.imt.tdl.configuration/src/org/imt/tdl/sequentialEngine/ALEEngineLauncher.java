package org.imt.tdl.sequentialEngine;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecoretools.ale.core.env.IAleEnvironment;
import org.eclipse.emf.ecoretools.ale.core.parser.ParsedFile;
import org.eclipse.emf.ecoretools.ale.implementation.ExtendedClass;
import org.eclipse.emf.ecoretools.ale.implementation.Method;
import org.eclipse.emf.ecoretools.ale.implementation.ModelUnit;
import org.eclipse.gemoc.ale.interpreted.engine.AleEngine;
import org.eclipse.gemoc.ale.interpreted.engine.Helper;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLThreadAdapter;
import org.eclipse.gemoc.dsl.debug.impl.ThreadImpl;
import org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher.GemocSourceLocator;
import org.eclipse.gemoc.executionframework.engine.commons.DslHelper;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Trace;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.gemoc.traceaddon.GenericTraceEngineAddon;
import org.imt.gemoc.engine.custom.launcher.CustomALELauncher;
import org.imt.tdl.observer.ModelExecutionObserver;

public class ALEEngineLauncher extends AbstractEngine{
	private AleEngine aleEngine = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public void launchModelDebugger() {
		IDebugTarget[] debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
		IThread[] testCaseDebuggerThreads = null;
		try {
			testCaseDebuggerThreads = debugTargets[0].getThreads();
		} catch (DebugException e) {
			e.printStackTrace();
		}
		//get the thread running the test case debugger to suspend it during model debugging
		DSLThreadAdapter testCaseDebugThread = (DSLThreadAdapter) testCaseDebuggerThreads[0];
		
		ThreadImpl testDebugger = (ThreadImpl) testCaseDebugThread.getTarget();
		if (testDebugger.getState().toString() == "STEPPING_INTO") {
			this.breakAtStart();
		}
		
		executioncontext.setResourceModel(MUTResource);
		CustomALELauncher launcher = new CustomALELauncher();
		launcher.executioncontext = executioncontext;
		Launch debugLaunch = new Launch(launchConfiguration, ILaunchManager.DEBUG_MODE, new GemocSourceLocator());
		DebugPlugin.getDefault().getLaunchManager().addLaunch(debugLaunch);	
		try {
			//launch the debugger for the model under test
			launcher.launch(launchConfiguration, ILaunchManager.DEBUG_MODE, debugLaunch, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		//suspend the test case debugger while the model debugger is running
		while (!debugLaunch.isTerminated()) {	
			synchronized (testCaseDebugThread) {
				if (!testCaseDebugThread.isSuspended()) {
					try {
						testCaseDebugThread.suspend();
					} catch (DebugException e) {
						e.printStackTrace();
					}
				}	
			}
		}
	}
	
	@Override
	public String executeModelSynchronous() {
		try{
			aleEngine = createExecutionEngine();
		}catch (EngineContextException e) {
			e.printStackTrace();
			return "FAIL: Cannot execute the model under test";
		} catch (CoreException e) {
				e.printStackTrace();
		}
		final Runnable modelRunner = new Thread() {
			@Override 
			public void run() { 
				aleEngine.startSynchronous();
			}
		};
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		@SuppressWarnings("rawtypes")
		final Future future = executor.submit(modelRunner);
		executor.shutdown(); // This does not cancel the already-scheduled task.
		try { 
		  future.get(10, TimeUnit.SECONDS); 
		}
		catch (InterruptedException ie) { 
			ie.printStackTrace();
		}
		catch (ExecutionException ee) { 
			ee.printStackTrace();
		}
		catch (TimeoutException te) { 
			//te.printStackTrace();
			System.out.println("TimeoutException -> There is an infinite loop in the model under test");
			future.cancel(true);
			aleEngine.stop();
			return "FAIL: TimeoutException -> There is an infinite loop in the model under test";
		}
		if (!executor.isTerminated()) {
		    executor.shutdownNow(); // If you want to stop the code that hasn't finished
		}
		this.setModelResource(this.aleEngine.getExecutionContext().getResourceModel());
		return "PASS: The model under test executed successfully";
	}
	
	@Override
	public String executeModelAsynchronous() {
		try{
			aleEngine = createExecutionEngine();
		}catch (EngineContextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAIL: Cannot execute the model under test";
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aleEngine.start();
		return "The engine is running";
	}
	
	@Override
	public String stopAsynchronousExecution() {
		aleEngine.stop();
		setModelResource(aleEngine.getExecutionContext().getResourceModel());
		return "PASS: The model under test executed successfully";
	}
	
	@SuppressWarnings("unchecked")
	private AleEngine createExecutionEngine() throws EngineContextException, CoreException{
		//if the resource is updated (e.g., the value of its dynamic features are set by the test case)
		//then the execution context should be updated
		executioncontext.setResourceModel(MUTResource);
		CustomALELauncher launcher = new CustomALELauncher();
		launcher.executioncontext = executioncontext;
		
		return (AleEngine) launcher.createExecutionEngine(runConfiguration, executionMode);
	}
	@Override
	protected String getModelEntryPointMethodName(){
		EClass target = null;
		final EClass finalTarget = target;
		org.eclipse.gemoc.dsl.Dsl language = DslHelper.load(_language);
		IAleEnvironment environment = Helper.gemocDslToAleDsl(language);
		
		List<ParsedFile<ModelUnit>> parsedSemantics = environment.getBehaviors().getParsedFiles();
		List<Method> mainOperations =
    		parsedSemantics
	    	.stream()
	    	.filter(sem -> sem.getRoot() != null)
	    	.map(sem -> sem.getRoot())
	    	.flatMap(unit -> unit.getClassExtensions().stream())
	    	.filter(xtdCls -> finalTarget == null || finalTarget == xtdCls.getBaseClass())
	    	.flatMap(xtdCls -> xtdCls.getMethods().stream())
    		.filter(op -> op.getTags().contains("main"))
    		.collect(Collectors.toList());
		
		Iterator<Method> it = mainOperations.iterator();
		return provideMethodLabel(it.next());
	}
	@Override
	protected String getModelInitializationMethodName() {
		if (_language != null && _entryPointMethod != null) {
			List<String> segments = Arrays.asList(_entryPointMethod.split("::"));
			if (segments.size() >= 2) {
				String tagetClassName = segments.get(segments.size() - 2);
				org.eclipse.gemoc.dsl.Dsl language = DslHelper.load(_language);

				try(IAleEnvironment environment = Helper.gemocDslToAleDsl(language)) {
					Optional<Method> initOperation = Optional.empty();
					try {
						List<ParsedFile<ModelUnit>> parsedSemantics =environment.getBehaviors().getParsedFiles();
						initOperation = parsedSemantics.stream().filter(sem -> sem.getRoot() != null)
								.map(sem -> sem.getRoot()).flatMap(unit -> unit.getClassExtensions().stream())
								.filter(xtdCls -> xtdCls.getBaseClass().getName().equals(tagetClassName))
								.flatMap(xtdCls -> xtdCls.getMethods().stream()).filter(op -> op.getTags().contains("init"))
								.findFirst();
					} catch (Exception e) {
					}
	
					if (initOperation.isPresent()) {
						return provideMethodLabel(initOperation.get());
					}
				}
			}
		}
		return "";
	}
	
	public String provideMethodLabel(Object element) {
		if(element instanceof Method) {
			Method mtd = (Method) element;
			ExtendedClass base = (ExtendedClass) mtd.eContainer();
			if(base.getBaseClass() != mtd.getOperationRef().getEContainingClass()) {
				return provideClassLabel(base.getBaseClass()) + "::" + mtd.getOperationRef().getName();
			}
			else {
				return provideClassLabel(mtd.getOperationRef());
			}
		}
		return provideClassLabel(element);
	}
	
	public String provideClassLabel(Object element) {
		if(element instanceof ENamedElement){
			ENamedElement namedElement = (ENamedElement) element;
			StringBuilder sb = new StringBuilder();
			if(namedElement.eContainer() != null){
				sb.append(provideClassLabel(namedElement.eContainer()));
				sb.append("::");
			}
			sb.append(namedElement.getName());
			return sb.toString();
		}
		else return element == null ? "" : element.toString();
	}

	@Override
	public Resource getModelResource() {
		return aleEngine != null ? aleEngine.getExecutionContext().getResourceModel() : MUTResource;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Trace<Step<?>, TracedObject<?>, State<?, ?>> getExecutionTrace() {
		return aleEngine != null ? (Trace<Step<?>, TracedObject<?>, State<?, ?>>) aleEngine
										.getAddon(GenericTraceEngineAddon.class).getTrace() : null;
	}
	
	@Override
	public void attach (ModelExecutionObserver observer) {
	
	}

	@Override
	public void disposeResources() {
		MUTResource.unload();
		aleEngine.dispose();
	}
}
