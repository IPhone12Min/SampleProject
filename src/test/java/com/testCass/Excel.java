package com.testCass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.configuration.Base;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Excel extends Base{
	
	//public static WebDriver driver;
	public static DataFormatter df=new DataFormatter();
	
	  @Test(dataProvider = "ExcelDataProvider")
	  public void loginVerificationSoftAssert(String[] d) {
		  //System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\SeleniumProject\\SeleniumJavaProject\\Drivers\\chromedriver.exe");
		  WebDriverManager.chromedriver().setup();
		  WebDriver driver=new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get("https://opensource-demo.orangehrmlive.com/");
		  driver.findElement(By.xpath("//input[@id='txtUsername']")).clear();
		  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(d[0]);
		  driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(d[1]);
		  driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		  Assert.assertTrue(driver.findElement(By.xpath("//a[@id='welcome']")).isDisplayed());
		  driver.findElement(By.xpath("//a[@id='menu_pim_viewMyDetails']")).click();
		  driver.quit();
	  }
	  
	  @DataProvider(name="ExcelDataProvider", parallel = true)
	  public String[][] excelData() throws IOException {
		  File file=new File(projectPath+"//DataSheet.xlsx");
		  FileInputStream fi=new FileInputStream(file);
		  XSSFWorkbook workbook=new XSSFWorkbook(fi);
		  XSSFSheet sh=workbook.getSheet("Sheet1");
		  
		  int noofRows=sh.getPhysicalNumberOfRows();
		  int noofColums=sh.getRow(0).getLastCellNum();
		  String[][] data=new String[noofRows][noofColums];
		  for (int i = 0; i < noofRows; i++) {
			  for (int j = 0; j < noofColums; j++) {
				  
				  //data[i][j] = df.formatCellValue(sh.getRow(i).getCell(j));
				  data[i][j] = sh.getRow(i).getCell(j).toString();
			}
			
		}
		 workbook.close();
		 fi.close();
		 return data;
		  
	  }
}
