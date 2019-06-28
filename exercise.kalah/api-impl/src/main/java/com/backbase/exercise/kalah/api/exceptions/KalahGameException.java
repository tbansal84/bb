package com.backbase.exercise.kalah.api.exceptions;

/**
 * Base unchecked exception for all exceptions.
 *
 * @author Yengibar Manasyan
 */
public abstract class KalahGameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Init based on provided message.
	 *
	 * @param message message of exception
	 */
	public KalahGameException(String message) {
		super(message);
	}

	/**
	 * Init based on provided {@code message} and {@code cause}.
	 *
	 * @param message message of exception
	 * @param cause   reason of this exception
	 */
	public KalahGameException(String message, Exception cause) {
		super(message, cause);
	}

	public KalahGameException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KalahGameException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public KalahGameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public KalahGameException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	
}
