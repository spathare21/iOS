package com.ooyala.playback.ios.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ooyala.playback.ios.IOSEvents;
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
public class SampleAppBasePage {

	final static Logger logger = Logger.getLogger(SampleAppBasePage.class);
	
	IOSDriver driver = WebDriverFactory.getIOSDriver();
    
	int eventVerificationCount = 0;

	
	

	//Locators
	private final By QA_MODE_SWITCH = By.xpath("//XCUIElementTypeSwitch[1]"); 
	private final By NOTIFICATION_AREA = By.xpath("//XCUIElementTypeTextView[1]");
	private final By LOADING_SPINNER = By.xpath("//XCUIElementTypeActivityIndicator[1]");
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
    	verifyEvent(event.getEvent(), consoleMessage, timeout);
    	return this;
    }
    
    public String getNotificationEvents() {
    	int counter = 0;
    	int timeout = 10;
    	String notifications = null;
    	while (counter < timeout) {
    		notifications = driver.findElement(NOTIFICATION_AREA).getText();
    		if (notifications != null)
    			return notifications;
    		counter ++;
    	}
    	throw new NullPointerException("Notification events is NULL !!!");
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
    	tapScreenIfRequired();
    	driver.findElement(PLAY_PAUSE_BUTTON).click();
    	return this;
    }
    
    public SampleAppBasePage pauseVideo() {
    	tapScreenIfRequired();
    	driver.findElement(PLAY_PAUSE_BUTTON).click();
    	return this;
    }
    
    
    public SampleAppBasePage handleLoadingSpinner() {
        int i = 0;
        int timeOut = 20;
        
        try {
        	waitForPresence(LOADING_SPINNER);
        	while (true) {
	        	driver.findElement(LOADING_SPINNER);
	            if (i < timeOut){
	                logger.info("Handling Loading Spinner .... ");
	                Thread.sleep(1000);
	                i++;
	            } else {
	            	logger.error("Loading spinner occured more than " + i + " seconds");
	                return this;
	            }
        	}
            
        } catch (Exception e) {
        	logger.error("Loading spinner not present !!!");
        }
         return this;
    }

    public void verifyEvent(String eventToBeVerified, String consoleMessage, int timeout){
         
        int returncount = 0;
        boolean status = false;
        long startTime = System.currentTimeMillis();
        

        while((System.currentTimeMillis() - startTime) < timeout) {
        	logger.info("Waiting for notification >>>> : " + eventToBeVerified);
        	String notifiationEvents = getNotificationEvents();
            returncount = TestUtils.verifyNotificationEventPresence(notifiationEvents, eventToBeVerified, eventVerificationCount);

            if (returncount == -1)
                status = false;
            
            else {
                status = true;
                eventVerificationCount = returncount;
            }            

            if (status == true) {
            	logger.info(consoleMessage);
            	return;
            }
        }
        if(!status) {
        	logger.error("ACTUAL notification : "  + getNotificationEvents());
            Assert.fail("Event not found !!!. Expected Event : " + eventToBeVerified);
        }
    }
    
    public SampleAppBasePage selectVideo(By video) {
    	clickElement(video);
    	return this;
    }
    
    public SampleAppBasePage seekVideo() {
    	seekVideo(0 ,0);
    	return this;
    }
    
    public SampleAppBasePage seekVideo(int widthOffSet1, int widthOffSet2) {
    	int [] seekBarPos =  getSeekbarPosition();
        System.out.println(" Seeking -------------------------  ");
        driver.swipe(85, 342, 85+100, 342+342, 3);
        return this;
    }

    public int[] getSeekbarPosition() {
    	//List<WebElement> seekBarElements = driver.findElements(By.xpath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]"))
    	WebElement seekBarField = driver.findElement(By.xpath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeSlider[1]"));
    	int[] seekBarPos = new int[2];
    	seekBarPos[0] = seekBarField.getLocation().getX();  //Width
    	seekBarPos[1] = seekBarField.getLocation().getY(); //height
        System.out.println(" Dimensions bounds value is :-" + seekBarPos[0]);
        System.out.println(" Dimensions bounds value is :-" + seekBarPos[1]);
        System.out.println(" Dimensions bounds value is :-"+seekBarField.getSize().getHeight());
        System.out.println(" Dimensions bounds value is :-"+seekBarField.getSize().getWidth());
        return  seekBarPos;
        
    }
    
    public int getSlidingBarWidth() {
    	return driver.findElement(By.xpath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]")).getSize().getWidth();
    }
    
    public int getSliderPosition() {
    	return driver.findElement(By.xpath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]")).getLocation().getX();
    }

}
