package com.ooyala.playback.ios.utils;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ooyala.playback.ios.SampleAppProperties;

import io.appium.java_client.ios.IOSDriver;


@SuppressWarnings({"rawtypes", "unchecked"})

public class WebDriverUtils {

	public static ThreadLocal<IOSDriver> webdriver = new ThreadLocal<IOSDriver>();

	public static IOSDriver getIOSDriver() {
		if (webdriver.get() == null) 
			throw new NullPointerException("Driver is null. Please create driver instance !!!!");
		return (IOSDriver) webdriver.get();
	}
	
	public static void createAndroidDriver(SampleAppProperties appProperties) throws MalformedURLException {
		IOSDriver iosDriver = WebDriverFactory.createIOSDriver(appProperties);
		setIOSDriver(iosDriver);
	}
	
	public static void setIOSDriver(IOSDriver driver) {
		webdriver.set(driver);
	}
	
	public static void quitDriver() {
		webdriver.get().quit();
		webdriver.remove();
	}

    public static void waitForPresence(By locator) {

        WebDriverWait wait = new WebDriverWait(getIOSDriver(), 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }
 
    public static void clickElement(By locator) {
        WebElement element = getIOSDriver().findElement(locator);
        element.click();
    }
    
    public static void clickElement(WebElement element) {
    	element.click();
    }
    
    public static boolean isElementPresent(By by) {
		List<WebElement> eleList = getIOSDriver().findElements(by);
    	if (eleList.size() > 0) 
    		return true;
    	return false;
    }
	

}
