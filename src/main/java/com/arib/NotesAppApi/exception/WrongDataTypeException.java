package com.arib.NotesAppApi.exception;

public class WrongDataTypeException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongDataTypeException(String message) {
        super(message);
    }
}
