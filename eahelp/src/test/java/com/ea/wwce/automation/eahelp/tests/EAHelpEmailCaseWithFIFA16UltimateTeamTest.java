package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

import io.qameta.allure.Description;

public class EAHelpEmailCaseWithFIFA16UltimateTeamTest extends EAHelpBaseTest {

	@Test(description = "Verify that user is able to create case on with Game: FIFA 16 Ultimate Team", groups = {
			"Regression", "Sanity" })
	@Description("Verify that user is able to create case on with Game: FIFA 16 Ultimate Team")
	public void emailCaseWithFIFA16UltimateTeamTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39089,39130";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Select case configuration
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("fifaproduct").toString(),
				testData.get("fifaplatform").toString(), testData.get("fifacategory").toString(),
				testData.get("fifasubcategory").toString());
		
		eaHelpChannelSelectionPage.isICRPageLoaded();

		// Verify EA Help email SLA text
		assertTrue(eaHelpChannelSelectionPage.verifyEmailSLA().trim().equalsIgnoreCase(testData.get("SLA").toString()),
				"Verify Email SLA text");

		// create email case
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), testData.get("phonenumber").toString());

		// creating emil case
		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber();

		// verify Case number should not be null
		assertNotNull(caseNumber, "Verify case number is created successfully");

		// Assert all
		assertAll();
	}

}
