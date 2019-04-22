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
 * @description This Test is to Create Auth chat case and verify account details
 *              of the player in Account basic info.
 * 
 */

public class VerifyAuthChatToCasematchTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyAuthChatToCasematchTest.class);

	@Test(description = "Verify AuthChat Chat to case match", groups = { "Regression", "Sanity" })
	@Description("Verify AuthChat Chat to case match")
	public void verifyAuthChatchannel(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "18832";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify AuthChat Chat to case match" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login to TSM application in to chat advisor
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// Open Omni channel widget
		tsmAdvisorStatesPage.clickOmniChannel();
		// Select Presence status as Available - Chat
		tsmAdvisorStatesPage.selectOmniStatusAvailableChat();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// LOAD TSM ISNTANCE
		mDriverInstance.put("TSM", this.driver);

		// Create driver instance to open EAHELP website
		this.driver = this.loadNewInstance(context);
		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// LOAD TSM ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Verify Chat channel is enabled
		eaHelpChannelSelectionPage.verifyChannelButtonIsEnabled(testData, CaseType.chat);
		// Click on Request live chat button
		eaHelpChannelSelectionPage.clickChatNow();
		// Verify Chat popup is displayed
		tsmeahelpChannelSelectionPage.verifyChatPopup();

		// Switch to tsm
		this.switchToInstance(mDriverInstance.get("TSM"));
		// Wait for Chat spinner
		tsmChatPage.chatspinner();
		// click on Account tab
		tsmAccountBasicInfoPage.clkOnAccount();
		// Validate Account Basic info in TSM
		assertTrue(tsmAccountBasicInfoPage.verifyAccountBasicInfoPage(testData.get("firstName").toString(),
				testData.get("lastName").toString(), testData.get("country").toString(),
				testData.get("dateofbirth").toString(), testData.get("persona").toString(),
				testData.get("nucleusID").toString(), testData.get("primaryemail").toString(),
				testData.get("phonenumber1").toString(), testData.get("language").toString(),
				testData.get("Customervalue").toString()), "Account details are matched");

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
