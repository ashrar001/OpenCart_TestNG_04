package testCase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.AccountRegristration;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_Account_Regristration_Test extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void test() throws InterruptedException {
		logger.info("********* staring TC001_Account_Regristration_Test **********");
		try {
			
		
		HomePage hp = new HomePage(driver);
		logger.info(" clcik my account of home page class ");
		hp.clickMyAccount();
		logger.info(" clcik register of home page class ");
		hp.clickRegister();
		AccountRegristration ar = new AccountRegristration(driver);
		
		logger.info("fill all details of account regristration class ");
		ar.setFirstName(p.getProperty("name"));
		
		ar.setLastName(randomString().toUpperCase());
		ar.setEmail(randomString() + "@gmail.com");
		ar.setTelephone(randomNumber());
		String pass = randomAlphaNumeric();
		
		ar.setPassword(pass);
		
		ar.setConfrmPass(pass);
		ar.setCheckBox();
		ar.setButton();
		logger.info("                      validting              ");
		String confrmmsg = ar.getConfirmationMsg();
		Assert.assertEquals(confrmmsg, "Your Account Has Been Created!");
		
		}
		catch(Exception e) {
			logger.error("Test failed....");
			logger.debug("debugging...");
			logger.trace("trace label...");
			Assert.fail();
	}
		logger.info("********* Finshed TC001_Account_Regristration_Test **********");
	}
}
