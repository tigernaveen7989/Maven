package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class BOLsTest extends USFuelsBaseTest{

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
	@Test(groups = {"regression"})
	@Description("verify_download_using_bol_number")
	public void verify_download_using_bol_number(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		String bolNumber = testData.get("bolnumber").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnBOLsLink();
		
		assertEquals(bolsPage.verifyTitle(), "BOLs", "Verify BOL page title");	
		bolsPage.clickOnShipToDropDown();
		bolsPage.clickOnShipToAllDropDownCheckBox();
		bolsPage.clickOnShipToDropDown();
		bolsPage.clickOnFromCalenderIcon();
		bolsPage.clickOnFromCalenderPrevMonthButton();
		bolsPage.clickOnFromCalenderDayButton();
		bolsPage.enterBOLNumber(bolNumber);
		bolsPage.clickOnSearchButton();
		bolsPage.verifyBOLNoColumnValues(bolNumber);
		bolsPage.selectAllCheckBox();
		bolsPage.clickOnDownloadButton();
		bolsPage.verifyDownloadHeader();
		bolsPage.selectXMLRadioButton();
		bolsPage.clickOnDownloadButton1();
		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = {"sanity"})
	@Description("verify_bols")
	public void verify_bols(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnBOLsLink();
		
		assertEquals(bolsPage.verifyTitle(), "BOLs", "Verify BOL page title");	
		assertTrue(bolsPage.verifySoldToDropdown(), "Verify Sold To Dropdown");
		assertTrue(bolsPage.verifyShipToDropdown(), "Verify Ship To Dropdown");
		assertTrue(bolsPage.verifySearchButton(), "Verify Search Button");
		bolsPage.clickOnFromCalenderIcon();
		bolsPage.clickOnFromCalenderPrevMonthButton();
		bolsPage.clickOnFromCalenderPrevMonthButton();
		bolsPage.clickOnFromCalenderDayButton();
		bolsPage.clickOnSearchButton();
		assertTrue(bolsPage.verifyFromCalenderIcon(), "Verify From calender icon");
		assertTrue(bolsPage.verifyToCalenderIcon(), "Verify To calender icon");
		assertTrue(bolsPage.verifyBOLBumberEditbox(), "Verify BOL Number Editbox");
		assertTrue(bolsPage.verifyAddRemoveColumnsButton(), "Verify Add Remove Columns Button");
		assertTrue(bolsPage.verifyBOLsTable(), "Verify BOLs Table");
		assertAll();
	}
}
