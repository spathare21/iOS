package com.ooyala.playback.ios;


/**
 * 
 * @author nraman
 * This class contains all the Events triggered from IOS sampleApps
 */
public enum IOSEvents {
	
	PLAYBACK_STARTED("Notification Received: playStarted"),
	PLAYBACK_PAUSED("Notification Received: stateChanged. state: paused"),
	PLAYBACK_RESUMED("Notification Received: stateChanged. state: playing"),
	PLAYBACK_COMPLETED("Notification Received: playCompleted"),
	
	AD_STARTED("Notification Received: adStarted"),
	AD_COMPLETED("Notification Received: adCompleted");
	
	
	//TODO All other events

	String event;
	
	IOSEvents(String event) {
		this.event = event;
	}
	
	public String getEvent() {
		return event;
	}

}
