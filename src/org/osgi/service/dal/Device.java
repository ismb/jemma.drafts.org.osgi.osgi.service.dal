package org.osgi.service.dal;

/**
 * 
 * Represents the device in the OSGi service registry. Note that Device services are registered last. Before their
 * registration, there is Function services registration. The reverse order is used when the services are unregistered.
 * Device services are unregistered first before Function services.
 *
 */
public interface Device {
	static final String DEVICE_CATEGORY = "DAL";
	static final String SERVICE_UID = "dal.device.UID";
	static final String SERVICE_REFERENCE_UIDS = "dal.device.reference.UIDs";
	static final String SERVICE_DRIVER = "dal.device.driver";
	static final String SERVICE_NAME = "dal.device.name";
	static final String SERVICE_STATUS = "dal.device.status";
	static final String SERVICE_STATUS_DETAIL = "dal.device.status.detail";
	static final String SERVICE_HARDWARE_VENDOR = "dal.device.hardware.vendor";
	static final String SERVICE_HARDWARE_VERSION = "dal.device.hardware.version";
	static final String SERVICE_FIRMWARE_VENDOR = "dal.device.firmware.vendor";
	static final String SERVICE_FIRMWARE_VERSION = "dal.device.firmware.version";
	static final String SERVICE_TYPES = "dal.device.types";
	static final String SERVICE_MODEL = "dal.device.model";
	static final String SERVICE_SERIAL_NUMBER = "dal.device.serial.number";
	static final String SERVICE_DESCRIPTION = "dal.device.description";
	
	static final Integer STATUS_REMOVED = Integer.valueOf(0);
	static final Integer STATUS_OFFLINE = Integer.valueOf(1);
	static final Integer STATUS_ONLINE = Integer.valueOf(2);
	static final Integer STATUS_PROCESSING = Integer.valueOf(3);
	static final Integer STATUS_NOT_INITIALIZED = Integer.valueOf(4);
	static final Integer STATUS_NOT_CONFIGURED = Integer.valueOf(5);
	static final Integer STATUS_DETAIL_CONNECTING = Integer.valueOf(6);
	static final Integer STATUS_DETAIL_INITIALIZING = Integer.valueOf(7);
	static final Integer STATUS_DETAIL_REMOVING = Integer.valueOf(8);
	static final Integer STATUS_DETAIL_CONFIGURATION_NOT_APPLIED = Integer.valueOf(9);
	static final Integer STATUS_DETAIL_DEVICE_BROKEN = Integer.valueOf(10);
	static final Integer STATUS_DETAIL_DEVICE_COMMUNICATION_ERROR = Integer.valueOf(11);
	static final Integer STATUS_DETAIL_DEVICE_DATA_INSUFFICIENT = Integer.valueOf(12);
	static final Integer STATUS_DETAIL_DEVICE_NOT_ACCESSIBLE = Integer.valueOf(13);
	static final Integer STATUS_DETAIL_ERROR_APPLYING_CONFIGURATION = Integer.valueOf(14);
	static final Integer STATUS_DETAIL_IN_DUTY_CYCLE = Integer.valueOf(15);
	
	
	Object getServiceProperty(String propName);
	
	void remove() throws DeviceException, UnsupportedOperationException, SecurityException, IllegalStateException;
	
}
