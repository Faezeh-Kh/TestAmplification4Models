/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerAttributeValue()
 * @model annotation="aspect"
 * @generated
 */
public interface IntegerAttributeValue extends AttributeValue {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' reference.
	 * @see #setAttribute(IntegerAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerAttributeValue_Attribute()
	 * @model
	 * @generated
	 */
	IntegerAttribute getAttribute();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue#getAttribute <em>Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' reference.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(IntegerAttribute value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(Integer)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerAttributeValue_Value()
	 * @model
	 * @generated
	 */
	Integer getValue();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Integer value);

} // IntegerAttributeValue
