package com.trevorharron.scoretracker.exceptions;

@SuppressWarnings("serial")
public class NoPlayerException extends Exception{
	
	public NoPlayerException(String reason) {
		super(reason);
	}

}
