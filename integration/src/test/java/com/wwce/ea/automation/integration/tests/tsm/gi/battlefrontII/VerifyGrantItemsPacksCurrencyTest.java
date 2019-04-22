package com.wwce.ea.automation.integration.tests.tsm.gi.battlefrontII;

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
 * @description This Test is to Verify Grant Items, pack, Currency 
 * for Star wars battlefront II
 * 
 */

public class VerifyGrantItemsPacksCurrencyTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyGrantItemsPacksCurrencyTest.class);

	@Test(description = "Verify Grant, Items and Currency for Star wars battlefront II", groups = { "Regression", "Sanity" })
	@Description("Verify Grant, Items and Currency for Star wars battlefront II")
	public void verifyGrantItemsPacksCurrencyTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "53321, 53322, 53323";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Grant, Items and Currency for Star wars battlefront II" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		/*// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		assertNotNull(caseNumber, "Verify case number is not null");
		// LOAD EAHelp ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);*/
		
		String caseNumber = context.getAttribute("caseNumber").toString();

	/*	// Create driver instance to open TSM website
		this.driver = this.loadNewInstance(context);*/

		context.getAttribute("caseNumber");
		// Login to TSM
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD TSM ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Open Omni channel widget
		tsmAdvisorStatesPage.clickOmniChannel();
		// Select Presence status as Available - Chat
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		//Select Job role
		tsmJobRoleSelectionPage.selectJobRole(testData.get("email_jobRole").toString());
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search Case
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Product tab
		tsmProductPage.clickonProduct();
		
		//Verify Grant Items
		tsmGrantItemsPacksCurrency.grantItems(testData.get("itemCategory_battlefrontII").toString());
		assertEquals(tsmGrantItemsPacksCurrency.verifyGrantSuccessMessage(),
				testData.get("grantSuccessMessage").toString(), "Grant success message verified for Items");
		tsmGrantItemsPacksCurrency.closeGrantModal();
		
		//Verify Grant Packs
		tsmGrantItemsPacksCurrency.grantPacks(testData.get("packCategory_battlefrontII").toString());
		assertEquals(tsmGrantItemsPacksCurrency.verifyGrantSuccessMessage(),
				testData.get("grantSuccessMessage").toString(), "Grant success message verified for Packs");
		tsmGrantItemsPacksCurrency.closeGrantModal();
		
		//Verify Grant Currency
		tsmGrantItemsPacksCurrency.grantCurrency(testData.get("coins").toString(), testData.get("points").toString());
		assertEquals(tsmGrantItemsPacksCurrency.verifyGrantSuccessMessage(),
				testData.get("grantSuccessMessage").toString(), "Grant success message verified for currency");
		tsmGrantItemsPacksCurrency.closeGrantModal();
		
		// Close case
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
