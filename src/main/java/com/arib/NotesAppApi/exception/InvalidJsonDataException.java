package com.arib.NotesAppApi.exception;

public class InvalidJsonDataException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidJsonDataException(String message) {
        super(message);
    }
}
