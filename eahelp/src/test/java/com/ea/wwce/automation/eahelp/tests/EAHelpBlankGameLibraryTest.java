package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpBlankGameLibraryTest extends EAHelpBaseTest {

	/**
	 * Verify that a blank game library is displayed if there are no entitlements
	 * through omega
	 * 
	 * Verify user sees additional featured games into his account for less than 5
	 * games configured to his account.
	 * 
	 */
	@Test(description = "Verify that a blank game library is displayed", groups = { "Regression", "Sanity" })
	@Description("Verify that a blank game library is displayed")
	public void verifyBlankGameLibraryIsBlank(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40368,34136";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("noentitle_username").toString(),
				testData.get("noentitle_password").toString());

		// Click on game library
		eaHelpHomePage.clickOnGamesLibrary();
		
		/* * Verify user sees additional featured games into his account for less than 5
		 * games configured to his account.*/

		// Verify list of games available
		int defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify default games list
		assertEquals(Integer.toString(defaultGames), testData.get("totalGames").toString(),
				"Verify default games list");

		// Verify blank game library is present
		assertNull(eaHelpGameLibraryPage.verifyEntitlement("1"),
				"Verify entitlement flag is not present for blank game library user");

		// Do assert all to verify if there are any failures
		assertAll();
	}

}
