package com.ooyala.playback.ios;


import com.ooyala.playback.ios.pages.SampleAppBasePage;
import com.ooyala.playback.ios.utils.TestUtils;
import com.ooyala.playback.ios.utils.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import ru.yandex.qatools.allure.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Method;
import java.time.Instant;

/**
 *
 * This class contains methods to attach screenshot to Allure and SDK Version
 *
 */

public class IOSBaseTest implements IHookable {

    final static Logger logger = Logger.getLogger(IOSBaseTest.class);

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] screenshotattach(String testMethodName) throws Exception {
        try {
            String currentDir = System.getProperty("user.dir");
            String Screenshotpath = currentDir + "/res/snapshot/";
            File scrFile = ((TakesScreenshot) WebDriverFactory.getIOSDriver()).getScreenshotAs(OutputType.FILE);
            String imgFile= Screenshotpath + testMethodName + Instant.now().toEpochMilli() + ".jpg";
            File imgf = new File(imgFile);
            FileUtils.copyFile(scrFile, new File(imgFile));
            System.out.println("Screenshot captured");
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
            String logpath = storeLogFile(iTestResult.getMethod().getMethodName(), TestUtils.parseNotificationEvents(new SampleAppBasePage().getNotificationEvents()));
            appendLogToAllure(logpath);
        } catch (Exception e) {
            logger.error("Here is the error",e);
        }
    }

    public static String storeLogFile(String filename, String lines[]) throws Exception {
        String logFileName = filename + "_" + Instant.now().toEpochMilli()+".txt" ;
        String currentDir = System.getProperty("user.dir");
        String logspath = currentDir +"/res/snapshot/"+logFileName;

        File file = new File(currentDir+"/res/snapshot/" + logFileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            for(int i=0; i<lines.length; i++) {
                byte[] contentInBytes = lines[i].getBytes();
                fileOutputStream.write(contentInBytes );
                fileOutputStream.write(System.getProperty("line.separator").getBytes());
            }
            fileOutputStream.flush();
            fileOutputStream.close();
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

    private static byte[] toByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getPath()));
    }
}
