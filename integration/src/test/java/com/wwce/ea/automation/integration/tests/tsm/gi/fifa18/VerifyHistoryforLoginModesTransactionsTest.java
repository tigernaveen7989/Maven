package com.wwce.ea.automation.integration.tests.tsm.gi.fifa18;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import com.wwce.ea.automation.integration.tests.tsm.TSMIntegrationBaseTest;

import io.qameta.allure.Description;

/**
 * 
 * @author rgandham
 * @description This Test is to Verify History fields for Login, Events and
 *              Transactions fields for fifa 18, for FUT, WC Game modes
 * 
 */

public class VerifyHistoryforLoginModesTransactionsTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyHistoryforLoginModesTransactionsTest.class);

	@Test(description = "Verify Login, Mode and Transactions History fields for FIFA 18", groups = { "Regression",
			"Sanity" })
	@Description("Verify Login, Mode and Transactions History fields for FIFA 18")
	public void verifyHistoryFieldsTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "53430, 53431, 53432";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Login, Mode and Transactions History fields for fifa 18" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		/*
		 * // Load EA Help website
		 * eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.
		 * INTEGRATION_EAHELP_TSM_AUT_URL);
		 * 
		 * // Create Email case String caseNumber =
		 * eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		 * assertNotNull(caseNumber, "Verify case number is not null");
		 * 
		 * // LOAD EAHelp ISNTANCE mDriverInstance.put("EAHELP", this.driver);
		 */

		String caseNumber = context.getAttribute("caseNumber").toString();

		/*
		 * // Create driver instance to open TSM website this.driver =
		 * this.loadNewInstance(context);
		 */
		// Login to TSM
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL,
				testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD TSM ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Open Omni channel widget
		tsmAdvisorStatesPage.clickOmniChannel();
		// Select Presence status as Available - Chat
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// Select Job role
		tsmJobRoleSelectionPage.selectJobRole(testData.get("email_jobRole").toString());
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search Case
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Product tab
		tsmProductPage.clickonProduct();

		// Select FUT Game mode
		tsmProductPage.selectGameMode(testData.get("gameMode_FUT").toString());
		// click on History tab
		tsmHistoryPage.clickonHistoryTab();
		// select History filters

		assertTrue(
				tsmHistoryPage.selectandVerifyLoginHistory(testData.get("endDate").toString(),
						testData.get("endTime").toString(), testData.get("historyFields_fifa18").toString()),
				"All Login history fields matched: " + testData.get("historyFields_fifa18").toString());
		// Verify Modes history fields
		assertTrue(
				tsmHistoryPage.selectAndVerifyModesHistory(testData.get("endDate").toString(),
						testData.get("endTime").toString(), testData.get("modesData_fifa18").toString(),
						testData.get("modes_fifa18").toString(), testData.get("historyFields_fifa18").toString()),
				"All Events history fields matched: " + testData.get("modesData_fifa18").toString()
						+ testData.get("modes_fifa18").toString() + testData.get("historyFields_fifa18").toString());

		// Verify draft History fields
		assertTrue(
				tsmHistoryPage.selectandVerifyDraftHistory(testData.get("endDate").toString(),
						testData.get("endTime").toString(), testData.get("historyFields_fifa18").toString(),
						testData.get("Draft").toString()),
				"All Draft mode history fields verified: " + testData.get("historyFields_fifa18").toString());

		// Verify Transaction history fields
		assertTrue(
				tsmHistoryPage.selectandVerifyTransactionsHistory(testData.get("endDate").toString(),
						testData.get("endTime").toString(), testData.get("historyFields_fifa18").toString(),
						testData.get("transaction_fifa18").toString()),
				"All transaction fields matched: " + testData.get("historyFields_fifa18").toString() +
				testData.get("transaction_fifa18").toString());

		// Select FUT Game mode
		tsmProductPage.selectGameMode(testData.get("gameMode_WC").toString());

		// click on History tab
		tsmHistoryPage.clickonHistoryTab();

		// Verify Login History fields
		assertTrue(
				tsmHistoryPage.selectandVerifyLoginHistory(testData.get("endDate").toString(),
						testData.get("endTime").toString(), testData.get("historyFields_fifa18").toString()),
				"All Login history fields matched: " + testData.get("historyFields_fifa18").toString());

		// Select and Verify Event history field
		assertTrue(
				tsmHistoryPage.selectAndVerifyModesHistory(testData.get("endDate").toString(),
						testData.get("endTime").toString(), testData.get("modesData_fifa18").toString(),
						testData.get("modes_fifa18").toString(), testData.get("historyFields_fifa18").toString()),
				"All Events history fields matched: " + testData.get("modesData_fifa18").toString()
						+ testData.get("modes_fifa18").toString() + testData.get("historyFields_fifa18").toString());

		// Verify draft History fields
		assertTrue(
				tsmHistoryPage.selectandVerifyDraftHistory(testData.get("endDate").toString(),
						testData.get("endTime").toString(), testData.get("historyFields_fifa18").toString(),
						testData.get("Draft").toString()),
				"All Draft mode history fields matched: " + testData.get("historyFields_fifa18").toString());

		// Select and Verify Transaction History fields
		assertTrue(
				tsmHistoryPage.selectandVerifyTransactionsHistory(testData.get("endDate").toString(),
						testData.get("endTime").toString(), testData.get("historyFields_fifa18").toString(),
						testData.get("transaction_fifa18").toString()),
				"All transaction fields matched: " + testData.get("historyFields_fifa18").toString());

		// Close case
		tsmAccountBasicInfoPage.closeCase(caseNumber);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
