package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Shivam on 30/06/16.
 */
public class SDKVersion {

    // getting SDK Version of SDK
    public static void version() throws IOException {
        try
        {
            BufferedReader buf = new BufferedReader(new FileReader("system.log"));

            String line = "";

            line = buf.readLine();

            while (line != null)
            {
                //System.out.println("Line is "+line);
                //System.out.println("Going to Find SDK Version");
                if (line.contains("OOOoyalaPlayer.m : Ooyala SDK version:"))
                {
                    System.out.println("We are executing the script on " + line + "SDK Version") ;
                }
                line = buf.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void gitSHA() throws IOException {
        try
        {
            BufferedReader buf = new BufferedReader(new FileReader("system.log"));

            String line = "";

            line = buf.readLine();

            while (line != null)
            {
                //System.out.println("Line is "+line);
                //System.out.println("Going to Find SDK Version");
                if (line.contains("Ooyala SDK git SHA:"))
                {
                    System.out.println("We are executing the script on following Git SHA " + line + "commit");
                }
                line = buf.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


}



