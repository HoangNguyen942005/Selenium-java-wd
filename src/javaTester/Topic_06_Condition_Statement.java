package javaTester;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Condition_Statement {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
//	public static void main(String[] args) {
//
//		boolean status = 5 > 3;
//		System.out.println(status);
//		
//		// Hàm if sẽ nhận vào 1 điều kiện đúng
//		// Kiểm tra duy nhất 1 điều kiện
//		// Nếu điều kiện đúng thì sẽ action trong phần thân
//		if (status) {
//			// Đúng thì vào phần thân của if
//			// Sai thì bỏ qua
//			System.out.println("Go to if");
//			
//		}
//		
//		// Gán (assign)
//				int studentNumber = 10;
//				
//				// == So sánh
//				status = (studentNumber != 10);
//				System.out.println(status);
//
//		WebDriver driver = new FirefoxDriver();
//		
//		// Element luôn luôn có trong DOM dù popup hiển thị hay không
//		WebElement  salePopup = driver.findElement(By.id(""));
//		
//		if (salePopup.isDisplayed()) {
//			
//		}
//		
//		// Element ko có trong DOM khi popup ko hiển thị
//		List<WebElement> salePopups = driver.findElements(By.id(""));
//		
//		// Check element ko hiển thị 
//		if (salePopups.size() > 0 && salePopups.get(0).isDisplayed()) {
//			
//		}
//		
//		// Uncheck to checkbox
//		WebElement languageCheckbox = driver.findElement(By.id(""));
//		if (languageCheckbox.isSelected()) {
//			languageCheckbox.click();
//		}
//		
//		// Check to checkbox
//		if (!languageCheckbox.isSelected()) { // ! để phủ định lại điều kiện
//			languageCheckbox.click();
//		}
//		
//	}
	
//	@Test 
	public void TC_02_If_Else() {
		// Có tới 2 điều kiện : nếu như đúng vào if - sai thì vào else
		
		// Nếu driver start vs browser như Chrome/ Firefox/ Edge/ Safari thì dùng hàm click
		// thông thường (builtin) của Selenium WebElement
		
		// Nếu driver là IE thì dùng hàm click của JavascriptExecutor
		System.setProperty("webdriver.ie.driver", projectPath + "\\\\browserDrivers\\\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		if (driver.toString().contains("internet explorer")) {
			System.out.println("Click by Javascript Executor");
		} else {
			System.out.println("Click by Selenium WebElement");
		}
	}
	@Parameters("browser")
	//@Test 
	public void TC_03_If_Else_If_Else(String browserName) {
       if (browserName.equalsIgnoreCase("chrome")) {
    	   System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
   		   driver = new ChromeDriver();
	} else if (browserName.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	} else if (browserName.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		driver = new FirefoxDriver();
	} else { // Safari / Opera/ Cốc cốc...1
		throw new RuntimeException("Please input correct the browser name !");

	}
	
  }
	
	@Test
	public void TC_04_If_Else_If_Else() {
		// Page Object
		// Dynamic Page
		
		String pageName = "Login";
		
		if (pageName.equals("Login")) {
			// Loginpage loginpage = new LoginPage();
			// return loginPage
		} else if (pageName.equals("Register")) {
			// RegisterPage registerPage = new RegisterPage();
			// return registerPage;
		} else if (pageName.equals("New Customer")) {
			 // HomePage homePage = new CustomerPage();
			// return CustomerPage;
		} else {
            // HomePage homePage = new HomePage();
			// return homePage;
		}
		
		
		// If - Else viết gọn
		int age = 20;
		// ? true : false
		String access = (age < 18) ? "You can not access" : "Welcome to our system!";
	}
}
