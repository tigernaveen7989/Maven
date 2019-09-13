package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class InvoicedVolumesTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(InvoicedVolumesTest.class);
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
	@Description("verify_invoicedvolumes")
	public void verify_invoicedvolumes(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnInvoicedVolumsLink();
		
		assertEquals(invoicedVolumesPage.verifyTitle(), "Invoiced Volumes", "Verify Invoiced Volumes Page title");
		assertTrue(invoicedVolumesPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(invoicedVolumesPage.verifyCompanyDropdown(), "Verify Company Dropdown");
		assertTrue(invoicedVolumesPage.verifyShipToDropdown(), "Verify Shipto Dropdown");
		assertTrue(invoicedVolumesPage.verifyDownloadButton(), "Verify Download Button");
		assertTrue(invoicedVolumesPage.verifySearchButton(), "Verify Search Button");
		assertTrue(invoicedVolumesPage.verifyInvoicedVolumesTable(), "Verify Invoiced Volumes Table");
		assertTrue(invoicedVolumesPage.verifyShowFiltersLink(), "Verify Show Filters Link");
		assertAll();
	}
}
