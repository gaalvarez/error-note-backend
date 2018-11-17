/**
 * 
 */
package com.andesacademy.enote.exception;

/**
 * @author a2g
 *
 */
public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = 6101566725853798296L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
