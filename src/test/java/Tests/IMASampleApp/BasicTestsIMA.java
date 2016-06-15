package Tests.IMASampleApp;

import Utils.*;
import io.appium.java_client.AppiumDriver;

import org.junit.experimental.theories.Theories;
import org.testng.annotations.*;
import org.testng.Assert;
import pageObject.BaseClass;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Shivam on 18/05/16.
 */
public class BasicTestsIMA extends BaseClass{

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

        EventVerification.count =0 ;

        getLog.reboot();

        System.out.println("device reboot successfully");

        // set up appium
        LogFilePath = logFilePath;

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p = prop.loadProperty("IMASampleApp.properties");

        ud = getLog.getUdid();

        getLog.getlog(ud);
        System.out.println("log file created");

        Thread.sleep(5000);

        System.out.println("valued of ud is " + ud);
        SetupiOSDriver setUpdriver = new SetupiOSDriver();
        driver = setUpdriver.setUpandReturniOSDriver(p.getProperty("appFilePath"), p.getProperty("appName"), platformVersion, deviceName, ud);
        Thread.sleep(2000);

    }

    @AfterClass // Will be executed once all the tests are completed.
    public void tearDown() throws Exception {

        driver.closeApp();
        System.out.println("closing app ");

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p = prop.loadProperty("IMASampleApp.properties");
        String app = p.getProperty("app_Name");
        Thread.sleep(1000);
        //   getLog.appUninstall(app);

        getLog.delete();
        System.out.println("log file deleted");
        driver.quit();

    }

    @BeforeMethod
    public void beforeMethod() throws IOException
    {
        System.out.println("in before method ");
    }

    @AfterMethod
    public void afterMethod() throws IOException, InterruptedException {
        System.out.println("in after method");
        Thread.sleep(2000);
        BaseClass.masterBtn(driver);
        Thread.sleep(1000);

    }

    @Test
    public void IMA_Preroll() throws Exception {
        System.out.println(" playing IMA preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 9);

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

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 20000);

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
    public  void IMA_Midroll() throws Exception {

        System.out.println("Playing IMA Midroll");

        try {


            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 8);

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

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 60000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);

        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void IMA_Postroll() throws Exception {

        System.out.println("Playing IMA Postroll");
        try {

            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 7);

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

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 60000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);




        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    @Test
    public void IMA_Podded_Preroll() throws Exception {
        System.out.println(" playing IMA podded preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 6);

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

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 20000);

            Thread.sleep(3000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 30000);

            Thread.sleep(5000);
            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 40000);


            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 50000);

            Thread.sleep(5000);
            // Clicking on pause button
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 60000);



            // Verify playing event at normal screen
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 70000);

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
    public  void IMA_Podded_Midroll() throws Exception {

        System.out.println("Playing IMA Podded Midroll");

        try {


            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 5);

            // Verify SDK version
            Thread.sleep(4000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            EventVerification ev = new EventVerification();
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 20000);

            Thread.sleep(3000);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 30000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 40000);



            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 60000);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 70000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 80000);


            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);



        } catch (Exception e) {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void IMA_Podded_Postroll() throws Exception {

        System.out.println("Playing IMA Podded Postroll");
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
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again", 50000);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 50000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed", 60000);

            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 70000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed", 80000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted", "Video has been completed", 90000);
        }

        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void IMA_Podded_PreMidPost() throws Exception {
        System.out.println("IMA PreMidPost podded");

        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 3);

            // Verify SDK version
            Thread.sleep(3000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(2000);

            //Creting the object of EventVerification class
            EventVerification ev = new EventVerification();

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 10000);

            Thread.sleep(5000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 20000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 20000);

            Thread.sleep(5000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 30000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 40000);


            Thread.sleep(5000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 50000);



            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 60000);

            Thread.sleep(10000);
            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 70000);

            Thread.sleep(5000);
            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 70000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 70000);

            Thread.sleep(5000);
            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 80000);


            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 90000);

            Thread.sleep(5000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" ,90000);




            Thread.sleep(5000);
            // Clicking on pause button
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 90000);



            // Verify playing event at normal screen
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 90000);



            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 90000);

            Thread.sleep(5000);
            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 90000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 90000);

            Thread.sleep(5000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 90000);


            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 90000);

            Thread.sleep(5000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 90000);


            //verifind playCompleted Event

            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 100000);


        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public void IMA_Skippable() throws Exception {
        System.out.println(" playing IMA preroll");
        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 2);

            // Verify SDK version
            Thread.sleep(3000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            //Creting the object of EventVerification class
            EventVerification ev = new EventVerification();

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 10000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 20000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);

            Thread.sleep(5000);
            // Clicking on pause button
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 50000);

            // Verify playing event at normal screen
            play_pauseBtn(driver);

            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 50000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 60000);

            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 70000);

            //verifind playCompleted Event

            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 90000);


        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }
    }

    //@Test
    public  void IMA_PreMidPost_skippable() throws Exception {

        try {
            System.out.println("In test testPlay");
            Thread.sleep(2000);
            assetSelect(driver, 1);

            // Verify SDK version
            Thread.sleep(3000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            //Creting the object of EventVerification class
            EventVerification ev = new EventVerification();

            //verifing the ad started evnet

            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 15000);


            Thread.sleep(10000);
            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 20000);

            // Verify playStarted event
            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);

            Thread.sleep(5000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 40000);

            Thread.sleep(10000);
            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 50000);

            Thread.sleep(5000);

            play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: paused", "Video has been paused", 60000);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            ev.verifyEvent("Notification Received: stateChanged. state: playing", "Video started playing again" , 70000);

            //verifing the ad started evnet
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 80000);

            Thread.sleep(10000);
            //verifing ad Completed Event

            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 90000);

            // Verify playCompleted event
            ev.verifyEvent("Notification Received: playCompleted","Video has been completed", 100000);



        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

    //@Test
    public  void IMA_client_config() throws Exception {

        System.out.println("Playing IMA Client Side confu=ig");

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
            EventVerification ev = new EventVerification();

            ev.verifyEvent("Notification Received: playStarted", "Play has been started", 30000);


            Thread.sleep(10000);


            //adStarted event verification
            ev.verifyEvent("Notification Received: adStarted", "Ad has been started", 40000);


            Thread.sleep(5000);
            //verifing ad Completed Event


            ev.verifyEvent("Notification Received: adCompleted", " Ad has been completed" , 40000);

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


