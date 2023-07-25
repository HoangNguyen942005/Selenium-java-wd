package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, day, month, year;
	String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
        
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		firstName = "Hoang";
		lastName = "Akai";
		emailAddress = "hoangnn" + getRandomNumber() + "@gmail.com";
		companyName = "HoangnnCompany";
		password = "H1congacon";
		day  = "18";
		month  = "May";
		year  = "1990";
		
		countryName = "United States"; 
		provinceName = "Alaska";
		cityName = "City123";
		addressName = "123LBB";
		postalCode = "333666";
		phoneNumber = "30122432211";

	}
	 @Test
	 public void TC_01_Register_New_Account() {
		 
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//chọn ngày 7 bằng index
		//select.selectByIndex(7); không hay dùng
		
		//chọn ngày 14 bằng value
		//select.selectByValue("14"); không hay dùng
		
		//chọn ngày 18 bằng text
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		// Click vào My account
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		
		// Đăng nhập vào lại
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		// So sánh
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		// GetFirstSelectedOption : giá trị đầu tiên hiển thị 
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day); 
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
	 }
	 
	 @Test
		public void TC_02_add_address() {
			 driver.findElement(By.xpath("//div[@class='listbox']//a[text()='Addresses']")).click();
			 driver.findElement(By.cssSelector("button.add-address-button")).click();
			 
			 driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
			 driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
			 driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
			 driver.findElement(By.id("Address_Company")).sendKeys(companyName);
			 
			 new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);// dropdown default nên dùng hàm Select
			 new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
			 
			 driver.findElement(By.id("Address_City")).sendKeys(cityName);
			 driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
			 driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
			 driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
			 
			 driver.findElement(By.cssSelector("button.save-address-button")).click();
			 
			 Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName + " " + lastName); // nằm trên cùng 1 hàng
             Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress)); // nằm trên nhiều hàng 
             Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
             Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(),companyName );
             Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(),addressName );
             Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(cityName));
             Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(provinceName));
             Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalCode));
             Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(),countryName );
		 }
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	 public int getRandomNumber() {
		  Random rand = new Random();
		  return rand.nextInt(99999);	 
	 }
	 
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}