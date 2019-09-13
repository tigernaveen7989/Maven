package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class VolumesEntryTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(VolumesEntryTest.class);
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
	@Description("verify_volumesentry")
	public void verify_volumesentry(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnVolumesEntryLink();
		
		assertEquals(volumesEntryPage.verifyVolumesEntryTitle(), "Volumes Entry", "Verify Volumes Entry page title");	
		assertTrue(volumesEntryPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(volumesEntryPage.verifyMonthDropdown(), "Verify Month Dropdown");
		assertTrue(volumesEntryPage.verifyYearDropdown(), "Verify Year Dropdown");
		assertTrue(volumesEntryPage.verifyDownloadButton(), "Verify Download Button");
		assertTrue(volumesEntryPage.verifySubmitButton(), "Verify Submit Button");
		assertTrue(volumesEntryPage.verifySearchButton(), "Verify Search Button");
		assertTrue(volumesEntryPage.verifyCancelButton(), "Verify Cancel Button");
		assertTrue(volumesEntryPage.verifyVolumesEntryTable(), "Verify Volumes Entry Table");
		assertTrue(volumesEntryPage.verifySampleExcelLink(), "Verify Sample Excel Link");
		assertTrue(volumesEntryPage.verifyExcelUploadButton(), "Verify Excel Upload Button");
		assertAll();
	}
}
