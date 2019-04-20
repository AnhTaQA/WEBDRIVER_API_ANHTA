package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestDataProvider {
	WebDriver driver;
 
  @BeforeTest
  public void preCondition() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 	  
  }
  @Test(dataProvider ="userPassInfo" )
 public void TC_01_TestLoginWithValidAccount(String email ,String password) {
	 driver.get("http://live.guru99.com/index.php/customer/account/login/");
	 driver.findElement(By.xpath("//input[@id ='email']")).sendKeys(email);
	 driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys(password);
	 driver.findElement(By.xpath("//button[@id ='send2']")).click();
	 
	 AssertJUnit.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	 
	 driver.findElement(By.xpath("//div[@class= 'account-cart-wrapper']//span[text()='Account']")).click();
	 driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	 AssertJUnit.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	 
 }
 @DataProvider
 public  Object[][] userPassInfo(){
	 return new Object [][] {
		 {"auto_test_05@gmail.com","123123"},
		 {"auto_test_06@gmail.com","123123"},
		 {"auto_test_07@gmail.com","123123"},
		 
	 };
	 
 }
  
  
  
  
  @AfterTest
  public void postConditon
  () {
	  driver.quit();
  }

}
