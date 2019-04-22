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

public class CreateCaseResolveItResumeItAndVerifyStatusTest extends IntegrationBaseTest {

	@Test(description = "Create email case and resume  as a email case", groups = { "Regression", "Sanity" })
	@Description("Create email case and resume as a email case")
	public void createEmailCaseAndResumeAsEmailCase(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40491";
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

		// Get case number
		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// Verify EMAIL transcript is shown
		// assertTrue(eaHelpMyCasesPage.isEmailTranscriptShown(), "Verify EMAIL
		// transcript is shown");

		// Switch to Omega
		this.switchToInstance(mDriverInstance.get("OMEGA"));

		// wait for 5 secs
		Thread.sleep(5000);

		// Search case number
		omegaAgentHomePage.searchCase(caseNumber);

		// Verify is spinner is visible
		omegaLoginPage.isCasesTabLoaded(60);

		// Verify case status in omega
		omegaCasesPage.verifyCaseStatus(caseNumber, "New");

		// Resolve case details in omega
		omegaCaseDetailsPage.saveCaseDetails(testData.get("subject").toString(), testData.get("subject").toString(),
				testData.get("reason").toString(), testData.get("status").toString(),
				testData.get("resolutionStatus").toString());

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		// wait for 5 secs
		Thread.sleep(3000);

		// Click on send and next
		omegaSaveCaseConfirmationPage.clickOnSendAndNextCase();

		// Switch to Omega
		this.switchToInstance(mDriverInstance.get("EAHELP"));

		// wait for 5 secs
		Thread.sleep(5000);

		// EA Help case confirmation page
		eaHelpCaseConfirmationPage.clickOnCaseLink();

		// Verify chat transcript is shown
		assertTrue(eaHelpMyCasesPage.isEmailTranscriptShown(), "Verify chat transcript is shown");

		// Verify case status
		String st = eaHelpMyCasesPage.getCaseStaus();

		// verify case status
		assertTrue(st.trim().equalsIgnoreCase(testData.get("reason").toString()),
				"veriy case status as it is resolved");

		// click on resume button
		eaHelpMyCasesPage.clickOnResumeButton();

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		// get case number
		caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// Switch to Omega
		this.switchToInstance(mDriverInstance.get("OMEGA"));

		// wait for 5 secs
		Thread.sleep(5000);
		// eaHelpCaseConfirmationPage.switchWindowByTitle("Omega");
		// eaHelpCaseConfirmationPage.switchToDefaultContent();

		// Refresh omega
		eaHelpCaseConfirmationPage.refreshPage();

		Thread.sleep(2000);

		eaHelpCaseConfirmationPage.acceptAlert();

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		Thread.sleep(2000);

		// Select required role
		omegaRolesContainerPage.selectRole(testData.get("RoleName").toString());

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		Thread.sleep(2000);

		// Verify agent name
		omegaAgentHomePage.verifyAgentName(testData.get("RoleName").toString());

		// Search case number
		omegaAgentHomePage.searchCase(caseNumber);

		// Verify is spinner is visible
		omegaLoginPage.isCasesTabLoaded(60);

		// Verify case status in omega
		omegaCasesPage.verifyCaseStatus(caseNumber, "Updated by Customer");

		// Resolve case details in omega
		omegaCaseDetailsPage.saveCaseDetails(testData.get("subject").toString(), testData.get("subject").toString(),
				testData.get("reason").toString(), testData.get("status").toString(),
				testData.get("resolutionStatus").toString());

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		Thread.sleep(2000);

		// Click on send and next
		omegaSaveCaseConfirmationPage.clickOnSendAndNextCase();

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);
		
		// Verify is spinner is visible 
		omegaLoginPage.isCasesTabLoaded(60);

		/*
		 * 
		 * 
		 * Thread.sleep(2000);
		 */

		// Refresh omega
		eaHelpCaseConfirmationPage.refreshPage();

		Thread.sleep(2000);

		eaHelpCaseConfirmationPage.acceptAlert();

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		Thread.sleep(2000);

		// Select required role
		omegaRolesContainerPage.selectRole(testData.get("RoleName").toString());

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		Thread.sleep(2000);

		// Verify agent name
		omegaAgentHomePage.verifyAgentName(testData.get("RoleName").toString());

		// Search case number
		omegaAgentHomePage.searchCase(caseNumber);

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		// Refresh cases
		omegaCasesPage.refreshCases();

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		// Verify case status in omega
		assertTrue(omegaCasesPage.verifyCaseStatusUpdatedByAdvsior(caseNumber, testData.get("reason").toString()),
				"Verify case status in omega");

		// assert all
		assertAll();

	}

}
