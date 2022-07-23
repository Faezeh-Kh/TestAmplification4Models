/**
 */
package org.imt.pssm.reactive.model.statemachines.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.imt.pssm.reactive.model.statemachines.AttributeValue;
import org.imt.pssm.reactive.model.statemachines.CallEventOccurrence;
import org.imt.pssm.reactive.model.statemachines.Operation;
import org.imt.pssm.reactive.model.statemachines.StatemachinesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Event Occurrence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.CallEventOccurrenceImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.CallEventOccurrenceImpl#getInParameterValues <em>In Parameter Values</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.CallEventOccurrenceImpl#getOutParameterValues <em>Out Parameter Values</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.CallEventOccurrenceImpl#getReturnValue <em>Return Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CallEventOccurrenceImpl extends EventOccurrenceImpl implements CallEventOccurrence {
	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation operation;

	/**
	 * The cached value of the '{@link #getInParameterValues() <em>In Parameter Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInParameterValues()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeValue> inParameterValues;

	/**
	 * The cached value of the '{@link #getOutParameterValues() <em>Out Parameter Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutParameterValues()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeValue> outParameterValues;

	/**
	 * The cached value of the '{@link #getReturnValue() <em>Return Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnValue()
	 * @generated
	 * @ordered
	 */
	protected AttributeValue returnValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallEventOccurrenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinesPackage.Literals.CALL_EVENT_OCCURRENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getOperation() {
		if (operation != null && operation.eIsProxy()) {
			InternalEObject oldOperation = (InternalEObject)operation;
			operation = (Operation)eResolveProxy(oldOperation);
			if (operation != oldOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StatemachinesPackage.CALL_EVENT_OCCURRENCE__OPERATION, oldOperation, operation));
			}
		}
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(Operation newOperation) {
		Operation oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinesPackage.CALL_EVENT_OCCURRENCE__OPERATION, oldOperation, operation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeValue> getInParameterValues() {
		if (inParameterValues == null) {
			inParameterValues = new EObjectContainmentEList<AttributeValue>(AttributeValue.class, this, StatemachinesPackage.CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES);
		}
		return inParameterValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeValue> getOutParameterValues() {
		if (outParameterValues == null) {
			outParameterValues = new EObjectContainmentEList<AttributeValue>(AttributeValue.class, this, StatemachinesPackage.CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES);
		}
		return outParameterValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeValue getReturnValue() {
		return returnValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnValue(AttributeValue newReturnValue, NotificationChain msgs) {
		AttributeValue oldReturnValue = returnValue;
		returnValue = newReturnValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE, oldReturnValue, newReturnValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnValue(AttributeValue newReturnValue) {
		if (newReturnValue != returnValue) {
			NotificationChain msgs = null;
			if (returnValue != null)
				msgs = ((InternalEObject)returnValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE, null, msgs);
			if (newReturnValue != null)
				msgs = ((InternalEObject)newReturnValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE, null, msgs);
			msgs = basicSetReturnValue(newReturnValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE, newReturnValue, newReturnValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES:
				return ((InternalEList<?>)getInParameterValues()).basicRemove(otherEnd, msgs);
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES:
				return ((InternalEList<?>)getOutParameterValues()).basicRemove(otherEnd, msgs);
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE:
				return basicSetReturnValue(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OPERATION:
				if (resolve) return getOperation();
				return basicGetOperation();
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES:
				return getInParameterValues();
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES:
				return getOutParameterValues();
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE:
				return getReturnValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OPERATION:
				setOperation((Operation)newValue);
				return;
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES:
				getInParameterValues().clear();
				getInParameterValues().addAll((Collection<? extends AttributeValue>)newValue);
				return;
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES:
				getOutParameterValues().clear();
				getOutParameterValues().addAll((Collection<? extends AttributeValue>)newValue);
				return;
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE:
				setReturnValue((AttributeValue)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OPERATION:
				setOperation((Operation)null);
				return;
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES:
				getInParameterValues().clear();
				return;
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES:
				getOutParameterValues().clear();
				return;
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE:
				setReturnValue((AttributeValue)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OPERATION:
				return operation != null;
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES:
				return inParameterValues != null && !inParameterValues.isEmpty();
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES:
				return outParameterValues != null && !outParameterValues.isEmpty();
			case StatemachinesPackage.CALL_EVENT_OCCURRENCE__RETURN_VALUE:
				return returnValue != null;
		}
		return super.eIsSet(featureID);
	}

} //CallEventOccurrenceImpl
