/**
 * 
 */
package com.andesacademy.enote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gaalvarez
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3882714425246670529L;

	public UserNotFoundException(String exception) {
		super(exception);
	}
}
