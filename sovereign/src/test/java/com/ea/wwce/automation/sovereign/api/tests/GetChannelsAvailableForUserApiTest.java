package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GetChannelsAvailableForUserApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_CHANNELS_AVAILABLE_FOR_USER_TO_CONTACT");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify Get Channels available for user to contact API", groups = { "Sanity" })
	@Description("verify Get Channels available for user to contact API")
	public void verifyGetChannelsAvailableForUser(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49969";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("channelRank").toString()),"Verify channelRank is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("channel").toString()),"Verify channel is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("channelMessage").toString()),"Verify channelMessage is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("skill").toString()),"Verify skill is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("notBussinessHours").toString()),"Verify notBussinessHours is as expected");
			
		assertAll();

	}

}
