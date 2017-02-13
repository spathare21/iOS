package com.ooyala.playback.ios.imasampleapp.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ooyala.playback.ios.IOSBaseTest;
import com.ooyala.playback.ios.IOSEvents;
import com.ooyala.playback.ios.SampleAppProperties;
import com.ooyala.playback.ios.pages.FreewheelSampleAppPage;
import com.ooyala.playback.ios.pages.IMASampleAppPage;
import com.ooyala.playback.ios.utils.WebDriverFactory;


public class BasicTests extends IOSBaseTest {
	
	
	SampleAppProperties appProperties = null;
	
    @Parameters({"platformVersion", "deviceName"})
    @BeforeClass 
    public void beforeClass( String platformVersion,  String deviceName) throws Exception {
    	appProperties = new SampleAppProperties();
    	appProperties.setPlatformVersion(platformVersion);
    	appProperties.setDeviceName(deviceName);
    	appProperties.loadSampleAppProperties("IMASampleApp.properties");
    	WebDriverFactory.createIOSDriver(appProperties);
    	
    }
    
    @BeforeMethod(alwaysRun=true)
    public void beforeMethod() {
    	WebDriverFactory.getIOSDriver().launchApp();
    	new IMASampleAppPage().enableQAMode();
    }
    	
    
    @Test
    public  void ClientSide_configured_IMA_Ads() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
    							.selectVideo(IMASampleAppPage.CLIENTSIDE_CONFIGURED_IMA_ADS)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "ClientSide_configured_IMA_Ads video has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "ClientSide_configured_IMA_Ads ad has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "ClientSide_configured_IMA_Ads ad has competed", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "ClientSide_configured_IMA_Ads Video has resumed to playing state from paused state", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "ClientSide_configured_IMA_Ads video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "ClientSide_configured_IMA_Ads Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "ClientSide_configured_IMA_Ads video has completed playing", 90000);
    	
    }
    



}
