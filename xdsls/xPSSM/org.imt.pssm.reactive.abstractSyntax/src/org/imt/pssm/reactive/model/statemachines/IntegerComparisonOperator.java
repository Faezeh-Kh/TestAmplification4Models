/**
 */
package org.imt.pssm.reactive.model.statemachines;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Integer Comparison Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerComparisonOperator()
 * @model
 * @generated
 */
public enum IntegerComparisonOperator implements Enumerator {
	/**
	 * The '<em><b>SMALLER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SMALLER_VALUE
	 * @generated
	 * @ordered
	 */
	SMALLER(0, "SMALLER", "<"),

	/**
	 * The '<em><b>SMALLER EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SMALLER_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	SMALLER_EQUALS(1, "SMALLER_EQUALS", "<="),

	/**
	 * The '<em><b>EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	EQUALS(2, "EQUALS", "=="),

	/**
	 * The '<em><b>NOT EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_EQUALS(3, "NOT_EQUALS", "!="),

	/**
	 * The '<em><b>GREATER EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER_EQUALS(4, "GREATER_EQUALS", ">="),

	/**
	 * The '<em><b>GREATER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER(5, "GREATER", ">");

	/**
	 * The '<em><b>SMALLER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SMALLER
	 * @model literal="&lt;"
	 * @generated
	 * @ordered
	 */
	public static final int SMALLER_VALUE = 0;

	/**
	 * The '<em><b>SMALLER EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SMALLER_EQUALS
	 * @model literal="&lt;="
	 * @generated
	 * @ordered
	 */
	public static final int SMALLER_EQUALS_VALUE = 1;

	/**
	 * The '<em><b>EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUALS
	 * @model literal="=="
	 * @generated
	 * @ordered
	 */
	public static final int EQUALS_VALUE = 2;

	/**
	 * The '<em><b>NOT EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUALS
	 * @model literal="!="
	 * @generated
	 * @ordered
	 */
	public static final int NOT_EQUALS_VALUE = 3;

	/**
	 * The '<em><b>GREATER EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUALS
	 * @model literal="&gt;="
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_EQUALS_VALUE = 4;

	/**
	 * The '<em><b>GREATER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER
	 * @model literal="&gt;"
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_VALUE = 5;

	/**
	 * An array of all the '<em><b>Integer Comparison Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final IntegerComparisonOperator[] VALUES_ARRAY =
		new IntegerComparisonOperator[] {
			SMALLER,
			SMALLER_EQUALS,
			EQUALS,
			NOT_EQUALS,
			GREATER_EQUALS,
			GREATER,
		};

	/**
	 * A public read-only list of all the '<em><b>Integer Comparison Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<IntegerComparisonOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Integer Comparison Operator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static IntegerComparisonOperator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IntegerComparisonOperator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Integer Comparison Operator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static IntegerComparisonOperator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IntegerComparisonOperator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Integer Comparison Operator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static IntegerComparisonOperator get(int value) {
		switch (value) {
			case SMALLER_VALUE: return SMALLER;
			case SMALLER_EQUALS_VALUE: return SMALLER_EQUALS;
			case EQUALS_VALUE: return EQUALS;
			case NOT_EQUALS_VALUE: return NOT_EQUALS;
			case GREATER_EQUALS_VALUE: return GREATER_EQUALS;
			case GREATER_VALUE: return GREATER;
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
	private IntegerComparisonOperator(int value, String name, String literal) {
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
	
} //IntegerComparisonOperator
