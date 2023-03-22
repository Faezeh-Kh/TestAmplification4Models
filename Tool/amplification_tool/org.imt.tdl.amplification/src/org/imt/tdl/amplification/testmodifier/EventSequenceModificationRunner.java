package org.imt.tdl.amplification.testmodifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.EventType;
import org.etsi.mts.tdl.Behaviour;
import org.etsi.mts.tdl.Block;
import org.etsi.mts.tdl.CompoundBehaviour;
import org.etsi.mts.tdl.DataInstance;
import org.etsi.mts.tdl.DataInstanceUse;
import org.etsi.mts.tdl.Member;
import org.etsi.mts.tdl.Message;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.Parameter;
import org.etsi.mts.tdl.ParameterBinding;
import org.etsi.mts.tdl.StructuredDataInstance;
import org.etsi.mts.tdl.StructuredDataType;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.tdlFactory;
import org.imt.tdl.amplification.dsl.amplifier.ApplicationPolicy;
import org.imt.tdl.amplification.dsl.amplifier.EventCreation;
import org.imt.tdl.amplification.dsl.amplifier.EventDeletion;
import org.imt.tdl.amplification.dsl.amplifier.EventDuplication;
import org.imt.tdl.amplification.dsl.amplifier.EventModification;
import org.imt.tdl.amplification.dsl.amplifier.EventPermutation;
import org.imt.tdl.amplification.dsl.amplifier.EventSequenceModifier;
import org.imt.tdl.amplification.dsl.amplifier.TestModificationOperator;
import org.imt.tdl.utilities.DSLProcessor;
import org.imt.tdl.utilities.PathHelper;

public class EventSequenceModificationRunner extends AbstractTestModificationRunner{
	
	PathHelper pathHelper;
	Package tdlTestSuite;
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
	
	public EventSequenceModificationRunner(Package tdlTestSuite) {
		setTdlTestSuite(tdlTestSuite);
		applyAllModifiers = true;
	}
	
	public EventSequenceModificationRunner(Package tdlTestSuite, List<EventSequenceModifier> modifiers) {
		setTdlTestSuite(tdlTestSuite);
		this.modifiers = modifiers;
	}
	
	private void setTdlTestSuite(Package tdlTestSuite) {
		this.tdlTestSuite = tdlTestSuite;
		pathHelper = new PathHelper(tdlTestSuite);
		pathHelper.findModelAndDSLPathOfTestSuite();
	}

	@Override
	public List<TestDescription> generateTests(TestDescription testCase) {
		//initialization
		this.tdlTestCase = testCase;
		findTdlDataOfDSLInterface();
		
		generateTestsByEventModification();
		
		return generatedTestsByModification;
	}

	/* four operators can be applied:
	 * 1. duplication of existing 
	 * 2. deletion
	 * 3. reordering
	 * 4. modification of event parameters values with other values for the same parameter
	 * 5. creation of new events based on the interface
	 * NOTE: We assume a TDL test case only contains a series of Message elements each sending an accepted event to the model under test
	 */
	private void generateTestsByEventModification() {
		if (modifiers == null) {
			//default configuration: apply all operators
			generateTestsByEventDuplication();
			generateTestsByEventDeletion();
			generateTestsByEventPermutation();
			generateTestsByEventParameterModification();
			generateTestsByEventCreation();
		}
		else {
			for (TestModificationOperator modifier:modifiers) {
				policy = modifier.getPolicy();
				maxOccurrence = modifier.getMaxOccurrence() > 0 ? modifier.getMaxOccurrence() : 1;
				if (modifier instanceof EventDuplication) {
					generateTestsByEventDuplication();
				}
				else if (modifier instanceof EventDeletion) {
					generateTestsByEventDeletion();
				}
				else if (modifier instanceof EventPermutation) {
					generateTestsByEventPermutation();
				}
				else if (modifier instanceof EventModification) {
					generateTestsByEventParameterModification();
				}
				else if (modifier instanceof EventCreation) {
					generateTestsByEventCreation();
				}
				else if (modifier instanceof EventSequenceModifier) {
					generateTestsByEventDuplication();
					generateTestsByEventDeletion();
					generateTestsByEventPermutation();
					generateTestsByEventParameterModification();
					generateTestsByEventCreation();
				}
			}
		}
	}
	
