package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
	
	public static @FindBy(xpath="//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a") WebElement SignIn_Button;
	public static @FindBy(xpath="//*[@id=\"noSlide\"]/h1") WebElement Title;
	public static @FindBy(xpath="//*[@id=\"id_gender1\"]") WebElement Title_RadioButton;
	public static @FindBy(xpath="//input[@id='customer_firstname']") WebElement FirstName_EditBox;
	public static @FindBy(xpath="//input[@name='customer_lastname']") WebElement LastName_EditBox;
	public static @FindBy(xpath="//*[@id=\"days\"]") WebElement Day_DropDown;
	public static @FindBy(xpath="//*[@id=\"months\"]") WebElement Month_DropDown;
	public static @FindBy(xpath="//*[@id=\"years\"]") WebElement Year_DropDown;
	public static @FindBy(xpath="//*[@id=\"phone_mobile\"]") WebElement Phone_EditBox;
	public static @FindBy(xpath="//input[@id='email_create']") WebElement Email_EditBox;
	public static @FindBy(xpath="//*[@id=\"SubmitCreate\"]/span") WebElement CreateAnAccount_Button;
	public static @FindBy(xpath="//*[@id=\"address1\"]") WebElement Address_EditBox;
	public static @FindBy(xpath="//*[@id=\"city\"]") WebElement City_EditBox;
	public static @FindBy(xpath="//*[@id=\"id_state\"]") WebElement State_DropDown;
	public static @FindBy(xpath="//*[@id=\"postcode\"]") WebElement PostalCode_EditBox;
	public static @FindBy(xpath="//*[@id=\"id_country\"]") WebElement Country_DropDown;
	public static @FindBy(xpath="//*[@id=\"firstname\"]") WebElement First_Name_EditBox;
	public static @FindBy(xpath="//*[@id=\"lastname\"]") WebElement Last_Name_EditBox;
	public static @FindBy(xpath="//input[@name='passwd']") WebElement Password_EditBox;
	public static @FindBy(xpath="//*[@id=\"alias\"]") WebElement Alias_EditBox;
	public static @FindBy(xpath="//*[@id=\"submitAccount\"]/span") WebElement Register_Button;
	public static @FindBy(xpath="//*[@id=\"center_column\"]/p") WebElement Welcome_Text;
	
	public RegistrationPage() {
		super();
	}
	
	public static RegistrationPage clickOnSignIn() throws Exception{
		clickElement(SignIn_Button);
		return new RegistrationPage();
	}
	
	public static RegistrationPage clickOnRegisterButton() throws Exception{
		clickElement(Register_Button);
		return new RegistrationPage();
	}
	
	public static RegistrationPage selectTitleRadioButton() throws Exception{
		clickElement(Title_RadioButton);
		return new RegistrationPage();
	}
	
	public static RegistrationPage clickOnCreateAnAccountButton() throws Exception{
		clickElement(CreateAnAccount_Button);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterAlias(String alias) throws Exception {
		enterValue(Alias_EditBox, alias);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterFirstName(String firstName) throws Exception {
		enterValue(FirstName_EditBox, firstName);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterLastName(String lastName) throws Exception {
		enterValue(LastName_EditBox, lastName);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterFirstNameInAddress(String firstName) throws Exception {
		enterValue(First_Name_EditBox, firstName);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterLastNameInAddress(String lastName) throws Exception {
		enterValue(Last_Name_EditBox, lastName);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterPhone(String phone) throws Exception {
		enterValue(Phone_EditBox, phone);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterEmail(String email) throws Exception {
		enterValue(Email_EditBox, email);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterAddress(String address) throws Exception {
		enterValue(Address_EditBox, address);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterCity(String city) throws Exception {
		enterValue(City_EditBox, city);
		return new RegistrationPage();
	}
	
	public static RegistrationPage selectState(String state) throws Exception {
		selectElement(State_DropDown, state);
		return new RegistrationPage();
	}
	
	public static RegistrationPage selectCountry(String country) throws Exception {
		selectElement(Country_DropDown, country);
		return new RegistrationPage();
	}
	
	public static RegistrationPage selectDateOfBirth(String day, String month, String year) throws Exception {
		selectElementByIndex(Day_DropDown, 4);
		selectElementByIndex(Month_DropDown, 6);
		selectElementByIndex(Year_DropDown, 10);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterPostalCode(String postalCode) throws Exception {
		enterValue(PostalCode_EditBox, postalCode);
		return new RegistrationPage();
	}
	
	public static RegistrationPage enterPassword(String password) throws Exception {
		enterValue(Password_EditBox, password);
		return new RegistrationPage();
	}

	public static RegistrationPage validateYouAreOnRegistrationPage() throws Exception{
		isDisplayed(Title);
		String text = getText(Title);
		if(text.equals("CREATE AN ACCOUNT") || text.equals("AUTHENTICATION")) {
			System.out.println("Registration Page is displaying");
		}else {
			System.out.println("Registration Page is not displaying");
		}
		return new RegistrationPage();
	}

	public static RegistrationPage validateSuccessfulRegistration() throws Exception{
		isDisplayed(Welcome_Text);
		String text = getText(Welcome_Text);
		if(text.contains("Welcome to your account")) {
			System.out.println("Registration is successful");
		}else {
			System.out.println("Registration is not successful");
		}
		return new RegistrationPage();
	}
}
