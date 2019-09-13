package com.shell.markethub.integration.stepdefinition;

import com.shell.markethub.integration.pageobjects.LoginPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginPageSteps {

protected LoginPage loginPage = new LoginPage();

	@Given("^user navigates to markethub website homepage$")
	public void user_navigates_to_markethub_website_homepage() throws Throwable {
		loginPage.getMarketHubHomePage("https://akamai.markethub.shell.com/");
	}
	
	@And("^enter username as \"([^\"]*)\"$")
	public void enter_username_as_username(String userName) throws Throwable {
		loginPage.enterUserName(userName);
	}
	
	@And("^enter password as \"([^\"]*)\"$")
	public void enter_password_as_password(String password) throws Throwable {
		loginPage.enterPassword(password);	  
	}
	
	@Then("^click on login button$")
	public void click_on_login_button() throws Throwable {
		loginPage.clickOnLogin();
	}
}
