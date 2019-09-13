package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;


public class RackPricesTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(RackPricesTest.class);
	/**
	 * @description getDriver from BaseTest
	 */
	@BeforeMethod
	public void setUp() {
		super.setUp();
	}
		
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = {"regression"})
	@Description("verify_search_using_terminal")
	public void verify_search_using_terminal(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		String checkBoxValue;
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnRackPricesLink();
		
		assertEquals(rackPricesPage.verifyTitle(), "Rack Prices", "Verify Rack Prices Page title");
		rackPricesPage.clickOnTerminalDropDown();
		checkBoxValue = rackPricesPage.clickOnTermialDropDownCheckBox1();
		rackPricesPage.clickOnTerminalDropDown();
		rackPricesPage.clickOnSearchButton();
		rackPricesPage.verifyRackPricesRowCount();
		rackPricesPage.verifyTerminalColumnValues(checkBoxValue);
		
		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = {"sanity"})
	@Description("verify_rackprices")
	public void verify_rackprices(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnRackPricesLink();
		
		assertEquals(rackPricesPage.verifyTitle(), "Rack Prices", "Verify Rack Prices Page title");
		assertTrue(rackPricesPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(rackPricesPage.verifyTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(rackPricesPage.verifyProductDropdown(), "Verify Product Dropdown");
		assertTrue(rackPricesPage.verifyDownloadButton(), "Verify Download Button");
		assertTrue(rackPricesPage.verifySearchButton(), "Verify Search Button");
		assertTrue(rackPricesPage.verifyFromCalenderIcon(), "Verify From calender icon");
		assertTrue(rackPricesPage.verifyToCalenderIcon(), "Verify To calender icon");
		assertTrue(rackPricesPage.verifyAddRemoveColumnsButton(), "Verify Add Remove Columns Button");
		assertTrue(rackPricesPage.verifyRackPricesTable(), "Verify Rack Prices Table");
		assertAll();
	}
}
