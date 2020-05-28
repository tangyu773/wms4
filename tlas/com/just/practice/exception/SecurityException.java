package com.just.practice.exception;

@SuppressWarnings("serial")
public class SecurityException extends BaseRuntimeException {

	public SecurityException() {
		super();
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}

}
