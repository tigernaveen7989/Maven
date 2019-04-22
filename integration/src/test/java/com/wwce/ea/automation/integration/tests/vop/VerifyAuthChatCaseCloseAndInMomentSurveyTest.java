package com.wwce.ea.automation.integration.tests.vop;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpSurveyPage;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import com.ea.wwce.automation.salesforce.config.SalesforceDataConstants;

import io.qameta.allure.Description;

/**
 * Verify that for Chat channel in english locale when user ends chat user gets a
 * post chat survey
 * 
 * @author praveen
 *
 */
public class VerifyAuthChatCaseCloseAndInMomentSurveyTest extends VOPIntegrationBaseTest {

	@Test(description = "Validate Survey window for In Moment Survey when Customer click on End Chat link.", groups = {
			"Regression", "Sanity" })
	@Description("Validate Survey window for In Moment Survey when Advisor click on End Chat link.")
	public void createChatCaseAndVerifyInMomentSurveyCase(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "43188";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// Set salesforce config for chat
		salesforceLoginPage.setSalesForceConfigForChat(IntegrationDataConstants.INTEGRATION_SF_VOP_AUT_URL,
				testData.get("SF_username").toString(), testData.get("SF_password").toString(), testData.get("chatconfig_Value").toString());
					
		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_VOP_AUT_URL,
				testData.get("omega_username").toString(), testData.get("omega_password").toString(),
				testData.get("RoleName").toString());
		
		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);
		// Create driver instance to open eahelp website
		this.driver = this.loadNewInstance(context);
		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_VOP_AUT_URL);
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Verify loader is present
		eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(10);
		// Create chat case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.chat);

		assertNotNull(caseNumber, "Verify case number is not null");
		
		// Switch to chat window
		chatWindowPage.switchToChatWidnow(testData.get("Chat_Window_Title").toString());	
		// Send message from Player
		chatWindowPage.sendmsgfromPlayer(testData.get("chattext").toString());
		// Switch to Omega
		this.switchToInstance(mDriverInstance.get("OMEGA"));		
		// Send message from Advisor
		omegaSaveCaseConfirmationPage.sendmessagefromAdvisor(testData.get("chattext").toString());
		omegaSaveCaseConfirmationPage.clickOnEndChatFromAdvisor();
		omegaLoginPage.isSpinnerInvisible(30);
		Thread.sleep(2000);		
		//Switch to Eahlep
		this.switchToInstance(mDriverInstance.get("EAHELP"));		
		// Switch to chat window
		chatWindowPage.switchToChatWidnow(testData.get("Chat_Window_Title").toString());
		// click on end chat button
		chatWindowPage.clickOnEndChatButton();
		//Swith to InMoment survey window		
		chatWindowPage.switchWindowByTitle(testData.get("InMoment_Window_Title").toString());
		//Submit InMoment survey
		//chatWindowPage.waitAndSwitchToIframe(int ,String);		
		//Submit InMoment survey
		eahelpSurveyPage.isInMomentSurveySubmitted(testData.get("InMomentSurveyText").toString());
		//Validate Feedback form 				
		String note = eahelpSurveyPage.getFeedback();
		assertTrue(note.contains(testData.get("txtfeedback").toString()), "Feedback detail page displayed");
		
		// assert all
		assertAll();

	}

	}
