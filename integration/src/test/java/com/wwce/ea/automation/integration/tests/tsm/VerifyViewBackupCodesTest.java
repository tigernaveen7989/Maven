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
 * @description This Test is to Verify View Backup codes.
 * 
 */
public class VerifyViewBackupCodesTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyViewBackupCodesTest.class);

	@Test(description = "Verfiy Backup codes", groups = { "Regression", "Sanity" })
	@Description("Verfiy Backup codes")
	public void verifyAccountbasicinfo(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "27227";
		context.setAttribute("testcase_id", testID);
		logger.info("Verfiy Backup codes Test " + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		assertNotNull(caseNumber, "Verify case number is not null");

		// Create driver instance to open tsm website
		this.driver = this.loadNewInstance(context);
		// Login to TSM
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD TSM ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Click on Omni Channel
		tsmAdvisorStatesPage.clickOmniChannel();
		// Change Advisor presence status to Available Email
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search caseID in TSM application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Account tab
		tsmAccountBasicInfoPage.clkOnAccount();
		// Validate Generate backup codes
		assertTrue(tsmGenerateCodesPage.verifyAccountBackupCodes(), "Backup codes are available in account tab");
		// click on overview tab
		tsmAccountChangeHistorypage.clickonOverviewTab();
		// Validate Generate backup codes
		assertTrue(tsmGenerateCodesPage.verifyOverviewBackupCodes(), "Backup codes are available in Overview");

		// Close Case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
