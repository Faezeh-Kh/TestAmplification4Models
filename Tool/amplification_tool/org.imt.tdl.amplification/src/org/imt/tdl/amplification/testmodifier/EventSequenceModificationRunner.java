package org.imt.tdl.amplification.testmodifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.EventType;
import org.etsi.mts.tdl.Block;
import org.etsi.mts.tdl.CompoundBehaviour;
import org.etsi.mts.tdl.DataInstance;
import org.etsi.mts.tdl.DataInstanceUse;
import org.etsi.mts.tdl.Member;
import org.etsi.mts.tdl.Message;
import org.etsi.mts.tdl.Parameter;
import org.etsi.mts.tdl.ParameterBinding;
import org.etsi.mts.tdl.StructuredDataInstance;
import org.etsi.mts.tdl.StructuredDataType;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.tdlFactory;
import org.imt.tdl.amplification.dsl.amplifier.EventCreation;
import org.imt.tdl.amplification.dsl.amplifier.EventDeletion;
import org.imt.tdl.amplification.dsl.amplifier.EventDuplication;
import org.imt.tdl.amplification.dsl.amplifier.EventModification;
import org.imt.tdl.amplification.dsl.amplifier.EventPermutation;
import org.imt.tdl.amplification.dsl.amplifier.EventSequenceModifier;
import org.imt.tdl.amplification.dsl.amplifier.TestModificationOperator;
import org.imt.tdl.utilities.PathHelper;

public class EventSequenceModificationRunner extends AbstractTestModificationRunner{
	
	PathHelper pathHelper;
	org.etsi.mts.tdl.Package tdlTestSuite;
	List<EventSequenceModifier> modifiers;
	
	//the tdl instances corresponding to the accepted events
	List<StructuredDataInstance> tdlEventInstances = new ArrayList<>();
	Set<StructuredDataType> tdlEventParameterTypes = new HashSet<>();
	
	//keep a list of tdlInstances that are corresponding to eobjects of the model under test
	Map<String, List<StructuredDataInstance>> etype_tdlEObjects = new HashMap<>();
		
	private static String EVENTDUPLICATION = "EventDuplication";
	private static String EVENTCREATION = "EventCreation";
	private static String EVENTDELETION = "EventDeletion";
	private static String EVENTPERMUTATION = "EventPermutation";
	private static String EVENTMODIFICATION = "EventModification";
	
	public EventSequenceModificationRunner() {
		applyAllModifiers = true;
	}
	
	public EventSequenceModificationRunner(List<EventSequenceModifier> modifiers) {
		this.modifiers = modifiers;
	}

	@Override
	public List<TestDescription> generateTests(TestDescription testCase) {
		tdlTestSuite = (org.etsi.mts.tdl.Package) testCase.eContainer();
		pathHelper = new PathHelper(tdlTestSuite);
		//initialization
		findTdlDataOfDSLInterface();
		findTdlDataOfMUT();
		modifyExchangedEvents();
		return null;
	}

	/* four operators can be applied:
	 * 1. addition : duplication of existing & creation of new events based on the interface
	 * 2. deletion
	 * 3. reordering
	 * 4. modification of event parameters values with other values for the same parameter
	 * 
	 * NOTE: We assume a TDL test case only contains a series of Message elements each sending an accepted event to the model under test
	 */
	private void modifyExchangedEvents() {
		if (modifiers == null) {
			//default configuration: apply all operators
			if (tdlEventInstances != null) {
				generateTestsByEventCreation(null);
			}
			generateTestsByEventDuplication(null);
			generateTestsByEventDeletion(null);
			generateTestsByEventPermutation(null);
			generateTestsByEventModification(null);
		}
		else {
			for (TestModificationOperator modifier:modifiers) {
				policy = modifier.getPolicy();
				if (modifier instanceof EventCreation && tdlEventInstances != null) {
					generateTestsByEventCreation((EventCreation) modifier);
				}
				else if (modifier instanceof EventDuplication) {
					generateTestsByEventDuplication((EventDuplication) modifier);
				}
				else if (modifier instanceof EventDeletion) {
					generateTestsByEventDeletion((EventDeletion) modifier);
				}
				else if (modifier instanceof EventPermutation) {
					generateTestsByEventPermutation((EventPermutation) modifier);
				}
				else if (modifier instanceof EventModification) {
					generateTestsByEventModification((EventModification) modifier);
				}
			}
		}
	}
	
