package com.ooyala.playback.ios.utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Shivam on 08/02/17.
 */

/**
 * Getting Jenkins jobs URL after hitting the job on SC node.
 */
public class JenkinsUtils {
    final static Logger logger = Logger.getLogger(JenkinsUtils.class);


    public static String getJenkinsBuildnumber(){

        String lnk = "http://jenkins-master1.services.ooyala.net:8080/job/Appium-iOS-SampleApp-Quicklook-dev/lastBuild/api/json";
        String buildNumber = null;

        try {
            URL url = new URL(lnk);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                if(inputLine.contains("\"id\""))
                {
                    String[] _build = inputLine.split(":");
                    for (int i =0; i<_build.length; i++){
                        if(_build[i].contains("\"id\""))   {
                            String [] buildno = _build[i+1].split(",");
                            buildNumber = buildno[0].replace("\"","");
                        }
                    }
                    break;
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            logger.error("Error occured while parsing the build id",ex);
        }
        return buildNumber;
    }

    public static String getJenkinsBuildURL()
    {
        String buildId = getJenkinsBuildnumber();
        String link = "http://jenkins-master1.services.ooyala.net:8080/job/Appium-iOS-SampleApp-Quicklook-dev/"+buildId+"/console";
        return  link;
    }
}
