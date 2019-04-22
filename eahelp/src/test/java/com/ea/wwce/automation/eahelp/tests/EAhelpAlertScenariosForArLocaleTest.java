package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAhelpAlertScenariosForArLocaleTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAhelpAlertScenariosForArLocaleTest.class);

	/**
	 * M1022570
	 * 
	 * @param context
	 * @throws InterruptedException
	 */

	/**
	 * SF Alert Config names- Automation_HomePage_Alert_Arabic Scenario: Verify
	 * that Portal alert in Arabic Locale is display on Home Page in eahelp
	 * site.
	 * 
	 */

	@Test(description = "Verify that Portal alert in Arabic Locale is display on Home Page", groups = { "Regression","Sanity" })
	@Description("Verify that Portal alert in Arabic Locale is display on Home Page")
	public void verifyAlertTestScenarios(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39519";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar"));

		// Verify alert is shown on home page
		assertTrue(eaHelpHomePage.isAlertShownOnHomePage(), "Verify alert is shown on home page");

		// Verify alert is expanded by default on home page
		assertTrue(eaHelpHomePage.isAlertExpandedOnHomePage(), "Verify alert is expanded  by default on home page");

		// do verify all assertions are passed/failed
		assertAll();

	}

}
