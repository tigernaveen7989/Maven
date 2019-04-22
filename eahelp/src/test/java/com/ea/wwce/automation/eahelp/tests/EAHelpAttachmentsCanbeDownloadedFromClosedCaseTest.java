package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 Verify that the file attached for the "closed cases" can be
 *         downloaded
 * 
 */

public class EAHelpAttachmentsCanbeDownloadedFromClosedCaseTest extends EAHelpBaseTest {

	@Test(description = "Verify that the file attached for the closed cases can be downloaded", groups = { "Regression",
			"Sanity" })
	@Description("Verify that the file attached for the closed cases can be downloaded")
	public void verifyAttachmentsCanbeDownloadedFromClosedCaseTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40384";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// click on my cases link
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();

		// Submit case details
		eaHelpMyCasesUnAuthPage.accessCaseDetailsFromUnAuthMyCasesPage(testData.get("closed_caseId").toString(),
				testData.get("secretKey").toString());

		// select first case details
		eaHelpMyCasesPage.clickOnCaseDetailsLink(testData.get("closed_caseId").toString());

		// Verify attachments added
		assertTrue(eaHelpMyCasesPage.verifyAttachmentsAdded(), "Verify attachments added");

		Thread.sleep(5000);

		// download attached file.
		assertTrue(eaHelpMyCasesPage.downloadFile(0), "Verify file downloaded successfully");

		// do verify all assertions are passed/failed
		assertAll();
	}

}
