package Utils;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by dulari on 1/14/16.
 */
public class logging {


    public boolean getLog(String logfilename, String text, int lastlinenumber, int timeout) throws IOException {
        String filePath = logfilename;
        String textToMatch = text;
        String content;
        boolean found = false;
        int notFound = 0;

        try
        {
            BufferedReader bf = new BufferedReader(new FileReader(filePath));
            int linecount = 0;

            String line;
            //System.out.println("Searching for " + textToMatch + " in file...");
            long startTime = System.currentTimeMillis(); //fetch starting time
            while ((( line = bf.readLine()) != null) && (startTime- System.currentTimeMillis())< timeout)

            {
                // Increment the count and find the index of the word
                linecount++;
                int indexfound = line.indexOf(textToMatch);
                // If greater than -1, means we found the word
                if (indexfound > -1 && linecount > lastlinenumber) {
                    System.out.println("Word was found at position " + indexfound + " on line " + linecount);
                    found= true;
                }
            }

            if(found)
            {
                System.out.println(" Word " + text + " was found");
            }
            else
            {
                System.out.println("Word " + text + " was not found");
            }

            bf.close();
            if( linecount > lastlinenumber)
                lastlinenumber = linecount;
            System.out.println("Last line number is " + lastlinenumber );
            //if(!found)
              //  Assert.assertTrue(found);

            return found;
        }
        catch (IOException e)
        {
            System.out.println("IO Error Occurred: " + e.toString());

            return  false;
        }

    }

}
