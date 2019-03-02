package Selenium;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic01_CSSXpathExercise{
	WebDriver driver;
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  }
  @Test
  public void TC_01_LoginEmpty() throws InterruptedException {
	//<input id="email" class="input-text required-entry validate-email" type="email"
	  //title="Email Address" value="" name="login[username]" spellcheck="false" autocorrect="off" autocapitalize="off"/> 
	 System.out.println("Check log in with emplty fields");
	 driver.findElement(By.id("email")).sendKeys("Abc@gmail.com");
	 driver.findElement(By.id("email")).clear();
	 Thread.sleep(3000);
	 
	 
	 System.out.println("send key to email text box by class");
	 driver.findElement(By.className("input-text")).sendKeys("123@gmail.com");
	 driver.findElement(By.className("input-text")).clear();
	 Thread.sleep(3000);
	  
	 System.out.println("check send key to email by  name");
	 driver.findElement(By.name("login[username]")).sendKeys("456@gmail.com");
	 driver.findElement(By.name("login[username]")).clear();
	 Thread.sleep(3000);
	 
	 System.out.println("send key to email by css");
	 driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("css@gmail.com");
	 driver.findElement(By.cssSelector("input[name='login[username]']")).clear();
	 Thread.sleep(3000);
	
	 System.out.println("send key by xpath");
	 driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("Xpath@gmail.com");
	 driver.findElement(By.xpath("//input[@name='login[username]']")).clear();
	 Thread.sleep(3000);

	 
	 System.out.println("send key to email by link text");
	 driver.findElement(By.linkText("ABOUT US")).click();
	 Thread.sleep(3000);
	 
	 System.out.println("Click partial linktext");
	  driver.findElement(By.partialLinkText("SITE")).click();
	  Thread.sleep(3000);
	  
	 System.out.println("All link by tagname" + driver.findElements(By.tagName("a")).size());
	 
	
	
	 
  }
	 
	 
	 
	 		
	 

  
	  

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
