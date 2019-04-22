package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETExternalUserDetailApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETExternalUserDetailApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_EXTERNAL_USER_DETAIL");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify GET External User Detail API", groups = { "Sanity" })
	@Description("Verify GET External User Detail API")
	public void verifyExternalUserDetailIAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49852";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("nucleusUserId").toString()),"Verify nucleusUserId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("externalUserType").toString()),"Verify externalUserType is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("externalUserId").toString()),"Verify externalUserId is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("personaId").toString()),"Verify personaId is as exepected");
		
		assertAll();
	}

}
