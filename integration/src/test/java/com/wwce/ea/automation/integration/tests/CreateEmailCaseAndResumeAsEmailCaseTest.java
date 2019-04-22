package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

public class CreateEmailCaseAndResumeAsEmailCaseTest extends IntegrationBaseTest {

	@Test(description = "Create email case and resume  as a email case", groups = { "Regression", "Sanity" })
	@Description("Create email case and resume as a email case")
	public void createEmailCaseAndResumeAsEmailCase(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40452";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login as a chat advsior
		omegaRolesContainerPage.loginAsEmailAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("RoleName").toString());

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage
				.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL + testData.get("email_URL").toString());

		// Verify loader is present
		// eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(120);

		// Verify ICR Page is loaded
		assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// EA Help case confirmation page
		eaHelpCaseConfirmationPage.clickOnCaseLink();

		// Verify EMAIL transcript is shown
		assertTrue(eaHelpMyCasesPage.isEmailTranscriptShown(), "Verify EMAIL transcript is shown");

		// click on resume button
		eaHelpMyCasesPage.clickOnResumeButton();

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// EA Help case confirmation page
		eaHelpCaseConfirmationPage.clickOnCaseLink();

		// Verify EMAIL transcript is shown
		assertTrue(eaHelpMyCasesPage.isEmailTranscriptShown(), "Verify EMAIL transcript is shown");

		// Switch to Omega
		this.switchToInstance(mDriverInstance.get("OMEGA"));

		// wait for 5 secs
		Thread.sleep(5000);

		// Search case number
		omegaAgentHomePage.searchCase(caseNumber);

		// Verify is spinner is visible
		omegaLoginPage.isSpinnerInvisible(60);

		// wait for 5 secs
		Thread.sleep(5000);

		// Verify case details in omega
		assertTrue(
				omegaCaseDetailsPage.verifyCaseDetailsInOmega(testData.get("subject").toString(),
						testData.get("email_product").toString(), testData.get("email_platform").toString(),
						testData.get("email_category").toString(), testData.get("email_subcategory").toString()),
				"Verify case details in omega");

		// wait for spinner to disappear
		omegaLoginPage.isSpinnerInvisible(60);

		// Verify case status in omega
		omegaCasesPage.verifyCaseStatus(caseNumber, "Updated by Customer");

		// assert all
		assertAll();

	}

}
