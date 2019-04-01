package Selenium;


import java.util.List;
import java.util.Set;
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


//	  Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có)  - F5 (refresh page) nhiều lần thì sẽ xuất hiện popup
	  
//	  List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id ='vizury-notification-template']"));
//	  
//	  if (notificationIframe.size()>0){
//		  System.out.println("Notification  Iframe =" + notificationIframe.size());
//		  driver.switchTo().frame(notificationIframe.get(0));
//		  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
//		  WebElement closeButton = driver.findElement(By.xpath("//img[@class='popupCloseButton']"));
//		  javaExecutor.executeScript("arguments[0].click();", closeButton);
//		  driver.switchTo().defaultContent();  
//	  }
	  List <WebElement> popupBanner = driver.findElements(By.xpath("//img[@class='popupbanner']"));
	  
	 if(popupBanner.size()>0) {
		 System.out.println("pop-up Banner "+popupBanner.size());
		 
		 WebElement closeButton = driver.findElement(By.xpath("//img[@class='popupCloseButton']"));
		  javaExecutor.executeScript("arguments[0].click();", closeButton);
	 }
	 else {
		 System.out.println("popupBanner = 0");
	 }
	 
	  WebElement flipBaner = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe[contains(@id,'viz_iframe')]"));
	  driver.switchTo().frame(flipBaner);
//	  Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
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
@Test
public void TC_02_CheckCloseWindow() {
//	Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
//		Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
//		Step 03 - Kiểm tra title của window mới = Google
//		Step 04 - Close window mới
//		Step 05 - Switch về parent window
//		Step 06 - Kiểm tra đã quay về parent window thành công (title/ url)
	driver.get("https://daominhdam.github.io/basic-form/index.html");
	String parentID = driver.getWindowHandle();
	String parentTitle = driver.getTitle();
	WebElement clickHereLink = driver.findElement(By.xpath("//a[text()='Click Here']"));
	clickHereLink.click();
	switchToChildWindow(parentID);
	String tiltle = driver.getTitle();
	Assert.assertEquals(tiltle, "Google");
	driver.close();
	driver.switchTo().window(parentID);
	Assert.assertEquals(parentTitle, "SELENIUM WEBDRIVER FORM DEMO");
	
}
public void switchToChildWindow(String parentID) {
	Set<String> allWindowID = driver.getWindowHandles();
	for(String childID:allWindowID) {
		if(!childID.equals(parentID)) {
			driver.switchTo().window(childID);
			break;
		}
	}
}
@Test
public void TC_03_CheckMutiWindow() throws Exception {
//	Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
//		Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện

	driver.get("http://www.hdfcbank.com/");
	Thread.sleep(2000);
	
	  
	List <WebElement> popupBanner = driver.findElements(By.xpath("//img[@class='popupbanner']"));
	  
	 if(popupBanner.size()>0) {
		 System.out.println("pop-up Banner "+popupBanner.size());
		 
		 WebElement closeButton = driver.findElement(By.xpath("//img[@class='popupCloseButton']"));
		  javaExecutor.executeScript("arguments[0].click();", closeButton);
	 }
	 else {
		 System.out.println("popupBanner = 0");
	 }
	 
	
	  WebElement flipBaner = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe[contains(@id,'viz_iframe')]"));
	  driver.switchTo().frame(flipBaner);
//	  Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
	  WebElement flipBanerText = driver.findElement(By.xpath("//div[@id='bannercontainer']//span[@id = 'messageText' and text()='What are you looking for?']"));
	 Assert.assertTrue(flipBanerText.isDisplayed());
	 driver.switchTo().defaultContent(); 
	 String parentID = driver.getWindowHandle();
	 WebElement agiLink = driver.findElement(By.xpath("//a[text()='Agri']"));
	 agiLink.click();
//		Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới

	 switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
//		Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới

	 WebElement accountDetails = driver.findElement(By.xpath("//p[text()= 'Account Details']"));
	 accountDetails.click();
	 switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
//		Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
	 driver.switchTo().frame(driver.findElement(By.xpath("//frame[@src='footer.html']")));
	 driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
//	 Step 06- Click CSR link on Privacy Policy page
	 
	 switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	 driver.findElement(By.xpath("//a[text()='CSR']")).click();
//		Step 07 - Close tất cả windows/ tabs khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
//		Back về parent windows 
	 closeAllWithoutparentWindow(parentID);
	 String parentTitle = driver.getTitle();
	 Assert.assertEquals(parentTitle, "HDFC Bank: Personal Banking Services");
}
public boolean closeAllWithoutparentWindow(String parentID) {
	Set<String> allWindowID = driver.getWindowHandles();
	for (String childID:allWindowID) {
		
		if(!childID.equals(parentID)) {
			driver.switchTo().window(childID);
			driver.close();
		}
	}
	driver.switchTo().window(parentID);
	if(driver.getWindowHandles().size()==1) {
		return true;}
		else 
			return false;
		
	}

public void switchToWindowByTitle(String childTitle) {
	Set<String> allWindowID = driver.getWindowHandles();
	for (String childrenID:allWindowID) {
		driver.switchTo().window(childrenID);
		String currentWin = driver.getTitle();
		if(currentWin.equals(childTitle)) {
			break;
		}
		
		
	}
}
@Test
public void TC_04() {
//	Step 01 - Truy cập vào trang: http://live.guru99.com/index.php/
	driver.get("http://live.guru99.com/index.php/");
	String ParentID = driver.getWindowHandle();
//		Step 02 - Click vào Mobile tab
	driver.findElement(By.xpath("//a[text()='Mobile']")).click();
//		Step 03 - Add sản phẩm Sony Xperia vào để Compare (Add to Compare)
	WebElement sonyExpediaAddToCompare = driver.findElement(By.xpath("//a[@title='Sony Xperia' and text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"));
	sonyExpediaAddToCompare.click();
//		Step 04 - Add sản phẩm Samsung Galaxy vào để Compare (Add to Compare)
	WebElement samSungAddToCompare = driver.findElement(By.xpath("//a[@title='Samsung Galaxy' and text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"));
	samSungAddToCompare.click();
//		Step 05 - Click to Compare button
	driver.findElement(By.xpath("//button[@title = 'Compare']")).click();
//		Step 06 - Switch qa cửa sổ mới (chứa 2 sản phẩm đã được Add vào để Compare)
	switchToChildWindow(ParentID);
//		Step 07 - Verify title của cửa sổ bằng: Products Comparison List - Magento Commerce
	String compareWindowTitle = driver.getTitle();
	Assert.assertEquals(compareWindowTitle, "Products Comparison List - Magento Commerce");
//		Step 08 - Close tab và chuyển về Parent Window
	
	closeAllWithoutparentWindow(ParentID);
}
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
