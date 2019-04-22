package com.wwce.ea.automation.integration.tests.tsm;

import java.io.FileNotFoundException;
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
 * @author rgandham
 * @description This Test is to Create Auth Email case with attachment in EAHelp,
 * Verify view attachment and delete attachment in TSM Application.
 *              
 */

public class VerifyEmailCasewithAttachmentAndDeleteAttachmentTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyEmailCasewithAttachmentAndDeleteAttachmentTest.class);

	@Test(description = "Create email case with attachments and delete attachment in TSM", groups = { "Regression",
			"Sanity" })
	@Description("Create email case with attachments and delete attachment in TSM")
	public void emailCasewithAttachmentAndDeleteAttachment(ITestContext context) throws InterruptedException, FileNotFoundException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "16107, 16047, 16196, 17600, 15964, 15961, 15960, 15962";
		context.setAttribute("testcase_id", testID);
		logger.info("verify Delete attachment " + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// Login to EAHelp
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Select case configuration
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("product").toString(),
				testData.get("platform").toString(), testData.get("category").toString(),
				testData.get("subcategory").toString());

		// Verify loader is present
		eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(20);

		// Verify subject filed is present
		// eaHelpChannelSelectionPage.verifySubjectFieldIsVisible(60);

		eaHelpChannelSelectionPage.isEmailExpandButtonPresent();

		eaHelpChannelSelectionPage.clickEmailExpandicon();

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

		// Create driver instance to open tsm website
		this.driver = this.loadNewInstance(context);

		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// Click on Omni channel widget

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		tsmAdvisorStatesPage.clickOmniChannel();
		// Select Presence status as Available
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search CaseID in tsm application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// View attachment
		tsmCaseDetailsPage.clkOnAttachment();
		// Verify Attachment is opened in new tab
		assertTrue(tsmCaseDetailsPage.isAttachmentOpened(), "Attachment is opened ");
		// Delete attachment
		tsmCaseDetailsPage.deleteAttachment(caseNumber);
		// Verify Attachment is deleted
		assertFalse(tsmCaseDetailsPage.isAttachmentPresent(), "Attachment is present");
		// Close Case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
