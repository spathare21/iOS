package com.ooyala.playback.ios;


import com.ooyala.playback.ios.pages.SampleAppBasePage;
import com.ooyala.playback.ios.utils.CommandLineUtils;
import com.ooyala.playback.ios.utils.TestUtils;
import com.ooyala.playback.ios.utils.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import ru.yandex.qatools.allure.annotations.*;

import java.io.*;
import java.lang.reflect.Method;
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
            String logpath = storeLogFile(iTestResult.getMethod().getMethodName(), TestUtils.parseNotificationEvents(new SampleAppBasePage().getNotificationEvents()));
            appendLogToAllure(logpath);
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


    public static String storeLogFile(String filename, String lines[]) throws Exception {
        String logFileName = filename + "_" + Instant.now().toEpochMilli();
        String currentDir = System.getProperty("user.dir");
        String logspath = currentDir + "/res/snapshot/"+logFileName;

        File file = new File(logFileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            for(int i=0; i<lines.length; i++) {
                fileWriter.write(lines[i]);
            }
        }catch(IOException e){
            logger.error(e);
        }
        return logspath;
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static byte[] appendLogToAllure(String logspath) throws Exception {
        try {
            String filename = logspath;
            File logfilename = new File(filename);
            logger.debug("attach loggile "+ logfilename + " to allure");
            Thread.sleep(5000);
            return FileUtils.readFileToByteArray(logfilename);
        } catch (IOException ignored) {
            return null;
        }
    }
    
    @BeforeMethod(alwaysRun=true)
    public void beforeMethod(Method method) {
    	logger.info(" >>>>>>>>> Executing Test case : " + method.getName() + ">>>>>>>>>>");
    }



}
