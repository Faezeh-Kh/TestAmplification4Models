/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanAttributeValue()
 * @model annotation="aspect"
 * @generated
 */
public interface BooleanAttributeValue extends AttributeValue {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' reference.
	 * @see #setAttribute(BooleanAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanAttributeValue_Attribute()
	 * @model
	 * @generated
	 */
	BooleanAttribute getAttribute();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue#getAttribute <em>Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' reference.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(BooleanAttribute value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(Boolean)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanAttributeValue_Value()
	 * @model
	 * @generated
	 */
	Boolean getValue();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Boolean value);

} // BooleanAttributeValue
