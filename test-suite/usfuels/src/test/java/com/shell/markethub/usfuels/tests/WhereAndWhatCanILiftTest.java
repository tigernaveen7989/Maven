package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class WhereAndWhatCanILiftTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(BOLsTest.class);
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
	@Description("verify_whereandwhatcanilift")
	public void verify_whereandwhatcanilift(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnWhereAndWhatCanILiftLink();
		
		assertEquals(whereAndWhatCanILiftPage.verifyWhereAndWhatCanILiftTitle(), "Where And What Can I Lift", "Verify Where And What Can I Lift page title");	
		assertTrue(whereAndWhatCanILiftPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(whereAndWhatCanILiftPage.verifyTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(whereAndWhatCanILiftPage.verifySearchButton(), "Verify Search Button");
		assertTrue(whereAndWhatCanILiftPage.verifyDownloadButton(), "Verify Download Button");
		assertTrue(whereAndWhatCanILiftPage.verifyProductsTab(), "Verify Products Tab");
		assertTrue(whereAndWhatCanILiftPage.verifyCarriersTab(), "Verify Carriers Tab");
		assertTrue(whereAndWhatCanILiftPage.verifyPINsTab(), "Verify PINs Tab");
		assertTrue(whereAndWhatCanILiftPage.verifyWhereAndWhatCanILiftTable(), "Verify Where And What Can I Lift Table");
		assertAll();
	}
}
