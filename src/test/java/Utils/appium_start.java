package Utils;

import java.io.IOException;

/**
 * Created by Shivam on 13/06/16.
 */
public class appium_start {

    public static void appiumStart () throws IOException, InterruptedException {
        System.out.println("executing command for starting the appium");

        final String command = "appium --session override";

        System.out.println("command is " + command);

        String[] final_command = CommandLine.command(command);
        System.out.println("final command is " + final_command);

        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(final_command);
        System.out.println("logs command has been excuted");
        Thread.sleep(100000);
    }


}

