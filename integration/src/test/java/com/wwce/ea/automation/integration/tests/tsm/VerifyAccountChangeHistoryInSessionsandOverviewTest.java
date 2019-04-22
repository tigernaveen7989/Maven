package com.wwce.ea.automation.integration.tests.tsm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import com.ea.wwce.automation.tsm.pageobjects.TSMBasePageObject;

import io.qameta.allure.Description;

/**
 * 
 * @author rgandham
 * @description This Test is to Verify Account change history in sessions and
 *              Overviwew tab.
 * 
 */
public class VerifyAccountChangeHistoryInSessionsandOverviewTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyAccountChangeHistoryInSessionsandOverviewTest.class);

	@Test(description = "Verfiy Account Change History in Sessions and Overview tab ", groups = { "Regression",
			"Sanity" })
	@Description("Verfiy Account Change History in Sessions tab ")
	public void verifyAccountChangeHistoryInSessionstab(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "17498, 17287";
		context.setAttribute("testcase_id", testID);
		logger.info("Verfiy Account Change History in Sessions and Overview tab " + testID);
		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);

		eaHelpAccountManagementPage.navigatebasicInfo();

		String firstNameWithRandomNumber = testData.get("firstname").toString() + " "
				+ BasePageObject.generateRandomNumber(1, 9999);
		String lastNameWithRandomNumber = testData.get("lastname").toString() + " "
				+ BasePageObject.generateRandomNumber(1, 9999);
		String dateRandomNumber = BasePageObject.getRandomNumberInString(1, 12);
		String monthRandomNumber = BasePageObject.getRandomNumberInString(1, 12);
		String yearRandomNumber = BasePageObject.getRandomNumberInString(1975, 1980);

		String DOB = TSMBasePageObject.getMonthInString(monthRandomNumber) + "/" + dateRandomNumber + "/"
				+ yearRandomNumber.substring(yearRandomNumber.length() - 2);

		// Edit basic info
		eaHelpAccountManagementPage.editbasicInfoInformation(firstNameWithRandomNumber, lastNameWithRandomNumber,
				dateRandomNumber, monthRandomNumber, yearRandomNumber);

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

		// verify First name
		assertTrue(tsmAccountChangeHistorypage.searchandVerifyFNameInSessions(firstNameWithRandomNumber),
				"First name not matched");
		// Verify lastname
		assertTrue(tsmAccountChangeHistorypage.searchandVerifyLNameInSessions(lastNameWithRandomNumber),
				"Last name not matched");
		/*
		 * // Verify Date of Birth in sessions
		 * assertTrue(tsmAccountChangeHistorypage.searchandVerifyDOBInSessions(
		 * DOB), "DOB name not matched in Sessions");
		 */
		// Verify Succesfull Login
		assertTrue(tsmAccountChangeHistorypage.searchSuccessfullLoginInsessions(
				testData.get("successfulllogin").toString()), "Successfull login found");
		// Click on Overview tab
		tsmAccountChangeHistorypage.clickonOverviewTab();
		// verify First name
		assertTrue(tsmAccountChangeHistorypage.searchandVerifyFNameInOverview(firstNameWithRandomNumber),
				"First name not matched");
		// Verify lastname
		assertTrue(tsmAccountChangeHistorypage.searchandVerifyLNameInOverview(lastNameWithRandomNumber),
				"Last name not matched");
		/*
		 * // Verify Date of Birth in sessions
		 * assertTrue(tsmAccountChangeHistorypage.searchandVerifyDOBInOverview(
		 * DOB), "DOB name not matched in Overview");
		 */
		// Verify Successfull Login
		assertTrue(tsmAccountChangeHistorypage.searchSuccessfullLoginInOverview(
				testData.get("successfulllogin").toString()), "Successfull login found");

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
