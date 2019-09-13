package com.shell.markethub.uam.stepdefinition;

import org.testng.Assert;

import com.shell.markethub.uam.pageobjects.SearchCustomerUserPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class SearchCustomerUserPageSteps {

	protected SearchCustomerUserPage searchCustomerUserPage = new SearchCustomerUserPage();
	
	@Then("^verify search customer user title$")
	public void verify_search_customer_user_title() throws Throwable {
		Assert.assertTrue(searchCustomerUserPage.verifyTitle(), "Search Customer User Title is displaying");
	}
	
	@And("^enter search email as \"([^\"]*)\"$")
	public void enter_search_email_as_email(String email) throws Throwable {
		searchCustomerUserPage.enterEmail(email);
	}
	
	@And("^click on search button$")
	public void click_on_search_button() throws Throwable {
		searchCustomerUserPage.clickSearchButton();
	}
	
	@Then("^verify user list table$")
	public void verify_user_list_table() throws Throwable {
		Assert.assertTrue(searchCustomerUserPage.verifyUserListTablePresent(), "User List Table is displaying");
	}
	
	@And("^click on user list checkbox1$")
	public void click_on_user_list_checkbox1() throws Throwable {
		searchCustomerUserPage.clickOnUserListCheckBox1();
	}
	
	@And("^click on impersonate button$")
	public void click_on_impersonate_button() throws Throwable {
		searchCustomerUserPage.clickOnImpersonateButton();
	}
}
