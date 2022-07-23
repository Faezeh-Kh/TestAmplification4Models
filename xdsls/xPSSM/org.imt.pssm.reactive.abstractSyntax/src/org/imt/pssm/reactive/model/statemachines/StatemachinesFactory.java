/**
 */
package org.imt.pssm.reactive.model.statemachines;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage
 * @generated
 */
public interface StatemachinesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatemachinesFactory eINSTANCE = org.imt.pssm.reactive.model.statemachines.impl.StatemachinesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Custom System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom System</em>'.
	 * @generated
	 */
	CustomSystem createCustomSystem();

	/**
	 * Returns a new object of class '<em>Signal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Signal</em>'.
	 * @generated
	 */
	Signal createSignal();

	/**
	 * Returns a new object of class '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation</em>'.
	 * @generated
	 */
	Operation createOperation();

	/**
	 * Returns a new object of class '<em>Signal Event Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Signal Event Type</em>'.
	 * @generated
	 */
	SignalEventType createSignalEventType();

	/**
	 * Returns a new object of class '<em>Call Event Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Call Event Type</em>'.
	 * @generated
	 */
	CallEventType createCallEventType();

	/**
	 * Returns a new object of class '<em>Boolean Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Attribute</em>'.
	 * @generated
	 */
	BooleanAttribute createBooleanAttribute();

	/**
	 * Returns a new object of class '<em>Integer Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Attribute</em>'.
	 * @generated
	 */
	IntegerAttribute createIntegerAttribute();

	/**
	 * Returns a new object of class '<em>String Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Attribute</em>'.
	 * @generated
	 */
	StringAttribute createStringAttribute();

	/**
	 * Returns a new object of class '<em>Boolean Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Constraint</em>'.
	 * @generated
	 */
	BooleanConstraint createBooleanConstraint();

	/**
	 * Returns a new object of class '<em>Integer Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Constraint</em>'.
	 * @generated
	 */
	IntegerConstraint createIntegerConstraint();

	/**
	 * Returns a new object of class '<em>String Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Constraint</em>'.
	 * @generated
	 */
	StringConstraint createStringConstraint();

	/**
	 * Returns a new object of class '<em>State Machine</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Machine</em>'.
	 * @generated
	 */
	StateMachine createStateMachine();

	/**
	 * Returns a new object of class '<em>Region</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Region</em>'.
	 * @generated
	 */
	Region createRegion();

	/**
	 * Returns a new object of class '<em>Pseudostate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pseudostate</em>'.
	 * @generated
	 */
	Pseudostate createPseudostate();

	/**
	 * Returns a new object of class '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State</em>'.
	 * @generated
	 */
	State createState();

	/**
	 * Returns a new object of class '<em>Final State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Final State</em>'.
	 * @generated
	 */
	FinalState createFinalState();

	/**
	 * Returns a new object of class '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition</em>'.
	 * @generated
	 */
	Transition createTransition();

	/**
	 * Returns a new object of class '<em>Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trigger</em>'.
	 * @generated
	 */
	Trigger createTrigger();

	/**
	 * Returns a new object of class '<em>Behavior</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behavior</em>'.
	 * @generated
	 */
	Behavior createBehavior();

	/**
	 * Returns a new object of class '<em>Operation Behavior</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Behavior</em>'.
	 * @generated
	 */
	OperationBehavior createOperationBehavior();

	/**
	 * Returns a new object of class '<em>Boolean Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Attribute Value</em>'.
	 * @generated
	 */
	BooleanAttributeValue createBooleanAttributeValue();

	/**
	 * Returns a new object of class '<em>Integer Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Attribute Value</em>'.
	 * @generated
	 */
	IntegerAttributeValue createIntegerAttributeValue();

	/**
	 * Returns a new object of class '<em>String Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Attribute Value</em>'.
	 * @generated
	 */
	StringAttributeValue createStringAttributeValue();

	/**
	 * Returns a new object of class '<em>Integer Comparison Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Comparison Expression</em>'.
	 * @generated
	 */
	IntegerComparisonExpression createIntegerComparisonExpression();

	/**
	 * Returns a new object of class '<em>String Comparison Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Comparison Expression</em>'.
	 * @generated
	 */
	StringComparisonExpression createStringComparisonExpression();

	/**
	 * Returns a new object of class '<em>Boolean Binary Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Binary Expression</em>'.
	 * @generated
	 */
	BooleanBinaryExpression createBooleanBinaryExpression();

	/**
	 * Returns a new object of class '<em>Boolean Unary Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Unary Expression</em>'.
	 * @generated
	 */
	BooleanUnaryExpression createBooleanUnaryExpression();

	/**
	 * Returns a new object of class '<em>Completion Event Occurrence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Completion Event Occurrence</em>'.
	 * @generated
	 */
	CompletionEventOccurrence createCompletionEventOccurrence();

	/**
	 * Returns a new object of class '<em>Signal Event Occurrence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Signal Event Occurrence</em>'.
	 * @generated
	 */
	SignalEventOccurrence createSignalEventOccurrence();

	/**
	 * Returns a new object of class '<em>Call Event Occurrence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Call Event Occurrence</em>'.
	 * @generated
	 */
	CallEventOccurrence createCallEventOccurrence();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StatemachinesPackage getStatemachinesPackage();

} //StatemachinesFactory
