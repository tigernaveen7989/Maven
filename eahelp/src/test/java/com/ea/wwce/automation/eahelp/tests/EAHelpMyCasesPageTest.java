package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * 
 * @author M1022570
 * @Description - This is to verify My cases page validations -EG: Focus when we
 *              change pagination Scenario 1): Verify that focus on the My cases
 *              page is shifted to top of the page when user clicks on
 *              pagination control.
 * 
 */

public class EAHelpMyCasesPageTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpMyCasesPageTest.class);

	@Test(description = "Verify that focus on the My cases page is shifted to top of the page", groups = { "Regression",
			"Sanity" })
	@Description("Verify that focus on the My cases page is shifted to top of the page")
	public void verifyFocusIsShiftedToTopWhenUserClicksOnPagination(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39518";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage
				.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar") + "my-cases/");

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Verify current focus
		assertFalse(eaHelpMyCasesPage.verifyFocusIsShiftedToTop(),
				"Verify focus is shifted to top when pagination is clicked");

		// Assert all
		assertAll();

	}

}
