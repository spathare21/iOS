package Tests.BasicSampleAppTest;

/**
 * Created by mahesh on 23/05/16.
 */

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;

import Utils.*;
import pageObject.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class DeepTestsBasicPlayback extends  BaseClass{
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
        // We are exucting this command, If something went wrong in after Class while deleting the log file then next it should not effect the code.
        getLog.delete();

        // set up appium
        LogFilePath = logFilePath;

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("BasicPlaybackSampleApp.properties");

        ud = getLog.getUdid();

        getLog.getlog();
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
        Thread.sleep(5000);
        getLog.killAppium();

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
        System.out.println("Your script is executed on following SDK Version and Commit:------ ");
        Thread.sleep(1000);
        SDKVersion.version();
        Thread.sleep(1000);
        SDKVersion.gitSHA();

    }

    @Test
    public  void HLS() throws Exception {

        EventVerification ev = new EventVerification();
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 0);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "HLS video has been playing started", 15000);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 25000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "video is in playing state", 30000);

            // Click on fullscreen
            BaseClass.fullscreenBtn(driver);

            Thread.sleep(5000);

            // Verify pause event at full screen
            BaseClass.play_pause_fullscreenBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Verified video is in paused state at fullscreen at fullscreen", 10000);

            // Verify playing event at full screen
            Thread.sleep(5000);
            BaseClass.play_pause_fullscreenBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Verified video is in playing state at fullscreen at fullscreen", 10000);

            // Switch from full screen to normal screen
            Thread.sleep(5000);
            System.out.println("clicking on screen normal ");
            BaseClass.doneBtn(driver);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 900000);

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

            // Verify playStarted event
            ev.verifyEvent("playStarted", "MP4 video has been playing started", 20000);

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
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Verified video is in paused state at fullscreen at fullscreen", 20000);

            // Verify playing event at full screen
            Thread.sleep(5000);
            BaseClass.play_pause_fullscreenBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Verified video is in playing state at fullscreen at fullscreen", 20000);

            // Switch from full screen to normal screen
            Thread.sleep(5000);
            System.out.println("clicking on screen normal");
            BaseClass.doneBtn(driver);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 900000);
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


            // Verify playStarted event
            Thread.sleep(5000);
            ev.verifyEvent("playStarted", "VOD with CC video has been playing started", 20000);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 20000);

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

            // Click on fullscreen
            driver.tap(1, 200, 300, 5);
            fullscreenBtn(driver);
            Thread.sleep(5000);

            // Verify CC button on full screen
            BaseClass.ccBtn(driver, "full");
            boolean found3;
            try {
                found3 = driver.findElementByClassName("UIATableCell").isDisplayed();
            } catch (Exception e) {
                found3 = false;
            }
            if (!found3)
                Assert.assertTrue(found3);

            // Verify button on CC on full screen
            BaseClass.ccDoneBtn(driver);
            boolean found4;
            try {
                found4 = driver.findElementByClassName("UIATableCell").isDisplayed();
            } catch (Exception e) {
                found4 = false;
            }
            if (found4)
                Assert.assertTrue(found4);

            // Switch from full screen to normal screen
            driver.tap(1, 200, 300, 5);
            BaseClass.doneBtn(driver);

            // Verify playing event at normal screen
            Thread.sleep(5000);
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "video is in playing state", 20000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 900000);
        } catch (Exception e) {
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

            // Verify playStarted event
            ev.verifyEvent("playStarted", "Aspect Ratio video has been playing started", 20000);

            Thread.sleep(5000);
            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "video is in paused state", 30000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "video is in playing state", 40000);

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

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 900000);
        } catch (Exception e) {
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

            Thread.sleep(8000);
            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Preroll ad is start to playing", 10000);

            Thread.sleep(25000);

            // adCompleted event verification
            System.out.println("Veifying ad complete event");
            ev.verifyEvent("Notification Received: adPodCompleted", "Preroll ad is completed", 10000);

            // Verify playStarted event
            Thread.sleep(10000);
            System.out.println("Veifying play started event");
            ev.verifyEvent("playStarted", "Vast Preroll video has been playing started", 10000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is paused", 10000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paying", "Video is playing", 10000);


            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 900000);

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
            Thread.sleep(5000);
            ev.verifyEvent("playStarted", "Vast Midroll video has been playing started", 20000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is paused", 20000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video is playing", 20000);

            Thread.sleep(7000);
            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Midroll ad is start to playing", 20000);

            Thread.sleep(20000);

            // adCompleted event verification
            ev.verifyEvent("Notification Received: adPodCompleted", "Midroll ad is completed", 50000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 900000);

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
            Thread.sleep(5000);
            ev.verifyEvent("playStarted", "Vast Postroll video has been playing started", 20000);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video is in paused state", 20000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video is in playing state", 20000);

            Thread.sleep(13000);
            //adStarted event verification
             ev.verifyEvent("Notification Received: adStarted", "Postroll ad is start to playing", 20000);

            Thread.sleep(8000);
            // adCompleted event verification
            ev.verifyEvent("Notification Received: adPodCompleted", "Postroll ad is completed", 30000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "HLS video has been playing completed", 900000);

        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    // Click on fullscreen button
    public static void fullscreenBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 5);
        Thread.sleep(2000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(4).click();
    }


}