	private void findTdlDataOfDSLInterface() {
		String dslFilePath = pathHelper.getDSLPath().toString().replace("\\", "/");
		Resource dslRes = (new ResourceSetImpl()).getResource(URI.createURI(dslFilePath), true);
		Dsl dsl = (Dsl)dslRes.getContents().get(0);
		if (dsl.getEntry("behavioralInterface") != null) {
			String interfacePath = dsl.getEntry("behavioralInterface").getValue().replaceFirst("resource", "plugin");
			Resource interfaceRes = (new ResourceSetImpl()).getResource(URI.createURI(interfacePath), true);
			BehavioralInterface interfaceRootElement = (BehavioralInterface) interfaceRes.getContents().get(0);
			List<Event> acceptedEvents = interfaceRootElement.getEvents().stream()
					.filter(e -> e.getType() == EventType.ACCEPTED).collect(Collectors.toList());
			for (Event event:acceptedEvents) {
				//finding the tdl instance corresponding to the accepted event 
				StructuredDataInstance tdlEventInstance = tdlTestSuite.getPackagedElement().stream().filter(p -> p instanceof StructuredDataInstance).
					map(p -> (StructuredDataInstance) p).filter(d -> getDSLCompatibleName(d.getName()).equals(event.getName())).findFirst().get();
				tdlEventInstances.add(tdlEventInstance);
				//finding the types of the event parameters
				for (Member tdlEventParameter:((StructuredDataType)tdlEventInstance.getDataType()).allMembers()) {
					tdlEventParameterTypes.add((StructuredDataType) tdlEventParameter.getDataType());
				}
			}		
		}
	}
	
	private void findTdlDataOfMUT() {
		tdlEventParameterTypes.forEach(t -> etype_tdlEObjects.put(t.getName(), new ArrayList<>()));
		EObject2TDLConverter object2tdlCoverter = new EObject2TDLConverter(tdlTestSuite);
		Resource MUTResource = pathHelper.getMUTResource();
		TreeIterator<EObject> modelContents = MUTResource.getAllContents();
		while (modelContents.hasNext()) {
			EObject modelObject = modelContents.next();
			String objectType = modelObject.eClass().getName();
			if (tdlEventParameterTypes.stream().anyMatch(t -> t.getName().equals(objectType))) {
				StructuredDataInstance tdlInstance4object = (StructuredDataInstance) object2tdlCoverter.convertEObject2TDLData(modelObject);
				etype_tdlEObjects.get(objectType).add(tdlInstance4object);
			}
		}
		//for dynamic eTypes, create new eobjects
		//TODO: using MOMoT
	}
	
