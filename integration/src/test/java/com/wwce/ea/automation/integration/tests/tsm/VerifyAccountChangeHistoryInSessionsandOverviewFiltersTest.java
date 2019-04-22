package com.wwce.ea.automation.integration.tests.tsm;

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
 * @description This Test is to Verify Filters in Sessions and Overview tab.
 * 
 */
public class VerifyAccountChangeHistoryInSessionsandOverviewFiltersTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyAccountChangeHistoryInSessionsandOverviewFiltersTest.class);

	@Test(description = "Verfiy Filters in Sessions and Overview tab ", groups = { "Regression", "Sanity" })
	@Description("Verfiy Filters in Sessions and Overview tab  ")
	public void verifyAccountChangeHistoryInSessionsandOverviewFiltersTest(ITestContext context)
			throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "17504";
		context.setAttribute("testcase_id", testID);
		logger.info("Verfiy Filters in Sessions and Overview tab " + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Create driver instance to open tsm website
		this.driver = this.loadNewInstance(context);
		// Login to TSM application
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Click on Omni channel widget
		tsmAdvisorStatesPage.clickOmniChannel();
		// Select Presence status as Available
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search CaseID in tsm application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Account Tab
		tsmAccountBasicInfoPage.clkOnAccount();
		// Click on Sessions tab
		tsmAccountChangeHistorypage.clickonSessionsTab();
		// select Filters
		/*
		 * tsmAccountChangeHistorypage.sessionsFilters(); // Verify Succesfull
		 * Login assertTrue(tsmAccountChangeHistorypage.
		 * searchSuccessfullLoginInsessions(
		 * testData.get("successfulllogin").toString()),
		 * "Sessions Successfull login found");
		 */
		// Click on Overview tab
		tsmAccountChangeHistorypage.clickonOverviewTab();

		/*
		 * // select Filters tsmAccountChangeHistorypage.sessionsFilters();
		 * assertTrue(tsmAccountChangeHistorypage.
		 * searchSuccessfullLoginInOverview(
		 * testData.get("successfulllogin").toString()),
		 * "Overview Successfull login not found");
		 */
		// close case
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
