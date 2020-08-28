package com.rbc.databanqbackend.exception;

@SuppressWarnings("serial")
public class BizException extends Exception {
	
	public BizException() {
		super();
	}
	public BizException(String errorMessage) {
		super(errorMessage);
	}
}
