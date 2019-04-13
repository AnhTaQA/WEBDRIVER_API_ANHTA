package Selenium;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;



public class Topic14_WebDriverWaits{
	WebDriver driver;
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("");
	  }
//  @Test
  public void TC_01_Implicit_Wait() {
	  
//	  Step 01 - Truy cập vào trang: 
	  driver.get(" http://the-internet.herokuapp.com/dynamic_loading/2");
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	  http://the-internet.herokuapp.com/dynamic_loading/2
//	  Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
		 WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
		 
//	  Step 03 - Click the Start button
		 startButton.click();
//	  Step 04 - Wait result text will appear
		 Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
//	  Step 05 - Check result text is "Hello World!"	  
	
  }
//  @Test
  public void TC_02_Explicit_Wait_Fail() {
//	  Step 01 - Truy cập vào trang: 
//		  http://the-internet.herokuapp.com/dynamic_loading/2
	  driver.get(" http://the-internet.herokuapp.com/dynamic_loading/2");
			  WebDriverWait wait = new WebDriverWait(driver, 2);
			  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		  Step 02 - Click the Start button
				 WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
				 startButton.click();
//		  Step 03 - Wait Loading invisible
				 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@src='/img/ajax-loader.gif']")));
//		  Step 04 - Check result text is "Hello World!"
				 Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
  }  
// @Test
  public void TC_03_Explicit_Wait() {
//	  Step 01 - Truy cập vào trang: 
//		  http://the-internet.herokuapp.com/dynamic_loading/2
	  driver.get(" http://the-internet.herokuapp.com/dynamic_loading/2");
			  WebDriverWait wait = new WebDriverWait(driver, 2);
			  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		  Step 02 - Click the Start button
				 WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
				 startButton.click();
//		  Step 03 - Wait Loading invisible
				 //find element trc sau do moi wait
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
//		  Step 04 - Check result text is "Hello World!"
				 Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
  }  
//  @Test
  public void TC_04_Explicit_Wait_Fail() {
//	  Step 01 - Truy cập vào trang: 
//		  http://the-internet.herokuapp.com/dynamic_loading/2
	  driver.get(" http://the-internet.herokuapp.com/dynamic_loading/2");
			  WebDriverWait wait = new WebDriverWait(driver, 2);
			  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//		  Step 02 - Click the Start button
				 WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
				 startButton.click();
//		  Step 03 - Wait Loading invisible. Ham nay bi anh huong boi ca hai
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
				
//		  Step 04 - Check result text is "Hello World!"
				 Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
  }  
//  @Test
  public void TC_05_Implicit_Wait_ListWebELement() {
//	  Step 01 - Truy cập vào trang: 
//		  http://the-internet.herokuapp.com/dynamic_loading/2
	  driver.get(" http://the-internet.herokuapp.com/dynamic_loading/2");
			  WebDriverWait wait = new WebDriverWait(driver, 3);
			  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//		  Step 02 - Click the Start button
				 WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
				 startButton.click();
//		  Step 03 - Wait Loading invisible. FindElement tra ve list bang 0
			
				 wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//h4[text()='Hello World!']")))); 

//		  Step 04 - Check result text is "Hello World!"
				 Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
  }  
//  @Test
  public void TC_06_Implicit_Wait_WebELement() {
//  Step 01 - Truy cập vào trang: 
//	  http://the-internet.herokuapp.com/dynamic_loading/2
  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
//	  Step 02 - Check Hello World text invisible -> hết bao nhiêu s?
  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
  WebDriverWait wait = new WebDriverWait(driver, 5);
  System.out.println("Start time ="+getDateTimeSecond());
  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
  System.out.println("End time ="+getDateTimeSecond());
  
//	  Step 03 - Check Loading invisible -> hết bao nhiêu s?
  System.out.println("Start time ="+getDateTimeSecond());
  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@src='/img/ajax-loader.gif']")));
  System.out.println("End time ="+getDateTimeSecond());
  
  //	  Step 04 - Click the Start button
  System.out.println("Start time ="+getDateTimeSecond());
  WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
	 startButton.click();
	 System.out.println("End time ="+getDateTimeSecond());
	 
//	  Step 05 - Check Loading invisible -> hết bao nhiêu s?
	 System.out.println("Start time ="+getDateTimeSecond());
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@src='/img/ajax-loader.gif']")));
	  System.out.println("End time ="+getDateTimeSecond());
	  
//	  Step 06- Check Start button inivisible -> hết bao nhiêu s?
	  System.out.println("Start time ="+getDateTimeSecond());
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[text()='Start']")));
	  System.out.println("End time ="+getDateTimeSecond());

  }
  public Date getDateTimeSecond() {
      Date date = new Date();
      date = new Timestamp(date.getTime());
      return date;
  }
  
//  @Test
  public void TC_07_Explicit() {
//	  Step 01 - Truy cập vào trang:
//		  http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  WebDriverWait wait = new WebDriverWait(driver,5);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		  Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='calendarContainer']")).isDisplayed());
//	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
	 
//		  Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
	  String selectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
	  System.out.println("selected date = "+selectedDate);
//		  Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
	  driver.findElement(By.xpath("//a[text()='10']")).click();
//		  Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility)
//		  Xpath: //div[@class='raDiv']
	
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
	 String afterdate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
	  System.out.println("selected date = "+afterdate);

//		  Step 06 - Wait cho selected date = 	23 được visible ((sử dụng: visibility)
	 
//		  Xpath: //*[contains(@class,'rcSelected')]//a[text()='23']
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='10']/parent::td[@class='rcSelected']")));
//		  Step 07 - Verify ngày đã chọn bằng = Saturday, September 23, 2017
       Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(),'Wednesday, April 10, 2019')]")).isDisplayed());
  }
  
  
  @Test
  public void TC_08_FluentWait() {
//	  Step 01 - Truy cập vào trang: 
//		  https://daominhdam.github.io/fluent-wait/
	  driver.get("https://daominhdam.github.io/fluent-wait/");
//		  Step 02 - Wait cho đến khi countdown time được visible (visibility)
	  WebDriverWait wait = new WebDriverWait(driver,5);
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"))));
	  WebElement countdown= driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
			  
//		  Step 03 - Sử dụng Fluent wait để:
//		  Mỗi 1s kiểm tra countdount= 00 được xuất hiện trên page hay chưa (giây đếm ngược về 00)
//		  Tức là trong vòng 15s (tổng thời gian), cứ mỗi 1 giây verify xem nó đã đếm ngược về giây 00 hay chưa
	  new FluentWait<WebElement>(countdown)
      // Tổng time wait là 15s
      .withTimeout(15, TimeUnit.SECONDS)
       // Tần số mỗi 1s check 1 lần
       .pollingEvery(1, TimeUnit.SECONDS)
      // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
       .ignoring(NoSuchElementException.class)
       // Kiểm tra điều kiện
       .until(new Function<WebElement, Boolean>() {
           public Boolean apply(WebElement element) {
                      // Kiểm tra điều kiện countdount = 00
                      boolean flag =  element.getText().endsWith("00");
                      System.out.println("Time = " +  element.getText());
                      // return giá trị cho function apply
                      return flag;
           }
           });
  }
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
