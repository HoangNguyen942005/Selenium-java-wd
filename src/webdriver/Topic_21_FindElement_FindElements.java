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

public class Topic_21_FindElement_FindElements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
  
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
		expliciWait = new WebDriverWait(driver, 10);
	//	driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test 
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		
		// Element hiển thị
		// Có trên UI (bắt buộc)
		// Có trong HTML (bắt buộc)
		
		// Wait cho email address textbox hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		driver.findElement(By.id("email")).sendKeys("hoangnn@gmail.com");
	}

	//@Test
	public void TC_02_Invisible_Undisplayed_Invisility_I() {
		// Không có trên UI (bắt buộc)
		// Có trong HTML
		
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		// Wait cho re-enter email address textbox không hiển thị trên UI trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	//@Test
	public void TC_02_Invisible_Undisplayed_Invisility_II() {
		// Không có trên UI (bắt buộc)
		// Không có trong HTML
		
		driver.get("https://www.facebook.com/");
		
		// Wait cho re-enter email address textbox không hiển thị trên UI trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	//@Test
	public void TC_03_Precence_I() {
		// Có ở trong cây HTML (DOM) (bắt buộc)
		// Có ở trên UI
		
		driver.get("https://www.facebook.com/");
		
		// Wait cho email address textbox precence trong HTML trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	
	//@Test
	public void TC_03_Precence_II() {
		// Có ở trong cây HTML (DOM) (bắt buộc)
		// Không có trên UI
		
        driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		// Wait cho re-enter email address textbox không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	@Test // test case chưa chạy được
	public void TC_04_Staleness() {
		// Không có trên UI (bắt buộc)
		// Không ở trong cây HTML 
		
        driver.get("https://www.facebook.com/");
        
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        
       // driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("hoangnn@gmail.com");
        
        // Phase 1 : Element có trong cây HTML
        WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));
        
        // Thao tác với element nào đó.. làm cho element re-enter ko còn trong DOM nữa
        
        // Close popup đi
        driver.findElement(By.cssSelector("img._8idr")).click();
		
		// Wait cho re-enter email address textbox không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}