package com.ooyala.playback.ios.basicplaybacksampleapp.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ooyala.playback.ios.IOSBaseTest;
import com.ooyala.playback.ios.SampleAppProperties;
import com.ooyala.playback.ios.pages.BasicPlaybackSampleAppPage;
import com.ooyala.playback.ios.utils.WebDriverFactory;


public class BasicPlaybackSampleAppTests extends IOSBaseTest {
	
	
	SampleAppProperties appProperties = null;
	
    @Parameters({"platformVersion", "deviceName"})
    @BeforeClass 
    public void beforeClass( String platformVersion,  String deviceName) throws Exception {
    	appProperties = new SampleAppProperties();
    	appProperties.setPlatformVersion(platformVersion);
    	appProperties.setDeviceName(deviceName);
    	appProperties.loadSampleAppProperties("BasicplaybackSampleApp.properties");
    	WebDriverFactory.createIOSDriver(appProperties);
    	new BasicPlaybackSampleAppPage().enableQAMode();
    	
    }
    	
    
    @Test
    public  void HLS() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectHLS();
    							// TODO
    	
    }


}
