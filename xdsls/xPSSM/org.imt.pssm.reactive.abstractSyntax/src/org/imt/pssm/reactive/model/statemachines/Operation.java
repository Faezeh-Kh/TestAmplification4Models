/**
 */
package org.imt.pssm.reactive.model.statemachines;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.Operation#getInParameters <em>In Parameters</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.Operation#getOutParameters <em>Out Parameters</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.Operation#getReturn <em>Return</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends NamedElement {
	/**
	 * Returns the value of the '<em><b>In Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.Attribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Parameters</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getOperation_InParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getInParameters();

	/**
	 * Returns the value of the '<em><b>Out Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.Attribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Parameters</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getOperation_OutParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getOutParameters();

	/**
	 * Returns the value of the '<em><b>Return</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return</em>' containment reference.
	 * @see #setReturn(Attribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getOperation_Return()
	 * @model containment="true"
	 * @generated
	 */
	Attribute getReturn();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.Operation#getReturn <em>Return</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return</em>' containment reference.
	 * @see #getReturn()
	 * @generated
	 */
	void setReturn(Attribute value);

} // Operation
