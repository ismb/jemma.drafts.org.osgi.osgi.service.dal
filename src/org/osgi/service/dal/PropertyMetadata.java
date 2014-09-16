package org.osgi.service.dal;

import java.util.Map;

public interface PropertyMetadata {
	public static final int PROPERTY_ACCESS_READABLE = 1;
	public static final int PROPERTY_ACCESS_WRITABLE = 2;
	public static final int PROPERTY_ACCESS_EVENTABLE = 4;
	
	public static final String PROPERTY_ACCESS = "property.access";
	public static final String DESCRIPTION = "description";
	public static final String UNITS = "units";
	
	Map getMetadata(String unit);
	
	Object getResolution(String unit) throws IllegalArgumentException;
	
	FunctionData[] getEnumValues(String unit) throws IllegalArgumentException;
	
	FunctionData getMinValue(String unit) throws IllegalArgumentException;
	
	FunctionData getMaxValue(String unit) throws IllegalArgumentException;
	
}
