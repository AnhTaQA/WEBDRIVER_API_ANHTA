package Selenium;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic10_User_Interaction{
	WebDriver driver;
	Actions action;
	
 @BeforeTest
 public void beforeTest() {
  driver = new FirefoxDriver();
//	 System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
//	 driver = new ChromeDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 action = new Actions(driver);

	  }
 @Test
  public void TC_01_MoveMouseToElement() {

	  driver.get("http://www.myntra.com/");
	  WebElement Login = driver.findElement(By.xpath("//div[@class = 'desktop-userIconsContainer']"));
	  action.moveToElement(Login).perform();
	  WebElement loginButton = driver.findElement(By.xpath("//a[@class = 'desktop-linkButton' and text()='log in']"));
	  action.click(loginButton).perform();
//	  loginButton.click();
	  WebElement loginForm = driver.findElement(By.xpath("//div[@class= 'login-box']"));
	  Assert.assertTrue(loginForm.isDisplayed());
	  
	  
  }
 @Test
  public void TC_02_ClickAndHoldElemennt() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List <WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

	  action.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();
	  List<WebElement> selectedItems = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
	  int numberOfselectedItem = selectedItems.size();
	  Assert.assertEquals(4, numberOfselectedItem);
  }
  @Test
  public void TC_03_DoubleClick() {  

	  driver.get("http://www.seleniumlearn.com/double-click");
	  WebElement button = driver.findElement(By.xpath("//button[@type = 'button' and text() = 'Double-Click Me!']"));
	  action.doubleClick(button).perform();
	 Alert alert = driver.switchTo().alert();
	 String alertMessage = alert.getText();
	  Assert.assertEquals("The Button was double-clicked.", alertMessage);
	  alert.accept();
  }
  @Test
  public void TC_04_RightClick() {  

	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  WebElement button = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral' and text()='right click me']"));
	  action.contextClick(button).perform();
	  WebElement quitItem = driver.findElement(By.xpath("//span[text() ='Quit']/parent::li"));
	  action.moveToElement(quitItem).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).isDisplayed());
	  
	  
  }
  @Test
  public void TC_05_DragAndDropElement() throws Exception {  
	  driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	  WebElement smallCircle = driver.findElement(By.xpath("//div[@id = 'draggable']"));
	  WebElement bigCircle = driver.findElement(By.xpath("//div[@id = 'droptarget']"));
	  action.dragAndDrop(smallCircle, bigCircle).perform();
	  Thread.sleep(2000);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
	  
  }


  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
