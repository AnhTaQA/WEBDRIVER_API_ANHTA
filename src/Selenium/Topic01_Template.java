package Selenium;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic01_Template{
	WebDriver driver;
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("");
	  }
  @Test
  public void TC_01_CheckURL() {
	  
	  // check intergration between Slack and Github
  }
  @Test
  public void TC_02_CheckTitle() {
  }
  
	  

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
