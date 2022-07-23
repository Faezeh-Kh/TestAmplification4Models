package org.imt.arduino.reactive.relationships;

import static org.eclipse.gemoc.executionframework.event.manager.IImplementationRelationship.loadBehavioralInterface;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;
import org.eclipse.gemoc.executionframework.event.manager.SimpleImplementationRelationship;

public class ArduinoImplementationRelationship extends SimpleImplementationRelationship {
	
	private static Map<String, String> computeEventToMethodMap() {
		final Map<String, String> result = new HashMap<>();
		//Accepted rules
		result.put("run", "org.imt.arduino.reactive.interpreter.SketchAspect.execute");
		result.put("button_pressed", "org.imt.arduino.reactive.interpreter.PushButtonAspect.press");
		result.put("button_released", "org.imt.arduino.reactive.interpreter.PushButtonAspect.release");
		result.put("IRSensor_detected", "org.imt.arduino.reactive.interpreter.InfraRedSensorAspect.detect");
		result.put("IRSensor_notDetected", "org.imt.arduino.reactive.interpreter.InfraRedSensorAspect.notDetect");
		result.put("soundSensor_detected", "org.imt.arduino.reactive.interpreter.SoundSensorAspect.detect");
		//Exposed rules
		result.put("Pin_level_changed", "org.imt.arduino.reactive.arduino.DigitalPin.changeLevel");
		return result;
	}
	private static Set<String> computeRunToCompletionMap(List<Event> events) {
		final Set<String> result = new HashSet<>();
		result.add("org.imt.arduino.reactive.interpreter.SketchAspect.execute");
		result.add("org.imt.arduino.reactive.interpreter.PushButtonAspect.press");
		result.add("org.imt.arduino.reactive.interpreter.PushButtonAspect.release");
		result.add("org.imt.arduino.reactive.interpreter.InfraRedSensorAspect.detect");
		result.add("org.imt.arduino.reactive.interpreter.InfraRedSensorAspect.notDetect");
		result.add("org.imt.arduino.reactive.interpreter.SoundSensorAspect.detect");
		return result;
	}
	public ArduinoImplementationRelationship() {
		this(loadBehavioralInterface("platform:/plugin/org.imt.arduino.reactive.behavioralInterface/Arduino.bi"));
	}

	public ArduinoImplementationRelationship(BehavioralInterface behavioralInterface) {
		//TODO: we manually set the name of the DSL as the last argument but it has to be the rule executor
		super(behavioralInterface, computeRunToCompletionMap(behavioralInterface.getEvents()), computeEventToMethodMap(), "org.imt.arduino.reactiveArduino");
	}
}
