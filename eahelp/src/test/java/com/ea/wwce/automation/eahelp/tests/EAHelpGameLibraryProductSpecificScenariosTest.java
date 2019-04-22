package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpGameLibraryProductSpecificScenariosTest extends EAHelpBaseTest {

	@Test(description = "Verify Game Library  product specific test scenarios", groups = "Regression")
	@Description("Verify Game Library product specific test scenarios")
	public void verifyGameLibraryTestScenarios(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String title;
		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "39975,39976,39977,39978,39979,39980,39981,39982,39983,40002";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Click on game library
		eaHelpHomePage.clickOnGamesLibrary();

		/**
		 * Verify that authenticated user sees all the games for which user is entitled
		 * for Verify that user can view all the default 20 games + the entitlement
		 * games assigned through Omega
		 * 
		 */

		// Verify list of games available
		int defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify default games list
		assertEquals(Integer.toString(defaultGames), testData.get("totalGames").toString(),
				"Verify default games list");

		/**
		 * Verify that user can search all the games using keyword and not just the
		 * default games.
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("noDefaultGame").toString());

		// Verify list of games displayed
		List<WebElement> games = eaHelpGameLibraryPage.getDefaultsGamesList();
		int i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("noDefaultGame").toString()),
					"Verfiy search results return all the games associated with keyword-noDefaultGame");
			i++;
			if (i == 1) {
				break;
			}

		}

		/**
		 * Verify that games for which entitlements are banned not gets displayed on UI
		 * when user is logged in
		 * 
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("gameWithBannedEntitlment").toString());

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		// assertTrue(games.isEmpty(), "Games for which entitlements are banned not gets
		// displayed on UI");
		// Verify list of games displayed

		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("gameWithBannedEntitlment").toString()),
					"Verfiy search results return all the games associated with keyword-gameWithBannedEntitlment");
			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i));
			assertNull(val, "Verify no entitlement tag present");
			i++;
			if (i == 1) {
				break;
			}

		}

		/**
		 * Verify that games for which entitlements are disabled not gets displayed on
		 * UI when user is logged in
		 * 
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("gameWithDisabledEntitlement").toString());

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("gameWithDisabledEntitlement").toString()),
					"Verfiy search results return all the games associated with keyword-gameWithDisabledEntitlement");
			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i));
			assertNull(val, "Verify no entitlement tag present");
			i++;
			if (i == 1) {
				break;
			}

		}

		/**
		 * Verify that games for which entitlements are pending also gets displayed on
		 * UI when user is logged in
		 * 
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("gameWithPendingEntitlement").toString());

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("gameWithPendingEntitlement").toString()),
					"Verfiy search results return all the games associated with keyword -gameWithPendingEntitlement");
			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(1));
			assertTrue(val.contains("product-ribbon.png"), "Verify entitlement tag");
			i++;
			if (i == 1) {
				break;
			}

		}

		/**
		 * Verify that games for which entitlements are deleted does not get displayed
		 * on UI when user is logged in [Blue star should not be shown]
		 * 
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("gameWithDeletedEntitlement").toString());

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("gameWithDeletedEntitlement").toString()),
					"Verfiy search results return all the games associated with keyword-gameWithDeletedEntitlement");
			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i));
			assertNull(val, "Verify no entitlement tag present");
			i++;
			if (i == 1) {
				break;
			}

		}

		/**
		 * Verify that new game gets added in the game library when advisor grants a new
		 * entitlement to user Verify that game gets displayed in users library when
		 * user purchases and downloads game online Verify that one game should be
		 * displayed only once even though user has multiple entitlements to same game.
		 * 
		 */
		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("gameNewlyAdded").toString());

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("gameNewlyAdded").toString()),
					"Verfiy search results return all the games associated with keyword-newly added game");
			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(1));
			assertTrue(val.contains("product-ribbon.png"), "Verify entitlement tag");
			i++;
			if (i == 1) {
				break;
			}

		}

		/**
		 * Verify that games for which associated personas are banned also gets
		 * displayed on UI when user is logged in
		 * 
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("personaBanned").toString());

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("personaBanned").toString()),
					"Verfiy search results return all the games associated with keyword-personaBanned");
			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(1));
			assertTrue(val.contains("product-ribbon.png"), "Verify entitlement tag");
			i++;
			if (i == 1) {
				break;
			}

		}

		/**
		 * Verify that games for which associated personas are disabled also gets
		 * displayed on UI when user is logged in
		 * 
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("personaDisabled").toString());

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("personaDisabled").toString()),
					"Verfiy search results return all the games associated with keyword-personaDisabled");
			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(1));
			assertTrue(val.contains("product-ribbon.png"), "Verify entitlement tag");
			i++;
			if (i == 1) {
				break;
			}

		}

		// Assert all
		assertAll();

	}

}
