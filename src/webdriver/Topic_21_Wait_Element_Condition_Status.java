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

public class Topic_21_Wait_Element_Condition_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	//WebDriverWait expliciWait;
  
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
		//expliciWait = new WebDriverWait(driver, 10);
	    //driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// Đang apply 15s cho việc tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@Test 
	public void TC_01_FindElement() {
		// - Tìm thấy duy nhất 1 element/ node
		// Tìm thấy và thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên ko cần phải chờ hết timeouts 15s
		driver.findElement(By.cssSelector("input#email"));
		
		// - Tìm thấy nhiều hơn 1 element/ node
		// Nó sẽ thao tác với node đầu tiên
		// Ko quan tâm đến các node còn lại
		// Trong TH bắt locator sai thì nó sẽ tìm sai 
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("hoangnn@gmail.com");
		
		// - Không tìm thây element/ node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian tìm lại mà thấy element thì thỏa mãn đk - Pass
		// + Đánh fail testcase này tại step này + ko chạy step tiếp theo
		// + Throw ra 1 ngoại lệ : NoSuchElementExeption
		
		
	}

	@Test
	public void TC_02_FindElements() {
		// - Tìm thấy duy nhất 1 element/ node
		// Tìm thấy và lưu nó vào 1 list = 1 element
		// Vì nó tìm thấy nên ko cần phải chờ hết timeouts 15s
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elements.size());
		
		// - Tìm thấy nhiều hơn 1 element/ node
		// Tìm thấy và lưu nó vào 1 list = element tương ứng
		 elements = driver.findElements(By.xpath("//input[@type='email']"));
		System.out.println("List element number = " + elements.size());
		
		// - Không tìm thây element/ node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
	    // Nếu trong thời gian tìm lại mà thấy element thì thỏa mãn đk - Pass
		// Nếu hết thời gian (15s) mà vẫn ko tìm thấy element
		// + Ko đánh fail testcase + vẫn chạy step tiếp theo
		// + Trả về 1 list rỗng (empty) = 0
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}