package Utils;

import org.junit.Assert;

import java.io.IOException;


/**
 * Created by Shivam on 20/05/16.
 */
public class EventVerification {

    public static int count = 0;

    public EventVerification(){

    }

    public void verifyEvent(String eventType,String consoleMessage,int timeout) throws IOException {

        int returncount;


        boolean status=false;
        long startTime = System.currentTimeMillis(); //fetch starting time
        while(!status && (System.currentTimeMillis()-startTime)<timeout) {


            ParseEventFile pe=new ParseEventFile();

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



