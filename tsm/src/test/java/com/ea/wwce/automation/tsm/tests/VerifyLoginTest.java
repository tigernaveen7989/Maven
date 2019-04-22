package com.ea.wwce.automation.tsm.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.tsm.config.TSMDataConstants;

import io.qameta.allure.Description;

public class VerifyLoginTest extends TSMBaseTest {

	public static Logger logger = Logger.getLogger(VerifyLoginTest.class);

	@Test(description = "Login")
	@Description("Login")
	public void verifyLogin(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "15991";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify Login" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		// validate login
		tsmLoginPage.loginToTSM(TSMDataConstants.TSM_AUT_URL, testData.get("username").toString(), testData.get("password").toString());
		// open jobrole window
		tsmJobRoleSelectionPage.ClickOnJobRole();
		// Validate selected jobrole status
		//assertTrue(tsmJobRoleSelectionPage.getJobRoleStatus().equalsIgnoreCase(testData.get("JobRole").toString()),
	//			"Verifed Job Role of advsior");
		assertAll();

	}
}
