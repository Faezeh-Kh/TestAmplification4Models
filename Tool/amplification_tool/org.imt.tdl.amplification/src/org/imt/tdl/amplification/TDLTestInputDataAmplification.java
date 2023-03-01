package org.imt.tdl.amplification;

import java.util.ArrayList;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
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
import org.etsi.mts.tdl.DataType;
import org.etsi.mts.tdl.DataUse;
import org.etsi.mts.tdl.LiteralValueUse;
import org.etsi.mts.tdl.Member;
import org.etsi.mts.tdl.Message;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.Parameter;
import org.etsi.mts.tdl.ParameterBinding;
import org.etsi.mts.tdl.StructuredDataInstance;
import org.etsi.mts.tdl.StructuredDataType;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.tdlFactory;
import org.imt.tdl.amplification.utilities.EObject2TDLConverter;
import org.imt.tdl.mutation.utilities.PathHelper;

public class TDLTestInputDataAmplification {
	
	Package tdlTestSuite;
	TestDescription tdlTestCase;
	
	//the tdl instances corresponding to the accepted events
	List<StructuredDataInstance> tdlEventInstances = new ArrayList<>();
	Set<StructuredDataType> tdlEventParameterTypes = new HashSet<>();
	
	//keep a list of tdlInstances that are corresponding to eobjects of the model under test
	Map<String, List<StructuredDataInstance>> etype_tdlEObjects = new HashMap<>();
	
	List<LiteralValueUse> boolLiterals = new ArrayList<LiteralValueUse>();
	List<LiteralValueUse> stringLiterals = new ArrayList<LiteralValueUse>();
	List<LiteralValueUse> intLiterals = new ArrayList<LiteralValueUse>();
	List<LiteralValueUse> floatLiterals = new ArrayList<LiteralValueUse>();
	int numOfNewTests = 0;
	
	private static String BOOLMODIFICATION = "BooleanModification";
	private static String STRINGMODIFICATION = "StringModification";
	private static String INTMODIFICATION = "IntegerModification";
	private static String FLOATMODIFICATION = "FloatModification";
	private static String EVENTDUPLICATION = "EventDuplication";
	private static String EVENTCREATION = "EventCreation";
	private static String EVENTDELETION = "EventDeletion";
	private static String EVENTPERMUTATION = "EventPermutation";
	private static String EVENTMODIFICATION = "EventModification";
	
	public TDLTestInputDataAmplification(Package tdlTestSuite) {
		this.tdlTestSuite = tdlTestSuite;
	}
	
	public List<TestDescription> generateNewTestsByInputModification (TestDescription testCase) {
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		this.tdlTestCase = testCase;
		generatedTestsByModification.addAll(modifyLiteralData());
		generatedTestsByModification.addAll(modifyExchangedEvents());
		return generatedTestsByModification;
	}

	/* four operators can be applied:
	 * 1. addition : duplication of existing & creation of new events based on the interface
	 * 2. deletion
	 * 3. reordering
	 * 4. modification of event parameters values with other values for the same parameter
	 * 
	 * NOTE: We assume a TDL test case only contains a series of Message elements each sending an accepted event to the model under test
	 */
	private Collection<? extends TestDescription> modifyExchangedEvents() {
		//initialization
		findTdlDataOfDSLInterface();
		findTdlDataOfMUT();
		
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		generatedTestsByModification.addAll(generateTestsByEventDuplication());
		generatedTestsByModification.addAll(generateTestsByEventCreation());
		generatedTestsByModification.addAll(generateTestsByEventDeletion());
		generatedTestsByModification.addAll(generateTestsByEventPermutation());
		generatedTestsByModification.addAll(generateTestsByEventModification());
		return generatedTestsByModification;
	}
	
