package com.demoTest;

import org.testng.annotations.Test;

@Test(groups = "all")
public class TestClass001 {
  @Test(priority = 1, groups = {"Smoke", "Regression"})
  public void one() {
	  System.out.println("Test Method 1");
	  NullPointerException Exception = new NullPointerException("User Define Exception");
	  throw Exception;
  }
  
  
  @Test(priority = 2, groups = "Smoke")
  public void two() {
	  System.out.println("Test Method 2");
  }
  
  
  @Test(priority = 3, dependsOnMethods = {"one","two"}, alwaysRun = true)
  public void three() {
	  System.out.println("Test Method 3");
  }
  
  
  @Test(priority = 4, groups = "Windows.Chrome")
  public void four() {
	  System.out.println("Test Method 4");
  }
  
  
  @Test(priority = 5, groups = "Windows.Chromium")
  public void five() {
	  System.out.println("Test Method 5");
  }
}
