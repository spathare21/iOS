package com.ooyala.playback.ios.basicplaybacksampleapp.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ooyala.playback.ios.IOSBaseTest;
import com.ooyala.playback.ios.IOSEvents;
import com.ooyala.playback.ios.SampleAppProperties;
import com.ooyala.playback.ios.pages.BasicPlaybackSampleAppPage;
import com.ooyala.playback.ios.utils.WebDriverFactory;


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
    
    @BeforeMethod(alwaysRun=true)
    public void beforeMethod() {
    	WebDriverFactory.getIOSDriver().launchApp();
    	new BasicPlaybackSampleAppPage().enableQAMode();
    }
    	
    
    @Test
    public  void HLS() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.HLS)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "HLS video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "HLS video has been paused", 25000)
    							.seekVideoForward()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "HLS Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "HLS video has completed playing", 90000);
    	
    }
    
    @Test
    public  void MP4() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.MP4)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "MP4 video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "MP4 video has been paused", 25000)
    							.seekVideoForward()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "MP4 Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "MP4 video has completed playing", 90000);
    	
    }
    
    @Test
    public  void AspectRatio() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.ASPECT_RATIO)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "ASPECT_RATIO video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "ASPECT_RATIO video has been paused", 25000)
    							.seekVideoForward()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "ASPECT_RATIO Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "ASPECT_RATIO video has completed playing", 90000);
    	
    }
    
    /**
     * PAUSE step is removed in this Testcase as the video length is to short
     */
    @Test
    public  void VAST_AD_PreRoll() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.VAST_AD_PRE_ROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.AD_STARTED, "VAST_AD_PreRoll ad has started playing", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "VAST_AD_PreRoll ad has complated playing", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "VAST_AD_PreRoll video has started to play", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "VAST_AD_PreRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "VAST_AD_PreRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void VAST_AD_MidRoll() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.VAST_AD_MID_ROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "VAST_AD_MidRoll video has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "VAST_AD_MidRoll ad has started playing", 25000)    		
    							.verifyEvent(IOSEvents.AD_COMPLETED, "VAST_AD_MidRoll ad has complated playing", 25000)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "VAST_AD_MidRoll video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "VAST_AD_MidRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "VAST_AD_MidRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void VAST_AD_PostRoll() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.VAST_AD_POST_ROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "VAST_AD_PostRoll video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "VAST_AD_PostRoll video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "VAST_AD_PostRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "VAST_AD_PostRoll ad has started playing", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "VAST_AD_PostRoll ad has complated playing", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "VAST_AD_PostRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void VAST_AD_Wrapper() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.VAST_AD_WRAPPER)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "VAST_AD_Wrapper video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "VAST_AD_Wrapper video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "VAST_AD_Wrapper Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "VAST_AD_Wrapper video has completed playing", 90000);
    	
    }
    
    /**
     * PAUSE step is removed in this Testcase as the video length is to short
     */
    @Test
    public  void OOYALA_AD_PreRoll() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.OOYALA_AD_PRE_ROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.AD_STARTED, "OOYALA_AD_PreRoll ad has started playing", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "OOYALA_AD_PreRoll ad has complated playing", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "OOYALA_AD_PreRoll video has started to play", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "OOYALA_AD_PreRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "OOYALA_AD_PreRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void OOYALA_AD_MidRoll() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.OOYALA_AD_MID_ROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "OOYALA_AD_MidRoll video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "OOYALA_AD_MidRoll video has been paused", 25000)
    							.seekVideoForward()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "OOYALA_AD_MidRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "OOYALA_AD_MidRoll ad has started playing", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "OOYALA_AD_MidRoll ad has complated playing", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "OOYALA_AD_MidRoll video has completed playing", 90000);
    	
    }
    
    /**
     * PAUSE step is removed in this Testcase as the video length is to short
     */
   
    @Test
    public  void OOYALA_AD_PostRoll() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.OOYALA_AD_POST_ROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "OOYALA_AD_PostRoll video has started to play", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "OOYALA_AD_PostRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "OOYALA_AD_PostRoll ad has started playing", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "OOYALA_AD_PostRoll ad has complated playing", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "OOYALA_AD_PostRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void Vertical() throws Exception {
    	BasicPlaybackSampleAppPage basicPlaybackSampleAppPage = new BasicPlaybackSampleAppPage();
    	basicPlaybackSampleAppPage
    							.selectVideo(BasicPlaybackSampleAppPage.VERTICAL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Vertical video has started to play", 25000)
    							.letVideoPlayForSec(5)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Vertical video has been paused", 25000)
    							.seekVideoForward()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Vertical Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Vertical video has completed playing", 90000);
    	
    }
    
 


}
