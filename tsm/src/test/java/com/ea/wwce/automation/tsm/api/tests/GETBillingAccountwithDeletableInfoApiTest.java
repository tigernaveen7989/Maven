package com.ea.wwce.automation.tsm.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETBillingAccountwithDeletableInfoApiTest extends TSMAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETBillingAccountwithDeletableInfoApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_BILLINGACCOUNTS_DELETABLEINFO_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify Billing Accounts with deletable Info", groups = { "Sanity" })
	@Description("Verify Billing Accounts with deletable Info")
	public void verifyPendingInvoicesAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in
		// TestRail

		String testCaseID = "36033";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		assertEquals(base.getResponseCode(), Integer.parseInt(assertions.get("response_code").toString()),
				"Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("status").toString());
		
		
		assertAll();
	}

}
