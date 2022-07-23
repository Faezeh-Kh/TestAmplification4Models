/**
 */
package org.imt.arduino.reactive.arduino;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repeat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.arduino.reactive.arduino.Repeat#getIteration <em>Iteration</em>}</li>
 * </ul>
 *
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getRepeat()
 * @model
 * @generated
 */
public interface Repeat extends Control {
	/**
	 * Returns the value of the '<em><b>Iteration</b></em>' attribute.
	 * The default value is <code>"5"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iteration</em>' attribute.
	 * @see #setIteration(int)
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getRepeat_Iteration()
	 * @model default="5"
	 * @generated
	 */
	int getIteration();

	/**
	 * Sets the value of the '{@link org.imt.arduino.reactive.arduino.Repeat#getIteration <em>Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iteration</em>' attribute.
	 * @see #getIteration()
	 * @generated
	 */
	void setIteration(int value);

} // Repeat
