package org.imt.tdl.amplification.testmodifier;

import java.util.Optional;

import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument;
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
import org.etsi.mts.tdl.DataInstance;
import org.etsi.mts.tdl.DataInstanceUse;
import org.etsi.mts.tdl.DataUse;
import org.etsi.mts.tdl.MemberAssignment;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.ParameterBinding;
import org.etsi.mts.tdl.StructuredDataInstance;
import org.etsi.mts.tdl.tdlFactory;

public class Event2TDLConverter {
	
	Package testSuite; 
	
	public Event2TDLConverter (Package testSuite) {
		this.testSuite = testSuite;
	}
	
	public DataUse convertEventOccurrence2TdlDataUse (EventOccurrence exposedEvent) {
		DataInstanceUse expectedData = tdlFactory.eINSTANCE.createDataInstanceUse();
		//finding a tdl data instance with the name of the exposed event 
		//(the TDL library generator must generated it in the xDSL-specific events package)
		Optional<DataInstance> diOptional = testSuite.getPackagedElement().stream().
				filter(p -> p instanceof DataInstance).map(p -> (DataInstance) p).
				filter(d -> d.getName().equals(exposedEvent.getEvent().getName())).findFirst();
		if (diOptional.isEmpty()) {
			return null;
		}
		DataInstance di = diOptional.get();
		/*if the data instance is a structured data instance, 
		 * it means it has parameters that have to be bind 
		 * based on the values of the arguments of the exposed event 
		 */
		if (di instanceof StructuredDataInstance) {
			StructuredDataInstance sdi = (StructuredDataInstance) di;
			for (MemberAssignment ma: sdi.getMemberAssignment()) {
				//finding the event argument related to the member assignment 
				Optional<EventOccurrenceArgument> eoaOptional = exposedEvent.getArgs().stream().
					filter(a -> a.getParameter().getName().equals(ma.getMember().getName())).findFirst();
				if (eoaOptional.isEmpty()) {
					return null;
				}
				//get the value of the argument
				Value eventOccuArgValue = (eoaOptional.get()).getValue();
				//create parameterBindings for the created dataInstanceUse for each eventOccurrenceArgument
				ParameterBinding pb = tdlFactory.eINSTANCE.createParameterBinding();
				pb.setParameter(ma.getMember());
				pb.setDataUse(getTdlValueOfObjectValue(eventOccuArgValue));
				expectedData.getArgument().add(pb);
			}
		}
		expectedData.setDataInstance(di);
		return expectedData;
	}

	private DataUse getTdlValueOfObjectValue(Value eventOccuArgValue) {
		EObject2TDLConverter converter = new EObject2TDLConverter(testSuite);
		switch (eventOccuArgValue.eClass().getClassifierID()) {
		case ValuePackage.SINGLE_REFERENCE_VALUE:
			return converter.convertEObject2TDLDataUse(((SingleReferenceValue) eventOccuArgValue).getReferenceValue());
		case ValuePackage.SINGLE_OBJECT_VALUE:
			return converter.convertEObject2TDLDataUse(((SingleObjectValue) eventOccuArgValue).getObjectValue());
		case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE:
			return converter.convertBoolean2TDLData(((BooleanAttributeValue) eventOccuArgValue).isAttributeValue());
		case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE:
			return converter.convertBoolean2TDLData(((BooleanObjectAttributeValue) eventOccuArgValue).getAttributeValue());
		case ValuePackage.INTEGER_ATTRIBUTE_VALUE:
			return converter.convertInteger2TDLData(((IntegerAttributeValue) eventOccuArgValue).getAttributeValue());
		case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE:
			return converter.convertInteger2TDLData(((IntegerObjectAttributeValue) eventOccuArgValue).getAttributeValue());
		case ValuePackage.FLOAT_ATTRIBUTE_VALUE:
			return converter.convertFloat2TDLData(((FloatAttributeValue) eventOccuArgValue).getAttributeValue());
		case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE:
			return converter.convertFloat2TDLData(((FloatObjectAttributeValue) eventOccuArgValue).getAttributeValue());
		case ValuePackage.STRING_ATTRIBUTE_VALUE:
			return converter.convertString2TDLData(((StringAttributeValue) eventOccuArgValue).getAttributeValue());
		}
		return null;
	}
}
