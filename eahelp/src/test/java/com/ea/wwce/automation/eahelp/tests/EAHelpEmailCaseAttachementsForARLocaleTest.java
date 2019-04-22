package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;

import io.qameta.allure.Description;

public class EAHelpEmailCaseAttachementsForARLocaleTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpEmailCaseAttachementsForARLocaleTest.class);

	/**
	 * 1.Verify that the user can attach a file from Email Me page * 2.Verify
	 * that the file with supported formats can be uploaded properly: .txt,
	 * .doc, .docx, .xls, .xlsx, .pdf,. Jpg, .jpeg, .mpeg, .mpg"
	 * 
	 * 3.Verify that the uploaded file name is properly displayed 4.Verify that
	 * the file names for the uploaded files can be:
	 * a.txt,abcabcabcabcabcabcabcabc.doc" 5.Verify that the "Browse File"
	 * option is properly displayed and working for other locales than US
	 * 
	 * @param context
	 * @throws Exception
	 */

	@Test(description = "Verify email case attchment test scenarios ", groups = { "Regression", "Sanity" })
	@Description("Verify email cases attchment test scenarios ")
	public void verifyAttachmentAndDeleteFiles(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		try {
			// Provide the ID of the test case. This is the ID generated in the
			// TestRail when the manual test case is created.
			testID = "39512,39513";
			context.setAttribute("testcase_id", testID);
			logger.info("validating Verify Login Test" + testID);

			// Load the test data for the test ID
			DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
			testData = dataProvider.getTestData(testID);

			// load EAHELP
			eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar"));

			// Login to omega
			eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

			// load EAHELP
			eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar")
					+ testData.get("URL").toString());

			// Verify loader is present
			//eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(120);

			// Verify ICR Page is loaded
			assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

			// Verify subject filed is present
			eaHelpChannelSelectionPage.verifySubjectFieldIsVisible(60);

			// attach file "file1":"Blank1KB.txt",
			eaHelpChannelSelectionPage.attachfile(testData.get("file1").toString());

			// Verify progress loader is present
			eaHelpChannelSelectionPage.verifyProgressLoaderIsInVisible(30);

			// Verify attached file name is showing correctly
			boolean isTrue = eaHelpChannelSelectionPage.verifyFileName(testData.get("file1").toString());

			if (!isTrue) {
				// attach file "file1":"Blank1KB.txt",
				eaHelpChannelSelectionPage.attachfile(testData.get("file1").toString());

				// Verify attached file name is showing correctly
				eaHelpChannelSelectionPage.verifyFileName(testData.get("file1").toString());
			}

			// submit channel form
			eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
					testData.get("description").toString(), "NA");

			String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber();

			// Verify case number is created
			assertNotNull(caseNumber, "Verify case number is not null");

			// Click on case number link
			eaHelpCaseConfirmationPage.clickOnCaseLink();

			// Remove attachment from case - this requirement is changed in
			// SPrint 65 as customer cant delete file until advisor mark it
			// unlock EAHELP-7788	
			 

			// eaHelpMyCasesPage.deleteFile();

			// download attached file.
			// assertFalse(eaHelpMyCasesPage.verifyAttachmentsNotAdded(),
			// "Verify file is removed successfully");

			// verify cases are added
			assertTrue(eaHelpMyCasesPage.verifyAttachmentsAdded(), "Verify attachment are added");

			// download attached file.
			assertTrue(eaHelpMyCasesPage.downloadFile(0), "Verify file downloaded successfully");

			// Assert all
			assertAll();

		} catch (Exception e) {
			logger.warn("Not able to attach a file " + e.getMessage());
			throw e;
		}

	}

}