	private void findTdlDataOfDSLInterface() {
		String dslFilePath = PathHelper.getInstance().getDSLPath();
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
		Resource MUTResource = PathHelper.getInstance().getMUTResource();
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

	//generating new test cases by addition-duplication of existing events
	private Collection<? extends TestDescription> generateTestsByEventDuplication() {
		List<TestDescription> generatedTestsByEventDuplication = new ArrayList<>();
		Block messagesContainer = ((CompoundBehaviour) tdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> tdlMessages = messagesContainer.getBehaviour().stream()
				.map(b -> (Message) b).collect(Collectors.toList());
		//only for messages sending an event, perform modification by duplication
		for (Message tdlMessage: tdlMessages.stream().filter(m -> isMessageSendingAnEvent(m)).collect(Collectors.toList())) {
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, EVENTDUPLICATION);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			copyContainer.getBehaviour().add(copyTdlMessage(tdlMessage));
			generatedTestsByEventDuplication.add(copyTdlTestCase);
		}
		return generatedTestsByEventDuplication;
	}
	
	/* generating new test cases by addition-creation of new events
	 * for the accepted events of the behavioral interface that are not used in the test cases, create new events
	 * - if the EObjects used as event parameters are existed in the model under test
	 * all the possible new events are created and new tests are generated by adding one new event to them
	 * create new tests by adding new events one by one, and adding them all to one test case
	 */
	private Collection<? extends TestDescription> generateTestsByEventCreation() {
		if (tdlEventInstances == null) {
			return Collections.emptyList();
		}
		List<TestDescription> generatedTestsByEventCreation = new ArrayList<>();
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
					TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, EVENTCREATION);
					Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
					copyContainer.getBehaviour().add(newMessage);
					generatedTestsByEventCreation.add(copyTdlTestCase);
				}
				if (newMessages.size()>1) {
					//create a new test case containing all new messages
					List<Message> allNewMessages = new ArrayList<>();
					Collections.copy(allNewMessages, newMessages);
					TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, EVENTCREATION);
					Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
					copyContainer.getBehaviour().addAll(allNewMessages);
					generatedTestsByEventCreation.add(copyTdlTestCase);
					//for this new test case having several new messages, amplify it by other operators: duplication, permutation, deletion
					TestDescription inputTestCase = this.tdlTestCase;
					tdlTestCase = copyTdlTestCase;
					generatedTestsByEventCreation.addAll(generateTestsByEventDuplication());
					generatedTestsByEventCreation.addAll(generateTestsByEventPermutation());
					if (newMessages.size() > 2) {
						generatedTestsByEventCreation.addAll(generateTestsByEventDeletion());
					}
					tdlTestCase = inputTestCase;
				}
				newMessages.forEach(m -> newMessagesForNotUsedEvents.add(copyTdlMessage(m)));;
				n++;
			}
		}
		if (n>1) {
			//create a new test case containing all new messages for all not used event instances
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, EVENTCREATION);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			copyContainer.getBehaviour().addAll(newMessagesForNotUsedEvents);
			generatedTestsByEventCreation.add(copyTdlTestCase);
			//for this new test case having several new messages, amplify it by other operators: duplication, permutation, deletion
			TestDescription inputTestCase = this.tdlTestCase;
			tdlTestCase = copyTdlTestCase;
			generatedTestsByEventCreation.addAll(generateTestsByEventDuplication());
			generatedTestsByEventCreation.addAll(generateTestsByEventPermutation());
			if (n > 2) {
				generatedTestsByEventCreation.addAll(generateTestsByEventDeletion());
			}
			tdlTestCase = inputTestCase;
		}	
		
		return generatedTestsByEventCreation;
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
	//generating new tests by deleting the existing events
	private Collection<? extends TestDescription> generateTestsByEventDeletion() {
		List<TestDescription> generatedTestsByDeletion = new ArrayList<>();
		Block messagesContainer = ((CompoundBehaviour) tdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> tdlMessages = messagesContainer.getBehaviour().stream()
				.map(b -> (Message) b).collect(Collectors.toList());
		for (Message tdlMessage: tdlMessages.stream()
				.filter(m -> isMessageSendingAnEvent(m)).collect(Collectors.toList())) {
			TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, EVENTDELETION);
			Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
			copyContainer.getBehaviour().remove(tdlMessage);
			generatedTestsByDeletion.add(copyTdlTestCase);
		}
		return generatedTestsByDeletion;
	}

	//generating new tests by permuting the existing events
	private Collection<? extends TestDescription> generateTestsByEventPermutation() {
		List<TestDescription> generatedTestsByPermutation = new ArrayList<>();
		TestDescription copyTdlTestCase = copyTdlTestCase(tdlTestCase, EVENTPERMUTATION);
		Block copyContainer = ((CompoundBehaviour) copyTdlTestCase.getBehaviourDescription().getBehaviour()).getBlock();
		List<Message> copyMessages = copyContainer.getBehaviour().stream().map(b -> (Message) b).
				filter(m -> isMessageSendingAnEvent(m)).collect(Collectors.toList());
		copyContainer.getBehaviour().clear();
		copyContainer.getBehaviour().addAll(copyMessages);
		generatedTestsByPermutation.add(copyTdlTestCase);		
		return generatedTestsByPermutation;
	}
	
	/*
	 * generating new tests by modifying the event parameter values of existing events 
	 * indeed changing their values with other values of the same type
	 * NOTE: Event parameter values are indeed model objects (according to behavioral interface metalanguage)
	 * we initially discovered the types of the event parameters (tdlEventParameterTypes) 
	 * and find/create other/new eobjects for each of them and keeping them in etype_tdlEObjects
	 */
	private Collection<? extends TestDescription> generateTestsByEventModification() {
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
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
						generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, EVENTMODIFICATION));
					}
				}
			}
		}
		return generatedTestsByModification;
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

	private List<TestDescription> modifyLiteralData() {
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		Iterator<EObject> iterator = tdlTestCase.eAllContents();
		while (iterator.hasNext()) {
			EObject object = iterator.next();
			if (object instanceof DataInstanceUse) {
				findLiteralFeaturesOfData((DataInstanceUse) object);
			}
		}
		if (boolLiterals.size()>0) {
			generatedTestsByModification.addAll(modifyBooleanData());
		}
		else if (stringLiterals.size()>0) {
			generatedTestsByModification.addAll(modifyStringData());
		}
		else if (intLiterals.size()>0) {
			generatedTestsByModification.addAll(modifyIntegerData());
		}
		else if (floatLiterals.size()>0) {
			generatedTestsByModification.addAll(modifyFloatData());
		}
		return generatedTestsByModification;
	}
	
	private Collection<? extends TestDescription> modifyBooleanData() {
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		for (LiteralValueUse boolLiteral:boolLiterals) {
			String initialValue = getLiteralValue(boolLiteral);
			if (initialValue.equals("true")) {
				boolLiteral.setValue("\"false\"");
			}
			else if (initialValue.equals("false")) {
				boolLiteral.setValue("\"true\"");
			}
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, BOOLMODIFICATION));
			boolLiteral.setValue("\""+ initialValue + "\"");
		}
		return generatedTestsByModification;
	}

	/*four operators: 
	 * 1. add a random char, 
	 * 2. remove a random char, 
	 * 3. replace a random char and 
	 * 4. replace the string by a fully random string of the same size
	 */
	private Collection<? extends TestDescription> modifyStringData() {
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		for (LiteralValueUse stringLiteral:stringLiterals) {
			String initialValue = getLiteralValue(stringLiteral);
			StringBuilder sb = new StringBuilder(initialValue);
			Random rand = new Random();
			int randomIndex = rand.nextInt(sb.length());
			// 1. add a random char
			sb.append(RandomStringUtils.randomAlphanumeric(1));
			stringLiteral.setValue(sb.toString());
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, STRINGMODIFICATION));
			//2. remove a random char
			sb = new StringBuilder(initialValue);
			sb.deleteCharAt(randomIndex);
			stringLiteral.setValue(sb.toString());
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, STRINGMODIFICATION));
			//3. replace a random char
			sb = new StringBuilder(initialValue);
			sb.replace(randomIndex, randomIndex + 1, RandomStringUtils.randomAlphanumeric(1));
			stringLiteral.setValue(sb.toString());
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, STRINGMODIFICATION));
			//4. replace the string by a fully random string of the same size
			stringLiteral.setValue(RandomStringUtils.randomAlphanumeric(initialValue.length()));
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, STRINGMODIFICATION));
			//5. replace the string by an empty string (based on pitest tool)
			stringLiteral.setValue("\"\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, STRINGMODIFICATION));
			
			stringLiteral.setValue("\""+ initialValue + "\"");
		}
		return generatedTestsByModification;
	}

	/* five operators for numeric values: 
	 * plus 1, minus 1, multiply by 2, divide by 2, and replacement by an existing literal of the same type
	 */
	private Collection<? extends TestDescription> modifyIntegerData() {
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		for (LiteralValueUse intLiteral:intLiterals) {
			String initialValue = getLiteralValue(intLiteral);
			int value = Integer.parseInt(initialValue);
			//1. value = 1
			intLiteral.setValue("\"" + (1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			//2. value = 0
			intLiteral.setValue("\"" + (0) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			//3. value = -1
			intLiteral.setValue("\"" + (-1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			//4. negating value
			intLiteral.setValue("\"" + (-value) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			//5. value plus 1
			intLiteral.setValue("\"" + (value + 1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			//6. value minus 1
			intLiteral.setValue("\"" + (value - 1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			//7. value multiply by 2
			intLiteral.setValue("\"" + (value * 2) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			//8. value divide by 2
			intLiteral.setValue("\"" + (value / 2) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			//9. replacement by an existing literal of the same type
			List<LiteralValueUse> otherValues = intLiterals.stream()
					.filter(i -> i != intLiteral).collect(Collectors.toList());
			for (LiteralValueUse otherValue: otherValues) {
				intLiteral.setValue(otherValue.getValue());
				generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, INTMODIFICATION));
			}
			intLiteral.setValue("\""+ initialValue + "\"");
		}
		return generatedTestsByModification;
	}

	private Collection<? extends TestDescription> modifyFloatData() {
		List<TestDescription> generatedTestsByModification = new ArrayList<>();
		for (LiteralValueUse floatLiteral:floatLiterals) {
			String initialValue = getLiteralValue(floatLiteral);
			float value = Float.parseFloat(initialValue);
			//1. value = 1
			floatLiteral.setValue("\"" + (1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			//2. value = 0
			floatLiteral.setValue("\"" + (0) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			//3. value = -1
			floatLiteral.setValue("\"" + (-1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			//4. negating value
			floatLiteral.setValue("\"" + (-value) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			//5. value plus 1
			floatLiteral.setValue("\"" + (value + 1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			//6. value minus 1
			floatLiteral.setValue("\"" + (value - 1) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			//7. value multiply by 2
			floatLiteral.setValue("\"" + (value * 2) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			//8. value divide by 2
			floatLiteral.setValue("\"" + (float)(value / 2) + "\"");
			generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			//9. replacement by an existing literal of the same type
			List<LiteralValueUse> otherValues = floatLiterals.stream()
					.filter(i -> i != floatLiteral).collect(Collectors.toList());
			for (LiteralValueUse otherValue: otherValues) {
				floatLiteral.setValue(otherValue.getValue());
				generatedTestsByModification.add(copyTdlTestCase(tdlTestCase, FLOATMODIFICATION));
			}
			
			floatLiteral.setValue("\"" + initialValue + "\"");
		}
		return generatedTestsByModification;
	}


	private void findLiteralFeaturesOfData (DataInstanceUse dataInstanceUse){
		//finding literals that their value is directly used in the test case
		//(ignoring memberAssignments and only considering parameterBindings)
		for (ParameterBinding pb : dataInstanceUse.getArgument()) {
			DataUse parameterValue = pb.getDataUse();
			if (!pb.getParameter().getName().equals("_name")) {
				if (parameterValue instanceof LiteralValueUse) {
					classifyLiteralBasedOnType ((LiteralValueUse) parameterValue, pb.getParameter().getDataType());
				}else if (parameterValue instanceof DataInstanceUse) {
					findLiteralFeaturesOfData((DataInstanceUse) parameterValue);
				}
			}
		}
	}
	
	private void classifyLiteralBasedOnType (LiteralValueUse literal, DataType type) {
		String typeName = type.getName();
		if (typeName.equals("EBooleanObject") || typeName.equals("EBoolean")) {
			boolLiterals.add(literal);
		}
		else if (typeName.equals("EString")) {
			stringLiterals.add(literal);
		}
		else if (typeName.equals("EIntegerObject") || typeName.equals("EInt")) {
			intLiterals.add(literal);
		}
		else if (typeName.equals("EFloatObject") || typeName.equals("EFloat")) {
			floatLiterals.add(literal);
		}
	}
	
	private TestDescription copyTdlTestCase(TestDescription testCase, String modificationOperator) {
		TestDescription copyTdlTestCase = tdlFactory.eINSTANCE.createTestDescription();
		copyTdlTestCase.setName(testCase.getName()  + "_" + (numOfNewTests++) + "_" + modificationOperator);
		copyTdlTestCase.setTestConfiguration(testCase.getTestConfiguration());
		copyTdlTestCase.setBehaviourDescription(EcoreUtil.copy(testCase.getBehaviourDescription()));
		return copyTdlTestCase;
	}
	
	private Message copyTdlMessage (Message message) {
		Message copyMessage = tdlFactory.eINSTANCE.createMessage();
		copyMessage.setSourceGate(message.getSourceGate());
		copyMessage.getTarget().addAll(EcoreUtil.copyAll(message.getTarget()));
		copyMessage.setArgument(EcoreUtil.copy(message.getArgument()));
		copyMessage.setIsTrigger(message.isIsTrigger());
		copyMessage.setTimeLabel(EcoreUtil.copy(message.getTimeLabel()));
		return copyMessage;
	}
	
	private String getLiteralValue (LiteralValueUse literalValue) {
		String value = literalValue.getValue();
		if (value.startsWith("\"") || value.startsWith("'")){
			return value.substring(1, value.length()-1);//remove quotation marks
    	}
		return value;
	}
	
	private String getDSLCompatibleName (String name) {
		if (name.startsWith("_")) {
			return name.substring(1);
		}
		return name;
	}
	
//	private static boolean compareModels(Resource model1, Resource model2) {
//		IComparisonScope scope = new DefaultComparisonScope(model1, model2, null);
//		Comparison comparison = EMFCompare.builder().build().compare(scope);
//
//		List<Diff> differences = comparison.getDifferences();
//
//		if (differences.size() == 0) {
//			return true;
//		}
//
//		return false;
//	}
}
