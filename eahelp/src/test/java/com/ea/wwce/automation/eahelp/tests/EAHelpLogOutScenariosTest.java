package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class EAHelpLogOutScenariosTest extends EAHelpBaseTest {

	@Test(priority = 0)
	public void verifyLogOutOptionForEAHelp(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40152,40155,40156,40168";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("normalUserName").toString(), testData.get("password").toString());

		// logout from EA Help
		eaHelpLoginPage.logoutFromEAHelp();

		// Verify login link is present
		assertTrue(eaHelpLoginPage.isloginLinkPresent(), "verify login link is present");

		// Verify logout dropdown is present
		assertFalse(eaHelpLoginPage.isLogoutDropdownPresent(), "verify logout dropdown link is present");

		// Verify persona name is present
		assertFalse(eaHelpLoginPage.isPersonaNamePresent(), "verify persona name is present");

		// Assert all
		assertAll();

	}

	@Test(priority = 1)
	public void verifyLogOutOptionForAHQ(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40154";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(testData.get("AHQ_URL").toString());

		// validate login
		ahqLoginPage.LoginToAHQ(testData.get("username").toString(), testData.get("password").toString());

		// Delete all cookies
		eaHelpLoginPage.deleteAllCookies();

		// logout from EA Help
		ahqLoginPage.logOutFromAHQ();

		// Delete all cookies
		eaHelpLoginPage.deleteAllCookies();

		// Verify login link is present
		assertTrue(ahqLoginPage.verifyLoginLinkIsShown(), "verify AHQ login link is present");

		// Assert all
		assertAll();

	}

	@Test(priority = 2)
	public void verifyLogOutOptionForOrigin(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40153";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(testData.get("Origin_URL").toString());

		// validate login
		originLoginPage.loginToOrigin(testData.get("username").toString(), testData.get("password").toString());

		// Verify logout functionality
		originLoginPage.logOutFromOrigin();

		// Verify login link is present
		assertTrue(originLoginPage.verifyLoginLinkIsShown(), "verify origin login link is present");

		// Assert all
		assertAll();

	}

	@Test(priority = 3)
	public void verifyUserIsLoggedOutWhenCookiesCleared(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40159";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("normalUserName").toString(), testData.get("password").toString());

		// Delete all cookies
		eaHelpLoginPage.deleteAllCookies();

		eaHelpLoginPage.refreshPage();

		// Verify login link is present
		assertTrue(eaHelpLoginPage.isloginLinkPresent(), "verify login link is present");

		// Assert all
		assertAll();

	}

	@BeforeMethod
	public void openBrowser(ITestContext context) {
		if (this.driver == null) {
			this.driver = this.loadNewInstance(context);
		}

	}

	@AfterMethod
	public void quitBrowser(ITestContext context) {
		this.closeDriverInstance(this.driver);
	}
}
