package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_26_Fluent_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	FluentWait<WebElement> fluentElement;
	FluentWait<WebDriver> fluentDriver;
	
	long allTime = 15; // Tổng thời gian, Second
	long pollingTime = 100; // Tần số, Milisecond
	
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
		
	}

	//@Test 
	public void TC_01_Fluent_Wait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		findElement("//div[@id='start']/button").click();
		
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
	}

	@Test
	public void TC_02_Fluent() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdountTime = findElement("//div[@id='javascript_countdown_time']");
		
		fluentElement = new FluentWait<WebElement>(countdountTime);
		
		fluentElement.withTimeout(Duration.ofSeconds(allTime))   // Duration : khoảng thời gian
		.pollingEvery(Duration.ofMillis(pollingTime)) // pollingTime là tần số
		.ignoring(NoSuchElementException.class);      // Ignoring là bỏ qua kết quả trả về là NoSuchElementException
		
		// Apply Điều kiện : getText
		// WebElement : tham số của hàm apply trong function
		// Boolean : Kiểu dữ liệu trả về của hàm apply trong function
		fluentElement.until(new Function<WebElement, Boolean>() {
			@Override  // ghi đè
			public Boolean apply(WebElement element) {
				return element.getText().endsWith("00");  // getText trả về khi endsWith của nó về 00
			}
		});
	}
	
	public WebElement findElement(String xpathLocator) {

		// Dùng Fluent Wait
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Set tổng thời gian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(allTime))
		// -  1/10 giây check 1 lần
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
		
		// Apply điều kiện tìm Element
		WebElement element = fluentDriver.until(new Function<WebDriver, WebElement>() {
		// WebDriver : Tham số của hàm apply trong function
		// WebElement : Kiểu dữ liệu trả về của hàm apply trong function
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
		
		return element;
		
	}
		
	// Show ra time - stamp tại thời điểm gọi hàm này
	// time - stamp = ngày - giờ - phút - giây
	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
		
	}
}