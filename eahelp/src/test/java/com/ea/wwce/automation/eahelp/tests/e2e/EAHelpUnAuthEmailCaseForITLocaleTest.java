package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

import io.qameta.allure.Description;

public class EAHelpUnAuthEmailCaseForITLocaleTest extends EAHelpBaseTest {

	@Test(description = "Create un auth email case for IT locale", groups = { "Regression", "Sanity" })
	@Description("Create un auth email case")
	public void createUnAuthEmailCase(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();

		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40506";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "it"));

		// Select case configuration
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("product").toString(),
				testData.get("platform").toString(), testData.get("category").toString(),
				testData.get("subcategory").toString());

		// Submit unauth login to form
		eaHelpUnAuthCaseInformationPage.submitUnAuthLoginForm(testData.get("unAuthFName").toString(),
				testData.get("unAuthLName").toString(), testData.get("unAuthEmail").toString());

		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), testData.get("phonenumber").toString());

		// Get case number from case confirmation page
		assertNotNull(eaHelpCaseConfirmationPage.getCaseNumber().replace("#", ""),
				"Verify case number is created with attachement for unauth user");

		// Assert all
		assertAll();

	}
}
