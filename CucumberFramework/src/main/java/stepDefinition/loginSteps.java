package stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.LoginPage;
import pageObjects.SearchFlightPage;
import utils.DriverFactory;

public class loginSteps extends DriverFactory{

	@And("^verify login page title$")
	public void verify_login_page_title() throws Throwable {
		LoginPage.verifyTitle();
	}
	
	@Given("^enter username as \"([^\"]*)\"$")
	public void enter_username_as_username(String userName) throws Throwable {
		LoginPage.enterUserName(userName);
	}
	
	@And("^enter password as \"([^\"]*)\"$")
	public void enter_username_as_password(String password) throws Throwable {
		LoginPage.enterPassword(password);	  
	}
	
	@When("^click on signin button$")
	public void click_on_signin_button() throws Throwable {
		LoginPage.clickOnSignInButton();
	}
	
	@Then("^verify that error message your login attempt has been unsuccessfull is displayed$")
	public void verify_that_error_message_your_login_attemp_has_been_unsuccessful_is_displayed() throws Throwable {
		LoginPage.verifyErrorMessage();
	}
}
