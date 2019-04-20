package Selenium;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
	  
	  //ep 01 - Truy cập vào trang: http://www.myntra.com/
			driver.get("http://www.myntra.com/");
		//Step 02 - Hover chuột vào Menu để login
			driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']")).click();
		//Step 03 - Chọn Login button
			driver.findElement(By.xpath("//a[contains(text(),'log in')]")).click();	
		//Step 04 - Verify Login form được hiển thị
			String login = driver.findElement(By.xpath("//div[@class='login-box']")).getText();
			Assert.assertEquals(login,"Login to Myntra: ");
			System.out.println("Login to Myntra: " + login);
  }
	  

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
