package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

/**
 * This class is to verify this below scenarios Verify that from the product
 * page; if user clicks on "Contact Us" link, the same product is auto-selected
 * on Contact Us page. Verify that the UI displayed on product search page is
 * proper in different browsers. Verify that the product search works fine for
 * different locales Verify that the user gets navigated to the product page
 * properly. Verify that the user gets navigated to the product page properly
 * for different locales Verify the "Country Selector" options on the product
 * page Verify that the error messages or information messages displayed on
 * Product search box is correct and meaningful Verify that the error messages
 * or information messages displayed on Product search box is correct and
 * meaningful for different browsers Verify that the error messages or
 * information messages displayed on Product search box is correct and
 * meaningful for different locales Verify that User gets redirected back to
 * home page on changing locale from product library.
 * Verify that a tooltip with the proper product name gets displayed on hovering over the box images of any game.
 * Verify that the product names are displayed correctly In different locales.
 
 * @author M1022570
 *
 */

public class EAHelpProductSearchScenariosTest extends EAHelpBaseTest {

	/**
	 * Verify that from the product page; if user clicks on "Contact Us" link, the
	 * same product is auto-selected on Contact Us page.
	 * 
	 * @param context
	 */

	@Test(description = "if user clicks on Contact Us link, the same product is auto-selected on Contact Us page", groups = {
		"Regression", "Sanity" })
	@Description("if user clicks on Contact Uslink, the same product is auto-selected on Contact Us page")
	public void verifySameProductIsAutoSelected(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		String[] products;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "34120,40924,40003,40012,40010";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		products = testData.get("productName").toString().split(",");

		for (int i = 0; i < products.length; i++) {

			// Select product
			eaHelpGameLibraryPage.selectProduct(products[i]);

			// Verify search item is present
			String txt = eaHelpProductPage.getTextFromBreadCrumb().trim();

			// verify search item
			assertEquals(txt, products[i], "Verify searched item is shown");

			// load EAHELP
			eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		}

		// assert all
		assertAll();
	}

	/**
	 * Verify that the product search works fine for different locales
	 * 
	 * @param context
	 */

	@Test(description = "Verify that the product search works fine for different locales", groups = { "Regression",
		"Sanity" })
	@Description("Verify that the product search works fine for different locales")
	public void verifyProductSearchForDiffLocales(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		String[] products;
		String[] locales;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "34056,34101,34102,34103,34104,34105,34106,34107";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		products = testData.get("productName").toString().split(",");
		locales = testData.get("Locales").toString().split(",");

		for (int i = 0; i < products.length; i++) {

			// Select product
			eaHelpGameLibraryPage.selectProduct(products[i]);

			// Verify search item is present
			String txt = eaHelpProductPage.getTextFromBreadCrumb().trim();

			// verify search item
			assertEquals(txt, products[i], "Verify searched item is shown");

			if (i < products.length - 1) {

				// load EAHELP
				eaHelpLoginPage
						.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", locales[i]));

				// Is country selector present
				assertTrue(eaHelpHomePage.isCountrySelectorPresent(),
						"verify country selectpr is present on product page");

			}

		}

		// assert all
		assertAll();
	}

	/**
	 * Verify that the error messages or information messages displayed on Product
	 * search box is correct and meaningful
	 * 
	 * @param context
	 */

     @Test(description = "Verify that the error messages or information messages displayed for different locales", groups = {
			"Regression", "Sanity" })
	@Description("Verify that the error messages or information messages displayed")
	public void verifyLocalizedErrorMessage(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		String[] error;
		String[] locales;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "34108";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		error = testData.get("ErrorMessage").toString().split(",");
		locales = testData.get("Locales").toString().split(",");

		for (int i = 0; i < error.length; i++) {

			// Select product
			eaHelpGameLibraryPage.selectProduct(testData.get("InvalidProduct").toString());

			String val = eaHelpGameLibraryPage.verifyNoResultsMessage();

			// Verify that "No results found for product name" message
			assertTrue(error[i].equals(val.trim()), "Verify that \"No results found for product name\" message");

			if (i < error.length - 1) {
				// load EAHELP
				eaHelpLoginPage
						.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", locales[i]));
			}

		}

		// assert all
		assertAll();
	}
	
	
	/**
	 * Scenario: Verify that games titles having 'xx' is displayed properly on the
	 * Product page. Where 'xx' is year '12' or '13' or '14' or '15' or '16'
	 */

	@Test(description = "Verify that games titles having 'xx' is displayed properly", groups = {
			"Regression", "Sanity" })
	@Description("Verify that games titles having 'xx' is displayed properly")
	public void verifyGameTitles(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "41166";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		String[] products = testData.get("Products").toString().split(",");


		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		for (int i = 0; i < products.length; i++) {

			// Select product
			eaHelpGameLibraryPage.selectProduct(products[i]);

			// Verify search item is present
			String txt = eaHelpProductPage.getTextFromBreadCrumb().trim();

			// verify search item
			assertEquals(txt, products[i], "Verify searched item is shown");

			// load EAHELP
			eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		}

		// assert all
		assertAll();
	}

}
