package com.just.practice.exception;

@SuppressWarnings("serial")
public class DataAaccessException extends BaseRuntimeException {

	public DataAaccessException() {
		super();
	}

	public DataAaccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAaccessException(String message) {
		super(message);
	}

	public DataAaccessException(Throwable cause) {
		super(cause);
	}

}
