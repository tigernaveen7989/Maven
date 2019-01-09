package stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.RegistrationPage;
import utils.DriverFactory;

public class registrationSteps extends DriverFactory{

	@When("^click on signin$")
	public void click_on_signin() throws Throwable {
		RegistrationPage.clickOnSignIn();
	}
	
	@And("^click on create an account button$")
	public void click_on_create_an_account_button() throws Throwable {
		RegistrationPage.clickOnCreateAnAccountButton();
	}
	
	@And("^validate you are on registration page$")
	public void validate_you_are_on_registration_page() throws Throwable {
		RegistrationPage.validateYouAreOnRegistrationPage();
	}
	
	@And("^select title radio button$")
	public void select_title_radio_button() throws Exception{
		RegistrationPage.selectTitleRadioButton();
	}
		
	@And("^enter firstname \"([^\"]*)\"$")
	public void enter_firstname_firstname(String firstName) throws Throwable {
		RegistrationPage.enterFirstName(firstName);
	}
	
	@And("^enter lastname \"([^\"]*)\"$")
	public void enter_lastname_lastname(String lastName) throws Throwable {
		RegistrationPage.enterLastName(lastName);	  
	}
	
	@And("^enter firstname in address \"([^\"]*)\"$")
	public void enter_firstname_in_address_firstname(String firstName) throws Throwable {
		RegistrationPage.enterFirstNameInAddress(firstName);
	}
	
	@And("^enter lastname in address \"([^\"]*)\"$")
	public void enter_lastname_in_address_lastname(String lastName) throws Throwable {
		RegistrationPage.enterLastNameInAddress(lastName);	  
	}
	
	@And("^select date of birth \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void select_date_of_birth_day_and_month_year(String day, String month, String year) throws Throwable {
		RegistrationPage.selectDateOfBirth(day, month, year);
	}
	
	@Given("^enter valid email \"([^\"]*)\"$")
	public void enter_valid_email_email(String email) throws Throwable {
		RegistrationPage.enterEmail(email);
	}
	
	@And("^enter phone \"([^\"]*)\"$")
	public void enter_phone_phone(String phone) throws Throwable {
		RegistrationPage.enterPhone(phone);
	}
	
	@And("^enter address \"([^\"]*)\"$")
	public void enter_address_address(String address) throws Throwable {
		RegistrationPage.enterAddress(address);
	}
	
	@And("^enter city \"([^\"]*)\"$")
	public void enter_city_city(String city) throws Throwable {
		RegistrationPage.enterCity(city);
	}
	
	@And("^enter state \"([^\"]*)\"$")
	public void enter_state_state(String state) throws Throwable {
		RegistrationPage.selectState(state);
	}
	
	@And("^enter postalcode \"([^\"]*)\"$")
	public void enter_postalcode_postalcode(String postalCode) throws Throwable {
		RegistrationPage.enterPostalCode(postalCode);
	}
	
	@And("^enter alias \"([^\"]*)\"$")
	public void enter_alias_alias(String alias) throws Throwable {
		RegistrationPage.enterAlias(alias);
	}
	
	@And("^select country \"([^\"]*)\"$")
	public void select_country_country(String country) throws Throwable {
		RegistrationPage.selectCountry(country);
	}
	
	@And("^enter the password \"([^\"]*)\"$")
	public void enter_the_password_password(String password) throws Throwable {
		RegistrationPage.enterPassword(password);
	}
	
	@When("^click on register button$")
	public void click_on_register_button() throws Throwable {
		RegistrationPage.clickOnRegisterButton();
	}
	
	@Then("^validate user is successfully registered$")
	public void validate_user_is_successfully_registered() throws Throwable {
		RegistrationPage.validateSuccessfulRegistration();
	}
}
