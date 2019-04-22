package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

import io.qameta.allure.Description;

/**
 * Verify that Next Button is not enabled if Users enter any script name on FN
 * and LN field on Unauth form on all browsers, devices and locales
 * 
 * @author M1022570
 *
 */
public class EAHelpVerifyNextButtonIsNotEnabledTest extends EAHelpBaseTest {

	@Test(description = "Verify next button is disabled", groups = { "Regression", "Sanity" })
	@Description("Verify next button is disabled")
	public void verifyNextButtonIsNotEnabled(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "36168,36169,36170";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Select case configuration
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("product").toString(),
				testData.get("platform").toString(), testData.get("category").toString(),
				testData.get("subcategory").toString());

		// Submit unauth login form and verify next button is disabled
		assertFalse(
				eaHelpUnAuthCaseInformationPage.verifyNextButtonIsEnabled(testData.get("SQLInJ_FName").toString(),
						testData.get("SQLInJ_LName").toString(), testData.get("Email").toString()),
				" Submit unauth login form and verify next button is disabled");

		// Submit unauth login form and verify next button is disabled
		assertFalse(
				eaHelpUnAuthCaseInformationPage.verifyNextButtonIsEnabled(testData.get("JavaS_FName").toString(),
						testData.get("JavaS_LName").toString(), testData.get("Email").toString()),
				" Submit unauth login form and verify next button is disabled");

		// Submit unauth login form and verify next button is disabled
		assertFalse(
				eaHelpUnAuthCaseInformationPage.verifyNextButtonIsEnabled(testData.get("Numbers_FName").toString(),
						testData.get("Numbers_LName").toString(), testData.get("Email").toString()),
				" Submit unauth login form and verify next button is disabled");

		// Assert all
		assertAll();

	}

}
