package webdriver;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
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

public class Topic_15_Action_Part_3 {
	WebDriver driver;
	Actions action; // khai báo hàm để thao tác với chuột
	JavascriptExecutor jsExecutor; // Import thư viện JS
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String dragAnddropHelperPath = projectPath + "\\dragAnddrop\\drag_and_drop_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver; // Khởi tạo
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	// @Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Scroll đến element Double click me
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");	 
	}

	// @Test
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	
		// Click vào Quit
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(2);
		
		// Acccept alert
		driver.switchTo().alert().accept();
		sleepInSecond(2);

		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	 }

	// @Test
	public void TC_03_Drag_And_Drop_HTML4() { // kéo và thả
		 driver.get("https://automationfc.github.io/kendo-drag-drop/");
		 
		 WebElement smallCicrle = driver.findElement(By.cssSelector("div#draggable"));
		 WebElement bigCicrle = driver.findElement(By.cssSelector("div#droptarget"));
		 
		 action.dragAndDrop(smallCicrle, bigCicrle).perform();
		 sleepInSecond(3);
		 
		 // Verify text
		 Assert.assertEquals(bigCicrle.getText(), "You did great!");
		 
		 // Verify Background Color
		 String bigCircleBackgroundColor = bigCicrle.getCssValue("background-color");
		 System.out.println(bigCircleBackgroundColor);
		 
		 Assert.assertEquals(Color.fromString(bigCircleBackgroundColor).asHex().toUpperCase(), "#03A9F4");
	}
	
//	@Test // chưa chạy được
	public void TC_04_Drag_And_Drop_HTML5() throws IOException {

		String jsHelper = getContentFile(dragAnddropHelperPath);
		
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String sourceCss = "div#column-a";
		String targetCss = "div#column-b";
		
		jsHelper = jsHelper + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		
		// Drag A to B
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		
		// Drag B to A
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
		
	}
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
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