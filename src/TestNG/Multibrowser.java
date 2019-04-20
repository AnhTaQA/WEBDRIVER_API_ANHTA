package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;


public class Multibrowser {
	WebDriver driver;
 @Parameters ("browser")
  @BeforeTest
  public void preCondition(String browsername) {
	 if(browsername.equals("firefox")) {
	  driver = new FirefoxDriver();
	  }
	 else if (browsername.equals("chrome")) {
		 System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		 driver = new ChromeDriver();
	 }
	 else if (browsername.equalsIgnoreCase("headless")){
		 ChromeOptions options = new ChromeOptions();
	        options.addArguments("headless");
	        options.addArguments("window-size=1366x768");
	         driver = new ChromeDriver(options);
		 
	 }
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 	  
  }
 @Parameters({"username","password"})
  @Test(invocationCount = 3)
 public void TC_01_TestLoginWithValidAccount(String useremail, String userpass) {
	 driver.get("http://live.guru99.com/index.php/customer/account/login/");
	 driver.findElement(By.xpath("//input[@id ='email']")).sendKeys(useremail);
	 driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys(userpass);
	 driver.findElement(By.xpath("//button[@id ='send2']")).click();
	 
	 AssertJUnit.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	 
	 driver.findElement(By.xpath("//div[@class= 'account-cart-wrapper']//span[text()='Account']")).click();
	 driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	 AssertJUnit.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	 
 }
 
  
  
  
  
  @AfterTest
  public void postConditon
  () {
	  driver.quit();
  }

}
