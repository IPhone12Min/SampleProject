package com.demoTest;

import org.testng.annotations.Test;

public class TestClass003 {
  @Test(priority = 6, groups = "Regression")
  public void six() {
	  System.out.println("Test Method 6");
  }
  
  
  @Test(priority = 7, groups = "Regression")
  public void seven() {
	  System.out.println("Test Method 7");
  }
  
  
  @Test(priority = 8)
  public void eight() {
	  System.out.println("Test Method 8");
  }
  
  
  @Test(priority = 9, groups = "Windows.Chrome")
  public void nine() {
	  System.out.println("Test Method 9");
  }
  
  
  @Test(priority = 10)
  public void ten() {
	  System.out.println("Test Method 10");
  }
}
