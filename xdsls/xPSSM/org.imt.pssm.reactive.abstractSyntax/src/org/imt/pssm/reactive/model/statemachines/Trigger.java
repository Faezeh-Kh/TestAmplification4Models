/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.Trigger#getEventType <em>Event Type</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getTrigger()
 * @model
 * @generated
 */
public interface Trigger extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Event Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Type</em>' containment reference.
	 * @see #setEventType(EventType)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getTrigger_EventType()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EventType getEventType();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.Trigger#getEventType <em>Event Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Type</em>' containment reference.
	 * @see #getEventType()
	 * @generated
	 */
	void setEventType(EventType value);

} // Trigger
