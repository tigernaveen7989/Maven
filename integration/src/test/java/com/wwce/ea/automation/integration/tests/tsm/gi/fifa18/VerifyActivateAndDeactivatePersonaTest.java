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
 * @description This Test is to Verify Activate and Deactivate Persona for fifa 18, FUT and WC game modes.
 * 
 */
public class VerifyActivateAndDeactivatePersonaTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyActivateAndDeactivatePersonaTest.class);

	@Test(description = "Verify Activate and Deactivate Persona Test for fia 18 game", groups = { "Regression", "Sanity" })
	@Description("Verify Activate and Deactivate Persona Test for fia 18 game")
	public void verifyActivateAndDeactivatePersonaTest(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "53436";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Activate and Deactivate Persona Test for fia 18 game: " + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

	/*	// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		assertNotNull(caseNumber, "Verify case number is not null");*/
		
		String caseNumber = context.getAttribute("caseNumber").toString();

/*		// Create driver instance to open tsm website
		this.driver = this.loadNewInstance(context);*/
		
		// Login to TSM
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Click on Omni Channel
		tsmAdvisorStatesPage.clickOmniChannel();
		// Change Advisor presence status to Available Email
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		//Select Job role
		tsmJobRoleSelectionPage.selectJobRole(testData.get("email_jobRole").toString());
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search caseID in TSM application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Product tab
		tsmProductPage.clickonProduct();
		
		// Select FUT Game mode
		tsmProductPage.selectGameMode(testData.get("gameMode_FUT").toString());
		// Click on Additional info tab
		tsmAdditionalInfoPage.clickonAdditionalInfoTab();
		//Verify Deactivate Persona
		assertEquals(testData.get("inactive").toString(), tsmAdditionalInfoPage.deActivatePersona(testData.get("inactive").toString()), "Club Name updated");
		//Verify Activate Persona
		assertEquals(testData.get("active").toString(), tsmAdditionalInfoPage.activatePersona(testData.get("active").toString()), "Club Name updated");
		
		// Select WC Game mode
		tsmProductPage.selectGameMode(testData.get("gameMode_WC").toString());
		// Click on Additional info tab
		tsmAdditionalInfoPage.clickonAdditionalInfoTab();
		//Verify Deactivate Persona
		assertEquals(testData.get("inactive").toString(), tsmAdditionalInfoPage.deActivatePersona(testData.get("inactive").toString()), "Club Name updated");
		//Verify Activate Persona
		assertEquals(testData.get("active").toString(), tsmAdditionalInfoPage.activatePersona(testData.get("active").toString()), "Club Name updated");
		
		// Close Case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
