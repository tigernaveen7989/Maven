package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETGeoLocationDetailsApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_GEO_LOCATION_DETAILS_FOR_GIVEN_IP");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify GET geo location details for given IP", groups = { "Sanity" })
	@Description("Verification of GET geo location details for given IP")
	public void verifyGeoLocationDetails(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49635";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");
		
		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("ipAddress").toString()),"Verify ipAddress is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("country").toString()),"Verify country is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("region").toString()),"Verify region is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("city").toString()),"Verify city is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("zipcode").toString()),"Verify zipcode is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("latitude").toString()),"Verify latitude is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("longitude").toString()),"Verify longitude is as exepected");
		
		assertAll();

	}

}
