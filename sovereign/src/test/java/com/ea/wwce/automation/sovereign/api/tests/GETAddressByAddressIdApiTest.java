package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETAddressByAddressIdApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_ADDRESS_BY_ADDRESS_ID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify GET Address By Address Id API", groups = { "Sanity" })
	@Description("Verify GET Address By Address Id API")
	public void verifyAddressByAddressIdAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49194";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("addressId").toString()),"Verify addressId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("userId").toString()),"Verify userId is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("street").toString()),"Verify street is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("city").toString()),"Verify city is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("state").toString()),"Verify state is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("zip").toString()),"Verify zip is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("country").toString()),"Verify country is as exepected");
		assertAll();
	}

}
