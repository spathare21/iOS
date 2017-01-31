package com.ooyala.playback.ios.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;



/**
 * 
 * @author nraman
 * Utility to read property file
 */

public class ReadPropertyFile {
	
	public static Properties readPropertiesFile(String propFile) throws IOException  {
        
        Properties prop = new Properties();
        InputStream inputstream = null;
        
        try {
        	inputstream = new FileInputStream(propFile);
        } catch (FileNotFoundException e) {
        	throw new FileNotFoundException("Property file ' " + propFile + " ' not found in ClassPath");	
		}
        
       prop.load(inputstream);
       printLoadedPropertyValues(prop);
       return prop;

    }
	
    private static void printLoadedPropertyValues(Properties prop) {

    	Enumeration<Object> keys = prop.keys();
    	while (keys.hasMoreElements()) {
    	    String key = (String) keys.nextElement();
    	    String value = (String) prop.get(key);
    	    System.out.println(key + " : " + value);
    	}
    	
    }
 
}
