/**
 */
package org.imt.arduino.reactive.arduino;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Change Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getChangeType()
 * @model
 * @generated
 */
public enum ChangeType implements Enumerator {
	/**
	 * The '<em><b>RISING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RISING_VALUE
	 * @generated
	 * @ordered
	 */
	RISING(0, "RISING", "RISING"),

	/**
	 * The '<em><b>FALLING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FALLING_VALUE
	 * @generated
	 * @ordered
	 */
	FALLING(1, "FALLING", "FALLING"),

	/**
	 * The '<em><b>CHANGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHANGE_VALUE
	 * @generated
	 * @ordered
	 */
	CHANGE(2, "CHANGE", "CHANGE");

	/**
	 * The '<em><b>RISING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RISING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RISING_VALUE = 0;

	/**
	 * The '<em><b>FALLING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FALLING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FALLING_VALUE = 1;

	/**
	 * The '<em><b>CHANGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHANGE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CHANGE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Change Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ChangeType[] VALUES_ARRAY =
		new ChangeType[] {
			RISING,
			FALLING,
			CHANGE,
		};

	/**
	 * A public read-only list of all the '<em><b>Change Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ChangeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Change Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ChangeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ChangeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Change Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ChangeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ChangeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Change Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ChangeType get(int value) {
		switch (value) {
			case RISING_VALUE: return RISING;
			case FALLING_VALUE: return FALLING;
			case CHANGE_VALUE: return CHANGE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ChangeType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ChangeType
