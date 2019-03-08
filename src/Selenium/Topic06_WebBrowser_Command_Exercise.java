package Selenium;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic06_WebBrowser_Command_Exercise{
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath("//label[@for='under_18']");
	By educationtextbox = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interestDevelopment = By.xpath("//label[@for = 'development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonEnabled = By.xpath("//button[@id='button-enabled']");
	By password = By.xpath("//input[@id='password']");
	By ageRadioDisabled = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestDisabled = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisabled = By.xpath("//button[@id='button-disabled']");
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("https://daominhdam.github.io/basic-form/index.html");
	  }
  @Test
  public void TC_01_Check_Email_Age_Education() {
	  System.out.println("Kiểm tra các phần tử sau hiển thị trên trang: Email/ Age (Under 18)/ Education ");
	  if (isElementDisplayed(emailTextbox)) {
		  driver.findElement(emailTextbox).sendKeys("Automation testing");
		  
	  }
	  
	  if (isElementDisplayed(ageUnder18Radio)) {
		  driver.findElement(ageUnder18Radio).click();
		  
	  }
	  
	  if (isElementDisplayed(educationtextbox)) {
		  driver.findElement(educationtextbox).sendKeys("Automation testing");
		  
	  }
	 
  }
 
	

@Test
  public void TC_02_Check_Element_Enabled() {
	System.out.println("Test Script 02: Kiểm tra phần tử enable/ disable trên trang");
	if (isElementEnable(jobRole01)) {
		System.out.println("Job role 1 is enabled");
	}
	else {
		System.out.println("Job role 1 is disabled");
		
	}
	
	
	
	if (isElementEnable(interestDevelopment)) {
		System.out.println(" Interest Development  1 is enabled");
	}
	else {
		System.out.println("Interest Development 1 is disabled");
		
		
		
	}
	if (isElementEnable(slider01)) {
		System.out.println(" Slider  01 is enabled");
	}
	else {
		System.out.println("Slider 01 is disabled");
		
		
		
	}
	if (isElementEnable(buttonEnabled)) {
		System.out.println(" Button   is enabled");
	}
	else {
		System.out.println("Button  is disabled");
		
	}
	
	
	if (isElementEnable(password)) {
		System.out.println(" password   is enabled");
	}
	else {
		System.out.println("password  is disabled");
		
	}
	
	
	if (isElementEnable(ageRadioDisabled)) {
		System.out.println(" Age radio button  is enabled");
	}
	else {
		System.out.println("Age radio button is disabled");
		
	}
	
	
	if (isElementEnable(biography)) {
		System.out.println(" biography is enabled");
	}
	else {
		System.out.println("biography is disabled");
		
	}
	
	
	if (isElementEnable(jobRole02)) {
		System.out.println("Job role 02 is enabled");
	}
	else {
		System.out.println("Job role 02 is disabled");
		
	}
	
	if (isElementEnable(interestDisabled)) {
		System.out.println(" Interest  is enabled");
	}
	else {
		System.out.println("Interest  is disabled");
		
	}
	
	if (isElementEnable(slider02)) {
		System.out.println(" slider 02 is enabled");
	}
	else {
		System.out.println("slider 02 is disabled");
		
	}
	
	if (isElementEnable(buttonDisabled)) {
		System.out.println(" button   1 is enabled");
		}
	else {
		System.out.println("button is disabled");
		
	}
	
  }
public boolean isElementEnable(By byValue) {
	return driver.findElement(byValue).isEnabled();
}
  
	public boolean isElementDisplayed(By byValue){
		return driver.findElement(byValue).isDisplayed();
	}
	

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
