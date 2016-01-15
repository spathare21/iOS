package Tests;

/**
 * Created by dulari on 1/14/16.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import static org.junit.Assert.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import org.apache.commons.lang.RandomStringUtils;
import io.appium.java_client.ios.*;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.remote.SuiteSlave;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import Utils.*;

public class BasicPlaybackSampleApp {

    private AppiumDriver driver;

    private WebElement row;

    public WebDriverWait wait;

    logging _utils = new logging();
    boolean found=false;

    // This variable is used in checking the latest entry in the log.
    private static int lastlinenumber;

    @Before // Will be executed before any of the test run.
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File("", "/Users/dulari/Library/Developer/Xcode/DerivedData/BasicPlaybackSampleApp-dtfvalcbrzeeqtbyjqgniyzepltg/Build/Products/Release-iphonesimulator");
        File app = new File(appDir, "BasicPlaybackSampleApp.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", "8.1"); // Device Id: 34E644BB-B258-45B4-9320-E667AE62B5C2
        capabilities.setCapability("deviceName", "iPad Air");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @After // Will be executed once all the tests are completed.
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public  void testPlay() throws Exception {

        //wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
        //wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[1]")).click();
        //wd.findElement(By.xpath("//UIAApplication[1]")).click();

        System.out.println("testing 123");
        // captureLog(driver,"testing123");
        //driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")).click();
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
        Thread.sleep(3000);
        found=_utils.getLog("Ooyala SDK version",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        Thread.sleep(3000);
        found=_utils.getLog("playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        driver.findElement(By.xpath("//UIAApplication[1]")).click();
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[1]")).click();
        // pause for 5 second
        Thread.sleep(2000);
        found=_utils.getLog("state: paused",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        driver.findElement(By.xpath("//UIAApplication[1]")).click();
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[1]")).click();
        driver.findElement(By.xpath("//UIAApplication[1]")).click();
        found=_utils.getLog("state: playing",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);
        //Thread.sleep(2000);
        boolean end=true;
        while(end)
        {
            end=!_utils.getLog("playCompleted",lastlinenumber);
            Thread.sleep(1000);
        }
    }

}
