package com.ea.wwce.automation.tsm.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETLoginHistorywithLocationInfowithEmptyIDTest extends TSMAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETLoginHistorywithLocationInfowithEmptyIDTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_LOGIN_HISTORY_LOCATION_INFO_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify Get Login History with location info API", groups = { "Sanity" })
	@Description("Verify Get Login History with location info API")

	public void verifyChatTimeWaitAPI(ITestContext context) {
		// Map the test ID to the automation result for automated updated in
		// TestRail
		String testCaseID = "57329";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(base.getResponseCode(), Integer.parseInt(assertions.get("response_code").toString()),
				"Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("failed_status").toString());

		assertTrue(base.getResponseData().contains(assertions.get("errorMessage").toString()), 
				"Error message found");
		
		assertAll();
	}

	

}
