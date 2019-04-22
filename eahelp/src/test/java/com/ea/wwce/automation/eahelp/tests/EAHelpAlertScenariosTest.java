package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpAlertScenariosTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpAlertScenariosTest.class);

	/**
	 * M1022570
	 * 
	 * @param context
	 * @throws InterruptedException
	 */

	/**
	 * SF Alert Config names-
	 * Automation_HomePage_Alert/Automation_IsGlobal_Alert/
	 * Automation_Deactivated_Alert/Automation Product Page
	 * Alert/Automation_Alert_NewTab -SF END 2. Verify that the portal alert for
	 * product gets displayed on portal. 3. Verify that the portal alert for
	 * product gets displayed and not displayed on home page 4. Verify that the
	 * portal alert configured for ishome=true displays on home page. 5. Verify
	 * that alert displays as expandable by default with the expandable content
	 * showing up in it. 6. Verify that the URL specified and not checked open
	 * in new tab while configuring, it opens properly and in same window. 7.
	 * Verify that the URL specified and Open in new tab is checked, it opens
	 * properly and in different tab. 8. Verify that the portal alert configured
	 * for product displays only for that product and not for others 9. Verify
	 * that the portal alert configured for product and home page displays only
	 * for that product and home page and not for others 10. Verify that the
	 * portal alert configured for isglobal=true displays on all other pages
	 * including home page 11. Verify that alert having Ishome = true does not
	 * show on all pages but home page. 12.Verify that the portal alert stops
	 * displaying when deactivated. 13.Verify that portal alert displays until
	 * end date is met. 14.Verify that the portal alert continues to display
	 * without timeline till is active. 15.Verify that portal alert displays
	 * only within start date and end date range.
	 * 
	 */

	@Test(description = "Verify the alert test scenarios", groups = { "Regression", "Sanity" })
	@Description("Verify the alert test scenarios")
	public void verifyAlertTestScenarios(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40391,40392,40393,40394,40395,40396,40397,40398,40399,40400,40401,40402,40403,40404,40926,40927,40928";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		/* on specific product page test cases */

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "fr")
				+ testData.get("URL1").toString());

		// Verify alert is shown on product page
		assertTrue(eaHelpProductPage.isAlertShownOnProductPage(), "Verify alert is shown on product page");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "fr"));

		// Verify alert is not shown on home page
		assertFalse(eaHelpHomePage.isAlertShownOnHomePage(), "Verify alert is shown on home page");

		/* open configured link in same tab scenarios */

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "es"));

		// Verify alert is shown on home page
		assertTrue(eaHelpHomePage.isAlertShownOnHomePage(), "Verify alert is shown on home page");

		// Verify alert is expanded by default on home page
		assertTrue(eaHelpHomePage.isAlertExpandedOnHomePage(), "Verify alert is expanded  by default on home page");

		// Verify Current page URL
		assertEquals(eaHelpHomePage.clickOnAlertLink(), testData.get("CurrentPageURL").toString(),
				"Verify current page URL");

		// Verify current page title
		assertEquals(eaHelpHomePage.verifyCurrentPageTitle(), testData.get("CurrentPageTitle").toString(),
				"Verify current page title");

		/* isHome true and should not show on product page */

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "es")
				+ testData.get("URL3").toString());

		// Verify alert is not shown on product page
		assertFalse(eaHelpProductPage.isAlertShownOnProductPage(), "Verify alert is not shown on other product page");

		/*
		 * open link in new tab scenario Home page and product page scenarios
		 */

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "it"));

		// Verify alert is shown on home page
		assertTrue(eaHelpHomePage.isAlertShownOnHomePage(), "Verify alert is shown on home page");

		// Verify alert is opened in new window
		eaHelpHomePage.verifyAlertIsOpenedNewTab(testData.get("CurrentPageTitle").toString());

		// Close Current page tab
		eaHelpHomePage.closeCurrentWindow();

		// Switch to EA Help home page
		eaHelpHomePage.switchWindowByTitle(testData.get("CurrentPageTitle").toString());

		// Navigate to BF3 page and verify Alert is showing
		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "it")
				+ testData.get("URL2").toString());

		// Verify alert is shown on product page
		assertTrue(eaHelpProductPage.isAlertShownOnProductPage(), "Verify alert is shown on product page");

		// Navigate to FIFA 18 page and verify Alert is not showing
		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "it")
				+ testData.get("URL3").toString());

		// Verify alert is shown on product page
		assertFalse(eaHelpProductPage.isAlertShownOnProductPage(), "Verify alert is not showing on product page");

		/*
		 * Verify deactivated alert scenarios
		 */
		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "nz"));

		// Verify alert is shown on home page
		 assertFalse(eaHelpHomePage.isAlertShownOnHomePage(),"Verify alert is not shown on home page as it is deactivated");

		/*
		 * Verify isGlobal alert scenarios
		 */
		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "de"));

		// Verify alert is shown on home page
		 assertTrue(eaHelpHomePage.isAlertShownOnHomePage(), "Verify alert is shown on home page");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "de")
				+ testData.get("URL2").toString());

		// Verify alert is shown on product page
		assertTrue(eaHelpProductPage.isAlertShownOnProductPage(), "Verify alert is shown on product page");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "de")
				+ testData.get("URL1").toString());

		// Verify alert is shown on product page
		assertTrue(eaHelpProductPage.isAlertShownOnProductPage(), "Verify alert is shown on product page");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "de")
				+ testData.get("URL3").toString());

		// Verify alert is shown on product page
		assertTrue(eaHelpProductPage.isAlertShownOnProductPage(), "Verify alert is shown on product page");

		// load EAHELP
		//eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "de")
		//		+ testData.get("URL4").toString());

		// Verify alert is shown on product page
		//assertTrue(eaHelpProductPage.isAlertShownOnProductPage(), "Verify alert is shown on product page");

		// do verify all assertions are passed/failed
		assertAll();

	}

}
