package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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

public class Topic_16_Popup_Part_3_Random_PopUp {
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

	// Yêu cầu :
	// Random popup nên nó có thể hiển thị 1 cách ngẫu nhiên hoặc ko hiển thị
	// Nếu như ko hiển thị thì mình cần thao tác lên popup -> Đóng nó để đi qua step tiếp theo
	// Khi mà đóng nó lại có thể refresh trang để nó hiện lên lại/ hoặc là ko
	// Nếu như nó ko hiển thị thì qua step tiếp theo luôn
	
	//@Test 
	public void TC_01_Random_In_Dom_Java_Code_Geeks() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(30);
		
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		
		// Vì nó luôn có trong DOM nên có thể dùng isDisplayed() để kiểm tra được
		if (driver.findElement(lePopup).isDisplayed()) {
			// Nhập email vào
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
			driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
			sleepInSecond(10);
			
			// Verify
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(),"Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));
			sleepInSecond(15);
			// Đóng popup đi --> Qua step tiếp theo
			// Sau 5s nó sẽ tự đóng popup
		}  
		
		String articleName = "Agile Testing Explained";
		
		   // Qua step này
		  driver.findElement(By.cssSelector("input#search-input")).sendKeys(articleName);
		  driver.findElement(By.cssSelector("button#search-submit>span.tie-icon-search")).click();
		  sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='post-title']/a[text()='Agile Testing Explained']")).getText(), articleName);
	}

	//@Test
	public void TC_02_Fixed_In_Dom_VNK() {
		driver.get("https://vnk.edu.vn/");
        sleepInSecond(40);
        
        By popup = By.cssSelector("div#tve_editor");
        
        if (driver.findElement(popup).isDisplayed()) {
			// Close popup này đi hoặc là click vào link  
        	driver.findElement(By.cssSelector("div.thrv_icon>svg.tcb-icon")).click();
        	sleepInSecond(3);
		}
		
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
	}
	
	@Test
	public void TC_03_Fixed_Not_In_Dom_Dehieu() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		By popup = By.cssSelector("div.popup-content");
		
       //findElement --> Sẽ bị fail khi ko tìm thấy element --> ném ra 1 ngoại lệ : NoSuchElementException
	   //findElements --> ko bị fail khi ko tìm thấy element --> trả về 1 list rỗng (empty)
		
		// isDisplayed()
		// Không còn trong DOM thì khi nó vào tìm element : findElements
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("HoangNN");
			driver.findElement(By.id("popup-email")).sendKeys(emailAddress);
			driver.findElement(By.id("popup-phone")).sendKeys("0983446321");
			sleepInSecond(3);
			
			driver.findElement(By.cssSelector("button#close-popup")).click();
		}
		
		// Nếu popup không hiển thị
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.id("search-courses")).sendKeys(courseName);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		
		// Duy nhất 1 course hiển thị
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course-content >h4")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content >h4")).getText(), courseName);	
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