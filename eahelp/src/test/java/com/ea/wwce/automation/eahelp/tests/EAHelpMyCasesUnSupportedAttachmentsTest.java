package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;

import io.qameta.allure.Description;

public class EAHelpMyCasesUnSupportedAttachmentsTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpMyCasesUnSupportedAttachmentsTest.class);

	/**
	 * Verify that the file with not-supported formats can NOT be uploaded. Also
	 * verify the error message displayed. .abc, .xyz, .1+a" Verify the error
	 * message upon trying to upload a 5MB + file
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "Verify my cases un supported attachement test scenarios ", groups = { "Regression", "Sanity" })
	@Description("Verify my cases unsupported attachement test scenarios")
	public void verifyFileAttachementsScenarios(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40377,40379";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage
				.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString() + testData.get("URL").toString());

		// Verify loader is present
		//eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(120);

		// Verify ICR Page is loaded
		assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

		// Verify subject filed is present
		eaHelpChannelSelectionPage.verifySubjectFieldIsVisible(120);

		Thread.sleep(2000);

		// submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), "NA");

		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber();

		// Verify case number is created
		assertNotNull(caseNumber, "Verify case number is not null");

		// Click on case number link
		eaHelpCaseConfirmationPage.clickOnCaseLink();

		// attach file "file1":"Blank0KB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file1").toString());

		// attach file "file1":"Blank1KB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file2").toString());

		// attach file "file1":"Blank4.9MB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file3").toString());

		// attach file "file1":"Blank5MB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file4").toString());

		// Verify attachments added
		assertFalse(eaHelpMyCasesPage.verifyAttachmentsNotAdded(), "verify file name is matching");

		// do verify all assertions are passed/failed
		assertAll();

	}

}
