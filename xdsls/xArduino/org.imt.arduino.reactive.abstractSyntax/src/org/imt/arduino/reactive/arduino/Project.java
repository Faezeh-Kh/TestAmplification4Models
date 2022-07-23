/**
 */
package org.imt.arduino.reactive.arduino;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.imt.arduino.reactive.arduino.Project#getBoards <em>Boards</em>}</li>
 *   <li>{@link org.imt.arduino.reactive.arduino.Project#getSketches <em>Sketches</em>}</li>
 * </ul>
 *
 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getProject()
 * @model
 * @generated
 */
public interface Project extends EObject {
	/**
	 * Returns the value of the '<em><b>Boards</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.arduino.reactive.arduino.Board}.
	 * It is bidirectional and its opposite is '{@link org.imt.arduino.reactive.arduino.Board#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Boards</em>' containment reference list.
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getProject_Boards()
	 * @see org.imt.arduino.reactive.arduino.Board#getProject
	 * @model opposite="project" containment="true"
	 * @generated
	 */
	EList<Board> getBoards();

	/**
	 * Returns the value of the '<em><b>Sketches</b></em>' containment reference list.
	 * The list contents are of type {@link org.imt.arduino.reactive.arduino.Sketch}.
	 * It is bidirectional and its opposite is '{@link org.imt.arduino.reactive.arduino.Sketch#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sketches</em>' containment reference list.
	 * @see org.imt.arduino.reactive.arduino.ArduinoPackage#getProject_Sketches()
	 * @see org.imt.arduino.reactive.arduino.Sketch#getProject
	 * @model opposite="project" containment="true"
	 * @generated
	 */
	EList<Sketch> getSketches();

} // Project
