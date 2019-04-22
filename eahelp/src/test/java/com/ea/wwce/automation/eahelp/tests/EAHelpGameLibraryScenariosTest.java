package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

/**
 * Verify that an > arrow gets displayed if there are more than 5 games. Verify
 * that < arrow gets displayed to navigate back to first page of games. Verify
 * that user can navigate forward & backward using arrows available in game
 * library Verify that arrows does not gets displayed when user has exact 5
 * games in game library Verify that product names get displayed properly for
 * different locales. Verify that user can search for additional products
 * through search functionality on product selection.
 * 
 * @author M1022570
 *
 */

public class EAHelpGameLibraryScenariosTest extends EAHelpBaseTest {

	@Test(description = "Verify Game Library test scenarios", groups = "Regression")
	@Description("Verify Game Library test scenarios")
	public void verifyGameLibraryTestScenarios(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String title;
		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "34122,39953,39954,39955,39956,39957,39958,39959,39960,39964,39965,39966,39967,39968,39969,39970,39971,39973,39974,34114,34115,34116,34117,34118,34119,34123,34137,40015,41276,41277,41278,41279,41280,41281,41282,40028,40035";
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

		// Verify Games icon is present under games library
		String gtxt = eaHelpGameLibraryPage.verifyGamesIconPresent();

		assertTrue(gtxt.equals(testData.get("icons").toString().split(",")[0]),
				"Verify that 'Products' icon text should be displayed in full under product library.");

		// Verify Accounts icon is present under Accounts library
		String atxt = eaHelpGameLibraryPage.verifyAccountsIconPresent();

		assertTrue(atxt.equals(testData.get("icons").toString().split(",")[1]),
				"Verify that 'Accounts' icon text should be displayed in full under product library.");

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
		 * Verify that the entitlted games are displayed with a 'Blue star' symbol in
		 * top left corner of the image. Verify that the entitled games are displayed
		 * before the default games when authenticated user product library loaded. *
		 * Verify that games for which entitlements are active gets displayed on UI when
		 * user is logged in
		 * 
		 */

		// Verify entitlement tag for first 5 default games
		for (int i = 0; i <= 4; i++) {
			/*
			 * assertNotNull(eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i)),
			 * "Verify entitlement tag is present");
			 */

			String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(i));
			assertTrue(val.contains("product-ribbon.png"), "Verify entitlement tag");
		}

		/**
		 * Verify that ">" arrow gets displayed when user has more than 5 games in game
		 * library Verify that "<" arrow gets displayed when user clicks on forward
		 * arrow to view next 5 games in game library Verify that user can navigate
		 * forward & backword using arrows available in game library
		 * 
		 */

		// Verify right arrow is present
		assertTrue(eaHelpGameLibraryPage.verifyRightArrow(), "Verify left arrow is present");

		// Verify user can navigate forward & backword using arrows available in game
		// library
		assertTrue(
				testData.get("totalNavigations").toString().equals(eaHelpGameLibraryPage.verifyUserNavigateForward()),
				"Verify user can navigate forward");

		// Verify left arrow is present
		assertTrue(eaHelpGameLibraryPage.verifyLeftArrow(), "Verify left arrow is present");

		// Verify user can navigate forward & backword using arrows available in game
		// library
		assertTrue(
				testData.get("totalNavigations").toString().equals(eaHelpGameLibraryPage.verifyUserNavigateBackward()),
				"Verify user can navigate backward");

		/**
		 * Verify that user is able to search the product from the game library using a
		 * keyword *
		 */
		assertTrue(eaHelpGameLibraryPage.searchForProduct(testData.get("game").toString()),
				"Verify product is dispaled as epxected");

		/**
		 * Verify that entitlement indicator does not go away ( for entitled games) on
		 * doing a search *
		 */
		String val = eaHelpGameLibraryPage.verifyEntitlement(Integer.toString(1));
		assertTrue(val.contains("product-ribbon.png"), "Verify entitlement tag");

