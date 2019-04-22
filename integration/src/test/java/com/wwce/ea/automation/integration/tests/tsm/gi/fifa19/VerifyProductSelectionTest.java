package com.wwce.ea.automation.integration.tests.tsm.gi.fifa19;

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
 * @description This Test is to Verify Product Selection for fifa 19 in TSM application.
 * 
 */

public class VerifyProductSelectionTest extends TSMIntegrationBaseTest {
	
	public static Logger logger = Logger.getLogger(VerifyProductSelectionTest.class);

	@Test(description = "Verify Product Selection for fifa 19", groups = { "Regression", "Sanity" })
	@Description("Verify Product Selection")
	public void verifyProductSelectionTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "28082, 28004, 28075, 28007, 28326, 28077";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Change Product" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

/*		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		assertNotNull(caseNumber, "Verify case number is not null");
		// LOAD EAHelp ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		 context.setAttribute("caseNumber", caseNumber);
		 context.getAttribute("caseNumber");	 

		// Create driver instance to open TSM website
		this.driver = this.loadNewInstance(context);*/
		
		//Get case number
		 String caseNumber = context.getAttribute("caseNumber").toString();
		
		// LOAD TSM ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
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
		// Verify Change Product
		assertEquals(tsmProductPage.selectProduct(testData.get("product_battlefield").toString(), testData.get("productname").toString()),
				testData.get("product_battlefield").toString(), "Product not matched");
		//Verify Product modal is cancelled
		assertTrue(tsmProductPage.isProductModalCancelled(), "Select product Modal is closed");
		//Verify Owned Product
		assertTrue(tsmProductPage.verifyOwnedProducts(testData.get("productname_fifa19").toString(),testData.get("product_fifa19").toString()), "Owned products are matched");
		//Close case
		tsmAccountBasicInfoPage.closeCase(caseNumber);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
