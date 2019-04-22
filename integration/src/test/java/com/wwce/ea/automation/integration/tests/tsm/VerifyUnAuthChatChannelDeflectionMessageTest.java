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
 * @description This Test is to Verify UnAuth Chat Deflection message in EAHelp.
 *              
 */

public class VerifyUnAuthChatChannelDeflectionMessageTest extends TSMIntegrationBaseTest {
	
	public static Logger logger = Logger.getLogger(VerifyUnAuthChatChannelDeflectionMessageTest.class);

	@Test(description = "Verify UnAuthChat channel Deflection Message", groups = { "Regression", "Sanity" })
	@Description("Verify UnAuthChat channel Deflection Message")
	public void verifyUnAuthChatchannelDeflectionMessage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "18917, 18947, 18948";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify UnAuthChat channel Deflection Message" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("product").toString(),
				testData.get("platform").toString(), testData.get("category").toString(),
				testData.get("subcategory").toString());

		// Submit unauth login to form
		eaHelpUnAuthCaseInformationPage.submitUnAuthLoginForm(testData.get("FName").toString(),
				testData.get("LName").toString(), testData.get("Email").toString());
		String defletionmessage = eaHelpChannelSelectionPage.verifyChatdeflection();
		assertTrue(defletionmessage.equalsIgnoreCase(testData.get("defletionmessage").toString()),
				"Verified Deflection message");
		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
