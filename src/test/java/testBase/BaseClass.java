package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void tearDown() {
		driver.manage().window().minimize();
		driver.quit();
	}

	public String randomString() {
		String randomString = RandomStringUtils.randomAlphabetic(6);
		return randomString;
	}

	public String randomNumber() {
		String number = RandomStringUtils.randomNumeric(10);
		return number;
	}

	public String randomAlphaNumeric() {
		String passwordAlpah = RandomStringUtils.randomAlphabetic(4);
		String passwordNumeric = RandomStringUtils.randomNumeric(4);
		return (passwordAlpah + "*$" + passwordNumeric);
	}
}
