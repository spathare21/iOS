package Utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Madhav on 10/27/16.
 */
public class ParseEventFile {
    int count1;
    public int latestCount(String line){


        String[] tokens = line.split(":");
        //System.out.println("printing tokens", +tokens.toString());
        String trimToken = tokens[4].trim();
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

    // Madhav Added
    public int parseeventfile(AppiumDriver driver, String comp, int count ) throws IOException {

        try

        {
            int i=0;
            String line;

            //Madhav
            WebElement text = driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextView[1]"));
            //Madhav Commented
            //System.out.println(" Event value from textbox is "+text.getText());

            //Madhav
            String[] lines = text.getText().split("::::::::::");


            //String line = "";//Madhav Commented

            //line = buf.readLine();// Madhav Commented

            while(i != lines.length){
                line=lines[i++]; //Madhav
                //System.out.println("Inside while Loop"+line);
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

                //line = buf.readLine();//Madhav Commented
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



