package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * FY17-20461 Verify that controller is passing deep link parameter for a new
 * logged in In AHQ user
 * 
 * @author M1022570
 *
 */

public class EAHelpControllerIsPassingDeepLinkParameterForAHQToEAHelpTest extends EAHelpBaseTest {

	@Test(description = "Verify that controller is passing deep link parameter", groups = { "Regression", "Sanity" })
	@Description("Verify that controller is passing deep link parameter")
	public void VerifyControllerIsPassingDeepLinkParameterForAHQToEAHelp(ITestContext context)
			throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39266,40149";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(testData.get("AHQ_URL").toString());

		// validate login
		ahqLoginPage.LoginToAHQ(testData.get("username").toString(), testData.get("password").toString());

		// Navigate tp EA Help ICR page
		eaHelpLoginPage.loadEAHelp(
				context.getAttribute("BASE_SERVICE_URL").toString() + testData.get("EmailChannelURL").toString());

		// Verify User name is logged in
		assertEquals(eaHelpHomePage.getUserName().toLowerCase(), testData.get("user_name").toString(),
				"Verify user name is matcjing");

		// assert all
		assertAll();
	}

}
