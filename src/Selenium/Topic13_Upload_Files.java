package Selenium;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic13_Upload_Files{
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;
	//get path of file
	String rootFolder = System.getProperty("user.dir");
	String fileName01 = "Image01.PNG";
	String fileName02 = "Image02.png";
	String fileName03 = "Image03.jpg";

	
	String filePath01 = rootFolder+"\\Upload_file\\"+fileName01;
	String filePath02 = rootFolder+"\\Upload_file\\"+fileName02;
	String filePath03 = rootFolder+"\\Upload_file\\"+fileName03;
	
	String chromePath = rootFolder+"\\Upload_file\\chrome.exe";
	String firefoxPath = rootFolder+"\\Upload_file\\firefox.exe";
	String iePath = rootFolder+"\\Upload_file\\ie.exe";
	String [] files = {filePath01,filePath02,filePath03};
	String firstName = "Anh";
	String subFolderName = "Anh"+randomNumber();
	String email = "Anh"+randomNumber()+"@gmail.com";
	
 @BeforeTest
 public void beforeTest() {
//	 driver = new FirefoxDriver();
//
	 System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();

//	  System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
//	  driver = new FirefoxDriver();
	  jsExecutor =  (JavascriptExecutor) driver;
	  waitExplicit = new WebDriverWait(driver,60);
//  get path of
	  
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	
	  }
 @Test
  public void TC_01_UploadFileBySendkeys_UploadInquee() throws Exception {
//	  Step 01 - Truy cập vào trang: 
//		  http://blueimp.github.com/jQuery-File-Upload/
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
//		  Step 02 - Sử dụng phương thức sendKeys để upload file nhiều file cùng lúc chạy cho 3 trình duyệt (IE/ Firefox/ Chrome
	  
//		  Upload 3 file:
//		  Image01.png
//		  Image02.png
//		  Image03.png
	  for(String file: files) {
		  WebElement addFileButton = driver.findElement(By.xpath("//input[@name='files[]']"));  
		  addFileButton.sendKeys(file); 
		  try {
			  Thread.sleep(2000);
		  }
		  catch(InterruptedException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  
//		  Step 03 - Kiểm tra file đã được chọn thành công
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName01+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName02+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName03+"']")).isDisplayed());
	 
//		  Step 04 - Click Start button để upload cho cả 3 file
	 WebElement StartUploadButton = driver.findElement(By.xpath("//span[text()='Start upload']"));
	 if(StartUploadButton.isDisplayed()) {
		 StartUploadButton.click();
	 }
	 else {
		jsExecutor.executeScript("arguments[0].click();", StartUploadButton);
	 }
//		  Step 05 - Sau khi upload thành công verify cả 3 file đã được upload
	 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
	 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName02+"']")).isDisplayed());
	 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName03+"']")).isDisplayed());
	 

	
  }
 @Test
  public void TC_02_UploadMultipleFiles() throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  WebElement uploadFileButton = driver.findElement(By.xpath("//input[@name='files[]']"));
	  uploadFileButton.sendKeys(filePath01+"\n"+filePath02+"\n"+filePath03);

	  
	  Thread.sleep(5000);
	  jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	  List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));

	  for(WebElement start:startButtons) {
  
		 start.click();
			
		  Thread.sleep(1000);
	  }
	  	 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName02+"']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName03+"']")).isDisplayed());
  }
 @Test

  public void TC_03_Upload_file_by_AutoIT () throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
//	  Step 01 - Truy cập vào trang:
//		  http://blueimp.github.com/jQuery-File-Upload/
	  for(String file:files) {
		  if(driver.toString().contains("ie")) {
			  WebElement uploadFileButton = driver.findElement(By.xpath("//input[@name='files[]']"));  
			  jsExecutor.executeScript("arguments[0].click();", uploadFileButton);
		  }
		  
	  else {
		 WebElement uploadFileButton = driver.findElement(By.cssSelector(".fileinput-button"));
	  uploadFileButton.click();
	 }
	 
	  Runtime.getRuntime().exec(new String[] {chromePath, file });
	 Thread.sleep(3000);
	  jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	  }
//		  Step 02 - Sử dụng AutoIT để upload file chạy cho 3 trình duyệt (IE/ Firefox/ Chrome)
	  
