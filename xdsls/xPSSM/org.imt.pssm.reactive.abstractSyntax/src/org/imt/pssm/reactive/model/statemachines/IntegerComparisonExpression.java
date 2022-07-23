/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Comparison Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperand1 <em>Operand1</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperand2 <em>Operand2</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerComparisonExpression()
 * @model
 * @generated
 */
public interface IntegerComparisonExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator
	 * @see #setOperator(IntegerComparisonOperator)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerComparisonExpression_Operator()
	 * @model required="true"
	 * @generated
	 */
	IntegerComparisonOperator getOperator();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(IntegerComparisonOperator value);

	/**
	 * Returns the value of the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand1</em>' reference.
	 * @see #setOperand1(IntegerAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerComparisonExpression_Operand1()
	 * @model required="true"
	 * @generated
	 */
	IntegerAttribute getOperand1();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperand1 <em>Operand1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand1</em>' reference.
	 * @see #getOperand1()
	 * @generated
	 */
	void setOperand1(IntegerAttribute value);

	/**
	 * Returns the value of the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand2</em>' reference.
	 * @see #setOperand2(IntegerAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getIntegerComparisonExpression_Operand2()
	 * @model required="true"
	 * @generated
	 */
	IntegerAttribute getOperand2();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperand2 <em>Operand2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand2</em>' reference.
	 * @see #getOperand2()
	 * @generated
	 */
	void setOperand2(IntegerAttribute value);

} // IntegerComparisonExpression
