package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.server.handler.interactions.SendKeyToActiveElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Javascript_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test 
	public void TC_01_Tech_Panda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		
		// Get domain của page
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");
		
		// Click vào Mobile
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		// Click vào Add to cart
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(3);
		
		// Kiểm tra message
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		// Click vào Customer Service
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		
		// Scroll tới cuối trang
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(3);
		
		// Nhập email vào new letter và click 
		sendkeyToElementByJS("//input[@id='newsletter']", "afc" + getRandomNumber() + "@gmail.net");
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		
		// Verify message hiển thị ra
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		// Chuyển qua domain khác
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(5);
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
	}
	
	@Test 
	public void TC_02_HTML5_Validation_Message() {
		driver.get("https://warranty.rode.com/register");
		
		String registerButton = "//button[(text()=' Register ')]";
		String NameTextbox = "//input[@id='name']";
		String EmailTextbox = "//input[@id='email']";
		String passwordTextbox = "//input[@id='password']";
		String confirmpasswordTextbox = "//input[@id='password_confirmation']";
		
		// Click vào Register
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(NameTextbox), "Please fill out this field.");
		
		// Nhập vào Name
		sendkeyToElementByJS(NameTextbox, "Automation Test");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(EmailTextbox), "Please fill out this field.");
		
		// Nhập vào 1 Email sai
		sendkeyToElementByJS(EmailTextbox, "auto@test@gmail.com");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(EmailTextbox), "A part following '@' should not contain the symbol '@'.");
		
		// Nhập vào 1 email đúng
		sendkeyToElementByJS(EmailTextbox, "autotest@gmail.com");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(passwordTextbox), "Please fill out this field.");
		
		// Nhập vào password
		sendkeyToElementByJS(passwordTextbox, "123456789");
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(confirmpasswordTextbox), "Please fill out this field.");	
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
    // Lấy ra Domain
	public String getDomainName() {
		return (String) jsExecutor.executeScript("return document.domain;");	
	}
	// Lấy ra Text
	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}
    // Cuộn xuống cuối trang
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
    // Chuyển sang 1 URL khác
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}
    // Hight light vào Element đang thao tác
	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
    // Click vào Element
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}
    // Cuộn lên đầu Element
	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}
	// Nhập dữ liệu vào
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	
	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
    // Tìm Locator
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