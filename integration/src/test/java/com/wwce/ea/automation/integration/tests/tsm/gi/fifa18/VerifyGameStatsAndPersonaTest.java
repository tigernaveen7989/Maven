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
 * @description This Test is to Verify Game Stats and persona for fifa 18, FUT
 *              and WC game modes
 * 
 */

public class VerifyGameStatsAndPersonaTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyGameStatsAndPersonaTest.class);

	@Test(description = "Verfiy Game Stats and Persona fifa 18 ", groups = { "Regression", "Sanity" })
	@Description("Verfiy Game Stats and Persona fifa 18")
	public void verifyGameStatsAndPersonaTest(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "53438";
		context.setAttribute("testcase_id", testID);
		logger.info("Verfiy Game Stats and Persona fifa 18 " + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

/*		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// LOAD EAHELP ISNTANCE mDriverInstance.put("EAHELP", this.driver);
		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		assertNotNull(caseNumber, "Verify case number is not null");

		// Create driver instance to open TSM website
		this.driver = this.loadNewInstance(context);*/

		String caseNumber = context.getAttribute("caseNumber").toString();

		// Login to TSM application in to chat advisor
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD EAHELP ISNTANCE
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
		// Search case
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Product tab
		tsmProductPage.clickonProduct();

		// Select FUT Game mode
		tsmProductPage.selectGameMode(testData.get("gameMode_FUT").toString());
		// Click on Additional info tab
		tsmAdditionalInfoPage.clickonAdditionalInfoTab();
		// Verify Game stats for FUT game mode
		assertTrue(tsmAdditionalInfoPage.areGameStatsFieldsAvailable(testData.get("gameStats_fifa18").toString()),
				"Game stats fields are available: " + testData.get("gameStats_fifa18").toString());
		// Verify Persona for FUT game mode
		assertTrue(tsmAdditionalInfoPage.arePersonaFieldsAvailable(testData.get("persona_fifa18").toString()),
				"Persona fields are available: " + testData.get("persona_fifa18").toString());
		// Verify Associated clubs for FUT game mode
		assertTrue(tsmAdditionalInfoPage.areAssociatedClubFieldsAvailable(testData.get("clubs_fifa18").toString()),
				"Associated clubs fields are available: " + testData.get("clubs_fifa18").toString());

		// Select WC Game mode
		tsmProductPage.selectGameMode(testData.get("gameMode_WC").toString());
		// Click on Additional info tab
		tsmAdditionalInfoPage.clickonAdditionalInfoTab();
		// Verify Game stats for WC game mode
		assertTrue(tsmAdditionalInfoPage.areGameStatsFieldsAvailable(testData.get("gameStats_fifa18").toString()),
				"Game stats fields are available: " + testData.get("gameStats_fifa18").toString());
		// Verify Persona for WC game mode
		assertTrue(tsmAdditionalInfoPage.arePersonaFieldsAvailable(testData.get("persona_fifa18").toString()),
				"Persona fields are available: " + testData.get("persona_fifa18").toString());
		// Verify Associated clubs for WC game mode
		assertTrue(tsmAdditionalInfoPage.areAssociatedClubFieldsAvailable(testData.get("clubs_fifa18").toString()),
				"Associated clubs fields are available: " + testData.get("clubs_fifa18").toString());

		// Close Case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
