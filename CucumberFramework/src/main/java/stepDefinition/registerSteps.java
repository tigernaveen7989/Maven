package stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.RegisterPage;
import pageObjects.SearchFlightPage;
import utils.DriverFactory;

public class registerSteps extends DriverFactory{
	
	@Given("^user navigates to newtours demo website homepage$")
	public void user_navigates_to_newtours_demo_website_homepage() throws Throwable {
		RegisterPage.getNewToursDemoHomePage();
	}
	
	@And("^click on register link$")
	public void click_on_register_link() throws Exception{
		RegisterPage.clickOnRegisterLink();
	}
	
	@And("^enter firstname as \"([^\"]*)\"$")
	public void enter_firstname_as_firstname(String firstName) throws Throwable {
		RegisterPage.enterFirstName(firstName);
	}
	
	@And("^enter lastname as \"([^\"]*)\"$")
	public void enter_lastname_as_lastname(String lastName) throws Throwable {
		RegisterPage.enterLastName(lastName);	  
	}
	
	@And("^enter email as \"([^\"]*)\"$")
	public void enter_email_as_email(String email) throws Throwable {
		RegisterPage.enterEmail(email);
	}
	
	@And("^enter phone as \"([^\"]*)\"$")
	public void enter_phone_as_phone(String phone) throws Throwable {
		RegisterPage.enterPhone(phone);
	}
	
	@And("^enter address as \"([^\"]*)\"$")
	public void enter_address_as_address(String address) throws Throwable {
		RegisterPage.enterAddress(address);
	}
	
	@And("^enter city as \"([^\"]*)\"$")
	public void enter_city_as_city(String city) throws Throwable {
		RegisterPage.enterCity(city);
	}
	
	@And("^enter state as \"([^\"]*)\"$")
	public void enter_state_as_state(String state) throws Throwable {
		RegisterPage.enterState(state);
	}
	
	@And("^enter postalcode as \"([^\"]*)\"$")
	public void enter_postalcode_as_postalcode(String postalCode) throws Throwable {
		RegisterPage.enterPostalCode(postalCode);
	}
	
	@And("^select country as \"([^\"]*)\"$")
	public void select_country_as_country(String country) throws Throwable {
		RegisterPage.selectCountry(country);
	}
	
	@And("^enter username \"([^\"]*)\"$")
	public void enter_username_username(String userName) throws Throwable {
		RegisterPage.enterUserName(userName);
	}
	
	@And("^enter password \"([^\"]*)\"$")
	public void enter_password_password(String password) throws Throwable {
		RegisterPage.enterPassword(password);
	}
	
	@And("^enter confirm password as \"([^\"]*)\"$")
	public void enter_confirm_password_as_confirmpassword(String confirmPassword) throws Throwable {
		RegisterPage.enterConfirmPassword(confirmPassword);
	}
	

	@When("^click on submit button$")
	public void click_on_submit_button() throws Throwable {
		RegisterPage.clickOnSubmitButton();
	}
}
