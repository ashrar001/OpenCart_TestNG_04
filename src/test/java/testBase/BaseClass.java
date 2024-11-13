package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public WebDriver driver;
    public Logger logger;
    public Properties p;
	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String o,String b) throws IOException {
		logger = LogManager.getLogger(this.getClass());
		switch(b.toLowerCase()) {
		case "chrome" :driver = new ChromeDriver();break;
		case "edge"   :driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default:
			System.out.println("please enter valid browser name");
			return;
		}
		
		
		FileInputStream fis=new FileInputStream("./src//test//resources//config.Properties");
		p=new Properties();
		p.load(fis);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to(p.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
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
		return ("%!"+passwordAlpah + "*$" + passwordNumeric);
	}
}
