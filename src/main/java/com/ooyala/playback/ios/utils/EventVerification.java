package com.ooyala.playback.ios.utils;

import org.testng.Assert;


public class EventVerification {
    
    private int count ;

    public EventVerification(){
        count = 0;
    }

    public void verifyEvent(String eventType,String consoleMessage,int timeout){
         
        int returncount = 0;
        boolean status=false;
        long startTime = System.currentTimeMillis(); //fetch starting time
        
        while(!status && (System.currentTimeMillis()-startTime)<timeout) {
        	// TODO >>>>  returncount = AndroidUtils.parseEventFile(eventType, count);

            if (returncount == -1)
                status = false;
            
            else {
                status = true;
                count = returncount;
            }            

            if (status == true) {
                System.out.println(consoleMessage);
                System.out.println("\n");
            }
        }
        if(!status) {
            Assert.assertTrue(status, "verifyEvent failed !!!!");
        }
    }
}
