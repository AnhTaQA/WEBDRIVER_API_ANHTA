package Training;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Lession03_WebElement_WebBrowser {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\anhtt\\Desktop\\Software\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);
	}

	@Test
	public void TC_01_Check_Element_Displayed() throws Exception {

		// email, age, education
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed());

	}




	@AfterTest
	public void afterTest() {
//	  driver.quit();
	}

}
