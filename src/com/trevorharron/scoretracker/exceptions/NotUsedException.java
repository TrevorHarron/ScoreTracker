package com.trevorharron.scoretracker.exceptions;

@SuppressWarnings("serial")
public class NotUsedException extends Exception {

	public NotUsedException(String reason) {
		super(reason);
	}

}
