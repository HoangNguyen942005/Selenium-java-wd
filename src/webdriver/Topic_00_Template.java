package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Mở trang Register ra
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fsearch");
	}

	// HTML của FirstName textbox 
	// <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
	
	// Tên thẻ của element (TagName HTML) : input
	// Tên của thuộc tính (Attribute name) : type, data-val, data-val-required, id, name
	// Giá trị của thuộc tính (Attribute value) : text, true, First name is required, FirstName, FirstName
	@Test
	public void TC_01_ID() {
		// Muốn thao tác lên element thì đầu tiên phải tìm được element đó : findElêmnt
		// find theo cái gì : id/ class/ name/ css/ xpath/...
		// Find thấy element rồi thì action lên element đó : click/ senkey/...
		
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		
	}

	@Test
	public void TC_02_Class() {
		// mở màn hình search
		driver.get("https://demo.nopcommerce.com/search");
		// nhập text vào Search textbox
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
		
	}

	@Test
	public void TC_03_Name() {
		// Click vào Advaned Search Checkbox
		driver.findElement(By.name("advs")).click();
	}
	@Test
	public void TC_04_TagName() {
		// tìm ra và in có bao nhiêu thẻ input trên màn hình hiện tại
		System.out.println(driver.findElements(By.tagName("input")).size());
		
	}
	@Test
	public void TC_05_LinkText() {
		// CLick vào đường link Addresses (tuyệt đối)
		driver.findElement(By.linkText("Addresses")).click();
	}
	@Test
	public void TC_06_PartialLinkText() {
		// CLick vào đường link Apply for vendor account (tương đối)
		driver.findElement(By.partialLinkText("vendor account")).click();
		
	}
	@Test
	public void TC_07_CSS() {
		// mở lại trang register 
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fsearch");
		//1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		//2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		//3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("hoang200594@gmail.com");
		
	}
	@Test
	public void TC_08_Xpath() {
		// mở lại trang register 
				driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fsearch");
				//1
				driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
				//2
				driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
				//3
				driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("hoang200594@gmail.com");
				
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}