package Tests.FreewheelSampleAppTest;


import org.apache.commons.exec.ExecuteException;
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
import sun.jvm.hotspot.debugger.cdbg.basic.BasicBaseClass;

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

        // set up appium
        LogFilePath = logFilePath;

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("FreewheelSampleApp.properties");

        ud = getLog.getUdid();

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
        Properties p=prop.loadProperty("FreewheelSampleApp.properties");
        String app = p.getProperty("app_Name");
        Thread.sleep(1000);
        getLog.appUninstall(app);

        getLog.delete("system.log");
        System.out.println("log file deleted");


        driver.quit();



    }

    @BeforeMethod
    public void  beforeMethod() throws IOException {

        System.out.println("in before method ");
        getLog.getlog(ud);
        System.out.println("log file created");

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
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(2000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            Thread.sleep(5000);
            found = _utils.getLog(LogFilePath, "playStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "paused", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "playing", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playCompleted event
            boolean end = true;
            while (end) {
                end = !_utils.getLog(LogFilePath, "playCompleted", lastlinenumber);
                Thread.sleep(1000);
            }
            Thread.sleep(10000);

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
            Thread.sleep(5000);
            found = _utils.getLog(LogFilePath, "playStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "paused", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "playing", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(10000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playCompleted event
            boolean end = true;
            while (end) {
                end = !_utils.getLog(LogFilePath, "playCompleted", lastlinenumber);
                Thread.sleep(1000);
            }
            Thread.sleep(10000);

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
            Thread.sleep(7000);
            found = _utils.getLog(LogFilePath, "playStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "paused", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "playing", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(23000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playCompleted event
            boolean end = true;
            while (end) {
                end = !_utils.getLog(LogFilePath, "playCompleted", lastlinenumber);
                Thread.sleep(1000);
            }
            Thread.sleep(10000);

        }
        catch (Exception e)
        {
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

            Thread.sleep(5000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playStarted event
            Thread.sleep(7000);
            found = _utils.getLog(LogFilePath, "playStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "paused", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "playing", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(9000);


            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(20000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playCompleted event
            boolean end = true;
            while (end) {
                end = !_utils.getLog(LogFilePath, "playCompleted", lastlinenumber);
                Thread.sleep(1000);
            }
            Thread.sleep(10000);

        }
        catch (Exception e)
        {
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


            // Verify playStarted event
            Thread.sleep(3000);
            found = _utils.getLog(LogFilePath, "playStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "paused", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(2000);

            BaseClass.overlay(driver);

            //  BaseClass.overlay(driver);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "playing", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playCompleted event
            boolean end = true;
            while (end) {
                end = !_utils.getLog(LogFilePath, "playCompleted", lastlinenumber);
                Thread.sleep(1000);
            }
            Thread.sleep(10000);

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
            Thread.sleep(3000);
            found = _utils.getLog(LogFilePath, "playStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "paused", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "playing", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            Thread.sleep(2000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(23000);

            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(25000);
            // Verify playCompleted event
            boolean end = true;
            while (end) {
                end = !_utils.getLog(LogFilePath, "playCompleted", lastlinenumber);
                Thread.sleep(1000);
            }
            Thread.sleep(10000);

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
            assetSelect(driver, 3);

            // Verify SDK version
            Thread.sleep(5000);
            found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(5000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playStarted event
            Thread.sleep(7000);
            found = _utils.getLog(LogFilePath, "playStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify pause event at normal screen
            play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "paused", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(1000);

            BaseClass.overlay(driver);


            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            found = _utils.getLog(LogFilePath, "playing", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(9000);


            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(20000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(7000);

            // adCompleted event verification
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);


            // Verify playCompleted event
            boolean end = true;
            while (end) {
                end = !_utils.getLog(LogFilePath, "playCompleted", lastlinenumber);
                Thread.sleep(1000);
            }
            Thread.sleep(10000);

        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            e.printStackTrace();
        }

    }

}