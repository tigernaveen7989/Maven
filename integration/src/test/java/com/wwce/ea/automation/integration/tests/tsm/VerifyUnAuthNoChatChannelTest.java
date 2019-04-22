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
 * @description This Test is to Verify UnAuth No Chat channel in EAHlep.
 * 
 */

public class VerifyUnAuthNoChatChannelTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyUnAuthNoChatChannelTest.class);

	@Test(description = "Verify UnAuth No Chat channel", groups = { "Regression", "Sanity" })
	@Description("Verify UnAuth No Chat channel")
	public void verifyUnAuthChatchannelDeflectionMessage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "18920";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify UnAuth No Chat channel" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

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
		// Verify Chat channel
		assertFalse(eaHelpChannelSelectionPage.isChatChannelPresent(), "Chat channel is present");

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
