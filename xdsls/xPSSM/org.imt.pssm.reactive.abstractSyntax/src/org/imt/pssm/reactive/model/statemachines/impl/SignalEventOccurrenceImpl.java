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
import org.imt.pssm.reactive.model.statemachines.Signal;
import org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence;
import org.imt.pssm.reactive.model.statemachines.StatemachinesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Signal Event Occurrence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.SignalEventOccurrenceImpl#getSignal <em>Signal</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.SignalEventOccurrenceImpl#getAttributeValues <em>Attribute Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SignalEventOccurrenceImpl extends EventOccurrenceImpl implements SignalEventOccurrence {
	/**
	 * The cached value of the '{@link #getSignal() <em>Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal()
	 * @generated
	 * @ordered
	 */
	protected Signal signal;

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
	protected SignalEventOccurrenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinesPackage.Literals.SIGNAL_EVENT_OCCURRENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal getSignal() {
		if (signal != null && signal.eIsProxy()) {
			InternalEObject oldSignal = (InternalEObject)signal;
			signal = (Signal)eResolveProxy(oldSignal);
			if (signal != oldSignal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__SIGNAL, oldSignal, signal));
			}
		}
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal basicGetSignal() {
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignal(Signal newSignal) {
		Signal oldSignal = signal;
		signal = newSignal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__SIGNAL, oldSignal, signal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeValue> getAttributeValues() {
		if (attributeValues == null) {
			attributeValues = new EObjectContainmentEList<AttributeValue>(AttributeValue.class, this, StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES);
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
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES:
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
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__SIGNAL:
				if (resolve) return getSignal();
				return basicGetSignal();
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES:
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
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__SIGNAL:
				setSignal((Signal)newValue);
				return;
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES:
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
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__SIGNAL:
				setSignal((Signal)null);
				return;
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES:
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
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__SIGNAL:
				return signal != null;
			case StatemachinesPackage.SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES:
				return attributeValues != null && !attributeValues.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SignalEventOccurrenceImpl
