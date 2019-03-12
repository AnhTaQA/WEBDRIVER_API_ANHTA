package Selenium;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic06_WebBrowser_Command_Exercise{
	WebDriver driver;
	
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By educationtextbox = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interestDevelopment = By.xpath("//input[@id = 'development']");
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
	driver.get("https://daominhdam.github.io/basic-form/index.html");
	Assert.assertTrue(isElementEnabled(emailTextbox));
	Assert.assertTrue(isElementEnabled(ageUnder18Radio));
	Assert.assertTrue(isElementEnabled(educationtextbox));
	Assert.assertTrue(isElementEnabled(jobRole01));
	Assert.assertTrue(isElementEnabled(interestDevelopment));
	Assert.assertTrue(isElementEnabled(slider01));
	Assert.assertTrue(isElementEnabled(buttonEnabled));
	Assert.assertFalse(isElementEnabled(password));
	Assert.assertFalse(isElementEnabled(ageRadioDisabled));
	Assert.assertFalse(isElementEnabled(biography));
	Assert.assertFalse(isElementEnabled(jobRole02));
	Assert.assertFalse(isElementEnabled(interestDisabled));
	Assert.assertFalse(isElementEnabled(slider02));
	Assert.assertFalse(isElementEnabled(buttonDisabled));
	
  }
@Test
public void TC_03_Check_Element_Selected() throws Exception {
	driver.get("https://daominhdam.github.io/basic-form/index.html");
	checkToCheckbox(interestDevelopment);
	checkToCheckbox(ageUnder18Radio);
	Assert.assertTrue(isElementSelected(ageUnder18Radio));
	Assert.assertTrue(isElementSelected(interestDevelopment));
	uncheckToCheckbox(interestDevelopment);
	Assert.assertFalse(isElementSelected(interestDevelopment));
	
}
	public boolean isElementDisplayed(By byValue){
		if (driver.findElement(byValue).isDisplayed()) {
			System.out.println("Element [" + byValue +"] is dislayed" );
			return true;
		}
		else {
			System.out.println("Element [" + byValue +"] is not dislayed" );
			return false; 
		}
		
	}
	public boolean isElementEnabled(By byValue) {
		if (driver.findElement(byValue).isEnabled()){
			System.out.println("Element [" + byValue +"] is enabled" );	
			return true;
		}
		else {
			System.out.println("Element [" + byValue +"] is not enabled" );	
			return false;
		}
	}
	public void checkToCheckbox(By byValue) {
		WebElement element = driver.findElement(byValue);
		if (!element.isSelected()) {
			element.click();
			
		}
	}
	public void uncheckToCheckbox(By byValue) {
	 WebElement element = driver.findElement(byValue);
	 if (element.isSelected()) {
			element.click();
			
		}
	}
	public boolean isElementSelected(By byValue) {
		if (driver.findElement(byValue).isSelected()){
			System.out.print("Element [" + byValue +"] is selected");
			return true;
		}
		else {
			System.out.print("Element [" + byValue +"] is selected");
			return false;
		}
	}
	
@AfterTest
 public void afterTest() {
	  driver.quit();
  }

}
