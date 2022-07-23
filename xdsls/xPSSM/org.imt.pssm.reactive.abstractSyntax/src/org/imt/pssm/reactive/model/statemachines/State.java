/**
 */
package org.imt.pssm.reactive.model.statemachines;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.State#getRegions <em>Regions</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.State#getEntry <em>Entry</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.State#getDoActivity <em>Do Activity</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.State#getExit <em>Exit</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.State#getDeferrableTriggers <em>Deferrable Triggers</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.State#getConnectionPoint <em>Connection Point</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getState()
 * @model
 * @generated
 */
public interface State extends Vertex {
	/**
	 * Returns the value of the '<em><b>Regions</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.Region}.
	 * It is bidirectional and its opposite is '{@link org.imt.pssm.reactive.model.statemachines.Region#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regions</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getState_Regions()
	 * @see org.imt.pssm.reactive.model.statemachines.Region#getState
	 * @model opposite="state" containment="true"
	 * @generated
	 */
	EList<Region> getRegions();

	/**
	 * Returns the value of the '<em><b>Entry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry</em>' containment reference.
	 * @see #setEntry(Behavior)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getState_Entry()
	 * @model containment="true"
	 * @generated
	 */
	Behavior getEntry();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.State#getEntry <em>Entry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry</em>' containment reference.
	 * @see #getEntry()
	 * @generated
	 */
	void setEntry(Behavior value);

	/**
	 * Returns the value of the '<em><b>Do Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Do Activity</em>' containment reference.
	 * @see #setDoActivity(Behavior)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getState_DoActivity()
	 * @model containment="true"
	 * @generated
	 */
	Behavior getDoActivity();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.State#getDoActivity <em>Do Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Do Activity</em>' containment reference.
	 * @see #getDoActivity()
	 * @generated
	 */
	void setDoActivity(Behavior value);

	/**
	 * Returns the value of the '<em><b>Exit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exit</em>' containment reference.
	 * @see #setExit(Behavior)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getState_Exit()
	 * @model containment="true"
	 * @generated
	 */
	Behavior getExit();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.State#getExit <em>Exit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exit</em>' containment reference.
	 * @see #getExit()
	 * @generated
	 */
	void setExit(Behavior value);

	/**
	 * Returns the value of the '<em><b>Deferrable Triggers</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.Trigger}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deferrable Triggers</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getState_DeferrableTriggers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Trigger> getDeferrableTriggers();

	/**
	 * Returns the value of the '<em><b>Connection Point</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.Pseudostate}.
	 * It is bidirectional and its opposite is '{@link org.imt.pssm.reactive.model.statemachines.Pseudostate#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Point</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getState_ConnectionPoint()
	 * @see org.imt.pssm.reactive.model.statemachines.Pseudostate#getState
	 * @model opposite="state" containment="true"
	 * @generated
	 */
	EList<Pseudostate> getConnectionPoint();

} // State
