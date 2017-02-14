package com.ooyala.playback.ios.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
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
	private final By SEEK_BAR = By.xpath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]");
	private final By SLIDER = By.xpath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeImage[1]");
	
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
    	if (!isElementPresent(TOOL_BAR)) {
    		tapScreen();
    		logger.info("Screen tapped");
    	}
    	return this;
    }
    
     public SampleAppBasePage waitAndTapScreen(int sec) {
    	waitForSec(sec);
    	if (!isElementPresent(TOOL_BAR)) {
    		tapScreen();
    		logger.info("Screen tapped");
    	}
    	return this;
    }
    
    public SampleAppBasePage tapScreen() {
    	//BAD XPATH. have to modify this. FindElements approach takes lot of time (> 5sec).
    	driver.findElement(By.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")).click();
    	return this;
    }
    
    public SampleAppBasePage playVideo() {
    	tapScreenIfRequired();
    	try {
    		waitAndFindElement(PLAY_PAUSE_BUTTON).click();
    	} catch (Exception e) {
			logger.info("Play button not found. Tapping screen and retrying..");
			tapScreenIfRequired();
			waitAndFindElement(PLAY_PAUSE_BUTTON).click();
		}
    	
    	return this;
    }
    
    public SampleAppBasePage pauseVideo() {
    	tapScreenIfRequired();
    	try {
    		waitAndFindElement(PLAY_PAUSE_BUTTON).click();
    	} catch (Exception e) {
			logger.info("Play button not found. Tapping screen and retrying..");
			tapScreenIfRequired();
			waitAndFindElement(PLAY_PAUSE_BUTTON).click();
		}
    	
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
	                waitForSec(1);
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

    
    public SampleAppBasePage seekVideoBack() throws InterruptedException {
    	int startx = getSliderPosition();
    	Element seekbar =  getSeekBarPosition();
        logger.info("Seeking back -------------------------  ");
        tapScreenIfRequired();
        int seekBackLength = ((startx + 1) - seekbar.getStartXPosition()) / 2; // seek back till middle of the played time
        driver.swipe((startx + 1), seekbar.getYposition(), ((startx + 1) - seekBackLength), seekbar.getYposition() + seekbar.getYposition(), 3);
        return this;
    }
    
    public SampleAppBasePage seekVideoForward() throws InterruptedException {
    	int startx = getSliderPosition();
    	Element seekbar =  getSeekBarPosition();
        logger.info("Seeking forward -------------------------  ");
        tapScreenIfRequired();
        int seekForwardLength = (seekbar.getEndXPosition() - (startx + 1)) - 30; //This will seek just before end of the the video
        driver.swipe((startx + 1), seekbar.getYposition(), ((startx + 1) + (seekForwardLength)), seekbar.getYposition() + seekbar.getYposition(), 3);
        return this;
    }

    public Element getSeekBarPosition() throws InterruptedException {
    	waitAndTapScreen(3);
    	Point seekbarElementPos = driver.findElement(SEEK_BAR).getLocation();
    	Element seekbar = new Element();
    	seekbar.setStartXPosition(seekbarElementPos.getX());
    	seekbar.setYposition(seekbarElementPos.getY());
    	waitForSec(3);
    	tapScreenIfRequired();
    	seekbar.setWidth(driver.findElement(SEEK_BAR).getSize().getWidth());
    	seekbar.setEndXPosition(seekbar.getWidth() + seekbar.getStartXPosition());
    	logger.info("SeekBarPosition : StartXPosition > " + seekbar.getStartXPosition() + ", "
    			     + " EndXPosition > " + seekbar.getEndXPosition() + ", Width > " + seekbar.getWidth() + " Yposition > " + seekbar.getYposition());
    	return seekbar;
																				
    }
    
    public int getSliderPosition() throws InterruptedException {
    	waitAndTapScreen(5);
    	int sliderXPosition = driver.findElement(SLIDER).getLocation().getX();
    	logger.info("Slider X Position >> : " + sliderXPosition);
    	return sliderXPosition;
    }
    
    public WebElement waitAndFindElement(By locator) {
    	waitForPresence(locator);
    	logger.info("Wait for element succeeded");
    	return driver.findElement(locator);
    }
    
    public SampleAppBasePage letVideoPlayForSec(int sec) throws InterruptedException {
    	int count = 0;
    	while(count < sec) {
    		waitForSec(1);
    		count++;
    	}
    	
    	return this;
    }
    
    public void waitForSec(int sec) {
    	try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@SuppressWarnings("unused")
    private class Element {
    	private int startXPosition;
    	private int endXPosition;
		private int yPosition;
		private int width;
		int height;

		public int getYposition() {
			return yPosition;
		}

		public void setYposition(int yPosition) {
			this.yPosition = yPosition;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getStartXPosition() {
			return startXPosition;
		}

		public void setStartXPosition(int xPosition) {
			this.startXPosition = xPosition;
		}
		

		public int getEndXPosition() {
			return endXPosition;
		}

		public void setEndXPosition(int xPosition) {
			this.endXPosition = xPosition;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
    	
    	
    }

}
