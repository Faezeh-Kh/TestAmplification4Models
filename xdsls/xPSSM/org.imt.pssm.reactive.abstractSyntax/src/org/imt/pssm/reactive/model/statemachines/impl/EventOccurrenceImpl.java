/**
 */
package org.imt.pssm.reactive.model.statemachines.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.imt.pssm.reactive.model.statemachines.EventOccurrence;
import org.imt.pssm.reactive.model.statemachines.StatemachinesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Occurrence</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class EventOccurrenceImpl extends MinimalEObjectImpl.Container implements EventOccurrence {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventOccurrenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinesPackage.Literals.EVENT_OCCURRENCE;
	}

} //EventOccurrenceImpl
