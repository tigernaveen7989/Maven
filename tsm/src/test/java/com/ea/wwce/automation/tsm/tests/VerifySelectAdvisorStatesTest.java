package com.ea.wwce.automation.tsm.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.tsm.config.TSMDataConstants;

import io.qameta.allure.Description;

public class VerifySelectAdvisorStatesTest extends TSMBaseTest {

	public static Logger logger = Logger.getLogger(VerifySelectAdvisorStatesTest.class);

	@Test(description = "Select Advisor")
	@Description("Select Advisor")
	public void verifySelectAdvisorStates(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "15352,15353,16932,16933,15354,15355,21303,15352";

		context.setAttribute("testcase_id", testID);
		logger.info("Verify Advisor states" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		// LOAD TSM ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// validate Login
		tsmLoginPage.loginToTSM(TSMDataConstants.TSM_AUT_URL, testData.get("username").toString(), testData.get("password").toString());
		// Open Omni Channel window
		tsmAdvisorStatesPage.clickOmniChannel();
		assertTrue(tsmAdvisorStatesPage.selectOmniStatusAvailableEmail(), "Available- Email Selected");
		assertTrue(tsmAdvisorStatesPage.selectOmniStatusAvailableChat(), "Available- chat Selected");
		assertTrue(tsmAdvisorStatesPage.selectOmniStatusAvailableMeeting(), "Meeting Selected");
		/*assertTrue(tsmAdvisorStatesPage.selectOmniStatusAvailableTraining(), "Training Selected");*/
		assertTrue(tsmAdvisorStatesPage.selectOmniStatusAvailableOffline(), "Offline Selected");
		tsmAdvisorStatesPage.CloseOmnichannel();
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
