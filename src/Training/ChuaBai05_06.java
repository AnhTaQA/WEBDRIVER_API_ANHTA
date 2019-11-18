package Training;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class ChuaBai05_06{
	WebDriver driver;
	String Random_email = "Test"+randomNumber()+"@gmail.com";
	
	
 @BeforeTest
 public void beforeTest() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\anhtt\\Desktop\\Software\\geckodriver-v0.26.0-win64\\geckodriver.exe");
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("http://live.guru99.com/");
	  Thread.sleep(2000);
	  }
//  @Test
  public void TC_05_LogInWithCorrectAccount() throws Exception  {
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  
	  //input du lieu vao o textbox
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nguyenhong.hus.201@gmail.com");
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Hong123@@");
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  String text = driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'nguyen hong')]")).getText();
	 Assert.assertTrue(text.contains("nguyen hong"));
	
	  
	  
  }
	  
  @Test
  public void TC_05_Sign_up() throws Exception {
//Click My account
    driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
 //Click create My account   
    driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
   //SendKey first name
    driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Anh");
    //Send last name
    driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Ta");
    //send email
	driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(Random_email);
	//send password
    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Quynhanh2016");
    
    //send confirm password
    driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("Quynhanh2016");
    
    //Click register
    driver.findElement(By.xpath("//button[@title='Register']")).click();
    
    //getText
    String Verify_message = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
    
    //so sánh
    Assert.assertEquals(Verify_message, "Thank you for registering with Main Website Store.");
    
    //Click account
    driver.findElement(By.xpath("//span[(text()='Account')]/preceding-sibling::span")).click();
    
    //Click logout
    driver.findElement(By.xpath("//a[@title='Log Out']")).click();
    
    //Kiem tra logo hien thi tren man hinh
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
//	  driver.quit();
  }

}
