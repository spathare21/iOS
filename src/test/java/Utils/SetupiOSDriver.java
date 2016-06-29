package Utils;

/**
 * Created by dulari on 5/9/16.
 */

import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import Utils.getLog;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static Utils.getLog.getUdid;


public class SetupiOSDriver {



    AppiumDriver driver;

    public AppiumDriver setUpandReturniOSDriver( String appFilePath,String appName,String platformVersion,String deviceName,  String ud) throws IOException {

        File app = new File(appFilePath, appName);




        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("udid", ud);
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("newCommandTimeout", 50000);
        capabilities.setCapability("showIOSLog",true);


        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
        return driver;
    }

    // Return iOS driver running on a specifc port number
    public AppiumDriver setUpandReturniOSDriver( String appFilePath,String appName,String platformVersion,String deviceName,  String udid,int portNumber) throws MalformedURLException {

        File app = new File(appFilePath, appName);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("newCommandTimeout", 50000);
        capabilities.setCapability("platformName", "iOS");

        driver = new IOSDriver(new URL("http://127.0.0.1:" + portNumber + "/wd/hub"), capabilities);
        return driver;
    }

}


