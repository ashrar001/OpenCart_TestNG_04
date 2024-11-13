package testCase;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPageValidating;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_DDTest extends BaseClass{
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups="Sanity")
	public void verify_login_DDT(String email,String pass,String exp) {
		logger.info("******** starting TC003_DDTest *********");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.setEamil(email);
		lp.setPassword(pass);
		lp.clickButton();
		MyAccountPageValidating mp=new MyAccountPageValidating(driver);
		boolean targetPage=mp.isMyAccountPageIsExist();
		if(exp.equalsIgnoreCase("vAlId")) {  //(1)- if data is valid
			if(targetPage==true) {       //(2)- login is sucessfull
				mp.clickLogoutLink();      //(4)- logout from page
				Assert.assertTrue(true);   //(3)- test is pass
			}
			if(targetPage=false) {         //login is unsucessfull
				Assert.assertTrue(false);   //test is fail
			}
		}
		if(exp.equalsIgnoreCase("InVaLiD")) {
			if(targetPage==true) {
				mp.clickLogoutLink();
				Assert.assertTrue(false);
			}
			if(targetPage==false) {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("******** finish TC003_DDTest *********");
	}

}
