package org.osgi.service.dal;

public interface Function {
	static final String SERVICE_UID = "dal.function.UID";
	static final String SERVICE_TYPE = "dal.function.type";
	static final String SERVICE_VERSION = "dal.function.version";
	static final String SERVICE_DEVICE_UID = "dal.function.device.UID";
	static final String SERVICE_REFERENCE_UIDS = "dal.function.reference.UIDs";
	static final String SERVICE_DESCRIPTION = "dal.function.description";
	static final String SERVICE_OPERATION_NAMES = "dal.function.operation.names";
	static final String SERVICE_PROPERTY_NAMES = "dal.function.property.names";
	
	
	PropertyMetadata getPropertyMetadata(String propertyName) throws IllegalArgumentException;
	
	OperationMetadata getOperationMetadata(String operationName) throws IllegalArgumentException;
	
	Object getServiceProperty(String propName);
}
