package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class AllocationsTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(AllocationsTest.class);
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
	@Test(groups = {"sanity"})
	@Description("verify_allocations")
	public void verify_allocations(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnAllocationsLink();
		
		assertEquals(allocationsPage.verifyTitle(), "Allocations", "Verify Allocations page title");	
		assertTrue(allocationsPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(allocationsPage.verifyTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(allocationsPage.verifyProductDropdown(), "Verify Product Dropdown");
		assertTrue(allocationsPage.verifyDownloadButton(), "Verify Download Button");
		assertTrue(allocationsPage.verifyRequestLoadButton(), "Verify Request Load Button");
		assertTrue(allocationsPage.verifySearchButton(), "Verify Search Button");
		assertTrue(allocationsPage.verifyAddRemoveColumnsButton(), "Verify Add Remove Columns Button");
		assertTrue(allocationsPage.verifyAllocationsTable(), "Verify Allocations Table");
		assertTrue(allocationsPage.verifyDisclaimerNote(), "Verify Disclaimer Note");
		assertTrue(allocationsPage.verifyShowFiltersLink(), "Verify Show Filters Link");
		assertAll();
	}
}
