    package Tests;

    /**
     * Created by dulari on 1/14/16.
     */

    import org.testng.Assert;
    import org.testng.annotations.*;
    import io.appium.java_client.AppiumDriver;

    import java.util.*;

    //import org.apache.commons.lang.RandomStringUtils;
    //import org.json.simple.JSONObject;
    //import org.json.simple.parser.JSONParser;
    import org.openqa.selenium.*;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import Utils.*;

    public class BasicPlaybackSampleAppTests {

        private AppiumDriver driver;

        String LogFilePath;

        logging _utils = new logging();
        boolean found=false;

        // This variable is used in checking the latest entry in the log.
        private static int lastlinenumber;

        @Parameters({"platformVersion", "deviceName", "logFilePath","udid" })

        @BeforeTest // Will be executed before any of the test run.
        public void beforeTest( String platformVersion,  String deviceName, String logFilePath,  String udid) throws Exception {

            // set up appium
            LogFilePath = logFilePath;

            LoadPropertyValues prop = new LoadPropertyValues();
            Properties p=prop.loadProperty("BasicPlaybackSampleApp.properties");

            getLog.getlog(udid);

            SetupiOSDriver setUpdriver = new SetupiOSDriver();
            driver = setUpdriver.setUpandReturniOSDriver( p.getProperty("appFilePath"),  p.getProperty("appName"),platformVersion, deviceName, udid);
            Thread.sleep(2000);

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
