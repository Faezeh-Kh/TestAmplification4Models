/**
 */
package org.imt.pssm.reactive.model.statemachines;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.CustomSystem#getStatemachine <em>Statemachine</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.CustomSystem#getSignals <em>Signals</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.CustomSystem#getOperations <em>Operations</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getCustomSystem()
 * @model
 * @generated
 */
public interface CustomSystem extends EObject {
	/**
	 * Returns the value of the '<em><b>Statemachine</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statemachine</em>' containment reference.
	 * @see #setStatemachine(StateMachine)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getCustomSystem_Statemachine()
	 * @model containment="true" required="true"
	 * @generated
	 */
	StateMachine getStatemachine();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.CustomSystem#getStatemachine <em>Statemachine</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statemachine</em>' containment reference.
	 * @see #getStatemachine()
	 * @generated
	 */
	void setStatemachine(StateMachine value);

	/**
	 * Returns the value of the '<em><b>Signals</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.Signal}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signals</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getCustomSystem_Signals()
	 * @model containment="true"
	 * @generated
	 */
	EList<Signal> getSignals();

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.Operation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getCustomSystem_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

} // CustomSystem
