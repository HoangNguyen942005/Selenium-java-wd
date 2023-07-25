package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_2_Fixed_Not_In_DOM {
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
		driver.manage().window().maximize();
		
		// ImlicitlyWait ảnh hưởng tới việc tìm element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	}

	
	//@Test 
	public void TC_01_Fixed_Not_In_Dom_Tiki() { // Not In Dom là khi chưa mở element ra thì nó chưa xuất hiện trong HTML
		driver.get("https://tiki.vn/");
		
		// By : Nó chưa có thì đi tìm element
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		// Verify nó chưa hiện khi chưa click vào Login button, dùng findElements để nó không bị lỗi
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// Click cho bật login Popup lên
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(2);
		
		// Verify nó hiển thị , dùng findElements
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		
		driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("09834466201");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		sleepInSecond(2);
		
		// Close popUp
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		// Verify nó không hiển thị, dùng findElements
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}

	//@Test
	public void TC_02_Random_Not_In_Dom() {
		driver.get("https://www.facebook.com/");
		
		By createAccount = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		// Verify Create Account popup không hiển thị, dùng Elements số nhiều mới verify được
		Assert.assertEquals(driver.findElements(createAccount).size(), 0);
		
		driver.findElement(By.cssSelector("a[data-testid=open-registration-form-button]")).click();
		sleepInSecond(2);
		
		// Verify Create Account popup  hiển thị
		Assert.assertEquals(driver.findElements(createAccount).size(), 1);
		
		driver.findElement(By.name("firstname")).sendKeys("Automation");
		driver.findElement(By.name("lastname")).sendKeys("Fc");
		driver.findElement(By.name("reg_email__")).sendKeys("0983090322");
		driver.findElement(By.name("reg_passwd__")).sendKeys("1234567890");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("5");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("May");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1990");
		
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		// Verify Create Account popup không hiển thị
		Assert.assertEquals(driver.findElements(createAccount).size(), 0);
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