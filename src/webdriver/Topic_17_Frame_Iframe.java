package webdriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
    String emailAddress = "automation" + getRandomNumber() + "@gmail.com";
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
		
		//driver = new ChromeDriver(options);
		//driver.manage().window().maximize();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test 
	public void TC_01_Kyna_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		
		// Verify Facebook iframe hiển thị
		// Facebook iframe vẫn là 1 element của trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
		// Cần phải switch vào đúng cái thẻ iframe chứa các element đó
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		
		String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(facebookLike);
		
		Assert.assertEquals(facebookLike, "165K followers");
		
		// Cần switch về main page
		driver.switchTo().defaultContent();
		
		// Từ main page switch qua iframe Chat
		driver.switchTo().frame("cs_chat_iframe");
		
		// Click vào chat để show lên chát support
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("ABC");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
	    new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");	
	    driver.findElement(By.name("message")).sendKeys("Tư vấn khóa học Exel");
	    sleepInSecond(3);
	    
	   // Cần switch về main page
	    driver.switchTo().defaultContent();
	  
	   // Click vào tìm kiếm
	    driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
	    driver.findElement(By.cssSelector("button.search-button")).click();
	  
	  List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
	  
	  for (WebElement course : courseName) {
		Assert.assertTrue(course.getText().contains("Excel"));
	}    
}

	//@Test
	public void TC_02_HDFC_Bank_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		sleepInSecond(2);
		
		// Switch qua frame chưa login textbox
		driver.switchTo().frame("login_page");
		
		// Input vào Customer ID
		driver.findElement(By.name("fldLoginUserId")).sendKeys("hoang1234");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		// Verify password textbox hiển thị
		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
	}
	
    public int getRandomNumber() {
	    	return new Random().nextInt(99999);
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