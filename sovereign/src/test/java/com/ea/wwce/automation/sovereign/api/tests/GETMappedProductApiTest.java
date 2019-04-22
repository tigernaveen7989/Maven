package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETMappedProductApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETMappedProductApiTest.class);
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
		String testCaseID = "49860";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("crmProductName_1").toString()),"Verify crmProductName_1 is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("productDisplayName_1").toString()),"Verify externalUserType is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("platform_1").toString()),"Verify platform_1 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("status_1").toString()),"Verify status_1 is as exepected");
		
		assertTrue(base.getResponseData().contains(assertions.get("crmProductName_2").toString()),"Verify crmProductName_2 is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("productDisplayName_2").toString()),"Verify productDisplayName_2 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("platform_2").toString()),"Verify platform_2 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("status_2").toString()),"Verify status_2 is as exepected");
		
		
		assertTrue(base.getResponseData().contains(assertions.get("crmProductName_3").toString()),"Verify crmProductName_3 is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("productDisplayName_3").toString()),"Verify productDisplayName_3 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("platform_3").toString()),"Verify platform_3 is as exepected");
	    assertTrue(base.getResponseData().contains(assertions.get("status_3").toString()),"Verify status_3 is as exepected");
		
		assertAll();
	}

}
