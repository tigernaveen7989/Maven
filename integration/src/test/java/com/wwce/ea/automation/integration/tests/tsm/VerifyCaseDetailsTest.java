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
 * @description This Test is to Create Auth Email case in EAHelp, Verify Case
 *              details in TSM Application.
 * 
 */

public class VerifyCaseDetailsTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyCaseDetailsTest.class);

	@Test(description = "Verfiy Case Details ", groups = { "Regression", "Sanity" })
	@Description("Verfiy Case Details ")
	public void verifyCaseDetails(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "20544, 15914, 15928, 16023, 16024, 16026, 16027, 16045";
		context.setAttribute("testcase_id", testID);
		logger.info("Verfiy Case Details" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		/*		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);

		assertNotNull(caseNumber, "Verify case number is not null");

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Create driver instance to open tsm website
		this.driver = this.loadNewInstance(context);*/
		
		String caseNumber = context.getAttribute("caseNumber").toString();
		// Login to TSM application
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD TSM ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Open Omni channel widget
		tsmAdvisorStatesPage.clickOmniChannel();
		// Select Presence status as Available - Email
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search CaseID in TSM application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Verify Case Details
		String CaseID = tsmCaseDetailsPage.verifyCaseID();
		assertEquals(CaseID, caseNumber, "Case number not matched");
		String strsubject = tsmCaseDetailsPage.verifySubject();
		assertEquals(strsubject, testData.get("subject"), "Subject data is matched");
		/*String description = tsmCaseDetailsPage.verifyDescription();
		assertEquals(description, testData.get("tsmdescription"), "Description data is matched");*/
		String product = tsmCaseDetailsPage.verifyProduct();
		assertEquals(product, testData.get("tsmproduct"), "Product data is matched");
		String platform = tsmCaseDetailsPage.verifyPlatform();
		assertEquals(platform, testData.get("tsmplatform"), "Platform data is matched");
		String category = tsmCaseDetailsPage.verifycategory();
		assertEquals(category, testData.get("tsmcategory"), "Category data is matched");
		String subcategory = tsmCaseDetailsPage.verifysubcategory();
		assertEquals(subcategory, testData.get("tsmsubcategory"), "Issue data is matched");
		// Close Case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
