package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_1_Fixed_In_DOM {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		Map<String, Integer> prefs = new HashMap<String, Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(options);
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test // Chưa xử lý được bài tập này
	public void TC_01_Fixed_Popup_In_Dom() {  // Bài này thuộc Not In Dom
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);
		
		// Dùng biến By
		By loginPopup = By.xpath("//div[@class='modal fade in']//div[@class='modal-dialog']");
		
		// Verify popup is undisplayed
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// Click vào button Login 
		driver.findElement(By.cssSelector("button.login_")).click();
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
			
	}

	//@Test
	public void TC_02_Fixed_In_Dom_Kyna() {  // In Dom là dù chưa xuất hiện trên UI nhưng nó vẫn hiển thị trong HTML
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(2);
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		
		//  Undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		// Click Đăng nhập
		driver.findElement(By.xpath("//a[@class='login-btn']")).click();
		sleepInSecond(2);

		//  Displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		// Nhập dữ liệu
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(1);
		// Verify message 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		// Đóng
		driver.findElement(By.cssSelector("button.k-popup-account-close ")).click();
		sleepInSecond(1);

		// Verify Undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());	
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