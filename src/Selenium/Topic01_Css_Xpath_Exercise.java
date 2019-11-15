package Selenium;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic01_Css_Xpath_Exercise{
	WebDriver driver;
	String Random_email = "Test"+randomNumber()+"@gmail.com";
	
	
 @BeforeTest
 public void beforeTest() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\anhtt\\Desktop\\Software\\geckodriver-v0.26.0-win64\\geckodriver.exe");
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get(" http://live.guru99.com/");
	  Thread.sleep(2000);
	  }
  @Test
  public void TC_01_Check_Login_Empty() throws Exception  {
	  System.out.println("Test Script 01: Login empty");
	  //Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
	  //Step 03 - Để trống Username/ Password
	  driver.findElement(By.id("email")).sendKeys("");
	  driver.findElement(By.id("pass")).sendKeys("");
	  // Step 04 - Click Login button
	  driver.findElement(By.id("send2")).click();
	  //Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.
	  String Email_message = driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(Email_message, "This is a required field.");
	  String Pass_message = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(Pass_message, "This is a required field.");
	  driver.findElement(By.id("email")).clear();
	  driver.findElement(By.id("pass")).clear();
	  Thread.sleep(3000); 
	 
	  
		  
	   
	 
  }
  @Test
  public void TC_02_Login_Invalid_Email() throws Exception{
	  System.out.println("Test Script 02: Login with Email invalid");
	//Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
//		  Step 03 - Nhập email invalid: 123434234@12312.123123
	  driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.id("pass")).sendKeys("");
	  // Step 04 - Click Login button
	  driver.findElement(By.id("send2")).click();
//		  Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
	  Thread.sleep(1000); 
	  String Email_message = driver.findElement(By.id("advice-validate-email-email")).getText();
	  Assert.assertEquals(Email_message, "Please enter a valid email address. For example johndoe@domain.com.");
	  
	 
  }
  @Test
  public void TC_03_Password_less_than_6() throws Exception {
	 
	  System.out.println("Test Script 03: Login with Password < 6 characters");
	//Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
	//	  Step 3 - Nhập email correct and password incorrect: automation@gmail.com/ 123
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  // Step 04 - Click Login button
	  driver.findElement(By.id("send2")).click();
	  Thread.sleep(1000); 
	//	 Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
	  String Pass_message = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(Pass_message, "Please enter 6 or more characters without leading or trailing spaces.");
	  
	
  }
	  
  
	  
  @Test
  public void TC_04_Login_Incorrect_Password() throws Exception  {
	  System.out.println("Test Script 04: Login with Password incorrect"); 
	//Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Thread.sleep(2000);
	  //Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123123123
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123123123");
	  // Step 04 - Click Login button
	  driver.findElement(By.id("send2")).click();
	  //Step 05 - Verify error message xuất hiện: Invalid login or password.
	  String Error_message = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
	  Assert.assertEquals(Error_message, "Invalid login or password.");
	  Thread.sleep(3000);
  }
  @Test
  public void TC_05_Sign_up() throws Exception {
	System.out.println("Create an account");
    driver.findElement(By.xpath("//a[@title='My Account']/ancestor::div[@class='footer']")).click();
    
    driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
   
    driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Anh");
    driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Ta");
	driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(Random_email);
    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Quynhanh2016");
    driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("Quynhanh2016");
    driver.findElement(By.xpath("//button[@title='Register']")).click();
    String Verify_message = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
    Assert.assertEquals(Verify_message, "Thank you for registering with Main Website Store.");
    driver.findElement(By.xpath("//span[(text()='Account')]/preceding-sibling::span")).click();
    driver.findElement(By.xpath("//a[@title='Log Out']")).click();
   Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, 'logo.png')]")).isDisplayed());

   
    
  }
  private int randomNumber() {
	  Random random = new Random();
	  int randomNumber = random.nextInt(99999);
	  System.out.println("random number" + randomNumber);
	return randomNumber;
}
@AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
