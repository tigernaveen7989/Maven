package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETCustomerLoginHistoryApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETCustomerLoginHistoryApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_CUSTOMER_LOGIN_HISTORY");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify GET Customer login history API", groups = { "Sanity" })
	@Description("Verify GET Customer login history API")
	public void verifyCustomerLoginHistory(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49850";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");
		

			// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("sequenceId").toString()),"Verify sequenceId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("method").toString()),"Verify method is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("pid").toString()),"Verify pid is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("result").toString()),"Verify result is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("timestamp").toString()),"Verify timestamp is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("clientId").toString()),"Verify clientId is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("ip").toString()),"Verify ip is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("ipGeolocation").toString()),"Verify ipGeolocation is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("machineProfileId").toString()),"Verify machineProfileId is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("totalResultCount").toString()),"Verify totalResultCount is as exepected");
		
		assertAll();
	}

}
