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

public class Topic_24_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	String beachFileName = "beach1.jpg";
    String computerFileName = "computer.jpg";
    String doraemonFileName = "doraemon.jpg";
    
    String beachFilePath = projectPath + "\\uploadFile\\" + beachFileName;
    String computerFilePath = projectPath + "\\uploadFile\\" + computerFileName;
    String doraemonFilePath = projectPath + "\\uploadFile\\" + doraemonFileName;
    
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
	public void TC_01_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 3);
		
		// CLick vào start button 
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Thiếu thời gian để cho 1 element tiếp theo hoạt động được
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
				
		// Loading icon mất 5s mới biến mất
		
		// Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		// TC 1 fail vì set thời gian 3s
	}

	//@Test
	public void TC_02_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 5);
		
		// CLick vào start butotn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Đủ thời gian để cho 1 element tiếp theo hoạt động được
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		// TC 2 pass vì đủ thời gian để verify
	}
	
	//@Test
	public void TC_03_More_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 50);
		
		// CLick vào start butotn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Thừa thời gian để cho 1 element tiếp theo hoạt động được
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		// TC 3 pass vì đủ thời gian verify, tuy nhiên verify xong sẽ lập tức dừng lai chứ ko chạy hết 50s
	}
	
	//@Test
	public void TC_04_Visible() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
		
        explicitWait = new WebDriverWait(driver, 5);
		
		// CLick vào start butotn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Đủ thời gian để cho 1 element tiếp theo hoạt động được
		// Visible là có trên UI và có trong DOM, wait đến khi text hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	//@Test
	public void TC_05_InVisible() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
		
        explicitWait = new WebDriverWait(driver, 5);
		
		// CLick vào start butotn
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Đủ thời gian để cho 1 element tiếp theo hoạt động được
		// Invisible là không có trên UI nhưng có trong DOM, wait cho đến khi icon loading biến mất khỏi UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		// Get text và verify 
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	//@Test
	public void TC_06_Ajax_Loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
        explicitWait = new WebDriverWait(driver, 15);
		
		// Wait cho Date Picker được hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.RadCalendar")));
		
		// Verify cho Selected Dates là ko có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), 
				"No Selected Dates to display.");
		
		// Wait cho ngày 19 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		// Hiện ra cookie nên chưa xử lý được, cần thêm step này để TCs có thể chạy
		// expliciWait = new WebDriverWait(driver, 15);
		
		// Click vào ngày 19
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		// Wait cho cái Ajax icon loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		
		// Wait cho ngày vừa được chọn là được phép click trở lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));
		
		// Verify cho Selected Dates là "Wednesday, July 19, 2023"
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), 
				"Wednesday, July 19, 2023");
	}
	
	@Test
	public void TC_07_Upload_File() { // chưa xử lý được 
        driver.get("https://gofile.io/uploadFiles");
		
        explicitWait = new WebDriverWait(driver, 45);
        
		// Wait cho Add Files button được visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beachFilePath + "\n" + computerFilePath + "\n" + doraemonFilePath);
		
		// Wait cho các loading icon của từng file biến mất khỏi UI
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress "))));
		
		// Wait cho upload message thành công được visible
		//expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row']//div[text()='Your files have been successfully uploaded']")));
		
		// Verify message is displayed
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row']//div[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		// Wait cho Show link download 
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='https://gofile.io/d/P0ScOC']")));
		
		// Click vào link download
		driver.findElement(By.xpath("//a[text()='https://gofile.io/d/P0ScOC']")).click();
		
	}
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}