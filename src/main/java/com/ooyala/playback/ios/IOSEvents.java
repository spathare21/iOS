package com.ooyala.playback.ios;


public enum IOSEvents {
	
	PLAYBACK_STARTED("Notification Received: playStarted", "Video Started to Play", 35000);
	
	//TODO All other events

	String eventType, consoleMessage;
	int timeout;
	
	
	IOSEvents(String eventType, String consoleMessage, int timeout) {
		this.eventType = eventType;
		this.consoleMessage = consoleMessage;
		this.timeout = timeout;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	public String getconsoleMessage() {
		return consoleMessage;
	}
	
	public int getTimeout() {
		return timeout;
	}
	

}
