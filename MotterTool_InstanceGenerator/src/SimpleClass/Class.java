/**
 */
package SimpleClass;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link SimpleClass.Class#getParent <em>Parent</em>}</li>
 *   <li>{@link SimpleClass.Class#isIs_persistent <em>Is persistent</em>}</li>
 *   <li>{@link SimpleClass.Class#getAttrs <em>Attrs</em>}</li>
 * </ul>
 * </p>
 *
 * @see SimpleClass.SimpleClassPackage#getClass_()
 * @model
 * @generated
 */
public interface Class extends Classifier {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Class)
	 * @see SimpleClass.SimpleClassPackage#getClass_Parent()
	 * @model ordered="false"
	 * @generated
	 */
	Class getParent();

	/**
	 * Sets the value of the '{@link SimpleClass.Class#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Class value);

	/**
	 * Returns the value of the '<em><b>Is persistent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is persistent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is persistent</em>' attribute.
	 * @see #setIs_persistent(boolean)
	 * @see SimpleClass.SimpleClassPackage#getClass_Is_persistent()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	boolean isIs_persistent();

	/**
	 * Sets the value of the '{@link SimpleClass.Class#isIs_persistent <em>Is persistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is persistent</em>' attribute.
	 * @see #isIs_persistent()
	 * @generated
	 */
	void setIs_persistent(boolean value);

	/**
	 * Returns the value of the '<em><b>Attrs</b></em>' containment reference list.
	 * The list contents are of type {@link SimpleClass.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attrs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attrs</em>' containment reference list.
	 * @see SimpleClass.SimpleClassPackage#getClass_Attrs()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Attribute> getAttrs();

} // Class
