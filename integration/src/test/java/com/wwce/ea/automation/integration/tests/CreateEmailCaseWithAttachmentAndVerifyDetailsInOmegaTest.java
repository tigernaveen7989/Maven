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

public class CreateEmailCaseWithAttachmentAndVerifyDetailsInOmegaTest extends IntegrationBaseTest {

	public static Logger logger = Logger.getLogger(CreateEmailCaseWithAttachmentAndVerifyDetailsInOmegaTest.class);

	@Test(description = "Create email case with attachments and verify details in omega", groups = { "Regression",
			"Sanity" })
	@Description("Create email case with attachments and verify details in omega")
	public void createEmailCaseWithAttachmentAndVerifyDetailsInOmega(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40446";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage
				.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL + testData.get("email_URL").toString());

		// Verify loader is present
		// eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(120);

		// Verify subject filed is present
		eaHelpChannelSelectionPage.verifySubjectFieldIsVisible(60);

		// attach file "file1":"Blank1KB.txt",
		eaHelpChannelSelectionPage.attachfile(testData.get("file1").toString());

		// Verify progress loader is present
		eaHelpChannelSelectionPage.verifyProgressLoaderIsInVisible(30);

		// Verify attached file name is showing correctly
		eaHelpChannelSelectionPage.verifyFileName(testData.get("file1").toString());

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Login as a phone advsior
		omegaRolesContainerPage.loginAsEmailAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("RoleName").toString());

		// Verify is spinner is visible
		omegaLoginPage.isSpinnerInvisible(60);

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Search case number
		omegaAgentHomePage.searchCase(caseNumber);

		// Verify is spinner is visible
		omegaLoginPage.isSpinnerInvisible(60);

		// Verify case details in omega
		assertTrue(
				omegaCaseDetailsPage.verifyCaseDetailsInOmega(testData.get("subject").toString(),
						testData.get("email_product").toString(), testData.get("email_platform").toString(),
						testData.get("email_category").toString(), testData.get("email_subcategory").toString()),
				"Verify case details in omega");

		// verify attachment is present
		assertTrue(omegaCasesPage.verifyAttachementIsPresent(), "verify case attachement is present");

		// Assert all
		assertAll();

	}

}
