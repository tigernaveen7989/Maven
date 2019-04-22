package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETTwoFactorAuthenticationApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETTwoFactorAuthenticationApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_TWO_FACTOR_AUTH");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify User's Two-Factor Authentication API", groups = { "Sanity" })
	@Description("Verify GET User's Two-Factor Authentication API")
	public void verifyChatTimeWaitTimeAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49148";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("codeType").toString()),"Verify codeType is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("email").toString()),"Verify email is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("status").toString()),"Verify status is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("code").toString()),"Verify code is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("isExpired").toString()),"Verify isExpired is as exepected");
	

		assertAll();
	}

}
