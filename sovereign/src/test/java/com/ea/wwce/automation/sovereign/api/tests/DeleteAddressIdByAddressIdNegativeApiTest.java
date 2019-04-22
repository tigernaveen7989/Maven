package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class DeleteAddressIdByAddressIdNegativeApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "DELETE_ADDRESS_BY_ADDRESS_ID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify DELETE User Security State API", groups = { "Sanity" })
	@Description("verify DELETE User Security State API")
	public void verifyDeleteAddressIdByAddressIdApiTest(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49826";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("errorShortMessage").toString()),	"Verify errorShortMessage is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("errorMessage").toString()),	"Verify errorMessage is as expected");
		
		assertAll();

	}

}
