package com.ooyala.playback.ios;


import com.ooyala.playback.ios.utils.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static com.google.common.io.Files.toByteArray;

/**
 *
 * This class contains methods to attach screenshot to Allure and SDK Version
 *
 */

public class IOSBaseTest implements IHookable {

    final static Logger logger = Logger.getLogger(IOSBaseTest.class);

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] screenshotattach(String testMethodName) throws Exception
    {
        try {
            String currentDir = System.getProperty("user.dir");
            String Screenshotpath = currentDir + "/res/snapshot/";
            File scrFile = ((TakesScreenshot) WebDriverFactory.getIOSDriver()).getScreenshotAs(OutputType.FILE);
            String imgFile= Screenshotpath + testMethodName + Instant.now().toEpochMilli() + ".jpg";
            File imgf = new File(imgFile);
            FileUtils.copyFile(scrFile, new File(imgFile));
            return toByteArray(imgf);
        }
        catch(Exception e)
        {
            logger.error("Exception while taking screenshot : " + e.getMessage());
        }
        return new byte[0];
    }

    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {

        iHookCallBack.runTestMethod(iTestResult);
        try {
            screenshotattach(iTestResult.getMethod().getMethodName());
            sdkVersion();
        } catch (Exception e) {
            logger.error("Here is the error",e);
        }
    }

    public static void sdkVersion() throws IOException {

        List<LogEntry> logEntries = WebDriverFactory.getIOSDriver().manage().logs().get("logcat").getAll();
        for (int i=0;i<logEntries.size();i++)
        {
            if(logEntries.get(i).toString().contains("Ooyala SDK Version:"))
            {
                logger.info(logEntries.get(i).toString());
                String sdkVersion = logEntries.get(i).toString();
                logger.info("SDK version is" +sdkVersion);
                String[] version = sdkVersion.split(":");
                if(version.length>6)
                    sdkVersion = version[6];
            }
        }
    }

}
