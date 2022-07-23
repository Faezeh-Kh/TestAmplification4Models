/**
 */
package org.imt.pssm.reactive.model.statemachines.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.imt.pssm.reactive.model.statemachines.EventType;
import org.imt.pssm.reactive.model.statemachines.StatemachinesPackage;
import org.imt.pssm.reactive.model.statemachines.Trigger;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.TriggerImpl#getEventType <em>Event Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TriggerImpl extends NamedElementImpl implements Trigger {
	/**
	 * The cached value of the '{@link #getEventType() <em>Event Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventType()
	 * @generated
	 * @ordered
	 */
	protected EventType eventType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinesPackage.Literals.TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventType(EventType newEventType, NotificationChain msgs) {
		EventType oldEventType = eventType;
		eventType = newEventType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatemachinesPackage.TRIGGER__EVENT_TYPE, oldEventType, newEventType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventType(EventType newEventType) {
		if (newEventType != eventType) {
			NotificationChain msgs = null;
			if (eventType != null)
				msgs = ((InternalEObject)eventType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatemachinesPackage.TRIGGER__EVENT_TYPE, null, msgs);
			if (newEventType != null)
				msgs = ((InternalEObject)newEventType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatemachinesPackage.TRIGGER__EVENT_TYPE, null, msgs);
			msgs = basicSetEventType(newEventType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinesPackage.TRIGGER__EVENT_TYPE, newEventType, newEventType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinesPackage.TRIGGER__EVENT_TYPE:
				return basicSetEventType(null, msgs);
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
			case StatemachinesPackage.TRIGGER__EVENT_TYPE:
				return getEventType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatemachinesPackage.TRIGGER__EVENT_TYPE:
				setEventType((EventType)newValue);
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
			case StatemachinesPackage.TRIGGER__EVENT_TYPE:
				setEventType((EventType)null);
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
			case StatemachinesPackage.TRIGGER__EVENT_TYPE:
				return eventType != null;
		}
		return super.eIsSet(featureID);
	}

} //TriggerImpl
