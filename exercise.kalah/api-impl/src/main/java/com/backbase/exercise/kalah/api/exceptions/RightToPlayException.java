package com.backbase.exercise.kalah.api.exceptions;

/**
 * Throw it when player want to play not during his/her turn.
 *
 * @author Yengibar Manasyan
 */
public class RightToPlayException extends KalahGameException {

	private static final long serialVersionUID = 1L;

	public RightToPlayException(String message) {
		super(message);
	}
}
