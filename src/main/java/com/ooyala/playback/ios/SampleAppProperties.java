package com.ooyala.playback.ios;

import java.io.IOException;
import java.util.Properties;

import com.ooyala.playback.ios.utils.ReadPropertyFile;


public class SampleAppProperties {
	
	private static final String PROPERTY_FILE_PATH = "./src/test/resources/config/";
		
	//Default values
	private String APP_VALUE = "app-debug.apk";
	private String PLATFORM_NAME = "iOS";
	private String AUTOMATION_NAME  = "XCUITest";

	private String appValue;
	private String platformName; 
	private String platformVersion; 
	private String appPackage;
	private String appActivity;	
	private String udid;  
	private String screenMode;
	private String appDir; 
	private boolean showIOSLog = true;
	
	
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

	public String getAppValue() {
		return appValue;
	}

	public void setAppValue(String appValue) {
		this.appValue = appValue;
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
	
	public String getAUTOMATION_NAME() {
		return AUTOMATION_NAME;
	}

	public void setAUTOMATION_NAME(String aUTOMATION_NAME) {
		AUTOMATION_NAME = aUTOMATION_NAME;
	}


	
	
	public void loadSampleAppProperties(String propertyFileName) throws IOException {
		
		Properties prop = ReadPropertyFile.readPropertiesFile(PROPERTY_FILE_PATH + propertyFileName);
		
		setUdid(prop.getProperty("udid"));
		setAppActivity(prop.getProperty("appActivity"));
		setAppDir(prop.getProperty("appDir"));
		setAppValue(
					(prop.getProperty("appValue") == null) ? APP_VALUE : prop.getProperty("appValue"));
		setPlatformName(
					(prop.getProperty("platformName") == null) ? PLATFORM_NAME : prop.getProperty("platformName"));
		setPlatformVersion(prop.getProperty("platformVersion"));
		setAppPackage(prop.getProperty("appPackage"));
		setScreenMode(prop.getProperty("ScreenMode"));
		
	}



	


	
}
