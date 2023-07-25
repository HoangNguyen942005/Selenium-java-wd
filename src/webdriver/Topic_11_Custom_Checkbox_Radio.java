package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new ChromeDriver();
		
		// Luôn khởi tạo sau biến driver này
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	// https://docs.google.com/document/d/1kPgRirztWIC9R_XiZFNYI3E0KVWfrzf2x_Het5MRj3s/edit#heading=h.zg8vyw1whenb
	// @Test
	 public void TC_01_() { // TC 1 Fail vì thẻ input bị ẩn đi
		 driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		 sleepInSecond(5);
		 
		 // CASE 1
		 // Thẻ input bị che nên ko thao tác được
		 // Thẻ input lại dùng để verify được --> vì thẻ isSelected() chỉ work với thẻ input		 
		 // Thao tác chọn
		 driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		 sleepInSecond(3);
		 // Verify đã chọn thành công
		 Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());		
	 }
		 
	// @Test
		public void TC_02_() { // Verify bị fail vì thẻ isSelected() chỉ work với thẻ input	
		 driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		 sleepInSecond(5);
		 
		 // CASE 2
		 // Thẻ khác với input để click (span/ div/ label/...) --> đang hiển thị là được
		 // Thẻ này lại không verify được	--> vì thẻ isSelected() chỉ work với thẻ input		 
		 // Thao tác chọn
		 driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).click();
		 sleepInSecond(3);
		 
		 // Verify đã chọn thành công
		 Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).isSelected());	
		 
		 // Thẻ span// div/ label luôn luôn trả về false
			 
	 }
	 
	// @Test
		public void TC_03_(){
		 driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		 sleepInSecond(5);
		 
		 // CASE 3
		 // Thẻ khác với input để click (span/ div/ label/...) --> đang hiển thị là được
		 // Thẻ này lại không verify được	--> vì thẻ isSelected() chỉ work với thẻ input		 
		 // Thao tác chọn
		 driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		 sleepInSecond(3);
		 
		 // Verify đã chọn thành công
		 Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());	
		 
		 // Ở TH viết basic/ demo thì được
		 // Appply vào dự án/ framework thì không nên
		 // Vì 1 element phải define tới nhiều locator (dễ bị hiêu nhầm/ mất thời gian để maintain/ ko tập trung)
	 }
	 
	// @Test
		public void TC_04_(){
			
		 driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		 sleepInSecond(5);
		 
		 // CASE 4
		 // Thẻ input bị ẩn những vẫn dùng click
		 // Hàm click() của WebElement nó sẽ ko thao tác vào element bị ẩn được
		 // Nên sẽ dùng 1 hàm click() của JavaScript để click (click vào element bị ẩn được)
		 // Selenium cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào kịch bản test được --> JavascriptExecutor
		
		 By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		 
		// Thao tác chọn
		// Thao tác với locator ẩn nên tạo hàm jsExecutor để thao tác 
		 jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		 sleepInSecond(3); 
		 
		 // Verify đã chọn thành công
		 Assert.assertTrue(driver.findElement(radioButton).isSelected());
	 }
	
	 //@Test
		public void TC_05_(){
		     driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		     sleepInSecond(3);
		     
		     By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		     By checkbox = By.cssSelector("div[aria-label='Quảng Ngãi']");
		     
		   // Thao tác chọn
			 
			 jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
			 sleepInSecond(2); 
			 jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
			 sleepInSecond(2); 
			 
			 // Verify đã chọn thành công
			 // Cách 1 
			 Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
			 Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed()); 
			 
			 // Cách 2
			 Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
			 Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
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