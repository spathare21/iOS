package pageObject;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import Utils.*;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Madhav on 11/01/16.
 */
public class BaseClass {
    // Click on required test asset
    public static void assetSelect(AppiumDriver driver, int assetNo){
        List<WebElement> asset = driver.findElementsByClassName("UIATableCell");
        asset.get(assetNo).click();
    }

    public static void waitForElementBasedOnXpath(AppiumDriver driver, String xPathString){


        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathString)));
    }




    public static boolean sdkVersion(String LogFilePath, int lastlinenumber) throws IOException {
        logging _utils = new logging();
        boolean found = false;
        System.out.println("file path opf log file is >>"+ LogFilePath);
        found = _utils.getLog(LogFilePath,"Ooyala SDK version",lastlinenumber);
        if(found == true) {
            Assert.assertTrue(found);
            return found;
        }
        else
        {
            return false;
        }
    }

    // Click on Master button in normal screen
    public static void masterBtn(AppiumDriver driver){
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(0).click();
    }

    // Click on Pay OR Pause button in normal screen
    public static void play_pauseBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 3);
        Thread.sleep(2000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(2).click();
    }

    // Click on fullscreen button
    public static void fullscreenBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 5);
        Thread.sleep(2000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(3).click();
    }

    // Click on Done button
    public static void doneBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 5);
        Thread.sleep(2000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(0).click();
    }

    // Click on Pay OR Pause button in full screen
    public static void play_pause_fullscreenBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 5);
        Thread.sleep(2000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(3).click();
    }

    public static  void overlay(AppiumDriver driver)
    {
        boolean found = false;
        try{
            List<WebElement> l  = driver.findElementsByClassName("UIAScrollView");
            found = l.get(0).isDisplayed();
            System.out.println("Overlay displayed");
            found = true;

        }
        catch (Exception e)
        {
            System.out.println("value of found" +found);
        }

        if(!found)
        {
            Assert.assertTrue(!found);
        }
    }


    // Click on CC button
    public static void ccBtn(AppiumDriver driver, String screen) throws InterruptedException {
        driver.tap(1, 200, 300, 5);
        Thread.sleep(2000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        if(screen == "normal"){
            button.get(3).click();
        }
        else{
            button.get(1).click();
        }

    }

    // Click on CC button
    public static void ccDoneBtn(AppiumDriver driver) throws InterruptedException {
     //   driver.tap(1, 200, 300, 5);
        Thread.sleep(2000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(0).click();
    }

}
