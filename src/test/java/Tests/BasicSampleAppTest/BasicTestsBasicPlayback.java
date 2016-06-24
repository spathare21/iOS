package Tests.BasicSampleAppTest;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;
import Utils.EventVerification;
import Utils.LoadPropertyValues;
import Utils.SetupiOSDriver;
import Utils.getLog;
import Utils.logging;
import io.appium.java_client.AppiumDriver;
import pageObject.BaseClass;

/**
 * Created by Shivam on 10/06/16.
 */
public class BasicTestsBasicPlayback extends BaseClass {

    public static String ud;
    private AppiumDriver driver;

    String LogFilePath;
    logging _utils = new logging();
    boolean found=false;

    // This variable is used in checking the latest entry in the log.
    private static int lastlinenumber;

    @Parameters({"platformVersion", "deviceName", "logFilePath" })

    @BeforeClass // Will be executed before any of the test run.
    public void beforeTest( String platformVersion,  String deviceName, String logFilePath) throws Exception {

        EventVerification.count =0 ;

        getLog.reboot();

        System.out.println("Device reboot successfully");

        // set up appium
        LogFilePath = logFilePath;

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("BasicPlaybackSampleApp.properties");

        ud = getLog.getUdid();

        getLog.getlog(ud);
        System.out.println("log file created");

        Thread.sleep(5000);

        System.out.println("valued of ud is " +ud);
        SetupiOSDriver setUpdriver = new SetupiOSDriver();
        driver = setUpdriver.setUpandReturniOSDriver( p.getProperty("appFilePath"),  p.getProperty("appName"),platformVersion, deviceName, ud);
        Thread.sleep(2000);

    }

    @AfterClass // Will be executed once all the tests are completed.
    public void tearDown() throws Exception {


        driver.closeApp();
        System.out.println("closing app ");

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("BasicPlaybackSampleApp.properties");
        String app = p.getProperty("app_Name");
        Thread.sleep(1000);
        //getLog.appUninstall(app);

        getLog.delete();
        System.out.println("log file deleted");

        driver.quit();

    }

    @BeforeMethod
    public void  beforeMethod() throws IOException
    {
        System.out.println("in before method ");
    }

    @AfterMethod
    public void afterMethod() throws IOException, InterruptedException {
        System.out.println("in after method");
        BaseClass.masterBtn(driver);
        Thread.sleep(2000);

    }



    @Test
    public  void HLS() throws Exception {

        EventVerification ev = new EventVerification();
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 0);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "HLS video has been playing started", 15000);

            Thread.sleep(5000);


            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 25000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "video is in playing state", 40000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 90000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    @Test
    public  void MP4() throws Exception {

        EventVerification ev = new EventVerification();

        System.out.println("In test testPlay");
        try {


            Thread.sleep(2000);
            assetSelect(driver, 1);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "MP4 video has been playing started", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 30000);

            // Verify playing event at normal screen
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

    @Test
    public  void vodCC() throws Exception {

        EventVerification ev = new EventVerification();

        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 2);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            Thread.sleep(5000);
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

    @Test
    public  void aspectRatio() throws Exception {

        EventVerification ev = new EventVerification();

        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 3);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Aspect Ratio video has been playing started", 20000);

            Thread.sleep(5000);
            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 30000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "video is in playing state", 40000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 900000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    @Test
    public void vast_PreRoll() throws InterruptedException {
        EventVerification ev = new EventVerification();

        System.out.println("Playing Vast Preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 5);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Preroll ad is start to playing", 20000);

            Thread.sleep(5000);

            // adCompleted event verification

            ev.verifyEvent("Notification Received: adPodCompleted", "Preroll ad is completed", 30000);

            // Verify playStarted event
            ev.verifyEvent("playStarted", "Vast Preroll video has been playing started", 40000);

            Thread.sleep(2000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is paused", 50000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video is playing", 60000);


            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 90000);

        }
        catch (Exception e)
        {

            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    @Test
    public  void vast_Midroll() throws Exception {
        EventVerification ev = new EventVerification();
        System.out.println("Playing Vast Midroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 6);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Vast Midroll video has been playing started", 20000);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Midroll ad is start to playing", 30000);


            // adCompleted event verification
            ev.verifyEvent("Notification Received: adPodCompleted", "Midroll ad is completed", 40000);

            Thread.sleep(2000);
            // Verify pause event at normal screen
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is paused", 50000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video is playing", 60000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 90000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    @Test
    public  void vast_Postroll() throws Exception {
        EventVerification ev = new EventVerification();
        System.out.println("Playing vast Postroll");
        try {

            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 7);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if(!found)
                Assert.assertTrue(found);

            // Verify playStarted event

            ev.verifyEvent("Notification Received: playStarted", "Vast Postroll video has been playing started", 20000);

            Thread.sleep(2000);
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
            ev.verifyEvent("Notification Received: playCompleted", "VAST Postroll video has been playing completed", 90000);

        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public  void VAST_AD_Wrapper() throws Exception {

        EventVerification ev = new EventVerification();

        System.out.println("In test testPlay");
        try {


            Thread.sleep(2000);
            assetSelect(driver, 8);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Vast AD Wrapper video has been playing started", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 30000);

            // Verify playing event at normal screen
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

    @Test
    public void ooyala_PreRoll() throws InterruptedException {
        EventVerification ev = new EventVerification();

        System.out.println("Playing Vast Preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 9);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


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

    @Test
    public  void ooyala_Midroll() throws Exception {
        EventVerification ev = new EventVerification();
        System.out.println("Playing Vast Midroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 10);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

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

    @Test
    public  void ooyala_Postroll() throws Exception {
        EventVerification ev = new EventVerification();
        System.out.println("Playing vast Postroll");
        try {

            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 11);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if(!found)
                Assert.assertTrue(found);

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

    @Test
    public void Multi_Ad() throws InterruptedException {
        EventVerification ev = new EventVerification();

        System.out.println("Playing Vast Preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 12);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


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

    //TODO Handel Length of video is too long
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
    }

}