	private void generateTestsByEventDuplication() {
		modifyExchangedEvents(new DuplicateEvent(), getAllOriginalMessages());
	}

	private void generateTestsByEventDeletion() {
		modifyExchangedEvents(new DeleteEvent(), getAllOriginalMessages());
	}

	private void generateTestsByEventPermutation() {
		modifyExchangedEvents(new PermuteEvent(), getAllOriginalMessages());
	}

	private void generateTestsByEventParameterModification() {
		modifyExchangedEvents(new ModifyEventParameter(), getAllOriginalMessages());
	}

	private void generateTestsByEventCreation() {
		if (tdlEventInstances != null) {
			List<Message> newMessages = generateMessages4MissedEvents();
			if (newMessages.size()>0) {
				modifyExchangedEvents(new CreateEvent(), newMessages);
			}
		}
	}
	
	private void modifyExchangedEvents(ITestDataModifier modifier, List<Message> tdlMessages) {
		if (policy == ApplicationPolicy.ALL) {//modify all events to generate a test case
			modifier.modifyData(tdlMessages);
		}
		else if (policy == ApplicationPolicy.ONE) {//modify a random event to generate a test
			Random random = new Random();
			int randomIndex = random.nextInt(tdlMessages.size());
			modifier.modifyData(tdlMessages.subList(randomIndex, randomIndex+1));
		}
		else if (policy == ApplicationPolicy.EACH) {//generate a test case by modifying each value
			for (int i=0; i< tdlMessages.size(); i++) {
				modifier.modifyData(tdlMessages.subList(i, i+1));
			}
		}
		else if (policy == ApplicationPolicy.FIRST || policy == ApplicationPolicy.FIXED) {//modify the first events (up to maxOccurrence) to generate a test
			modifier.modifyData(tdlMessages.subList(0, maxOccurrence));
		}
		else if (policy == ApplicationPolicy.LAST) {//modify the last events (up to maxOccurrence) to generate a test
			modifier.modifyData(tdlMessages.subList(tdlMessages.size()-maxOccurrence, tdlMessages.size()));
		}
	}
	
