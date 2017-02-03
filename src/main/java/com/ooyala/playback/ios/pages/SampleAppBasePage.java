package com.ooyala.playback.ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ooyala.playback.ios.IOSEvents;
import com.ooyala.playback.ios.exceptions.PageNotCurrentException;
import com.ooyala.playback.ios.utils.EventVerification;
import com.ooyala.playback.ios.utils.TestUtils;
import com.ooyala.playback.ios.utils.WebDriverFactory;
import static org.assertj.core.api.Assertions.*;


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
public class SampleAppBasePage {

	IOSDriver driver = WebDriverFactory.getIOSDriver();
	EventVerification ev = null;
	
	
	//Locators
	private final By QA_MODE_SWITCH = By.xpath("//XCUIElementTypeSwitch[1]"); 
	private final By NOTIFICATION_AREA = By.xpath("//XCUIElementTypeTextView[1]");
	private final By LOADING_SPINNER = By.id("In progress");
	private final By TOOL_BAR = By.xpath("//XCUIElementTypeToolbar[1]");
	private final By PLAY_PAUSE_BUTTON = By.xpath("//XCUIElementTypeToolbar[1]/XCUIElementTypeButton[1]");
	
	/**
	 * 
	 * @param pageIdentifier
	 * Method to assert if the expected app is opened correctly.
	 */
	public void assertCurrentPage(By pageIdentifier) {
		try{
			waitForPresence(pageIdentifier);
		} catch (Exception e) {
			throw new PageNotCurrentException(TestUtils.getCallerClassNameFromThread() + " is not the current app", e);
		}
	}
	
    public void waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public void waitForNotPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
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
    
    public SampleAppBasePage waitForNotificationAreaToLoad() {
    	waitForPresence(NOTIFICATION_AREA);
    	return this;
    }
    
    /**
     * 
     * @param event
     * @param consoleMessage
     * @param timeout (in Milliseconds)
     * @return
     */
    public SampleAppBasePage verifyEvent(IOSEvents event, String consoleMessage, int timeout) {
    	if (ev == null)
    		ev= new EventVerification();
    	ev.verifyEvent(getNotificationEvents(), event.getEvent(), consoleMessage, timeout);
    	return this;
    }
    
    public String getNotificationEvents() {
    	return driver.findElement(NOTIFICATION_AREA).getText();
    }
    
    
    public SampleAppBasePage handleLoadingSpinner() {
    	waitForNotPresence(LOADING_SPINNER);
    	return this;
    }

    /**
     * this method is to tap the player to make play/pause button visible.
     */
    public SampleAppBasePage tapScreenIfRequired() {
    	if (!isElementPresent(TOOL_BAR))
    		tapScreen();
    	return this;
    }
    
    public SampleAppBasePage tapScreen() {
    	List<WebElement> elements = driver.findElements(By.xpath("//XCUIElementTypeOther"));
    	elements.get(6).click();
    	return this;
    }
    
    public SampleAppBasePage playVideo() {
    	driver.findElement(PLAY_PAUSE_BUTTON);
    	return this;
    }
    
    public SampleAppBasePage pauseVideo() {
    	driver.findElement(PLAY_PAUSE_BUTTON);
    	return this;
    }
    

    
    

}
