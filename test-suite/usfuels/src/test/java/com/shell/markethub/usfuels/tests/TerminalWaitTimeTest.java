package com.shell.markethub.usfuels.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class TerminalWaitTimeTest extends USFuelsBaseTest{

	private static Logger logger = Logger.getLogger(TerminalWaitTimeTest.class);
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
	@Description("verify_terminalwaittime")
	public void verify_terminalwaittime(ITestContext context) throws Exception{	
		String userName = testData.get("username").toString();
		String password = testData.get("password").toString();
		String date = testData.get("date").toString();
		String month = testData.get("month").toString();
		String year = testData.get("year").toString();
		String terminal = testData.get("terminal").toString();
		String time = testData.get("time").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnTerminalWaitTimeLink();
		
		assertEquals(terminalWaitTimePage.verifyTitle(), "Terminal Wait Time", "Verify Terminal Wait Time title");
		assertTrue(terminalWaitTimePage.verifyTerminalDropdown(), "Verify Terminal Dropdown");
		assertTrue(terminalWaitTimePage.verifyDateIcon(), "Verify Date Icon");
		assertTrue(terminalWaitTimePage.verifySearchButton(), "Verify Search Button");
		assertTrue(terminalWaitTimePage.verifyTimeEditbox(), "Verify Time Editbox");
		terminalWaitTimePage.selectTerminalDropdown(terminal);
		terminalWaitTimePage.clickOnDateIcon();
		terminalWaitTimePage.selectYear(year);
		terminalWaitTimePage.selectMonth(month);
		terminalWaitTimePage.selectDate(date);
		terminalWaitTimePage.enterTime(time);
		terminalWaitTimePage.clickOnSearchButton();
		assertTrue(terminalWaitTimePage.verifyTerminalWaitTimeGraph(), "Verify Terminal Wait Time Graph");
		assertAll();
	}
}
