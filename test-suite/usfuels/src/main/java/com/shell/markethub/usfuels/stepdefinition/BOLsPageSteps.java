package com.shell.markethub.usfuels.stepdefinition;

import org.testng.Assert;

import com.shell.markethub.usfuels.pageobjects.BOLsPage;
import com.shell.markethub.usfuels.pageobjects.RackPricesPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class BOLsPageSteps {

	protected BOLsPage bolsPage = new BOLsPage();

	@Then("^verify bols page title$")
	public void verify_bols_page_title() throws Throwable {
		Assert.assertEquals(bolsPage.verifyTitle(), "BOLs");
	}

	@And("^click on ship to dropdown$")
	public void click_on_ship_to_dropdown() throws Throwable {
		bolsPage.clickOnShipToDropDown();
	}

	@And("^click on ship to all dropdown checkbox$")
	public void click_on_ship_to_all_dropdown_checkbox() throws Throwable {
		bolsPage.clickOnShipToAllDropDownCheckBox();
	}

	@And("^click on from calender icon$")
	public void click_on_from_calender_icon() throws Throwable {
		bolsPage.clickOnFromCalenderIcon();
	}
	
	@And("^click on from calender prev month button$")
	public void click_on_from_calender_prev_month_button() throws Throwable {
		bolsPage.clickOnFromCalenderPrevMonthButton();
	}
	
	@And("^click on from calender day button$")
	public void click_on_from_calender_day_button() throws Throwable {
		bolsPage.clickOnFromCalenderDayButton();
	}
	
	@And("^enter bol number \"([^\"]*)\"$")
	public void enter_bol_number_bolnumber(String bolNumber) throws Throwable {
		bolsPage.enterBOLNumber(bolNumber);
	}
	
	@And("^click on bol page search button$")
	public void click_on_bol_page_search_button() throws Throwable {
		bolsPage.clickOnSearchButton();
	}
	
	@Then("^verify bols no column values \"([^\"]*)\"$")
	public void verify_bols_no_column_values_bolnumber(String bolNumber) throws Throwable {
		bolsPage.verifyBOLNoColumnValues(bolNumber);
	}
	
	@And("^select all checkbox$")
	public void select_all_checkbox() throws Throwable {
		bolsPage.selectAllCheckBox();
	}

	@And("^click on download button$")
	public void click_on_download_button() throws Throwable {
		bolsPage.clickOnDownloadButton();
	}
	
	@Then("^verify download header$")
	public void verify_download_header() throws Throwable {
		bolsPage.verifyDownloadHeader();
	}
	
	@And("^select xml radio button$")
	public void select_xml_radio_button() throws Throwable {
		bolsPage.selectXMLRadioButton();
	}
	
	@And("^click on download button1$")
	public void click_on_download_button1() throws Throwable {
		bolsPage.clickOnDownloadButton1();
	}
}
