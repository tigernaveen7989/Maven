package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class SupplyWorkspaceTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(SupplyWorkspaceTest.class);
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
	@Description("verify_supplyworkspace")
	public void verify_supplyworkspace(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnSupplyWorkspaceLink();
		
		assertEquals(supplyWorkspacePage.verifySupplyWorkspaceTitle(), "Supply Workspace", "Verify Supply Workspace Page title");
		assertEquals(supplyWorkspacePage.verifyAllocationsWidgetTitle(), "ALLOCATIONS", "Verify Allocations Widget Title");
		assertTrue(supplyWorkspacePage.verifyAllocationsSoldToDropdown(), "Verify Soldto Dropdown");
		assertTrue(supplyWorkspacePage.verifyAllocationsTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(supplyWorkspacePage.verifyAllocationsProductDropdown(), "Verify Product Dropdown");
		assertTrue(supplyWorkspacePage.verifyAllocationsSearchButton(), "Verify Search Button");
		assertTrue(supplyWorkspacePage.verifyAllocationsExpandResultsLink(), "Verify Expand Results Link");
		assertTrue(supplyWorkspacePage.verifyAllocationsShowFiltersLink(), "Verify Show Filters Link");
		assertTrue(supplyWorkspacePage.verifyAllocationsDownloadButton(), "Verify Download Button");
		assertTrue(supplyWorkspacePage.verifyAllocationsViewDetailLink(), "Verify View Detail Link");
		assertTrue(supplyWorkspacePage.verifyAllocationsJumpToTopTechWebsiteLink(), "Verify Jump To Top Tech Website Link");
		assertTrue(supplyWorkspacePage.verifyAllocationsRefreshLink(), "Verify Refresh Link");
		assertTrue(supplyWorkspacePage.verifyAllocationsWidgetTable(), "Verify Allocations Widget Table");
		
		supplyWorkspacePage.clickOnContractPricesExpandIcon();
		assertEquals(supplyWorkspacePage.verifyContractPricesWidgetTitle(), "CONTRACT PRICES", "Verify CONTRACT PRICES Widget Title");
		assertTrue(supplyWorkspacePage.verifyContractPricesSoldToDropdown(), "Verify Soldto Dropdown");
		assertTrue(supplyWorkspacePage.verifyContractPricesTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(supplyWorkspacePage.verifyContractPricesSearchButton(), "Verify Search Button");
		assertTrue(supplyWorkspacePage.verifyContractPricesExpandResultsLink(), "Verify Expand Results Link");
		assertTrue(supplyWorkspacePage.verifyContractPricesShowFiltersLink(), "Verify Show Filters Link");
		assertTrue(supplyWorkspacePage.verifyContractPricesDownloadButton(), "Verify Download Button");
		assertTrue(supplyWorkspacePage.verifyContractPricesViewDetailLink(), "Verify View Detail Link");
		assertTrue(supplyWorkspacePage.verifyContractPricesRefreshLink(), "Verify Refresh Link");
		assertTrue(supplyWorkspacePage.verifyContractPricesWidgetTable(), "Verify Contract Prices Widget Table");
		
		supplyWorkspacePage.clickOnRackPricesExpandIcon();
		assertEquals(supplyWorkspacePage.verifyRackPricesWidgetTitle(), "RACK PRICES", "Verify RACK PRICES Widget Title");
		assertTrue(supplyWorkspacePage.verifyRackPricesSoldToDropdown(), "Verify Soldto Dropdown");
		assertTrue(supplyWorkspacePage.verifyRackPricesTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(supplyWorkspacePage.verifyRackPricesSearchButton(), "Verify Search Button");
		assertTrue(supplyWorkspacePage.verifyRackPricesExpandResultsLink(), "Verify Expand Results Link");
		assertTrue(supplyWorkspacePage.verifyRackPricesShowFiltersLink(), "Verify Show Filters Link");
		assertTrue(supplyWorkspacePage.verifyRackPricesDownloadButton(), "Verify Download Button");
		assertTrue(supplyWorkspacePage.verifyRackPricesViewDetailLink(), "Verify View Detail Link");
		assertTrue(supplyWorkspacePage.verifyRackPricesRefreshLink(), "Verify Refresh Link");
		assertTrue(supplyWorkspacePage.verifyRackPricesWidgetTable(), "Verify Rack Prices Widget Table");
		
		supplyWorkspacePage.clickOnBOLsExpandIcon();
		assertEquals(supplyWorkspacePage.verifyBOLsWidgetTitle(), "BOLs", "Verify BOLs Widget Title");
		assertTrue(supplyWorkspacePage.verifyBOLsSoldToDropdown(), "Verify Soldto Dropdown");
		assertTrue(supplyWorkspacePage.verifyBOLsTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(supplyWorkspacePage.verifyBOLsSearchButton(), "Verify Search Button");
		assertTrue(supplyWorkspacePage.verifyBOLsShowFiltersLink(), "Verify Show Filters Link");
		assertTrue(supplyWorkspacePage.verifyBOLsShipToDropdown(), "Verify Ship To Dropdown");
		assertTrue(supplyWorkspacePage.verifyBOLsViewDetailLink(), "Verify View Detail Link");
		assertTrue(supplyWorkspacePage.verifyBOLsRefreshLink(), "Verify Refresh Link");
		
		assertAll();
	}
}
