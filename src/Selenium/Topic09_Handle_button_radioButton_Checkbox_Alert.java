package Selenium;




import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic09_Handle_button_radioButton_Checkbox_Alert{
	WebDriver driver;
	JavascriptExecutor javascriptExecutor;
	
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 javascriptExecutor = (JavascriptExecutor) driver;
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	
	  }
// @Test
  public void TC_01_CheckURL() {
	  driver.get("http://live.guru99.com/");
	  WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	  
	  javascriptExecutor.executeScript("arguments[0].click();", myAccountLink);
	  String URL1 = driver.getCurrentUrl();
	  Assert.assertEquals(URL1, "http://live.guru99.com/index.php/customer/account/login/");
	  WebElement createAnAccount = driver.findElement(By.xpath("//span[text()='Create an Account']"));
	  javascriptExecutor.executeScript("arguments[0].click();", createAnAccount);
	  String URL2 = driver.getCurrentUrl();
	  Assert.assertEquals(URL2, "http://live.guru99.com/index.php/customer/account/create/");
	  
	  
  }
 // @Test
  public void TC_02_ChecktoCheckbox() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  
	 By DuoZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
	 checktoCheckBox(DuoZoneCheckbox);
	Assert.assertTrue(isElementSelected(DuoZoneCheckbox));
	uncheckToCheckbox(DuoZoneCheckbox);
	Assert.assertFalse(isElementSelected(DuoZoneCheckbox));
	  
	  
  }
	//@Test
	public void TC_03_CheckRadioButton(){
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement petrol147kW = driver.findElement(By.xpath("//label[text()= '2.0 Petrol, 147kW']/preceding-sibling::input"));
		javascriptExecutor.executeScript("arguments[0].click();",petrol147kW);
		if (petrol147kW.isSelected()) {
			System.out.println(" 2.0 Petrol, 147kW is selected");
		}
		else {
			javascriptExecutor.executeScript("arguments[0].click();",petrol147kW);
		}
	}
	  
	//@Test
	public void TC_04_CheckJsAlert(){
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement jsAlert = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
		jsAlert.click();
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Alert");
		alert.accept();
		String message = driver.findElement(By.xpath("//h4[text()='Result:']/following-sibling::p")).getText();
		Assert.assertEquals(message, "You clicked an alert successfully");
		
		
	}
	//@Test
	public void TC_05_CheckJsConfirm(){
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement jsAlert = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
		jsAlert.click();
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Confirm");
		alert.dismiss();
		String message = driver.findElement(By.xpath("//h4[text()='Result:']/following-sibling::p")).getText();
		Assert.assertEquals(message, "You clicked: Cancel");
		
	}
	//@Test
	public void TC_06_CheckJsPrompt(){
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		String textInput = "I am a tester";
		WebElement jsAlert = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
		jsAlert.click();
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS prompt");
		alert.sendKeys(textInput);
		alert.accept();
		String message = driver.findElement(By.xpath("//h4[text()='Result:']/following-sibling::p")).getText();
		Assert.assertEquals(message, "You entered: " + textInput);
			
	}
	@Test
	public void TC_07_CheckAuthentication() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		WebElement sucessfulMessage = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]"));
		
		Assert.assertTrue(sucessfulMessage.isDisplayed());
		
	}
 public void checktoCheckBox(By byValue) {
	WebElement element = driver.findElement( byValue);
	if (!element.isSelected()) {
	
		javascriptExecutor.executeScript("arguments[0].click();",element);
	}
	 
 }
 public void uncheckToCheckbox(By byValue) {
	 WebElement element =driver.findElement(byValue);
	 if(element.isSelected()) {
			javascriptExecutor.executeScript("arguments[0].click();",element);
		}
 }
 
 public boolean isElementSelected(By byValue) {
	 WebElement element =driver.findElement(byValue);
	 if(element.isSelected()) {
		 return true;
	 }
	 else {
		 return false;
	 }
 }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
