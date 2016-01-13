/**
 * Created by dulari on 1/6/16.
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

public class FirstTest {

    private AppiumDriver driver;

    private WebElement row;

    public WebDriverWait wait;

    private static int lastlinenumber;

    @Before
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

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

  /*  @Test
    public void testFindElement() throws Exception {
        //first view in UICatalog is a table
        IOSElement table = (IOSElement) driver.findElementByClassName("UIATableView");
        assertNotNull(table);
        //is number of cells/rows inside table correct
        List<WebElement> rows = table.findElementsByClassName("UIATableCell");
        assertEquals(12, rows.size());
        //is first one about buttons
        assertEquals("Buttons, Various uses of UIButton", rows.get(0).getAttribute("name"));
        //navigationBar is not inside table
        WebElement nav_bar = null;
        try {
            nav_bar = table.findElementByClassName("UIANavigationBar");
        } catch (NoSuchElementException e) {
            //expected
        }
        assertNull(nav_bar);
        //there is nav bar inside the app
        driver.getPageSource();
        nav_bar = driver.findElementByClassName("UIANavigationBar");
        assertNotNull(nav_bar);
    }
*/


   //@Test
    public void testScreenshot() {

        System.out.println("In testScreenshot");
        //make screenshot and get is as base64
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        wait = new WebDriverWait(driver,30);

        String screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BASE64);

        assertNotNull(screenshot);
        //make screenshot and save it to the local filesystem
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        assertNotNull(file);
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
        getLog("Ooyala SDK version");
        Thread.sleep(4000);
        getLog("playStarted");
        driver.findElement(By.xpath("//UIAApplication[1]")).click();
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[1]")).click();
        Thread.sleep(3000);
        getLog("state: paused");
        driver.findElement(By.xpath("//UIAApplication[1]")).click();
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[1]")).click();
        driver.findElement(By.xpath("//UIAApplication[1]")).click();
        getLog("state: playing");
        Thread.sleep(2000);
        while(true)
        {

        }


        //;
    }

    public static void getLog(String text) throws IOException {
        String filePath = "/Users/dulari/Library/Logs/CoreSimulator/34E644BB-B258-45B4-9320-E667AE62B5C2/system.log";
        String textToMatch = text;
        String content;
        boolean found = false;
        int notFound = 0;

        try
        {
            BufferedReader bf = new BufferedReader(new FileReader(filePath));
            int linecount = 0;

            String line;
            //System.out.println("Searching for " + textToMatch + " in file...");
            while (( line = bf.readLine()) != null)

            {
                // Increment the count and find the index of the word
                linecount++;
                int indexfound = line.indexOf(textToMatch);
                // If greater than -1, means we found the word
                if (indexfound > -1 && linecount > lastlinenumber) {
                    System.out.println("Word was found at position " + indexfound + " on line " + linecount);
                    found= true;
                }
            }

            if(found)
            {
                System.out.println(" Word " + text + " was found");
            }
            else
            {
                System.out.println("Word " + text + " was not found");
            }

            bf.close();
            if( linecount > lastlinenumber)
                lastlinenumber = linecount;
            System.out.println("Last line number is " + lastlinenumber );
            if(!found)
                Assert.assertTrue(found);
        }
        catch (IOException e)
        {
            if(!found)
            Assert.assertTrue(found);
            System.out.println("IO Error Occurred: " + e.toString());

        }

    }

    public static void captureLog(AppiumDriver driver, String testName) throws Exception {
        DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH-mm-ss");
        Date today =  Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        String logPath = "/Users/dulari/Library/Logs/CoreSimulator/34E644BB-B258-45B4-9320-E667AE62B5C2/system.log";


        //log.info(driver.getSessionId() + ": Saving device log...");
        //--show-sim-log
        List<LogEntry> logEntries = driver.manage().logs().get("appium server").filter(Level.ALL);

        //List<LogEntry> logEntries = driver.manage().logs().get("logcat").filter(Level.parse("Notification Received: "));
        File logFile = new File(logPath + reportDate + "_" + testName + ".txt");
        PrintWriter log_file_writer = new PrintWriter(logFile);
        log_file_writer.println(logEntries );
        log_file_writer.flush();
        //log.info(driver.getSessionId() + ": Saving device log - Done.");
    }



}

