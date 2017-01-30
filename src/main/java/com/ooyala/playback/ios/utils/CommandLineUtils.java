package com.ooyala.playback.ios.utils;

import java.io.IOException;

public class CommandLineUtils {
	
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static Process execCommand(String command) throws IOException{
        String[] new_command;
        if(isWindows()) 
            new_command = new String[] {"cmd.exe", "/c", command};
        else 
            new_command = new String[]{"/bin/sh", "-c", command};
        
        Runtime run = Runtime.getRuntime();
        return run.exec(new_command);

    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }


}
