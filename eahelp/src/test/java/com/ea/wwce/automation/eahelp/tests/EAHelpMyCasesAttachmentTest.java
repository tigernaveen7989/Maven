package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;

import io.qameta.allure.Description;

public class EAHelpMyCasesAttachmentTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpMyCasesAttachmentTest.class);

	/**
	 * * "Verify that the file with supported formats can be uploaded properly:
	 * .txt, .doc, .docx, .xls, .xlsx, .pdf,. Jpg, .jpeg, .mpeg, .mpg" Verify
	 * that the uploaded file name is properly displayed Verify that the
	 * uploaded file can be downloaded and can be viewed properly "Verify that
	 * the file names for the uploaded files can be: a.txt,
	 * abcabcabcabcabcabcabcabc.doc" Verify that the same file can be uploaded
	 * multiple times to the same case
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "Verify my cases attachement test scenarios ", groups = { "Regression", "Sanity" })
	@Description("Verify my cases attachement test scenarios")
	public void verifyFileAttachementsScenarios(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40372,40373,40374,40375,40376,40378,40380,40381,40382,40383";
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

		/*// attach file "file1":"Blank1KB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file0").toString());

		// Verify attached file name is showing correctly
		assertTrue(eaHelpMyCasesPage.verifyAttachedFileName(testData.get("file0").toString()),
				"verify file name is matching");
*/
		// attach file "file1":"Blank0KB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file1").toString());

		// Verify attached file name is showing correctly
		assertTrue(eaHelpMyCasesPage.verifyAttachedFileName(testData.get("file1").toString()),
				"verify file name is matching");

		// attach file "file1":"Blank1KB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file2").toString());

		// Verify attached file name is showing correctly
		assertTrue(eaHelpMyCasesPage.verifyAttachedFileName(testData.get("file2").toString()),
				"verify file name is matching");

		// attach file "file1":"Blank4.9MB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file3").toString());

		// Verify attached file name is showing correctly
		assertTrue(eaHelpMyCasesPage.verifyAttachedFileName(testData.get("file3").toString()),
				"verify file name is matching");

		// attach file "file1":"Blank5MB.txt",
		eaHelpMyCasesPage.attachFileFromMyCases(testData.get("file4").toString());

		// Verify attached file name is showing correctly
		assertTrue(eaHelpMyCasesPage.verifyAttachedFileName(testData.get("file4").toString()),
				"verify file name is matching");

		// Verify attachments added
		assertTrue(eaHelpMyCasesPage.verifyAttachmentsAdded(), "verify file name is matching");

		Thread.sleep(5000);

		// download attached file.
		assertTrue(eaHelpMyCasesPage.downloadFile(0), "Verify file downloaded successfully");

		// do verify all assertions are passed/failed
		assertAll();

	}

}
