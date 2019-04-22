package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETNucleasAccountDetailsNegativeApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETNucleasAccountDetailsNegativeApiTest.class);
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
		String testCaseID = "43487";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");
		
		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("status").toString()),"Verify expected status value is present");
		assertTrue(base.getResponseData().contains(assertions.get("errorShortMessage").toString()),"Verify errorShort Message value is present");

		assertAll();
	}

}
