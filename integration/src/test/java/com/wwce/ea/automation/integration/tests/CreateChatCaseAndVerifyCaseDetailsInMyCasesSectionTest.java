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

public class CreateChatCaseAndVerifyCaseDetailsInMyCasesSectionTest extends IntegrationBaseTest {

	@Test(description = "Create email case and resume  as a email case", groups = { "Regression", "Sanity" })
	@Description("Create email case and resume as a email case")
	public void createChatCaseAndResumeAsEmailCase(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40454";
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

		// Create driver instance to open eahelp
		this.driver = this.loadNewInstance(context);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Login to EAHelp
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(
				IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL + testData.get("channel_URL").toString());

		// Verify loader is present
		// eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(120);

		// Verify ICR Page is loaded
		assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.chat, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// Click on my cases button
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();

		// Verify case id is on top
		assertTrue(eaHelpMyCasesPage.isCaseIDOnTop(caseNumber), "Verify case id is on top");

		// Click on case number
		eaHelpMyCasesPage.clickOnCaseDetailsLink(caseNumber);

		// Verify resume button
		assertTrue(eaHelpMyCasesPage.isCaseResumeButtonPresent(), "Verify resume button is present");

		// Verify attach button is present
		assertTrue(eaHelpMyCasesPage.isAttachmentButtonPresent(), "Verify attach button is present");

		// Is chat script present
		assertTrue(eaHelpMyCasesPage.isChatTranscriptShown(), "Is chat script present");

		// Assert all
		assertAll();

	}

}
