package com.shell.markethub.integration.stepdefinition;

import org.testng.Assert;

import com.shell.markethub.integration.pageobjects.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class HomePageSteps {

protected HomePage homePage = new HomePage();
	
	@And("^click on home button$")
	public void click_on_home_button() throws Throwable {
		homePage.clickOnHomeButton();
	}
	
	@And("^click on manage profile icon$")
	public void click_on_manage_profile_icon() throws Throwable {
		homePage.clickOnManageProfileIcon();
	}
	
	@Then("^verify \"([^\"]*)\" text$")
	public void verify_youareimpersonating_text(String arg1) throws Throwable {
		Assert.assertEquals(homePage.verifyYouAreImpersonatingText(), arg1);
	}
	
	@And("^click on all tab$")
	public void click_on_all_tab() throws Throwable {
		homePage.clickOnAllTab();
	}
	
	@And("^click on rack prices link$")
	public void click_on_rack_prices_link() throws Throwable {
		homePage.clickOnRackPricesLink();
	}
	
	@And("^click on bols link$")
	public void click_on_bols_link() throws Throwable {
		homePage.clickOnBOLsLink();
	}
}
