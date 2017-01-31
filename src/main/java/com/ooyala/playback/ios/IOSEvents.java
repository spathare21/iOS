package com.ooyala.playback.ios;


/**
 * 
 * @author nraman
 * This class contains all the Events triggered from IOS sampleApps
 */
public enum IOSEvents {
	
	PLAYBACK_STARTED("Notification Received: playStarted");
	
	//TODO All other events

	String eventType;
	
	IOSEvents(String eventType) {
		this.eventType = eventType;
	}
	
	public String getEventType() {
		return eventType;
	}

}
