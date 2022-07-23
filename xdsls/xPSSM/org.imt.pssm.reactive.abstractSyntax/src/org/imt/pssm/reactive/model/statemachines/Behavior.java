/**
 */
package org.imt.pssm.reactive.model.statemachines;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.Behavior#getEmittedSignals <em>Emitted Signals</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBehavior()
 * @model
 * @generated
 */
public interface Behavior extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Emitted Signals</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emitted Signals</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBehavior_EmittedSignals()
	 * @model containment="true"
	 * @generated
	 */
	EList<SignalEventOccurrence> getEmittedSignals();

} // Behavior
