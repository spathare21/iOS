package com.ooyala.playback.ios.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.testng.Assert;


/**
 * 
 * @author nraman
 * Test utilities like TakeScreenshot, StoreLogFile, SPlitstring, readfile, etc..
 *
 */

public class TestUtils {
	
	final static Logger logger = Logger.getLogger(TestUtils.class);
	
	public static String getCallerClassNameFromThread() {

		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
		return stackTraceElement.getClassName();

	}
	
    public static int getLatestCount(String line){
        int count;
        String[] tokens = line.split(":");
        String trimToken = tokens[4].trim();
        count = Integer.parseInt(trimToken);
        return count;
    }

    public static int verifyNotificationEventPresence(String notificationEvents, String eventToBeVerified, int count) {
        try {
        	
        	String lines[] = parseNotificationEvents(notificationEvents);
            for (String line : lines){
                
                if(line.contains("state: ERROR")) {
                	logger.error("APP CRASHED !!!!! ");
                    Assert.fail("App is crashed during playback");
                    
                }
                if(line.contains(eventToBeVerified)) {
                  if (getLatestCount(line) > count) {
                	  	logger.info("Event Recieved From SDK AND Sample App :- " + line);
                        count = getLatestCount(line);
                        return count;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Exception while parsing notification events " + e);
            e.printStackTrace();
        }

        return -1;
    }
    
    
    public static String[] parseNotificationEvents(String notificationEvents) {
    	String[] lines = notificationEvents.split("::::::::::");
    	return lines;
    	
    }



}
