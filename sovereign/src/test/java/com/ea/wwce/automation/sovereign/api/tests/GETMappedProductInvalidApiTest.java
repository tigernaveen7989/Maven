package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETMappedProductInvalidApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETMappedProductInvalidApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_MAPPED_PRODUCT");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify GET Mapped Product API", groups = { "Sanity" })
	@Description("Verify GET Mapped Product API")
	public void verifyMappedProductDetailsAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49861";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("errorCode").toString()),"Verify errorCode is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("sourceMessage").toString()),"Verify externalUserType is as exepected");

		assertAll();
	}

}
