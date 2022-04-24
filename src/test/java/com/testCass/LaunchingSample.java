package com.testCass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.configuration.Base;

public class LaunchingSample extends Base {

	private static Logger log = Logger.getLogger(LaunchingSample.class);

	@Test
	public static void normalLaunch() throws IOException {
		try {
			init();
			test = reports.createTest("LaunchingSample");
			log.info("Foods");
			launch("chrome");
			test.log(Status.INFO, "Browser launched");
			navigat("url");
			selectOption("AmazonDropdown_xpath", "Books");
			sendText("AmazonSearchField_xpath", "Harry Potter");
			ClickElement("AmazonClick_xpath");

		} catch (Exception e) {
			test.fail(e, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShort()).build());
		} finally {
			quiteBrowser();
			reports.flush();
		}
	}

}
