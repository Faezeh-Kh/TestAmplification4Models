/**
 */
package org.imt.pssm.reactive.model.statemachines.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.imt.pssm.reactive.model.statemachines.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatemachinesFactoryImpl extends EFactoryImpl implements StatemachinesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatemachinesFactory init() {
		try {
			StatemachinesFactory theStatemachinesFactory = (StatemachinesFactory)EPackage.Registry.INSTANCE.getEFactory(StatemachinesPackage.eNS_URI);
			if (theStatemachinesFactory != null) {
				return theStatemachinesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StatemachinesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachinesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StatemachinesPackage.CUSTOM_SYSTEM: return createCustomSystem();
			case StatemachinesPackage.SIGNAL: return createSignal();
			case StatemachinesPackage.OPERATION: return createOperation();
			case StatemachinesPackage.SIGNAL_EVENT_TYPE: return createSignalEventType();
			case StatemachinesPackage.CALL_EVENT_TYPE: return createCallEventType();
			case StatemachinesPackage.BOOLEAN_ATTRIBUTE: return createBooleanAttribute();
			case StatemachinesPackage.INTEGER_ATTRIBUTE: return createIntegerAttribute();
			case StatemachinesPackage.STRING_ATTRIBUTE: return createStringAttribute();
			case StatemachinesPackage.BOOLEAN_CONSTRAINT: return createBooleanConstraint();
			case StatemachinesPackage.INTEGER_CONSTRAINT: return createIntegerConstraint();
			case StatemachinesPackage.STRING_CONSTRAINT: return createStringConstraint();
			case StatemachinesPackage.STATE_MACHINE: return createStateMachine();
			case StatemachinesPackage.REGION: return createRegion();
			case StatemachinesPackage.PSEUDOSTATE: return createPseudostate();
			case StatemachinesPackage.STATE: return createState();
			case StatemachinesPackage.FINAL_STATE: return createFinalState();
			case StatemachinesPackage.TRANSITION: return createTransition();
			case StatemachinesPackage.TRIGGER: return createTrigger();
			case StatemachinesPackage.BEHAVIOR: return createBehavior();
			case StatemachinesPackage.OPERATION_BEHAVIOR: return createOperationBehavior();
			case StatemachinesPackage.BOOLEAN_ATTRIBUTE_VALUE: return createBooleanAttributeValue();
			case StatemachinesPackage.INTEGER_ATTRIBUTE_VALUE: return createIntegerAttributeValue();
			case StatemachinesPackage.STRING_ATTRIBUTE_VALUE: return createStringAttributeValue();
			case StatemachinesPackage.INTEGER_COMPARISON_EXPRESSION: return createIntegerComparisonExpression();
			case StatemachinesPackage.STRING_COMPARISON_EXPRESSION: return createStringComparisonExpression();
			case StatemachinesPackage.BOOLEAN_BINARY_EXPRESSION: return createBooleanBinaryExpression();
			case StatemachinesPackage.BOOLEAN_UNARY_EXPRESSION: return createBooleanUnaryExpression();
			case StatemachinesPackage.COMPLETION_EVENT_OCCURRENCE: return createCompletionEventOccurrence();
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE: return createSignalEventOccurrence();
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE: return createCallEventOccurrence();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case StatemachinesPackage.BOOLEAN_BINARY_OPERATOR:
				return createBooleanBinaryOperatorFromString(eDataType, initialValue);
			case StatemachinesPackage.BOOLEAN_UNARY_OPERATOR:
				return createBooleanUnaryOperatorFromString(eDataType, initialValue);
			case StatemachinesPackage.INTEGER_COMPARISON_OPERATOR:
				return createIntegerComparisonOperatorFromString(eDataType, initialValue);
			case StatemachinesPackage.STRING_COMPARISON_OPERATOR:
				return createStringComparisonOperatorFromString(eDataType, initialValue);
			case StatemachinesPackage.PSEUDOSTATE_KIND:
				return createPseudostateKindFromString(eDataType, initialValue);
			case StatemachinesPackage.TRANSITION_KIND:
				return createTransitionKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case StatemachinesPackage.BOOLEAN_BINARY_OPERATOR:
				return convertBooleanBinaryOperatorToString(eDataType, instanceValue);
			case StatemachinesPackage.BOOLEAN_UNARY_OPERATOR:
				return convertBooleanUnaryOperatorToString(eDataType, instanceValue);
			case StatemachinesPackage.INTEGER_COMPARISON_OPERATOR:
				return convertIntegerComparisonOperatorToString(eDataType, instanceValue);
			case StatemachinesPackage.STRING_COMPARISON_OPERATOR:
				return convertStringComparisonOperatorToString(eDataType, instanceValue);
			case StatemachinesPackage.PSEUDOSTATE_KIND:
				return convertPseudostateKindToString(eDataType, instanceValue);
			case StatemachinesPackage.TRANSITION_KIND:
				return convertTransitionKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomSystem createCustomSystem() {
		CustomSystemImpl customSystem = new CustomSystemImpl();
		return customSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal createSignal() {
		SignalImpl signal = new SignalImpl();
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignalEventType createSignalEventType() {
		SignalEventTypeImpl signalEventType = new SignalEventTypeImpl();
		return signalEventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallEventType createCallEventType() {
		CallEventTypeImpl callEventType = new CallEventTypeImpl();
		return callEventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanAttribute createBooleanAttribute() {
		BooleanAttributeImpl booleanAttribute = new BooleanAttributeImpl();
		return booleanAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerAttribute createIntegerAttribute() {
		IntegerAttributeImpl integerAttribute = new IntegerAttributeImpl();
		return integerAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringAttribute createStringAttribute() {
		StringAttributeImpl stringAttribute = new StringAttributeImpl();
		return stringAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanConstraint createBooleanConstraint() {
		BooleanConstraintImpl booleanConstraint = new BooleanConstraintImpl();
		return booleanConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerConstraint createIntegerConstraint() {
		IntegerConstraintImpl integerConstraint = new IntegerConstraintImpl();
		return integerConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringConstraint createStringConstraint() {
		StringConstraintImpl stringConstraint = new StringConstraintImpl();
		return stringConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachine createStateMachine() {
		StateMachineImpl stateMachine = new StateMachineImpl();
		return stateMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Region createRegion() {
		RegionImpl region = new RegionImpl();
		return region;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pseudostate createPseudostate() {
		PseudostateImpl pseudostate = new PseudostateImpl();
		return pseudostate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FinalState createFinalState() {
		FinalStateImpl finalState = new FinalStateImpl();
		return finalState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trigger createTrigger() {
		TriggerImpl trigger = new TriggerImpl();
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Behavior createBehavior() {
		BehaviorImpl behavior = new BehaviorImpl();
		return behavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationBehavior createOperationBehavior() {
		OperationBehaviorImpl operationBehavior = new OperationBehaviorImpl();
		return operationBehavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanAttributeValue createBooleanAttributeValue() {
		BooleanAttributeValueImpl booleanAttributeValue = new BooleanAttributeValueImpl();
		return booleanAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerAttributeValue createIntegerAttributeValue() {
		IntegerAttributeValueImpl integerAttributeValue = new IntegerAttributeValueImpl();
		return integerAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringAttributeValue createStringAttributeValue() {
		StringAttributeValueImpl stringAttributeValue = new StringAttributeValueImpl();
		return stringAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerComparisonExpression createIntegerComparisonExpression() {
		IntegerComparisonExpressionImpl integerComparisonExpression = new IntegerComparisonExpressionImpl();
		return integerComparisonExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringComparisonExpression createStringComparisonExpression() {
		StringComparisonExpressionImpl stringComparisonExpression = new StringComparisonExpressionImpl();
		return stringComparisonExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanBinaryExpression createBooleanBinaryExpression() {
		BooleanBinaryExpressionImpl booleanBinaryExpression = new BooleanBinaryExpressionImpl();
		return booleanBinaryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanUnaryExpression createBooleanUnaryExpression() {
		BooleanUnaryExpressionImpl booleanUnaryExpression = new BooleanUnaryExpressionImpl();
		return booleanUnaryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompletionEventOccurrence createCompletionEventOccurrence() {
		CompletionEventOccurrenceImpl completionEventOccurrence = new CompletionEventOccurrenceImpl();
		return completionEventOccurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignalEventOccurrence createSignalEventOccurrence() {
		SignalEventOccurrenceImpl signalEventOccurrence = new SignalEventOccurrenceImpl();
		return signalEventOccurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallEventOccurrence createCallEventOccurrence() {
		CallEventOccurrenceImpl callEventOccurrence = new CallEventOccurrenceImpl();
		return callEventOccurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanBinaryOperator createBooleanBinaryOperatorFromString(EDataType eDataType, String initialValue) {
		BooleanBinaryOperator result = BooleanBinaryOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanBinaryOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanUnaryOperator createBooleanUnaryOperatorFromString(EDataType eDataType, String initialValue) {
		BooleanUnaryOperator result = BooleanUnaryOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanUnaryOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerComparisonOperator createIntegerComparisonOperatorFromString(EDataType eDataType, String initialValue) {
		IntegerComparisonOperator result = IntegerComparisonOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIntegerComparisonOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringComparisonOperator createStringComparisonOperatorFromString(EDataType eDataType, String initialValue) {
		StringComparisonOperator result = StringComparisonOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStringComparisonOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PseudostateKind createPseudostateKindFromString(EDataType eDataType, String initialValue) {
		PseudostateKind result = PseudostateKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPseudostateKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionKind createTransitionKindFromString(EDataType eDataType, String initialValue) {
		TransitionKind result = TransitionKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransitionKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachinesPackage getStatemachinesPackage() {
		return (StatemachinesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StatemachinesPackage getPackage() {
		return StatemachinesPackage.eINSTANCE;
	}

} //StatemachinesFactoryImpl
