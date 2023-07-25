package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
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

public class Topic_10_Button_Radio_Checkbox {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	// https://docs.google.com/document/d/1kPgRirztWIC9R_XiZFNYI3E0KVWfrzf2x_Het5MRj3s/edit#heading=h.zg8vyw1whenb
	// @Test
	 public void TC_01_Button() {
		 
		 driver.get("https://www.fahasa.com/customer/account/create");
		 
		 driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		 
		 By loginButton = By.cssSelector("button.fhs-btn-login"); // tìm Locator button Login
		 
		 // Verify loginButton là Disable
		 Assert.assertFalse(driver.findElement(loginButton).isEnabled()); // loginButton đang Disable nên nếu kiểm tra Enabled là sai
		 
		 // Tạo 1 String get giá trị thuộc tính màu CssValue
		String loginButtonBackground =  driver.findElement(loginButton).getCssValue("background-image");
		
		//Kiểm tra Background của Button có đúng là chứa contains là rgb 
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		System.out.println(loginButtonBackground); 
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("hoang2005944@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		sleepInSecond(2);
		
		 // Verify loginButton là Enabled
		
		 Assert.assertTrue(driver.findElement(loginButton).isEnabled()); // loginButton đang Enabled nên nếu kiểm tra Enabled là đúng
		 
		 loginButtonBackground =  driver.findElement(loginButton).getCssValue("background-color");
			
		 Color loginButtonBackgroundColour = Color.fromString(loginButtonBackground);
		 
		 //Kiểm tra màu Background của Login Button theo hệ màu Hex có đúng, topUpperCase là để viết hoa
		 Assert.assertEquals(loginButtonBackgroundColour.asHex().toUpperCase(), "#C92127");
			
		 System.out.println(loginButtonBackground); 
			
	 }
		 
	 //@Test
		public void TC_02_Default_Checkbox_Radio_Single() {
			 driver.get("https://automationfc.github.io/multiple-fields/");
			 
			 // Click chọn 1 checkbox
			 driver.findElement(By.xpath("//label[contains(text(),' Diabetes ')]/preceding-sibling::input")).click();
			 
			// Click chọn 1 radio button
			 driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).click();
			 
			 // Verify các checkbox/ Radio đã chọn
			 Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),' Diabetes ')]/preceding-sibling::input")).isSelected());
			 Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).isSelected());
		 
			 // Checkbox có thể bỏ chọn --> không hiển thị nữa
			 driver.findElement(By.xpath("//label[contains(text(),' Diabetes ')]/preceding-sibling::input")).click();
			 Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),' Diabetes ')]/preceding-sibling::input")).isSelected());
			 
			 // Radio không thể bỏ chọn được --> không thay đổi trạng thái
			 driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).click();
			 Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).isSelected());	 
	 }
	 
	// @Test
		public void TC_03_Default_Checkbox_Radio_Multiple(){
		     driver.get("https://automationfc.github.io/multiple-fields/");
		     
		     // Tìm Locator của tất cả giá trị trong checkbox và đưa vào 1 list  
		     List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		     
	     /* // Dùng vòng lặp để duyệt qua và click vào tất cả checkbox
		     for (WebElement checkbox : allCheckboxes) {
				checkbox.click();
				//sleepInSecond(1);
			}
			
		     // Verify tất cả checkbox được chọn thành công
		     for (WebElement checkbox : allCheckboxes) {
					Assert.assertTrue(checkbox.isSelected());
				} */
		     
		     // Nếu gặp 1 checkbox có tên là X thì mới click
		     for (WebElement checkbox : allCheckboxes) {
					if (checkbox.getAttribute("value").equals("Hepatitis")) {
						checkbox.click();
			}	
		}
	 }
	 
	 @Test
		public void TC_04_Default_Checkbox(){
		     driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		     
		     // Chọn nó , ! phủ định điều kiện, nếu nó chưa được chọn thì click
		     if (!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
		    	 driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
			}
		     // Verify
		     Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		     // Bỏ chọn, nếu nó được chọn rồi thì click lại để bỏ chọn
		     if (driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
		    	 driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
			} 
		    // Verify
		     Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
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