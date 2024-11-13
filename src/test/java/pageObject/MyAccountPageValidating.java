package pageObject;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPageValidating extends BasePage{
	public MyAccountPageValidating(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath = "(//a[text()='Logout'])[2]")
	WebElement logout;
	
	public boolean isMyAccountPageIsExist() {
		try {
		myAccount.isDisplayed();
		return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public void clickLogoutLink() {
		logout.click();
	}

}
