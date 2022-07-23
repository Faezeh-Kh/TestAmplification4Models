/**
 */
package org.imt.pssm.reactive.model.statemachines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Comparison Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperand1 <em>Operand1</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperand2 <em>Operand2</em>}</li>
 * </ul>
 *
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringComparisonExpression()
 * @model
 * @generated
 */
public interface StringComparisonExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.imt.pssm.reactive.model.statemachines.StringComparisonOperator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonOperator
	 * @see #setOperator(StringComparisonOperator)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringComparisonExpression_Operator()
	 * @model required="true"
	 * @generated
	 */
	StringComparisonOperator getOperator();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(StringComparisonOperator value);

	/**
	 * Returns the value of the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand1</em>' reference.
	 * @see #setOperand1(StringAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringComparisonExpression_Operand1()
	 * @model required="true"
	 * @generated
	 */
	StringAttribute getOperand1();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperand1 <em>Operand1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand1</em>' reference.
	 * @see #getOperand1()
	 * @generated
	 */
	void setOperand1(StringAttribute value);

	/**
	 * Returns the value of the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand2</em>' reference.
	 * @see #setOperand2(StringAttribute)
	 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesPackage#getStringComparisonExpression_Operand2()
	 * @model required="true"
	 * @generated
	 */
	StringAttribute getOperand2();

	/**
	 * Sets the value of the '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperand2 <em>Operand2</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand2</em>' reference.
	 * @see #getOperand2()
	 * @generated
	 */
	void setOperand2(StringAttribute value);

} // StringComparisonExpression
