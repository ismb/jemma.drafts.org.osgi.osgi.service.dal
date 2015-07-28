package org.osgi.service.dal;

import java.util.Map;

abstract public class FunctionData extends Object implements Comparable {
	public static final String FIELD_TIMESTAMP = "timestamp";
	public static final String FIELD_METADATA = "metadata";
	public static final String META_INFO_DESCRIPTION = "description";
	public final long timestamp;
	public final Map metadata;
	
	public FunctionData(Map fields) {
		if(fields == null) {
			throw new NullPointerException();
		}
		if(fields.containsKey(FIELD_TIMESTAMP)) {
			timestamp = (Long)fields.get(FIELD_TIMESTAMP);
		} else {
			timestamp = Long.MIN_VALUE;
		}
		if(fields.containsKey(FIELD_METADATA)) {
			metadata = (Map) fields.get(FIELD_METADATA);
		} else {
			metadata = null;
		}
	}
	
	public FunctionData(long timestamp, Map metadata) {
		this.timestamp = timestamp;
		this.metadata = metadata;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public Map getMetadata() {
		return metadata;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        FunctionData otherFunct = (FunctionData) other;
		if(this.getTimestamp()==otherFunct.getTimestamp() && this.getMetadata().equals(otherFunct.getMetadata())) {
			return true;
		}
		return false;
	}
	
	@Override 
	public int hashCode() {
		return Long.valueOf(this.getTimestamp()).hashCode() * this.getMetadata().hashCode();  
	}
}