//		  Step 03 - Kiểm tra file đã được tải lên thành công
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName01+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName02+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName03+"']")).isDisplayed());
//	  
  
  List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));

  for(WebElement start:startButtons) {

	 start.click();
		
	  Thread.sleep(1000);
  }
  	 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
	 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName02+"']")).isDisplayed());
	 Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName03+"']")).isDisplayed()); 
}
 @Test
  public void TC_04_Upload_Files_By_Robot() throws Exception {

//	  Step 01 - Truy cập vào trang:
//		  http://blueimp.github.com/jQuery-File-Upload/
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
//		  Step 02 - Sử dụng Robot để upload file chạy cho 3 trình duyệt (IE/ Firefox/ Chrome)
// Specify file location 
	  for(String file: files) {
	  StringSelection select = new StringSelection(file);
	  // Copy to clipboard
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
//		  Step 03 - Kiểm tra file đã được tải lên thành công
      if(driver.toString().contains("ie")) {
		  WebElement uploadFileButton = driver.findElement(By.xpath("//input[@name='files[]']"));  
		  jsExecutor.executeScript("arguments[0].click();", uploadFileButton);
		  Thread.sleep(2000);
	      }
		  
	  else {
		 WebElement uploadFileButton = driver.findElement(By.cssSelector(".fileinput-button"));
	  uploadFileButton.click();
	  Thread.sleep(2000);
          }
      
	  
	  Robot robot = new Robot();
	  Thread.sleep(1000);
	  //Press Enter
	  robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      // Nhan xuong Ctrl - V
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);

      // Nha Ctrl - V
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_V);
      Thread.sleep(1000);

      // Nhan Enter
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      Thread.sleep(2000);

	  } 
	  jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	  Thread.sleep(1000);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName01+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName02+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '"+fileName03+"']")).isDisplayed());
  }
@Test
public void TC_05_Upload_files() throws Exception {
//	Step 01 - Open URL: 'https://encodable.com/uploaddemo/'
	driver.get("https://encodable.com/uploaddemo/");
//		Step 02 - Choose Files to Upload (Ex: UploadFile.jpg)
	WebElement uploadButton = driver.findElement(By.xpath("//input[@id='uploadname1']"));
	uploadButton.sendKeys(filePath01);
	Thread.sleep(1000);
	
//		Step 03 - Select dropdown (Upload to: /uploaddemo/files/)
	
	selectItemFromDropdownList("//select[@name='subdir1']","//select[@name='subdir1']/option","/uploaddemo/files/");
//		Step 04 - Input random folder to 'New subfolder? Name:) textbox (Ex: dam1254353)
	
	WebElement subfolder = driver.findElement(By.xpath("//label[text()='New subfolder? Name:']/following-sibling::input"));
	
	subfolder.sendKeys(subFolderName);
//		Step 05 - Input email and firstname (dam@gmail.com/ DAM DAO)
	
	driver.findElement(By.xpath("//input[@name = 'email_address']")).sendKeys(email);
	driver.findElement(By.xpath("//input[@name = 'first_name']")).sendKeys(firstName);
	
//		Step 06 - Click Begin Upload (Note: Wait for page load successfully)
	driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
//		Step 07 - Verify information
	Thread.sleep(5000);
	String pageText = (String)jsExecutor.executeScript("return document.documentElement.innerText");
	Assert.assertTrue(pageText.contains(email));
	Assert.assertTrue(pageText.contains(firstName));
	Assert.assertTrue(pageText.contains(fileName01));
//		    + Email Address: dam@gmail.com/ First Name: DAM DAO
//		    + File name: UploadFile.jpg
//		Step 08 - Click 'View Uploaded Files' link
	WebElement viewLink = driver.findElement(By.xpath("//a[text()='View Uploaded Files']"));
	viewLink.click();
//		Step 09 - Click to random folder (Ex: dam1254353)

	driver.findElement(By.xpath("//a[text()='"+subFolderName+"']")).click();

//		Step 09 - Verify file name exist in folder (UploadFile.jpg)
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
	
	
	
}
public void selectItemFromDropdownList(String parentXpath, String allItemXpath, String expectedValue) {
	WebElement parentDropDown = driver.findElement(By.xpath(parentXpath));
	parentDropDown.click();
	waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));
	for (WebElement item:allItem ) {
		if(item.getText().equals(expectedValue)) {
			item.click();
			break;
		}
		
	}
	  
}
public int randomNumber() {
	Random random = new Random();
	int randomNumber = random.nextInt(9999);
	return randomNumber;
	 
 }
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
