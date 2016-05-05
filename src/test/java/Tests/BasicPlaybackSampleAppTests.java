package Tests;

/**
 * Created by dulari on 1/14/16.
 */

import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;

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

public class BasicPlaybackSampleAppTests {

    private AppiumDriver driver;

    private WebElement row;

    public WebDriverWait wait;

    String LogFilePath;

    logging _utils = new logging();
    boolean found=false;

    // This variable is used in checking the latest entry in the log.
    private static int lastlinenumber;

    @Parameters({"appFilePath","appName","platformVersion", "deviceName", "logFilePath","udid" })

    @BeforeTest // Will be executed before any of the test run.
    public void beforeTest(@Optional String appFilePath, @Optional String appName, @Optional String platformVersion, @Optional String deviceName,@Optional String logFilePath, @Optional String udid) throws Exception {

        // set up appium
        LogFilePath = logFilePath;
        File classpathRoot = new File(System.getProperty("user.dir"));
        System.out.println(appFilePath);

        File appDir = new File("", appFilePath);

        File app = new File(appDir, appName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", platformVersion); // Device Id: 34E644BB-B258-45B4-9320-E667AE62B5C2
        System.out.println(platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        System.out.println(deviceName);
        capabilities.setCapability("app", app.getAbsolutePath());
        System.out.println(app.getAbsolutePath());


        getLog.getlog(udid);
       // capabilities.setCapability("app", appName);
        capabilities.setCapability("udid", udid);
        System.out.println(udid);
        System.out.println("Goignt to start server");
        //driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


        System.out.println("server started");

        System.out.println("Now log file should create");
       // getLog.getlog(udid);

       /* File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File("", "/Users/dulari/Library/Developer/Xcode/DerivedData/BasicPlaybackSampleAppTests-dtfvalcbrzeeqtbyjqgniyzepltg/Build/Products/Release-iphonesimulator");
        File app = new File(appDir, "BasicPlaybackSampleAppTests.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", "8.1"); // Device Id: 34E644BB-B258-45B4-9320-E667AE62B5C2
        capabilities.setCapability("deviceName", "iPad Air");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities); */
    }

    @AfterTest // Will be executed once all the tests are completed.
    public void tearDown() throws Exception {
        getLog.delete("system.log");
         driver.quit();


    }

    @Test
    public  void MP4() throws Exception {

        System.out.println("In test testPlay");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
        Thread.sleep(5000);
        System.out.println("file path opf log file is >>"+ LogFilePath);
        found=_utils.getLog(LogFilePath,"Ooyala SDK version",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        Thread.sleep(10000);
        found=_utils.getLog(LogFilePath,"playStarted",lastlinenumber);
        if(!found)
            Assert.assertTrue(found);

        Thread.sleep(45000);

        boolean end=true;
        while(end)
        {
            end=!_utils.getLog(LogFilePath,"playCompleted",lastlinenumber);
            Thread.sleep(1000);
        }
        Thread.sleep(10000);


    }


}
