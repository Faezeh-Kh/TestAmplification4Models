/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.IntegerConstraint#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerConstraint()
 * @model
 * @generated
 */
public interface IntegerConstraint extends Constraint {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(IntegerComparisonExpression)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerConstraint_Expression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	IntegerComparisonExpression getExpression();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.IntegerConstraint#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(IntegerComparisonExpression value);

} // IntegerConstraint
