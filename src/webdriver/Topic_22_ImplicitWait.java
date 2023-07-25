package webdriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_ImplicitWait {
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
		
		// 1- Ảnh hưởng trực tiếp tới 2 hàm : findElement/ findElements 
		// 2- Ngoại lệ
		// Impliciwait Wait set ở đâu nó sẽ apply từ đó trở xuống
		// Nếu bị gán lại thì sẽ dùng cái giá trị mới/ không dùng giá trị cũ
		
	    //driver.manage().window().maximize();
		// Đang apply 10s cho việc tìm element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// implicitlyWait : Wait ngầm định - không cho trạng thái hoặc điều kiện nào cụ thể 
		// Wait cho việc TÌM Element
	}

	@Test 
	public void TC_01_Not_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// CLick vào start butotn-
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Loading icon mất 5s mới biến mất
		
		// Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		// TC 1 fail vì set thời gian không đủ vì loading icon chưa biến mất nên chưa verify được step tiếp theo
	}

	@Test
	public void TC_02_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// CLick vào start butotn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		// TC 2 pass vì đủ thời gian và có thể verify được step tiếp theo
	}
	
	@Test
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// CLick vào start butotn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		// TC 3 pass nhưng cũng sẽ không chạy hết 10s
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}