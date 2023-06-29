package webdriver;

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

public class Topic_06_Web_Element_Excercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	// dùng lại nhiều lần nên tạo biến cho gọn code
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#development");
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	// @Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Textbox nhập vào text và in ra console
		if(driver.findElement(emailTextbox).isDisplayed()){   //tìm emailTextbox, kiểm tra xem nó được hiển thị ko
		   driver.findElement(emailTextbox).sendKeys("Selenium WebDriver"); // được hiển thị thì nhập vào 1 senkeys
		   System.out.println("Email textbox is displayed");   // sau đó in ra 
    }   else {   // nếu sai
    	
    	 System.out.println("Email textbox is not displayed");   // in ra dòng này
    }
	 
		// / Textarea nhập vào text và in ra console
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium Grid");
			System.out.println("Education textarea is displayed");
		} else {
			
			System.out.println("Education textarea is not displayed");
		}
		
		// Radio button và in ra
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age Under 18  is displayed");
		} else {
			
			System.out.println("Age Under 18  is not displayed");
			
		}
		// Tìm Name User 5 và in ra
		if (driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Name User 5 is displayed");
			
		} else {
			System.out.println("Name User 5 is not displayed");
		}
	}

	// @Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(passwordTextbox).isEnabled()) {  // tìm password textbox  xem có thể nhập dữ liệu không
			System.out.println("Password textbox is enabled");  // nếu nhập được thì in ra dòng này
			
		} else {
			System.out.println("Password textbox is disabled");  // nếu không nhập được thì in ra dòng này
		}
		
		if (driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("Biography Text Area is enabled");
			
		} else {
			System.out.println("Biography Text Area is disabled");
		}
		
		if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email Textbox is enabled");
			
		} else {
			System.out.println("Email Textbox is disabled");
		}
		
	}

	// @Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Verify checkbox/ radio button are deselected
		
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected()); // nó trả về false nếu nó chưa được chọn (mặc định chua chọn)
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		
		// Click vào 2 element này
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		
		//Verify checkbox/ radio button are deselected
		
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected()); // vì được chọn rồi nên sẽ là True
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
		
		
		
	}
	
	@Test
	public void TC_04_Register() {
		driver = new ChromeDriver();
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("Hoang@gmail.com");
	
		By passwordTextbox = By.id("new_password");
		By signupButton = By.cssSelector("#create-account-enabled");
		
		driver.findElement(passwordTextbox).sendKeys("abc");
		driver.findElement(signupButton).click();
		sleepInSecond(3);
		
		//Verify lowercase
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-charcompleted']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		driver.findElement(signupButton).click();
		sleepInSecond(3);
		
		//Verify upcase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
			
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		driver.findElement(signupButton).click();
		sleepInSecond(3);
		
		// Verify Number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#$");
		driver.findElement(signupButton).click();
		sleepInSecond(3);
		
		
		// Verify special charater
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABCDEFGH");
		driver.findElement(signupButton).click();
		sleepInSecond(3);
		
		
		// Verify  charater >=8
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
			
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("abc123A!");
		driver.findElement(signupButton).click();
		sleepInSecond(3);
		
		//Verify full data 
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}