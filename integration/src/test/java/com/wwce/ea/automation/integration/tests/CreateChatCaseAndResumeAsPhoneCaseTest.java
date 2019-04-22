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

public class CreateChatCaseAndResumeAsPhoneCaseTest extends IntegrationBaseTest {

	@Test(description = "Create chat case and resume it as email case", groups = { "Regression", "Sanity" })
	@Description("Create chat case and resume it as email case")
	public void createChatCaseAndResumeItAsEmailCase(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40449";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("chatRoleName").toString());

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("CHAT", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("phoneRoleName").toString());

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("PHONE", this.driver);

		// Create driver instance to open ea help website
		this.driver=this.loadNewInstance(context);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);
		
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		
		//Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(
				IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL + testData.get("channel_URL").toString());

		// Verify loader is present
		//eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(120);

		// Verify ICR Page is loaded
		assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.chat, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// EA Help case confirmation page
		eaHelpCaseConfirmationPage.clickOnCaseLink();

		// Verify chat transcript is shown
		assertTrue(eaHelpMyCasesPage.isChatTranscriptShown(), "Verify chat transcript is shown");

		// click on resume button
		eaHelpMyCasesPage.clickOnResumeButton();

		// Verify Get call option is enabled
		assertTrue(eaHelpChannelSelectionPage.fillCaseDetailsAndVerifyChannelButtonIsEnabled(CaseType.phone,
				testData.get("subject").toString(), testData.get("description").toString(),
				testData.get("phonenumber").toString()), "Verify Get call option is enabled");		
		
		

		/*
		 * // submit channel form
		 * eaHelpChannelSelectionPage.submitChannelForm(CaseType.phone,
		 * testData.get("subject").toString(),
		 * testData.get("description").toString(), "NA");
		 * 
		 * // get chat case number caseNumber =
		 * eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");
		 * 
		 * // Verify case number is created assertNotNull(caseNumber,
		 * "Verify case number is not null");
		 * 
		 * // EA Help case confirmation page
		 * eaHelpCaseConfirmationPage.clickOnCaseLink();
		 * 
		 * // click on resume button eaHelpMyCasesPage.clickOnResumeButton();
		 */

		// Verify email transcript is shown
		// assertTrue(eaHelpMyCasesPage.isEmailTranscriptShown(), "Verify email
		// transcript is shown");

		// assert all
		assertAll();

	}

}
