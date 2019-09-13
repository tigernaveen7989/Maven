package com.shell.markethub.usfuels.stepdefinition;

import org.testng.Assert;

import com.shell.markethub.usfuels.pageobjects.RackPricesPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class RackPricesPageSteps {

	protected RackPricesPage rackPricesPage = new RackPricesPage();
	public String checkBoxValue;

	@Then("^verify rack prices page title$")
	public void verify_rack_prices_page_title() throws Throwable {
		Assert.assertEquals(rackPricesPage.verifyTitle(), "Rack Prices");
	}

	@And("^click on terminal dropdown$")
	public void click_on_terminal_dropdown() throws Throwable {
		rackPricesPage.clickOnTerminalDropDown();
	}

	@And("^click on terminal dropdown first checkbox$")
	public void click_on_terminal_dropdown_first_checkbox() throws Throwable {
		checkBoxValue = rackPricesPage.clickOnTermialDropDownCheckBox1();
	}

	@And("^click on search button$")
	public void click_on_search_button() throws Throwable {
		rackPricesPage.clickOnSearchButton();
	}

	@Then("^verify rack prices row count$")
	public void verify_rack_prices_row_count() throws Throwable {
		rackPricesPage.verifyRackPricesRowCount();
	}

	@Then("^verify terminal column values$")
	public void verify_terminal_column_values() throws Throwable {
		rackPricesPage.verifyTerminalColumnValues(checkBoxValue);
	}
}
