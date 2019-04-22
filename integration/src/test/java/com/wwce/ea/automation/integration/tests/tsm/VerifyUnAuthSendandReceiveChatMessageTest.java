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
 * @description This Test is to Create UnAuth Chat case in EAHelp, Verify send
 *              and receive message from Player and Advisor.
 * 
 */

public class VerifyUnAuthSendandReceiveChatMessageTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyUnAuthSendandReceiveChatMessageTest.class);

	@Test(description = "Verify UnAuth Chat Send and Receive chat message", groups = { "Regression", "Sanity" })
	@Description("Verify UnAuth Chat Send and Receive chat message")
	public void verifyUnAuthSendandReceiveChatMessage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "21164, 21166";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify UnAuth Chat Send and Receive chat message" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login to TSM application in to chat advisor
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL,
				testData.get("username_chatAdvisor").toString(), testData.get("password_chatAdvisor").toString());
		// Wait for Chat spinner
		tsmChatPage.chatspinner();
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
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Select case configuration
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("product").toString(),
				testData.get("platform").toString(), testData.get("category").toString(),
				testData.get("subcategory").toString());

		// Submit unauth login to form
		eaHelpUnAuthCaseInformationPage.submitUnAuthLoginForm(testData.get("FName").toString(),
				testData.get("LName").toString(), testData.get("Email").toString());
		// Submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.chat, testData.get("subject").toString(),
				testData.get("description").toString(), testData.get("phonenumber").toString());
		// Click on Request live chat button
		eaHelpChannelSelectionPage.clickChatNow();
		// Verify Chat popup is displayed
		tsmeahelpChannelSelectionPage.verifyChatPopup();
		// Send Chat message from player
		tsmeahelpChannelSelectionPage.sendChatMessage(testData.get("chatmessage").toString());
		// Switch to tsm
		this.switchToInstance(mDriverInstance.get("TSM"));
		// Wait for Chat spinner
		tsmChatPage.chatspinner();
		// Verify the Message received by advisor
		String playerchatMessage = tsmChatPage.verifyMessagefromPlayer();
		assertTrue(playerchatMessage.equalsIgnoreCase(testData.get("chatmessage").toString()),
				"Chat Message for player is not matched");
		// Send Chat message from Advisor
		tsmChatPage.sendMessagefromAdvisor(testData.get("sendMessagefromAdv").toString());
		// Switch to tsm
		this.switchToInstance(mDriverInstance.get("EAHELP"));
		// Verify the Message received by Player
		String advisorchatMessage = tsmeahelpChannelSelectionPage.verifyMessagefromAdvisor();
		assertTrue(advisorchatMessage.equalsIgnoreCase(testData.get("sendMessagefromAdv").toString()),
				"Chat Message from Advisor is matched");

		// Close chat
		tsmeahelpChannelSelectionPage.gobackbutton();
		tsmeahelpChannelSelectionPage.closeChatPopup();

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
