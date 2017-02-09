package com.ooyala.playback.ios.listeners;


import com.ooyala.playback.ios.utils.JenkinsUtils;
import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author nraman
 * Listener class for reporting / onTestSuccess / onTestFailure / Retries..
 */

public class IOSTestListener implements IReporter {

    final static Logger logger = Logger.getLogger(IOSTestListener.class);

    public static LinkedHashMap<String, String> Testdata =
            new LinkedHashMap<String, String>();

    @Override
    public void generateReport(List<XmlSuite> xmlSuite, List<ISuite> iSuites, String s) {
        for (ISuite suits : iSuites) {
            //logger.debug("Suite Name : " + suits.getName());
            Map<String, ISuiteResult> result = suits.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                int psize = context.getSuite().getXmlSuite().getTests().size();
                String[] packagename = new String[psize];
                for (int i = 0; i < psize; i++) {
                    packagename[i] = context.getSuite().getXmlSuite().getTests().get(i).getClasses().get(i).getName();
                }
                setTestResult(Integer.toString(context.getPassedTests().size()), Integer.toString(context.getFailedTests().size()), Integer.toString(context.getSkippedTests().size()), Integer.toString(context.getAllTestMethods().length), context.getName(), packagename);
            }
        }
    }

    public void setTestResult(String pass, String fail, String skip, String total, String suiteName, String[] Package) {
        Date date = new Date();
        String CurrntDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        Testdata.put("Date", CurrntDate);
        Testdata.put("SuiteName", suiteName);
        for (int i = 0; i < Package.length; i++) {
            Testdata.put("Package" + i, Package[i]);
        }
        Testdata.put("Jenkins URL", JenkinsUtils.getJenkinsBuildURL());
        Testdata.put("Jenkins Report URL", "http://jenkins-master1.services.ooyala.net:8080/job/appium-android-test-2-dev/"+JenkinsUtils.getJenkinsBuildURL()+"/allure/#/");
        Testdata.put("Total", total);
        Testdata.put("Pass", pass);
        Testdata.put("Fail", fail);
        Testdata.put("Skip", skip);
        logger.debug("size of map : " + Testdata.size());
        for (String key : Testdata.keySet()) {
            String value = Testdata.get(key);
        }
    }
}