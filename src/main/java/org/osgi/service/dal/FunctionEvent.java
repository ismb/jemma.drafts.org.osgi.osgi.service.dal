package org.osgi.service.dal;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.event.Event;

public class FunctionEvent extends Event {
	public static final String EVENT_PACKAGE = "org/osgi/service/dal/";
	public static final String EVENT_CLASS = "org/osgi/service/dal/FunctionEvent/";
	public static final String TOPIC_PROPERTY_CHANGED = "org/osgi/service/dal/FunctionEvent/PROPERTY_CHANGED";
	public static final String PROPERTY_FUNCTION_UID = "dal.function.UID";
	public static final String PROPERTY_FUNCTION_PROPERTY_NAME = "dal.function.property.name";
	public static final String PROPERTY_FUNCTION_PROPERTY_VALUE = "dal.function.property.value";

	public FunctionEvent(String topic, Dictionary properties) {
		super(topic, properties);
	}

	public FunctionEvent(String topic, Map properties) {
		super(topic, properties);
	}

	public FunctionEvent(final String topic, final String functionUID, final String propName, final FunctionData propValue) {
		// FIXME is there a better way to do this ?
		super(topic, new HashMap<String, Object>() {
			{
				put(PROPERTY_FUNCTION_UID, functionUID);
				put(PROPERTY_FUNCTION_PROPERTY_NAME, propName);
				put(PROPERTY_FUNCTION_PROPERTY_VALUE, propValue);
			}
		});
	}

	public String getFunctionUID() {
		return (String) this.getProperty(PROPERTY_FUNCTION_UID);
	}

	public String getFunctionPorpertyName() {
		return (String) this.getProperty(PROPERTY_FUNCTION_PROPERTY_NAME);
	}

	public FunctionData getFunctionPropertyValue() {
		return (FunctionData) this.getProperty(PROPERTY_FUNCTION_PROPERTY_VALUE);
	}
}
