package Tests;

/**
 * Created by dulari on 1/14/16.
 */

import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadFactory;

import Utils.*;

public class OoyalaSkinSampleAppTests {

    public static String ud;
    private AppiumDriver driver;

    String LogFilePath;
    logging _utils = new logging();
    boolean found=false;

    pageObject.OoyalaSkinSampleApp pgObj = new pageObject.OoyalaSkinSampleApp();


    // This variable is used in checking the latest entry in the log.
    private static int lastlinenumber;

    @Parameters({"platformVersion", "deviceName", "logFilePath" })

    @BeforeClass // Will be executed before any of the test run.
    public void beforeTest( String platformVersion,  String deviceName, String logFilePath) throws Exception {

        // set up appium
        LogFilePath = logFilePath;

        LoadPropertyValues prop = new LoadPropertyValues();
        Properties p=prop.loadProperty("OoyalaSkinSampleApp.properties");

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
        Properties p=prop.loadProperty("OoyalaSkinSampleApp.properties");
        String app = p.getProperty("bundleid");
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
    public void afterMethod() throws IOException {
        System.out.println("in after method");

    }

    @Test
    public void BasicPlayback() throws Exception{

        //Test HLS
        try
        {
            System.out.println("In Basic Playback Test");
            Thread.sleep(2000);
            pgObj.assetSelect(driver, 1);
            HLS();
        }
        catch (Exception e){
            System.out.println("Error Occured while testing OoyalaSkinSampleApp -> BasicPlayback->HLS()");
            Assert.assertTrue(false);
            Thread.sleep(3000);

        }

    }

    public  void HLS() throws Exception {

        System.out.println("In test testPlay");
        Thread.sleep(2000);
        pgObj.assetSelect(driver, 1);

        // Verify SDK version
        Thread.sleep(5000);
        found = pgObj.sdkVersion(LogFilePath, lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playStarted event
        Thread.sleep(5000);
        found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify pause event at normal screen
        pgObj.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at normal screen
        pgObj.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Click on fullscreen
        pgObj.fullscreenBtn(driver);
        Thread.sleep(5000);

        // Verify pause event at full screen
        pgObj.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at full screen
        Thread.sleep(5000);
        pgObj.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Switch from full screen to normal screen
        Thread.sleep(5000);
        System.out.println("clicking on screen normal ");
        pgObj.doneBtn(driver);

        // Verify playCompleted event
        boolean end=true;
        while(end)
        {
            end=!_utils.getLog(LogFilePath,"playCompleted",lastlinenumber);
            Thread.sleep(1000);
        }
        Thread.sleep(10000);

        // Click on Master button
        pgObj.masterBtn(driver);

    }


   // @Test
    public void MP4() throws Exception {

        System.out.println("In test testPlay");
        Thread.sleep(2000);
        pgObj.assetSelect(driver, 1);

        // Verify SDK version
        Thread.sleep(5000);
        found = pgObj.sdkVersion(LogFilePath, lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playStarted event
        Thread.sleep(5000);
        found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify pause event at normal screen
        pgObj.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at normal screen
        pgObj.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Click on fullscreen
        pgObj.fullscreenBtn(driver);
        Thread.sleep(5000);

        // Verify pause event at full screen
        pgObj.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at full screen
        Thread.sleep(5000);
        pgObj.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Switch from full screen to normal screen
        Thread.sleep(5000);
        System.out.println("clicking on screen normal ");
        pgObj.doneBtn(driver);

        // Verify playCompleted event
        boolean end=true;
        while(end)
        {
            end=!_utils.getLog(LogFilePath,"playCompleted",lastlinenumber);
            Thread.sleep(1000);
        }
        Thread.sleep(10000);

        // Click on Master button
        pgObj.masterBtn(driver);

    }

}

