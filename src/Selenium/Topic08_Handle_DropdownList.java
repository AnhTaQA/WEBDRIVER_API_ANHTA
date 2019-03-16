package Selenium;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic08_Handle_DropdownList{
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascriptExecutor;
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 waitExplicit = new WebDriverWait(driver,30);
	 javascriptExecutor = (JavascriptExecutor) driver;
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 
	  }
  @Test
  public void TC_01_HTMLDropDownList() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  WebElement jobRole01 = driver.findElement(By.xpath("//select[@id= 'job1']"));
	
	  Select jobRoleSelect = new Select(jobRole01); 
	  Assert.assertFalse(jobRoleSelect.isMultiple());
	  
	  jobRoleSelect.selectByVisibleText("Automation Tester");
	  Assert.assertEquals("Automation Tester", jobRoleSelect.getFirstSelectedOption().getText());
	  jobRoleSelect.selectByValue("manual");
	  Assert.assertEquals("Manual Tester", jobRoleSelect.getFirstSelectedOption().getText());
	  jobRoleSelect.selectByIndex(3);
	  Assert.assertEquals("Mobile Tester", jobRoleSelect.getFirstSelectedOption().getText());
	  Assert.assertEquals(5, jobRoleSelect.getOptions().size());
	  
	    }
  @Test
  public void TC_02_JqueryCustomDropdown() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']"));
	  Thread.sleep(1000);
	  
	  selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "1");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='1']"));
	  Thread.sleep(1000);
	  
	  selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "17");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='17']"));
	  Thread.sleep(1000);
	  
	  
	  

  }
  @Test
  public void TC_03_angularCustomDropdown() throws Exception {
	  driver.get("https://material.angular.io/components/select/examples ");
	  selectItemInCustomDropdown("//mat-select[@placeholder='State']","//mat-option//span[@class='mat-option-text']","California");
	  Assert.assertTrue(isElementDisplayed("//span[@class='mat-option-text' and text()='California']"));
	  Thread.sleep(1000);
	  selectItemInCustomDropdown("//mat-select[@placeholder='State']","//mat-option//span[@class='mat-option-text']","Nevada");
	  Assert.assertTrue(isElementDisplayed("//span[@class='mat-option-text' and text()='Nevada']"));
	  Thread.sleep(1000);
	  selectItemInCustomDropdown("//mat-select[@placeholder='State']","//mat-option//span[@class='mat-option-text']","Alabama");
	  Assert.assertTrue(isElementDisplayed("//span[@class='mat-option-text' and text()='Alabama']"));
	  Thread.sleep(1000);
  }
  
  @Test
  public void TC_04_TelerikDropDownList() throws Exception {
	  driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index ");
	  selectItemInCustomDropdown("//span[@aria-labelledby='color_label']//span[contains(@class,'k-dropdown-wrap')]","//ul[@id='color_listbox']/li", 
	  		"Black");
	  Assert.assertTrue(isElementDisplayed("//span[@aria-labelledby='color_label']//span[contains(@class,'k-dropdown-wrap')]/span[@class ='k-input' and text()='Black']"));
	  Thread.sleep(1000);
	  
	  selectItemInCustomDropdown("//span[@aria-labelledby='color_label']//span[contains(@class,'k-dropdown-wrap')]","//ul[@id='color_listbox']/li", 
		  		"Orange");
	  Assert.assertTrue(isElementDisplayed("//span[@aria-labelledby='color_label']//span[contains(@class,'k-dropdown-wrap')]/span[@class ='k-input' and text()='Orange']"));
	  Thread.sleep(1000);

	  selectItemInCustomDropdown("//span[@aria-labelledby='color_label']//span[contains(@class,'k-dropdown-wrap')]","//ul[@id='color_listbox']/li", 
		  		"Grey");
	  Assert.assertTrue(isElementDisplayed("//span[@aria-labelledby='color_label']//span[contains(@class,'k-dropdown-wrap')]/span[@class ='k-input' and text()='Grey']"));
  }
  
  @Test
  public void TC_05_MekeroDropDownList() throws Exception {
	  driver.get("https://mikerodham.github.io/vue-dropdowns/");
	
	  selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
	  Assert.assertTrue(isElementDisplayed("//li[@class='dropdown-toggle' and contains(text(),'First Option')]"));
	  Thread.sleep(1000);
	  selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
	  Assert.assertTrue(isElementDisplayed("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]"));
	  Thread.sleep(1000);
	  selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Third Option");
	  Assert.assertTrue(isElementDisplayed("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]"));
	  Thread.sleep(1000);
  }
 @Test
	  public void TC_06_indrimuskaDropDownList() throws Exception {
	  driver.get("http://indrimuska.github.io/jquery-editable-select/");
	  driver.findElement(By.xpath("//div[@id='default-place']//input[contains(@class,'es-input')]")).sendKeys("Audi");
	  selectItemInCustomDropdown("//div[@id='default-place']//input[contains(@class,'es-input')]","//div[@id='default-place']//ul[@class='es-list']/li","Audi");
	  Assert.assertTrue(isPresent(By.xpath("//div[@id='default-place']//li[@class='es-visible' and contains(text(),'Audi')]")));
	  Thread.sleep(1000);
	  }
//	 @Test
//	  public void TC_07_wenzhixinDropDownList() throws Exception {
//	  driver.get("http://wenzhixin.net.cn/p/multiple-select/docs/ ");
//	  
//	  
//	  }
//	  
  
  public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) throws InterruptedException  {
	
	  WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
//	  parentDropdown.click();
       javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	 
	 
	  List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));

	 
	  for (int i = 0; i< allItems.size(); i++) {
		  if (allItems.get(i).getText().equals(expectedValueItem)){
			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", allItems.get(i));
			  Thread.sleep(1500);
			  //javascriptExecutor.executeScript("arguments[0].click();", allItems.get(i));
			 allItems.get(i).click();
			  break;
		  }
	  }
//	  for(WebElement childElement: allItems) {
//		  if(childElement.getText().equals(expectedValueItem)) {
//			  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
//			  Thread.sleep(1500);
//			  javascriptExecutor.executeScript("arguments[0].click();", childElement);
////			  childElement.click();
//			  break;
//		  }
//	  }
  }

  
  
 public boolean isElementDisplayed(String xpathValue) {
	 WebElement element  = driver.findElement(By.xpath(xpathValue));
	 if (element.isDisplayed()){
		 return true;	 
	 }
	 else {
		 return false;
	 }
 }
 public boolean isPresent(By value) {
	    
		if(driver.findElements(value).size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
