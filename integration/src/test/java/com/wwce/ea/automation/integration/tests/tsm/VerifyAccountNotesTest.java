package com.wwce.ea.automation.integration.tests.tsm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

/**
 * 
 * @author rgandham
 * @description This Test is to Add and Verify Account notes for a player.
 * 
 */
public class VerifyAccountNotesTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyAccountNotesTest.class);

	@Test(description = "Verfiy Account Notes ", groups = { "Regression", "Sanity" })
	@Description("Verfiy Account Notes ")
	public void verifyAccountNotes(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "14409, 13856, 13878, 13943, 14740";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Account notes Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

/*			// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		assertNotNull(caseNumber, "Verify case number is not null");

		// Create driver instance to open tsm website
		this.driver = this.loadNewInstance(context);*/
		
		String caseNumber = context.getAttribute("caseNumber").toString();
		
		// Login to TSM application
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Open Omni channel widget
		tsmAdvisorStatesPage.clickOmniChannel();
		// Select Presence status as Available - Email
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// Closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search CaseID in TSM application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Account Tab
		tsmAccountBasicInfoPage.clkOnAccount();
		// Click on Account notes
		tsmAddAccountNotesPage.clkOnAccountNotes();
		String accountNotesRandomNumber = testData.get("txtNotes").toString() + " "
				+ BasePageObject.generateRandomNumber(1, 9);
		// Click Add Account notes
		tsmAddAccountNotesPage.addAccountNotes(accountNotesRandomNumber);
		// Fetching Added account note and comparing
		String note = tsmAddAccountNotesPage.getAccNotes(accountNotesRandomNumber);
		assertTrue(note.equalsIgnoreCase(accountNotesRandomNumber), "Account notes are added");
		// Close case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}

}
