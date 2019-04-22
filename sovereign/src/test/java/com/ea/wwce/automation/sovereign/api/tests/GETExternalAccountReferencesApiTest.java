package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETExternalAccountReferencesApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETExternalAccountReferencesApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_EXTERNAL_ACCOUNT_REFERENCES");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify Chat Wait Time API", groups = { "Sanity" })
	@Description("Verification of Chat Wait time API")
	public void verifyExternalAccountReferencesAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "43769";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("referenceId").toString()),"Verify reference Id is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("referenceType").toString()),"Verify reference Type is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("referenceValue").toString()),"Verify reference Value is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("discoverable").toString()),"Verify discoverable is as exepected");

		assertAll();
	}

}
