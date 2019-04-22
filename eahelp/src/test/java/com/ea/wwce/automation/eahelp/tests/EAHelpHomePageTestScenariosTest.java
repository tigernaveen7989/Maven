package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 FY-15209 Verify that Contact Us button is displayed in
 *         global header for Authenticated User FY-15210 Verify that Contact Us
 *         button is displayed in global header for un-authenticated User
 * 
 */
public class EAHelpHomePageTestScenariosTest extends EAHelpBaseTest {

	@Test(priority = 3, description = "Verify Contact us button in global header", groups = { "Regression", "Sanity" })
	@Description("Verify Contact us button in global header")
	public void verifyContactUsButtonIsInGlobalHeader(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "38980,38981";

		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Verify contact us button is present in global header
		assertTrue(eaHelpHomePage.isContactUsButtonPresent(),
				"Anonymous view - Verify contact us button is present in global header");

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Verify contact us button is present in global header
		assertTrue(eaHelpHomePage.isContactUsButtonPresent(),
				"Logged in View- Verify contact us button is present in global header");

		// assert all
		assertAll();
	}

	/**
	 * Verify that there is search box on top right corner of help page. Verify that
	 * user can key in related game searches. Verify that it error message is
	 * displayed 'No results found.', if no searches are found. Verify that there is
	 * a separate section at the top left side Verify that the section shows the
	 * language name. Verify that the section can be expanded/collapsed. Verify that
	 * user can select / deselect locale from the locale section Verify that
	 * Login/signup links are displayed on home even after browser cache is cleared
	 * Verify that there is a section dedicated to Top contact drivers Verify that
	 * top contact driver image is displayed at the bottom of home page Verify that
	 * the Answer HQ link displays on EA home page. Verify that Home page top
	 * contact driver separator is visible
	 * 
	 * @throws Exception
	 */

	@Test(priority = 1)
	public void verifyHomePageTestScenarios(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40032,40038,40045,40046,40047,40048,40049,40064,40040,40041,40059,40065";

		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Verify login link is shown
		assertTrue(eaHelpLoginPage.isloginLinkPresent(), "Verify login link is shown");

		// Verify signup link is shown
		assertTrue(eaHelpHomePage.isSingUpLinkPresent(), "Verify signup link is shown");

		// Verify contact driver image is shown
		assertTrue(eaHelpHomePage.isContactDriverImagePresent(), "Verify contact driver image is shown");

		// Verify contact driver container is shown
		assertTrue(eaHelpHomePage.isContactDriverContainerPresent(), " Verify contact driver container is shown");

		// Verify AHQ container is shown
		assertTrue(eaHelpHomePage.isAHQSectionPresent(), "Verify AHQ container is shown");

		// search game from global search
		eaHelpHomePage.globalSearch(testData.get("searchGame_1").toString());

		// Verify product name on product page
		assertTrue(eaHelpProductPage.getTextFromBreadCrumb().equals(testData.get("searchGame_1").toString()),
				"Verify product name on product page");

		/**
		 * Verify that required correct associated articles are displayed on product
		 * page as per global search on home page. *
		 */
		// Verify that required correct associated articles are displayed on product
		// page
		assertTrue(eaHelpProductPage.verifyArtilceName(testData.get("searchGame_1").toString()),
				"Verify that required correct associated articles are displayed on product page");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// search game from global search
		eaHelpHomePage.globalSearch(testData.get("searchGame_2").toString());

		// Verify no results page is shown
		assertTrue(eaHelpProductPage.noResultsPage(), " Verify no results page is shown");

		// Verify country selector present
		assertTrue(eaHelpHomePage.isCountrySelectorPresent(), "Verify country selector present");

		// Click on country selector
		eaHelpHomePage.clickOnCountrySelector();

		// Click on country link
		eaHelpHomePage.selectCountry(testData.get("country").toString());

		/**
		 * Verify that on changing locale from product page, site should not redirect to
		 * home page
		 * 
		 */

		// Verify product name on product page
		assertTrue(eaHelpProductPage.getTextFromBreadCrumb().equals(testData.get("searchGame_2").toString()),
				"Verify product name on product page");

		Thread.sleep(3000); // String url = eaHelpHomePage.getCurrentPageURL();

		// Get current page url
		assertTrue(eaHelpHomePage.getCurrentPageURL().contains(testData.get("country").toString()),
				"verify home page url is changed to exepected locale");

		// Assert all
		assertAll();

	}

