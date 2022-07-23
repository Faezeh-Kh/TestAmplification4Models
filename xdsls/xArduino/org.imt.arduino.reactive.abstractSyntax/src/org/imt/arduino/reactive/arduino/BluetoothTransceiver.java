/**
 */
package org.imt.arduino.reactive.arduino;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bluetooth Transceiver</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.arduino.reactive.arduino.BluetoothTransceiver#getConnectedTransceiver <em>Connected Transceiver</em>}</li>
 * </ul>
 *
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getBluetoothTransceiver()
 * @model
 * @generated
 */
public interface BluetoothTransceiver extends ArduinoAnalogModule {
	/**
	 * Returns the value of the '<em><b>Connected Transceiver</b></em>' reference list.
	 * The list contents are of type {@link org.imt.arduino.reactive.arduino.BluetoothTransceiver}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connected Transceiver</em>' reference list.
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getBluetoothTransceiver_ConnectedTransceiver()
	 * @model
	 * @generated
	 */
	EList<BluetoothTransceiver> getConnectedTransceiver();

} // BluetoothTransceiver
