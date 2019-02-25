package Selenium;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic01_CheckEnvironment {
	WebDriver driver;
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("http://live.guru99.com/");
	  }
  @Test
  public void TC_01_CheckURL() {
	  String homePageURL = driver.getCurrentUrl();
	  Assert.assertEquals(homePageURL, "http://live.guru99.com/");
	  
	  // check intergration between Slack and Github
  }
  @Test
  public void TC_02_CheckTitle() {
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Home page");
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
