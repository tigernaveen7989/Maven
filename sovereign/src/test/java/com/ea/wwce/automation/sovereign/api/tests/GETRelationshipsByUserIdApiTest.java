package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETRelationshipsByUserIdApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETRelationshipsByUserIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "RELATIONSHIPS_BY_USER_ID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify GET Relationships By UserId API", groups = { "Sanity" })
	@Description("Verify GET Relationships By UserId API")
	public void verifyChatTimeWaitTimeAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "43771";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("relationshipUri").toString()),"Verify relationship Uri is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("userUri").toString()),"Verify user Uri is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("relatedUserType").toString()),"Verify related User Type Value is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("relatedUserUri").toString()),"Verify relatedUser Uri is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("relationshipUri_1").toString()),"Verify relationship Uri_1 Value is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("relatedUserUri_1").toString()),"Verify related User Uri_1 is as exepected");

		assertAll();
	}

}
