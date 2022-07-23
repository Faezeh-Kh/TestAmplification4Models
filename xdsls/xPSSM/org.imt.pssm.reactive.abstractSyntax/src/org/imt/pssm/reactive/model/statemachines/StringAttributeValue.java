/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.StringAttributeValue#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.StringAttributeValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringAttributeValue()
 * @model annotation="aspect"
 * @generated
 */
public interface StringAttributeValue extends AttributeValue {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' reference.
	 * @see #setAttribute(StringAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringAttributeValue_Attribute()
	 * @model
	 * @generated
	 */
	StringAttribute getAttribute();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.StringAttributeValue#getAttribute <em>Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' reference.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(StringAttribute value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringAttributeValue_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.StringAttributeValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // StringAttributeValue
