package Selenium;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic12_JavaScript_Executor{
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	String customerName,dateOfBirth,address, city, state, pinno, mobile, email, password;
	By customerNameTextbox = By.xpath("//input[@name ='name']");
	By genderradio = By.xpath("//input[@value='m']");
	By dobTextBox = By.xpath("//input[@name='dob']");
	By addressTextarea = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name ='city']");
	By stateTextbox = By.xpath("//input[@name ='state']");
	By pinnoTextbox = By.xpath("//input[@name ='pinno']");
	By mobileTextbox = By.xpath("//input[@name ='telephoneno']");
	By emailTextbox = By.xpath("//input[@name ='emailid']");
	By passwordTextbox = By.xpath("//input[@name ='password']");
	
 @BeforeTest
 public void beforeTest() {
//	 driver = new FirefoxDriver();
	 System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();
	 jsExecutor = (JavascriptExecutor) driver;
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 //Data
	 
	 customerName ="Ta Thi Anh";
	 dateOfBirth = "02-03-1996";
	 address = "Trung Hoa Cau Giay";
	 city = "Ha Noi";
	 state="Viet Nam";
	 pinno= "600000";
	 mobile= "0123456789";
	 email= "anh"+randomNumber()+"@gmail.com";
	 System.out.println(email);
	 password="Test12345";
	 
	  }
  @Test
  public void TC_01() {
	driver.get("http://live.guru99.com/");  
	
//	  Step 01 - Truy cập vào trang: http://live.guru99.com/
//		  Step 02 - Sử dụng JE để get domain của page
	  String guruDomain = (String) jsExecutor.executeScript("return document.domain");
	  System.out.println(guruDomain);
//		  Verify domain =  live.guru99.com
	  Assert.assertEquals(guruDomain, "live.guru99.com");
//		  Step 03 - Sử dụng JE để get URL của page
	  String URL = (String) jsExecutor.executeScript("return document.URL");
//		  Verify URL =  http://live.guru99.com/
	 Assert.assertEquals(URL, "http://live.guru99.com/");
//		  Step 04 - Open MOBILE page (Sử dụng JE)
	 WebElement mobileTab = driver.findElement(By.xpath("//a[text() = 'Mobile']"));
	 jsExecutor.executeScript("arguments[0].click();", mobileTab);
//		  Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
	 WebElement samsungGalaxyAddToCardButton = driver.findElement(By.xpath("//a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
	 jsExecutor.executeScript("arguments[0].click();", samsungGalaxyAddToCardButton);
//		  Hướng dẫn: sử dụng following-sibling
//		  Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
	 String shopingCardInerText = (String) jsExecutor.executeScript("return document.documentElement.innerText");
	 Assert.assertTrue(shopingCardInerText.contains("Samsung Galaxy was added to your shopping cart."));
//		  Step 07 - Open PRIVACY POLICY page (Sử dụng JE)
//	  Verify title của page = Privacy Policy (Sử dụng JE)
	 WebElement privacyPolicy = driver.findElement(By.xpath("//a[text()= 'Privacy Policy']"));
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", privacyPolicy);
	 jsExecutor.executeScript("arguments[0].click();", privacyPolicy);
	 String privacyPolicyTitle = driver.getTitle();
	 Assert.assertEquals(privacyPolicyTitle, "Privacy Policy");

//		  Step 08 - Srcoll xuống cuối page
	 jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//		  Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath: 
	String wishlist_cntText = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td")).getText();
	Assert.assertEquals(wishlist_cntText, "The number of items in your Wishlist.");
//	Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE)
//		Verify domain sau khi navigate = demo.guru99.com
	jsExecutor.executeScript("window.location= 'http://demo.guru99.com/v4/'");
	String domain =(String) jsExecutor.executeScript("return document.domain");
	Assert.assertEquals(domain, "demo.guru99.com");
  }
   @Test
   public void TC_02_CreateNewCustomer() throws Exception {
//	   Step 01 - Access vào trang: http://demo.guru99.com/v4
	   driver.get("http://demo.guru99.com/v4");
//		   Step 02 - Đăng nhập với thông tin: User =  mngr181358 | Pass = berydUp
	   driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358");
	   driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
	   driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	   Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
//		   Note: Manual test để lấy thông tin User/Pass nếu hết hạn - User chỉ tồn tại trong 20 ngày - http://demo.guru99.com/   
//		   Step 03 - Chọn menu New Customer
	   driver.findElement(By.xpath("//a[text()='New Customer']")).click();
//		   Step 04 - Nhập toàn bộ dữ liệu đúng > Click Submit
	   driver.findElement(customerNameTextbox).sendKeys(customerName);
	   driver.findElement(genderradio).click();
//	   jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dobTextBox));
	   driver.findElement(dobTextBox).sendKeys(dateOfBirth);
//	   sendkeyToElementByJS(driver.findElement(dobTextBox),dateOfBirth);
	   driver.findElement(addressTextarea).sendKeys(address);
	   driver.findElement(cityTextbox).sendKeys(city);
	   driver.findElement(stateTextbox).sendKeys(state);
	   driver.findElement(pinnoTextbox).sendKeys(pinno);
	   driver.findElement(mobileTextbox).sendKeys(mobile);
	   driver.findElement(emailTextbox).sendKeys(email);
	   driver.findElement(passwordTextbox).sendKeys(password);
	   Thread.sleep(3000);
	   driver.findElement(By.xpath("//input[@name='sub']")).click();
//	   Step 05 - Verify tạo mới Customer thành công
	   Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
	   Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText(),customerName);
	   Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText(),"male");
	   String oldDate = driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText();
	   String datePart[]= oldDate.split("-");
	   String year = datePart[0];
	   String month = datePart[1];
	   String date = datePart[2];
	   String newDate = month+"-"+date+"-"+year;
	   Assert.assertEquals(newDate,dateOfBirth);
	   Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(),address);
	   Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(),city);
	   Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(),state);
	   Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(),pinno);
	   Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(),mobile);
	   Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(),email);
   }
	
	 public int randomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(9999);
		return randomNumber;
		 
	 }
	 @Test
	   public void TC_03_CreateNewAccount() throws Exception {
//		 Step 01 - Truy cập vào trang: http://live.guru99.com/
		 driver.get("http://live.guru99.com/");
//			 Step 02 - Click vào link "My Account" để tới trang đăng nhập (Sử dụng JE)
		 WebElement myAccount = driver.findElement(By.xpath(" //div[@class='footer']//a[@title='My Account'][contains(text(),'My Account')]"));
		 clickToElementByJS(myAccount);
//			 Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)
		 WebElement createAnAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		 clickToElementByJS(createAnAccount);
//			 Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password (Sử dụng JE)
		 WebElement firstName = driver.findElement(By.xpath("//input[@id='firstname']"));
		 sendkeyToElementByJS(firstName,"Anh");
		 WebElement lastName = driver.findElement(By.xpath("//input[@id='lastname']"));
		 sendkeyToElementByJS(lastName,"Ta");
		 WebElement emailfield = driver.findElement(By.xpath("//input[@id='email_address']"));
		 sendkeyToElementByJS(emailfield,email);
		 WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password']"));
		 sendkeyToElementByJS(passwordfield,password);
		 WebElement confirmPassword = driver.findElement(By.xpath(" //input[@id='confirmation']"));
		 sendkeyToElementByJS(confirmPassword,password); 
//			 (Lưu ý: Tạo random cho dữ liệu tại field Email Address)
//			 Step 05 - Click REGISTER button (Sử dụng JE)
		 WebElement registerButton = driver.findElement(By.xpath("//span[contains(text(),'Register')]"));
		 clickToElementByJS(registerButton);
//			 Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. (Sử dụng JE)
		 String sucessText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
		 Thread.sleep(1000);
		 Assert.assertTrue(sucessText.contains("Thank you for registering with Main Website Store.")); 
//			 Step 06 - Logout khỏi hệ thống (Sử dụng JE)
		 clickToElementByJS(driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")));
		 clickToElementByJS(driver.findElement(By.xpath("//a[text()='Log Out']"))); 
//			 Step 07 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công (Sử dụng JE)
		Thread.sleep(7000);
		WebElement homepage = driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]"));
		homepage.isDisplayed();
		String homepageTitle = (String)jsExecutor.executeScript("return document.title");
		Assert.assertEquals(homepageTitle,"Home page");
	 }
	 public Object clickToElementByJS(WebElement element) {
         
         return jsExecutor.executeScript("arguments[0].click();", element);
 }
	 public Object sendkeyToElementByJS(WebElement element, String value) {
		 return jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	 }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
