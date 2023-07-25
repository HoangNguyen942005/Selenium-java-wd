package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_Part_2 {
	WebDriver driver;
	Actions action; // khai báo hàm để thao tác với chuột
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	// @Test
	public void TC_01_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Đang chứa 12 số/ item trong list này
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// Click vào số 1 (source)
		action.clickAndHold(listNumber.get(0))
		
		// Di chuột tới target cần 
		.moveToElement(listNumber.get(7))
				
		// Nhả chuột trái ra
		.release()
		
		// Execute
		.perform();
		
		sleepInSecond(3);
		
		// Sau khi chọn xong thì cho các giá trị được chọn vào 1 list
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		
		// Verify có 8 giá trị được chọn từ list
		Assert.assertEquals(listSelectedNumber.size(), 8);
		 
	}

	 @Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Chạy được cho cả MAC/ Windows
		Keys key = null;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		  
		// Đang chứa 12 số/ item trong list này
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// Nhấn Ctrl xuống
		action.keyDown(key.CONTROL).perform();
		
		// Click chọn các số random
		action.click(listNumber.get(0))
		.click(listNumber.get(3))
		.click(listNumber.get(5))
		.click(listNumber.get(10)).perform();
		
		// Nhả phím Ctrl ra
		action.keyUp(key.CONTROL).perform();
		
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 4);
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