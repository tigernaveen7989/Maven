package com.wwce.ea.automation.integration.tests.tsm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

/**
 * 
 * @author rgandham
 * @description This Test is to Create Auth Email case in EAHelp, Verify Case
 *              details in TSM Application.
 * 
 */

public class VerifyEmailCaseRoutingTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyEmailCaseRoutingTest.class);

	@Test(description = "Verfiy Email Case Routing", groups = { "Regression", "Sanity" })
	@Description("Verfiy Email Case Routing")
	public void verifyEmailCaseRoutingTest(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "15977";
		context.setAttribute("testcase_id", testID);
		logger.info("Verfiy Email Case Routing" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

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

		// Create driver instance to open EAHELP website
		this.driver = this.loadNewInstance(context);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);

		assertNotNull(caseNumber, "Verify case number is not null");

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Switch to TSM application
		this.switchToInstance(mDriverInstance.get("TSM"));

		/*// Search CaseID in TSM application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");*/
		// Verify Case Details
		String CaseID = tsmCaseDetailsPage.verifyCaseID();
		assertEquals(CaseID, caseNumber, "Case number not matched");

		// Close Case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
