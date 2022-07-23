/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Unary Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanUnaryExpression()
 * @model
 * @generated
 */
public interface BooleanUnaryExpression extends BooleanExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator
	 * @see #setOperator(BooleanUnaryOperator)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanUnaryExpression_Operator()
	 * @model required="true"
	 * @generated
	 */
	BooleanUnaryOperator getOperator();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BooleanUnaryOperator value);

	/**
	 * Returns the value of the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' reference.
	 * @see #setOperand(BooleanAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanUnaryExpression_Operand()
	 * @model required="true"
	 * @generated
	 */
	BooleanAttribute getOperand();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression#getOperand <em>Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(BooleanAttribute value);

} // BooleanUnaryExpression
