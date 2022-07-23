/**
 */
package org.imt.pssm.reactive.model.statemachines;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Completion Event Occurrence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence#getState <em>State</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getCompletionEventOccurrence()
 * @model annotation="aspect"
 * @generated
 */
public interface CompletionEventOccurrence extends EObject {
	/**
	 * Returns the value of the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference.
	 * @see #setState(State)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getCompletionEventOccurrence_State()
	 * @model
	 * @generated
	 */
	State getState();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence#getState <em>State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(State value);

} // CompletionEventOccurrence
