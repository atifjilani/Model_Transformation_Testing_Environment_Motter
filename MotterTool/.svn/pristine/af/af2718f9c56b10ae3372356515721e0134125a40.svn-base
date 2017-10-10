/**
 */
package SimpleClass.impl;

import SimpleClass.Attribute;
import SimpleClass.SimpleClassPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SimpleClass.impl.ClassImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link SimpleClass.impl.ClassImpl#isIs_persistent <em>Is persistent</em>}</li>
 *   <li>{@link SimpleClass.impl.ClassImpl#getAttrs <em>Attrs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassImpl extends ClassifierImpl implements SimpleClass.Class {
	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected SimpleClass.Class parent;

	/**
	 * The default value of the '{@link #isIs_persistent() <em>Is persistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIs_persistent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_PERSISTENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIs_persistent() <em>Is persistent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIs_persistent()
	 * @generated
	 * @ordered
	 */
	protected boolean is_persistent = IS_PERSISTENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAttrs() <em>Attrs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttrs()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> attrs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleClassPackage.Literals.CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleClass.Class getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject)parent;
			parent = (SimpleClass.Class)eResolveProxy(oldParent);
			if (parent != oldParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimpleClassPackage.CLASS__PARENT, oldParent, parent));
			}
		}
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleClass.Class basicGetParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(SimpleClass.Class newParent) {
		SimpleClass.Class oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleClassPackage.CLASS__PARENT, oldParent, parent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIs_persistent() {
		return is_persistent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIs_persistent(boolean newIs_persistent) {
		boolean oldIs_persistent = is_persistent;
		is_persistent = newIs_persistent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimpleClassPackage.CLASS__IS_PERSISTENT, oldIs_persistent, is_persistent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAttrs() {
		if (attrs == null) {
			attrs = new EObjectContainmentEList<Attribute>(Attribute.class, this, SimpleClassPackage.CLASS__ATTRS);
		}
		return attrs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimpleClassPackage.CLASS__ATTRS:
				return ((InternalEList<?>)getAttrs()).basicRemove(otherEnd, msgs);
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
			case SimpleClassPackage.CLASS__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
			case SimpleClassPackage.CLASS__IS_PERSISTENT:
				return isIs_persistent();
			case SimpleClassPackage.CLASS__ATTRS:
				return getAttrs();
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
			case SimpleClassPackage.CLASS__PARENT:
				setParent((SimpleClass.Class)newValue);
				return;
			case SimpleClassPackage.CLASS__IS_PERSISTENT:
				setIs_persistent((Boolean)newValue);
				return;
			case SimpleClassPackage.CLASS__ATTRS:
				getAttrs().clear();
				getAttrs().addAll((Collection<? extends Attribute>)newValue);
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
			case SimpleClassPackage.CLASS__PARENT:
				setParent((SimpleClass.Class)null);
				return;
			case SimpleClassPackage.CLASS__IS_PERSISTENT:
				setIs_persistent(IS_PERSISTENT_EDEFAULT);
				return;
			case SimpleClassPackage.CLASS__ATTRS:
				getAttrs().clear();
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
			case SimpleClassPackage.CLASS__PARENT:
				return parent != null;
			case SimpleClassPackage.CLASS__IS_PERSISTENT:
				return is_persistent != IS_PERSISTENT_EDEFAULT;
			case SimpleClassPackage.CLASS__ATTRS:
				return attrs != null && !attrs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (is_persistent: ");
		result.append(is_persistent);
		result.append(')');
		return result.toString();
	}

} //ClassImpl
