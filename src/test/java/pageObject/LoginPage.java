package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
public LoginPage(WebDriver driver) {
	super(driver);
}
@FindBy(xpath = "//input[@name='email']")
WebElement emailTxt;
@FindBy(xpath = "//input[@name='password']")
WebElement passwordTxt;
@FindBy(xpath = "//input[@type='submit']")
WebElement buttonTxt;

public void setEamil(String mail) {
	emailTxt.sendKeys(mail);
}
public void setPassword(String pass) {
	passwordTxt.sendKeys(pass);
}
public void clickButton() {
	buttonTxt.click();
}
}
