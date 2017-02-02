package com.ooyala.playback.ios.utils;

import org.apache.log4j.Logger;
import org.testng.Assert;


/**
 * 
 * This class contains EventVerfication logic
 *
 */

public class EventVerification {
	
	final static Logger logger = Logger.getLogger(TestUtils.class);
    
    int count = 0;

    public void verifyEvent(String notificationEvents, String eventToBeVerified, String consoleMessage, int timeout){
         
        int returncount = 0;
        boolean status=false;
        long startTime = System.currentTimeMillis();
        
        while(!status && (System.currentTimeMillis() - startTime) < timeout) {
        	TestUtils.verifyNotificationEventPresence(notificationEvents, eventToBeVerified, returncount);

            if (returncount == -1)
                status = false;
            
            else {
                status = true;
                count = returncount;
            }            

            if (status == true) 
            	logger.info(consoleMessage);
        }
        if(!status) 
            Assert.fail("VerifyEvent failed !!!!");
    }
}
