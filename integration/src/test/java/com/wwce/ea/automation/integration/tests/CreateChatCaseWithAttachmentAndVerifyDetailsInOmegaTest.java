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

public class CreateChatCaseWithAttachmentAndVerifyDetailsInOmegaTest extends IntegrationBaseTest {

	public static Logger logger = Logger.getLogger(CreateChatCaseWithAttachmentAndVerifyDetailsInOmegaTest.class);

	@Test(description = "Create chat case with attachments and verify details in omega", groups = { "Regression",
			"Sanity" })
	@Description("Create chat case with attachments and verify details in omega")
	public void createChatCaseWithAttachmentAndVerifyDetailsInOmega(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40447";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login as a phone advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
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

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// load EAHELP
		eaHelpLoginPage
				.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL + testData.get("chat_URL").toString());

		// Verify loader is present
		eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(30);

		assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

		// attach file "file1":"Blank1KB.txt",
		eaHelpChannelSelectionPage.attachfile(testData.get("file1").toString());

		// Verify progress loader is present
		eaHelpChannelSelectionPage.verifyProgressLoaderIsInVisible(20);

		// Verify attached file name is showing correctly
		if (!eaHelpChannelSelectionPage.verifyFileName(testData.get("file1").toString())) {

			// attach file "file1":"Blank1KB.txt",
			eaHelpChannelSelectionPage.attachfile(testData.get("file1").toString());

			// Verify progress loader is present
			eaHelpChannelSelectionPage.verifyProgressLoaderIsInVisible(20);

		}

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.chat, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// Switch to Omega
		this.switchToInstance(mDriverInstance.get("OMEGA"));

		// Adding 120 secs sleep as case needs to be loaded in omega
		Thread.sleep(120000);

		omegaLoginPage.isSpinnerInvisible(60);

		// verify attachment is present
		assertTrue(omegaCasesPage.verifyAttachementIsPresent(), "verify case attachement is present");

		// Verify case details in omega
		assertTrue(
				omegaCaseDetailsPage.verifyCaseDetailsInOmega(testData.get("subject").toString(),
						testData.get("product").toString(), testData.get("platform").toString(),
						testData.get("category").toString(), testData.get("subcategory").toString()),
				"Verify case details in omega");

		// Assert all
		assertAll();

	}

}
