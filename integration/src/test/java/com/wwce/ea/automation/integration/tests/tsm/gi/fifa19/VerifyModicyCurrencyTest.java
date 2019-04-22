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
 * @description This Test is to Verify Grant and Debit Currency(Modify Currency) for fifa 19
 * 
 */

public class VerifyModicyCurrencyTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyModicyCurrencyTest.class);

	@Test(description = "Verify Grant and debit currency", groups = { "Regression", "Sanity" })
	@Description("Verify Grant and debit currency")
	public void verifyGrantDebitCurrencyTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "44027, 44028, 44029, 49665";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Grant and debit currency" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		/*
		 * // Load EA Help website
		 * eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.
		 * INTEGRATION_EAHELP_TSM_AUT_URL);
		 * 
		 * // Create Email case String caseNumber =
		 * eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		 * assertNotNull(caseNumber, "Verify case number is not null");
		 */

		String caseNumber = context.getAttribute("caseNumber").toString();

		/*
		 * // LOAD EAHelp ISNTANCE mDriverInstance.put("EAHELP", this.driver);
		 */
		/*
		 * // Create driver instance to open TSM website this.driver =
		 * this.loadNewInstance(context);
		 */
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
		// Select Job role
		tsmJobRoleSelectionPage.selectJobRole(testData.get("email_jobRole").toString());
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search Case
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Product tab
		tsmProductPage.clickonProduct();
		// Click on Currency Inventory tab
		tsmCurrentInventorypage.clickonCurrentInventoryTab();
		//Select Currency
		tsmCurrentInventorypage.selectCurrency();
		//Verify Grant Currency
		assertTrue(tsmCurrentInventorypage.verifyGrantCurrency(testData.get("amount").toString(),
				testData.get("currency_fifa19").toString()), "Verified Grant currency");
		//Verify Debit Currency
		assertTrue(tsmCurrentInventorypage.verifyDebitCurrency(testData.get("amount").toString(),
				testData.get("currency_fifa19").toString()), "Verified Debit currency");

		// Close case
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
