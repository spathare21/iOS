package com.ooyala.playback.ios.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.MobileBy;

public class IMASampleAppPage extends SampleAppBasePage {
	
	final static Logger logger = Logger.getLogger(IMASampleAppPage.class);
	
	public final static By CLIENTSIDE_CONFIGURED_IMA_ADS = By.name("Client-side configured IMA Ads");
	public final static By PRE_MID_AND_POST_SKIPPABLE = By.name("Pre, Mid and Post Skippable");
	public final static By SKIPPABLE = By.name("Skippable");
	public final static By PODDED_PRE_MID_POST = By.name("Podded Pre-Mid-Post");
	public final static By PODDED_POSTROLL = By.name("Podded Postroll");
	public final static By PODDED_MIDROLL = By.name("Podded Midroll");
	public final static By AD_RULES_POSTROLL = By.name("Ad-Rules Postroll");
	public final static By AD_RULES_MIDROLL = By.name("Ad-Rules Midroll");
	public final static By PODDED_PREROLL = By.name("Podded Preroll");
	public final static By AD_RULES_PREROLL = By.name("Ad-Rules Preroll");
	
	
	public IMASampleAppPage() {
		assertCurrentPage(CLIENTSIDE_CONFIGURED_IMA_ADS);
	}
	


}
