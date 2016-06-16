package Utils;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.*;
import java.io.IOException;
import java.io.*;


public class getLog {

    public static Process pro;

    // capturing the iDevices log and saving in one System.log file
    public static void getlog(String udid) throws IOException {
        System.out.println("executing command for system log");
        System.out.println("in getlog udid is : " + udid);
        final String command = "idevicesyslog -u " + udid + " >> system.log";

        System.out.println("command is " + command);

        String[] final_command = CommandLine.command(command);
        System.out.println("final command is " + final_command);

        Runtime run = Runtime.getRuntime();
        pro = run.exec(final_command);
        System.out.println("logs command has been excuted");
    }

    // Delete the System.log file after class.
    public static void delete() throws IOException {
        System.out.println("Deleting system log file");
        final String cmd = " rm  -rf  system.log";
        String[] final_command = CommandLine.command(cmd);
        System.out.println("The command we are executing is " + cmd);
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(final_command);

    }

    // getting UDID of iDevices from command line
    public static String getUdid() throws IOException {

        String deviceid = "";

        System.out.println("executing command for getting Device udid");
        final String command1 = "idevice_id --list";

        System.out.println("command is " + command1);

        String[] final_command1 = CommandLine.command(command1);
        System.out.println("final command to get udid is " + final_command1);

        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(final_command1);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(pr.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(pr.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String deviceudid = null;
        while ((deviceudid = stdInput.readLine()) != null) {
            System.out.println(" Device Id  :" + deviceudid + "\n");
            deviceid = deviceudid;

        }
        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((deviceudid = stdError.readLine()) != null) {
            System.out.println("Some error occured while executing command ");
        }
        return deviceid;
    }


    public static String getDeviceInfo() throws IOException {

        String deviceInfo = " ";
        String DeviceOs = " ";
        String DeviceName = " ";

        System.out.println("executing command for getting Device Info");
        final String command2 = "ideviceinfo";

        System.out.println("command is " + command2);

        String[] final_command2 = CommandLine.command(command2);
        System.out.println("final command to get Device Info " + final_command2);

        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(final_command2);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(pr.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(pr.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String deviceinfo = null;
        while ((deviceinfo = stdInput.readLine()) != null) {

            if (deviceinfo.contains("DeviceName")) {
                DeviceName = deviceinfo;
                System.out.println("" + DeviceName + "\n");
            }

            if (deviceinfo.contains("ProductVersion")) {
                DeviceOs = deviceinfo;
                System.out.println("Device Os is :" + DeviceOs + "\n");
            }

        }
        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((deviceinfo = stdError.readLine()) != null) {
            System.out.println("error occured while executing command ");
        }
        return (DeviceName + " " + DeviceOs);
    }


    //uninstalling app after execution of all tests
    public static void appUninstall(String ap) throws IOException, InterruptedException {
        String app = "";
        System.out.println("uninstalling application");

        final String command = "ios-deploy --list_bundle_id | grep  " +  ap  + " ";
        System.out.println("command to list bundle ids is >> " +command);

        String[] final_command = CommandLine.command(command);

        Thread.sleep(2000);
        Process pr = Runtime.getRuntime().exec(final_command);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(pr.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(pr.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String app_name = null;
        while ((app_name = stdInput.readLine()) != null) {
            System.out.println(" get app_name is   :" + app_name + "\n");
            app = app_name;

        }
        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((app_name = stdError.readLine()) != null) {
            System.out.println("Some error occured while executing command ");
        }

        Thread.sleep(2000);
        final String command1 = "ios-deploy --uninstall_only --bundle_id " + app;
        System.out.println("final caommand1 to uninstall app is " + command1);

        String[] final_command2 = CommandLine.command(command1);

        Runtime run2 = Runtime.getRuntime();
        Process pr2 = run2.exec(final_command2);

        System.out.println("app uninstalled");

    }

    public static void reboot () throws IOException, InterruptedException {
        System.out.println("executing command for system log");

        final String command = "idevicediagnostics restart";

        System.out.println("command is " + command);

        String[] final_command = CommandLine.command(command);
        System.out.println("final command is " + final_command);

        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(final_command);
        System.out.println("logs command has been excuted");
        Thread.sleep(100000);
    }

}