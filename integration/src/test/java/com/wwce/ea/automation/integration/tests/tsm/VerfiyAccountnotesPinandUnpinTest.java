package com.wwce.ea.automation.integration.tests.tsm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

/**
 * 
 * @author rgandham
 * @description This Test is to Verify Pin and UnPin of Account notes.
 * 
 */
public class VerfiyAccountnotesPinandUnpinTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerfiyAccountnotesPinandUnpinTest.class);

	@Test(description = "Verfiy pin and unpin Notes ", groups = { "Regression", "Sanity" })
	@Description("Verfiy pin notes ")
	public void verfiyAccountnotesPinandUnpin(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "13883, 13882";
		context.setAttribute("testcase_id", testID);
		logger.info("verify pin and Unpin notes" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		/*
		 * // Load EA Help website
		 * eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.
		 * INTEGRATION_EAHELP_TSM_AUT_URL); // LOAD EAHELP ISNTANCE
		 * mDriverInstance.put("EAHELP", this.driver);
		 * 
		 * // Create Email case String caseNumber =
		 * eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		 * 
		 * assertNotNull(caseNumber, "Verify case number is not null");
		 * 
		 * // Create driver instance to open tsm website this.driver =
		 * this.loadNewInstance(context);
		 */

		String caseNumber = context.getAttribute("caseNumber").toString();

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
		// Click on Account notes
		tsmAddAccountNotesPage.clkOnAccountNotes();
		// pin notes
		tsmAddAccountNotesPage.pinNotes();
		// verify pinned notes
		assertTrue(tsmAddAccountNotesPage.ispinnednotespresent(), "Verified Account notes pinned");
		// Unpin Notes
		tsmAddAccountNotesPage.unPinNotes();
		// Verify Unpined notes
		assertTrue(tsmAddAccountNotesPage.isUnpinnednotespresent(), "Verified Account notes are Unpinned");
		// Close case tab
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}

}
