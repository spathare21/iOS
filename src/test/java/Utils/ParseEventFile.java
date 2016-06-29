package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Shivam on 20/05/16.
 */
public class ParseEventFile {
    int count1;
    public int latestCount(String line){

        //System.out.println("count 1 is " +count1);
        String[] tokens = line.split(":");
        //System.out.println("printing tokens", +tokens.toString());
        String trimToken = tokens[11].trim();
        //System.out.println("trim tocked in " +trimToken);

        count1 = Integer.parseInt(trimToken);
        return count1;
        //System.out.println("count 1 is " +count1);

    }

    public int parseeventfile(String comp, int count ) throws IOException {

        try

        {
            BufferedReader buf = new BufferedReader(new FileReader("system.log"));

            String line = "";

            line = buf.readLine();

            while(line != null){
                //System.out.println(line);
                if(line.contains("state: ERROR"))
                {
                    System.out.println("App crashed");
                    System.exit(0);
                }
                if(line.contains(comp))
                {
                    if (latestCount(line)>count) {

                        System.out.println("Event Recieved From SDK AND Sample App :- " + line);
                        count=latestCount(line);
                        //System.out.println("count is if condition in parse event method is " +count);
                        return count;
                    }

                }

                line = buf.readLine();
                //line = line+1;
            }

        }
        catch (Exception e)
        {
            System.out.println("Exception " + e);
            e.printStackTrace();
        }

        return -1;
    }

}
