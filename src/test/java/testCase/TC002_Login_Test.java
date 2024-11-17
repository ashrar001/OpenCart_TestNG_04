package testCase;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPageValidating;
import testBase.BaseClass;

public class TC002_Login_Test extends BaseClass{
	
@Test(groups= "Sanity")
 public void verify_login() {
	 logger.info("******* Staring TC002_Login_Test *********");
	 
	 try {
	 HomePage hp=new HomePage(driver);
	 
	 hp.clickMyAccount();
	 hp.clickLogin();
	 
	 LoginPage lp=new LoginPage(driver);
	 
	 lp.setEamil(p.getProperty("email"));
	 lp.setPassword(p.getProperty("pass"));
	 lp.clickButton();
	 
	 MyAccountPageValidating mp=new MyAccountPageValidating(driver);
	 boolean targetPage=mp.isMyAccountPageIsExist();
	 Assert.assertTrue(targetPage);
	 
}
catch(Exception e) {
	Assert.fail();
 }
	 logger.info("******** Finished TC002_Login_Test *********");
}
}