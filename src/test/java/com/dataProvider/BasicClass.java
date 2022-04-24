package com.dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BasicClass {
	
	public static WebDriver driver;
	
  @Test(dataProvider = "LoginTestData")
  public void testLogin(String username, String password) {
	  System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\SeleniumProject\\SeleniumJavaProject\\Drivers\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://opensource-demo.orangehrmlive.com/");
	  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//a[@id='welcome']")).isDisplayed());
	  driver.quit();
  }
  
  
  @DataProvider(name="LoginTestData", parallel = true)
  public Object[][] loginData() {
	  Object [][] data= new Object[2][2];
	  data[0][0] = "Admin";
	  data[0][1] = "admin123";
	  
	  data[1][0] = "Admin";
	  data[1][1] = "admin12";
	  
	  return data;
  }
}
