package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpGameLibraryUnAuthScenariosTest extends EAHelpBaseTest {

	@Test(description = "Verify Game Library un auth test scenarios", groups = "Regression")
	@Description("Verify Game Library un auth test scenarios")
	public void verifyGameLibraryTestScenarios(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "39961,39962,39963,34111,34112,39999,39997,39964,34110";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Click on game library
		eaHelpHomePage.clickOnGamesLibrary();

		/**
		 * Verify that the entitled games do not get displayed once user selects
		 * "logout" button from top right and user logs out. Verify that the entitled
		 * games are displayed again with the blue star in top right when user logs in.
		 * Verify that only default games gets displayed in the Game library when user
		 * having no entitlements gets logged in
		 * 
		 * Verify that all purchased games get replaced with default games when
		 * authenticated user logs out on home page
		 * 
		 */

		// Verify list of games available
		int defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify default games list
		assertEquals(Integer.toString(defaultGames), testData.get("totalGames").toString(),
				"Verify default games list");

		/**
		 * Verify that the entitlted games are displayed with a 'Blue star' symbol in
		 * top left corner of the image. Verify that the entitled games are displayed
		 * before the default games when authenticated user product library loaded. *
		 * Verify that games for which entitlements are active gets displayed on UI when
		 * user is logged in
		 * 
		 * Verify that all default games get replaced with purchased games when
		 * unauthenticated user gets logged in on home page
		 * 
		 * Verify that entitled games are not displayed to unauthenticated user.

		 * 
		 */

		// Verify entitlement tag for first 5 default games
		for (int i = 0; i <= 4; i++) {
			/*
			 * assertNotNull(eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i)),
			 * "Verify entitlement tag is present");
			 */

			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i));
			assertNull(val, "Verify no entitlement tag present");
		}

		// Login to EA help
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Verify list of games available
		defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify default games list
		assertEquals(Integer.toString(defaultGames), testData.get("totalGames").toString(),
				"Verify default games list");

		Thread.sleep(5000);

		// Verify entitlement tag for first 5 default games
		for (int i = 0; i <= 4; i++) {
			/*
			 * assertNotNull(eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i)),
			 * "Verify entitlement tag is present");
			 */

			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i));
			assertTrue(val.contains("product-ribbon.png"), "Verify entitlement tag");
		}

		// Assert all
		assertAll();

	}

}
