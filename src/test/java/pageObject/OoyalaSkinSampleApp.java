package pageObject;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by dulari on 5/17/16.
 */

//playing the video
public class OoyalaSkinSampleApp extends BaseClass {

   // public void play_video(AppiumDriver driver)
    //{
      //  System.out.println(" clicking on play button");
        //List<WebElement> p = driver.findElementsByClassName("UIAElement");
        //p.get(0).click();
   // }

    // Click on Pay OR Pause button in normal screen
    public static void play_pauseBtn(AppiumDriver driver) throws InterruptedException {

        System.out.println(" clicking on play button");
        List<WebElement> p = driver.findElementsByClassName("UIAElement");
        p.get(0).click();

    }

    public static void select_integration (AppiumDriver driver, int listno)
    {
        List<WebElement> l = driver.findElementsByClassName("UIATableCell");
        l.get(listno).click();
        System.out.println("Freewheel integration selected");
    }

    public static void presenceOfElement(AppiumDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("UIAElement")));
        System.out.println("All element displayed");

    }

    public static void skipButton(AppiumDriver driver)
    {
        driver.findElementByXPath("//UIAButton[@name='Skip Ad']").click();
    }
}
