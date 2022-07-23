/**
 */
package org.imt.pssm.reactive.model.statemachines;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Pseudostate Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getPseudostateKind()
 * @model
 * @generated
 */
public enum PseudostateKind implements Enumerator {
	/**
	 * The '<em><b>Initial</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INITIAL_VALUE
	 * @generated
	 * @ordered
	 */
	INITIAL(0, "initial", "initial"),

	/**
	 * The '<em><b>Join</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOIN_VALUE
	 * @generated
	 * @ordered
	 */
	JOIN(1, "join", "join"),

	/**
	 * The '<em><b>Fork</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORK_VALUE
	 * @generated
	 * @ordered
	 */
	FORK(2, "fork", "fork"),

	/**
	 * The '<em><b>Terminate</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TERMINATE_VALUE
	 * @generated
	 * @ordered
	 */
	TERMINATE(3, "terminate", "terminate"),

	/**
	 * The '<em><b>Entrypoint</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENTRYPOINT_VALUE
	 * @generated
	 * @ordered
	 */
	ENTRYPOINT(4, "entrypoint", "entrypoint"),

	/**
	 * The '<em><b>Exitpoint</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXITPOINT_VALUE
	 * @generated
	 * @ordered
	 */
	EXITPOINT(5, "exitpoint", "exitpoint");

	/**
	 * The '<em><b>Initial</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INITIAL
	 * @model name="initial"
	 * @generated
	 * @ordered
	 */
	public static final int INITIAL_VALUE = 0;

	/**
	 * The '<em><b>Join</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOIN
	 * @model name="join"
	 * @generated
	 * @ordered
	 */
	public static final int JOIN_VALUE = 1;

	/**
	 * The '<em><b>Fork</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORK
	 * @model name="fork"
	 * @generated
	 * @ordered
	 */
	public static final int FORK_VALUE = 2;

	/**
	 * The '<em><b>Terminate</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TERMINATE
	 * @model name="terminate"
	 * @generated
	 * @ordered
	 */
	public static final int TERMINATE_VALUE = 3;

	/**
	 * The '<em><b>Entrypoint</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENTRYPOINT
	 * @model name="entrypoint"
	 * @generated
	 * @ordered
	 */
	public static final int ENTRYPOINT_VALUE = 4;

	/**
	 * The '<em><b>Exitpoint</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXITPOINT
	 * @model name="exitpoint"
	 * @generated
	 * @ordered
	 */
	public static final int EXITPOINT_VALUE = 5;

	/**
	 * An array of all the '<em><b>Pseudostate Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PseudostateKind[] VALUES_ARRAY =
		new PseudostateKind[] {
			INITIAL,
			JOIN,
			FORK,
			TERMINATE,
			ENTRYPOINT,
			EXITPOINT,
		};

	/**
	 * A public read-only list of all the '<em><b>Pseudostate Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PseudostateKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Pseudostate Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PseudostateKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PseudostateKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Pseudostate Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PseudostateKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PseudostateKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Pseudostate Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PseudostateKind get(int value) {
		switch (value) {
			case INITIAL_VALUE: return INITIAL;
			case JOIN_VALUE: return JOIN;
			case FORK_VALUE: return FORK;
			case TERMINATE_VALUE: return TERMINATE;
			case ENTRYPOINT_VALUE: return ENTRYPOINT;
			case EXITPOINT_VALUE: return EXITPOINT;
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
	private PseudostateKind(int value, String name, String literal) {
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
	
} //PseudostateKind
