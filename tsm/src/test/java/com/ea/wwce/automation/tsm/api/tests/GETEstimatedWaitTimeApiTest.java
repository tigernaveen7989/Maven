package com.ea.wwce.automation.tsm.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETEstimatedWaitTimeApiTest extends TSMAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETEstimatedWaitTimeApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_ESTIMATED_WAIT_TIME_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify Chat Wait Time API", groups = { "Sanity" })
	@Description("Verification of Chat Wait time API")

	public void verifyChatTimeWaitAPI(ITestContext context) {
		// Map the test ID to the automation result for automated updated in
		// TestRail
		String testCaseID = "20485";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(base.getResponseCode(), Integer.parseInt(assertions.get("response_code").toString()),
				"Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("status").toString());

		assertTrue(base.getResponseData().contains(assertions.get("skillName").toString()), 
				"skill Name found");
		assertTrue(base.getResponseData().contains(assertions.get("skillLocale").toString()), 
				"skill Localefound");
		assertTrue(base.getResponseData().contains(assertions.get("skillDescription").toString()), 
				"skill Description found");
		assertTrue(base.getResponseData().contains(assertions.get("waitTimeInSeconds").toString()), 
				"waitTimeInSeconds found");
		assertTrue(base.getResponseData().contains(assertions.get("score").toString()), 
				"score found");
		assertTrue(base.getResponseData().contains(assertions.get("inBusinessHours").toString()), 
				"Business Hours found");
		assertTrue(base.getResponseData().contains(assertions.get("buttonId").toString()), 
				"Button id found");
		//base.getResponseData().containsKey(assertions.get("buttonId").toString());
		
		assertAll();
	}

	

}
