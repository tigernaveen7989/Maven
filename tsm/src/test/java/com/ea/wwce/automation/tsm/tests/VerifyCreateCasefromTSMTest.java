package com.ea.wwce.automation.tsm.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.tsm.config.TSMDataConstants;

import io.qameta.allure.Description;

public class VerifyCreateCasefromTSMTest extends TSMBaseTest {

	public static Logger logger = Logger.getLogger(VerifyCreateCasefromTSMTest.class);

	@Test(description = "Verify Create Case")
	@Description("Verify Create Case")
	public void verifyCreateCasefromTSM(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "15821, 40702, 40703, 40704, 40705";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Create Case" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// validate Login
		tsmLoginPage.loginToTSM(TSMDataConstants.TSM_AUT_URL, testData.get("username").toString(), testData.get("password").toString());
		// Open Omni Channel window
		tsmAdvisorStatesPage.clickOmniChannel();
		// Verify Advisor selects Available -Email
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search for player account
		tsmCreateCasePage.searchPlayerAcc(testData.get("playerAcc").toString());
		//Verify Disabled field 
		tsmCreateCasePage.verifyDisabledfields();
		// Create case
		tsmCreateCasePage.createCase(testData.get("productVal").toString(), testData.get("platformVal").toString(),
				testData.get("categoryVal").toString(), testData.get("subcategoryVal").toString(),
				testData.get("casesubject").toString());
		// Verify case is created
		tsmCreateCasePage.verifyCase();
		// Close player account tab
		tsmCreateCasePage.closePlayerAcc(testData.get("playerAcc").toString());
		assertAll();
	}
}
