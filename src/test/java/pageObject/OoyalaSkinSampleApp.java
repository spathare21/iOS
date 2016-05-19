package pageObject;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by dulari on 5/17/16.
 */

//playing the video
public class OoyalaSkinSampleApp extends BaseClass {

    public void play_video (AppiumDriver driver)
    {
        System.out.println(" clicking on play button");
        List<WebElement> p = driver.findElementsByClassName("UIAElement");
        p.get(0).click();
    }
}
