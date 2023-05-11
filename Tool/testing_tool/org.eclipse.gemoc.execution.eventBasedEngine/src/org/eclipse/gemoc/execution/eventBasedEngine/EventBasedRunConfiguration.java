/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.eventBasedEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.gemoc.executionframework.engine.core.RunConfiguration;
import org.eclipse.gemoc.executionframework.event.manager.IImplementationRelationship;
import org.eclipse.gemoc.executionframework.event.manager.ISubtypingRelationship;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;

public class EventBasedRunConfiguration extends RunConfiguration implements IEventBasedRunConfiguration {

	private final String implemRelId = "org.eclipse.gemoc.executionframework.event.implementationrelationship";

	private final String subtypeRelId = "org.eclipse.gemoc.executionframework.event.subtypingrelationship";

	private List<EventOccurrence> _initialScenario = new ArrayList<>();

	private boolean _waitForEvent;

	private List<IImplementationRelationship> implementationRelationships;

	private List<ISubtypingRelationship> subtypingRelationships;

	public EventBasedRunConfiguration(ILaunchConfiguration launchConfiguration) throws CoreException {
		super(launchConfiguration);
	}

	@SuppressWarnings("unchecked")
	private <T> T getRelationshipInstance(String relationshipId, String extensionPointId, Class<T> asType) {
		IConfigurationElement[] relationships = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(extensionPointId);
		for (IConfigurationElement relationship : relationships) {
			if (relationship.getAttribute("id").equals(relationshipId)) {
				try {
					return (T) relationship.createExecutableExtension("class");
				} catch (IllegalArgumentException | SecurityException | CoreException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	protected void extractInformation() throws CoreException {
		super.extractInformation();

		final Set<String> implementationRelationshipIds = getAttribute(IMPL_REL_IDS, Collections.emptySet());
		final Set<String> subtypingRelationshipIds = getAttribute(SUBTYPE_REL_IDS, Collections.emptySet());

		implementationRelationships = implementationRelationshipIds.stream()
				.map(id -> getRelationshipInstance(id, implemRelId, IImplementationRelationship.class))
				.filter(r -> r != null).collect(Collectors.toList());

		subtypingRelationships = subtypingRelationshipIds.stream()
				.map(id -> getRelationshipInstance(id, subtypeRelId, ISubtypingRelationship.class))
				.filter(r -> r != null).collect(Collectors.toList());
		
		//EPLStateMachineImplementationRelationship stateMachineRelation = new EPLStateMachineImplementationRelationship();
		//implementationRelationships.add(stateMachineRelation);
		
		_waitForEvent = getAttribute(WAIT_FOR_EVENT, false);

//		final BehavioralInterface behavioralInterface = EventBasedDslHelper.getBehavioralInterface(languageName);
//		if (behavioralInterface != null) {
//			final String startEvent = getAttribute(START_EVENT, "");
//			final Map<String, String> paramToArg = getAttribute(START_EVENT_OCCURRENCE_ARGS, Collections.emptyMap());
//			behavioralInterface.getEvents().stream().filter(e -> e.getName().equals(startEvent)).findFirst()
//					.ifPresent(event -> {
//						final EventOccurrence startEventOccurrence = EventBasedDslHelper.createEventOccurrence(event);
//						final List<EventOccurrenceArgument> args = startEventOccurrence.getArgs();
//						event.getParams().forEach(p -> {
//							args.stream().filter(a -> p == a.getParameter()).findFirst().ifPresent(a -> {
//								Value value = a.getValue();
//								String arg = paramToArg.get(p.getName());
//								switch (value.eClass().getClassifierID()) {
//								case ValuePackage.SINGLE_REFERENCE_VALUE:
//									if (arg != null) {
//										if (arg.startsWith("/")) {
//											Resource model = getModel();
//											((SingleReferenceValue) value).setReferenceValue(model.getEObject(arg));
//										} else if (arg.startsWith("platform:/resource")) {
//											URI objectURI = URI.createURI(arg);
//											ResourceSet resourceSet = new ResourceSetImpl();
//											Resource resource = resourceSet.createResource(objectURI);
//											EObject argObject = null;
//											try {
//												resource.load(Collections.emptyMap());
//												argObject = resource.getContents().stream().findFirst().orElse(null);
//											} catch (IOException e) {
//												e.printStackTrace();
//												resource = null;
//											}
//											((SingleReferenceValue) value).setReferenceValue(argObject);
//										}
//									}
//									break;
//								case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE:
//								case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE:
//									((BooleanAttributeValue) value).setAttributeValue(Boolean.parseBoolean(arg));
//									break;
//								case ValuePackage.INTEGER_ATTRIBUTE_VALUE:
//								case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE:
//									((IntegerObjectAttributeValue) value).setAttributeValue(Integer.parseInt(arg));
//									break;
//								case ValuePackage.FLOAT_ATTRIBUTE_VALUE:
//								case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE:
//									((FloatObjectAttributeValue) value).setAttributeValue(Float.parseFloat(arg));
//									break;
//								case ValuePackage.STRING_ATTRIBUTE_VALUE:
//									((StringAttributeValue) value).setAttributeValue(arg);
//									break;
//								}
//							});
//						});
//						_initialScenario.add(startEventOccurrence);
//					});
//		}
	}

//	private Resource getModel() {
//		Resource resource = null;
//		URI modelURI = getExecutedModelURI();
//		if (!modelURI.isEmpty()) {
//			ResourceSet resourceSet = new ResourceSetImpl();
//			resource = resourceSet.createResource(modelURI);
//			try {
//				resource.load(Collections.emptyMap());
//			} catch (IOException e) {
//				e.printStackTrace();
//				resource = null;
//			}
//		}
//		return resource;
//	}

	@Override
	public List<EventOccurrence> getInitialScenario() {
		return _initialScenario;
	}

	@Override
	public boolean getWaitForEvent() {
		return _waitForEvent;
	}

	public List<IImplementationRelationship> getImplementationRelationships() {
		return implementationRelationships;
	}

	public List<ISubtypingRelationship> getSubtypingRelationships() {
		return subtypingRelationships;
	}

}
