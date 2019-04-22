package com.wwce.ea.automation.integration.tests.tsm.gi.fifa19;

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
 * @description This Test is to Verify Grant Items, pack, Currency for Fifa 19
 * 
 */

public class VerifyGrantItemsPacksCurrencyTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyGrantItemsPacksCurrencyTest.class);

	@Test(description = "Verify Grant, Items and Currency for fifa 19", groups = { "Regression", "Sanity" })
	@Description("Verify Grant, Items and Currency")
	public void verifyGrantItemsPacksCurrencyTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "44024, 44025, 44026";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Grant, Items and Currency" + testID);

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
		tsmGrantItemsPacksCurrency.grantItems(testData.get("itemCategory_fifa19").toString());
		//Verify Grant success message for Items
		assertEquals(tsmGrantItemsPacksCurrency.verifyGrantSuccessMessage(),
				testData.get("grantSuccessMessage").toString(), "Grant success message verified for Items");
		//Close Grant modal
		tsmGrantItemsPacksCurrency.closeGrantModal();
		//Verify Grant packs
		tsmGrantItemsPacksCurrency.grantPacks(testData.get("packCategory_fifa19").toString());
		//Verify Grant success message for Packs
		assertEquals(tsmGrantItemsPacksCurrency.verifyGrantSuccessMessage(),
				testData.get("grantSuccessMessage").toString(), "Grant success message verified for Packs");
		//Close Grant Modal
		tsmGrantItemsPacksCurrency.closeGrantModal();
		//Verify Grnat Currency
		tsmGrantItemsPacksCurrency.grantCurrency(testData.get("coins").toString(), testData.get("points").toString());
		//Verify Grant success message for Currency
		assertEquals(tsmGrantItemsPacksCurrency.verifyGrantSuccessMessage(),
				testData.get("grantSuccessMessage").toString(), "Grant success message verified for currency");
		//Close grant modal
		tsmGrantItemsPacksCurrency.closeGrantModal();
		
		//Close Case
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
