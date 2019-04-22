package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * @author M1022570 Scenario: Verify that the unauth user is able to fill the
 *         EAHELP site survey in arabic locale . *
 */

public class EAHelpCSATSurveySubmissionForArLocaleTest extends EAHelpBaseTest {

	@Test(description = "Verify that the unauth user is able to fill the EAHELP site survey", groups = { "Regression",
			"Sanity" })
	@Description("Verify that the unauth user is able to fill the EAHELP site survey")
	public void verifyCSATSurveyLinkIsDisplayedAndSubmitForm(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39520";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar"));

		// Click on survey link
		eaHelpHomePage.clickOnSurveyLink();

		// Switch to survey form
		eaHelpHomePage.switchWindowByIndex(1);

		// select question from the list
		assertTrue(eaHelpCSATSurveyPage.submitCSATSurveyForm(testData.get("Survey_Product").toString(),
				testData.get("FeedBack").toString()), "Verify CSAT survey form is submitted");

		// Assert All
		assertAll();
	}

}
