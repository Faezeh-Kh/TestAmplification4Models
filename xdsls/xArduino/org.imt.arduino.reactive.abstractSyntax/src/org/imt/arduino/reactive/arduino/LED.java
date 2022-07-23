/**
 */
package org.imt.arduino.reactive.arduino;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>LED</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.arduino.reactive.arduino.LED#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getLED()
 * @model
 * @generated
 */
public interface LED extends ArduinoDigitalModule {
	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * The default value is <code>"blue"</code>.
	 * The literals are from the enumeration {@link org.imt.arduino.reactive.arduino.Color}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see org.imt.arduino.reactive.arduino.Color
	 * @see #setColor(Color)
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getLED_Color()
	 * @model default="blue" required="true"
	 * @generated
	 */
	Color getColor();

	/**
	 * Sets the value of the '{@link org.imt.arduino.reactive.arduino.LED#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see org.imt.arduino.reactive.arduino.Color
	 * @see #getColor()
	 * @generated
	 */
	void setColor(Color value);

} // LED
