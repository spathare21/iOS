package pageObject;



import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;


/**
 * Created by Shivam on 13/06/16.
 */
public class AdvancedplaybackSampleApp {

    public static void select_asset(AppiumDriver driver, int a) {
        System.out.println("Selecting Video 2");
        java.util.List<WebElement> l = driver.findElementsByClassName("UIAButton");
        l.get(a).click();

    }

    public static void custom_Play_pause(AppiumDriver driver)
    {
        java.util.List<WebElement> l = driver.findElementsByClassName("UIAButton");
        l.get(2).click();
}
}
