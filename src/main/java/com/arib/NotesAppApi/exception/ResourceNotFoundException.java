package com.arib.NotesAppApi.exception;

//@ResponseStatus(code = HttpStatus.NOT_FOUND) //when not customising the response
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
