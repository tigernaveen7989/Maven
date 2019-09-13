package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class ContractRecaptureTest extends USFuelsBaseTest{

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
	@Description("verify_contractrecapture")
	public void verify_contractrecapture(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		String soldTo = testData.get("soldTo").toString();
		String shipTo = testData.get("shipTo").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnContractRecaptureLink();
		
		assertEquals(contractRecapturePage.verifyContractRecaptureTitle(), "Contract Recapture", "Verify Contract Recapture page title");	
		assertTrue(contractRecapturePage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(contractRecapturePage.verifyShipToDropdown(), "Verify Ship To Dropdown");
		assertTrue(contractRecapturePage.verifyTerminalDateIcon(), "Verify Terminal Date Icon");
		assertTrue(contractRecapturePage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(contractRecapturePage.verifyAddButton(), "Verify Add Button");
		assertTrue(contractRecapturePage.verifyCalculateButton(), "Verify Calculate Button");
		assertTrue(contractRecapturePage.verifyRefreshButton(), "Verify Refresh Button");
		contractRecapturePage.selectSoldToDropdown(soldTo);
		contractRecapturePage.selectShipToDropdown(shipTo);
		contractRecapturePage.clickOnAddButton();
		assertTrue(contractRecapturePage.verifyContractRecaptureTable(), "Verify Contract Recapture Table");
		assertAll();
	}
}
