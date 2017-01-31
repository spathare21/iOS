package com.ooyala.playback.ios.exceptions;

public class PageNotCurrentException extends RuntimeException {


	private static final long serialVersionUID = -7704718734841175705L;
	
	public PageNotCurrentException(String message) {
		super(message);
	}

	public PageNotCurrentException(String message, Throwable cause) {
		super(message, cause);
	}


}