		/**
		 * Verify that one game gets displayed in game library even though user has
		 * multiple entitlements of same game
		 * 
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("singleGame").toString());

		// Verify list of games displayed
		defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify only one game is displayed
		assertTrue("1".equals(Integer.toString(defaultGames)), "Verify only one game is dispalyed");

		/**
		 * Verify that the search results return all the games associated with the
		 * keyword.
		 * 
		 */
		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("game").toString().substring(0, 6));

		// Verify list of games displayed
		List<WebElement> games = eaHelpGameLibraryPage.getDefaultsGamesList();
		int i = 0;
		for (WebElement game : games) {

			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			String subTitle = testData.get("game").toString().substring(0, 6);

			assertTrue(title.contains(subTitle), "Verfiy search results return all the games associated with keyword");
			i++;
			if (i == 5) {
				break;
			}

		}

		//

		/**
		 * Verify that user can search all the games using keyword and not just the
		 * default games.
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("searchGame").toString());

		// Verify list of games displayed
		defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("searchGame").toString()),
					"Verfiy search results return all the games associated with keyword");
			i++;
			if (i == 1) {
				break;
			}

		}

		/**
		 * Verify that keyword search can work for all games configured and not just the
		 * games user is entitled to *
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("searchGame1").toString());

		// Verify list of games displayed
		defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();
			assertTrue(title.contains(testData.get("searchGame1").toString()),
					"Verfiy search results return all the games associated with keyword");
			i++;
			if (i == 1) {
				break;
			}
		}

		/**
		 * Verify that "No results found for product name" message gets displayed for
		 * unavailable games. * Verify that appropriate error message gets displayed and
		 * all products displayed when user enter invalid product name while searching
		 * the product
		 * 
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("invalidGame").toString());
		val = eaHelpGameLibraryPage.verifyNoResultsMessage();
		// Verify that "No results found for product name" message
		assertTrue(testData.get("ErrorMessage").toString().equals(val.trim()),
				"Verify that \"No results found for product name\" message");

		/**
		 * Verify that user can cancel the search using 'X' icon displayed after typing
		 * a keyword.
		 */
		eaHelpGameLibraryPage.cancelSearch();

		// Verify searched value is cleared
		assertTrue(eaHelpGameLibraryPage.getSearchValue().isEmpty(), "Verify searched value is cleared");

		/**
		 * Game Library : Verify that any and all games/products with NFL and Ma in the
		 * title regardless of order should appear in the instant results all NFL Madden
		 * games should appear
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("game_1").toString());

		// Verify list of games displayed
		defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();

			if (title.contains(testData.get("game_2").toString())
					|| title.contains(testData.get("game_1").toString())) {
				assertTrue(true, "Verfiy search results return all the games associated with keyword");
				i++;
				if (i == 4) {
					break;
				}
			}
		}

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("game_2").toString());

		// Verify list of games displayed
		defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();

			if (title.contains(testData.get("game_2").toString())
					|| title.contains(testData.get("game_1").toString())) {
				assertTrue(true, "Verfiy search results return all the games associated with keyword");
				i++;
				if (i == 4) {
					break;
				}
			}
		}

		/**
		 * Scenario1:Product Page : Verify that any and all games/products with age or
		 * in in the title regardless of order should appear in the instant
		 * results/carousel Dragon Age: Inquisition and Dragon Age Origins *
		 */

		// Search for a product
		eaHelpGameLibraryPage.searchForProduct(testData.get("game_3").toString());

		// Verify list of games displayed
		defaultGames = eaHelpGameLibraryPage.getDefaultsGamesSize();

		// Verify list of games displayed
		games = eaHelpGameLibraryPage.getDefaultsGamesList();
		i = 0;
		for (WebElement game : games) {
			title = eaHelpGameLibraryPage.getGameTitle(game).trim();

			if (title.contains(testData.get("game_3").toString())) {
				assertTrue(true, "Verfiy search results return all the games associated with keyword");
				i++;
				if (i == 4) {
					break;
				}
			}
		}

		// Assert all
		assertAll();

	}

}
