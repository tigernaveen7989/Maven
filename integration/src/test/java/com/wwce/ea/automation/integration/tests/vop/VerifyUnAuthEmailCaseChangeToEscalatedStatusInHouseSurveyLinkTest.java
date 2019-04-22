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
 * escalate the case
 * Login into Gmail
 * No Survey send to the user
 * @author Naveen Kumar
 *
 */

public class VerifyUnAuthEmailCaseChangeToEscalatedStatusInHouseSurveyLinkTest extends VOPIntegrationBaseTest {

	@Test(description = "Verify The Un Auth Email Case Change To Escalated Status In House Survey", groups = { "Regression",
			"Sanity", "Production_Email" })
	@Description("Verify The Un Auth Email Case Change To Escalated Status In House Survey")
	public void VerifyUnAuthEmailCaseChangeToEscalatedStatusInhouseSurveyCaseTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "49292";
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

		omegaSaveCaseConfirmationPage.escalateCase(testData.get("chattext").toString(),
				testData.get("outMsg").toString(), testData.get("queue").toString(), testData.get("status").toString());

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);

		Thread.sleep(2000);

		// Verify spinner element is visible
		omegaLoginPage.isSpinnerInvisible(30);
		assertFalse(omegaSaveCaseConfirmationPage.isNextCaseButtonPresent(), "verify chat sinppet window is closed");
				
		Thread.sleep(5000);

		// Switch to GMAIL
		this.switchToInstance(mDriverInstance.get("GMAIL"));
		
		this.driver.navigate().refresh();
		
		// driver will wait till gmail email list loads
		gmailHomePage.loadGmailList();
		
		int count = gmailHomePage.countSurveyMails(testData.get("gmailsubject").toString()+" "+caseNumber.replace("#", ""));
		assertEquals(count, 0, count+" survey mails received, 0 survey mails expected");
		
		// Assert all
		assertAll();
	}
}