	/* generating new test cases by addition-creation of new events
	 * for the accepted events of the behavioral interface that are not used in the test cases, create new events
	 * - if the EObjects used as event parameters are existed in the model under test
	 * all the possible new events are created and new tests are generated by adding one new event to them
	 * create new tests by adding new events one by one, and adding them all to one test case
	 */
	private void generateTestsByEventCreation(EventSequenceModifier modifier) {
		//TODO: apply based on policy
		Set<StructuredDataInstance> eventsUsedInTests = findEventsUsedInTestCase();
		List<StructuredDataInstance> eventsNotUsedInTests = tdlEventInstances.stream().
				filter(e -> !eventsUsedInTests.contains(e)).collect(Collectors.toList());
		List<Message> newMessagesForNotUsedEvents = new ArrayList<>();
		int n = 0;
		for (StructuredDataInstance tdlEventInstance: eventsNotUsedInTests) {
			//check whether for each event parameter, at least one eobject exists in the model under test
			//this is required for setting the value of parameters
			List<Member> tdlEventParameters = ((StructuredDataType) tdlEventInstance.getDataType()).allMembers();
			if (tdlEventParameters.stream().allMatch(p -> !etype_tdlEObjects.get(p.getDataType().getName()).isEmpty())) {
				List<Message> newMessages = generateNewMessages4NotUsedEvent(tdlEventInstance);
				//for each new message, create a new test case
				for (Message newMessage: newMessages) {
					TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, numOfNewTests++, EVENTCREATION);
					Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
					copyContainer.getBehaviour().add(newMessage);
					generatedTestsByModification.add(copyTdlTestCase);
				}
				if (newMessages.size()>1) {
					//create a new test case containing all new messages
					List<Message> allNewMessages = new ArrayList<>();
					Collections.copy(allNewMessages, newMessages);
					TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, numOfNewTests++, EVENTCREATION);
					Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
					copyContainer.getBehaviour().addAll(allNewMessages);
					generatedTestsByModification.add(copyTdlTestCase);
					//for this new test case having several new messages, amplify it by other operators: duplication, permutation, deletion
					TestDescription inputTestCase = this.tdlTestCase;
					tdlTestCase = copyTdlTestCase;
					generateTestsByEventDuplication(modifier);
					generateTestsByEventPermutation(modifier);
					if (newMessages.size() > 2) {
						generateTestsByEventDeletion(modifier);
					}
					tdlTestCase = inputTestCase;
				}
				newMessages.forEach(m -> newMessagesForNotUsedEvents.add(copyTdlMessage(m)));;
				n++;
			}
		}
		if (n>1) {
			//create a new test case containing all new messages for all not used event instances
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, numOfNewTests++, EVENTCREATION);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			copyContainer.getBehaviour().addAll(newMessagesForNotUsedEvents);
			generatedTestsByModification.add(copyTdlTestCase);
			//for this new test case having several new messages, amplify it by other operators: duplication, permutation, deletion
			TestDescription inputTestCase = this.tdlTestCase;
			tdlTestCase = copyTdlTestCase;
			generateTestsByEventDuplication(modifier);
			generateTestsByEventPermutation(modifier);
			if (n > 2) {
				generateTestsByEventDeletion(modifier);
			}
			tdlTestCase = inputTestCase;
		}
	}
	
	//creating new messages for the not used event by using all possible values for all event parameters
	private List<Message> generateNewMessages4NotUsedEvent(StructuredDataInstance tdlEventInstance) {
		List<Message> newMessages = new ArrayList<>();
		newMessages.add(createFirstMessage4Event(tdlEventInstance));
		int paramIndex = 0;
		int paramSize = ((StructuredDataType) tdlEventInstance.getDataType()).allMembers().size();
		while (paramIndex < paramSize) {
			List<Message> newMessagesByModification = new ArrayList<>();
			Parameter consideredParameter = ((StructuredDataType) tdlEventInstance.getDataType()).allMembers().get(paramIndex);
			for (Message newMessage:newMessages) {
				newMessagesByModification.addAll(createNewMessagesByModification(newMessage, consideredParameter));
			}
			newMessages.addAll(newMessagesByModification);
			paramIndex++;
		}
		return newMessages;
	}
	
	//creating new Messages by modifying the value of a specific parameter
	private List<Message> createNewMessagesByModification(Message currentMessage, Parameter consideredParameter) {
		List<Message> newMessages = new ArrayList<>();
		DataInstanceUse exchangedTdlEventInstance = (DataInstanceUse) currentMessage.getArgument();
		//find parameterBinding of the currentMessage related to the considered parameter
		Optional<ParameterBinding> pbOptional = exchangedTdlEventInstance.getArgument().stream().filter(pb -> pb.getParameter() == consideredParameter).findFirst();
		if (pbOptional.isEmpty()) {
			return null;
		}
		ParameterBinding relatedBinding = pbOptional.get();
		String paramType = consideredParameter.getDataType().getName().toString();
		int paramValueIndex = 1;
		int paramValuesSize = etype_tdlEObjects.get(paramType).size();
		while(paramValueIndex < paramValuesSize) {
			Message newMessage = copyTdlMessage(currentMessage);
			relatedBinding = ((DataInstanceUse) newMessage.getArgument()).getArgument().stream().
					filter(pb -> pb.getParameter() == consideredParameter).findFirst().get();
			((DataInstanceUse)relatedBinding.getDataUse()).setDataInstance(etype_tdlEObjects.get(paramType).get(paramValueIndex));
			newMessages.add(newMessage);
			paramValueIndex++;
		}
		return newMessages;
	}
	
	//the first message uses the first value for each parameter
	private Message createFirstMessage4Event(StructuredDataInstance tdlEventInstance) {
		Block messagesContainer = ((CompoundBehaviour) tdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		Message existingMessage = messagesContainer.getBehaviour().stream().map(b -> (Message) b).findFirst().get();
		//create a new message for sending an instance of the event 
		Message newMessage = tdlFactory.eINSTANCE.createMessage();
		newMessage.setSourceGate(existingMessage.getSourceGate());
		newMessage.getTarget().addAll(EcoreUtil.copyAll(existingMessage.getTarget()));
		DataInstanceUse exchangedTdlEventInstance = tdlFactory.eINSTANCE.createDataInstanceUse();
		exchangedTdlEventInstance.setDataInstance(tdlEventInstance);
		for (Parameter parameter:((StructuredDataType) tdlEventInstance.getDataType()).allMembers()) {
			String paramType = parameter.getDataType().getName().toString();
			ParameterBinding tdlParameterValue = tdlFactory.eINSTANCE.createParameterBinding();
			tdlParameterValue.setParameter(parameter);
			DataInstanceUse paramValue = tdlFactory.eINSTANCE.createDataInstanceUse();
			paramValue.setDataInstance(etype_tdlEObjects.get(paramType).get(0));
			tdlParameterValue.setDataUse(paramValue);
			exchangedTdlEventInstance.getArgument().add(tdlParameterValue);
		}
		newMessage.setArgument(exchangedTdlEventInstance);
		return newMessage;
	}
	
	private Set<StructuredDataInstance> findEventsUsedInTestCase() {
		Set<StructuredDataInstance> eventsUsedInTests = new HashSet<StructuredDataInstance>();
		Block messagesContainer = ((CompoundBehaviour) tdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> tdlMessages = messagesContainer.getBehaviour().stream()
				.map(b -> (Message) b).collect(Collectors.toList());
		for (Message message:tdlMessages.stream()
				.filter(m -> isMessageSendingAnEvent(m)).collect(Collectors.toList())) {
			DataInstance tdlEvent = ((DataInstanceUse) message.getArgument()).getDataInstance();
			eventsUsedInTests.add((StructuredDataInstance) tdlEvent);
		}
		return eventsUsedInTests;
	}

	private boolean isMessageSendingAnEvent(Message message) {
		 return tdlEventInstances.contains(((DataInstanceUse) message.getArgument()).getDataInstance());
	}
	
	//generating new test cases by addition-duplication of existing events
	private void generateTestsByEventDuplication(EventSequenceModifier modifier) {
		//TODO: apply based on policy
		Block messagesContainer = ((CompoundBehaviour) tdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> tdlMessages = messagesContainer.getBehaviour().stream()
				.map(b -> (Message) b).collect(Collectors.toList());
		//only for messages sending an event, perform modification by duplication
		for (Message tdlMessage: tdlMessages.stream().filter(m -> isMessageSendingAnEvent(m)).collect(Collectors.toList())) {
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, numOfNewTests++, EVENTDUPLICATION);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			copyContainer.getBehaviour().add(copyTdlMessage(tdlMessage));
			generatedTestsByModification.add(copyTdlTestCase);
		}
	}
		
	//generating new tests by deleting the existing events
	private void generateTestsByEventDeletion(EventSequenceModifier modifier) {
		//TODO: apply based on policy
		Block messagesContainer = ((CompoundBehaviour) tdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> tdlMessages = messagesContainer.getBehaviour().stream()
				.map(b -> (Message) b).collect(Collectors.toList());
		for (Message tdlMessage: tdlMessages.stream()
				.filter(m -> isMessageSendingAnEvent(m)).collect(Collectors.toList())) {
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, numOfNewTests++, EVENTDELETION);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			copyContainer.getBehaviour().remove(tdlMessage);
			generatedTestsByModification.add(copyTdlTestCase);
		}
	}

	//generating new tests by permuting the existing events
	private void generateTestsByEventPermutation(EventSequenceModifier modifier) {
		//TODO: apply based on policy
		TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, numOfNewTests++, EVENTPERMUTATION);
		Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> copyMessages = copyContainer.getBehaviour().stream().map(b -> (Message) b).
				filter(m -> isMessageSendingAnEvent(m)).collect(Collectors.toList());
		copyContainer.getBehaviour().clear();
		copyContainer.getBehaviour().addAll(copyMessages);
		generatedTestsByModification.add(copyTdlTestCase);
	}
	
	/*
	 * generating new tests by modifying the event parameter values of existing events 
	 * indeed changing their values with other values of the same type
	 * NOTE: Event parameter values are indeed model objects (according to behavioral interface metalanguage)
	 * we initially discovered the types of the event parameters (tdlEventParameterTypes) 
	 * and find/create other/new eobjects for each of them and keeping them in etype_tdlEObjects
	 */
	private void generateTestsByEventModification(EventSequenceModifier modifier) {
		//TODO: apply based on policy
		List<DataInstanceUse> eventParameterValues = findValuesOfEventParamsInTestCase();
		for (DataInstanceUse tdlEobjectRef:eventParameterValues) {
			String eobjectType = tdlEobjectRef.getDataInstance().getDataType().getName();
			DataInstance initialEObject = tdlFactory.eINSTANCE.createStructuredDataInstance();
			initialEObject = EcoreUtil.copy(tdlEobjectRef.getDataInstance());
			//if there are some values for this type, generate new test by changing the value to other values of the same type
			if (!etype_tdlEObjects.get(eobjectType).isEmpty()) {
				for (StructuredDataInstance tdlEobjectInstance:etype_tdlEObjects.get(eobjectType)) {
					if (!EcoreUtil.equals(initialEObject, tdlEobjectInstance)) {
						tdlEobjectRef.setDataInstance(tdlEobjectInstance);
						generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, numOfNewTests++, EVENTMODIFICATION));
					}
				}
			}
		}
	}
	
	//finding values of event parameters		
	private List<DataInstanceUse> findValuesOfEventParamsInTestCase() {
		Block messagesContainer = ((CompoundBehaviour) tdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> tdlMessages = messagesContainer.getBehaviour().stream()
				.map(b -> (Message) b).filter(m -> isMessageSendingAnEvent(m)).collect(Collectors.toList());
		List<DataInstanceUse> exchangedEvents = tdlMessages.stream()
				.map(m -> (DataInstanceUse) m.getArgument()).collect(Collectors.toList());
		List<DataInstanceUse> eventParamsValues = new ArrayList<>();
		for (DataInstanceUse eventInstance: exchangedEvents) {
			if (eventInstance.getArgument().size()>0) {
				for (ParameterBinding eventParamValue: eventInstance.getArgument()) {
					eventParamsValues.add((DataInstanceUse) eventParamValue.getDataUse());
				}
			}
		}
		return eventParamsValues;
	}
		
//	//finding parameterBindings that their value are eobject instances
//	private List<DataInstanceUse> findEObjectRefsInTestCase(TestDescription copyTdlTestCase) {
//		Block messagesContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
//		List<Message> tdlMessages = messagesContainer.getBehaviour().stream().map(b -> (Message) b).toList();
//		List<DataInstanceUse> exchangedEvents = tdlMessages.stream().map(m -> (DataInstanceUse) m.getArgument()).toList();
//		List<DataInstanceUse> eobjectRefrences = new ArrayList<>();
//		for (DataInstanceUse eventInstance: exchangedEvents) {
//			Iterator<EObject> iterator = eventInstance.eAllContents();
//			while (iterator.hasNext()) {
//				EObject object = iterator.next();
//				if (object instanceof ParameterBinding && ((ParameterBinding) object).getDataUse() instanceof DataInstanceUse) {
//					DataInstanceUse reference = (DataInstanceUse)((ParameterBinding) object).getDataUse();
//					eobjectRefrences.addAll(getDirectEObjectReferences(reference));
//				}
//			}
//		}
//		return eobjectRefrences;
//	}
//	
//	private List<DataInstanceUse> getDirectEObjectReferences(DataInstanceUse reference){
//		List<DataInstanceUse> eobjectRefrences = new ArrayList<>();
//		if (reference.getItem() != null && reference.getItem().size()>0) {
//			for (StaticDataUse item: reference.getItem()) {
//				if (item instanceof DataInstanceUse) {
//					eobjectRefrences.addAll(getDirectEObjectReferences((DataInstanceUse)item));
//				}	
//			}
//		}
//		else if (reference.getDataInstance() != null ){
//			eobjectRefrences.add(reference);
//		}
//		return eobjectRefrences;
//	}
	
	private String getDSLCompatibleName (String name) {
		if (name.startsWith("_")) {
			return name.substring(1);
		}
		return name;
	}
}
