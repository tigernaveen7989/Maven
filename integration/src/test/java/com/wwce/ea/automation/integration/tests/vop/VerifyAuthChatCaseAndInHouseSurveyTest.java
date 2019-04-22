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

import io.qameta.allure.Description;

/**
 * Verify that for Chat channel in arabic locale when user ends chat user gets a
 * post chat survey
 * 
 * @author praveen
 *
 */
public class VerifyAuthChatCaseAndInHouseSurveyTest extends VOPIntegrationBaseTest {

	@Test(description = "Validate Survey window for In House Survey when Customer click on End Chat link.", groups = {
			"Regression", "Sanity" })
	@Description("Validate Survey window for In House Survey when Customer click on End Chat link.")
	public void createChatCaseAndVerifySurveyCase(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "42286";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// set salesforce config for chat
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
		// Create Chat case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.chat);
		assertNotNull(caseNumber, "Verify case number is not null");

		// Switch to chat window
		chatWindowPage.switchToChatWidnow(testData.get("Chat_Window_Title").toString());
		// click on end chat button
		chatWindowPage.clickOnEndChatButton();		
		//if(chatWindowPage.getPageTitle()==testData.get("Chat_Window_Title").toString()))
		chatWindowPage.verifyChatSurveyPresent();		
		chatWindowPage.switchWindowByTitle(testData.get("Inhouse_Window_Title").toString());
		//submit InHouse survey
		assertTrue(eahelpSurveyPage.isInHouseSurveySubmitted(testData.get("InHouseSurveyText").toString()), "Survey completed");
		//eahelpSurveyPage.submitInHouseSurvey(testData.get("question1").toString(), testData.get("answer1").toString(), testData.get("question2").toString(), testData.get("answer2").toString(), testData.get("question3").toString(), testData.get("answer3").toString(), testData.get("question4").toString(), testData.get("answer4").toString(), testData.get("question5").toString(), testData.get("answer5").toString(), testData.get("question6").toString(), testData.get("answer6").toString(), testData.get("question7").toString(), testData.get("answer7").toString(), testData.get("InHouseSurveyText").toString());
				
		// assert all
		assertAll();

	}

	}
