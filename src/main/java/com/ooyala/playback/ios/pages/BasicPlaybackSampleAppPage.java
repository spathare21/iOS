package com.ooyala.playback.ios.pages;

import org.openqa.selenium.By;

public class BasicPlaybackSampleAppPage extends SampleAppBasePage {
	
	public final static By HLS = By.name("HLS Video");
	public final static By MP4 = By.name("MP4 Video");
	public final static By VOD_WITH_CCS = By.name("VOD with CCs");
	public final static By ASPECT_RATIO = By.name("4:3 Aspect Ratio");
	public final static By VAST_AD_PRE_ROLL = By.name("VAST Ad Pre-roll");
	public final static By VAST_AD_MID_ROLL = By.name("VAST Ad Mid-roll");
	public final static By VAST_AD_POST_ROLL = By.name("VAST Ad Post-roll");
	public final static By VAST_AD_WRAPPER = By.name("VAST Ad Wrapper");
	public final static By OOYALA_AD_PRE_ROLL = By.name("Ooyala Ad Pre-roll");
	public final static By OOYALA_AD_MID_ROLL = By.name("Ooyala Ad Mid-roll");
	public final static By OOYALA_AD_POST_ROLL = By.name("Ooyala Ad Post-roll");
	public final static By VERTICAL = By.name("Vertical 16:9");
	public final static By MULTI_AD_COMBINATION = By.name("Multi Ad combination");
	public final static By SCAN_CODE = By.name("Scan Code");
	
	
	public BasicPlaybackSampleAppPage() {
		assertCurrentPage(HLS);
	}

}
