package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class ContractPricesTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(ContractPricesTest.class);
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
	@Description("verify_contractprices")
	public void verify_contractprices(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnContractPricesLink();
		
		assertEquals(contractPricesPage.getTitle(), "Contract Prices", "Verify Contract Prices Page title");
		assertTrue(contractPricesPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(contractPricesPage.verifyTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(contractPricesPage.verifyProductDropdown(), "Verify Product Dropdown");
		assertTrue(contractPricesPage.verifyDownloadButton(), "Verify Download Button");
		assertTrue(contractPricesPage.verifySearchButton(), "Verify Search Button");
		assertTrue(contractPricesPage.verifyFromCalenderIcon(), "Verify From calender icon");
		assertTrue(contractPricesPage.verifyToCalenderIcon(), "Verify To calender icon");
		assertTrue(contractPricesPage.verifyAddRemoveColumnsButton(), "Verify Add Remove Columns Button");
		assertTrue(contractPricesPage.verifyContractPricesTable(), "Verify Contract Prices Table");
		
		assertAll();
	}
}
