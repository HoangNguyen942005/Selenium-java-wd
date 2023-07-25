package webdriver;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	Alert alert;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String authenChrome = projectPath + "\\autoIT\\authen_chrome.exe";
	String authenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";
	String osName = System.getProperty("os.name");
	String username = "admin";
	String password = "admin";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new ChromeDriver();

		// Luôn khởi tạo sau biến driver này
		expliciWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	// https://docs.google.com/document/d/1kPgRirztWIC9R_XiZFNYI3E0KVWfrzf2x_Het5MRj3s/edit#heading=h.zg8vyw1whenb
	// @Test
	 public void TC_01_Accept_Alert() {
		 driver.get("https://automationfc.github.io/basic-form/");
			
	     driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	     sleepInSecond(2);
	     
	     // Dùng swithchTo để làm việc với các thành phần không có trong Element
	     // Wait cho đến khi alert xuất hiện và switch qua luôn
	     alert = expliciWait.until(ExpectedConditions.alertIsPresent());
	     
	     // Verify alert title đúng như mong đợi
	     Assert.assertEquals(alert.getText(), "I am a JS Alert");
	     
	     // Acccept cái Alert này
	     alert.accept();
	    
	     // Verify sau khi accept alert
	     Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");  
	 }
		 
	// @Test
		public void TC_02_Confirm_Alert() {
		 driver.get("https://automationfc.github.io/basic-form/");
			
	     driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	     sleepInSecond(2);
	     
	     // Dùng swithchTo để làm việc với các thành phần không có trong Element
	     // Wait và switch qua luôn
	     alert = expliciWait.until(ExpectedConditions.alertIsPresent());
	     
	     // Verify alert title đúng như mong đợi
	     Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	     
	     // Cancel cái Alert này
	     alert.dismiss();
	     sleepInSecond(3);
	     Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel"); 
	 }
	 
	// @Test
		public void TC_03_Prompt_Alert(){
		 driver.get("https://automationfc.github.io/basic-form/");
			
	     driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	     sleepInSecond(2);
	     
	     // Dùng swithchTo để làm việc với các thành phần không có trong Element
	     // Wait và switch qua luôn
	     alert = expliciWait.until(ExpectedConditions.alertIsPresent());
	     
	     // Verify alert title đúng như mong đợi
	     Assert.assertEquals(alert.getText(), "I am a JS prompt");
	     
	     String courseName = "Fullstack Selenium Java";
	     
	     // Nhập text vào Alert này
	     alert.sendKeys(courseName);
	     sleepInSecond(3);
	     
	     // Acccept cái Alert này
	     alert.accept();
	     sleepInSecond(1);
	     Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courseName); 
	 }
	 
	// @Test
		public void TC_04_Authentication_Alert_I(){
		   driver.get("https://the-internet.herokuapp.com/");
		   
		   String authenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		   // Truyền trực tiếp Username, password vào url này --> tự động Sign In luôn
		   // http:// + Username : Password @ the-internet.herokuapp.com/basic_auth
		   driver.get(passUserAndPassToUrl(authenUrl, "admin", "admin"));
		 
		   Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
			.isDisplayed(), "Congratulations! You must have the proper credentials.");   
	 }
	
	  @Test
		public void TC_05_Authentication_Alert_II() throws IOException{
		  
		  if (driver.toString().contains("firefox")) {
			  // Runtime.getRuntime().exec : thực thi 1 file exe trong code Java
			  Runtime.getRuntime().exec(new String[] {authenFirefox, username, password});
		} else if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {authenChrome, username, password});
		}
		  
		   driver.get("https://the-internet.herokuapp.com/basic_auth");   
		   
		   Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
		   .isDisplayed(), "Congratulations! You must have the proper credentials.");  
	 }
		
	public String passUserAndPassToUrl (String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];	
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