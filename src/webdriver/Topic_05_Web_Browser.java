package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
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
		
		//Tương tác với Browser thì sẽ thông qua biến WebDriver driver
		// Tương tác với Element thì sẽ thông qua biển Element
	}
	@Test
	public void TC_01_() {
	    // 1- tên hàm : dùng để làm gì . close : đóng, quit : tắt, findElement : tìm đối tượng
		// 2- tham số truyền vào : khi dùng hàm này cần truyền vào dữ liệu gì để xử lý hay không (bất kỳ 1 kiểu dữ liệu gì)
		// 3- Kiểu dữ liệu trả về 
		   //3.1 Tip 1 : cứ hàm nào action thì ko trả về (click/ nhập/ accept/ hover/ refresh/back) >> void
		  // 3.2 Tip 2 : cứ hàm nào lấy dữ liệu ra thì cần trả về (get url/ title/ ID/ Window/ text/ attribute/ css/ value)
		  // - Có thể lưu vào 1 biến để sử dụng cho các step sau
		  // - Có thể sử dụng luôn (ko cần tạo biến)
		
		// Java Document 
		driver.close(); //*
		driver.quit(); //**
		
		// có thể lưu nó vào 1 biến để sử dụng cho các step sau >> dùng lại nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']")); //**
		//emailTextbox.clear();
		//emailTextbox.sendKeys("");
		
		// có thể sử dụng luôn (ko cần tạo biến)
		
		//driver.findElement(By.xpath("//button[@id='login']")).click();
		
		List<WebElement> checkboxes =  driver.findElements(By.xpath("")); //*
		
		//Mở ra 1 URL nào đó
		
		driver.get("https://www.facebook.com/"); //**
		
		// Trả về Url của page hiện tại
		
		driver.getCurrentUrl();
		
		// có thể sử dụng luôn (ko cần tạo biến)
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
		
		//Trả về Source code HTML của page hiện tại
		// verify tương đối
		driver.getPageSource();
		
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ"));
		
		// Trả về title của page hiện tại 
		Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
		
		//lấy ra được ID của Window/ Tab mà driver đang đứng (active)
		String loginWindowID = driver.getWindowHandle(); //**
		
		// lây ra ID của tất cả window/tab 
		 Set<String> allIDs = driver.getWindowHandles(); //**
		
		//Cookie/Cache
		Options opt = driver.manage();
		
		//Login thành công => lưu lại
		opt.getCookies(); //**
		
		//Testcase khác => set cookie vào lại => ko cần phải login nữa
		
		Timeouts time = opt.timeouts();
		
		// khoảng thời gian chờ cho element xuất hiện
		time.implicitlyWait(5, TimeUnit.SECONDS); //**
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		// khoảng thời gian chờ page load xong trog x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		// WebDriver API - Javascript Executor (JavascriptExecutor library)
		// Khoảng thời gian chờ script được thực thi trong vòng x giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		// 
		Window win = opt.window();
		win.fullscreen(); // ko dùng
		win.maximize(); // hay dùng //**
		
	    Navigation nav = driver.navigate(); 
	    nav.back();
	    nav.refresh();
	    nav.forward();
		
	    TargetLocator tar = driver.switchTo();
	    tar.alert();
	    tar.frame("");//**
	    tar.window("");//**
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}