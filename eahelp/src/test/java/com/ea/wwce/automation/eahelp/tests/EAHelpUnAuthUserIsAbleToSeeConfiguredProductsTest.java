package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 Scenario: Verify that game titles having '20xx' is not
 *         displayed on the games dropdown on the Home page Verify that game
 *         having fullgame flag is only displayed on game dropdown in chrome
 *         browers
 * 
 *         Verify that user can select a game on home page from drop-down list
 *         of categories and products
 * 
 * 
 */

public class EAHelpUnAuthUserIsAbleToSeeConfiguredProductsTest extends EAHelpBaseTest {

	@Test(description = "Verify that game titles having '20xx' is not displayed", groups = { "Regression", "Sanity" })
	@Description("Verify that game titles having '20xx' is not displayed")
	public void verifyUnAuthUserIsAbleToSeeConfiguredProductsForArLocale(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "38952,39265,40921,40923";// 13687
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// click on Games tab
		eaHelpHomePage.clickOnGamesTab();

		// Verify configured products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-1").toString()),
				"Verify configured product is present in products list");

		// Verify configured products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-2").toString()),
				"Verify configured product is present in products list");

		// Verify configured products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-3").toString()),
				"Verify configured product is present in products list");

		// Verify configured products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-4").toString()),
				"Verify configured product is present in products list");

		// Verify configured products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-5").toString()),
				"Verify configured product is present in products list");

		// Verify configured products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-6").toString()),
				"Verify configured product is present in products list");

		// Verify configured products are visible
		/*assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-7").toString()),
				"Verify configured product is present in products list");*/

		// Verify XX products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-8").toString()),
				"Verify configured product is present in products list");

		// Verify XX products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-9").toString()),
				"Verify configured product is present in products list");

		// Verify XX products are visible
		assertTrue(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-10").toString()),
				"Verify configured product is present in products list");

		// Verify 20XX products are not visible
		assertFalse(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-11").toString()),
				"Verify configured product is present in products list");

		// Verify 20XX products are not visible
		assertFalse(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-12").toString()),
				"Verify configured product is present in products list");

		// Verify 20xx products are not visible
		assertFalse(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-13").toString()),
				"Verify configured product is present in products list");

		// Verify 20xx products are not visible
		assertFalse(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-14").toString()),
				"Verify configured product is present in products list");

		// Verify 20xx products are not visible
		assertFalse(eaHelpHomePage.verifyConfiguredProductsArePresent(testData.get("Product-15").toString()),
				"Verify configured product is present in products list");

		// Assert all
		assertAll();

	}

}
