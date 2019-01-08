package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{
	public static @FindBy(xpath="//input[@name='firstName']") WebElement FirstName_EditBox;
	public static @FindBy(xpath="//input[@name='lastName']") WebElement LastName_EditBox;
	public static @FindBy(xpath="//input[@name='phone']") WebElement Phone_EditBox;
	public static @FindBy(xpath="//input[@id='userName']") WebElement Email_EditBox;
	public static @FindBy(xpath="//input[@name='address1']") WebElement Address_EditBox;
	public static @FindBy(xpath="//input[@name='city']") WebElement City_EditBox;
	public static @FindBy(xpath="//input[@name='state']") WebElement State_EditBox;
	public static @FindBy(xpath="//input[@name='postalCode']") WebElement PostalCode_EditBox;
	public static @FindBy(xpath="//select[@name='country']") WebElement Country_DropDown;
	public static @FindBy(xpath="//input[@id='email']") WebElement UserName_EditBox;
	public static @FindBy(xpath="//input[@name='password']") WebElement Password_EditBox;
	public static @FindBy(xpath="//input[@name='confirmPassword']") WebElement ConfirmPassword_EditBox;
	public static @FindBy(xpath="//input[@name='register']") WebElement Submit_Button;
	public static @FindBy(xpath="/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a") WebElement Register_Link;
	
	public RegisterPage() {
		super();
	}
	
	public static RegisterPage getNewToursDemoHomePage() throws Exception{
		getURL("http://newtours.demoaut.com/mercurywelcome.php");
		return new RegisterPage();
	}
	
	public static RegisterPage clickOnRegisterLink() throws Exception{
		clickElement(Register_Link);
		return new RegisterPage();
	}
	
	public static RegisterPage enterUserName(String userName) throws Exception {
		enterValue(UserName_EditBox, userName);
		return new RegisterPage();
	}
	
	public static RegisterPage enterFirstName(String firstName) throws Exception {
		enterValue(FirstName_EditBox, firstName);
		return new RegisterPage();
	}
	
	public static RegisterPage enterLastName(String lastName) throws Exception {
		enterValue(LastName_EditBox, lastName);
		return new RegisterPage();
	}
	
	public static RegisterPage enterPhone(String phone) throws Exception {
		enterValue(Phone_EditBox, phone);
		return new RegisterPage();
	}
	
	public static RegisterPage enterEmail(String email) throws Exception {
		enterValue(Email_EditBox, email);
		return new RegisterPage();
	}
	
	public static RegisterPage enterAddress(String address) throws Exception {
		enterValue(Address_EditBox, address);
		return new RegisterPage();
	}
	
	public static RegisterPage enterCity(String city) throws Exception {
		enterValue(City_EditBox, city);
		return new RegisterPage();
	}
	
	public static RegisterPage enterState(String state) throws Exception {
		enterValue(State_EditBox, state);
		return new RegisterPage();
	}
	
	public static RegisterPage enterPostalCode(String postalCode) throws Exception {
		enterValue(PostalCode_EditBox, postalCode);
		return new RegisterPage();
	}
	
	public static RegisterPage enterPassword(String password) throws Exception {
		enterValue(Password_EditBox, password);
		return new RegisterPage();
	}
	
	public static RegisterPage enterConfirmPassword(String confirmPassword) throws Exception {
		enterValue(ConfirmPassword_EditBox, confirmPassword);
		return new RegisterPage();
	}
	
	public static RegisterPage selectCountry(String country) throws Exception{
		selectElement(Country_DropDown, country);
		return new RegisterPage();
	}
	
	public static RegisterPage clickOnSubmitButton() throws Exception {
		clickElement(Submit_Button);
		return new RegisterPage();
	}
}
