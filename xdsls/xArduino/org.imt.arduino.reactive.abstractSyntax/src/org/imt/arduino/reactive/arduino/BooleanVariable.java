/**
 */
package org.imt.arduino.reactive.arduino;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.arduino.reactive.arduino.BooleanVariable#isInitialValue <em>Initial Value</em>}</li>
 * </ul>
 *
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getBooleanVariable()
 * @model
 * @generated
 */
public interface BooleanVariable extends Variable {
	/**
	 * Returns the value of the '<em><b>Initial Value</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Value</em>' attribute.
	 * @see #setInitialValue(boolean)
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getBooleanVariable_InitialValue()
	 * @model default="false"
	 * @generated
	 */
	boolean isInitialValue();

	/**
	 * Sets the value of the '{@link org.imt.arduino.reactive.arduino.BooleanVariable#isInitialValue <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Value</em>' attribute.
	 * @see #isInitialValue()
	 * @generated
	 */
	void setInitialValue(boolean value);

} // BooleanVariable
