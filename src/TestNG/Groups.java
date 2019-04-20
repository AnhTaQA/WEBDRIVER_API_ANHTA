package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Groups {
	@Test(groups ="payment", description = "test description 1", priority = 5, enabled = false)
	public void TC_01 (){
		System.out.println("Run Test case 01");
	}
	@Test(groups ="customer",priority = 1)
	public void TC02 (){
		System.out.println("Run Test case 02");
	}
	@Test(groups ="payment",priority = 2)
	public void TC03 (){
		System.out.println("Run Test case 03");
	}
	@Test(groups ="payment",priority = 4)
	public void TC04 (){
		System.out.println("Run Test case 04");
	}
  @Test(groups ="customer",priority = 3)
  public void TC05 (){
	  System.out.println("Run Test case 05");
  }
  @Test(groups ="payment",priority = 6,enabled = false)
  public void TC06 (){
	  System.out.println("Run Test case 06");
  }
}