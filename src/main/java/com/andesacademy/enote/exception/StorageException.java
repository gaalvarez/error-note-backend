/**
 * 
 */
package com.andesacademy.enote.exception;

/**
 * @author a2g
 *
 */
public class StorageException extends RuntimeException {

	private static final long serialVersionUID = -4524535033689606226L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