	private List<Message> getAllOriginalMessages(){
		//only for messages sending an event, perform the needed modification
		Block messagesContainer = ((CompoundBehaviour) tdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> tdlMessages = messagesContainer.getBehaviour().stream()
				.filter (b -> b instanceof Message && isMessageSendingAnEvent((Message) b))
				.map(b -> (Message) b).collect(Collectors.toList());
		return tdlMessages;
	}
	
	private List<Message> getEquivalentMessagesOfTestCase(List<Message> messages2find, TestDescription copyTdlTestCase){
		Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		return copyContainer.getBehaviour().stream()
			.filter(b -> b instanceof Message).map(b -> (Message)b)
			.filter(cm -> messages2find.stream().anyMatch(m -> EcoreUtil.equals(m, cm)))
			.collect(Collectors.toList());
	}
	
	private List<Message> generateMessages4MissedEvents(){
		Set<StructuredDataInstance> eventsUsedInTests = findEventsUsedInTestCase();
		List<StructuredDataInstance> eventsNotUsedInTests = tdlEventInstances.stream().
				filter(e -> !eventsUsedInTests.contains(e)).collect(Collectors.toList());
		List<Message> newMessagesForNotUsedEvents = new ArrayList<>();
		for (StructuredDataInstance tdlEventInstance: eventsNotUsedInTests) {
			//check whether for each event parameter, at least one eobject exists in the model under test
			//this is required for setting the value of parameters
			List<Member> tdlEventParameters = ((StructuredDataType) tdlEventInstance.getDataType()).allMembers();
			if (tdlEventParameters.stream()
					.allMatch(p -> !etype_tdlEObjects.get(p.getDataType().getName()).isEmpty())) {
				newMessagesForNotUsedEvents.addAll(transformEvent2Message(tdlEventInstance));
			}
		}
		return newMessagesForNotUsedEvents;
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
	
	//creating new messages for the not used event by using all possible values for all event parameters
	private List<Message> transformEvent2Message(StructuredDataInstance tdlEventInstance) {
		List<Message> newMessages = new ArrayList<>();
		newMessages.add(createFirstMessage4Event(tdlEventInstance));
		int paramIndex = 0;
		int paramSize = ((StructuredDataType) tdlEventInstance.getDataType()).allMembers().size();
		while (paramIndex < paramSize) {
			List<Message> newMessagesByModification = new ArrayList<>();
			Parameter consideredParameter = ((StructuredDataType) tdlEventInstance.getDataType()).allMembers().get(paramIndex);
			for (Message newMessage:newMessages) {
				newMessagesByModification.addAll(generateNewMessagesByNewParameterValues(newMessage, consideredParameter));
			}
			newMessages.addAll(newMessagesByModification);
			paramIndex++;
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
	
	//creating new Messages by modifying the value of a specific parameter
	private List<Message> generateNewMessagesByNewParameterValues(Message currentMessage, Parameter consideredParameter) {
		List<Message> newMessages = new ArrayList<>();
		DataInstanceUse exchangedTdlEventInstance = (DataInstanceUse) currentMessage.getArgument();
		//find parameterBinding of the currentMessage related to the considered parameter
		Optional<ParameterBinding> pbOptional = exchangedTdlEventInstance.getArgument().stream()
				.filter(pb -> pb.getParameter() == consideredParameter)
				.findFirst();
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
	
	private void findTdlDataOfDSLInterface() {
		DSLProcessor dslProcessor = new DSLProcessor(pathHelper.getDSLPath());
		if (dslProcessor.dslHasBehavioralInterface()) {
			Resource interfaceRes = (new ResourceSetImpl()).getResource(dslProcessor.getBehavioralInterfacePluginURI(), true);
			BehavioralInterface interfaceRootElement = (BehavioralInterface) interfaceRes.getContents().get(0);
			List<Event> acceptedEvents = interfaceRootElement.getEvents().stream()
					.filter(e -> e.getType() == EventType.ACCEPTED).collect(Collectors.toList());
			for (Event event:acceptedEvents) {
				//finding the tdl instance corresponding to the accepted event 
				StructuredDataInstance tdlEventInstance = tdlTestSuite.getPackagedElement().stream()
						.filter(p -> p instanceof StructuredDataInstance)
						.map(p -> (StructuredDataInstance) p)
						.filter(d -> getDSLCompatibleName(d.getName()).equals(event.getName()))
						.findFirst().get();
				tdlEventInstances.add(tdlEventInstance);
				//finding the types of the event parameters
				for (Member tdlEventParameter:((StructuredDataType)tdlEventInstance.getDataType()).allMembers()) {
					tdlEventParameterTypes.add((StructuredDataType) tdlEventParameter.getDataType());
				}
			}	
			findTdlDataOfEventParameters();
		}
	}
	
	private void findTdlDataOfEventParameters() {
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
	
	private String getDSLCompatibleName (String name) {
		if (name.startsWith("_")) {
			return name.substring(1);
		}
		return name;
	}
	
	private boolean isMessageSendingAnEvent(Message message) {
		 return tdlEventInstances.contains(((DataInstanceUse) message.getArgument()).getDataInstance());
	}
	
	class DuplicateEvent implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			String newTestId = (numOfNewTests++) + "_" + EVENTDUPLICATION + "_" + policy;
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, newTestId);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			List<Message> tdlMessages = (List<Message>) data;
			tdlMessages.forEach(m -> copyContainer.getBehaviour().add(copyTdlMessage(m)));
			generatedTestsByModification.add(copyTdlTestCase);
		}
	}
	
	class DeleteEvent implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			String newTestId = (numOfNewTests++) + "_" + EVENTDELETION + "_" + policy;
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, newTestId);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			List<Message> tdlMessages = getEquivalentMessagesOfTestCase((List<Message>) data, copyTdlTestCase);
			copyContainer.getBehaviour().removeAll(tdlMessages);
			generatedTestsByModification.add(copyTdlTestCase);
		}
	}
	
