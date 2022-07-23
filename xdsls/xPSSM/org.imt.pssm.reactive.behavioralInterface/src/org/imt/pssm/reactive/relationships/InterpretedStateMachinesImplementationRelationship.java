package org.imt.pssm.reactive.relationships;


import static org.eclipse.gemoc.executionframework.event.manager.IImplementationRelationship.loadBehavioralInterface;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.event.manager.SimpleImplementationRelationship;

public class InterpretedStateMachinesImplementationRelationship extends SimpleImplementationRelationship {

	private static Map<String, String> computeEventToMethodMap() {
		final Map<String, String> result = new HashMap<>();
		result.put("run", "org.imt.pssm.reactive.interpreter.StateMachineAspect.run");
		result.put("signal_received", "org.imt.pssm.reactive.interpreter.StateMachineAspect.eventOccurrenceReceived");
		result.put("callOperation_received", "org.imt.pssm.reactive.interpreter.StateMachineAspect.eventOccurrenceReceived");
		result.put("behavior_executed", "org.imt.pssm.reactive.model.statemachines.Behavior.execute");
		return result;
	}
	
	private static Set<String> computeRunToCompletionMap() {
		final Set<String> result = new HashSet<>();
		result.add("org.imt.pssm.reactive.interpreter.StateMachineAspect.run");
		result.add("org.imt.pssm.reactive.interpreter.StateMachineAspect.eventOccurrenceReceived");
		return result;
	}

	public InterpretedStateMachinesImplementationRelationship() {
		this((BehavioralInterface) loadBehavioralInterface(
				"platform:/plugin/org.imt.pssm.reactive.behavioralInterface/ReactivePSSM.bi"));
	}

	public InterpretedStateMachinesImplementationRelationship(BehavioralInterface behavioralInterface) {
		//TODO: we manually set the name of the DSL as the last argument but it has to be the rule executor
		super(behavioralInterface, computeRunToCompletionMap(), computeEventToMethodMap(), "org.imt.pssm.reactive.ReactivePSSM");
	}
}
