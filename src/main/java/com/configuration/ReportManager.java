package com.configuration;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager extends Base{
	
	public static ExtentReports rep;
	public static ExtentSparkReporter spark;
	
	public static ExtentReports reportGenerator() throws IOException {
		if(reports==null) {
			rep = new ExtentReports();
			spark = new ExtentSparkReporter(projectPath+"//ExtentReportHTML//ExtentReport.html");
			File fil = new File("ExtentReport.xml");
			spark.loadXMLConfig(fil);
			rep.attachReporter(spark);
		}
		return rep;
	}

}
