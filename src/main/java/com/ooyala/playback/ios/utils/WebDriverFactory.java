package com.ooyala.playback.ios.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import com.ooyala.playback.ios.SampleAppProperties;
import io.appium.java_client.ios.IOSDriver;


/**
 * 
 * @author nraman
 * This class takes care of driver creation logic
 *
 */

@SuppressWarnings({"rawtypes"})
public class WebDriverFactory {
	
	public static ThreadLocal<IOSDriver> webdriver = new ThreadLocal<IOSDriver>();

	public static IOSDriver getIOSDriver() {
		if (webdriver.get() == null) 
			throw new NullPointerException("Driver is null. Please create driver instance !!!!");
		return (IOSDriver) webdriver.get();
	}
	
	public static void setIOSDriver(IOSDriver driver) {
		webdriver.set(driver);
	}
	
	public static void quitDriver() {
		webdriver.get().quit();
		webdriver.remove();
	}

	public static IOSDriver createIOSDriver(SampleAppProperties appProperties) throws MalformedURLException  {
        
		File app = new File(appProperties.getAppDir(), appProperties.getAppName());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", appProperties.getPlatformVersion());
        capabilities.setCapability("deviceName", appProperties.getDeviceName());
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("udid", appProperties.getUdid());
        capabilities.setCapability("platformName", appProperties.getPlatformName());
        capabilities.setCapability("showIOSLog", appProperties.showIOSLog());
        capabilities.setCapability("automationName", appProperties.getAutomationName());
        capabilities.setCapability("newCommandTimeout", appProperties.getNewCommandTimeOut());
        
        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        setIOSDriver(driver);
        return driver;
      
    }
	
 
}
