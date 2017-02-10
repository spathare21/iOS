package com.ooyala.playback.ios.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.MobileBy;

public class FreewheelSampleAppPage extends SampleAppBasePage {
	
	 final static Logger logger = Logger.getLogger(FreewheelSampleAppPage.class);
	
	public final static By FREEWHEEL_MULTI_MIDROLL = By.name("Freewheel Multi Midroll");
	public final static By FREEWHEEL_PREMIDPOST_OVERLAY = By.name("Freewheel PreMidPost Overlay");
	public final static By FREEWHEEL_OVERLAY = By.name("Freewheel Overlay");
	public final static By FREEWHEEL_PREMIDPOST = By.name("Freewheel PreMidPost");
	public final static By FREEWHEEL_POSTROLL = By.name("Freewheel Postroll");
	public final static By FREEWHEEL_MIDROLL = By.name("Freewheel Midroll");
	public final static By FREEWHEEL_PREROLL = By.name("Freewheel Preroll");
	
	private final By AD_OVERLAY = MobileBy.AccessibilityId("Advertisement");
	
	public FreewheelSampleAppPage() {
		assertCurrentPage(FREEWHEEL_MULTI_MIDROLL);
	}
	
	public FreewheelSampleAppPage verifyAdOverlayPresence() {
		Assert.assertTrue(isElementPresent(AD_OVERLAY), "Ad overlay not present");
		return this;
	}
	
	public FreewheelSampleAppPage waitForOverlayToDisapper() {
		logger.info("Waiting for Overlay to disappear....");
		waitForNotPresence(AD_OVERLAY);
		Assert.assertFalse(isElementPresent(AD_OVERLAY), "Ad overlay still present");
		return this;
	}

}
