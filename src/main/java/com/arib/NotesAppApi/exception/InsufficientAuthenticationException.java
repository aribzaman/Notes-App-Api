package com.arib.NotesAppApi.exception;

public class InsufficientAuthenticationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientAuthenticationException(String msg) {
		super(msg);
	}

}
