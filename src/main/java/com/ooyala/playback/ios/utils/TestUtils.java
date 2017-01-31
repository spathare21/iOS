package com.ooyala.playback.ios.utils;


/**
 * 
 * @author nraman
 * Test utilities like TakeScreenshot, StoreLogFile, SPlitstring, readfile, etc..
 *
 */

public class TestUtils {
	
	public static String getCallerClassNameFromThread() {

		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
		return stackTraceElement.getClassName();

	}


}
