/**
 */
package org.imt.pssm.reactive.model.statemachines;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.imt.pssm.reactive.model.statemachines.StatemachinesFactory
 * @model kind="package"
 * @generated
 */
public interface StatemachinesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "statemachines";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.imt.pssm.reactive/statemachines/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "statemachines";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatemachinesPackage eINSTANCE = org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.CustomSystemImpl <em>Custom System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.CustomSystemImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getCustomSystem()
	 * @generated
	 */
	int CUSTOM_SYSTEM = 0;

	/**
	 * The feature id for the '<em><b>Statemachine</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_SYSTEM__STATEMACHINE = 0;

	/**
	 * The feature id for the '<em><b>Signals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_SYSTEM__SIGNALS = 1;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_SYSTEM__OPERATIONS = 2;

	/**
	 * The number of structural features of the '<em>Custom System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_SYSTEM_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Custom System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_SYSTEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.NamedElementImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.SignalImpl <em>Signal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.SignalImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getSignal()
	 * @generated
	 */
	int SIGNAL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__ATTRIBUTES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Signal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Signal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.OperationImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>In Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__IN_PARAMETERS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Out Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__OUT_PARAMETERS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Return</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__RETURN = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.EventTypeImpl <em>Event Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.EventTypeImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getEventType()
	 * @generated
	 */
	int EVENT_TYPE = 3;

	/**
	 * The number of structural features of the '<em>Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.SignalEventTypeImpl <em>Signal Event Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.SignalEventTypeImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getSignalEventType()
	 * @generated
	 */
	int SIGNAL_EVENT_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Signal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_EVENT_TYPE__SIGNAL = EVENT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Signal Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_EVENT_TYPE_FEATURE_COUNT = EVENT_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Signal Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_EVENT_TYPE_OPERATION_COUNT = EVENT_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.CallEventTypeImpl <em>Call Event Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.CallEventTypeImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getCallEventType()
	 * @generated
	 */
	int CALL_EVENT_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_TYPE__OPERATION = EVENT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Call Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_TYPE_FEATURE_COUNT = EVENT_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Call Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_TYPE_OPERATION_COUNT = EVENT_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.AttributeImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanAttributeImpl <em>Boolean Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanAttributeImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanAttribute()
	 * @generated
	 */
	int BOOLEAN_ATTRIBUTE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE__NAME = ATTRIBUTE__NAME;

	/**
	 * The number of structural features of the '<em>Boolean Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Boolean Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.IntegerAttributeImpl <em>Integer Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.IntegerAttributeImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerAttribute()
	 * @generated
	 */
	int INTEGER_ATTRIBUTE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE__NAME = ATTRIBUTE__NAME;

	/**
	 * The number of structural features of the '<em>Integer Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Integer Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StringAttributeImpl <em>String Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StringAttributeImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringAttribute()
	 * @generated
	 */
	int STRING_ATTRIBUTE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE__NAME = ATTRIBUTE__NAME;

	/**
	 * The number of structural features of the '<em>String Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>String Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_OPERATION_COUNT = ATTRIBUTE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.ConstraintImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 10;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanConstraintImpl <em>Boolean Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanConstraintImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanConstraint()
	 * @generated
	 */
	int BOOLEAN_CONSTRAINT = 11;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTRAINT__EXPRESSION = CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.IntegerConstraintImpl <em>Integer Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.IntegerConstraintImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerConstraint()
	 * @generated
	 */
	int INTEGER_CONSTRAINT = 12;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_CONSTRAINT__EXPRESSION = CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StringConstraintImpl <em>String Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StringConstraintImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringConstraint()
	 * @generated
	 */
	int STRING_CONSTRAINT = 13;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CONSTRAINT__EXPRESSION = CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StateMachineImpl <em>State Machine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StateMachineImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStateMachine()
	 * @generated
	 */
	int STATE_MACHINE = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Regions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__REGIONS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>State Machine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>State Machine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.RegionImpl <em>Region</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.RegionImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getRegion()
	 * @generated
	 */
	int REGION = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Vertice</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__VERTICE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__TRANSITIONS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>State Machine</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__STATE_MACHINE = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>State</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__STATE = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Current Vertex</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__CURRENT_VERTEX = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.VertexImpl <em>Vertex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.VertexImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getVertex()
	 * @generated
	 */
	int VERTEX = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__CONTAINER = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outgoing Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__OUTGOING_TRANSITIONS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Incoming Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__INCOMING_TRANSITIONS = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.PseudostateImpl <em>Pseudostate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.PseudostateImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getPseudostate()
	 * @generated
	 */
	int PSEUDOSTATE = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDOSTATE__NAME = VERTEX__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDOSTATE__CONTAINER = VERTEX__CONTAINER;

	/**
	 * The feature id for the '<em><b>Outgoing Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDOSTATE__OUTGOING_TRANSITIONS = VERTEX__OUTGOING_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Incoming Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDOSTATE__INCOMING_TRANSITIONS = VERTEX__INCOMING_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDOSTATE__KIND = VERTEX_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>State</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDOSTATE__STATE = VERTEX_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Pseudostate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDOSTATE_FEATURE_COUNT = VERTEX_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Pseudostate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDOSTATE_OPERATION_COUNT = VERTEX_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StateImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getState()
	 * @generated
	 */
	int STATE = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = VERTEX__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__CONTAINER = VERTEX__CONTAINER;

	/**
	 * The feature id for the '<em><b>Outgoing Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__OUTGOING_TRANSITIONS = VERTEX__OUTGOING_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Incoming Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__INCOMING_TRANSITIONS = VERTEX__INCOMING_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Regions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__REGIONS = VERTEX_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__ENTRY = VERTEX_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Do Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DO_ACTIVITY = VERTEX_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Exit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__EXIT = VERTEX_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Deferrable Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DEFERRABLE_TRIGGERS = VERTEX_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Connection Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__CONNECTION_POINT = VERTEX_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = VERTEX_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_OPERATION_COUNT = VERTEX_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.FinalStateImpl <em>Final State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.FinalStateImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getFinalState()
	 * @generated
	 */
	int FINAL_STATE = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__NAME = STATE__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__CONTAINER = STATE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Outgoing Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__OUTGOING_TRANSITIONS = STATE__OUTGOING_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Incoming Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__INCOMING_TRANSITIONS = STATE__INCOMING_TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Regions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__REGIONS = STATE__REGIONS;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__ENTRY = STATE__ENTRY;

	/**
	 * The feature id for the '<em><b>Do Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__DO_ACTIVITY = STATE__DO_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Exit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__EXIT = STATE__EXIT;

	/**
	 * The feature id for the '<em><b>Deferrable Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__DEFERRABLE_TRIGGERS = STATE__DEFERRABLE_TRIGGERS;

	/**
	 * The feature id for the '<em><b>Connection Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__CONNECTION_POINT = STATE__CONNECTION_POINT;

	/**
	 * The number of structural features of the '<em>Final State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Final State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE_OPERATION_COUNT = STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.TransitionImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__SOURCE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TARGET = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TRIGGERS = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__CONTAINER = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__KIND = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Effect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__EFFECT = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__CONSTRAINT = NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.TriggerImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Event Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__EVENT_TYPE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BehaviorImpl <em>Behavior</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.BehaviorImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBehavior()
	 * @generated
	 */
	int BEHAVIOR = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Emitted Signals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR__EMITTED_SIGNALS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Behavior</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Behavior</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.OperationBehaviorImpl <em>Operation Behavior</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.OperationBehaviorImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getOperationBehavior()
	 * @generated
	 */
	int OPERATION_BEHAVIOR = 24;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__NAME = BEHAVIOR__NAME;

	/**
	 * The feature id for the '<em><b>Emitted Signals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__EMITTED_SIGNALS = BEHAVIOR__EMITTED_SIGNALS;

	/**
	 * The feature id for the '<em><b>Attribute Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__ATTRIBUTE_VALUES = BEHAVIOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Behavior</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR_FEATURE_COUNT = BEHAVIOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Operation Behavior</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR_OPERATION_COUNT = BEHAVIOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.AttributeValueImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getAttributeValue()
	 * @generated
	 */
	int ATTRIBUTE_VALUE = 25;

	/**
	 * The number of structural features of the '<em>Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanAttributeValueImpl <em>Boolean Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanAttributeValueImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanAttributeValue()
	 * @generated
	 */
	int BOOLEAN_ATTRIBUTE_VALUE = 26;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE__VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.IntegerAttributeValueImpl <em>Integer Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.IntegerAttributeValueImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerAttributeValue()
	 * @generated
	 */
	int INTEGER_ATTRIBUTE_VALUE = 27;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE__VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StringAttributeValueImpl <em>String Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StringAttributeValueImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringAttributeValue()
	 * @generated
	 */
	int STRING_ATTRIBUTE_VALUE = 28;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE__ATTRIBUTE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE__VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.ExpressionImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 29;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.IntegerComparisonExpressionImpl <em>Integer Comparison Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.IntegerComparisonExpressionImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerComparisonExpression()
	 * @generated
	 */
	int INTEGER_COMPARISON_EXPRESSION = 30;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_COMPARISON_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_COMPARISON_EXPRESSION__OPERAND1 = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_COMPARISON_EXPRESSION__OPERAND2 = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Integer Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_COMPARISON_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Integer Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_COMPARISON_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StringComparisonExpressionImpl <em>String Comparison Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StringComparisonExpressionImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringComparisonExpression()
	 * @generated
	 */
	int STRING_COMPARISON_EXPRESSION = 31;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION__OPERAND1 = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION__OPERAND2 = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>String Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>String Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanExpressionImpl <em>Boolean Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanExpressionImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanExpression()
	 * @generated
	 */
	int BOOLEAN_EXPRESSION = 32;

	/**
	 * The number of structural features of the '<em>Boolean Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Boolean Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanBinaryExpressionImpl <em>Boolean Binary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanBinaryExpressionImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanBinaryExpression()
	 * @generated
	 */
	int BOOLEAN_BINARY_EXPRESSION = 33;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINARY_EXPRESSION__OPERATOR = BOOLEAN_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINARY_EXPRESSION__OPERAND1 = BOOLEAN_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINARY_EXPRESSION__OPERAND2 = BOOLEAN_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Boolean Binary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINARY_EXPRESSION_FEATURE_COUNT = BOOLEAN_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Boolean Binary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINARY_EXPRESSION_OPERATION_COUNT = BOOLEAN_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanUnaryExpressionImpl <em>Boolean Unary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanUnaryExpressionImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanUnaryExpression()
	 * @generated
	 */
	int BOOLEAN_UNARY_EXPRESSION = 34;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_UNARY_EXPRESSION__OPERATOR = BOOLEAN_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_UNARY_EXPRESSION__OPERAND = BOOLEAN_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Boolean Unary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_UNARY_EXPRESSION_FEATURE_COUNT = BOOLEAN_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Boolean Unary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_UNARY_EXPRESSION_OPERATION_COUNT = BOOLEAN_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.EventOccurrenceImpl <em>Event Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.EventOccurrenceImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getEventOccurrence()
	 * @generated
	 */
	int EVENT_OCCURRENCE = 35;

	/**
	 * The number of structural features of the '<em>Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.CompletionEventOccurrenceImpl <em>Completion Event Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.CompletionEventOccurrenceImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getCompletionEventOccurrence()
	 * @generated
	 */
	int COMPLETION_EVENT_OCCURRENCE = 36;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETION_EVENT_OCCURRENCE__STATE = 0;

	/**
	 * The number of structural features of the '<em>Completion Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETION_EVENT_OCCURRENCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Completion Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETION_EVENT_OCCURRENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.SignalEventOccurrenceImpl <em>Signal Event Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.SignalEventOccurrenceImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getSignalEventOccurrence()
	 * @generated
	 */
	int SIGNAL_EVENT_OCCURRENCE = 37;

	/**
	 * The feature id for the '<em><b>Signal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_EVENT_OCCURRENCE__SIGNAL = EVENT_OCCURRENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Attribute Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES = EVENT_OCCURRENCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Signal Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_EVENT_OCCURRENCE_FEATURE_COUNT = EVENT_OCCURRENCE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Signal Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_EVENT_OCCURRENCE_OPERATION_COUNT = EVENT_OCCURRENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.impl.CallEventOccurrenceImpl <em>Call Event Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.impl.CallEventOccurrenceImpl
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getCallEventOccurrence()
	 * @generated
	 */
	int CALL_EVENT_OCCURRENCE = 38;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_OCCURRENCE__OPERATION = EVENT_OCCURRENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Parameter Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES = EVENT_OCCURRENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Out Parameter Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES = EVENT_OCCURRENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Return Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_OCCURRENCE__RETURN_VALUE = EVENT_OCCURRENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Call Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_OCCURRENCE_FEATURE_COUNT = EVENT_OCCURRENCE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Call Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_EVENT_OCCURRENCE_OPERATION_COUNT = EVENT_OCCURRENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator <em>Boolean Binary Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanBinaryOperator()
	 * @generated
	 */
	int BOOLEAN_BINARY_OPERATOR = 39;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator <em>Boolean Unary Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanUnaryOperator()
	 * @generated
	 */
	int BOOLEAN_UNARY_OPERATOR = 40;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator <em>Integer Comparison Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerComparisonOperator()
	 * @generated
	 */
	int INTEGER_COMPARISON_OPERATOR = 41;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonOperator <em>String Comparison Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonOperator
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringComparisonOperator()
	 * @generated
	 */
	int STRING_COMPARISON_OPERATOR = 42;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.PseudostateKind <em>Pseudostate Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.PseudostateKind
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getPseudostateKind()
	 * @generated
	 */
	int PSEUDOSTATE_KIND = 43;

	/**
	 * The meta object id for the '{@link org.imt.pssm.reactive.model.statemachines.TransitionKind <em>Transition Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.imt.pssm.reactive.model.statemachines.TransitionKind
	 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getTransitionKind()
	 * @generated
	 */
	int TRANSITION_KIND = 44;


	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.CustomSystem <em>Custom System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom System</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CustomSystem
	 * @generated
	 */
	EClass getCustomSystem();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.CustomSystem#getStatemachine <em>Statemachine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Statemachine</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CustomSystem#getStatemachine()
	 * @see #getCustomSystem()
	 * @generated
	 */
	EReference getCustomSystem_Statemachine();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.CustomSystem#getSignals <em>Signals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Signals</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CustomSystem#getSignals()
	 * @see #getCustomSystem()
	 * @generated
	 */
	EReference getCustomSystem_Signals();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.CustomSystem#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CustomSystem#getOperations()
	 * @see #getCustomSystem()
	 * @generated
	 */
	EReference getCustomSystem_Operations();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signal</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Signal
	 * @generated
	 */
	EClass getSignal();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.Signal#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Signal#getAttributes()
	 * @see #getSignal()
	 * @generated
	 */
	EReference getSignal_Attributes();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.Operation#getInParameters <em>In Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>In Parameters</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Operation#getInParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_InParameters();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.Operation#getOutParameters <em>Out Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Out Parameters</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Operation#getOutParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_OutParameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.Operation#getReturn <em>Return</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Return</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Operation#getReturn()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Return();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.EventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Type</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.EventType
	 * @generated
	 */
	EClass getEventType();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.SignalEventType <em>Signal Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signal Event Type</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.SignalEventType
	 * @generated
	 */
	EClass getSignalEventType();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.SignalEventType#getSignal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Signal</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.SignalEventType#getSignal()
	 * @see #getSignalEventType()
	 * @generated
	 */
	EReference getSignalEventType_Signal();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.CallEventType <em>Call Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Event Type</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventType
	 * @generated
	 */
	EClass getCallEventType();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.CallEventType#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventType#getOperation()
	 * @see #getCallEventType()
	 * @generated
	 */
	EReference getCallEventType_Operation();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.BooleanAttribute <em>Boolean Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Attribute</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanAttribute
	 * @generated
	 */
	EClass getBooleanAttribute();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.IntegerAttribute <em>Integer Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Attribute</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerAttribute
	 * @generated
	 */
	EClass getIntegerAttribute();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.StringAttribute <em>String Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Attribute</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringAttribute
	 * @generated
	 */
	EClass getStringAttribute();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.BooleanConstraint <em>Boolean Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Constraint</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanConstraint
	 * @generated
	 */
	EClass getBooleanConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.BooleanConstraint#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanConstraint#getExpression()
	 * @see #getBooleanConstraint()
	 * @generated
	 */
	EReference getBooleanConstraint_Expression();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.IntegerConstraint <em>Integer Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Constraint</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerConstraint
	 * @generated
	 */
	EClass getIntegerConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.IntegerConstraint#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerConstraint#getExpression()
	 * @see #getIntegerConstraint()
	 * @generated
	 */
	EReference getIntegerConstraint_Expression();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.StringConstraint <em>String Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Constraint</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringConstraint
	 * @generated
	 */
	EClass getStringConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.StringConstraint#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringConstraint#getExpression()
	 * @see #getStringConstraint()
	 * @generated
	 */
	EReference getStringConstraint_Expression();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.StateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Machine</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StateMachine
	 * @generated
	 */
	EClass getStateMachine();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.StateMachine#getRegions <em>Regions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Regions</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StateMachine#getRegions()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_Regions();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Region <em>Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Region</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Region
	 * @generated
	 */
	EClass getRegion();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.Region#getVertice <em>Vertice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vertice</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Region#getVertice()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_Vertice();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.Region#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transitions</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Region#getTransitions()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_Transitions();

	/**
	 * Returns the meta object for the container reference '{@link org.imt.pssm.reactive.model.statemachines.Region#getStateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>State Machine</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Region#getStateMachine()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_StateMachine();

	/**
	 * Returns the meta object for the container reference '{@link org.imt.pssm.reactive.model.statemachines.Region#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>State</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Region#getState()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_State();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.Region#getCurrentVertex <em>Current Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Current Vertex</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Region#getCurrentVertex()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_CurrentVertex();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Vertex
	 * @generated
	 */
	EClass getVertex();

	/**
	 * Returns the meta object for the container reference '{@link org.imt.pssm.reactive.model.statemachines.Vertex#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Vertex#getContainer()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_Container();

	/**
	 * Returns the meta object for the reference list '{@link org.imt.pssm.reactive.model.statemachines.Vertex#getOutgoingTransitions <em>Outgoing Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Transitions</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Vertex#getOutgoingTransitions()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_OutgoingTransitions();

	/**
	 * Returns the meta object for the reference list '{@link org.imt.pssm.reactive.model.statemachines.Vertex#getIncomingTransitions <em>Incoming Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Transitions</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Vertex#getIncomingTransitions()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_IncomingTransitions();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Pseudostate <em>Pseudostate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pseudostate</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Pseudostate
	 * @generated
	 */
	EClass getPseudostate();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.Pseudostate#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Pseudostate#getKind()
	 * @see #getPseudostate()
	 * @generated
	 */
	EAttribute getPseudostate_Kind();

	/**
	 * Returns the meta object for the container reference '{@link org.imt.pssm.reactive.model.statemachines.Pseudostate#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>State</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Pseudostate#getState()
	 * @see #getPseudostate()
	 * @generated
	 */
	EReference getPseudostate_State();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.State#getRegions <em>Regions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Regions</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.State#getRegions()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Regions();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.State#getEntry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Entry</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.State#getEntry()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Entry();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.State#getDoActivity <em>Do Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Do Activity</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.State#getDoActivity()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_DoActivity();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.State#getExit <em>Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exit</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.State#getExit()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Exit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.State#getDeferrableTriggers <em>Deferrable Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deferrable Triggers</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.State#getDeferrableTriggers()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_DeferrableTriggers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.State#getConnectionPoint <em>Connection Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Point</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.State#getConnectionPoint()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_ConnectionPoint();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.FinalState <em>Final State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Final State</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.FinalState
	 * @generated
	 */
	EClass getFinalState();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.Transition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition#getSource()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Source();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition#getTarget()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Target();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.Transition#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition#getTriggers()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Triggers();

	/**
	 * Returns the meta object for the container reference '{@link org.imt.pssm.reactive.model.statemachines.Transition#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition#getContainer()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Container();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.Transition#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition#getKind()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Kind();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.Transition#getEffect <em>Effect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Effect</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition#getEffect()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Effect();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.Transition#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constraint</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Transition#getConstraint()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Constraint();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.Trigger#getEventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Type</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Trigger#getEventType()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_EventType();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Behavior
	 * @generated
	 */
	EClass getBehavior();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.Behavior#getEmittedSignals <em>Emitted Signals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Emitted Signals</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Behavior#getEmittedSignals()
	 * @see #getBehavior()
	 * @generated
	 */
	EReference getBehavior_EmittedSignals();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.OperationBehavior <em>Operation Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Behavior</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.OperationBehavior
	 * @generated
	 */
	EClass getOperationBehavior();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.OperationBehavior#getAttributeValues <em>Attribute Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute Values</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.OperationBehavior#getAttributeValues()
	 * @see #getOperationBehavior()
	 * @generated
	 */
	EReference getOperationBehavior_AttributeValues();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.AttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Value</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.AttributeValue
	 * @generated
	 */
	EClass getAttributeValue();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue <em>Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Attribute Value</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue
	 * @generated
	 */
	EClass getBooleanAttributeValue();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue#getAttribute()
	 * @see #getBooleanAttributeValue()
	 * @generated
	 */
	EReference getBooleanAttributeValue_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanAttributeValue#getValue()
	 * @see #getBooleanAttributeValue()
	 * @generated
	 */
	EAttribute getBooleanAttributeValue_Value();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue <em>Integer Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Attribute Value</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue
	 * @generated
	 */
	EClass getIntegerAttributeValue();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue#getAttribute()
	 * @see #getIntegerAttributeValue()
	 * @generated
	 */
	EReference getIntegerAttributeValue_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerAttributeValue#getValue()
	 * @see #getIntegerAttributeValue()
	 * @generated
	 */
	EAttribute getIntegerAttributeValue_Value();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.StringAttributeValue <em>String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Attribute Value</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringAttributeValue
	 * @generated
	 */
	EClass getStringAttributeValue();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.StringAttributeValue#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringAttributeValue#getAttribute()
	 * @see #getStringAttributeValue()
	 * @generated
	 */
	EReference getStringAttributeValue_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.StringAttributeValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringAttributeValue#getValue()
	 * @see #getStringAttributeValue()
	 * @generated
	 */
	EAttribute getStringAttributeValue_Value();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression <em>Integer Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Comparison Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression
	 * @generated
	 */
	EClass getIntegerComparisonExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperator()
	 * @see #getIntegerComparisonExpression()
	 * @generated
	 */
	EAttribute getIntegerComparisonExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperand1 <em>Operand1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand1</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperand1()
	 * @see #getIntegerComparisonExpression()
	 * @generated
	 */
	EReference getIntegerComparisonExpression_Operand1();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperand2 <em>Operand2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand2</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonExpression#getOperand2()
	 * @see #getIntegerComparisonExpression()
	 * @generated
	 */
	EReference getIntegerComparisonExpression_Operand2();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression <em>String Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Comparison Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonExpression
	 * @generated
	 */
	EClass getStringComparisonExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperator()
	 * @see #getStringComparisonExpression()
	 * @generated
	 */
	EAttribute getStringComparisonExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperand1 <em>Operand1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand1</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperand1()
	 * @see #getStringComparisonExpression()
	 * @generated
	 */
	EReference getStringComparisonExpression_Operand1();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperand2 <em>Operand2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand2</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonExpression#getOperand2()
	 * @see #getStringComparisonExpression()
	 * @generated
	 */
	EReference getStringComparisonExpression_Operand2();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.BooleanExpression <em>Boolean Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanExpression
	 * @generated
	 */
	EClass getBooleanExpression();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression <em>Boolean Binary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Binary Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression
	 * @generated
	 */
	EClass getBooleanBinaryExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperator()
	 * @see #getBooleanBinaryExpression()
	 * @generated
	 */
	EAttribute getBooleanBinaryExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperand1 <em>Operand1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand1</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperand1()
	 * @see #getBooleanBinaryExpression()
	 * @generated
	 */
	EReference getBooleanBinaryExpression_Operand1();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperand2 <em>Operand2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand2</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryExpression#getOperand2()
	 * @see #getBooleanBinaryExpression()
	 * @generated
	 */
	EReference getBooleanBinaryExpression_Operand2();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression <em>Boolean Unary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Unary Expression</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression
	 * @generated
	 */
	EClass getBooleanUnaryExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression#getOperator()
	 * @see #getBooleanUnaryExpression()
	 * @generated
	 */
	EAttribute getBooleanUnaryExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryExpression#getOperand()
	 * @see #getBooleanUnaryExpression()
	 * @generated
	 */
	EReference getBooleanUnaryExpression_Operand();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.EventOccurrence <em>Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Occurrence</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.EventOccurrence
	 * @generated
	 */
	EClass getEventOccurrence();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence <em>Completion Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Completion Event Occurrence</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence
	 * @generated
	 */
	EClass getCompletionEventOccurrence();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CompletionEventOccurrence#getState()
	 * @see #getCompletionEventOccurrence()
	 * @generated
	 */
	EReference getCompletionEventOccurrence_State();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence <em>Signal Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signal Event Occurrence</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence
	 * @generated
	 */
	EClass getSignalEventOccurrence();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence#getSignal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Signal</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence#getSignal()
	 * @see #getSignalEventOccurrence()
	 * @generated
	 */
	EReference getSignalEventOccurrence_Signal();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence#getAttributeValues <em>Attribute Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute Values</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.SignalEventOccurrence#getAttributeValues()
	 * @see #getSignalEventOccurrence()
	 * @generated
	 */
	EReference getSignalEventOccurrence_AttributeValues();

	/**
	 * Returns the meta object for class '{@link org.imt.pssm.reactive.model.statemachines.CallEventOccurrence <em>Call Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Event Occurrence</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventOccurrence
	 * @generated
	 */
	EClass getCallEventOccurrence();

	/**
	 * Returns the meta object for the reference '{@link org.imt.pssm.reactive.model.statemachines.CallEventOccurrence#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventOccurrence#getOperation()
	 * @see #getCallEventOccurrence()
	 * @generated
	 */
	EReference getCallEventOccurrence_Operation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.CallEventOccurrence#getInParameterValues <em>In Parameter Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>In Parameter Values</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventOccurrence#getInParameterValues()
	 * @see #getCallEventOccurrence()
	 * @generated
	 */
	EReference getCallEventOccurrence_InParameterValues();

	/**
	 * Returns the meta object for the containment reference list '{@link org.imt.pssm.reactive.model.statemachines.CallEventOccurrence#getOutParameterValues <em>Out Parameter Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Out Parameter Values</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventOccurrence#getOutParameterValues()
	 * @see #getCallEventOccurrence()
	 * @generated
	 */
	EReference getCallEventOccurrence_OutParameterValues();

	/**
	 * Returns the meta object for the containment reference '{@link org.imt.pssm.reactive.model.statemachines.CallEventOccurrence#getReturnValue <em>Return Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Return Value</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.CallEventOccurrence#getReturnValue()
	 * @see #getCallEventOccurrence()
	 * @generated
	 */
	EReference getCallEventOccurrence_ReturnValue();

	/**
	 * Returns the meta object for enum '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator <em>Boolean Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Boolean Binary Operator</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator
	 * @generated
	 */
	EEnum getBooleanBinaryOperator();

	/**
	 * Returns the meta object for enum '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator <em>Boolean Unary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Boolean Unary Operator</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator
	 * @generated
	 */
	EEnum getBooleanUnaryOperator();

	/**
	 * Returns the meta object for enum '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator <em>Integer Comparison Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Integer Comparison Operator</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator
	 * @generated
	 */
	EEnum getIntegerComparisonOperator();

	/**
	 * Returns the meta object for enum '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonOperator <em>String Comparison Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>String Comparison Operator</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonOperator
	 * @generated
	 */
	EEnum getStringComparisonOperator();

	/**
	 * Returns the meta object for enum '{@link org.imt.pssm.reactive.model.statemachines.PseudostateKind <em>Pseudostate Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Pseudostate Kind</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.PseudostateKind
	 * @generated
	 */
	EEnum getPseudostateKind();

	/**
	 * Returns the meta object for enum '{@link org.imt.pssm.reactive.model.statemachines.TransitionKind <em>Transition Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Transition Kind</em>'.
	 * @see org.imt.pssm.reactive.model.statemachines.TransitionKind
	 * @generated
	 */
	EEnum getTransitionKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StatemachinesFactory getStatemachinesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.CustomSystemImpl <em>Custom System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.CustomSystemImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getCustomSystem()
		 * @generated
		 */
		EClass CUSTOM_SYSTEM = eINSTANCE.getCustomSystem();

		/**
		 * The meta object literal for the '<em><b>Statemachine</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_SYSTEM__STATEMACHINE = eINSTANCE.getCustomSystem_Statemachine();

		/**
		 * The meta object literal for the '<em><b>Signals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_SYSTEM__SIGNALS = eINSTANCE.getCustomSystem_Signals();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_SYSTEM__OPERATIONS = eINSTANCE.getCustomSystem_Operations();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.SignalImpl <em>Signal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.SignalImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getSignal()
		 * @generated
		 */
		EClass SIGNAL = eINSTANCE.getSignal();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNAL__ATTRIBUTES = eINSTANCE.getSignal_Attributes();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.OperationImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>In Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__IN_PARAMETERS = eINSTANCE.getOperation_InParameters();

		/**
		 * The meta object literal for the '<em><b>Out Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__OUT_PARAMETERS = eINSTANCE.getOperation_OutParameters();

		/**
		 * The meta object literal for the '<em><b>Return</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__RETURN = eINSTANCE.getOperation_Return();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.EventTypeImpl <em>Event Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.EventTypeImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getEventType()
		 * @generated
		 */
		EClass EVENT_TYPE = eINSTANCE.getEventType();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.SignalEventTypeImpl <em>Signal Event Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.SignalEventTypeImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getSignalEventType()
		 * @generated
		 */
		EClass SIGNAL_EVENT_TYPE = eINSTANCE.getSignalEventType();

		/**
		 * The meta object literal for the '<em><b>Signal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNAL_EVENT_TYPE__SIGNAL = eINSTANCE.getSignalEventType_Signal();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.CallEventTypeImpl <em>Call Event Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.CallEventTypeImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getCallEventType()
		 * @generated
		 */
		EClass CALL_EVENT_TYPE = eINSTANCE.getCallEventType();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_EVENT_TYPE__OPERATION = eINSTANCE.getCallEventType_Operation();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.AttributeImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanAttributeImpl <em>Boolean Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanAttributeImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanAttribute()
		 * @generated
		 */
		EClass BOOLEAN_ATTRIBUTE = eINSTANCE.getBooleanAttribute();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.IntegerAttributeImpl <em>Integer Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.IntegerAttributeImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerAttribute()
		 * @generated
		 */
		EClass INTEGER_ATTRIBUTE = eINSTANCE.getIntegerAttribute();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StringAttributeImpl <em>String Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StringAttributeImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringAttribute()
		 * @generated
		 */
		EClass STRING_ATTRIBUTE = eINSTANCE.getStringAttribute();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.ConstraintImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanConstraintImpl <em>Boolean Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanConstraintImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanConstraint()
		 * @generated
		 */
		EClass BOOLEAN_CONSTRAINT = eINSTANCE.getBooleanConstraint();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_CONSTRAINT__EXPRESSION = eINSTANCE.getBooleanConstraint_Expression();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.IntegerConstraintImpl <em>Integer Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.IntegerConstraintImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerConstraint()
		 * @generated
		 */
		EClass INTEGER_CONSTRAINT = eINSTANCE.getIntegerConstraint();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTEGER_CONSTRAINT__EXPRESSION = eINSTANCE.getIntegerConstraint_Expression();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StringConstraintImpl <em>String Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StringConstraintImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringConstraint()
		 * @generated
		 */
		EClass STRING_CONSTRAINT = eINSTANCE.getStringConstraint();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_CONSTRAINT__EXPRESSION = eINSTANCE.getStringConstraint_Expression();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.NamedElementImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StateMachineImpl <em>State Machine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StateMachineImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStateMachine()
		 * @generated
		 */
		EClass STATE_MACHINE = eINSTANCE.getStateMachine();

		/**
		 * The meta object literal for the '<em><b>Regions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__REGIONS = eINSTANCE.getStateMachine_Regions();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.RegionImpl <em>Region</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.RegionImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getRegion()
		 * @generated
		 */
		EClass REGION = eINSTANCE.getRegion();

		/**
		 * The meta object literal for the '<em><b>Vertice</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__VERTICE = eINSTANCE.getRegion_Vertice();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__TRANSITIONS = eINSTANCE.getRegion_Transitions();

		/**
		 * The meta object literal for the '<em><b>State Machine</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__STATE_MACHINE = eINSTANCE.getRegion_StateMachine();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__STATE = eINSTANCE.getRegion_State();

		/**
		 * The meta object literal for the '<em><b>Current Vertex</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__CURRENT_VERTEX = eINSTANCE.getRegion_CurrentVertex();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.VertexImpl <em>Vertex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.VertexImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getVertex()
		 * @generated
		 */
		EClass VERTEX = eINSTANCE.getVertex();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__CONTAINER = eINSTANCE.getVertex_Container();

		/**
		 * The meta object literal for the '<em><b>Outgoing Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__OUTGOING_TRANSITIONS = eINSTANCE.getVertex_OutgoingTransitions();

		/**
		 * The meta object literal for the '<em><b>Incoming Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__INCOMING_TRANSITIONS = eINSTANCE.getVertex_IncomingTransitions();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.PseudostateImpl <em>Pseudostate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.PseudostateImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getPseudostate()
		 * @generated
		 */
		EClass PSEUDOSTATE = eINSTANCE.getPseudostate();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PSEUDOSTATE__KIND = eINSTANCE.getPseudostate_Kind();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PSEUDOSTATE__STATE = eINSTANCE.getPseudostate_State();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StateImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Regions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__REGIONS = eINSTANCE.getState_Regions();

		/**
		 * The meta object literal for the '<em><b>Entry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__ENTRY = eINSTANCE.getState_Entry();

		/**
		 * The meta object literal for the '<em><b>Do Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__DO_ACTIVITY = eINSTANCE.getState_DoActivity();

		/**
		 * The meta object literal for the '<em><b>Exit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__EXIT = eINSTANCE.getState_Exit();

		/**
		 * The meta object literal for the '<em><b>Deferrable Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__DEFERRABLE_TRIGGERS = eINSTANCE.getState_DeferrableTriggers();

		/**
		 * The meta object literal for the '<em><b>Connection Point</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__CONNECTION_POINT = eINSTANCE.getState_ConnectionPoint();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.FinalStateImpl <em>Final State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.FinalStateImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getFinalState()
		 * @generated
		 */
		EClass FINAL_STATE = eINSTANCE.getFinalState();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.TransitionImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__SOURCE = eINSTANCE.getTransition_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TARGET = eINSTANCE.getTransition_Target();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TRIGGERS = eINSTANCE.getTransition_Triggers();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__CONTAINER = eINSTANCE.getTransition_Container();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__KIND = eINSTANCE.getTransition_Kind();

		/**
		 * The meta object literal for the '<em><b>Effect</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__EFFECT = eINSTANCE.getTransition_Effect();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__CONSTRAINT = eINSTANCE.getTransition_Constraint();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.TriggerImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '<em><b>Event Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__EVENT_TYPE = eINSTANCE.getTrigger_EventType();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BehaviorImpl <em>Behavior</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.BehaviorImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBehavior()
		 * @generated
		 */
		EClass BEHAVIOR = eINSTANCE.getBehavior();

		/**
		 * The meta object literal for the '<em><b>Emitted Signals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR__EMITTED_SIGNALS = eINSTANCE.getBehavior_EmittedSignals();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.OperationBehaviorImpl <em>Operation Behavior</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.OperationBehaviorImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getOperationBehavior()
		 * @generated
		 */
		EClass OPERATION_BEHAVIOR = eINSTANCE.getOperationBehavior();

		/**
		 * The meta object literal for the '<em><b>Attribute Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_BEHAVIOR__ATTRIBUTE_VALUES = eINSTANCE.getOperationBehavior_AttributeValues();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.AttributeValueImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getAttributeValue()
		 * @generated
		 */
		EClass ATTRIBUTE_VALUE = eINSTANCE.getAttributeValue();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanAttributeValueImpl <em>Boolean Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanAttributeValueImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanAttributeValue()
		 * @generated
		 */
		EClass BOOLEAN_ATTRIBUTE_VALUE = eINSTANCE.getBooleanAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE = eINSTANCE.getBooleanAttributeValue_Attribute();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_ATTRIBUTE_VALUE__VALUE = eINSTANCE.getBooleanAttributeValue_Value();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.IntegerAttributeValueImpl <em>Integer Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.IntegerAttributeValueImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerAttributeValue()
		 * @generated
		 */
		EClass INTEGER_ATTRIBUTE_VALUE = eINSTANCE.getIntegerAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE = eINSTANCE.getIntegerAttributeValue_Attribute();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_ATTRIBUTE_VALUE__VALUE = eINSTANCE.getIntegerAttributeValue_Value();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StringAttributeValueImpl <em>String Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StringAttributeValueImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringAttributeValue()
		 * @generated
		 */
		EClass STRING_ATTRIBUTE_VALUE = eINSTANCE.getStringAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_ATTRIBUTE_VALUE__ATTRIBUTE = eINSTANCE.getStringAttributeValue_Attribute();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ATTRIBUTE_VALUE__VALUE = eINSTANCE.getStringAttributeValue_Value();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.ExpressionImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.IntegerComparisonExpressionImpl <em>Integer Comparison Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.IntegerComparisonExpressionImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerComparisonExpression()
		 * @generated
		 */
		EClass INTEGER_COMPARISON_EXPRESSION = eINSTANCE.getIntegerComparisonExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_COMPARISON_EXPRESSION__OPERATOR = eINSTANCE.getIntegerComparisonExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Operand1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTEGER_COMPARISON_EXPRESSION__OPERAND1 = eINSTANCE.getIntegerComparisonExpression_Operand1();

		/**
		 * The meta object literal for the '<em><b>Operand2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTEGER_COMPARISON_EXPRESSION__OPERAND2 = eINSTANCE.getIntegerComparisonExpression_Operand2();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.StringComparisonExpressionImpl <em>String Comparison Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StringComparisonExpressionImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringComparisonExpression()
		 * @generated
		 */
		EClass STRING_COMPARISON_EXPRESSION = eINSTANCE.getStringComparisonExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_COMPARISON_EXPRESSION__OPERATOR = eINSTANCE.getStringComparisonExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Operand1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_COMPARISON_EXPRESSION__OPERAND1 = eINSTANCE.getStringComparisonExpression_Operand1();

		/**
		 * The meta object literal for the '<em><b>Operand2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_COMPARISON_EXPRESSION__OPERAND2 = eINSTANCE.getStringComparisonExpression_Operand2();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanExpressionImpl <em>Boolean Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanExpressionImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanExpression()
		 * @generated
		 */
		EClass BOOLEAN_EXPRESSION = eINSTANCE.getBooleanExpression();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanBinaryExpressionImpl <em>Boolean Binary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanBinaryExpressionImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanBinaryExpression()
		 * @generated
		 */
		EClass BOOLEAN_BINARY_EXPRESSION = eINSTANCE.getBooleanBinaryExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_BINARY_EXPRESSION__OPERATOR = eINSTANCE.getBooleanBinaryExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Operand1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_BINARY_EXPRESSION__OPERAND1 = eINSTANCE.getBooleanBinaryExpression_Operand1();

		/**
		 * The meta object literal for the '<em><b>Operand2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_BINARY_EXPRESSION__OPERAND2 = eINSTANCE.getBooleanBinaryExpression_Operand2();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.BooleanUnaryExpressionImpl <em>Boolean Unary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.BooleanUnaryExpressionImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanUnaryExpression()
		 * @generated
		 */
		EClass BOOLEAN_UNARY_EXPRESSION = eINSTANCE.getBooleanUnaryExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_UNARY_EXPRESSION__OPERATOR = eINSTANCE.getBooleanUnaryExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_UNARY_EXPRESSION__OPERAND = eINSTANCE.getBooleanUnaryExpression_Operand();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.EventOccurrenceImpl <em>Event Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.EventOccurrenceImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getEventOccurrence()
		 * @generated
		 */
		EClass EVENT_OCCURRENCE = eINSTANCE.getEventOccurrence();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.CompletionEventOccurrenceImpl <em>Completion Event Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.CompletionEventOccurrenceImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getCompletionEventOccurrence()
		 * @generated
		 */
		EClass COMPLETION_EVENT_OCCURRENCE = eINSTANCE.getCompletionEventOccurrence();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETION_EVENT_OCCURRENCE__STATE = eINSTANCE.getCompletionEventOccurrence_State();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.SignalEventOccurrenceImpl <em>Signal Event Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.SignalEventOccurrenceImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getSignalEventOccurrence()
		 * @generated
		 */
		EClass SIGNAL_EVENT_OCCURRENCE = eINSTANCE.getSignalEventOccurrence();

		/**
		 * The meta object literal for the '<em><b>Signal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNAL_EVENT_OCCURRENCE__SIGNAL = eINSTANCE.getSignalEventOccurrence_Signal();

		/**
		 * The meta object literal for the '<em><b>Attribute Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNAL_EVENT_OCCURRENCE__ATTRIBUTE_VALUES = eINSTANCE.getSignalEventOccurrence_AttributeValues();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.impl.CallEventOccurrenceImpl <em>Call Event Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.impl.CallEventOccurrenceImpl
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getCallEventOccurrence()
		 * @generated
		 */
		EClass CALL_EVENT_OCCURRENCE = eINSTANCE.getCallEventOccurrence();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_EVENT_OCCURRENCE__OPERATION = eINSTANCE.getCallEventOccurrence_Operation();

		/**
		 * The meta object literal for the '<em><b>In Parameter Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_EVENT_OCCURRENCE__IN_PARAMETER_VALUES = eINSTANCE.getCallEventOccurrence_InParameterValues();

		/**
		 * The meta object literal for the '<em><b>Out Parameter Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_EVENT_OCCURRENCE__OUT_PARAMETER_VALUES = eINSTANCE.getCallEventOccurrence_OutParameterValues();

		/**
		 * The meta object literal for the '<em><b>Return Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_EVENT_OCCURRENCE__RETURN_VALUE = eINSTANCE.getCallEventOccurrence_ReturnValue();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator <em>Boolean Binary Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.BooleanBinaryOperator
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanBinaryOperator()
		 * @generated
		 */
		EEnum BOOLEAN_BINARY_OPERATOR = eINSTANCE.getBooleanBinaryOperator();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator <em>Boolean Unary Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.BooleanUnaryOperator
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getBooleanUnaryOperator()
		 * @generated
		 */
		EEnum BOOLEAN_UNARY_OPERATOR = eINSTANCE.getBooleanUnaryOperator();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator <em>Integer Comparison Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.IntegerComparisonOperator
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getIntegerComparisonOperator()
		 * @generated
		 */
		EEnum INTEGER_COMPARISON_OPERATOR = eINSTANCE.getIntegerComparisonOperator();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.StringComparisonOperator <em>String Comparison Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.StringComparisonOperator
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getStringComparisonOperator()
		 * @generated
		 */
		EEnum STRING_COMPARISON_OPERATOR = eINSTANCE.getStringComparisonOperator();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.PseudostateKind <em>Pseudostate Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.PseudostateKind
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getPseudostateKind()
		 * @generated
		 */
		EEnum PSEUDOSTATE_KIND = eINSTANCE.getPseudostateKind();

		/**
		 * The meta object literal for the '{@link org.imt.pssm.reactive.model.statemachines.TransitionKind <em>Transition Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.imt.pssm.reactive.model.statemachines.TransitionKind
		 * @see org.imt.pssm.reactive.model.statemachines.impl.StatemachinesPackageImpl#getTransitionKind()
		 * @generated
		 */
		EEnum TRANSITION_KIND = eINSTANCE.getTransitionKind();

	}

} //StatemachinesPackage
