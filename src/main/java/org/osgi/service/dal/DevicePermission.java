package org.osgi.service.dal;

import java.security.BasicPermission;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

public final class DevicePermission extends BasicPermission {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6790743754313046504L;

	public static final String ACTION_REMOVE = "remove";
	
	private final Device device;
	private final Filter osgiFilter;
	
	public DevicePermission(String filter, String action) {
		super(filter, action);
		if(!action.equals(ACTION_REMOVE)) {
			throw new IllegalArgumentException();
		}
		try {
			osgiFilter = FrameworkUtil.createFilter(filter);
		} catch (InvalidSyntaxException e) {
			throw new IllegalArgumentException();
		}
		device = null;
	}
	
	public DevicePermission(Device device, String action) {
		// FIXME giusto usare come filter nel caso di device l'id univoco?
		super("("+Device.SERVICE_UID+"="+(String)device.getServiceProperty(Device.SERVICE_UID)+")", action);
		this.device = device;
		this.osgiFilter = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        DevicePermission other = (DevicePermission) obj;
		if(this.getName().equals(other.getName()) && this.getActions().equals(other.getActions())) {
			return true;
		}
		return false;
	}
	
	@Override 
	public int hashCode() {
		return (device==null) ? this.getName().hashCode() * this.getActions().hashCode() : this.device.hashCode() * this.getActions().hashCode();  
	}
	
	@Override
	public String getActions() {
		return ACTION_REMOVE;
	}
	
	@Override
	public boolean implies(Permission p) {
		if (!(p instanceof DevicePermission)) {
			return false;
		}
		// FIXME da verificare se e' il modo corretto per capire se quale costruttore e' stato usato
		// in teoria no, perche' lo stesso filtro potrebbe essere usato anche con l'altro costruttore
		if(p.getName().startsWith("("+Device.SERVICE_UID)) {
			throw new IllegalArgumentException();
		}
		if(this==p) {
			return true;
		}
		// FIXME come si capisce se un filter implica un altro? Così è corretto?
		// Questo modo di verificare l'implicazione è legato a come si è scelto di implementare
		// il costruttore con Device come parametro, cioè inserendo come filtro il service UID
		return osgiFilter.match(FrameworkUtil.getBundle(this.getClass()).getBundleContext().getServiceReference(p.getName()));
	}
	
	@Override
	public PermissionCollection newPermissionCollection() {
		return new DevicePermissionCollection();
	}
}


/**
 * Class returned by {@link DevicePermission#newPermissionCollection()}.
 *
 * FIXME ispirato da 
 * http://www.massapi.com/source/jdk1.6.0_17/src/javax/management/MBeanServerPermission.java.html
 * 
 * @serial include
 */
class DevicePermissionCollection extends PermissionCollection {
    /** @serial Null if no permissions in collection, otherwise a
    single permission that is the union of all permissions that
    have been added.  */
    private DevicePermission collectionPermission;
 
    private static final long serialVersionUID = -5661980843569388590L;
 
    public synchronized void add(Permission permission) {
    	if (!(permission instanceof DevicePermission)) {
    		final String msg = "Permission not a DevicePermission: " + permission;
    		throw new IllegalArgumentException(msg);
    	}
    	if (isReadOnly()) {
    		throw new SecurityException("Read-only permission collection");
    	}
    	DevicePermission dp = (DevicePermission) permission;
    	if (collectionPermission == null) {
    		collectionPermission = dp;
    	} else if (!collectionPermission.implies(permission)) {
    		// FIXME è corretto così?
    		collectionPermission = new DevicePermission(collectionPermission.getName() + " | " + permission.getName(), DevicePermission.ACTION_REMOVE);
    	}
    }
 
    public synchronized boolean implies(Permission permission) {
    	return (collectionPermission != null &&
    			collectionPermission.implies(permission));
    }
 
    public synchronized Enumeration<Permission> elements() {
    	Set<Permission> set;
    	if (collectionPermission == null) {
    		set = Collections.emptySet();
    	} else {
    		set = Collections.singleton((Permission) collectionPermission);
    	}
    	return Collections.enumeration(set);
    }
}