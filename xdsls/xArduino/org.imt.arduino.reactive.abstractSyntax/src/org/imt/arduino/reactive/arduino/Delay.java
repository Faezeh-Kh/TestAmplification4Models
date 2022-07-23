/**
 */
package org.imt.arduino.reactive.arduino;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delay</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.arduino.reactive.arduino.Delay#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.imt.arduino.reactive.arduino.Delay#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getDelay()
 * @model
 * @generated
 */
public interface Delay extends Utilities {
	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link org.imt.arduino.reactive.arduino.Time}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see org.imt.arduino.reactive.arduino.Time
	 * @see #setUnit(Time)
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getDelay_Unit()
	 * @model
	 * @generated
	 */
	Time getUnit();

	/**
	 * Sets the value of the '{@link org.imt.arduino.reactive.arduino.Delay#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see org.imt.arduino.reactive.arduino.Time
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(Time value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(int)
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getDelay_Value()
	 * @model
	 * @generated
	 */
	int getValue();

	/**
	 * Sets the value of the '{@link org.imt.arduino.reactive.arduino.Delay#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(int value);

} // Delay
