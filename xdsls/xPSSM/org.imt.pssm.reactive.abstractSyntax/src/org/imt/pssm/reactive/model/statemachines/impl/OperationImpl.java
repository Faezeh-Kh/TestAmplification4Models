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

import org.imt.pssm.reactive.model.statemachines.Attribute;
import org.imt.pssm.reactive.model.statemachines.Operation;
import org.imt.pssm.reactive.model.statemachines.StatemachinesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.OperationImpl#getInParameters <em>In Parameters</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.OperationImpl#getOutParameters <em>Out Parameters</em>}</li>
 *   <li>{@link org.imt.pssm.reactive.model.statemachines.impl.OperationImpl#getReturn <em>Return</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationImpl extends NamedElementImpl implements Operation {
	/**
	 * The cached value of the '{@link #getInParameters() <em>In Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> inParameters;

	/**
	 * The cached value of the '{@link #getOutParameters() <em>Out Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> outParameters;

	/**
	 * The cached value of the '{@link #getReturn() <em>Return</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturn()
	 * @generated
	 * @ordered
	 */
	protected Attribute return_;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinesPackage.Literals.OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getInParameters() {
		if (inParameters == null) {
			inParameters = new EObjectContainmentEList<Attribute>(Attribute.class, this, StatemachinesPackage.OPERATION__IN_PARAMETERS);
		}
		return inParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getOutParameters() {
		if (outParameters == null) {
			outParameters = new EObjectContainmentEList<Attribute>(Attribute.class, this, StatemachinesPackage.OPERATION__OUT_PARAMETERS);
		}
		return outParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute getReturn() {
		return return_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturn(Attribute newReturn, NotificationChain msgs) {
		Attribute oldReturn = return_;
		return_ = newReturn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatemachinesPackage.OPERATION__RETURN, oldReturn, newReturn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturn(Attribute newReturn) {
		if (newReturn != return_) {
			NotificationChain msgs = null;
			if (return_ != null)
				msgs = ((InternalEObject)return_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatemachinesPackage.OPERATION__RETURN, null, msgs);
			if (newReturn != null)
				msgs = ((InternalEObject)newReturn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatemachinesPackage.OPERATION__RETURN, null, msgs);
			msgs = basicSetReturn(newReturn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinesPackage.OPERATION__RETURN, newReturn, newReturn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinesPackage.OPERATION__IN_PARAMETERS:
				return ((InternalEList<?>)getInParameters()).basicRemove(otherEnd, msgs);
			case StatemachinesPackage.OPERATION__OUT_PARAMETERS:
				return ((InternalEList<?>)getOutParameters()).basicRemove(otherEnd, msgs);
			case StatemachinesPackage.OPERATION__RETURN:
				return basicSetReturn(null, msgs);
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
			case StatemachinesPackage.OPERATION__IN_PARAMETERS:
				return getInParameters();
			case StatemachinesPackage.OPERATION__OUT_PARAMETERS:
				return getOutParameters();
			case StatemachinesPackage.OPERATION__RETURN:
				return getReturn();
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
			case StatemachinesPackage.OPERATION__IN_PARAMETERS:
				getInParameters().clear();
				getInParameters().addAll((Collection<? extends Attribute>)newValue);
				return;
			case StatemachinesPackage.OPERATION__OUT_PARAMETERS:
				getOutParameters().clear();
				getOutParameters().addAll((Collection<? extends Attribute>)newValue);
				return;
			case StatemachinesPackage.OPERATION__RETURN:
				setReturn((Attribute)newValue);
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
			case StatemachinesPackage.OPERATION__IN_PARAMETERS:
				getInParameters().clear();
				return;
			case StatemachinesPackage.OPERATION__OUT_PARAMETERS:
				getOutParameters().clear();
				return;
			case StatemachinesPackage.OPERATION__RETURN:
				setReturn((Attribute)null);
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
			case StatemachinesPackage.OPERATION__IN_PARAMETERS:
				return inParameters != null && !inParameters.isEmpty();
			case StatemachinesPackage.OPERATION__OUT_PARAMETERS:
				return outParameters != null && !outParameters.isEmpty();
			case StatemachinesPackage.OPERATION__RETURN:
				return return_ != null;
		}
		return super.eIsSet(featureID);
	}

} //OperationImpl
