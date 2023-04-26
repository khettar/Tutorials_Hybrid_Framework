package com.tutorials.qa.utils;

import java.util.Date;

public class Utilities {
	
public static String generateEmailWithTimeStamp() {
		
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "khettar123"+timestamp+"@gmail.com";
	}
public static final int implicitlyWaitTime = 10;
public static final int pageloadTime = 10;
public static final int scriptTime = 100;
}
