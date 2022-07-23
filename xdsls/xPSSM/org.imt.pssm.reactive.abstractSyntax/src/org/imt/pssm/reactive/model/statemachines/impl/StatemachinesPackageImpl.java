/**
 */
package org.imt.pssm.reactive.model.statemachines.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.imt.pssm.reactive.model.statemachines.Attribute;
import org.imt.pssm.reactive.model.statemachines.AttributeValue;
import org.imt.pssm.reactive.model.statemachines.Behavior;
import org.imt.pssm.reactive.model.statemachines.BooleanAttribute;
import org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue;
import org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression;
import org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator;
import org.imt.pssm.reactive.model.statemachines.BooleanConstraint;
import org.imt.pssm.reactive.model.statemachines.BooleanExpression;
import org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression;
import org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator;
import org.imt.pssm.reactive.model.statemachines.CallEventOccurrence;
import org.imt.pssm.reactive.model.statemachines.CallEventType;
import org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence;
import org.imt.pssm.reactive.model.statemachines.Constraint;
import org.imt.pssm.reactive.model.statemachines.CustomSystem;
import org.imt.pssm.reactive.model.statemachines.EventOccurrence;
import org.imt.pssm.reactive.model.statemachines.EventType;
import org.imt.pssm.reactive.model.statemachines.Expression;
import org.imt.pssm.reactive.model.statemachines.FinalState;
import org.imt.pssm.reactive.model.statemachines.IntegerAttribute;
import org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue;
import org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression;
import org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator;
import org.imt.pssm.reactive.model.statemachines.IntegerConstraint;
import org.imt.pssm.reactive.model.statemachines.NamedElement;
import org.imt.pssm.reactive.model.statemachines.Operation;
import org.imt.pssm.reactive.model.statemachines.OperationBehavior;
import org.imt.pssm.reactive.model.statemachines.Pseudostate;
import org.imt.pssm.reactive.model.statemachines.PseudostateKind;
import org.imt.pssm.reactive.model.statemachines.Region;
import org.imt.pssm.reactive.model.statemachines.Signal;
import org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence;
import org.imt.pssm.reactive.model.statemachines.SignalEventType;
import org.imt.pssm.reactive.model.statemachines.State;
import org.imt.pssm.reactive.model.statemachines.StateMachine;
import org.imt.pssm.reactive.model.statemachines.StatemachinesFactory;
import org.imt.pssm.reactive.model.statemachines.StatemachinesPackage;
import org.imt.pssm.reactive.model.statemachines.StringAttribute;
import org.imt.pssm.reactive.model.statemachines.StringAttributeValue;
import org.imt.pssm.reactive.model.statemachines.StringComparisonExpression;
import org.imt.pssm.reactive.model.statemachines.StringComparisonOperator;
import org.imt.pssm.reactive.model.statemachines.StringConstraint;
import org.imt.pssm.reactive.model.statemachines.Transition;
import org.imt.pssm.reactive.model.statemachines.TransitionKind;
import org.imt.pssm.reactive.model.statemachines.Trigger;
import org.imt.pssm.reactive.model.statemachines.Vertex;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatemachinesPackageImpl extends EPackageImpl implements StatemachinesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customSystemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signalEventTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callEventTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateMachineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass regionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pseudostateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass finalStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behaviorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationBehaviorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerComparisonExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringComparisonExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanBinaryExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanUnaryExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventOccurrenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass completionEventOccurrenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signalEventOccurrenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callEventOccurrenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum booleanBinaryOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum booleanUnaryOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum integerComparisonOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum stringComparisonOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum pseudostateKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transitionKindEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StatemachinesPackageImpl() {
		super(eNS_URI, StatemachinesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link StatemachinesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StatemachinesPackage init() {
		if (isInited) return (StatemachinesPackage)EPackage.Registry.INSTANCE.getEPackage(StatemachinesPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredStatemachinesPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		StatemachinesPackageImpl theStatemachinesPackage = registeredStatemachinesPackage instanceof StatemachinesPackageImpl ? (StatemachinesPackageImpl)registeredStatemachinesPackage : new StatemachinesPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theStatemachinesPackage.createPackageContents();

		// Initialize created meta-data
		theStatemachinesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStatemachinesPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StatemachinesPackage.eNS_URI, theStatemachinesPackage);
		return theStatemachinesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomSystem() {
		return customSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomSystem_Statemachine() {
		return (EReference)customSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomSystem_Signals() {
		return (EReference)customSystemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomSystem_Operations() {
		return (EReference)customSystemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignal() {
		return signalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignal_Attributes() {
		return (EReference)signalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_InParameters() {
		return (EReference)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_OutParameters() {
		return (EReference)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_Return() {
		return (EReference)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventType() {
		return eventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignalEventType() {
		return signalEventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignalEventType_Signal() {
		return (EReference)signalEventTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCallEventType() {
		return callEventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallEventType_Operation() {
		return (EReference)callEventTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanAttribute() {
		return booleanAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerAttribute() {
		return integerAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringAttribute() {
		return stringAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanConstraint() {
		return booleanConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanConstraint_Expression() {
		return (EReference)booleanConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerConstraint() {
		return integerConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntegerConstraint_Expression() {
		return (EReference)integerConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringConstraint() {
		return stringConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringConstraint_Expression() {
		return (EReference)stringConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateMachine() {
		return stateMachineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateMachine_Regions() {
		return (EReference)stateMachineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRegion() {
		return regionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_Vertice() {
		return (EReference)regionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_Transitions() {
		return (EReference)regionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_StateMachine() {
		return (EReference)regionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_State() {
		return (EReference)regionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegion_CurrentVertex() {
		return (EReference)regionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVertex() {
		return vertexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_Container() {
		return (EReference)vertexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_OutgoingTransitions() {
		return (EReference)vertexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_IncomingTransitions() {
		return (EReference)vertexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPseudostate() {
		return pseudostateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPseudostate_Kind() {
		return (EAttribute)pseudostateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPseudostate_State() {
		return (EReference)pseudostateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Regions() {
		return (EReference)stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Entry() {
		return (EReference)stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_DoActivity() {
		return (EReference)stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Exit() {
		return (EReference)stateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_DeferrableTriggers() {
		return (EReference)stateEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_ConnectionPoint() {
		return (EReference)stateEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFinalState() {
		return finalStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Source() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Target() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Triggers() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Container() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_Kind() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Effect() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Constraint() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrigger() {
		return triggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrigger_EventType() {
		return (EReference)triggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehavior() {
		return behaviorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavior_EmittedSignals() {
		return (EReference)behaviorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationBehavior() {
		return operationBehaviorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationBehavior_AttributeValues() {
		return (EReference)operationBehaviorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeValue() {
		return attributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanAttributeValue() {
		return booleanAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanAttributeValue_Attribute() {
		return (EReference)booleanAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanAttributeValue_Value() {
		return (EAttribute)booleanAttributeValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerAttributeValue() {
		return integerAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntegerAttributeValue_Attribute() {
		return (EReference)integerAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerAttributeValue_Value() {
		return (EAttribute)integerAttributeValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringAttributeValue() {
		return stringAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringAttributeValue_Attribute() {
		return (EReference)stringAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringAttributeValue_Value() {
		return (EAttribute)stringAttributeValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression() {
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerComparisonExpression() {
		return integerComparisonExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerComparisonExpression_Operator() {
		return (EAttribute)integerComparisonExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntegerComparisonExpression_Operand1() {
		return (EReference)integerComparisonExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntegerComparisonExpression_Operand2() {
		return (EReference)integerComparisonExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringComparisonExpression() {
		return stringComparisonExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringComparisonExpression_Operator() {
		return (EAttribute)stringComparisonExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringComparisonExpression_Operand1() {
		return (EReference)stringComparisonExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringComparisonExpression_Operand2() {
		return (EReference)stringComparisonExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanExpression() {
		return booleanExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanBinaryExpression() {
		return booleanBinaryExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanBinaryExpression_Operator() {
		return (EAttribute)booleanBinaryExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanBinaryExpression_Operand1() {
		return (EReference)booleanBinaryExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanBinaryExpression_Operand2() {
		return (EReference)booleanBinaryExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanUnaryExpression() {
		return booleanUnaryExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanUnaryExpression_Operator() {
		return (EAttribute)booleanUnaryExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanUnaryExpression_Operand() {
		return (EReference)booleanUnaryExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventOccurrence() {
		return eventOccurrenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompletionEventOccurrence() {
		return completionEventOccurrenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompletionEventOccurrence_State() {
		return (EReference)completionEventOccurrenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignalEventOccurrence() {
		return signalEventOccurrenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignalEventOccurrence_Signal() {
		return (EReference)signalEventOccurrenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignalEventOccurrence_AttributeValues() {
		return (EReference)signalEventOccurrenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCallEventOccurrence() {
		return callEventOccurrenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallEventOccurrence_Operation() {
		return (EReference)callEventOccurrenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallEventOccurrence_InParameterValues() {
		return (EReference)callEventOccurrenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallEventOccurrence_OutParameterValues() {
		return (EReference)callEventOccurrenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallEventOccurrence_ReturnValue() {
		return (EReference)callEventOccurrenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBooleanBinaryOperator() {
		return booleanBinaryOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBooleanUnaryOperator() {
		return booleanUnaryOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIntegerComparisonOperator() {
		return integerComparisonOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStringComparisonOperator() {
		return stringComparisonOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPseudostateKind() {
		return pseudostateKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTransitionKind() {
		return transitionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachinesFactory getStatemachinesFactory() {
		return (StatemachinesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		customSystemEClass = createEClass(CUSTOM_SYSTEM);
		createEReference(customSystemEClass, CUSTOM_SYSTEM__STATEMACHINE);
		createEReference(customSystemEClass, CUSTOM_SYSTEM__SIGNALS);
		createEReference(customSystemEClass, CUSTOM_SYSTEM__OPERATIONS);

		signalEClass = createEClass(SIGNAL);
		createEReference(signalEClass, SIGNAL__ATTRIBUTES);

		operationEClass = createEClass(OPERATION);
		createEReference(operationEClass, OPERATION__IN_PARAMETERS);
		createEReference(operationEClass, OPERATION__OUT_PARAMETERS);
		createEReference(operationEClass, OPERATION__RETURN);

		eventTypeEClass = createEClass(EVENT_TYPE);

		signalEventTypeEClass = createEClass(SIGNAL_EVENT_TYPE);
		createEReference(signalEventTypeEClass, SIGNAL_EVENT_TYPE__SIGNAL);

		callEventTypeEClass = createEClass(CALL_EVENT_TYPE);
		createEReference(callEventTypeEClass, CALL_EVENT_TYPE__OPERATION);

		attributeEClass = createEClass(ATTRIBUTE);

		booleanAttributeEClass = createEClass(BOOLEAN_ATTRIBUTE);

		integerAttributeEClass = createEClass(INTEGER_ATTRIBUTE);

		stringAttributeEClass = createEClass(STRING_ATTRIBUTE);

		constraintEClass = createEClass(CONSTRAINT);

		booleanConstraintEClass = createEClass(BOOLEAN_CONSTRAINT);
		createEReference(booleanConstraintEClass, BOOLEAN_CONSTRAINT__EXPRESSION);

		integerConstraintEClass = createEClass(INTEGER_CONSTRAINT);
		createEReference(integerConstraintEClass, INTEGER_CONSTRAINT__EXPRESSION);

		stringConstraintEClass = createEClass(STRING_CONSTRAINT);
		createEReference(stringConstraintEClass, STRING_CONSTRAINT__EXPRESSION);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		stateMachineEClass = createEClass(STATE_MACHINE);
		createEReference(stateMachineEClass, STATE_MACHINE__REGIONS);

		regionEClass = createEClass(REGION);
		createEReference(regionEClass, REGION__VERTICE);
		createEReference(regionEClass, REGION__TRANSITIONS);
		createEReference(regionEClass, REGION__STATE_MACHINE);
		createEReference(regionEClass, REGION__STATE);
		createEReference(regionEClass, REGION__CURRENT_VERTEX);

		vertexEClass = createEClass(VERTEX);
		createEReference(vertexEClass, VERTEX__CONTAINER);
		createEReference(vertexEClass, VERTEX__OUTGOING_TRANSITIONS);
		createEReference(vertexEClass, VERTEX__INCOMING_TRANSITIONS);

		pseudostateEClass = createEClass(PSEUDOSTATE);
		createEAttribute(pseudostateEClass, PSEUDOSTATE__KIND);
		createEReference(pseudostateEClass, PSEUDOSTATE__STATE);

		stateEClass = createEClass(STATE);
		createEReference(stateEClass, STATE__REGIONS);
		createEReference(stateEClass, STATE__ENTRY);
		createEReference(stateEClass, STATE__DO_ACTIVITY);
		createEReference(stateEClass, STATE__EXIT);
		createEReference(stateEClass, STATE__DEFERRABLE_TRIGGERS);
		createEReference(stateEClass, STATE__CONNECTION_POINT);

		finalStateEClass = createEClass(FINAL_STATE);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__SOURCE);
		createEReference(transitionEClass, TRANSITION__TARGET);
		createEReference(transitionEClass, TRANSITION__TRIGGERS);
		createEReference(transitionEClass, TRANSITION__CONTAINER);
		createEAttribute(transitionEClass, TRANSITION__KIND);
		createEReference(transitionEClass, TRANSITION__EFFECT);
		createEReference(transitionEClass, TRANSITION__CONSTRAINT);

		triggerEClass = createEClass(TRIGGER);
		createEReference(triggerEClass, TRIGGER__EVENT_TYPE);

		behaviorEClass = createEClass(BEHAVIOR);
		createEReference(behaviorEClass, BEHAVIOR__EMITTED_SIGNALS);

		operationBehaviorEClass = createEClass(OPERATION_BEHAVIOR);
		createEReference(operationBehaviorEClass, OPERATION_BEHAVIOR__ATTRIBUTE_VALUES);

		attributeValueEClass = createEClass(ATTRIBUTE_VALUE);

		booleanAttributeValueEClass = createEClass(BOOLEAN_ATTRIBUTE_VALUE);
		createEReference(booleanAttributeValueEClass, BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE);
		createEAttribute(booleanAttributeValueEClass, BOOLEAN_ATTRIBUTE_VALUE__VALUE);

		integerAttributeValueEClass = createEClass(INTEGER_ATTRIBUTE_VALUE);
		createEReference(integerAttributeValueEClass, INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE);
		createEAttribute(integerAttributeValueEClass, INTEGER_ATTRIBUTE_VALUE__VALUE);

		stringAttributeValueEClass = createEClass(STRING_ATTRIBUTE_VALUE);
		createEReference(stringAttributeValueEClass, STRING_ATTRIBUTE_VALUE__ATTRIBUTE);
		createEAttribute(stringAttributeValueEClass, STRING_ATTRIBUTE_VALUE__VALUE);

		expressionEClass = createEClass(EXPRESSION);

		integerComparisonExpressionEClass = createEClass(INTEGER_COMPARISON_EXPRESSION);
		createEAttribute(integerComparisonExpressionEClass, INTEGER_COMPARISON_EXPRESSION__OPERATOR);
		createEReference(integerComparisonExpressionEClass, INTEGER_COMPARISON_EXPRESSION__OPERAND1);
		createEReference(integerComparisonExpressionEClass, INTEGER_COMPARISON_EXPRESSION__OPERAND2);

		stringComparisonExpressionEClass = createEClass(STRING_COMPARISON_EXPRESSION);
		createEAttribute(stringComparisonExpressionEClass, STRING_COMPARISON_EXPRESSION__OPERATOR);
		createEReference(stringComparisonExpressionEClass, STRING_COMPARISON_EXPRESSION__OPERAND1);
		createEReference(stringComparisonExpressionEClass, STRING_COMPARISON_EXPRESSION__OPERAND2);

		booleanExpressionEClass = createEClass(BOOLEAN_EXPRESSION);

		booleanBinaryExpressionEClass = createEClass(BOOLEAN_BINARY_EXPRESSION);
		createEAttribute(booleanBinaryExpressionEClass, BOOLEAN_BINARY_EXPRESSION__OPERATOR);
		createEReference(booleanBinaryExpressionEClass, BOOLEAN_BINARY_EXPRESSION__OPERAND1);
		createEReference(booleanBinaryExpressionEClass, BOOLEAN_BINARY_EXPRESSION__OPERAND2);

		booleanUnaryExpressionEClass = createEClass(BOOLEAN_UNARY_EXPRESSION);
		createEAttribute(booleanUnaryExpressionEClass, BOOLEAN_UNARY_EXPRESSION__OPERATOR);
		createEReference(booleanUnaryExpressionEClass, BOOLEAN_UNARY_EXPRESSION__OPERAND);

		eventOccurrenceEClass = createEClass(EVENT_OCCURRENCE);

		completionEventOccurrenceEClass = createEClass(COMPLETION_EVENT_OCCURRENCE);
		createEReference(completionEventOccurrenceEClass, COMPLETION_EVENT_OCCURRENCE__STATE);

		signalEventOccurrenceEClass = createEClass(SIGNAL_EVENT_OCCURRENCE);
		createEReference(signalEventOccurrenceEClass, SIGNAL_EVENT_OCCURRENCE__SIGNAL);
		createEReference(signalEventOccurrenceEClass, SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES);

		callEventOccurrenceEClass = createEClass(CALL_EVENT_OCCURRENCE);
		createEReference(callEventOccurrenceEClass, CALL_EVENT_OCCURRENCE__OPERATION);
		createEReference(callEventOccurrenceEClass, CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES);
		createEReference(callEventOccurrenceEClass, CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES);
		createEReference(callEventOccurrenceEClass, CALL_EVENT_OCCURRENCE__RETURN_VALUE);

		// Create enums
		booleanBinaryOperatorEEnum = createEEnum(BOOLEAN_BINARY_OPERATOR);
		booleanUnaryOperatorEEnum = createEEnum(BOOLEAN_UNARY_OPERATOR);
		integerComparisonOperatorEEnum = createEEnum(INTEGER_COMPARISON_OPERATOR);
		stringComparisonOperatorEEnum = createEEnum(STRING_COMPARISON_OPERATOR);
		pseudostateKindEEnum = createEEnum(PSEUDOSTATE_KIND);
		transitionKindEEnum = createEEnum(TRANSITION_KIND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		signalEClass.getESuperTypes().add(this.getNamedElement());
		operationEClass.getESuperTypes().add(this.getNamedElement());
		signalEventTypeEClass.getESuperTypes().add(this.getEventType());
		callEventTypeEClass.getESuperTypes().add(this.getEventType());
		attributeEClass.getESuperTypes().add(this.getNamedElement());
		booleanAttributeEClass.getESuperTypes().add(this.getAttribute());
		integerAttributeEClass.getESuperTypes().add(this.getAttribute());
		stringAttributeEClass.getESuperTypes().add(this.getAttribute());
		booleanConstraintEClass.getESuperTypes().add(this.getConstraint());
		integerConstraintEClass.getESuperTypes().add(this.getConstraint());
		stringConstraintEClass.getESuperTypes().add(this.getConstraint());
		stateMachineEClass.getESuperTypes().add(this.getNamedElement());
		regionEClass.getESuperTypes().add(this.getNamedElement());
		vertexEClass.getESuperTypes().add(this.getNamedElement());
		pseudostateEClass.getESuperTypes().add(this.getVertex());
		stateEClass.getESuperTypes().add(this.getVertex());
		finalStateEClass.getESuperTypes().add(this.getState());
		transitionEClass.getESuperTypes().add(this.getNamedElement());
		triggerEClass.getESuperTypes().add(this.getNamedElement());
		behaviorEClass.getESuperTypes().add(this.getNamedElement());
		operationBehaviorEClass.getESuperTypes().add(this.getBehavior());
		booleanAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		integerAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		stringAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		integerComparisonExpressionEClass.getESuperTypes().add(this.getExpression());
		stringComparisonExpressionEClass.getESuperTypes().add(this.getExpression());
		booleanExpressionEClass.getESuperTypes().add(this.getExpression());
		booleanBinaryExpressionEClass.getESuperTypes().add(this.getBooleanExpression());
		booleanUnaryExpressionEClass.getESuperTypes().add(this.getBooleanExpression());
		signalEventOccurrenceEClass.getESuperTypes().add(this.getEventOccurrence());
		callEventOccurrenceEClass.getESuperTypes().add(this.getEventOccurrence());

		// Initialize classes, features, and operations; add parameters
		initEClass(customSystemEClass, CustomSystem.class, "CustomSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCustomSystem_Statemachine(), this.getStateMachine(), null, "statemachine", null, 1, 1, CustomSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCustomSystem_Signals(), this.getSignal(), null, "signals", null, 0, -1, CustomSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCustomSystem_Operations(), this.getOperation(), null, "operations", null, 0, -1, CustomSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signalEClass, Signal.class, "Signal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSignal_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, Signal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperation_InParameters(), this.getAttribute(), null, "inParameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_OutParameters(), this.getAttribute(), null, "outParameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_Return(), this.getAttribute(), null, "return", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventTypeEClass, EventType.class, "EventType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(signalEventTypeEClass, SignalEventType.class, "SignalEventType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSignalEventType_Signal(), this.getSignal(), null, "signal", null, 1, 1, SignalEventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callEventTypeEClass, CallEventType.class, "CallEventType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCallEventType_Operation(), this.getOperation(), null, "operation", null, 1, 1, CallEventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanAttributeEClass, BooleanAttribute.class, "BooleanAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(integerAttributeEClass, IntegerAttribute.class, "IntegerAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringAttributeEClass, StringAttribute.class, "StringAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constraintEClass, Constraint.class, "Constraint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanConstraintEClass, BooleanConstraint.class, "BooleanConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBooleanConstraint_Expression(), this.getBooleanExpression(), null, "expression", null, 1, 1, BooleanConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerConstraintEClass, IntegerConstraint.class, "IntegerConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntegerConstraint_Expression(), this.getIntegerComparisonExpression(), null, "expression", null, 1, 1, IntegerConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringConstraintEClass, StringConstraint.class, "StringConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringConstraint_Expression(), this.getStringComparisonExpression(), null, "expression", null, 1, 1, StringConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateMachineEClass, StateMachine.class, "StateMachine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateMachine_Regions(), this.getRegion(), this.getRegion_StateMachine(), "regions", null, 1, -1, StateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(regionEClass, Region.class, "Region", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRegion_Vertice(), this.getVertex(), this.getVertex_Container(), "vertice", null, 0, -1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRegion_Transitions(), this.getTransition(), this.getTransition_Container(), "transitions", null, 0, -1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRegion_StateMachine(), this.getStateMachine(), this.getStateMachine_Regions(), "stateMachine", null, 0, 1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRegion_State(), this.getState(), this.getState_Regions(), "state", null, 0, 1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRegion_CurrentVertex(), this.getVertex(), null, "currentVertex", null, 0, 1, Region.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexEClass, Vertex.class, "Vertex", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVertex_Container(), this.getRegion(), this.getRegion_Vertice(), "container", null, 0, 1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertex_OutgoingTransitions(), this.getTransition(), this.getTransition_Source(), "outgoingTransitions", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertex_IncomingTransitions(), this.getTransition(), this.getTransition_Target(), "incomingTransitions", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pseudostateEClass, Pseudostate.class, "Pseudostate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPseudostate_Kind(), this.getPseudostateKind(), "kind", null, 1, 1, Pseudostate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPseudostate_State(), this.getState(), this.getState_ConnectionPoint(), "state", null, 0, 1, Pseudostate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getState_Regions(), this.getRegion(), this.getRegion_State(), "regions", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_Entry(), this.getBehavior(), null, "entry", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_DoActivity(), this.getBehavior(), null, "doActivity", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_Exit(), this.getBehavior(), null, "exit", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_DeferrableTriggers(), this.getTrigger(), null, "deferrableTriggers", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_ConnectionPoint(), this.getPseudostate(), this.getPseudostate_State(), "connectionPoint", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(finalStateEClass, FinalState.class, "FinalState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransition_Source(), this.getVertex(), this.getVertex_OutgoingTransitions(), "source", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Target(), this.getVertex(), this.getVertex_IncomingTransitions(), "target", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Triggers(), this.getTrigger(), null, "triggers", null, 0, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Container(), this.getRegion(), this.getRegion_Transitions(), "container", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Kind(), this.getTransitionKind(), "kind", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Effect(), this.getBehavior(), null, "effect", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Constraint(), this.getConstraint(), null, "constraint", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(triggerEClass, Trigger.class, "Trigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrigger_EventType(), this.getEventType(), null, "eventType", null, 1, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(behaviorEClass, Behavior.class, "Behavior", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehavior_EmittedSignals(), this.getSignalEventOccurrence(), null, "emittedSignals", null, 0, -1, Behavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationBehaviorEClass, OperationBehavior.class, "OperationBehavior", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationBehavior_AttributeValues(), this.getAttributeValue(), null, "attributeValues", null, 0, -1, OperationBehavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeValueEClass, AttributeValue.class, "AttributeValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanAttributeValueEClass, BooleanAttributeValue.class, "BooleanAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBooleanAttributeValue_Attribute(), this.getBooleanAttribute(), null, "attribute", null, 0, 1, BooleanAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBooleanAttributeValue_Value(), ecorePackage.getEBooleanObject(), "value", null, 0, 1, BooleanAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerAttributeValueEClass, IntegerAttributeValue.class, "IntegerAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntegerAttributeValue_Attribute(), this.getIntegerAttribute(), null, "attribute", null, 0, 1, IntegerAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntegerAttributeValue_Value(), ecorePackage.getEIntegerObject(), "value", null, 0, 1, IntegerAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringAttributeValueEClass, StringAttributeValue.class, "StringAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringAttributeValue_Attribute(), this.getStringAttribute(), null, "attribute", null, 0, 1, StringAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringAttributeValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, StringAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(integerComparisonExpressionEClass, IntegerComparisonExpression.class, "IntegerComparisonExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerComparisonExpression_Operator(), this.getIntegerComparisonOperator(), "operator", null, 1, 1, IntegerComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntegerComparisonExpression_Operand1(), this.getIntegerAttribute(), null, "operand1", null, 1, 1, IntegerComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntegerComparisonExpression_Operand2(), this.getIntegerAttribute(), null, "operand2", null, 1, 1, IntegerComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringComparisonExpressionEClass, StringComparisonExpression.class, "StringComparisonExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringComparisonExpression_Operator(), this.getStringComparisonOperator(), "operator", null, 1, 1, StringComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringComparisonExpression_Operand1(), this.getStringAttribute(), null, "operand1", null, 1, 1, StringComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringComparisonExpression_Operand2(), this.getStringAttribute(), null, "operand2", null, 1, 1, StringComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanExpressionEClass, BooleanExpression.class, "BooleanExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanBinaryExpressionEClass, BooleanBinaryExpression.class, "BooleanBinaryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanBinaryExpression_Operator(), this.getBooleanBinaryOperator(), "operator", null, 1, 1, BooleanBinaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBooleanBinaryExpression_Operand1(), this.getBooleanAttribute(), null, "operand1", null, 1, 1, BooleanBinaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBooleanBinaryExpression_Operand2(), this.getBooleanAttribute(), null, "operand2", null, 1, 1, BooleanBinaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanUnaryExpressionEClass, BooleanUnaryExpression.class, "BooleanUnaryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanUnaryExpression_Operator(), this.getBooleanUnaryOperator(), "operator", null, 1, 1, BooleanUnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBooleanUnaryExpression_Operand(), this.getBooleanAttribute(), null, "operand", null, 1, 1, BooleanUnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventOccurrenceEClass, EventOccurrence.class, "EventOccurrence", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(completionEventOccurrenceEClass, CompletionEventOccurrence.class, "CompletionEventOccurrence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompletionEventOccurrence_State(), this.getState(), null, "state", null, 0, 1, CompletionEventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signalEventOccurrenceEClass, SignalEventOccurrence.class, "SignalEventOccurrence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSignalEventOccurrence_Signal(), this.getSignal(), null, "signal", null, 1, 1, SignalEventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSignalEventOccurrence_AttributeValues(), this.getAttributeValue(), null, "attributeValues", null, 0, -1, SignalEventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callEventOccurrenceEClass, CallEventOccurrence.class, "CallEventOccurrence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCallEventOccurrence_Operation(), this.getOperation(), null, "operation", null, 1, 1, CallEventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCallEventOccurrence_InParameterValues(), this.getAttributeValue(), null, "inParameterValues", null, 0, -1, CallEventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCallEventOccurrence_OutParameterValues(), this.getAttributeValue(), null, "outParameterValues", null, 0, -1, CallEventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCallEventOccurrence_ReturnValue(), this.getAttributeValue(), null, "returnValue", null, 0, 1, CallEventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(booleanBinaryOperatorEEnum, BooleanBinaryOperator.class, "BooleanBinaryOperator");
		addEEnumLiteral(booleanBinaryOperatorEEnum, BooleanBinaryOperator.AND);
		addEEnumLiteral(booleanBinaryOperatorEEnum, BooleanBinaryOperator.OR);

		initEEnum(booleanUnaryOperatorEEnum, BooleanUnaryOperator.class, "BooleanUnaryOperator");
		addEEnumLiteral(booleanUnaryOperatorEEnum, BooleanUnaryOperator.TRUE);
		addEEnumLiteral(booleanUnaryOperatorEEnum, BooleanUnaryOperator.FALSE);

		initEEnum(integerComparisonOperatorEEnum, IntegerComparisonOperator.class, "IntegerComparisonOperator");
		addEEnumLiteral(integerComparisonOperatorEEnum, IntegerComparisonOperator.SMALLER);
		addEEnumLiteral(integerComparisonOperatorEEnum, IntegerComparisonOperator.SMALLER_EQUALS);
		addEEnumLiteral(integerComparisonOperatorEEnum, IntegerComparisonOperator.EQUALS);
		addEEnumLiteral(integerComparisonOperatorEEnum, IntegerComparisonOperator.NOT_EQUALS);
		addEEnumLiteral(integerComparisonOperatorEEnum, IntegerComparisonOperator.GREATER_EQUALS);
		addEEnumLiteral(integerComparisonOperatorEEnum, IntegerComparisonOperator.GREATER);

		initEEnum(stringComparisonOperatorEEnum, StringComparisonOperator.class, "StringComparisonOperator");
		addEEnumLiteral(stringComparisonOperatorEEnum, StringComparisonOperator.EQUALS);
		addEEnumLiteral(stringComparisonOperatorEEnum, StringComparisonOperator.NOT_EQUALS);

		initEEnum(pseudostateKindEEnum, PseudostateKind.class, "PseudostateKind");
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.INITIAL);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.JOIN);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.FORK);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.TERMINATE);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.ENTRYPOINT);
		addEEnumLiteral(pseudostateKindEEnum, PseudostateKind.EXITPOINT);

		initEEnum(transitionKindEEnum, TransitionKind.class, "TransitionKind");
		addEEnumLiteral(transitionKindEEnum, TransitionKind.INTERNAL);
		addEEnumLiteral(transitionKindEEnum, TransitionKind.LOCAL);
		addEEnumLiteral(transitionKindEEnum, TransitionKind.EXTERNAL);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// aspect
		createAspectAnnotations();
	}

	/**
	 * Initializes the annotations for <b>aspect</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createAspectAnnotations() {
		String source = "aspect";
		addAnnotation
		  (getRegion_CurrentVertex(),
		   source,
		   new String[] {
		   });
		addAnnotation
		  (attributeValueEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (booleanAttributeValueEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (integerAttributeValueEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (stringAttributeValueEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (eventOccurrenceEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (completionEventOccurrenceEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (signalEventOccurrenceEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (getSignalEventOccurrence_AttributeValues(),
		   source,
		   new String[] {
		   });
		addAnnotation
		  (callEventOccurrenceEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (getCallEventOccurrence_InParameterValues(),
		   source,
		   new String[] {
		   });
		addAnnotation
		  (getCallEventOccurrence_OutParameterValues(),
		   source,
		   new String[] {
		   });
		addAnnotation
		  (getCallEventOccurrence_ReturnValue(),
		   source,
		   new String[] {
		   });
	}

} //StatemachinesPackageImpl