	@Test(priority = 4)
	public void verifyHomePageLocalizedText(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40029,40030,40033,40034,40051,40053,40050,40052,40054,40055,40057";

		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		String[] locales = testData.get("locale").toString().split(",");
		String[] singup = testData.get("singup").toString().split(",");
		String[] helpWithaGame = testData.get("helpWithaGame").toString().split(",");
		String[] managemyaccount = testData.get("managemyaccount").toString().split(",");
		String[] localetext = testData.get("localetext").toString().split(",");
		int i = 0;
		for (String locale : locales) {
			// load EAHELP
			eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", locale));
			// Thread.sleep(20000);
			String txt = eaHelpHomePage.getTextFromActiveLocale().toString().trim();
			assertTrue(txt.equalsIgnoreCase(localetext[i].toString()), "Verify active locale text is as expected");
			txt = eaHelpHomePage.getTextFromAccountBlock().toString().trim();
			assertTrue(txt.equalsIgnoreCase(managemyaccount[i].toString()), "Verify account block text as expected");
			txt = eaHelpHomePage.getTextFromGamesBlock().toString().trim();
			assertTrue(txt.equalsIgnoreCase(helpWithaGame[i].toString()), "Verify games block text as expected");
			txt = eaHelpHomePage.getTextFromSingUpLink().toString().trim();
			assertTrue(txt.equalsIgnoreCase(singup[i].toString()), "Verify signup text as expected");
			// Verify localized flag is shown
			assertTrue(eaHelpHomePage.verifyLocalizedFlag(locale), "Verify localized flag is shown");
			i++;
		}

		// assert all
		assertAll();
	}

	@Test(priority = 2)
	public void verifyLogOutIsLocalized(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40031";

		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("normalUserName").toString(), testData.get("password").toString());

		String[] locales = testData.get("locale").toString().split(",");
		String[] singup = testData.get("logout").toString().split(",");
		int i = 0;
		for (String locale : locales) {
			// load EAHELP
			eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", locale));
			String txt = eaHelpLoginPage.getLogoutText();
			assertTrue(txt.equalsIgnoreCase(singup[i]), "Verify logout text is localized");
			i++;
		}
	}

	@Test(priority = 0)
	public void homePageMiscellaneousScenarios(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40058,40060,40061,41966,40052,40055,41982";

		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		String[] locales = testData.get("locale").toString().split(",");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("ruUserName").toString(), testData.get("password").toString());

		// Verify localized flag is shown
		assertTrue(eaHelpHomePage.verifyLocalizedFlag(locales[0]), "Verify localized flag is shown");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", locales[1]));

		// Verify AHQ container is shown
		assertFalse(eaHelpHomePage.isAHQSectionPresent(), "Verify AHQ container is shown");

		// search game from global search
		eaHelpHomePage.globalSearch(testData.get("searchGame").toString());

		// click on home page logo
		eaHelpHomePage.clickOnLogoImage();
		
		String txt = eaHelpHomePage.getTextFromAccountBlock().toString().trim();
		assertNotNull(txt, "Verify account block is shown");

		// assert all
		assertAll();

	}

	@BeforeMethod
	public void openBrowser(ITestContext context) {
		if (this.driver.toString().contains("(null)")) {
			this.driver = this.loadNewInstance(context);
		}
	}

	@AfterMethod
	public void quitBrowser(ITestContext context) {
		this.closeDriverInstance(this.driver);
	}

}
