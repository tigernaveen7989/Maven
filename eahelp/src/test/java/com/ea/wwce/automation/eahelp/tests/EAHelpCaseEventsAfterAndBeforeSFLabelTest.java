package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpCaseEventsAfterAndBeforeSFLabelTest extends EAHelpBaseTest {

	/*
	 * Scenario : Verify that the data in EAHelp My cases is displayed correctly
	 * for events on a case that were created before and edited after the SF
	 * label date Scenario : Verify that the data in EAHelp My cases is
	 * displayed correctly for events on a case that were created after the SF
	 * label date Scenario : Verify that the data in EAHelp My cases is
	 * displayed correctly for events on a case that were created before the SF
	 * label date Scenario : Verify that there is no significant lag in
	 * displaying the events for cases EAHelp events that are fetched
	 */

	@Test(description = "Verify EAHelp my cases events ", groups = { "Regression", "Sanity" })
	@Description("Verify EAHelp my cases events")
	public void verifyCaseEvents(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39514,39515,39516,39517";
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
		eaHelpMyCasesUnAuthPage.accessCaseDetailsFromUnAuthMyCasesPage(testData.get("caseId").toString(),
				testData.get("secretKey").toString());

		// select first case details
		eaHelpMyCasesPage.clickOnCaseDetailsLink(testData.get("caseId").toString());

		// verify customer events log in case details section
		assertTrue(eaHelpMyCasesPage.isCustomerLoggedEventsPresent(),
				"verify customer logged events added in case details section");

		// Verify advisor events log in case details section
		assertTrue(eaHelpMyCasesPage.isAdvsiorLoggedEventsPresent(),
				"verify customer logged events added in case details section");

		// Load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// click on my cases link
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();

		// Submit case details
		eaHelpMyCasesUnAuthPage.accessCaseDetailsFromUnAuthMyCasesPage(testData.get("caseId_1").toString(),
				testData.get("secretKey_1").toString());

		// select first case details
		eaHelpMyCasesPage.clickOnCaseDetailsLink(testData.get("caseId_1").toString());

		// verify customer events log in case details section
		assertTrue(eaHelpMyCasesPage.isCustomerLoggedEventsPresent(),
				"verify customer logged events added in case details section");

		// Load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// click on my cases link
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();

		// Submit case details
		eaHelpMyCasesUnAuthPage.accessCaseDetailsFromUnAuthMyCasesPage(testData.get("recentcaseId").toString(),
				testData.get("recentcasesecretKey").toString());

		// select first case details
		eaHelpMyCasesPage.clickOnCaseDetailsLink(testData.get("recentcaseId").toString());

		// Verify advisor events log in case details section
		assertTrue(eaHelpMyCasesPage.isAdvsiorLoggedEventsPresent(),
				"verify customer logged events added in case details section");

		// Assert all
		assertAll();

	}

}
