package webdriver;

import java.awt.Point;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_WebElement() {
		driver.get("https://demo.nopcommerce.com/login?ReturnUrl=%2Fcustomer%2Finfo");
		
		// Tương tác được với Element thì cần phải tìm được element đó thông qua locator của nó
		
		// By : id/ class/ name/ xpath/ css/ tagname/ linkText/ partialLinkText
		// Khi mà element được dùng lại nhiều lần => khai báo biến
		WebElement emailTextbox = driver.findElement(By.id("Email"));
		emailTextbox.isDisplayed();
		emailTextbox.clear();
		emailTextbox.sendKeys("a");
		
		// Khi dùng 1 lần => không cần
		driver.findElement(By.id("Email")).sendKeys("");
		
		WebElement element = driver.findElement(By.className(""));
		
		//Dùng cho các textbox/ textarea/ dropdown (Editable)
		// Xóa dữ liệu trước khi nhập text
		element.clear(); //*
		// Nhập liệu, nhập 1 giá trị nào đó 
		element.sendKeys(""); //**
		// Click vào các button/ checkbox/ radio/ img 
		element.click(); //**
		// tìm thuộc tính của 1 locator =>> lấy ra attribute
		element.getAttribute(""); //**
		// get thuộc tính CSs
		element.getCssValue("background-color"); //*
		
		// GUI : font/ size/ color/ location/ position
		// vị trí của element so với web (bên ngoài)
		//Point point = element.getLocation(); // ít dùng
		//point.x = 324;
		//point.y = 324;
		
		// Kích thước của element (bên trong)
		Dimension di =  element.getSize();
		di.getHeight();
		di.getWidth();
		
		System.out.println(di.height);
		System.out.println(di.width);
		// location + size
		element.getRect();
		
		// chụp hình khi TCs fail
		element.getScreenshotAs(OutputType.FILE); //*
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		// khi cần lấy ra tên thẻ HTML của Element đó >> truyền vào cho 1 locator khác
		
		element.getTagName(); //  hay dùng với id, name, css
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		String emailTextBoxTagname = driver.findElement(By.cssSelector("Email")).getTagName();
		
		driver.findElement(By.xpath("//" + emailTextBoxTagname + "[@id='Email']"));
		
		//lấy text từ Error message/ success message/ label/ header  
		element.getText(); //**
		
		// Khi nào dùng getText - getAttribute 
		// Khi cái value mình cần lấy nó nằm bên ngoài => getText
		// Khi cái value mình cần lấy nó nằm bên trong => getAttribute
		
		// dùng để verify xem 1 element hiển thị hoặc ko, 
		// dùng cho tất cả các element 
		Assert.assertTrue(element.isDisplayed()); //**
		Assert.assertFalse(element.isDisplayed());
		
		// dùng để verify xem 1 element có thao tác đươc hay ko 
		// dùng cho tất cả các element 
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// dùng để verify xe, 1 element được chọn hay chưa
		// phạm vi : checkbox/ radio button 
		Assert.assertTrue(element.isSelected()); //**
		Assert.assertFalse(element.isSelected());
		
		// các element nằm trong thể form
		// tương ứng với hành vi end user Enter
		element.submit();

		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}