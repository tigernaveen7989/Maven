package com.ea.wwce.automation.sovereign.api.tests;

import static org.testng.Assert.assertNotNull;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;



public class POSTTwoFactorAuthCodesApiTest extends SovereignAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "POST_TWO_FACTOR_AUTH_CODES");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify POST Generate Two-Factor Authentication Codes API", groups = { "Sanity" })
	@Description("verifyPOST Generate Two-Factor Authentication Codes API")
	public void verifyPostTwoFactorAuthCodesTest(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49816";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("codeType").toString()),	"Verify codeType is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("email").toString()),	"Verify email is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("status").toString()),	"Verify status is as expected");
		assertNotNull(assertions.get("code1").toString(),	"Verify code1 is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("isExpired").toString()),	"Verify isExpired is as expected");
		assertNotNull(base.getResponseData().contains(assertions.get("code2").toString()),	"Verify code2 is as expected");
		
		assertAll();

	}
 
}
