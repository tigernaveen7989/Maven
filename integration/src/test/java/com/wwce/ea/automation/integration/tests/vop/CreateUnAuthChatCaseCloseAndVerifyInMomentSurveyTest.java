package com.wwce.ea.automation.integration.tests.vop;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import io.qameta.allure.Description;

public class CreateUnAuthChatCaseCloseAndVerifyInMomentSurveyTest extends VOPIntegrationBaseTest {

	@Test(description = "Validate Survey window display for In Moment Survey when Advisor click on End Chat link.", groups = { "Regression", "Sanity" })
	@Description("Validate Survey window display for In Moment Survey when Advisor click on End Chat link.")
	public void verifyUnAuthChatInHouseSurvey(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "43292";
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

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_VOP_AUT_URL);

		// LOAD EAHelp ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// Select platform
		eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

		// Select category
		eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());

		// Select sub category
		eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());

		// Click on select contact option
		eaHelpCaseInformationPage.clickOnSelectContactOption();

		// Submit unauth login to form
		eaHelpUnAuthCaseInformationPage.submitUnAuthLoginForm(testData.get("FName").toString(),
				testData.get("LName").toString(), testData.get("Email").toString());
		// Submit channel form
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.chat, testData.get("subject").toString(),
				testData.get("description").toString(), testData.get("phonenumber").toString());
				
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
		    eahelpSurveyPage.isInMomentSurveySubmitted(testData.get("InMomentSurveyText").toString());
		//Validate Feedback form 				
			String note = eahelpSurveyPage.getFeedback();
			assertTrue(note.contains(testData.get("txtfeedback").toString()), "Feedback detail page displayed");
		
		// Assert all
		assertAll();

	}
}
