package webdriver;

import java.awt.Point;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait expliciWait; // wait tường minh
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
		expliciWait = new WebDriverWait(driver, 30); // khởi tạo để chạy
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// Muốn chọn item cho Speed dropdown
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(3);
		// So sánh ở đây là tìm cái Locator duy nhất có text = expectedTextItem
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Mr.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mr.");
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Mrs.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Dr.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
	} 
	  
	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropdown("div.selection.dropdown", "span.text", "Christian");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		
		selectItemInDropdown("div.selection.dropdown", "span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
		selectItemInDropdown("div.selection.dropdown", "span.text", "Matt");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
	}
	
	//@Test
	public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu>li", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu>li", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu>li", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
    }
	
	@Test
	public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
        enterAndSelectItemInDropdown("input.search", "span.text", "Angola");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");
		
		enterAndSelectItemInDropdown("input.search", "span.text", "Afghanistan");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");
		
		enterAndSelectItemInDropdown("input.search", "span.text", "Benin");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

	// Tránh lặp lại code nhiều lần thì chỉ cần gọi hàm ra để dùng
	// Đi kèm với tham số - dữ liệu trong dấu ()
    // Nếu truyền cứng 1 giá trị vào trong hàm = vô nghĩa
	// Nên define để dùng đi dùng lại
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem) {
		
//		1. Click vào 1 thẻ bất kỳ để làm sao nó xổ ra được hết các item của dropdown
		// parentCss là Locator của button khi bấm vào nó sẽ hiển thị các item của dropdown
		driver.findElement(By.cssSelector(parentCss)).click();
		
//		2. Chờ cho tất cả các item được load ra thành công
		// Phải lấy để đại diện cho tất cả item
		// Lấy đến cái thẻ chứa text
		// allItemCss là Locator chứa tất cả các giá trị trong dropdown đó
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		// Đưa hết tất cả item trong dropdown vào 1 list
		List<WebElement> speedDropdownItems =  driver.findElements(By.cssSelector(allItemCss));
		
//		3. Tìm item xem đúng cái đang cần hay không (dùng vòng lặp duyệt qua tất cả item)
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText(); // tạo biến string để get text ra
			System.out.println(itemText);
			
//		4. Kiểm tra cái text của item đúng với cái mình mong muốn
			// expectedTextItem là Locator chứa giá trị mình muốn lấy ra
			// Nếu getText của temItem trả về giá trị bằng expectedTextItem thì click vào item đó
			if (tempItem.getText().trim().equals(expectedTextItem)) {
//		5. Click vào item đó	
			tempItem.click();
			// Thoát khỏi vòng lặp không xét các item khác nữa
			break;	
			}
		}
	}
	
public void enterAndSelectItemInDropdown(String textboxCss, String allItemCss, String expectedTextItem) {
		
//		1. Nhập expected text item vào - xổ ra item matching
	    driver.findElement(By.cssSelector(textboxCss)).clear(); // xóa giá trị hiển thị mặc định lúc đầu
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		
//		2. Chờ cho tất cả các item được load ra thành công
		// Phải lấy để đại diện cho tất cả item
		// Lấy đến cái thẻ chứa text
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		//Đưa hết tất cả item trong dropdown vào 1 list
		List<WebElement> speedDropdownItems =  driver.findElements(By.cssSelector(allItemCss));
		
//		3. Tìm item xem đúng cái đang cần hay không (dùng vòng lặp duyệt qua tất cả item)
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText(); // tạo biến string để get text ra
			System.out.println(itemText);
			
//		4. Kiểm tra cái text của item đúng với cái mình mong muốn
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				sleepInSecond(1);
//		5. Click vào item đó	
			tempItem.click();
			// Thoát khỏi vòng lặp không xét các item khác nữa
			break;		
			}
		}
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}