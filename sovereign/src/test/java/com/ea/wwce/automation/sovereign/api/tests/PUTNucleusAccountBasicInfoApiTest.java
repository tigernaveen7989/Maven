package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class PUTNucleusAccountBasicInfoApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(PUTNucleusAccountBasicInfoApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "PUT_NUCLEUS_ACCOUNT_BASIC_INFO");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify PUT Nucleus Account Basic Info API", groups = { "Regression" })
	@Description("verify PUT Nucleus Account Basic Info API")
	public void verifyNucleusAccountBasicInfoAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49701";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("status").toString()),"Verify status is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("response").toString()),"Verify response is as exepected");
		assertAll();

	}

}
