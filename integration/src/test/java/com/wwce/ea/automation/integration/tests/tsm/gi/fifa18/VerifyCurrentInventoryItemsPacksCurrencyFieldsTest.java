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
 * @description This Test is to Verify Currenct Inventory Items, packs, Currency
 *              for fifa 18, FUT and WC game modes
 * 
 */

public class VerifyCurrentInventoryItemsPacksCurrencyFieldsTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyCurrentInventoryItemsPacksCurrencyFieldsTest.class);

	@Test(description = "Verify Current Inventory Grant, Items and Currency fields", groups = { "Regression",
			"Sanity" })
	@Description("Verify Current Inventory Grant, Items and Currency fields for fifa 18")
	public void verifyCurrentInventoryGrantItemsPacksCurrencyTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "53427, 53428, 53429";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Current Inventory Grant, Items and Currency fields" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		/*
		 * // Load EA Help website
		 * eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.
		 * INTEGRATION_EAHELP_TSM_AUT_URL); // Create Email case String
		 * caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData,
		 * CaseType.email); assertNotNull(caseNumber,
		 * "Verify case number is not null"); // LOAD EAHelp ISNTANCE
		 * mDriverInstance.put("EAHELP", this.driver);
		 */

		String caseNumber = context.getAttribute("caseNumber").toString();

		/*
		 * // Create driver instance to open TSM website this.driver =
		 * this.loadNewInstance(context);
		 */
		// Login to TSM
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL,
				testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
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

		// Select FUT Game mode
		tsmProductPage.selectGameMode(testData.get("gameMode_FUT").toString());

		// Click on Current Inventory tab
		tsmCurrentInventorypage.clickonCurrentInventoryTab();
		// Select currency
		tsmCurrentInventorypage.selectCurrency();

		// Verify currency fields for differency currency types
		assertTrue(
				tsmCurrentInventorypage.areCurrencyFieldsAvailable(testData.get("currenyType_fifa18").toString(),
						testData.get("currencyFields_fifa18").toString()),
				"All fields for currency types are available for FUT game mode: "
						+ testData.get("currenyType_fifa18").toString()
						+ testData.get("currencyFields_fifa18").toString());

		// Select Items and verify item fields
		tsmCurrentInventorypage.selectItems(testData.get("itemCategory_fifa18").toString());
		assertTrue(tsmCurrentInventorypage.areItemFieldsAvailable(testData.get("itemFields_fifa18").toString()),
				"All fields in kitcards category are available for FUT game mode: "
						+ testData.get("itemFields_fifa18").toString());

		// Select packs and verify pack fields
		tsmCurrentInventorypage.selectPacks(testData.get("packCategory_fifa18").toString());
		assertTrue(tsmCurrentInventorypage.arePackFieldsAvailable(testData.get("packFields_fifa18").toString()),
				"All pack fields are available for FUT game mode: " + testData.get("packFields_fifa18").toString());
		assertTrue(tsmCurrentInventorypage.arePackDetailsFieldsAvailable(testData.get("packDetails_fifa18").toString()),
				"All pack detail fields are available for FUT game mode: " + testData.get("packDetails_fifa18").toString());

		// Select FUT Game mode
		tsmProductPage.selectGameMode(testData.get("gameMode_WC").toString());

		// Click on Current Inventory tab
		tsmCurrentInventorypage.clickonCurrentInventoryTab();
		// Select currency
		tsmCurrentInventorypage.selectCurrency();

		// Verify currency fields for different currency types
		assertTrue(
				tsmCurrentInventorypage.areCurrencyFieldsAvailable(testData.get("currenyType_fifa18").toString(),
						testData.get("currencyFields_fifa18: ").toString()),
				"All fields for currency types are available for WC game mode" + testData.get("currenyType_fifa18").toString() +
				testData.get("currencyFields_fifa18").toString());

		// Select Items and verify item fields
		tsmCurrentInventorypage.selectItems(testData.get("itemCategory_fifa18").toString());
		assertTrue(tsmCurrentInventorypage.areItemFieldsAvailable(testData.get("itemFields_fifa18").toString()),
				"All fields in kitcards category are available for WC game mode: " + testData.get("itemFields_fifa18").toString());

		// Select packs and verify pack fields
		tsmCurrentInventorypage.selectPacks(testData.get("packCategory_fifa18").toString());
		assertTrue(tsmCurrentInventorypage.arePackFieldsAvailable(testData.get("packFields_fifa18").toString()),
				"All pack fields are available for WC game mode" + testData.get("packFields_fifa18").toString());
		// Verify pack details feilds
		assertTrue(tsmCurrentInventorypage.arePackDetailsFieldsAvailable(testData.get("packDetails_fifa18").toString()),
				"All pack detail fields are available for WC game mode: " + testData.get("packDetails_fifa18").toString());

		tsmAccountBasicInfoPage.closeCase(caseNumber);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
