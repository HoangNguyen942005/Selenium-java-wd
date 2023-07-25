package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Excercise_Login {
	WebDriver driver;
	Random rand; // khởi tạo biến random
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, password, fullName; 
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
        
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Dùng nhiều lần nên tạo hàm để truyền dữ liệu khi senKeys
		emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		password = "12345678";
	}

	 @Test
	 public void Login_01_Empty_Email_And_Password() {
		 
		 driver.get("http://live.techpanda.org/");
		    // Click vào My Account
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			sleepInSecond(2);
			
			// Không nhập ở email và so sánh message trả ra bằng nhau
			driver.findElement(By.id("send2")).click();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
			
			// Không nhập ở password và so sánh message trả ra bằng nhau			
			Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	 }
	 
	// @Test
		public void Login_02_Invalid_Email_And_Valid_Password() {
			 
			    driver.get("http://live.techpanda.org/");
			 
			    // Click vào My Account
				driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(2);
				
				//Nhập email không hợp lệ
				driver.findElement(By.id("email")).sendKeys("12341234@1234.1234");
				
				//Nhập password hợp lệ
				driver.findElement(By.id("pass")).sendKeys("123456");
				sleepInSecond(2);
				
				// Click button Login
				driver.findElement(By.id("send2")).click();
				
				// So sánh message trả về ở email 
				Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email"))
				.getText(), "Please enter a valid email address. For example johndoe@domain.com.");
				
		 }
	 
	// @Test
		public void Login_03_Valid_Email_And_Invalid_Password() {
			 
			    driver.get("http://live.techpanda.org/");
			    
			    // Click vào My Account
				driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(2);
				
				// Nhập email hợp lệ
				driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
				
				// Nhập password không hợp lệ
				driver.findElement(By.id("pass")).sendKeys("123");
				sleepInSecond(2);
				
				// Click button Login
				driver.findElement(By.id("send2")).click();
				
				// So sánh message trả về ở password
				Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass"))
				.getText(), "Please enter 6 or more characters without leading or trailing spaces.");
				
		 }
	
	// @Test
		public void Login_04_Incorrect_Email_And_Invalid_Password() {
			 
			    driver.get("http://live.techpanda.org/");
			    
			    // Click vào My Account
				driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(2);
				
				//Nhập email incorrect (email chưa từng tồn tại trong hệ thống)
				driver.findElement(By.id("email")).sendKeys(emailAddress); // Email random 
				
				//Nhập password invalid
				driver.findElement(By.id("pass")).sendKeys("123123123");
				sleepInSecond(2);
				
				// Click button Login
				driver.findElement(By.id("send2")).click();
				
				// So sánh message trả về ở messages
				Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
					
		 }
	 
	// @Test
		public void Login_05_Create_New_Account() {
			 
			    driver.get("http://live.techpanda.org/");
			   
			    // Click vào My Account
				driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(2); 
	        
			    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			    sleepInSecond(2); 
                // Nhập dữ liệu
			
			    driver.findElement(By.id("firstname")).sendKeys(firstName);
			    driver.findElement(By.id("lastname")).sendKeys(lastName);
			    driver.findElement(By.id("email_address")).sendKeys(emailAddress);
			    driver.findElement(By.id("password")).sendKeys(password);
			    driver.findElement(By.id("confirmation")).sendKeys(password);
			    
			    // CLick button đăng ký
			    driver.findElement(By.xpath("//button[@title='Register']")).click();
			
			    // Xác nhận với message trả về
			    driver.findElement(By.cssSelector("li.success-msg span")).getText();
			    Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span"))
			    .getText(),"Thank you for registering with Main Website Store.");
	       
			    // Tìm các thành phần trong Contact information
			    String contactInformationText =  driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	            System.out.println(contactInformationText);
	        
	            // So sánh giá trị có đúng không
	            Assert.assertTrue(contactInformationText.contains(fullName));
	            Assert.assertTrue(contactInformationText.contains(emailAddress));
            
	            // Click log out
	            driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
	            driver.findElement(By.xpath("//a[@title='Log Out']")).click(); 
	        
	            // So sánh giá trị hiển thị ở trang main
	            Assert.assertTrue(driver.findElement(By.cssSelector("div.main-container div.page-title")).isDisplayed());
	 }
	 
	// @Test
		public void Login_06_Login_Valid_Info() {
			 
			    driver.get("http://live.techpanda.org/");
			    
			    // Click vào My Account
				driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
				sleepInSecond(2); 
	        
			    //driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			    //sleepInSecond(2); 
			
			    driver.findElement(By.id("email")).sendKeys(emailAddress);
			    
			    //Nhập password 
			    driver.findElement(By.id("pass")).sendKeys(password);
			    sleepInSecond(2);
			    
			    // Click button Login
			    driver.findElement(By.id("send2")).click();
			
			    String contactInformationText =  driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	            System.out.println(contactInformationText);
	        
	            // So sánh giá trị có đúng không
	            Assert.assertTrue(contactInformationText.contains(fullName));
	            Assert.assertTrue(contactInformationText.contains(emailAddress));    
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