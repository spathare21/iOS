package Tests.BasicSampleAppTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;
import Utils.EventVerification;
import Utils.LoadPropertyValues;
import Utils.SDKVersion;
import Utils.SetupiOSDriver;
import Utils.getLog;
import Utils.logging;
import io.appium.java_client.AppiumDriver;
import pageObject.BaseClass;

/**
 * Created by Madhav on 10/27/16.
 */
public class BasicTestsBasicPlayback extends BaseClass {

    //Variable for storing UUID of the device
    public static String ud;
    //Variable for storing IOS Driver
    private AppiumDriver driver;

    //String LogFilePath;
    logging _utils = new logging();
    boolean found=false;

    // parameters obtained from XML file // TODO: 10/31/16 move these parameters to utils Properties file
    @Parameters({"platformVersion", "deviceName", "logFilePath" })

    @BeforeClass // Will be executed before any of the test run.
    public void beforeTest( String platformVersion,  String deviceName, String logFilePath) throws Exception {

        EventVerification.count =0 ;

        //loading the property values
        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("BasicPlaybackSampleApp.properties");

        // Getting UUID of the device programatically
        ud = getLog.getUdid();
        System.out.println("valued of ud is " +ud);

        // Setting up IOS Driver
        SetupiOSDriver setUpdriver = new SetupiOSDriver();
        driver = setUpdriver.setUpandReturniOSDriver( p.getProperty("appFilePath"),  p.getProperty("appName"),platformVersion, deviceName, ud);

        Thread.sleep(2000);

    }

    @AfterClass // Will be executed once all the tests are completed.
    //public void tearDown() throws Exception {
    public void afterTest() throws Exception {
        //closing the app under test
        driver.closeApp();
        System.out.println("closing app ");

        //Loading the property file to get App name .
        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("BasicPlaybackSampleApp.properties");
        String app = p.getProperty("app_Name");
        Thread.sleep(1000);

        //Uninstall the app //todo refactor appUninstall method
        getLog.appUninstall(app);

        driver.quit();
    }

    @BeforeMethod
    public void  beforeMethod() throws IOException
    {
        System.out.println("in before method \n");
    }

    @AfterMethod
    public void afterMethod() throws IOException, InterruptedException {
        System.out.println("in after method \n");
        BaseClass.masterBtn(driver);
        Thread.sleep(2000);

    }



    @Test
    public  void HLS() throws Exception {

        EventVerification ev = new EventVerification();
        try {
            System.out.println("In Basic Playback Sample App Home Screen \n");
            Thread.sleep(2000);

            //todo Write code to check if QA mode is enabled , only if its disabled , enable it
            //Enable QA Mode . Should be moved to Before Test ?
            WebElement w = driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]"));
            System.out.println(" If QA Mode is enabled \n"+ w.getAttribute("value"));
            if(w.getAttribute("value").equals("false")){
                driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]")).click();
            }
            System.out.println(" If QA Mode is enabled \n"+ w.getAttribute("value"));

            //Click on HLS Video
            driver.findElement(By.name("HLS Video")).click();

            BaseClass.waitForElementBasedOnXpath(driver,"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]");
            Thread.sleep(5000);

            // Verify playStarted event
            ev.verifyEvent(driver,"Notification Received: playStarted", "HLS video has started to play", 25000);

