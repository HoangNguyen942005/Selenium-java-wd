package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_02_Manage_Cart {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest(alwaysRun = true) // alwaysRun chỉ set-up ở BeforeTest và AfterTest
	public void initBrowser() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test(groups = {"admin", "cart"})
	  public void Product_01_Create_Visa() {
		  
		  
	  }
	  
	  @Test(groups = {"admin", "cart"})
	  public void Product_02_View_Visa() {
		  
		  
	  }
	  
	  @Test(groups = {"admin", "cart"})
	  public void Product_03_Update_Visa() {
		  
		  
	  }
	  
	  @Test(groups = {"admin", "cart"})
	  public void Product_04_Delete_Visa() {
		  
		  
	  }
	  
	  @AfterTest(alwaysRun = true)  
		public void cleanBrowser() {
			driver.quit();
		}
}
