/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.StringConstraint#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringConstraint()
 * @model
 * @generated
 */
public interface StringConstraint extends Constraint {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(StringComparisonExpression)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringConstraint_Expression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	StringComparisonExpression getExpression();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.StringConstraint#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(StringComparisonExpression value);

} // StringConstraint
