package com.ooyala.playback.ios.pages;

import org.openqa.selenium.By;

public class BasicPlaybackSampleAppPage extends SampleAppBasePage {
	
	private final By HLS = By.name("HLS Video");
	
	public BasicPlaybackSampleAppPage() {
		assertCurrentPage(HLS);
	}
	
	public BasicPlaybackSampleAppPage selectHLS() {
		clickElement(HLS);
		assertCurrentPage(HLS);
		return this;
	}
	
	

}
