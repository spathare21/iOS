package Utils;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by dulari on 1/14/16.
 */
public class logging {


    public boolean getLog(String logfilename, String text, int lastlinenumber) throws IOException {
        String filePath = logfilename;
        // iPad Air 8.1 : 0479400F-8D43-4716-B93D-058060EC7A3D
        // iPhone 6 8.1 : 34E644BB-B258-45B4-9320-E667AE62B5C2
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
            while (( line = bf.readLine()) != null)

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
