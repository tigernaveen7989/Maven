package com.ea.wwce.automation.tsm.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETPendingInvoicesNullAccountNumberApiTest extends TSMAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETPendingInvoicesNullAccountNumberApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_PENDING_INVOICE_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify Pending Invoices list with Account number empty", groups = { "Sanity" })
	@Description("verify Pending Invoices list with Account number empty")
	public void verifyPendingInvoicesNullAccountNumberAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in
		// TestRail
		String testCaseID = "35505";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		assertEquals(base.getResponseCode(), Integer.parseInt(assertions.get("response_code").toString()),
				"Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("failed_status").toString());
		assertTrue(base.getResponseData().contains(assertions.get("errorMessage").toString()),
				"invoice id found");
		assertTrue(base.getResponseData().contains(assertions.get("sourceMessage").toString()),
				"invoice id found");

		assertAll();
	}

}
