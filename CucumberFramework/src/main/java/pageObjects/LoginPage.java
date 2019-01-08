package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	public static @FindBy(xpath="//*[@aria-label='AirAsia Log In and Member Sign Up']") WebElement Title;
	public static @FindBy(xpath="//*[@id='loginbutton']") WebElement Login_Button;
	public static @FindBy(id="username-input--login") WebElement UserName_EditBox;
	public static @FindBy(id="password-input--login") WebElement Password_EditBox;
	public static @FindBy(xpath="//*[@id=\"aaw-error\"]/div[2]") WebElement Login_Error_Message;
	
	public LoginPage() {
		super();
	}
	
	public static LoginPage verifyTitle() throws Exception{
		isDisplayed(Title);
		return new LoginPage();
	}

	public static LoginPage enterUserName(String userName) throws Exception {
		clickElement(UserName_EditBox);
		enterValue(UserName_EditBox, userName);
		return new LoginPage();
	}

	public static LoginPage enterPassword(String password) throws Exception {
		clickElement(Password_EditBox);
		enterValue(Password_EditBox, password);
		return new LoginPage();
	}

	public static LoginPage clickOnSignInButton() throws Exception{
		clickElement(Login_Button);
		Thread.sleep(5000);
		return new LoginPage();		
	}
	
	public static LoginPage verifyErrorMessage() throws Exception {
		isDisplayed(Login_Error_Message);
		String text = getText(Login_Error_Message);
		if(text.contains("Your log in attempts has been unsuccessful")) {
			System.out.println("Login is unsuccessful");
		}else {
			System.out.println("Login is successful");
		}
		return new LoginPage();
	}

}
