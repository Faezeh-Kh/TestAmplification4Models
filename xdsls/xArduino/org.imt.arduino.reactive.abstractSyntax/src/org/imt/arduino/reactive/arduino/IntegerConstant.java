/**
 */
package org.imt.arduino.reactive.arduino;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Constant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.arduino.reactive.arduino.IntegerConstant#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getIntegerConstant()
 * @model
 * @generated
 */
public interface IntegerConstant extends Constant, IntegerExpression {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(int)
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getIntegerConstant_Value()
	 * @model default="0"
	 * @generated
	 */
	int getValue();

	/**
	 * Sets the value of the '{@link org.imt.arduino.reactive.arduino.IntegerConstant#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(int value);

} // IntegerConstant
