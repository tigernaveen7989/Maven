package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * Verify that the Login URL is changed
 * 
 * @author M1022570
 *
 */
public class EAHelpLoginURLIsChangedTest extends EAHelpBaseTest {

	@Test(description = "Verify that the Login URL is changed", groups = { "Regression", "Sanity" })
	@Description("Verify that the Login URL is changed t")
	public void verifyLoginURLIsChanged(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "36164,41283";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		/**
		 * Verify that the URL containing 'en-be' is redirected to the URL containing
		 * 'en' locale on QA1 environment.
		 */

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(testData.get("homePage_URL").toString());

		// get current page url
		String loginURL = eaHelpAccountManagementPage.getCurrentPageURL();

		// Verify URL is updated
		assertTrue(loginURL.contains(testData.get("locale").toString()), "Verify login URL is changed");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// click on manage my account
		eaHelpAccountManagementPage.clickManageAccount();

		// Click on recovery password link
		eaHelpAccountManagementPage.recoverPassword();

		// Switch to login URL
		eaHelpAccountManagementPage.switchWindowByTitle("Login In");

		// get current page url
		loginURL = eaHelpAccountManagementPage.getCurrentPageURL();

		// Verify URL is updated
		assertTrue(loginURL.contains(testData.get("Login_URL").toString()), "Verify login URL is changed");

		// assert all
		assertAll();
	}

}
