package Tests.AdvancedSampleApp;

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
import Utils.SDKVersion;
import Utils.SetupiOSDriver;
import Utils.getLog;
import Utils.logging;
import io.appium.java_client.AppiumDriver;
import pageObject.AdvancedplaybackSampleApp;
import pageObject.BaseClass;

/**
 * Created by Shivam on 13/06/16.
 */
public class BasicTestsAdvancedPlayback extends BaseClass {

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
        Properties p=prop.loadProperty("AdvancedPlaybackSampleApp.properties");

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
        Properties p=prop.loadProperty("AdvancedPlaybackSampleApp.properties");
        String app = p.getProperty("app_Name");
        Thread.sleep(1000);
        //getLog.appUninstall(app);

        //Thread.sleep(5000);
        getLog.delete();
        System.out.println("log file deleted");

        driver.quit();
        Thread.sleep(2000);
        getLog.killAppium();
    }

    @BeforeMethod
    public void  beforeMethod() throws IOException, InterruptedException {
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

    //@Test
    public  void Plugin_Player() throws Exception {

        try {


            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 0);

            EventVerification ev = new EventVerification();

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adPodStarted", "Ad has been started", 10000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 20000);


            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 25000);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adPodStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 60000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 40000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adPodStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 60000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void Change_Video() throws Exception {

        try {


            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 1);

            EventVerification ev = new EventVerification();


            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 25000);


            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 40000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 60000);
            Thread.sleep(2000);

            AdvancedplaybackSampleApp.select_asset(driver, 5);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 65000);


            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 70000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 80000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void InsertAd_RunTime() throws Exception {
        try {

            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 2);

            EventVerification ev = new EventVerification();


            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 25000);

            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 40000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 60000);
            Thread.sleep(2000);

            System.out.println("selecting VAST Ad");

            AdvancedplaybackSampleApp.select_asset(driver,4);

            play_pauseBtn(driver);

            System.out.println("VAST Ad selected");

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 60000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Play has been started", 65000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);

            System.out.println("Selecting Ooyala Ad");

            AdvancedplaybackSampleApp.select_asset(driver,5);

            play_pauseBtn(driver);

            System.out.println("Ooyala Ad selected");

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 100000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 120000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Play has been started", 150000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 190000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void Play_IntialTime() throws Exception {

        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 3);

            EventVerification ev = new EventVerification();

           //PlayWith initial time so seek event occur
            ev.verifyEvent("Notification Received: seekStarted. state: ready","Video seeked before playing started", 25000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 25000);


            Thread.sleep(5000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 40000);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 60000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void custom_control() throws Exception {

        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 4);

            EventVerification ev = new EventVerification();

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 25000);


            Thread.sleep(5000);

            // Verify pause event at normal screen
            AdvancedplaybackSampleApp.custom_Play_pause(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 40000);

            // Verify playing event at normal screen
            AdvancedplaybackSampleApp.custom_Play_pause(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 60000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void performance_Profiling() throws Exception {

        System.out.println("Playing Preformance Profiling");

        try {

            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 7);

            // Verify playStarted event
            EventVerification ev = new EventVerification();
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 20000);

            Thread.sleep(5000);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adPodCompleted", " Ad has been completed" , 60000);

            Thread.sleep(2000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 30000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public  void custom_overlay() throws Exception {

       System.out.println("Playing custom overlay");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 5);

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


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);


        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }




}