            Thread.sleep(5000);


            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent(driver,"Notification Received: stateChanged. state: paused", "HLS video has been paused ", 25000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent(driver,"Notification Received: stateChanged. state: playing", "HLS Video has resumed to playing state from paused state", 40000);

            // Verify playCompleted event
            ev.verifyEvent(driver,"Notification Received: playCompleted", "HLS video has completed playing", 90000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    //@Test
    public  void MP4() throws Exception {

        EventVerification ev = new EventVerification();
        try {
            System.out.println("In Basic Playback Sample App Home Screen \n");
            Thread.sleep(2000);


            //Enable QA Mode . Should be moved to Before Test ?
            WebElement w = driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]"));
            if(w.getAttribute("value").equals("false")){
                driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]")).click();
            }

            //Click on MP4 Video
            driver.findElement(By.name("MP4 Video")).click();

            BaseClass.waitForElementBasedOnXpath(driver,"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]");
            Thread.sleep(5000);

            // Verify playStarted event
            ev.verifyEvent(driver,"Notification Received: playStarted", "MP4 video has started to play", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent(driver,"Notification Received: stateChanged. state: paused", "MP4 video has been paused", 30000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent(driver,"Notification Received: stateChanged. state: playing", "MP4 Video has resumed to playing state from paused state", 50000);


            // Verify playCompleted event
            ev.verifyEvent(driver,"Notification Received: playCompleted", "MP4 video has completed playing", 90000);
        }

        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    //@Test
    public  void vodCC() throws Exception {
        EventVerification ev = new EventVerification();
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 2);

            ev.verifyEvent("Notification Received: playStarted", "VOD with CC video has been playing started", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 30000);

            // Verify CC button
            BaseClass.ccBtn(driver, "normal");
            boolean found1;
            try {
                found1 = driver.findElementByClassName("UIATableCell").isDisplayed();
            } catch (Exception e) {
                found1 = false;
            }
            if (!found1)
                Assert.assertTrue(found1);

            Thread.sleep(1000);

            // Verify Done button on CC
            BaseClass.ccDoneBtn(driver);
            boolean found2;
            try {
                found2 = driver.findElementByClassName("UIATableCell").isDisplayed();
            } catch (Exception e) {
                found2 = false;
            }
            if (found2)
                Assert.assertTrue(found2);


            // Verify playing event at normal screen
            Thread.sleep(5000);

            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "video is in playing state", 50000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 90000);
        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    //@Test
    public  void aspectRatio() throws Exception {

        EventVerification ev = new EventVerification();
        try {
            System.out.println("In Basic Playback Sample App Home Screen \n");
            Thread.sleep(2000);

            //Enable QA Mode . Should be moved to Before Test ?
            WebElement w = driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]"));
            if(w.getAttribute("value").equals("false")){
                driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]")).click();
            }

            //Click on 4:3 Aspect Ratio Video
            driver.findElement(By.name("4:3 Aspect Ratio")).click();

            BaseClass.waitForElementBasedOnXpath(driver,"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]");
            Thread.sleep(5000);

            // Verify playStarted event
            ev.verifyEvent(driver,"Notification Received: playStarted", "Aspect Ratio video has started to play", 20000);

            Thread.sleep(5000);
            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);

            // Pause Playing video
            ev.verifyEvent(driver,"Notification Received: stateChanged. state: paused", "Aspect Ratio video has been paused", 30000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent(driver,"Notification Received: stateChanged. state: playing", "Aspect Ratio has resumed to playing state from paused state", 40000);


            // Verify playCompleted event
            ev.verifyEvent(driver,"Notification Received: playCompleted", "Aspect Ratio video has completed playing", 900000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    //@Test
    public void vast_PreRoll() throws InterruptedException {

        EventVerification ev = new EventVerification();
        try {
            System.out.println("In Basic Playback Sample App Home Screen \n");
            Thread.sleep(2000);

            //Enable QA Mode . Should be moved to Before Test ?
            WebElement w = driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]"));
            if(w.getAttribute("value").equals("false")){
                driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]")).click();
            }

            //Click on VAST Ad Pre-roll Video
            driver.findElement(By.name("VAST Ad Pre-roll")).click();

            BaseClass.waitForElementBasedOnXpath(driver,"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]");
            Thread.sleep(5000);

            //adStarted event verification
            ev.verifyEvent(driver,"Notification Received: adStarted", "Preroll ad has started playing", 20000);

            Thread.sleep(5000);

            // adCompleted event verification
            ev.verifyEvent(driver,"Notification Received: adCompleted", "Preroll ad has completed playing", 30000);

            // Verify playStarted event
            ev.verifyEvent(driver,"playStarted", "Vast Preroll video has started to play", 40000);

            //  Commenting Play pause sequence because video length is too small.....
            //Thread.sleep(2000);
            // Verify pause event at normal screen
            //play_pauseBtn(driver);
            //ev.verifyEvent(driver,"Notification Received: stateChanged. state: paused", "Vast Preroll video has paused", 50000);
            // Verify playing event at normal screen
            //BaseClass.play_pauseBtn(driver);
            //ev.verifyEvent(driver,"Notification Received: stateChanged. state: playing", "Vast Preroll video has started playing again", 60000);
            //........................................................................

            ev.verifyEvent(driver,"Notification Received: playCompleted", "Vast Preroll video has completed playing", 90000);

        }
        catch (Exception e)
        {

            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    //@Test
    public  void vast_Midroll() throws Exception {

        EventVerification ev = new EventVerification();
        try {
            System.out.println("In Basic Playback Sample App Home Screen \n");
            Thread.sleep(2000);

            //Enable QA Mode . Should be moved to Before Test ?
            WebElement w = driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]"));
            if(w.getAttribute("value").equals("false")){
                driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]")).click();
            }

            //Click on VAST Ad Mid-roll Video
            driver.findElement(By.name("VAST Ad Mid-roll")).click();

            BaseClass.waitForElementBasedOnXpath(driver,"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]");
            Thread.sleep(5000);

            // Verify playStarted event
            ev.verifyEvent(driver,"Notification Received: playStarted", "Vast Midroll video has been started to play", 20000);

            //adStarted event verification
            ev.verifyEvent(driver,"Notification Received: adStarted", "Midroll ad has started to play", 30000);

            // adCompleted event verification
            ev.verifyEvent(driver,"Notification Received: adCompleted", "Midroll ad has completed to play", 40000);

            // Verify playCompleted event
            ev.verifyEvent(driver,"Notification Received: playCompleted", "VAST Mid-roll video has completed play", 90000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    //@Test
    public  void vast_Postroll() throws Exception {

        EventVerification ev = new EventVerification();
        try {
            System.out.println("In Basic Playback Sample App Home Screen \n");
            Thread.sleep(2000);

            //Enable QA Mode . Should be moved to Before Test ?
            WebElement w = driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]"));
            if(w.getAttribute("value").equals("false")){
                driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]")).click();
            }

            //Click on VAST Ad Post-roll Video
            driver.findElement(By.name("VAST Ad Post-roll")).click();

            BaseClass.waitForElementBasedOnXpath(driver,"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]");
            Thread.sleep(5000);

            ev.verifyEvent(driver,"Notification Received: playStarted", "Vast Postroll video has started to play", 20000);

            Thread.sleep(3000);

            //adStarted event verification
            ev.verifyEvent(driver,"Notification Received: adStarted", "Postroll ad started to play", 50000);

            Thread.sleep(3000);

            // adCompleted event verification
            ev.verifyEvent(driver,"Notification Received: adCompleted", "Postroll ad has completed play", 60000);

            // Verify playCompleted event
            ev.verifyEvent(driver,"Notification Received: playCompleted", "VAST Postroll video has completed play", 90000);

        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void VAST_AD_Wrapper() throws Exception {

        EventVerification ev = new EventVerification();
        try {

            System.out.println("In Basic Playback Sample App Home Screen \n");
            Thread.sleep(2000);

            //Enable QA Mode . Should be moved to Before Test ?
            WebElement w = driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]"));
            if(w.getAttribute("value").equals("false")){
                driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeSwitch[1]")).click();
            }

            //Click on VAST Ad -roll Video
            driver.findElement(By.name("VAST Ad Wrapper")).click();

            BaseClass.waitForElementBasedOnXpath(driver,"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]");
            Thread.sleep(5000);

            // Verify playStarted event
            ev.verifyEvent(driver,"Notification Received: playStarted", "Vast AD Wrapper video has started to play", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent(driver,"Notification Received: stateChanged. state: paused", "Vast AD Wrapper video is paused", 30000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent(driver,"Notification Received: stateChanged. state: playing", "Vast AD Wrapper video started to play again", 50000);

            // Verify playCompleted event
            ev.verifyEvent(driver,"Notification Received: playCompleted", "Vast AD Wrapper video has completed play", 90000);
        }

        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    //@Test
    public void ooyala_PreRoll() throws InterruptedException {
        EventVerification ev = new EventVerification();

        System.out.println("Playing Vast Preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 9);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Preroll ad is start to playing", 20000);

            Thread.sleep(5000);

            // adCompleted event verification

            ev.verifyEvent("Notification Received: adPodCompleted", "Preroll ad is completed", 30000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Ooyala Preroll video has been playing started", 40000);

            Thread.sleep(2000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is paused", 50000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video is playing", 60000);


            ev.verifyEvent("Notification Received: playCompleted", "Ooyala Preroll video has been playing completed", 90000);

        }
        catch (Exception e)
        {

            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

   //@Test
    public  void ooyala_Midroll() throws Exception {
        EventVerification ev = new EventVerification();
        System.out.println("Playing Vast Midroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 10);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Ooyala Midroll video has been playing started", 20000);

            Thread.sleep(3000);
            // Verify pause event at normal screen
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is paused", 30000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video is playing", 40000);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Midroll ad is start to playing", 50000);


            // adCompleted event verification
            ev.verifyEvent("Notification Received: adPodCompleted", "Midroll ad is completed", 60000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "Ooyala Midroll video has been playing completed", 90000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

   //@Test
    public  void ooyala_Postroll() throws Exception {
        EventVerification ev = new EventVerification();
        System.out.println("Playing vast Postroll");
        try {

            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 11);

            // Verify playStarted event

            ev.verifyEvent("Notification Received: playStarted", "Vast Postroll video has been playing started", 20000);

            Thread.sleep(3000);
            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is in paused state", 30000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video is in playing state", 40000);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Postroll ad is start to playing", 50000);

            // adCompleted event verification
            ev.verifyEvent("Notification Received: adPodCompleted", "Postroll ad is completed", 60000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "Ooyala Postroll video has been playing completed", 90000);

        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public void Multi_Ad() throws InterruptedException {
        EventVerification ev = new EventVerification();

        System.out.println("Playing Vast Preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 12);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Preroll ad is start to playing", 20000);

            Thread.sleep(5000);

            // adCompleted event verification

            ev.verifyEvent("Notification Received: adPodCompleted", "Preroll ad is completed", 30000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Ooyala Preroll video has been playing started", 40000);

            Thread.sleep(2000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is paused", 50000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video is playing", 60000);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Preroll ad is start to playing", 70000);

            Thread.sleep(5000);

            // adCompleted event verification

            ev.verifyEvent("Notification Received: adPodCompleted", "Preroll ad is completed", 80000);

            ev.verifyEvent("Notification Received: playCompleted", "Ooyala Preroll video has been playing completed", 90000);

        }
        catch (Exception e)
        {

            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    /*//TODO Handel Length of video is too long
    //@Test
    public  void vertical() throws Exception {

        EventVerification ev = new EventVerification();

        System.out.println("In test testPlay");
        Thread.sleep(2000);
        assetSelect(driver, 4);

        // Verify SDK version
        Thread.sleep(5000);
        found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playStarted event
        Thread.sleep(5000);
        ev.verifyEvent("playStarted", "Vertical 16:9 video has been playing started", 20000);

        // Verify pause event at normal screen
        BaseClass.play_pauseBtn(driver);
        ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 20000);

        // Verify playing event at normal screen
        BaseClass.play_pauseBtn(driver);
        ev.verifyEvent("Notification Received: stateChanged. state: playing", "video is in playing state", 20000);

        // Click on fullscreen
        BaseClass.fullscreenBtn(driver);
        Thread.sleep(5000);

        // Verify pause event at full screen
        BaseClass.play_pause_fullscreenBtn(driver);
        ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state at fullscreen", 20000);

        // Verify playing event at full screen
        Thread.sleep(5000);
        BaseClass.play_pause_fullscreenBtn(driver);
        ev.verifyEvent("Notification Received: stateChanged. state: playing", "video is in playing state at fullscreen", 20000);

        // Switch from full screen to normal screen
        Thread.sleep(5000);
        System.out.println("clicking on screen normal ");
        BaseClass.doneBtn(driver);

        // Click on Master button
        BaseClass.masterBtn(driver);
    }*/

}
