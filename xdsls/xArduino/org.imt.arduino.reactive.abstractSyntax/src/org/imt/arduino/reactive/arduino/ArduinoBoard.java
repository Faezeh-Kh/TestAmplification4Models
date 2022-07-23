/**
 */
package org.imt.arduino.reactive.arduino;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Board</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.arduino.reactive.arduino.ArduinoBoard#getDigitalPins <em>Digital Pins</em>}</li>
 *   <li>{@link org.imt.arduino.reactive.arduino.ArduinoBoard#getAnalogPins <em>Analog Pins</em>}</li>
 * </ul>
 *
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getArduinoBoard()
 * @model
 * @generated
 */
public interface ArduinoBoard extends Board {
	/**
	 * Returns the value of the '<em><b>Digital Pins</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.arduino.reactive.arduino.DigitalPin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Digital Pins</em>' containment reference list.
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getArduinoBoard_DigitalPins()
	 * @model containment="true"
	 * @generated
	 */
	EList<DigitalPin> getDigitalPins();

	/**
	 * Returns the value of the '<em><b>Analog Pins</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.arduino.reactive.arduino.AnalogPin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Analog Pins</em>' containment reference list.
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getArduinoBoard_AnalogPins()
	 * @model containment="true"
	 * @generated
	 */
	EList<AnalogPin> getAnalogPins();

} // ArduinoBoard
