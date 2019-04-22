package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

/**
 * 
 * @author sadabala Create email case for UK locale
 */

public class EAHelpEmailCaseForUKLocaleTest extends IntegrationBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpEmailCaseForUKLocaleTest.class);

	@Test(description = "Verify the creation of Email case for UK locale", groups = { "Regression", "Sanity",
			"Production_Email" })
	@Description("Create email case for UK locale")
	public void verifyEmailCaseCreation(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40536";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL.replace("en", "uk"));

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// creating email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);

		// verify Case number should not be null
		assertNotNull(caseNumber, "Verify case number is created successfully");

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Login as a chat advsior
		omegaRolesContainerPage.loginAsEmailAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("RoleName").toString());

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// wait for 5 secs
		Thread.sleep(5000);

		// Search case number
		omegaAgentHomePage.searchCase(caseNumber);

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

		// Assert all
		assertAll();

	}

}
