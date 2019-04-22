package com.wwce.ea.automation.integration.tests.tsm.gi.battlefrontII;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import com.wwce.ea.automation.integration.tests.tsm.TSMIntegrationBaseTest;

import io.qameta.allure.Description;

/**
 * 
 * @author rgandham
 * @description This Test is to Verify Prodcut Snapshot for battlefront II
 * 
 */
public class VerifyProductSnapshotTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyProductSnapshotTest.class);

	@Test(description = "Verfiy Product Snapshot for battlefront II", groups = { "Regression", "Sanity" })
	@Description("Verfiy Product Snapshot for battlefront II")
	public void verifyProductSnapshotTest(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "53404";
		context.setAttribute("testcase_id", testID);
		logger.info("Verfiy Product Snapshot for battlefront II" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		assertNotNull(caseNumber, "Verify case number is not null");
		// LOAD EAHelp ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		context.setAttribute("caseNumber", caseNumber);
		context.getAttribute("caseNumber");

		// Create driver instance to open tsm website
		this.driver = this.loadNewInstance(context);

		// Login to TSM
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL,
				testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Click on Omni Channel
		tsmAdvisorStatesPage.clickOmniChannel();
		// Change Advisor presence status to Available Email
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// Select Job role
		tsmJobRoleSelectionPage.selectJobRole(testData.get("email_jobRole").toString());
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search caseID in TSM application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Product tab
		tsmProductPage.clickonProduct();
		/*
		 * // Validate Generate backup codes
		 * assertTrue(tsmProductPage.verifyProductandPlatform(testData.get(
		 * "product").toString(), testData.get("platfromStatus").toString()),
		 * "Prodcut and Platform are available"); // Validate persona
		 * assertTrue(tsmProductPage.verifyPersona(testData.get("personaone").
		 * toString(), testData.get("personatwo").toString()),
		 * "Personas are available"); // Validate Game modes
		 * assertTrue(tsmProductPage.verifyGameModes(testData.get("fut").
		 * toString()), "Game modes available");
		 */

		//Verify product fields
		assertTrue(tsmProductPage.areProductFieldsAvailable(testData.get("productFields_battlefrontII").toString()),
				"All product fields matched: " + testData.get("productFields_battlefrontII").toString());
		//Verify Action menu items
		assertTrue(tsmProductPage.areActionMenuItemsAvailable(testData.get("dropdownItems_battlefrontII").toString()),
				"All Action menu items matched" + testData.get("dropdownItems_battlefrontII").toString());

		// Close Case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
