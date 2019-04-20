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
	 waitExplicit = new WebDriverWait(driver,60);
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
//  @Test
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
  
// @Test
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
	  Thread.sleep(1000);
	  selectItemInCustomDropdown("//span[@aria-labelledby='color_label']//span[contains(@class,'k-dropdown-wrap')]","//ul[@id='color_listbox']/li", 
	  		"Black");
	  Assert.assertTrue(isElementDisplayed("//span[@aria-labelledby='color_label']//span[contains(@class,'k-dropdown-wrap')]/span[@class ='k-input' and text()='Black']"));
	  Thread.sleep(1000);
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
@Test
	  public void TC_07_wenzhixinDropDownList() throws Exception {
	  driver.get("http://multiple-select.wenzhixin.net.cn/examples/#basic.html ");
	  By contentIframeXpath = By.xpath("//div[@class='content']//iframe");
	  String [] items = {"January","March", "April"};
	  String[] newitems = {"January","March", "April","September","December"};
	  
	  WebElement contentIframe = driver.findElement(contentIframeXpath);
	  driver.switchTo().frame(contentIframe);
	  selectMutipleItems("//button[@class = 'ms-choice']","//div[@class='ms-drop bottom']//span",items);
	  Assert.assertTrue(isSelectedItem(items));
	  
	  driver.navigate().refresh();
	  WebElement contentIframeRefresh = driver.findElement(contentIframeXpath);
	  driver.switchTo().frame(contentIframeRefresh);
	  selectMutipleItems("//button[@class = 'ms-choice']","//div[@class='ms-drop bottom']//span",newitems);
	  Assert.assertTrue(isSelectedItem(newitems));
	  
	  
	  }
 @Test
	 public void TC_08_semantic_dropdownList() throws Exception {
		 driver.get("https://semantic-ui.com/modules/dropdown.html ");
		//Skill Multiple Selection
		 String [] items = {"Kitchen Repair","Ruby"};
		 selectMultipleSkills("//div[text()='Skills']/preceding::h4[text()='Multiple Selection']/following-sibling::div","//div[@class='menu transition visible']/div", items);
		 Assert.assertTrue(isSkillSelected(items));
		 //State Multiple Selecttion
		 
		
	 }
 @Test
	public void TC_09_SelectMultipleCountry() throws Exception {
		 driver.get("https://semantic-ui.com/modules/dropdown.html "); 
		 String [] countries = {"Aland Islands","Algeria","Andorra"};
		 selectMultipleCountry("//div[text()='Select Country']/parent::div[@class='ui fluid multiple search selection dropdown']","//div[@class='menu transition visible']/div[@class='item']",countries);
		
		 Assert.assertTrue(isCountrySelected(countries));
	 }
	  
  

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
//			  javascriptExecutor.executeScript("arguments[0].click();", allItems.get(i));
			 if( allItems.get(i).isDisplayed()) {
					 allItems.get(i).click();
			 } else {
				 javascriptExecutor.executeScript("arguments[0].click();", allItems.get(i));
			 }
			 
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
  public void selectMutipleItems (String parentXpath, String allItemsXpath, String[] expectedValues) throws Exception {
	  WebElement  parentDropdown = driver.findElement(By.xpath(parentXpath));
	  javascriptExecutor.executeScript("arguments[0].click();",parentDropdown );
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
	  List <WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
	  for (WebElement childELement: allItems) {
		  for( String item: expectedValues) {
			   if (childELement.getText().equals(item)) {
				   javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",childELement );
				   Thread.sleep(1000);
				   if(childELement.isDisplayed()) {
					   childELement.click();
				   }
					   else {
						   javascriptExecutor.executeScript("arguments[0].click();",childELement );
					   }
				   Thread.sleep(1000);
				   List  <WebElement> selectedItems = driver.findElements(By.xpath("//li[@class='selected']//span"));
				   
				   if(expectedValues.length== selectedItems.size()) {
					   break;
					   
					  }
				   
				   }
				   
				   
			   }
		  }
	  }
	  
	  
		  

  public void selectMultipleSkills(String parentXpath, String allItemsXpath, String[] expectedValues) throws Exception {
	  WebElement  parentDropdown = driver.findElement(By.xpath(parentXpath));
	  javascriptExecutor.executeScript("arguments[0].click();",parentDropdown );
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
	  List <WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
	  for (WebElement childELement: allItems) {
		  for( String item: expectedValues) {
			   if (childELement.getText().equals(item)) {
				   javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",childELement );
				   Thread.sleep(1000);
				   if(childELement.isDisplayed()) {
					   childELement.click();
				   }
					   else {
						   javascriptExecutor.executeScript("arguments[0].click();",childELement );
					   }
				   Thread.sleep(1000);
				   List  <WebElement> selectedItemsonBox = driver.findElements(By.xpath("//div[text()='Skills']/preceding::h4[text()='Multiple Selection']/following-sibling::div//a"));
//				   List <WebElement> selectedCountryOnBox = driver.findElements(By.xpath("//div[@class='ui fluid multiple search selection dropdown upward active visible']/a/i[contains(@class,'flag')]"));
				   if(expectedValues.length== selectedItemsonBox.size()) {
					   break;
					   
					  }
//				   else if(expectedValues.length== selectedCountryOnBox.size()){
//					   break;
//					   
//				   }
				   }
				   
				   
			   }
		  }
	  }
  public void selectMultipleCountry(String parentXpath, String allItemsXpath, String[] expectedValues) throws Exception {
	  WebElement  parentDropdown = driver.findElement(By.xpath(parentXpath));
	  javascriptExecutor.executeScript("arguments[0].click();",parentDropdown );
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
	  List <WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
	  for (WebElement childELement: allItems) {
		  for( String item: expectedValues) {
			   if (childELement.getText().equals(item)) {
				   javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",childELement );
				   Thread.sleep(1000);
				   if(childELement.isDisplayed()) {
					   childELement.click();
				   }
					   else {
						   javascriptExecutor.executeScript("arguments[0].click();",childELement );
					   }
				   Thread.sleep(1000);

				   List <WebElement> selectedCountryOnBox = driver.findElements(By.xpath("//div[@class = 'ui fluid multiple search selection dropdown active visible']//a[@class='ui label']"));
				  
				   if(expectedValues.length== selectedCountryOnBox.size()){
					   break;
					   
				   }
				   }
				   
				   
			   }
		  }
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
 public boolean isSelectedItem(String[] itemSelectedText) {
	 List<WebElement> selectedItems = driver.findElements(By.xpath("//li[@class='selected']//span"));
	 int numberOfElements = selectedItems.size();
	 String allSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']//span")).getText();
	 
	 if(numberOfElements<=3 && numberOfElements>0) {
		 for (String item : itemSelectedText) {
			 if (allSelectedText.contains(item)) {
				 break;
			 }
		 }
		 return true;
	 }
	 else {
		 return driver.findElement(By.xpath("//button[@class='ms-choice']//span[text()='" + numberOfElements+" of 12 selected']")).isDisplayed();
	 }
 
 }
 public boolean isSkillSelected(String[] selectedSkills) {
	 List<WebElement> selectedItems = driver.findElements(By.xpath("//div[@class='menu transition visible']/div[@class='item active filtered']"));
	 int numberOfElements = selectedItems.size();
	 String allSelectedText = driver.findElement(By.xpath("//div[text()='Skills']/preceding::h4[text()='Multiple Selection']/following-sibling::div//a")).getText(); 
	 if (numberOfElements>0) {
	 for (String item : selectedSkills) {
	  if(allSelectedText.contains(item)) {
		  break;
	  }
	 }
	 return true;
 }
	 else {
		 return false;
	 }
 }
 public boolean isCountrySelected(String[] selectedcountries) {
	 List<WebElement> selectedItems = driver.findElements(By.xpath("//div[@class='menu transition visible']/div[@class='item active filtered']"));
	 int numberOfElements = selectedItems.size();
//	 String allSelectedText = driver.findElement(By.xpath("//div[text()='Select Country']/parent::div[@class='ui fluid multiple search selection dropdown']//a[@class='ui label transition visible']")).getText(); 
	 String allSelectedText = driver.findElement(By.xpath("//a[@class='ui label transition visible']")).getText();
	 if (numberOfElements>0) {
	 for (String item : selectedcountries) {
	  if(allSelectedText.contains(item)) {
		  break;
	  }
	 }
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
