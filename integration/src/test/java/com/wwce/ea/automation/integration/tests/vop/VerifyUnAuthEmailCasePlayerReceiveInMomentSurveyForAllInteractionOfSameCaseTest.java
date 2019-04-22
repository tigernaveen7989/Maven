package com.wwce.ea.automation.integration.tests.vop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import com.ea.wwce.automation.integration.pageobjects.EACustomerSatisfactionSurveyPage;
import com.ea.wwce.automation.integration.pageobjects.GmailHomePage;

import io.qameta.allure.Description;

/**
 * This class is to create email case
 * Goto Salesforce
 * Set the configuration as 100%
 * create an un auth case
 * goto omega
 * Close the case to Resolved Status
 * goto ea help
 * click on resume case
 * open live chat
 * goto omega as chat advisor
 * close the same case
 * Login into Gmail
 * Click on Survey Link
 * Submit the survey
 * @author Naveen Kumar
 *
 */

public class VerifyUnAuthEmailCasePlayerReceiveInMomentSurveyForAllInteractionOfSameCaseTest extends VOPIntegrationBaseTest {

	@Test(description = "Verify The Un Auth Email Case Player Receive In Moment Survey For All Interaction Of Same Case", groups = { "Regression",
			"Sanity", "Production_Email" })
	@Description("Verify The Un Auth Email Case Player Receive In Moment Survey For All Interaction Of Same Case")
	public void VerifyUnAuthEmailCasePlayerReceiveInMomentSurveyForAllInteractionOfSameCaseTest(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "51576";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// Verify SF login
		salesforceLoginPage.loginToSalesForce(IntegrationDataConstants.INTEGRATION_SF_VOP_AUT_URL,
				testData.get("SF_username").toString(), testData.get("SF_password").toString());

		// Navigate to SF classic
		salesforceLoginPage.clickOnUserProfile();
		salesforceLoginPage.switchToSFClassic();
		// Enter Email Config value
		salesforceLoginPage.csatEmailExtrnalSurveyConfig(testData.get("emailconfig_Value").toString());
		// Switch to SF Lightning Link
		salesforceLoginPage.clickOnSFLightningLnk();
		salesforceLoginPage.clickOnUserProfile();
		// Logout from SF
		salesforceLoginPage.logout();

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
		
		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);
		
		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_VOP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHelp", this.driver);

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
				"verify email snippet window is closed");
		
		this.driver.close();
		
		//Switch to gmail instance
		this.switchToInstance(mDriverInstance.get("GMAIL"));
		
		this.driver.navigate().refresh();
		
		//driver will wait till gmail email list loads
		gmailHomePage.loadGmailList();
		
		int count = gmailHomePage.countSurveyMails(testData.get("gmailsubject").toString());
		assertEquals(count, 1, "1 survey mail is expected. "+count+" survey mail is received");
		
		// click on unread email
		gmailHomePage.clickOnUnReadMail(testData.get("gmailsubject1").toString());

		// read access key
		String accessKey = gmailHomePage.getAccessKey();

		// go back to main page
		gmailHomePage.clickOnBackButton();

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);
		
		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_VOP_AUT_URL,
				testData.get("omega_username").toString(), testData.get("omega_password").toString(),
				testData.get("RoleName1").toString());
		
		//Switch to EAHelp instance
		this.switchToInstance(mDriverInstance.get("EAHelp"));
		
		// click on my cases from account menu
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();

		Thread.sleep(5000);
		
		// login using case id and access key
		eaHelpMyCasesUnAuthPage.accessCaseDetailsFromUnAuthMyCasesPage(caseNumber.replace("#", ""), accessKey);

		// click on case details link
		eaHelpMyCasesPage.clickOnCaseDetailsLink(caseNumber.replace("#", ""));

		// click on resume case button
		eaHelpMyCasesPage.clickOnResumeButton();
		
		//Request live chat
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.chat, testData.get("subject").toString(), testData.get("description").toString(), testData.get("phonenumber").toString());
		
		// Switch to chat window
		chatWindowPage.switchToChatWidnow(testData.get("Chat_Window_Title").toString());
		// click on end chat button
		chatWindowPage.clickOnEndChatButton();

		// Switch to InMoment survey window
		chatWindowPage.switchWindowByTitle(testData.get("InMoment_Window_Title").toString());

		// Submit InMoment survey
		eahelpSurveyPage.isInMomentSurveySubmitted(testData.get("InMomentSurveyText").toString());
		// Validate Feedback form
		String note = eahelpSurveyPage.getFeedback();
		assertTrue(note.contains(testData.get("txtfeedback").toString()), "Feedback detail page displayed");
		
		// Assert all
		assertAll();
	}
}
