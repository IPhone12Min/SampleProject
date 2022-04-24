package com.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class Base {

	public static WebDriver driver;
	public static String projectPath = System.getProperty("user.dir");
	public static FileInputStream fis;
	public static Properties p;
	public static Properties mainProp;
	public static Properties childProp;
	public static Properties orProp;
	public static ExtentReports reports;
	public static ExtentTest test;
	
	public static void init() throws Exception {
		fis=new FileInputStream(projectPath+"//data.properties");
		p = new Properties();
		p.load(fis);
		System.out.println(p.getProperty("ChromeBrowser"));
		
		fis= new FileInputStream(projectPath+"//Environment.properties");
		mainProp=new Properties();
		mainProp.load(fis);
		System.out.println(mainProp.getProperty("Env"));
		
		fis= new FileInputStream(projectPath+"//"+mainProp.getProperty("Env")+".properties");
		childProp=new Properties();
		childProp.load(fis);
		
		fis= new FileInputStream(projectPath+"//or.properties");
		orProp=new Properties();
		orProp.load(fis);
		
		fis = new FileInputStream(projectPath+"//log4j.properties");
		PropertyConfigurator.configure(fis);
		
		reports = ReportManager.reportGenerator();
		
	}
	
	
	public static void launch(String browser) {
		if(browser.equals(p.getProperty("ChromeBrowser"))) {
			System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\SeleniumProject\\SeleniumJavaProject\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}else
		if(browser.equals(p.getProperty("EdgeChromiumBowser"))) {
			System.setProperty("webdriver.edge.driver", "E:\\Selenium\\SeleniumProject\\SeleniumJavaProject\\Drivers\\msedgedriver.exe");
			driver= new EdgeDriver();
			driver.manage().window().maximize();
		}
	}
	
	public static void navigat(String url) {
		//System.out.println(childProp.getProperty(url));
		driver.get(childProp.getProperty(url));
	}
	
	
	public static WebElement getElementType(String locatorKey) {
		WebElement element=null;
		if(locatorKey.endsWith("_id")) {
			element=driver.findElement(By.id(orProp.getProperty(locatorKey)));
		}else
			if(locatorKey.endsWith("_classname")) {
				element=driver.findElement(By.className(orProp.getProperty(locatorKey)));
			}else
				if(locatorKey.endsWith("_xpath")) {
					element=driver.findElement(By.xpath(orProp.getProperty(locatorKey)));
				}else
					if(locatorKey.endsWith("_css")) {
						element=driver.findElement(By.cssSelector(orProp.getProperty(locatorKey)));
					}else
						if(locatorKey.endsWith("_name")) {
							element=driver.findElement(By.name(orProp.getProperty(locatorKey)));
						}else
							if(locatorKey.endsWith("_linktext")) {
								element=driver.findElement(By.linkText(orProp.getProperty(locatorKey)));
							}else
								if(locatorKey.endsWith("_partiallinktext")) {
									element=driver.findElement(By.partialLinkText(orProp.getProperty(locatorKey)));
								}
		return element;
	}
	
	public static void ClickElement(String locatorKey) {
		getElementType(locatorKey).click();
		test.info("Clicked on the button");
	}
	
	public static void sendText(String locatorKey, String text) {
		getElementType(locatorKey).sendKeys(text);
		test.info("Enter the text");
	}
	
	public static void selectOption(String locatorKey, String option) {
		getElementType(locatorKey).sendKeys(option);
		test.info("Selected option from the dropdown");
	}
	
	public static void quiteBrowser() {
		//It will close all the browser session created by Automation
		driver.quit();
		test.info("Closed the browser");
	}
	
	public static String takeScreenShort() throws IOException {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = projectPath+"//ScreenShots//image.png";
		FileUtils.copyFile(source, new File(path));
		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
		 return Base64.getEncoder().encodeToString(imageBytes);
		
	}
}
