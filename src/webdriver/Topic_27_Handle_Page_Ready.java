package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_27_Handle_Page_Ready {
	WebDriver driver;
	WebDriverWait  explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new FirefoxDriver(); // không dùng được Chrome do chỉ hỗ trợ version 113
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
	}

	//@Test 
	public void TC_01_Orange_HRM_API() {
		driver.get("https://api.orangehrm.com/");
		
		// Wait cho icon loading biến mất
		// Vì khi nó biến mất dữ liệu mới được load thành công
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");	
	}

	//@Test
	public void TC_02_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Password")).clear();
		
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		// Click chuyển trang từ Login vào Dashboard
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		// Chờ AjaxBusyLoading biến mất, nên mong muốn nó là True
		Assert.assertTrue(waitForAjaxBusyLoadingInvisible());
		
		// Wait cho đến khi nào nút Logout có thể click được
		// explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));
		
		// Click chuyển trang từ Dashboard về Login
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		// Assert.assertTrue(waitForAjaxBusyLoadingInvisible());
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
	}
	@Test 
		public void TC_03_Test_Project() {
			driver.get("https://blog.testproject.io/");
			
			// Hover chuột vào 1 element bất kỳ tại page này để cho page ready
			action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
			
			Assert.assertTrue(isPageLoadedSuccess());
			
			// Tương tác lên search Textbox
			String keyword = "Selenium";
			driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
			
			// Click vào search 
			driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
			
			// Wait cho Page heading visible
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[@class='main-heading' and text()='Search Results']")));
			Assert.assertTrue(isPageLoadedSuccess());
			
			// Verify
			List<WebElement> postArticle = driver.findElements(By.cssSelector("h3.post-title>a"));
			
			// Chạy vòng lặp để duyệt qua các postArrticle
			// So sánh đúng nếu các postArrticle trả về giá trị chứa cointains là keyword
			for (WebElement article : postArticle) {
				Assert.assertTrue(article.getText().contains(keyword));
			}	
		}
	
	// hoặc cách 2 Wait cho đến khi icon loading biến mất
	public boolean waitForAjaxBusyLoadingInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	}
	
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				// Khi load xong trang sẽ trả về giá trị complete
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
		
	}
}