package Tests.FreewheelSampleAppTest;


import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.internal.EventFiringKeyboard;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;

import java.awt.*;
import java.io.*;
import java.sql.Time;
import java.util.*;


import Utils.*;
import pageObject.*;


/**
 * Created by Shivam on 18/05/16.
 */
public class BasicTestsFreewheel extends BaseClass {


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

        getLog.reboot();

        System.out.println("System rebooted successfully./");

        // set up appium
        LogFilePath = logFilePath;

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("FreewheelSampleApp.properties");

        ud = getLog.getUdid();

        System.out.println("valued of ud is " +ud);

        getLog.getlog(ud);
        System.out.println("log file created");
        Thread.sleep(5000);

        SetupiOSDriver setUpdriver = new SetupiOSDriver();
        driver = setUpdriver.setUpandReturniOSDriver( p.getProperty("appFilePath"),  p.getProperty("appName"),platformVersion, deviceName, ud);
        Thread.sleep(2000);

    }

    @AfterClass // Will be executed once all the tests are completed.
    public void tearDown() throws Exception {


        driver.closeApp();
        System.out.println("closing app ");

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("FreewheelSampleApp.properties");
        String app = p.getProperty("app_Name");
        Thread.sleep(1000);
        System.out.println("Deleting log file");
        getLog.delete();
        System.out.println("log file deleted");
        Thread.sleep(5000);
        //getLog.appUninstall(app);

        driver.quit();



    }

    @BeforeMethod
    public void  beforeMethod() throws IOException, InterruptedException
    {
        System.out.println("in before method ");

    }



    @AfterMethod
    public void afterMethod() throws IOException, InterruptedException {
        System.out.println("in after method");
        // Click on Master button
        Thread.sleep(2000);
        BaseClass.masterBtn(driver);
        Thread.sleep(1000);

    }

    @Test
    public  void fw_Preroll() throws Exception {

        System.out.println("Playing FW Preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 6);

            // Verify SDK version
            Thread.sleep(2000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            //Creting the object of EventVerification class
            EventVerification ev = new EventVerification();

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 10000);

           //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 20000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);

            Thread.sleep(5000);
            // Clicking on pause button
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 50000);



            // Verify playing event at normal screen
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);

            //verifind playCompleted Event

            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);

        }
        catch (Exception e)
        {

            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public  void fw_Midroll() throws Exception {

        System.out.println("Playing Freewheel Midroll");

        try {


            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 5);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playStarted event
            EventVerification ev = new EventVerification();
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 30000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);



            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 60000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public  void fw_Postroll() throws Exception {

        System.out.println("Playing Freewheel Postroll");

        try {


            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 4);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playStarted event
            EventVerification ev = new EventVerification();
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 30000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);



            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 60000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public  void fw_PreMidPost() throws Exception {

        try {


            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 3);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            EventVerification ev = new EventVerification();

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 10000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 20000);


            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 25000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 40000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);



            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 60000);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 60000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public  void fw_Overlay() throws Exception {

        System.out.println("Playing Overlay");
        try {

            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 2);

            // Verify SDK version
            Thread.sleep(4000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            EventVerification ev = new EventVerification();
            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);

            Thread.sleep(5000);
            // Clicking on pause button
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 50000);

            overlay(driver);

            // Verify playing event at normal screen
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);

            //verifind playCompleted Event

            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);

        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public  void fw_MultiMid() throws Exception {

        System.out.println("Playing MultiMidroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 0);

            // Verify SDK version
            Thread.sleep(3000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            EventVerification ev = new EventVerification();
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 20000);

            Thread.sleep(10000);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 30000);

            Thread.sleep(5000);

            //verifing ad Completed Event
            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 40000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 30000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 60000);

            Thread.sleep(5000);

            //verifing ad Completed Event
            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 70000);


            //verifing event for playCompleted

            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);

        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public  void fw_PreMidPost_Overlay() throws Exception {
        System.out.println(" Playing Freewheel PreMidPost overlay");

        try {

            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 1);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            //Creting the object of EventVerification class
            EventVerification ev = new EventVerification();

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 10000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 20000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);

            Thread.sleep(5000);

            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 40000);

            // Verify playing event at normal screen
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 60000);

            Thread.sleep(5000);
            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 70000);


            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 70000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 80000);

            //verifind playCompleted Event

            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);

        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

}
