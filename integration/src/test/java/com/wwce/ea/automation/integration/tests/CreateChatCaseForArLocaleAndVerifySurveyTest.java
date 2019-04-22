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

/**
 * Verify that for Chat channel in arabic locale when user ends chat user gets a
 * post chat survey
 * 
 * @author M1022570
 *
 */
public class CreateChatCaseForArLocaleAndVerifySurveyTest extends IntegrationBaseTest {

	@Test(description = "Verify that for Chat channel in arabic locale when user ends chat user gets a post chat survey", groups = {
			"Regression", "Sanity" })
	@Description("Verify that for Chat channel in arabic locale when user ends chat user gets a post chat survey")
	public void createEmailCaseAndResumeAsEmailCase(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39524";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("RoleName").toString());

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL.replace("en", "ar"));

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL.replace("en", "ar")
				+ testData.get("channel_URL").toString());

		// Verify loader is present
		eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(10);

		// Verify ICR Page is loaded
		assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.chat, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		// Get case number
		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		/*
		 * // Verify EMAIL transcript is shown //
		 * assertTrue(eaHelpMyCasesPage.isEmailTranscriptShown(), "Verify EMAIL //
		 * transcript is shown");
		 * 
		 * // wait for 2 mints as omega is taking much to load chat details
		 * Thread.sleep(1200000);
		 * 
		 * // Switch to Omega this.switchToInstance(mDriverInstance.get("OMEGA"));
		 * 
		 * // wait for 5 secs Thread.sleep(5000);
		 * 
		 * // Search case number omegaAgentHomePage.searchCase(caseNumber);
		 * 
		 * // Verify is spinner is visible omegaLoginPage.isCasesTabLoaded(60);
		 * 
		 * // Verify case status in omega omegaCasesPage.verifyCaseStatus(caseNumber,
		 * "New");
		 * 
		 * // Resolve case details in omega
		 * omegaCaseDetailsPage.saveCaseDetails(testData.get("subject").toString(),
		 * testData.get("subject").toString(), testData.get("reason").toString(),
		 * testData.get("status").toString(),
		 * testData.get("resolutionStatus").toString());
		 * 
		 * // Verify spinner element is visible omegaLoginPage.isSpinnerInvisible(30);
		 * 
		 * // wait for 5 secs Thread.sleep(3000);
		 * 
		 * // Click on send and next
		 * omegaSaveCaseConfirmationPage.clickOnSendAndNextCase();
		 * 
		 * // Switch to Omega this.switchToInstance(mDriverInstance.get("EAHELP"));
		 * 
		 * // wait for 5 secs Thread.sleep(5000);
		 */

		// Switch to chat window
		chatWindowPage.switchToChatWidnow(testData.get("Chat_Window_Title").toString());

		// click on end chat button
		chatWindowPage.clickOnEndChatButton();

		// verify survey button is present
		assertTrue(chatWindowPage.verifyChatSurveyPresent(), " verify survey button is present");

		// assert all
		assertAll();

	}

}
