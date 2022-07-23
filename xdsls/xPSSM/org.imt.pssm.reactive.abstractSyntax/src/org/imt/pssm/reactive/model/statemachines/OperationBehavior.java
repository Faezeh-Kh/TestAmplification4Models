/**
 */
package org.imt.pssm.reactive.model.statemachines;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.OperationBehavior#getAttributeValues <em>Attribute Values</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getOperationBehavior()
 * @model
 * @generated
 */
public interface OperationBehavior extends Behavior {
	/**
	 * Returns the value of the '<em><b>Attribute Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.pssm.reactive.model.statemachines.AttributeValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Values</em>' containment reference list.
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getOperationBehavior_AttributeValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<AttributeValue> getAttributeValues();

} // OperationBehavior
