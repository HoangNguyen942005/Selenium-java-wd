package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Url() {
	
		driver.get("http://live.techpanda.org/");
		 
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		sleepInSecond(2);// tạo hàm bên dưới để code được gọn
	
	    Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
	
        // Click vào Create an Account	
	     driver.findElement(By.xpath("//a[@title='Create an Account']")).click(); // Xpath 
	    //driver.findElement(By.cssSelector("a[title='Create an Account'")).click(); // viết bằng CSS bỏ // và @ đi
	     sleepInSecond(2);
	     
	     Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
	}
	

	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		 
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		sleepInSecond(2);
	
		// Verify lại 
	    Assert.assertEquals(driver.getTitle(),"Customer Login");
	
        // Click vào Create an Account	
	     driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	    //driver.findElement(By.cssSelector("a[title='Create an Account'")).click(); // viết bằng CSS bỏ // và @ đi
	     sleepInSecond(2);
	     
	     //Verify lại
	     Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/");
		 
		//  Click vào My Account 
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		sleepInSecond(2);
	
	
        // Click vào Create an Account	
	     driver.findElement(By.xpath("//a[@title='Create an Account']")).click(); 
	    //driver.findElement(By.cssSelector("a[title='Create an Account'")).click(); // viết bằng CSS bỏ // và @ đi
	     sleepInSecond(2);
	     
	     // Back lại trang vừa nãy
	     driver.navigate().back();
	     sleepInSecond(2);
	     Assert.assertEquals(driver.getTitle(),"Customer Login");
	     
	     // Foward
	     driver.navigate().forward();
	     sleepInSecond(2);
	     Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	}
	@Test
	public void TC_04_Page_Source_HTML() {
		driver.get("http://live.techpanda.org/");
		 
		//  Click vào My Account 
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
	
	    // Verify page HTML có chứa 1 chuỗi mong muốn, contains tìm kiếm chuỗi ký tự trong chuỗi này. đúng trả về True và ngược lại
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account "));
		 
		 // Click vào Create an Account	
	     driver.findElement(By.xpath("//a[@title='Create an Account']")).click(); 
	     sleepInSecond(2);
	     Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		 
		
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
		driver.quit();
	}
}