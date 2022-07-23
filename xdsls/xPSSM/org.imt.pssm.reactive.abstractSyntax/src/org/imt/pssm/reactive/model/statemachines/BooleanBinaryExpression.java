/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Binary Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperand1 <em>Operand1</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperand2 <em>Operand2</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanBinaryExpression()
 * @model
 * @generated
 */
public interface BooleanBinaryExpression extends BooleanExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator
	 * @see #setOperator(BooleanBinaryOperator)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanBinaryExpression_Operator()
	 * @model required="true"
	 * @generated
	 */
	BooleanBinaryOperator getOperator();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BooleanBinaryOperator value);

	/**
	 * Returns the value of the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand1</em>' reference.
	 * @see #setOperand1(BooleanAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanBinaryExpression_Operand1()
	 * @model required="true"
	 * @generated
	 */
	BooleanAttribute getOperand1();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperand1 <em>Operand1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand1</em>' reference.
	 * @see #getOperand1()
	 * @generated
	 */
	void setOperand1(BooleanAttribute value);

	/**
	 * Returns the value of the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand2</em>' reference.
	 * @see #setOperand2(BooleanAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getBooleanBinaryExpression_Operand2()
	 * @model required="true"
	 * @generated
	 */
	BooleanAttribute getOperand2();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperand2 <em>Operand2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand2</em>' reference.
	 * @see #getOperand2()
	 * @generated
	 */
	void setOperand2(BooleanAttribute value);

} // BooleanBinaryExpression
