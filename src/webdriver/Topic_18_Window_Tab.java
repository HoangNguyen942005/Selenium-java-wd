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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Window_Tab {
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
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}

	//@Test 
	public void TC_01_ID() {
		// Parent page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Window/ Tab nó sẽ có 2 hàm để lấy ra ID của Window/ tab đó
		
	    // 1- Nó sẽ lấy ra cái ID của tab/ window mà nó đang đứng (active)
		String parentPageWindowID = driver.getWindowHandle();
		System.out.println("Parent Page = " + parentPageWindowID);
		
		// Click vào Google link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		switchToWindowByID(parentPageWindowID); // Tạo hàm ở dưới, vì nó ko bằng parentPageWindowID nên nó quét sang ID Google
		
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		// Dùng hàm để lấy ra được ID của tab Google (Child)
		String googleWindowID = driver.getWindowHandle();
		
		switchToWindowByID(googleWindowID); // Tạo hàm ở dưới, vì nó ko bằng googleWindowID nên nó quét sang trang main
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");  
	}

	@Test
	public void TC_02_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String parentID = driver.getWindowHandle();
		
		// Click vào Google link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		switchToWindowByPageTitle("Google"); // Lấy title của google
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
		sleepInSecond(3);
		
		switchToWindowByPageTitle("Selenium WebDriver"); // Quay lại trang main
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
		// Click vào Facebook link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		
		switchToWindowByPageTitle("Facebook – log in or sign up");
		driver.findElement(By.cssSelector("input#email")).sendKeys("abc@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("123456789");
		
		switchToWindowByPageTitle("Selenium WebDriver"); // Quay lại trang main
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
		// Click vào Tiki link để bật ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.cssSelector("input[class^='FormSearch']")).sendKeys("Macbook");
		
		// Đóng tất cả các tab vừa mở, chỉ để lại trang main
		closeAllWindowWithoutParent(parentID);
		sleepInSecond(3);
	}
	
	//@Test
	public void TC_03_Live_Guru() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		String parentID = driver.getWindowHandle();
		
		// Click vào Xperia - Click
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), 
				"The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), 
				"The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		
		// Sau khi click Close Window thì driver chuyển về trang nào
		//driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		closeAllWindowWithoutParent(parentID);
		
		switchToWindowByPageTitle("Mobile");
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), 
				"The product IPhone has been added to comparison list.");
	}
	// Dùng được cho duy nhất 2 ID
	public void switchToWindowByID(String otherID){
		
		// Lấy 2 ID ra cho vào 1 Set 
		// Set lưu trữ các phần tử không bị trùng lặp
		Set<String> allWindowIDs = driver.getWindowHandles(); 
		
		// Sau đó dùng vòng lặp duyệt qua và kiểm tra		
		for (String id : allWindowIDs) {
			// Nếu id không bằng otherID thì nó sẽ switch qua cái id còn lại (vì chỉ có 2 id)
			if(!id.equals(otherID)) {
			driver.switchTo().window(id);	
			sleepInSecond(2);
			}
		}	
	}
	
	// Dùng cho từ 2 iD trở lên, lấy theo Title - Mỗi trang có 1 title riêng nên dễ thao tác
	public void switchToWindowByPageTitle(String expectedpageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles(); 
				
		for (String id : allWindowIDs) {
			// Switch đến từng ID trước
			driver.switchTo().window(id);
			
			// Lấy ra cái title của page này
			String actualPageTitle = driver.getTitle();
			
			if (actualPageTitle.equals(expectedpageTitle)) { // Nếu title thực tế bằng title mong đợi thì sẽ dừng vòng lặp
				sleepInSecond(2);
				break;
			}
		}	
	}
	
	public void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles(); 
		
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) { // nếu id không bằng parentID , sẽ switch qua các id khác và close các tab chứa id đó
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
		}
		driver.switchTo().window(parentID);
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