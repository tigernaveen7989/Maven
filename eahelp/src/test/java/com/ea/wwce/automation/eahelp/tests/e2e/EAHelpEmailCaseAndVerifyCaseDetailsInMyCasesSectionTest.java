package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

import io.qameta.allure.Description;

public class EAHelpEmailCaseAndVerifyCaseDetailsInMyCasesSectionTest extends EAHelpBaseTest {

	@Test(description = "Create email case and resume  as a email case", groups = { "Regression", "Sanity" })
	@Description("Create email case and resume as a email case")
	public void createEmailCaseAndResumeAsEmailCase(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();		
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40455";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// creating email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		
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

		// Is email script present
		assertTrue(eaHelpMyCasesPage.isEmailTranscriptShown(), "Is email script present");

		// Assert all
		assertAll();

	}

}
