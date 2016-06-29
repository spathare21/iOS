package Tests.OoyalaSkinSampleApp;



import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import Utils.EventVerification;
import Utils.LoadPropertyValues;
import Utils.SetupiOSDriver;
import Utils.getLog;
import Utils.logging;
import io.appium.java_client.AppiumDriver;
import pageObject.BaseClass;
import pageObject.OoyalaSkinSampleApp;

/**
 * Created by Shivam on 10/06/16.
 */
public class BasicTestsFreewheel extends BaseClass {

    public static String ud;
    private AppiumDriver driver;

    String LogFilePath;
    logging _utils = new logging();
    boolean found = false;

    // This variable is used in checking the latest entry in the log.
    private static int lastlinenumber;

    @Parameters({"platformVersion", "deviceName", "logFilePath"})

    @BeforeClass // Will be executed before any of the test run.
    public void beforeTest(String platformVersion, String deviceName, String logFilePath) throws Exception {

        EventVerification.count = 0;

        // set up appium
        LogFilePath = logFilePath;

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p = prop.loadProperty("OoyalaSkinSampleApp.properties");

        ud = getLog.getUdid();

        getLog.getlog();
        System.out.println("log file created");

        Thread.sleep(5000);

        System.out.println("valued of ud is " + ud);
        SetupiOSDriver setUpdriver = new SetupiOSDriver();
        driver = setUpdriver.setUpandReturniOSDriver(p.getProperty("appFilePath"), p.getProperty("appName"), platformVersion, deviceName, ud);
        Thread.sleep(2000);

        System.out.println("selecting integration");
        OoyalaSkinSampleApp.select_integration(driver, 2);
    }

    @AfterClass // Will be executed once all the tests are completed.
    public void tearDown() throws Exception {


        driver.closeApp();
        System.out.println("closing app ");

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p = prop.loadProperty("OoyalaSkinSampleApp.properties");
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
    public void beforeMethod() throws IOException {
        System.out.println("in before method ");
    }

    @AfterMethod
    public void afterMethod() throws IOException, InterruptedException {
        System.out.println("in after method");
        BaseClass.masterBtn(driver);
        Thread.sleep(2000);

    }

    @Test
    public  void fw_Preroll() throws Exception {
        System.out.println("Playing FW Preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 0);

            OoyalaSkinSampleApp.presenceOfElement(driver);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

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
            driver.tap(1, 200, 300, 5);
            Thread.sleep(1000);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 50000);


            // Verify playing event at normal screen
            OoyalaSkinSampleApp.play_pauseBtn(driver);

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
            assetSelect(driver, 1);

            OoyalaSkinSampleApp.presenceOfElement(driver);
            // Verify SDK version
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            // Verify playStarted event
            EventVerification ev = new EventVerification();
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            driver.tap(1, 200, 300, 5);
            Thread.sleep(1000);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 30000);


            // Verify playing event at normal screen
            OoyalaSkinSampleApp.play_pauseBtn(driver);
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
        System.out.println("Playing Freewheel Midroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 2);

            // Verify SDK version
            OoyalaSkinSampleApp.presenceOfElement(driver);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            // Verify playStarted event
            EventVerification ev = new EventVerification();
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 20000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            driver.tap(1, 200, 300, 5);
            Thread.sleep(1000);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 30000);


            // Verify playing event at normal screen
            OoyalaSkinSampleApp.play_pauseBtn(driver);
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
    public  void fw_Pre_MidPostroll() throws Exception {
       System.out.println("Playing Freewheel Midroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 3);

            // Verify SDK version
            OoyalaSkinSampleApp.presenceOfElement(driver);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            // Verify playStarted event
            EventVerification ev = new EventVerification();

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 15000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 25000);


            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            driver.tap(1, 200, 300, 5);
            Thread.sleep(1000);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 30000);


            // Verify playing event at normal screen
            OoyalaSkinSampleApp.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 40000);



            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 60000);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 70000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 80000);


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
            assetSelect(driver, 4);

            // Verify SDK version
            OoyalaSkinSampleApp.presenceOfElement(driver);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            EventVerification ev = new EventVerification();
            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);

            Thread.sleep(5000);

            overlay(driver);

            Thread.sleep(5000);

            // Clicking on pause button
            driver.tap(1, 200, 300, 5);
            Thread.sleep(1000);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 50000);

            OoyalaSkinSampleApp.play_pauseBtn(driver);

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
    public  void fw_PreMidPost_Overlay() throws Exception {
        System.out.println(" Playing Freewheel PreMidPost overlay");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 5);

            OoyalaSkinSampleApp.presenceOfElement(driver);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            //Creting the object of EventVerification class
            EventVerification ev = new EventVerification();

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 15000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 20000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);

            Thread.sleep(5000);

            overlay(driver);

            Thread.sleep(1000);

            driver.tap(1, 200, 300, 5);
            Thread.sleep(1000);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 40000);


            Thread.sleep(2000);

            // Verify playing event at normal screen
            driver.tap(1, 200, 300, 5);
            Thread.sleep(1000);
            OoyalaSkinSampleApp.play_pauseBtn(driver);

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

    @Test
    public  void fw_MultiMid() throws Exception {
        System.out.println("Playing MultiMidroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 6);

            OoyalaSkinSampleApp.presenceOfElement(driver);
            OoyalaSkinSampleApp.play_pauseBtn(driver);


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
            driver.tap(1, 200, 300, 5);
            Thread.sleep(1000);
            OoyalaSkinSampleApp.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 50000);

            // Verify playing event at normal screen
            OoyalaSkinSampleApp.play_pauseBtn(driver);

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

}

