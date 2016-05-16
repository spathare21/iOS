package pageObject;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Utils.*;
import java.io.IOException;
import java.util.List;


/**
 * Created by Shivam on 16/05/16.
 */
public class BasicPlayBackSampleApp {

    // Click on required test asset
    public static void assetSelect(AppiumDriver driver, int assetNo){
        List<WebElement> asset = driver.findElementsByClassName("UIATableCell");
        asset.get(assetNo).click();
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
        List<WebElement> button = driver.findElementsByClassName("UIAWindow");
        button.get(0).click();
    }

    // Click on Pay OR Pause button in normal screen
    public static void play_pauseBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 3);
        Thread.sleep(1000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(1).click();
    }

    // Click on fullscreen button
    public static void fullscreenBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 3);
        Thread.sleep(1000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(3).click();
    }

    // Click on Pay OR Pause button in full screen
    public static void play_pause_fullscreenBtn(AppiumDriver driver) throws InterruptedException {
        driver.tap(1, 200, 300, 3);
        Thread.sleep(1000);
        List<WebElement> button = driver.findElementsByClassName("UIAButton");
        button.get(3).click();
    }



}
