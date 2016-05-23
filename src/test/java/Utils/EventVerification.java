package Utils;

import org.junit.Assert;

import java.io.IOException;


/**
 * Created by Shivam on 20/05/16.
 */
public class EventVerification {

    private int count ;

    public EventVerification(){
        count=0;
    }

    public void verifyEvent(String eventType,String consoleMessage,int timeout) throws IOException {

        int returncount;

        System.out.println("in verify event");
        // Paused  Verification
        boolean status=false;
        long startTime = System.currentTimeMillis(); //fetch starting time
        while(!status && (System.currentTimeMillis()-startTime)<timeout) {

            //status = ParseEventsFile.parseeventfile("stateChanged - state: PAUSED");
            ParseEventFile pe=new ParseEventFile();
            System.out.println("event type is" +eventType);
            System.out.println("count is " +count);
            returncount = pe.parseeventfile(eventType, count);

            if (returncount== -1){
                status=false;
            }
            else{
                status=true;
                count=returncount;
            }

            if (status == true) {
                System.out.println(consoleMessage);
                System.out.println("\n");
            }
        }
        if(!status){
            Assert.assertTrue(status);
        }
    }
}



