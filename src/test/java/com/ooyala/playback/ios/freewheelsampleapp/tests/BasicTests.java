package com.ooyala.playback.ios.freewheelsampleapp.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ooyala.playback.ios.IOSBaseTest;
import com.ooyala.playback.ios.IOSEvents;
import com.ooyala.playback.ios.SampleAppProperties;
import com.ooyala.playback.ios.pages.FreewheelSampleAppPage;
import com.ooyala.playback.ios.utils.WebDriverFactory;


public class BasicTests extends IOSBaseTest {
	
	
	SampleAppProperties appProperties = null;
	
    @BeforeClass 
    public void beforeClass() throws Exception {
    	appProperties = new SampleAppProperties();
    	appProperties.loadSampleAppProperties("FreewheelSampleApp.properties");
    	WebDriverFactory.createIOSDriver(appProperties);
    	
    }
    
    @BeforeMethod(alwaysRun=true)
    public void beforeMethod() {
    	WebDriverFactory.getIOSDriver().launchApp();
    	new FreewheelSampleAppPage().enableQAMode();
    }
    	
    
    @Test
    public  void Freewheel_PreRoll() throws Exception {
    	FreewheelSampleAppPage freewheelSampleAppPage = new FreewheelSampleAppPage();
    	freewheelSampleAppPage
    							.selectVideo(FreewheelSampleAppPage.FREEWHEEL_PREROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_PreRoll ad has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_PreRoll ad has competed", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Freewheel_PreRoll video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Freewheel_PreRoll video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_PreRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Freewheel_PreRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void Freewheel_MidRoll() throws Exception {
    	FreewheelSampleAppPage freewheelSampleAppPage = new FreewheelSampleAppPage();
    	freewheelSampleAppPage
    							.selectVideo(FreewheelSampleAppPage.FREEWHEEL_MIDROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Freewheel_MidRoll video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Freewheel_MidRoll video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_MidRoll ad has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_MidRoll ad has competed", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_MidRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Freewheel_MidRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void Freewheel_PostRoll() throws Exception {
    	FreewheelSampleAppPage freewheelSampleAppPage = new FreewheelSampleAppPage();
    	freewheelSampleAppPage
    							.selectVideo(FreewheelSampleAppPage.FREEWHEEL_POSTROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Freewheel_PostRoll video has started to play", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Freewheel_PostRoll video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_PostRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_PostRoll ad has started to play", 50000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_PostRoll ad has competed", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Freewheel_PostRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void Freewheel_MultiMidRoll() throws Exception {
    	FreewheelSampleAppPage freewheelSampleAppPage = new FreewheelSampleAppPage();
    	freewheelSampleAppPage
    							.selectVideo(FreewheelSampleAppPage.FREEWHEEL_MULTI_MIDROLL)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Freewheel_MultiMidRoll video has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_MultiMidRoll ad has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_MultiMidRoll ad has competed", 25000)
    							.letVideoPlayForSec(3)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Freewheel_MultiMidRoll video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_MultiMidRoll Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_MultiMidRoll ad has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_MultiMidRoll ad has competed", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Freewheel_MultiMidRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void Freewheel_PreMidPost_Overlay() throws Exception {
    	FreewheelSampleAppPage freewheelSampleAppPage = new FreewheelSampleAppPage();
    	freewheelSampleAppPage
    							.selectVideo(FreewheelSampleAppPage.FREEWHEEL_PREMIDPOST_OVERLAY)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_PreMidPost_Overlay ad has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_PreMidPost_Overlay ad has competed", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Freewheel_PreMidPost_Overlay video has started to play", 25000)
    							.letVideoPlayForSec(2);
    	freewheelSampleAppPage
    							.verifyAdOverlayPresence()
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_PreMidPost_Overlay ad has started to play", 40000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_PreMidPost_Overlay ad has competed", 40000)
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_PreMidPost_Overlay Video has resumed after ad", 25000)
    							.letVideoPlayForSec(4)
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Freewheel_PreMidPost_Overlay video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_PreMidPost_Overlay Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_PreMidPost_Overlay ad has started to play", 40000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_PreMidPost_Overlay ad has competed", 40000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Freewheel_PreMidPost_Overlay video has completed playing", 90000);
    	
    }
    
 
    @Test
    public  void Freewheel_Overlay() throws Exception {
    	FreewheelSampleAppPage freewheelSampleAppPage = new FreewheelSampleAppPage();
    	freewheelSampleAppPage
    							.selectVideo(FreewheelSampleAppPage.FREEWHEEL_OVERLAY)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Freewheel_Overlay video has started to play", 25000);
        freewheelSampleAppPage
        						.verifyAdOverlayPresence()
    							.letVideoPlayForSec(3);
    	freewheelSampleAppPage
    							.waitForOverlayToDisapper()
    							.pauseVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Freewheel_Overlay video has been paused", 25000)
    							.seekVideoBack()
    							.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
    							.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
    							.playVideo()
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_Overlay Video has resumed to playing state from paused state", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Freewheel_Overlay video has completed playing", 90000);
    	
    }
    
    @Test
    public  void Freewheel_PreMidPost() throws Exception {
    	FreewheelSampleAppPage freewheelSampleAppPage = new FreewheelSampleAppPage();
    	freewheelSampleAppPage
    							.selectVideo(FreewheelSampleAppPage.FREEWHEEL_PREMIDPOST)
    							.waitForNotificationAreaToLoad()
    							.handleLoadingSpinner()
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_PreMidPost ad has started to play", 25000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_PreMidPost ad has competed", 25000)
    							.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Freewheel_PreMidPost video has started to play", 25000)
    							.letVideoPlayForSec(2)
						    	.pauseVideo()
								.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Freewheel_PreMidPost video has been paused", 25000)
								.seekVideoBack()
								.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
								.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
								.playVideo()
								.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_PreMidPost Video has resumed to playing state from paused state", 25000)
								.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_PreMidPost ad has started to play", 40000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_PreMidPost ad has competed", 40000)
    							.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Freewheel_PreMidPost Video has resumed after ad", 25000)
    							.verifyEvent(IOSEvents.AD_STARTED, "Freewheel_PreMidPost ad has started to play", 40000)
    							.verifyEvent(IOSEvents.AD_COMPLETED, "Freewheel_PreMidPost ad has competed", 40000)
    							.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Freewheel_PreMidPost video has completed playing", 90000);
    	
    }



}
