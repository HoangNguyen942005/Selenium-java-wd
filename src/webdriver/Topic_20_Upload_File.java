package webdriver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
    String emailAddress = "automation" + getRandomNumber() + "@gmail.com";
    JavascriptExecutor jsExecutor;
    
    String beachFileName = "beach1.jpg";
    String computerFileName = "computer.jpg";
    String doraemonFileName = "doraemon.jpg";
    
    String beachFilePath = projectPath + "\\uploadFile\\" + beachFileName;
    String computerFilePath = projectPath + "\\uploadFile\\" + computerFileName;
    String doraemonFilePath = projectPath + "\\uploadFile\\" + doraemonFileName;
    
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		Map<String, Integer> prefs = new HashMap<String, Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(options);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test 
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Load file lên
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(beachFilePath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(computerFilePath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(doraemonFilePath);
		sleepInSecond(1);
		
		// Verify file được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + computerFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + doraemonFileName +"']")).isDisplayed());
		
		// Click upload 
		List<WebElement> buttonload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonload) {
			button.click();
			sleepInSecond(1);
		}
		
		// Verify upload thành công (Link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + doraemonFileName + "']")).isDisplayed());
		
		// Verify upload thành công (Image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + beachFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + computerFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + doraemonFileName + "')]"));	
	}

	//@Test
	public void TC_02_Multiple_File_Per_Time() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Load file lên
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(beachFilePath + "\n" + computerFilePath + "\n" + doraemonFilePath);
		sleepInSecond(1);
		
		// Verify file được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + computerFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + doraemonFileName +"']")).isDisplayed());
		
		// Click upload 
		List<WebElement> buttonload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonload) {
			button.click();
			sleepInSecond(1);
		}
		
		// Verify upload thành công (Link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + doraemonFileName + "']")).isDisplayed());
		
		// Verify upload thành công (Image)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + beachFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + computerFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + doraemonFileName + "')]"));	
	}
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
    public int getRandomNumber() {
	    	return new Random().nextInt(99999);
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