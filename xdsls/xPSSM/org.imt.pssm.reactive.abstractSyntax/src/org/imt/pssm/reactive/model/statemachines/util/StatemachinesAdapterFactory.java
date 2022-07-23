/**
 */
package org.imt.pssm.reactive.model.statemachines.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.imt.pssm.reactive.model.statemachines.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage
 * @generated
 */
public class StatemachinesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StatemachinesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachinesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StatemachinesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StatemachinesSwitch<Adapter> modelSwitch =
		new StatemachinesSwitch<Adapter>() {
			@Override
			public Adapter caseCustomSystem(CustomSystem object) {
				return createCustomSystemAdapter();
			}
			@Override
			public Adapter caseSignal(Signal object) {
				return createSignalAdapter();
			}
			@Override
			public Adapter caseOperation(Operation object) {
				return createOperationAdapter();
			}
			@Override
			public Adapter caseEventType(EventType object) {
				return createEventTypeAdapter();
			}
			@Override
			public Adapter caseSignalEventType(SignalEventType object) {
				return createSignalEventTypeAdapter();
			}
			@Override
			public Adapter caseCallEventType(CallEventType object) {
				return createCallEventTypeAdapter();
			}
			@Override
			public Adapter caseAttribute(Attribute object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter caseBooleanAttribute(BooleanAttribute object) {
				return createBooleanAttributeAdapter();
			}
			@Override
			public Adapter caseIntegerAttribute(IntegerAttribute object) {
				return createIntegerAttributeAdapter();
			}
			@Override
			public Adapter caseStringAttribute(StringAttribute object) {
				return createStringAttributeAdapter();
			}
			@Override
			public Adapter caseConstraint(Constraint object) {
				return createConstraintAdapter();
			}
			@Override
			public Adapter caseBooleanConstraint(BooleanConstraint object) {
				return createBooleanConstraintAdapter();
			}
			@Override
			public Adapter caseIntegerConstraint(IntegerConstraint object) {
				return createIntegerConstraintAdapter();
			}
			@Override
			public Adapter caseStringConstraint(StringConstraint object) {
				return createStringConstraintAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseStateMachine(StateMachine object) {
				return createStateMachineAdapter();
			}
			@Override
			public Adapter caseRegion(Region object) {
				return createRegionAdapter();
			}
			@Override
			public Adapter caseVertex(Vertex object) {
				return createVertexAdapter();
			}
			@Override
			public Adapter casePseudostate(Pseudostate object) {
				return createPseudostateAdapter();
			}
			@Override
			public Adapter caseState(State object) {
				return createStateAdapter();
			}
			@Override
			public Adapter caseFinalState(FinalState object) {
				return createFinalStateAdapter();
			}
			@Override
			public Adapter caseTransition(Transition object) {
				return createTransitionAdapter();
			}
			@Override
			public Adapter caseTrigger(Trigger object) {
				return createTriggerAdapter();
			}
			@Override
			public Adapter caseBehavior(Behavior object) {
				return createBehaviorAdapter();
			}
			@Override
			public Adapter caseOperationBehavior(OperationBehavior object) {
				return createOperationBehaviorAdapter();
			}
			@Override
			public Adapter caseAttributeValue(AttributeValue object) {
				return createAttributeValueAdapter();
			}
			@Override
			public Adapter caseBooleanAttributeValue(BooleanAttributeValue object) {
				return createBooleanAttributeValueAdapter();
			}
			@Override
			public Adapter caseIntegerAttributeValue(IntegerAttributeValue object) {
				return createIntegerAttributeValueAdapter();
			}
			@Override
			public Adapter caseStringAttributeValue(StringAttributeValue object) {
				return createStringAttributeValueAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseIntegerComparisonExpression(IntegerComparisonExpression object) {
				return createIntegerComparisonExpressionAdapter();
			}
			@Override
			public Adapter caseStringComparisonExpression(StringComparisonExpression object) {
				return createStringComparisonExpressionAdapter();
			}
			@Override
			public Adapter caseBooleanExpression(BooleanExpression object) {
				return createBooleanExpressionAdapter();
			}
			@Override
			public Adapter caseBooleanBinaryExpression(BooleanBinaryExpression object) {
				return createBooleanBinaryExpressionAdapter();
			}
			@Override
			public Adapter caseBooleanUnaryExpression(BooleanUnaryExpression object) {
				return createBooleanUnaryExpressionAdapter();
			}
			@Override
			public Adapter caseEventOccurrence(EventOccurrence object) {
				return createEventOccurrenceAdapter();
			}
			@Override
			public Adapter caseCompletionEventOccurrence(CompletionEventOccurrence object) {
				return createCompletionEventOccurrenceAdapter();
			}
			@Override
			public Adapter caseSignalEventOccurrence(SignalEventOccurrence object) {
				return createSignalEventOccurrenceAdapter();
			}
			@Override
			public Adapter caseCallEventOccurrence(CallEventOccurrence object) {
				return createCallEventOccurrenceAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.CustomSystem <em>Custom System</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.CustomSystem
	 * @generated
	 */
	public Adapter createCustomSystemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Signal
	 * @generated
	 */
	public Adapter createSignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.EventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.EventType
	 * @generated
	 */
	public Adapter createEventTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.SignalEventType <em>Signal Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.SignalEventType
	 * @generated
	 */
	public Adapter createSignalEventTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.CallEventType <em>Call Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventType
	 * @generated
	 */
	public Adapter createCallEventTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.BooleanAttribute <em>Boolean Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanAttribute
	 * @generated
	 */
	public Adapter createBooleanAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.IntegerAttribute <em>Integer Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerAttribute
	 * @generated
	 */
	public Adapter createIntegerAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.StringAttribute <em>String Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.StringAttribute
	 * @generated
	 */
	public Adapter createStringAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Constraint
	 * @generated
	 */
	public Adapter createConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.BooleanConstraint <em>Boolean Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanConstraint
	 * @generated
	 */
	public Adapter createBooleanConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.IntegerConstraint <em>Integer Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerConstraint
	 * @generated
	 */
	public Adapter createIntegerConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.StringConstraint <em>String Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.StringConstraint
	 * @generated
	 */
	public Adapter createStringConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.StateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.StateMachine
	 * @generated
	 */
	public Adapter createStateMachineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Region <em>Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Region
	 * @generated
	 */
	public Adapter createRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Vertex
	 * @generated
	 */
	public Adapter createVertexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Pseudostate <em>Pseudostate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Pseudostate
	 * @generated
	 */
	public Adapter createPseudostateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.FinalState <em>Final State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.FinalState
	 * @generated
	 */
	public Adapter createFinalStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition
	 * @generated
	 */
	public Adapter createTransitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Trigger
	 * @generated
	 */
	public Adapter createTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Behavior
	 * @generated
	 */
	public Adapter createBehaviorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.OperationBehavior <em>Operation Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.OperationBehavior
	 * @generated
	 */
	public Adapter createOperationBehaviorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.AttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.AttributeValue
	 * @generated
	 */
	public Adapter createAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue <em>Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue
	 * @generated
	 */
	public Adapter createBooleanAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue <em>Integer Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue
	 * @generated
	 */
	public Adapter createIntegerAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.StringAttributeValue <em>String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.StringAttributeValue
	 * @generated
	 */
	public Adapter createStringAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression <em>Integer Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression
	 * @generated
	 */
	public Adapter createIntegerComparisonExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression <em>String Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonExpression
	 * @generated
	 */
	public Adapter createStringComparisonExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.BooleanExpression <em>Boolean Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanExpression
	 * @generated
	 */
	public Adapter createBooleanExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression <em>Boolean Binary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression
	 * @generated
	 */
	public Adapter createBooleanBinaryExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression <em>Boolean Unary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression
	 * @generated
	 */
	public Adapter createBooleanUnaryExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.EventOccurrence <em>Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.EventOccurrence
	 * @generated
	 */
	public Adapter createEventOccurrenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence <em>Completion Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence
	 * @generated
	 */
	public Adapter createCompletionEventOccurrenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence <em>Signal Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence
	 * @generated
	 */
	public Adapter createSignalEventOccurrenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.imt.pssm.reactive.model.statemachines.CallEventOccurrence <em>Call Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventOccurrence
	 * @generated
	 */
	public Adapter createCallEventOccurrenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //StatemachinesAdapterFactory
