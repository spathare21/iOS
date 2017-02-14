package com.ooyala.playback.ios.imasampleapp.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ooyala.playback.ios.IOSBaseTest;
import com.ooyala.playback.ios.IOSEvents;
import com.ooyala.playback.ios.SampleAppProperties;
import com.ooyala.playback.ios.pages.IMASampleAppPage;
import com.ooyala.playback.ios.utils.WebDriverFactory;


public class BasicTests extends IOSBaseTest {
	
	
	SampleAppProperties appProperties = null;
	
    @BeforeClass 
    public void beforeClass() throws Exception {
    	appProperties = new SampleAppProperties();
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
						.verifyEvent(IOSEvents.AD_STARTED, "ClientSide_configured_IMA_Ads ad has started to play", 25000);
    	imaSampleAppPage
						.verifySkipAdPresence()
						.verifyEvent(IOSEvents.AD_COMPLETED, "ClientSide_configured_IMA_Ads ad has competed", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "ClientSide_configured_IMA_Ads Video has resumed to playing state", 25000)
						.letVideoPlayForSec(3)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "ClientSide_configured_IMA_Ads video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "ClientSide_configured_IMA_Ads Video has resumed to playing state", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "ClientSide_configured_IMA_Ads video has completed playing", 90000);

    }
    
