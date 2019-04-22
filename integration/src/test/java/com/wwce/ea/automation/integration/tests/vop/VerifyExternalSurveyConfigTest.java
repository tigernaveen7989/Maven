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
public class VerifyExternalSurveyConfigTest extends VOPIntegrationBaseTest {

	@Test(description = "Validate Survey window for In Moment Survey when Customer click on End Chat link.", groups = {
			"Regression", "Sanity" })
	@Description("Validate Survey window for In Moment Survey when Customer click on End Chat link.")
	public void verifyAuthChatCaseAndTransferCaseTest(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "1234";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// Verify SF login
		salesforceLoginPage.loginToSalesForce(IntegrationDataConstants.INTEGRATION_SF_VOP_AUT_URL,
				testData.get("SF_username").toString(), testData.get("SF_password").toString());

		/*assertEquals(salesforceHomePage.getUserName(), testData.get("SFUserName").toString(),
				"Verify username is available");*/

		//Navigate to SF classic
		salesforceLoginPage.clickOnUserProfile();
		salesforceLoginPage.switchToSFClassic();
		//Enter Chat Config value
		salesforceLoginPage.csatChatExtrnalSurveyConfig(testData.get("chatconfig_Value").toString());
		//Switch to SF Lightning Link
		salesforceLoginPage.clickOnSFLightningLnk();
		salesforceLoginPage.clickOnUserProfile();
		//Logout from SF
		salesforceLoginPage.logout();
			
		
		// assert all
		assertAll();

	}

	}
