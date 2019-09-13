package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class SiteListTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(SiteListTest.class);
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
	@Description("verify_sitelist")
	public void verify_sitelist(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnSiteListLink();
		
		assertEquals(siteListPage.verifySiteListTitle(), "Site List", "Verify Site List page title");	
		assertTrue(siteListPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(siteListPage.verifyShipToDropdown(), "Verify Ship To Dropdown");
		assertTrue(siteListPage.verifySearchButton(), "Verify Download Button");
		assertTrue(siteListPage.verifyDownloadButton(), "Verify Download Button");
		assertTrue(siteListPage.verifyAddRemoveColumnsButton(), "Verify Add Remove Columns Button");
		assertTrue(siteListPage.verifySiteListTable(), "Verify Site List Table");
		assertAll();
	}
}