	class PermuteEvent implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			String newTestId = (numOfNewTests++) + "_" + EVENTPERMUTATION + "_" + policy;
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, newTestId);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			List<Message> tdlMessages = getEquivalentMessagesOfTestCase((List<Message>) data, copyTdlTestCase);
			copyContainer.getBehaviour().removeAll(tdlMessages);
			Collections.shuffle(tdlMessages);
			copyContainer.getBehaviour().addAll(tdlMessages);
			generatedTestsByModification.add(copyTdlTestCase);
		}
	}
	/*
	 * generating new tests by modifying the event parameter values of existing events 
	 * indeed changing their values with other values of the same type
	 * NOTE: Event parameter values are indeed model objects (according to behavioral interface metalanguage)
	 * we initially discover the types of the event parameters (tdlEventParameterTypes) 
	 * and find/create other/new eobjects for each of them and keeping them in etype_tdlEObjects
	 */
	class ModifyEventParameter implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			String newTestId = numOfNewTests + "_" + EVENTMODIFICATION + "_" + policy;
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, newTestId);
			Block messagesContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			List<Message> tdlMessages = getEquivalentMessagesOfTestCase((List<Message>) data, copyTdlTestCase);
			
			boolean oneParameterChanged = false;
			List<DataInstanceUse> eventParameterValues = findValuesForEventParams(tdlMessages);
			for (DataInstanceUse tdlEobjectRef:eventParameterValues) {
				String eobjectType = tdlEobjectRef.getDataInstance().getDataType().getName();
				DataInstance initialEObject = tdlFactory.eINSTANCE.createStructuredDataInstance();
				initialEObject = EcoreUtil.copy(tdlEobjectRef.getDataInstance());
				//if there are some values for this type, generate new test by changing the value to another value
				if (!etype_tdlEObjects.get(eobjectType).isEmpty()) {
					for (StructuredDataInstance tdlEobjectInstance:etype_tdlEObjects.get(eobjectType)) {
						if (!EcoreUtil.equals(initialEObject, tdlEobjectInstance)) {
							tdlEobjectRef.setDataInstance(tdlEobjectInstance);
							oneParameterChanged = true;
							break;
						}
					}
				}
			}
			//keep the new test case if at least one parameter changed
			if (oneParameterChanged) {		
				generatedTestsByModification.add(copyTdlTestCase);
				numOfNewTests++;
			}
		}
		
		//finding values of event parameters		
		private List<DataInstanceUse> findValuesForEventParams(List<Message> tdlMessages) {
			List<DataInstanceUse> exchangedEvents = tdlMessages.stream()
					.map(m -> (DataInstanceUse) m.getArgument())
					.collect(Collectors.toList());
			List<DataInstanceUse> eventParamsValues = new ArrayList<>();
			for (DataInstanceUse eventInstance: exchangedEvents) {
				if (eventInstance.getArgument().size()>0) {
					eventInstance.getArgument().forEach(a -> 
						eventParamsValues.add((DataInstanceUse) a.getDataUse()));
				}
			}
			return eventParamsValues;
		}
	}
	
	class CreateEvent implements ITestDataModifier{

		@Override
		public void modifyData(Object data) {
			List<Message> newTdlMessages = (List<Message>) data;
			String newTestId = (numOfNewTests++) + "_" + EVENTCREATION + "_" + policy;
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, newTestId);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			copyContainer.getBehaviour().addAll(newTdlMessages);
			generatedTestsByModification.add(copyTdlTestCase);
		}		
	}
}
