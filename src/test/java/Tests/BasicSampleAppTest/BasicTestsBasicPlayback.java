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


public class BasicTestsBasicPlayback extends  BaseClass{
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
        Properties p=prop.loadProperty("BasicPlaybackSampleApp.properties");

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
        Properties p=prop.loadProperty("BasicPlaybackSampleApp.properties");
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
    public void afterMethod() throws IOException {
        System.out.println("in after method");

    }


    @Test
    public  void HLS() throws Exception {

        System.out.println("In test testPlay");
        Thread.sleep(2000);
        assetSelect(driver, 0);

        // Verify SDK version
        Thread.sleep(5000);
        found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playStarted event
        Thread.sleep(5000);
        found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify pause event at normal screen
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at normal screen
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Click on fullscreen
        BaseClass.fullscreenBtn(driver);
        Thread.sleep(5000);

        // Verify pause event at full screen
        BaseClass.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at full screen
        Thread.sleep(5000);
        BaseClass.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Switch from full screen to normal screen
        Thread.sleep(5000);
        System.out.println("clicking on screen normal ");
        BaseClass.doneBtn(driver);

        // Verify playCompleted event
        boolean end=true;
        while(end)
        {
            end=!_utils.getLog(LogFilePath,"playCompleted",lastlinenumber);
            Thread.sleep(1000);
        }
        Thread.sleep(10000);
        // Click on Master button
        BaseClass.masterBtn(driver);
    }

    @Test
    public void MP4() throws Exception {
        System.out.println("In test testPlay");
        Thread.sleep(2000);
        assetSelect(driver, 1);

        // Verify SDK version
        Thread.sleep(5000);
        found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playStarted event
        Thread.sleep(5000);
        found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify pause event at normal screen
        BaseClass. play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at normal screen
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Click on fullscreen
        BaseClass.fullscreenBtn(driver);
        Thread.sleep(5000);

        // Verify pause event at full screen
        BaseClass.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at full screen
        Thread.sleep(5000);
        BaseClass.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Switch from full screen to normal screen
        Thread.sleep(5000);
        System.out.println("clicking on screen normal ");
        BaseClass.doneBtn(driver);

        // Verify playCompleted event
        boolean end=true;
        while(end)
        {
            end=!_utils.getLog(LogFilePath,"playCompleted",lastlinenumber);
            Thread.sleep(1000);
        }
        Thread.sleep(10000);
        // Click on Master button
        BaseClass.masterBtn(driver);
    }

    @Test
    public  void vodCC() throws Exception {

        System.out.println("In test testPlay");
        Thread.sleep(2000);
        assetSelect(driver, 2);

        // Verify SDK version
        Thread.sleep(5000);
        found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playStarted event
        Thread.sleep(5000);
        found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify pause event at normal screen
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify CC button
        BaseClass.ccBtn(driver, "normal");
        boolean found1;
        try{
            found1 = driver.findElementByClassName("UIATableCell").isDisplayed();
        }
        catch (Exception e){
            found1 = false;
        }
        if(!found1)
            Assert.assertTrue(found1);

        // Verify Done button on CC
        BaseClass.ccDoneBtn(driver);
        boolean found2;
        try{
            found2 = driver.findElementByClassName("UIATableCell").isDisplayed();
        }
        catch (Exception e){
            found2 = false;
        }
        if(found2)
            Assert.assertTrue(found2);

        // Click on fullscreen
        driver.tap(1, 200, 300, 5);
        fullscreenBtn(driver);
        Thread.sleep(5000);

        // Verify CC button on full screen
        BaseClass.ccBtn(driver, "full");
        boolean found3;
        try{
            found3 = driver.findElementByClassName("UIATableCell").isDisplayed();
        }
        catch (Exception e){
            found3 = false;
        }
        if(!found3)
            Assert.assertTrue(found3);

        // Verify button on CC on full screen
        BaseClass.ccDoneBtn(driver);
        boolean found4;
        try{
            found4 = driver.findElementByClassName("UIATableCell").isDisplayed();
        }
        catch (Exception e){
            found4 = false;
        }
        if(found4)
            Assert.assertTrue(found4);

        // Switch from full screen to normal screen
        driver.tap(1, 200, 300, 5);
        BaseClass.doneBtn(driver);

        // Verify playing event at normal screen
        Thread.sleep(5000);
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playCompleted event
        boolean end=true;
        while(end)
        {
            end=!_utils.getLog(LogFilePath,"playCompleted",lastlinenumber);
            Thread.sleep(1000);
        }
        Thread.sleep(10000);
        // Click on Master button
        BaseClass.masterBtn(driver);
    }

