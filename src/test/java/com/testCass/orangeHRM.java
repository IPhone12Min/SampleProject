package com.testCass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class orangeHRM {
	public static WebDriver driver;
	public static SoftAssert soft = new SoftAssert();
	
  @Parameters("url")
  @Test
  public void login(String url) {
	  System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\SeleniumProject\\SeleniumJavaProject\\Drivers\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get(url);
	  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
	  driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	  driver.quit();
  }
  
  @Test
  public void loginVerification() {
	  System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\SeleniumProject\\SeleniumJavaProject\\Drivers\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://opensource-demo.orangehrmlive.com/");
	  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
	  driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	  String actual=driver.findElement(By.xpath("//a[@id='welcome']")).getText();
	  Assert.assertEquals(actual.substring(0, 7), "Welcome", "Message miss match");
	  driver.findElement(By.xpath("//a[@id='menu_pim_viewMyDetails']")).click();
	  driver.quit();
  }
  
  @Test
  public void loginVerificationSoftAssert() {
	  System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\SeleniumProject\\SeleniumJavaProject\\Drivers\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://opensource-demo.orangehrmlive.com/");
	  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
	  driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	  String actual=driver.findElement(By.xpath("//a[@id='welcome']")).getText();
	  soft.assertEquals(actual.substring(0, 7), "Welcome", "Message miss match");
	  driver.findElement(By.xpath("//a[@id='menu_pim_viewMyDetails']")).click();
	  driver.quit();
	  soft.assertAll();
  }
  
}
