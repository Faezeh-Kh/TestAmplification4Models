/**
 */
package org.imt.pssm.reactive.model.statemachines.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.imt.pssm.reactive.model.statemachines.AttributeValue;
import org.imt.pssm.reactive.model.statemachines.OperationBehavior;
import org.imt.pssm.reactive.model.statemachines.StatemachinesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.OperationBehaviorImpl#getAttributeValues <em>Attribute Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationBehaviorImpl extends BehaviorImpl implements OperationBehavior {
	/**
	 * The cached value of the '{@link #getAttributeValues() <em>Attribute Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeValues()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeValue> attributeValues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationBehaviorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinesPackage.Literals.OPERATION_BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeValue> getAttributeValues() {
		if (attributeValues == null) {
			attributeValues = new EObjectContainmentEList<AttributeValue>(AttributeValue.class, this, StatemachinesPackage.OPERATION_BEHAVIOR__ATTRIBUTE_VALUES);
		}
		return attributeValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinesPackage.OPERATION_BEHAVIOR__ATTRIBUTE_VALUES:
				return ((InternalEList<?>)getAttributeValues()).basicRemove(otherEnd, msgs);
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
			case StatemachinesPackage.OPERATION_BEHAVIOR__ATTRIBUTE_VALUES:
				return getAttributeValues();
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
			case StatemachinesPackage.OPERATION_BEHAVIOR__ATTRIBUTE_VALUES:
				getAttributeValues().clear();
				getAttributeValues().addAll((Collection<? extends AttributeValue>)newValue);
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
			case StatemachinesPackage.OPERATION_BEHAVIOR__ATTRIBUTE_VALUES:
				getAttributeValues().clear();
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
			case StatemachinesPackage.OPERATION_BEHAVIOR__ATTRIBUTE_VALUES:
				return attributeValues != null && !attributeValues.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OperationBehaviorImpl
