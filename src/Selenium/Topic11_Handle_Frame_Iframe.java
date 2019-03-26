package Selenium;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic11_Handle_Frame_Iframe{
	WebDriver driver;
	JavascriptExecutor javaExecutor;
	
 @BeforeTest
 public void beforeTest() {
	 driver = new FirefoxDriver();
	 javaExecutor = (JavascriptExecutor)driver;
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 
	  }
  @Test
  public void TC_01_CheckURL() {
	  driver.get("https://www.hdfcbank.com/");
//	  Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
//		  Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có)  - F5 (refresh page) nhiều lần thì sẽ xuất hiện popup
//	  Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
	  
	  List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id ='vizury-notification-template']"));
	  
	  if (notificationIframe.size()>0){
		  System.out.println("Notification  Iframe =" + notificationIframe.size());
		  driver.switchTo().frame(notificationIframe.get(0));
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
		  WebElement closeButton = driver.findElement(By.xpath("//div[@id='div-close']"));
		  javaExecutor.executeScript("arguments[0].click();", closeButton);
		  driver.switchTo().defaultContent();  
	  }
	  WebElement flipBaner = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe[contains(@id,'viz_iframe')]"));
	  driver.switchTo().frame(flipBaner);
	  WebElement flipBanerText = driver.findElement(By.xpath("//div[@id='bannercontainer']//span[@id = 'messageText' and text()='What are you looking for?']"));
	 Assert.assertTrue(flipBanerText.isDisplayed());
	 driver.switchTo().defaultContent(); 
//	 Step 04:
//		 Verify banner có đúng 6 images (switch qua iframe nếu có)
	 WebElement slidingBanner = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe[contains(@id,'viz_iframe')]"));
	 driver.switchTo().frame(slidingBanner);
	 List <WebElement> bannerImages = driver.findElements(By.xpath("//div[@id='productcontainer']//img"));
	 int numberOfImages= bannerImages.size();
	 Assert.assertEquals(numberOfImages, 6);
	 driver.switchTo().defaultContent();
//	 Step 05 - Verify flipper banner được hiển thị và có 8 items
	 List <WebElement> flipBannerProduct = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	 Assert.assertEquals(flipBannerProduct.size(), 8);
	 
	 for(WebElement productImage:flipBannerProduct) {
		Assert.assertTrue(IsImageloaded(productImage)); 
		Assert.assertTrue(productImage.isDisplayed());
	 }
  }
public boolean IsImageloaded(WebElement imageElement) {
	return (boolean) javaExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageElement);
	
}
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
