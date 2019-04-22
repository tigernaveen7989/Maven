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
 * @description This Test is to Verify Auth Chat channel Estimated wait time in ICR Page.
 *              
 */

public class VerifyAuthChatEstimatedwaitTimeTest extends TSMIntegrationBaseTest {
	
	public static Logger logger = Logger.getLogger(VerifyAuthChatEstimatedwaitTimeTest.class);

	@Test(description = "Verify AuthChat channel estimated waittime", groups = { "Regression", "Sanity" })
	@Description("Verify AuthChat channel estimated waittime")
	public void verifyAuthChatEstimatedwaitTime(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "18802";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify AuthChat channel estimated waittime" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login to TSM application in to chat advisor
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// Open Omni channel widget
		tsmAdvisorStatesPage.clickOmniChannel();
		// Select Presence status as Available - Chat
		assertTrue(tsmAdvisorStatesPage.selectOmniStatusAvailableChat(), "Available chat Selected");
		tsmAdvisorStatesPage.clickOmniChannel();

		// Create driver instance to open EAHELP website
		this.driver = this.loadNewInstance(context);
		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Verify Chat option is enabled
		eaHelpChannelSelectionPage.verifyChannelButtonIsEnabled(testData, CaseType.chat);
		String EstmatedWaitTime = tsmeahelpChannelSelectionPage.verifyEstimatedwaitTime();
		assertNotNull(EstmatedWaitTime, "Verified Estimated wait time is not null");

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
