package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class CarrierMaintenanceTest extends USFuelsBaseTest{

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
	@Description("verify_carriermaintenance")
	public void verify_carriermaintenance(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		String terminal = testData.get("terminal").toString();
		String carrier = testData.get("carrier").toString();
		String soldTo = testData.get("soldTo").toString();
		String terminalValue = testData.get("terminalValue").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnCarrierMaintenanceLink();
		
		assertEquals(carrierMaintenancePage.verifyCarrierMaintenanceTitle(), "Carrier Maintenance", "Verify Carrier Maintenance page title");	
		assertTrue(carrierMaintenancePage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(carrierMaintenancePage.verifyTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(carrierMaintenancePage.verifyCarrierDropdown(), "Verify Carrier Dropdown");
		assertTrue(carrierMaintenancePage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(carrierMaintenancePage.verifyShipToDropdown(), "Verify Ship To Dropdown");
		assertTrue(carrierMaintenancePage.verifySearchButton(), "Verify Search Button");
		carrierMaintenancePage.selectTerminalDropdown(terminal);
		carrierMaintenancePage.selectCarrierDropdown(carrier);
		carrierMaintenancePage.selectSoldToDropdown(soldTo, terminalValue);
		carrierMaintenancePage.clickOnSearchButton();
		assertTrue(carrierMaintenancePage.verifyCarrierMaintenanceTable(), "Verify Carrier Maintenance Table");
		//assertTrue(carrierMaintenancePage.verifyAddRadioButton(), "Verify Add Radio Button");
		//assertTrue(carrierMaintenancePage.verifyRemoveRadioButton(), "Verify Remove Radio Button");
		assertAll();
	}
}
