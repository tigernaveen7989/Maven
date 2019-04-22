package com.wwce.ea.automation.integration.tests.vop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

/**
 * This class is to create email case
 * Close the case to Resolved Status
 * Again search the case from omega
 * Close the case to resolved status 
 * Login into Gmail
 * Click on Survey Link
 * Submit the survey
 * @author Naveen Kumar
 *
 */

public class VerifyUnAuthEmailCaseResolvedStatusSecondTimeInADayInMomementSurveyLink extends VOPIntegrationBaseTest {

	@Test(description = "Verify The Auth Email Resolved Status Second Time in a day In Moment Survey", groups = { "Regression",
			"Sanity", "Production_Email" })
	@Description("Verify The Auth Email Resolved Status Second Time in a day In Moment Survey")
	public void VerifyUnAuthEmailResolvedStatusSecondTimeInADayInmomentSurveyCaseTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "49668";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gmailLoginPage.loadGmail(IntegrationDataConstants.INTEGRATION_GMAIL_URL);
		
		// LOAD GMAIL INSNTANCE
		mDriverInstance.put("GMAIL", this.driver);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		gmailLoginPage.loginToGmail(testData.get("gmailemail").toString(), testData.get("gmailpassword").toString());
		
		//driver will wait till gmail email list loads
		gmailHomePage.loadGmailList();
		
		//Empty inbox
		gmailHomePage.deleteAllMails();
		
		this.driver.close();
		
		// Create driver instance to open EA website
		this.driver = this.loadNewInstance(context);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_VOP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHelp", this.driver);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// creating email case
		String caseNumber = eaHelpChannelSelectionPage.createUnAuthCase(testData, CaseType.email);

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

		// wait for 5 secs
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

		// Click on send and next
		omegaSaveCaseConfirmationPage.clickOnSendAndNextCase();

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		assertFalse(omegaSaveCaseConfirmationPage.isSendAndNextCaseButtonPresent(),
				"verify email sinppet window is closed");
				
		Thread.sleep(5000);
		
		this.driver.close();
		
		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		//Re-Login as a Email advaisor
		omegaRolesContainerPage.loginAsEmailAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_VOP_AUT_URL,
				testData.get("omega_username").toString(), testData.get("omega_password").toString(),
				testData.get("RoleName").toString());

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// wait for 5 secs
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

		// Click on send and next
		omegaSaveCaseConfirmationPage.clickOnSendAndNextCase();

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		assertFalse(omegaSaveCaseConfirmationPage.isSendAndNextCaseButtonPresent(),
				"verify email sinppet window is closed");
		
		Thread.sleep(20000);
		
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
		
		int count = gmailHomePage.countSurveyMails(testData.get("gmailsubject").toString());
		Assert.assertEquals(count, 2, count+" survey mails received, 2 survey mails expected");
		
		//click on survey email from list
		gmailHomePage.clickOnUnReadMail(testData.get("gmailsubject").toString());
		
		//click on start the survey link
		gmailHomePage.clickOnSurveyLink();
		
		// switch to Inmoment survey window.
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
