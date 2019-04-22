package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETNucleasAccountDetailsApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETNucleasAccountDetailsApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_NUCLEAS_ACCOUNT_DETAILS_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify Nucleas Account details API", groups = { "Sanity" })
	@Description("Verification of Nucleas account details")
	public void verifyNucleasAccountDetails(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "43486";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("userId").toString()),
				"Verify expected userId is present");
		assertTrue(base.getResponseData().contains(assertions.get("mobileNumber").toString()),
				"Verify expected mobileNumber is present");
		assertTrue(base.getResponseData().contains(assertions.get("securityQuestion1").toString()),
				"Verify expected securityQuestion1 is present");
		assertTrue(base.getResponseData().contains(assertions.get("securityAnswer1").toString()),
				"Verify expected securityAnswer1 is present");
		assertTrue(base.getResponseData().contains(assertions.get("securityQuestion2").toString()),
				"Verify expected securityQuestion2  is present");
		assertTrue(base.getResponseData().contains(assertions.get("securityAnswer2").toString()),
				"Verify expected securityAnswer2 is present");
		assertTrue(base.getResponseData().contains(assertions.get("securityQuestion3").toString()),
				"Verify expected securityQuestion3 is present");
		assertTrue(base.getResponseData().contains(assertions.get("securityAnswer3").toString()),
				"Verify expected securityAnswer3 is present");
		assertTrue(base.getResponseData().contains(assertions.get("securityState").toString()),
				"Verify expected securityState present");
		assertTrue(base.getResponseData().contains(assertions.get("userValue").toString()),
				"Verify expected userValue present");

		assertAll();
	}

}
