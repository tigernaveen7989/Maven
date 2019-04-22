package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

public class CreateCaseWithAttachmentAndVerifyInSFTest extends IntegrationBaseTest {

	Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();

	/**
	 * Create Case from portal for locale other than english and attach document
	 * from portal. Login to SF using non admin advisor ex. Game Advisor, Game
	 * Expert etc. Search case created in portal having attachment.
	 * 
	 * @param context
	 * @throws InterruptedException
	 */

	@Test(description = "Create a case on portal with attachement and verify in SF", groups = { "Regression",
			"Sanity" })
	@Description("Create a case on portal with attachement and verify in SF")
	public void createCaseWithAttachmentAndVerifyInSFTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39948,39949,39950";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage
				.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL + testData.get("email_URL").toString());

		// Verify loader is present
		// eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(120);

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
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Login as a phone advsior
		salesforceLoginPage.loginToSalesForce(IntegrationDataConstants.INTEGRATION_SF_AUT_URL,
				testData.get("sf_username").toString(), testData.get("sf_passowrd").toString());

		// LOAD SF ISNTANCE
		mDriverInstance.put("SF", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Search for case number
		salesforceHomePage.serachCase(caseNumber);
		
		Thread.sleep(3000);

		// click on case number
		salesforceHomePage.clickOnCaseNumber(caseNumber);

		Thread.sleep(3000);

		// verify attachment is present
		String attachment = salesforceHomePage.getAttachmentsCount();

		// Verify attachment is present
		assertEquals("1", attachment, "Verify attachments are present");

		// assert all
		assertAll();

	}

}
