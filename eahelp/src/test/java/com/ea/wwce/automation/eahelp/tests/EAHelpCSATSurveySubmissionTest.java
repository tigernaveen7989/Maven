package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpCSATSurveySubmissionTest extends EAHelpBaseTest {

	@Test(description = "Verify CSAT survey submission", groups = { "Regression", "Sanity" })
	@Description("Verify CSAT survey submission")
	public void verifyCSATSurveyLinkIsDisplayedAndSubmitForm(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();

		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40416,38958,38973";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Click on survey link
		eaHelpHomePage.clickOnSurveyLink();

		Thread.sleep(8000);
		// Switch to survey form
		eaHelpHomePage.switchWindowByIndex(1);

		// Get feedback text box width
		String width = eaHelpCSATSurveyPage.verifyFeedbackTextBoxWidth();

		// Verify feedback textbox width is as expected
		assertEquals(width, testData.get("width").toString(), "Verify feedback textbox width is as expected");

		// select question from the list
		assertTrue(eaHelpCSATSurveyPage.submitCSATSurveyForm(testData.get("Survey_Product").toString(),
				testData.get("FeedBack").toString()), "Verify CSAT survey form is submitted");

		// Assert All
		assertAll();
	}

}
