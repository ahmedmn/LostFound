package com.LostFound.exceptions;

/**
*
* @author Ahmed
*/


public class LostFoundServiceException extends RuntimeException {

	public LostFoundServiceException() {
		super();
	}

	public LostFoundServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LostFoundServiceException(String message, Throwable cause) {
		super(message, cause);

	}

	public LostFoundServiceException(String message) {
		super(message);

	}

	public LostFoundServiceException(Throwable cause) {
		super(cause);
	}

}
