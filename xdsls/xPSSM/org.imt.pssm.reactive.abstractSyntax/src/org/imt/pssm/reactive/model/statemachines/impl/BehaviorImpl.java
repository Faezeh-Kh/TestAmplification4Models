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

import org.imt.pssm.reactive.model.statemachines.Behavior;
import org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence;
import org.imt.pssm.reactive.model.statemachines.StatemachinesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.BehaviorImpl#getEmittedSignals <em>Emitted Signals</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BehaviorImpl extends NamedElementImpl implements Behavior {
	/**
	 * The cached value of the '{@link #getEmittedSignals() <em>Emitted Signals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmittedSignals()
	 * @generated
	 * @ordered
	 */
	protected EList<SignalEventOccurrence> emittedSignals;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BehaviorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinesPackage.Literals.BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SignalEventOccurrence> getEmittedSignals() {
		if (emittedSignals == null) {
			emittedSignals = new EObjectContainmentEList<SignalEventOccurrence>(SignalEventOccurrence.class, this, StatemachinesPackage.BEHAVIOR__EMITTED_SIGNALS);
		}
		return emittedSignals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinesPackage.BEHAVIOR__EMITTED_SIGNALS:
				return ((InternalEList<?>)getEmittedSignals()).basicRemove(otherEnd, msgs);
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
			case StatemachinesPackage.BEHAVIOR__EMITTED_SIGNALS:
				return getEmittedSignals();
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
			case StatemachinesPackage.BEHAVIOR__EMITTED_SIGNALS:
				getEmittedSignals().clear();
				getEmittedSignals().addAll((Collection<? extends SignalEventOccurrence>)newValue);
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
			case StatemachinesPackage.BEHAVIOR__EMITTED_SIGNALS:
				getEmittedSignals().clear();
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
			case StatemachinesPackage.BEHAVIOR__EMITTED_SIGNALS:
				return emittedSignals != null && !emittedSignals.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BehaviorImpl
