package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action_Part_I {
	WebDriver driver;
	Actions action; // khai báo hàm để thao tác với chuột
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	// @Test
	public void TC_01_ToolTip() {
		  driver.get("https://automationfc.github.io/jquery-tooltip/");
		  
		  // Sau action luôn là perform()
		  action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		  sleepInSecond(3);
		  
		  Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), 
				  "We ask for your age only for statistical purposes.");	 
	}

	// @Test
	public void TC_02_Myntra() {
          driver.get("https://www.myntra.com/");
          
          // Hover vào Kids
          action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
          sleepInSecond(3);
          
          // Click vào Home and Baths
          
          driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
          
          Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
	}

	 @Test
	public void TC_03_Fahasa() {
		 driver.get("https://www.fahasa.com/");
		 sleepInSecond(2);
		 
		 // Hover từ menu vào
		 action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		 sleepInSecond(2);
		 
		 action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
		 sleepInSecond(2);
		 
		 // Click vào 
		 driver.findElement(By.xpath("//div[contains(@class,'fhs_column_stretch')]//a[text()='Quản Trị - Lãnh Đạo']")).click();
		 
		 // Verify
		 Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed(), 
				"Quản Trị - Lãnh Đạo");
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}