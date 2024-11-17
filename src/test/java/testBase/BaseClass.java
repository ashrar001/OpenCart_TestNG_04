package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	@Parameters({ "os", "browser" })
	public void setup(String o, String b) throws IOException {
		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			if (o.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			} else if (o.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("no matching operating system");
				return;
			}
			switch (b.toLowerCase()) {
			case "chrome": cap.setBrowserName("chrome");break;
			case "firefox":cap.setBrowserName("FireFox");break;
			case "edge":cap.setBrowserName("MicrosoftEdge");break;
			default:System.out.println("No matching browser");return;
			}
			driver=new RemoteWebDriver(new URL("http://192.168.1.15:4444"),cap);
		}
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (b.toLowerCase()) {
			case "chrome":driver = new ChromeDriver();break;
			case "edge":driver = new EdgeDriver();break;
			case "firefox":driver = new FirefoxDriver();break;
			default:System.out.println("please enter valid browser name");return;
			}
		}

		FileInputStream fis = new FileInputStream("./src//test//resources//config.Properties");
		p = new Properties();
		p.load(fis);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to(p.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
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
		return ("%!" + passwordAlpah + "*$" + passwordNumeric);
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenShots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath; // exactly where screenshot capture path return to extendReportManager
	}
}