    @Test
    public  void aspectRatio() throws Exception {
        System.out.println("In test testPlay");
        Thread.sleep(2000);
        assetSelect(driver, 3);

        // Verify SDK version
        Thread.sleep(5000);
        found = BaseClass.sdkVersion(LogFilePath, lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playStarted event
        Thread.sleep(5000);
        found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify pause event at normal screen
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at normal screen
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Click on fullscreen
        BaseClass.fullscreenBtn(driver);
        Thread.sleep(5000);

        // Verify pause event at full screen
        BaseClass.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at full screen
        Thread.sleep(5000);
        BaseClass.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Switch from full screen to normal screen
        Thread.sleep(5000);
        System.out.println("clicking on screen normal ");
        BaseClass.doneBtn(driver);

        // Verify playCompleted event
        boolean end=true;
        while(end)
        {
            end=!_utils.getLog(LogFilePath,"playCompleted",lastlinenumber);
            Thread.sleep(1000);
        }
        Thread.sleep(10000);

        // Click on Master button
        BaseClass.masterBtn(driver);
    }

    @Test
    public  void vertical() throws Exception {

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
        found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify pause event at normal screen
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at normal screen
        BaseClass.play_pauseBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Click on fullscreen
        BaseClass.fullscreenBtn(driver);
        Thread.sleep(5000);

        // Verify pause event at full screen
        BaseClass.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Verify playing event at full screen
        Thread.sleep(5000);
        BaseClass.play_pause_fullscreenBtn(driver);
        found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        // Switch from full screen to normal screen
        Thread.sleep(5000);
        System.out.println("clicking on screen normal ");
        BaseClass.doneBtn(driver);

        // Click on Master button
        BaseClass.masterBtn(driver);

    }

    @Test
    public void vast_PreRoll() throws InterruptedException {
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
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(25000);

            // adCompleted event verification
            System.out.println("Veifying ad complete event");
            found = _utils.getLog(LogFilePath, "adPodCompleted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            // Verify playStarted event
            Thread.sleep(5000);
            System.out.println("Veifying play started event");
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
        Thread.sleep(10000);

        // Click on Master button
        BaseClass.masterBtn(driver);

    }

    @Test
    public  void vast_Midroll() throws Exception {

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

            Thread.sleep(7000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(18000);

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

        // Click on Master button
        BaseClass.masterBtn(driver);

    }


    @Test
    public  void vast_Postroll() throws Exception {

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
            found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
            if(!found)
                Assert.assertTrue(found);

            // Verify pause event at normal screen
            BaseClass.play_pauseBtn(driver);
            found=_utils.getLog(LogFilePath,"paused",lastlinenumber);
            if(!found)
                Assert.assertTrue(found);

            // Verify playing event at normal screen
            BaseClass.play_pauseBtn(driver);
            found=_utils.getLog(LogFilePath,"playing",lastlinenumber);
            if(!found)
                Assert.assertTrue(found);

            Thread.sleep(13000);
            //adStarted event verification
            found = _utils.getLog(LogFilePath, "adStarted", lastlinenumber);
            if (!found)
                Assert.assertTrue(found);

            Thread.sleep(8000);

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
        // Click on Master button
        BaseClass.masterBtn(driver);

    }


    // Click on fullscreen button
    public static void fullscreenBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 5);
        Thread.sleep(2000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(4).click();
    }


}