    @Test
    public  void Pre_Mid_Post_skippable() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.PRE_MID_AND_POST_SKIPPABLE)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						.verifyEvent(IOSEvents.AD_STARTED, "Pre_Mid_Post_skippable ad has started to play", 25000);
    	imaSampleAppPage
						.verifySkipAdPresence()
						.verifyEvent(IOSEvents.AD_COMPLETED, "Pre_Mid_Post_skippable ad has competed", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Pre_Mid_Post_skippable video has started to play", 25000)
						.verifyEvent(IOSEvents.AD_STARTED, "Pre_Mid_Post_skippable ad has started to play", 40000);
    	imaSampleAppPage
						.verifySkipAdPresence()
						.verifyEvent(IOSEvents.AD_COMPLETED, "Pre_Mid_Post_skippable ad has competed", 40000)
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Pre_Mid_Post_skippable Video has resumed after ad", 25000)
						.letVideoPlayForSec(4)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Pre_Mid_Post_skippable video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Pre_Mid_Post_skippable Video has resumed to playing state", 25000)
						.verifyEvent(IOSEvents.AD_STARTED, "Pre_Mid_Post_skippable ad has started to play", 40000);
    	imaSampleAppPage
						.verifySkipAdPresence()
						.verifyEvent(IOSEvents.AD_COMPLETED, "Pre_Mid_Post_skippable ad has competed", 40000)
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Pre_Mid_Post_skippable video has completed playing", 90000);
    	
    }
    
    
    /**
     * Video length is too short to check pause and seek functionality
     * @throws Exception
     */
    @Test
    public  void IMA_skippable() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.SKIPPABLE)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						.verifyEvent(IOSEvents.AD_STARTED, "IMA_skippable ad has started to play", 25000);
    	imaSampleAppPage
						.verifySkipAdPresence()
						.verifyEvent(IOSEvents.AD_COMPLETED, "IMA_skippable ad has competed", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_STARTED, "IMA_skippable video has started to play", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "IMA_skippable Video has resumed to playing state", 25000)
						.verifyEvent(IOSEvents.AD_STARTED, "IMA_skippable ad has started to play", 40000);
    	imaSampleAppPage
						.verifySkipAdPresence()
						.verifyEvent(IOSEvents.AD_COMPLETED, "IMA_skippable ad has competed", 40000)
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "IMA_skippable video has completed playing", 90000);
    	
    }
    
    
    @Test
    public  void Podded_Pre_Mid_Post() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.PODDED_PRE_MID_POST)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						
						.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 25000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 25000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 25000)
						
    					.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Podded_Pre_Mid_Post video has started to play", 25000)
						
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 25000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 25000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 25000)
						
    					.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Podded_Pre_Mid_Post Video has resumed after ad", 25000)
						.letVideoPlayForSec(4)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Podded_Pre_Mid_Post video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Podded_Pre_Mid_Post Video has resumed to playing state", 25000)
						
						.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 40000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 40000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 40000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 40000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_Pre_Mid_Post ad has started to play", 40000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_Pre_Mid_Post ad has competed", 40000)
						
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Podded_Pre_Mid_Post video has completed playing", 90000);
    	
    }

    
    @Test
    public  void Podded_PreRoll() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.PODDED_PREROLL)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						
						.verifyEvent(IOSEvents.AD_STARTED, "Podded_PreRoll ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_PreRoll ad has competed", 25000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_PreRoll ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_PreRoll ad has competed", 25000)
    					
    					.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Podded_PreRoll video has started to play", 25000)
						.letVideoPlayForSec(4)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Podded_PreRoll video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Podded_PreRoll Video has resumed to playing state", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Podded_PreRoll video has completed playing", 90000);
    	
    }

    @Test
    public  void Podded_MidRoll() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.PODDED_MIDROLL)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						
    					.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Podded_MidRoll video has started to play", 25000)
						
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_MidRoll ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_MidRoll ad has competed", 25000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_MidRoll ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_MidRoll ad has competed", 25000)
    					
    					.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Podded_MidRoll Video has resumed to playing state", 25000)
    					.letVideoPlayForSec(4)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Podded_MidRoll video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Podded_MidRoll Video has resumed to playing state", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Podded_MidRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void Podded_PostRoll() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.PODDED_POSTROLL)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						.verifyEvent(IOSEvents.PLAYBACK_STARTED, "Podded_PostRoll video has started to play", 25000)
						.letVideoPlayForSec(4)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "Podded_PostRoll video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "Podded_PostRoll Video has resumed to playing state", 25000)
						
						.verifyEvent(IOSEvents.AD_STARTED, "Podded_PostRoll ad has started to play", 40000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_PostRoll ad has competed", 40000)
    					.verifyEvent(IOSEvents.AD_STARTED, "Podded_PostRoll ad has started to play", 40000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "Podded_PostRoll ad has competed", 40000)
    				
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "Podded_PostRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void AdRules_PreRoll() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.AD_RULES_PREROLL)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						
						.verifyEvent(IOSEvents.AD_STARTED, "AdRules_PreRoll ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "AdRules_PreRoll ad has competed", 25000)
    					
    					.verifyEvent(IOSEvents.PLAYBACK_STARTED, "AdRules_PreRoll video has started to play", 25000)
						.letVideoPlayForSec(4)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "AdRules_PreRoll video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "AdRules_PreRoll Video has resumed to playing state", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "AdRules_PreRoll video has completed playing", 90000);
    	
    }

    @Test
    public  void AdRules_MidRoll() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.AD_RULES_MIDROLL)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						
    					.verifyEvent(IOSEvents.PLAYBACK_STARTED, "AdRules_MidRoll video has started to play", 25000)
						.verifyEvent(IOSEvents.AD_STARTED, "AdRules_MidRoll ad has started to play", 25000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "AdRules_MidRoll ad has competed", 25000)
    					
    					.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "AdRules_MidRoll Video has resumed to playing state", 25000)
    					.letVideoPlayForSec(4)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "AdRules_MidRoll video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "AdRules_MidRoll Video has resumed to playing state", 25000)
						.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "AdRules_MidRoll video has completed playing", 90000);
    	
    }
    
    @Test
    public  void AdRules_PostRoll() throws Exception {
    	IMASampleAppPage imaSampleAppPage = new IMASampleAppPage();
    	imaSampleAppPage
						.selectVideo(IMASampleAppPage.AD_RULES_POSTROLL)
						.waitForNotificationAreaToLoad()
						.handleLoadingSpinner()
						.verifyEvent(IOSEvents.PLAYBACK_STARTED, "AdRules_PostRoll video has started to play", 25000)
						.letVideoPlayForSec(4)
						.pauseVideo()
						.verifyEvent(IOSEvents.PLAYBACK_PAUSED, "AdRules_PostRoll video has been paused", 25000)
						.seekVideoBack()
						.verifyEvent(IOSEvents.SEEK_STARTED, "Seek video started", 10000)
						.verifyEvent(IOSEvents.SEEK_COMPLETED, "Seek video completed", 10000)
						.playVideo()
						.verifyEvent(IOSEvents.PLAYBACK_RESUMED, "AdRules_PostRoll Video has resumed to playing state", 25000)
						
						.verifyEvent(IOSEvents.AD_STARTED, "AdRules_PostRoll ad has started to play", 40000)
    					.verifyEvent(IOSEvents.AD_COMPLETED, "AdRules_PostRoll ad has competed", 40000)
    					.verifyEvent(IOSEvents.PLAYBACK_COMPLETED, "AdRules_PostRoll video has completed playing", 90000);
    	
    }



}
