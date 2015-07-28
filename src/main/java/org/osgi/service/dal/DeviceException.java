package org.osgi.service.dal;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class DeviceException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int CODE_UNKNOWN = 1;
	public static final int CODE_COMMUNICATION_ERROR = 2;
	public static final int CODE_TIMEOUT = 3;
	public static final int CODE_NOT_INITIALIZED = 4;
	public static final int CODE_NO_DATA = 5;
	
	private int code = CODE_UNKNOWN;
	
	public DeviceException(String message) {
		super(message);
	}

	
	public DeviceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DeviceException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}
	
	
	public int getCode() {
		return code;
	}
	
	@Override
	public Throwable getCause() {
		return super.getCause();
	}
	
	@Override
	public void printStackTrace() {
		System.err.println(this.getStackTrace());
	}
	
	@Override
	public void printStackTrace(PrintStream s) {
		s.println(this.getStackTrace());
	}
	
	@Override
	public void printStackTrace(PrintWriter s) {
		s.println(this.getStackTrace());
	}
}

