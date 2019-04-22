package com.wwce.ea.automation.integration.tests.vop;

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
 * This class is to create email case
 * close the case from omega
 * resume the case from eahelp
 * switch to omega
 * reset password
 * Close the case to Resolved Status
 * Login into Gmail
 * survey email send seperately
 * @author Naveen Kumar
 */

public class VerifyAuthEmailResumeCaseContainsTransactionInformationSurveyMailSendSeperatelyTest extends VOPIntegrationBaseTest{

	@Test(description = "Verify The Auth Email Resume Case Contains Transaction Information Sent To Player Survey Email Send Seperately", groups = { "Regression",
			"Sanity", "Production_Email" })
	@Description("Verify The Auth Email Resume Case Contains Transaction Information Sent To Player Survey Email Send Seperately")
	public void VerifyAuthEmailResumeCaseContainsTransactionInformationSurveyMailSendSeperatelyTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "53597";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_VOP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHelp", this.driver);

		// creating email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);

		// verify Case number should not be null
		assertNotNull(caseNumber, "Verify case number is created successfully");
		
		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Login as a email advsior
		omegaRolesContainerPage.loginAsEmailAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_VOP_AUT_URL,
				testData.get("omega_username").toString(), testData.get("omega_password").toString(),
				testData.get("RoleName").toString());

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		Thread.sleep(5000);
		
		// Search case number
		omegaAgentHomePage.searchCase(caseNumber.replace("#", ""));
		
		Thread.sleep(5000);
		
		// Verify is spinner is visible
		omegaLoginPage.isCasesTabLoaded(60);

		// Resolve case details in omega
		omegaCaseDetailsPage.saveCaseDetailsWithVOG(testData.get("subject").toString(),
				testData.get("subject").toString(), testData.get("reason").toString(),
				testData.get("status").toString(), testData.get("resolutionStatus").toString(),
				testData.get("vogTxt1").toString(), testData.get("vogTxt2").toString(),
				testData.get("vogTxt3").toString());

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		Thread.sleep(2000);

		// enter additional notes
		omegaSaveCaseConfirmationPage.enterAdditonalNotes(testData.get("subject").toString());

		// Click on send and next case
		omegaSaveCaseConfirmationPage.clickOnSendAndNextCase();

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		assertFalse(omegaSaveCaseConfirmationPage.isSendAndNextCaseButtonPresent(),
				"verify email sinppet window is closed");
		
		this.driver.close();
		
		// switch the instance to omega
		this.switchToInstance(mDriverInstance.get("EAHelp"));

		// click on my cases from account menu
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();

		Thread.sleep(5000);

		// click on case details link
		eaHelpMyCasesPage.clickOnCaseDetailsLink(caseNumber.replace("#", ""));

		//click on resume button
		eaHelpMyCasesPage.clickOnResumeButton();
		
		// Request live chat
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), testData.get("phonenumber").toString());
		
		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Login as a email advsior
		omegaRolesContainerPage.loginAsEmailAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_VOP_AUT_URL,
				testData.get("omega_username").toString(), testData.get("omega_password").toString(),
				testData.get("RoleName").toString());

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		Thread.sleep(5000);

		// Search case number
		omegaAgentHomePage.searchCase(caseNumber.replace("#", ""));

		Thread.sleep(5000);

		// Verify is spinner is visible
		omegaLoginPage.isCasesTabLoaded(60);
		
		//reset password
		omegaAgentHomePage.resetPassword();
		
		// Resolve case details in omega
		omegaCaseDetailsPage.saveCaseDetailsWithVOG(testData.get("subject").toString(),
				testData.get("subject").toString(), testData.get("reason").toString(),
				testData.get("status").toString(), testData.get("resolutionStatus").toString(),
				testData.get("vogTxt1").toString(), testData.get("vogTxt2").toString(),
				testData.get("vogTxt3").toString());

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		Thread.sleep(2000);

		// enter additional notes
		omegaSaveCaseConfirmationPage.enterAdditonalNotes(testData.get("subject").toString());

		// click on send and next case
		omegaSaveCaseConfirmationPage.clickOnSendAndNextCase();
		
		// Create driver instance to open gmail website
		this.driver = this.loadNewInstance(context);

		gmailLoginPage.loadGmail(IntegrationDataConstants.INTEGRATION_GMAIL_URL);

		gmailLoginPage.loginToGmail(testData.get("gmailemail").toString(), testData.get("gmailpassword").toString());

		Thread.sleep(5000);

		// LOAD GMAIL ISNTANCE
		mDriverInstance.put("GMAIL", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// driver will wait till gmail email list loads
		gmailHomePage.loadGmailList();

		Thread.sleep(10000);

		// click on survey email from list
		gmailHomePage.clickOnUnReadMail(testData.get("gmailsubject").toString() + " " + caseNumber.replace("#", ""));

		// Read body and validate this mail as transaction mail
		gmailHomePage.readMail("reset your password");

		// click on back button
		gmailHomePage.clickOnBackButton();
		
		//click on survey email from list
		gmailHomePage.clickOnUnReadMail(testData.get("gmailsubject1").toString());
		
		//click on start the survey link
		gmailHomePage.clickOnSurveyLink();
		
		// switch to InMoment survey window.
		gmailHomePage.switchWindowByIndex(1);
		
		// submit InHouse survey
		eaCustomerSatisfactionPage.isInMomentSurveySubmitted(testData.get("InMomentSurveyText").toString());

		Thread.sleep(5000);

		String note = eaCustomerSatisfactionPage.getFeedback();
		assertTrue(note.contains(testData.get("txtfeedback").toString()), "Feedback detail page displayed");
	
		// Assert all
		assertAll();
	}
}
