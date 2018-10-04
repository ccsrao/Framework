package com.configurations;
import java.text.SimpleDateFormat; 
import java.util.Date;

public class ReportPaths {
	/* Report file name with date and time stamp*/
	public static final String  reportPathName =  new SimpleDateFormat("YYYY-MM-dd-hh-mm-ss-SSS").format(new Date())+"_Report.html";
}
