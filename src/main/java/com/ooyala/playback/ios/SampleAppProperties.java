package com.ooyala.playback.ios;

import java.io.IOException;
import java.util.Properties;

import com.ooyala.playback.ios.utils.ReadPropertyFile;


/**
 * 
 * @author nraman
 * This class is object mapper for apps properties file
 *
 */

public class SampleAppProperties {
	
	private static final String PROPERTY_FILE_PATH = "./src/test/resources/config/";
		
	//Default values
	private String PLATFORM_NAME = "iOS";
	private String AUTOMATION_NAME  = "XCUITest";

	private String appName;
	private String platformName; 
	private String platformVersion; 
	private String appPackage;
	private String appActivity;	
	private String udid;  
	private String screenMode;
	private String appDir; 
	private boolean showIOSLog = true;
	private String deviceName;
	private int newCommandTimeOut = 50000;
	
	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getScreenMode() {
		return screenMode;
	}

	public void setScreenMode(String screenMode) {
		this.screenMode = screenMode;
	}

	public String getAppDir() {
		return appDir;
	}

	public void setAppDir(String appDir) {
		this.appDir = appDir;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPlatformVersion() {
		return platformVersion;
	}

	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	public String getAppActivity() {
		return appActivity;
	}

	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}

	public boolean showIOSLog() {
		return showIOSLog;
	}

	public void setShowIOSLog(boolean showIOSLog) {
		this.showIOSLog = showIOSLog;
	}
	
	public String getAutomationName() {
		return AUTOMATION_NAME;
	}

	public void setAutomationName(String aUTOMATION_NAME) {
		AUTOMATION_NAME = aUTOMATION_NAME;
	}


	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getNewCommandTimeOut() {
		return newCommandTimeOut;
	}

	public void setNewCommandTimeOut(int newCommandTimeOut) {
		this.newCommandTimeOut = newCommandTimeOut;
	}
	
	public void loadSampleAppProperties(String propertyFileName) throws IOException {
		
		Properties prop = ReadPropertyFile.readPropertiesFile(PROPERTY_FILE_PATH + propertyFileName);
		
		setUdid(prop.getProperty("udid"));
		setAppDir(prop.getProperty("appDir"));
		setAppName(prop.getProperty("appName"));
		setPlatformName(
					(prop.getProperty("platformName") == null) ? PLATFORM_NAME : prop.getProperty("platformName"));
		setScreenMode(prop.getProperty("ScreenMode"));
		setNewCommandTimeOut(Integer.parseInt(prop.getProperty("newCommandTimeout")));
		
	}



	
}
