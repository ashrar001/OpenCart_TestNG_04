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

	@Test
	public void test() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		AccountRegristration ar = new AccountRegristration(driver);
		Thread.sleep(3000);
		ar.setFirstName(randomString().toUpperCase());
		Thread.sleep(3000);
		ar.setLastName(randomString().toUpperCase());
		ar.setEmail(randomString() + "@gmail.com");
		ar.setTelephone(randomNumber());
		String pass = randomAlphaNumeric();
		Thread.sleep(3000);
		ar.setPassword(pass);
		Thread.sleep(3000);
		ar.setConfrmPass(pass);
		ar.setCheckBox();
		ar.setButton();
		String confrmmsg = ar.getConfirmationMsg();
		Assert.assertEquals(confrmmsg, "Your Account Has Been Created!");
	}
}
