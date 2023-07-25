package webdriver;

import java.util.Date;
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

public class Topic_25_Mix_Implicit_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	
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
		
		// ExplicitWait (WebDriverWait) : Wait tường minh - có trạng thái/ điều kiện cụ thể của Element
	}

	//@Test 
	public void TC_01_Element_Found() {
		// Element có xuất hiện và không chờ hết time out
		// Dù có set cả 2 loại wait thì ko ảnh hưởng
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Impclicit Wait : chỉ apply cho việc findElement/ findElements
		// Explicit Wait : cho các điều kiện của element
		
		driver.get("https://www.facebook.com/");
		
		// Implicit
		System.out.println("Thời gian bắt đầu của implicit: " + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thời gian kết thúc của implicit: " + getTimeStamp());
		
		// Explicit
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
	}

	//@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
		
		// Implicit
		System.out.println("Thời gian bắt đầu của implicit: " + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#email1")); // sẽ tìm không thấy Element này
		} catch (Exception e) {   // catch sẽ bắt lại và thực thi câu lệnh in ra
			System.out.println("Thời gian kết thúc của implicit: " + getTimeStamp());
		}
		
		// TC 2 về cơ bản là fail do ko tìm ra element, nhưng chạy hàm catch() thực thi lệnh in nên pass
		// Output
		// Có cơ chế tìm lại sau mỗi 0.5s
		// Khi hết timeout sẽ đánh fail testcase
		// Throw ra 1 exception : NoSuchElement	
	}
	
	//@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		// 3.1 - Implixit = Explicit
		// 3.2 - Implixit < Explicit
		// 3.3 - Implixit > Explicit
		// Nhận time out của cả 2 (chạy theo dạng Async)
		// Cả 2 cùng chạy 1 lúc/ hoặc chênh lệch từ 0.5 đến 1s
		// Implixit sẽ luôn chạy trước, vì luôn ưu tiên tìm Element trước
		// Mỗi lần run sẽ ra 1 tổng time out khác nhau
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
	/*	// Implicit
		System.out.println("Thời gian bắt đầu của implicit: " + getTimeStamp());
			try {
			  driver.findElement(By.cssSelector("input#email1")); 
			} catch (Exception e) {   
				System.out.println("Thời gian kết thúc của implicit: " + getTimeStamp());
		} */
				
		// Explicit
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#email1")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
		}
	}
	
	//@Test
		public void TC_04_Element_Not_Found_Explicit_By() {
		  explicitWait = new WebDriverWait(driver, 5);
		  driver.get("https://www.facebook.com/");
			
		// Explicit - By là tham số nhận vào của hàm explixit - visibilityOfAllElementsLocated(By)
		// Implixit = 0
		// Tổng thời gian = Explicit
			System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
			try {
				explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#email1")));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
			}	
		}
		
		@Test
		public void TC_05_Element_Not_Found_Explicit_Element() {
		  explicitWait = new WebDriverWait(driver, 5);
		  driver.get("https://www.facebook.com/");
			
			System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
			try {
				explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#email1"))));
				// Đối với visibility : Nó sẽ findElement trước rồi mới đến visibilityOf
				// Nếu không tìm ra Element sẽ kết thúc luôn theo set Implixit
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Thời gian kết thúc của explicit: " + getTimeStamp());
			}
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