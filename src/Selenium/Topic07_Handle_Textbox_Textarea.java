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





public class Topic07_Handle_Textbox_Textarea{
	WebDriver driver;
	//declare variables
	String customerName, gender,dateOfBirth,address,city,state,pin,mobileNumber,email,password,customerID; 
	String editAddress,editCity,editState,EditPin,editMobileNumber,editEmail;
	//locate element on add form
	By customerNameTextbox = By.xpath(" //input[@name='name']");
	By genderRadioMale = By.xpath(" //input[@value='m']");
	By dateOfBirthTextbox = By.xpath("//input[@id='dob']");
	By addressTextbox = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("  //input[@name='city']");
	By stateTextbox = By.xpath(" //input[@name='state']");
	By pinTextbox = By.xpath(" //input[@name='pinno']");
	By mobileNumberTextbox = By.xpath(" //input[@name='telephoneno']");
	By emailTextbox= By.xpath(" //input[@name='emailid']");
	By passwordTextbox = By.xpath(" //input[@name='password']");
	//locate element on Edit form 
	By genderfield = By.xpath("//input[@name='gender']");
	By namefield = By.xpath(" //input[@name='name']");
	By dateofBirthfield = By.xpath(" //input[@name='dob']");
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("http://demo.guru99.com/v4");
	 driver.findElement(By.xpath("//input[@name = 'uid']")).sendKeys("mngr181358");
	 driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("berydUp");
	 driver.findElement(By.xpath("//input[@value = 'LOGIN']")).click();
	 Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
     Assert.assertTrue(driver.findElement(By.xpath(" //td[contains(text(),'Manger Id : mngr181358')]")).isDisplayed());
	 // Add value
	 customerName = "Ta Thi Anh";
	 gender = "male";
	 dateOfBirth = "1993-01-01";
	 address = "123 Trung Hoa Cau Giay";
	 city = "Ha Noi";
	 state = "Vietnam";
	 pin = "555555";
	 mobileNumber = "0987654332";
	 email = "abc" + radomNumber()+"@gmail.com";
	 password = "123456";
//	 edit data
	 
	 editAddress = "123, Quang Trung, Ha Dong";
	 editCity = "Ha Noi 1";
	 editState = "Vietnam1";
	 EditPin = "666666";
	 editMobileNumber= "123456789";
	 editEmail = "abc123" + radomNumber()+"@gmail.com";
	 
	  }
  @Test
  public void TC_01_Create_New_Customer() throws Exception {
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Add New Customer']")).isDisplayed());
	  driver.findElement(customerNameTextbox).sendKeys(customerName);
	  driver.findElement(genderRadioMale).click();
	  driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
	  driver.findElement(addressTextbox).sendKeys(address);
	  driver.findElement(cityTextbox).sendKeys(city);
	  driver.findElement(stateTextbox).sendKeys(state);
	  driver.findElement(pinTextbox).sendKeys(pin);
	  driver.findElement(mobileNumberTextbox).sendKeys(mobileNumber);
	  driver.findElement(emailTextbox).sendKeys(email);
	  driver.findElement(passwordTextbox).sendKeys(password);
	  driver.findElement(By.xpath(" //input[@value='Submit']")).click();
	  Thread.sleep(2000);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3'and text()= 'Customer Registered Successfully!!!']")).isDisplayed());
	  //verify data
  
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirth);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobileNumber);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);
	  Thread.sleep(3000);
	  //get customer ID
	  customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	  System.out.println("Customer ID " + customerID);
  }
  @Test
  public void TC_02_Edit_Customer() throws Exception {
	  driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Edit Customer Form']")).isDisplayed());
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[@name = 'cusid']")).sendKeys(customerID);
	  driver.findElement(By.xpath(" //input[@value='Submit']")).click();
	  //check disabled fields
	  Assert.assertFalse(isElementEnabled(genderfield));
	  Assert.assertFalse(isElementEnabled(namefield));
	  Assert.assertFalse(isElementEnabled(dateofBirthfield));
	  //check customerName, address
	  Thread.sleep(2000);
	  //Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
//	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);
//	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
//	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirth);
	  //edit field
	  driver.findElement(addressTextbox).clear();
	  driver.findElement(addressTextbox).sendKeys(editAddress);
	  driver.findElement(cityTextbox).clear();
	  driver.findElement(cityTextbox).sendKeys(editCity);
	  
	  driver.findElement(stateTextbox).clear();
	  driver.findElement(stateTextbox).sendKeys(editState);
	 
	  driver.findElement(pinTextbox).clear();
	  driver.findElement(pinTextbox).sendKeys(EditPin);
	  
	  driver.findElement(mobileNumberTextbox).clear();
	  driver.findElement(mobileNumberTextbox).sendKeys(editMobileNumber);
	  driver.findElement(emailTextbox).clear();
	  driver.findElement(emailTextbox).sendKeys(editEmail);
	  driver.findElement(By.xpath(" //input[@value='Submit']")).click();
	  //Check edited data
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirth);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editAddress);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),editCity);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editState);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),EditPin);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editMobileNumber);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editEmail);
  }
  
	public int radomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(99999);
		System.out.println("random number" + randomNumber);
		return randomNumber;
	} 
	public boolean isElementEnabled(By byValue) {
		if (driver.findElement(byValue).isEnabled()) {
			System.out.println("Element [" + byValue +"] is enabled" );	
			return true;
		}
		else {
			System.out.println("Element [" + byValue +"] is not enabled" );	
			return false;
		}
	}

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
