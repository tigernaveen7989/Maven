package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class CarrierDriverLastLiftTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(CarrierDriverLastLiftTest.class);
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
	@Description("verify_carrierdriverlastlift")
	public void verify_carrierdriverlastlift(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnCarrierDriverLastLiftLink();
		
		assertEquals(carrierDriverLastLiftPage.getTitle(), "Carrier Driver Last Lift", "Verify Carrier Driver Last Lift Page title");
		assertTrue(carrierDriverLastLiftPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(carrierDriverLastLiftPage.verifyTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(carrierDriverLastLiftPage.verifyCarrierDropdown(), "Verify Carrier Dropdown");
		assertTrue(carrierDriverLastLiftPage.verifyDownloadButton(), "Verify Download Button");
		assertTrue(carrierDriverLastLiftPage.verifySearchButton(), "Verify Search Button");
		assertTrue(carrierDriverLastLiftPage.verifyAddRemoveColumnsButton(), "Verify Add Remove Columns Button");
		assertTrue(carrierDriverLastLiftPage.verifyCarrierDriverLastLiftTable(), "Verify Carrier Driver Last Lift Table");
		assertAll();
	}
}
