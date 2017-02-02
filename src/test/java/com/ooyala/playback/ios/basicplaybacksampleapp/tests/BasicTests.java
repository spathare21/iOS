package com.ooyala.playback.ios.basicplaybacksampleapp.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ooyala.playback.ios.IOSBaseTest;
import com.ooyala.playback.ios.IOSEvents;
import com.ooyala.playback.ios.SampleAppProperties;
import com.ooyala.playback.ios.pages.BasicPlaybackSampleAppPage;
import com.ooyala.playback.ios.utils.WebDriverFactory;
import com.sun.jna.platform.win32.Advapi32Util.EventLogType;


public class BasicTests extends IOSBaseTest {
	
	
	SampleAppProperties appProperties = null;
	
    @Parameters({"platformVersion", "deviceName"})
    @BeforeClass 
    public void beforeClass( String platformVersion,  String deviceName) throws Exception {
    	appProperties = new SampleAppProperties();
    	appProperties.setPlatformVersion(platformVersion);
    	appProperties.setDeviceName(deviceName);
    	appProperties.loadSampleAppProperties("BasicplaybackSampleApp.properties");
    	WebDriverFactory.createIOSDriver(appProperties);
    	
    }
    
    @BeforeMethod
    public void beforeMethod() {
    	WebDriverFactory.getIOSDriver().launchApp();
    	new BasicPlaybackSampleAppPage().enableQAMode();
    }
    	
    
    @Test
    public  void HLS() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectHLS()
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "HLS video has started to play", 25000)
    							.tapScreenIfRequired()
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "HLS video has been paused", 25000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "HLS Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "HLS video has completed playing", 90000);
    							
    							
    							
    							
    	
    }


}
