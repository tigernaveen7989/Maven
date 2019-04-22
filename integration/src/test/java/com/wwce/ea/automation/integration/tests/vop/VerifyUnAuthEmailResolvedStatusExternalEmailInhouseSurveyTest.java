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
 * Close the case to Resolved Status
 * Login into Gmail
 * Click on Survey Link
 * Submit the survey
 * @author Naveen Kumar
 *
 */

public class VerifyUnAuthEmailResolvedStatusExternalEmailInhouseSurveyTest extends VOPIntegrationBaseTest {
	
	@Test(description = "Verify The Un Auth Email In House Survey", groups = { "Regression",
			"Sanity", "Production_Email" })
	@Description("Verify The Un Auth Email In House Survey")
	public void VerifyUnAuthEmailResolvedStatusExternalEmailInhouseSurveyCaseTest(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "49291,49294,44072";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// set salesforce config for email
		salesforceLoginPage.setSalesForceConfigForEmail(IntegrationDataConstants.INTEGRATION_SF_VOP_AUT_URL,
			testData.get("SF_username").toString(), testData.get("SF_password").toString(), testData.get("emailconfig_Value").toString());

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

		// Login as a chat advsior
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
		
		// Create driver instance to open gmail website
		this.driver = this.loadNewInstance(context);
		
		gmailLoginPage.loadGmail(IntegrationDataConstants.INTEGRATION_GMAIL_URL);
		
		gmailLoginPage.loginToGmail(testData.get("gmailemail").toString(),
				testData.get("gmailpassword").toString());
		
		Thread.sleep(5000);
		
		// LOAD GMAIL ISNTANCE
		mDriverInstance.put("GMAIL", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		
		//driver will wait till gmail email list loads
		gmailHomePage.loadGmailList();
		
		Thread.sleep(15000);
		
		//click on survey email from list
		gmailHomePage.clickOnUnReadMail(testData.get("gmailsubject").toString()+" "+caseNumber.replace("#", ""));
		
		//click on start the survey link
		gmailHomePage.clickOnInHouseSurveyLink();
		
		// switch to Inhouse survey window.
		gmailHomePage.switchWindowByIndex(1);
		
		//submit InHouse survey	
		assertTrue(eahelpSurveyPage.isInHouseSurveySubmitted(testData.get("InHouseSurveyText").toString()), "Survey completed");		
			
		// Assert all
		assertAll();
	}
}
