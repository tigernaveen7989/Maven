package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETCustomerAccountChangeHistoryNegativeApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETCustomerAccountChangeHistoryNegativeApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_CUSTOMER_ACCOUNT_CHANGE_HISTORY");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify GET Customer account change history API", groups = { "Sanity" })
	@Description("Verification of GET Customer account change history API")
	public void verifyCustomerAccountChangeHistoryWithInvalidParameters(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49640";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions		
		assertTrue(base.getResponseData().contains(assertions.get("errorMessage").toString()),"Verify error Message value is present");
		assertTrue(base.getResponseData().contains(assertions.get("sourceMessage").toString()),"Verify sourceMessage value is present");
		
		assertAll();
	}

}
