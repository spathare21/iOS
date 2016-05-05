package Utils;

import java.io.IOException;
import java.util.*;
import java.io.IOException;
import java.io.*;


public class getLog {

    public static void getlog(String udid) throws IOException {
        System.out.println("execting command for system log");
        System.out.println("in getlog udid is : " + udid);
        String command = "idevicesyslog -u " + udid + " >> system.log";

        System.out.println("command is " + command);

        String[] final_command = CommandLine.command(command);
        System.out.println("final command is " + final_command);

        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(final_command);
        System.out.println("logs are getting saved in system.log file");
    }

    public static void delete(String logfile) throws IOException {
        System.out.println("Deleting system log file");
        final String cmd = "rm -rf " + logfile;
        String[] final_command = CommandLine.command(cmd);
        System.out.println("The command we are executing is " +cmd);
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(final_command);

    }
}