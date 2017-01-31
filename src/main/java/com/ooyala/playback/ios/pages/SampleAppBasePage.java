package com.ooyala.playback.ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ooyala.playback.ios.exceptions.PageNotCurrentException;
import com.ooyala.playback.ios.utils.TestUtils;
import com.ooyala.playback.ios.utils.WebDriverFactory;

import io.appium.java_client.ios.IOSDriver;


/**
 * 
 * @author nraman
 * Baseclass containing all common actions performed across the iOS sample apps 
 * and contains wrapper methods for interacting with webdriver APIs (clickelement, findelement etc..)
 * 
 * All other SampleAppPage object class should extend this class
 *
 */


@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class SampleAppBasePage {

	IOSDriver driver = WebDriverFactory.getIOSDriver();
	
	
	//Locators
	private final By QA_MODE_SWITCH = By.xpath("//XCUIElementTypeSwitch[1]"); 
	
	/**
	 * 
	 * @param pageIdentifier
	 * Method to assert if the expected app is opened correctly.
	 */
	public void assertCurrentPage(By pageIdentifier) {
		try{
			waitForPresence(pageIdentifier);
		} catch (Exception e) {
			throw new PageNotCurrentException(TestUtils.getCallerClassNameFromThread() + " is not the current app");
		}
	}
	
    public void waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
 
    public void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }
    
    public void clickElement(WebElement element) {
    	element.click();
    }
    
    public boolean isElementPresent(By by) {
		List<WebElement> eleList = driver.findElements(by);
    	if (eleList.size() > 0) 
    		return true;
    	return false;
    }
    
    public SampleAppBasePage enableQAMode() {
    	if (!isQAModeEnabled())
    		clickElement(QA_MODE_SWITCH);
    	
    	Assert.assertTrue(isQAModeEnabled(), "QA Mode is not enabled. Hence failing test");
    	return this;
    }
    
    public boolean isQAModeEnabled() {
    	return Boolean.parseBoolean(driver.findElement(QA_MODE_SWITCH).getAttribute("value"));
    }
    
    
    

}
