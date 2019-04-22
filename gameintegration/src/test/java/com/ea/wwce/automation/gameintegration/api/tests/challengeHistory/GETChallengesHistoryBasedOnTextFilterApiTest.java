package com.ea.wwce.automation.gameintegration.api.tests.challengeHistory;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETChallengesHistoryBasedOnTextFilterApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(GETChallengesHistoryBasedOnTextFilterApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_CHALLENGES_HISTORY_TEXTFILTER_API");
		super.beforeMethod(context);
	}
	
	@Test(description ="Verify GET CHALLENGES HISTORY BASED ON TEXT FILTER API ",groups={"Sanity"})
	@Description("Verification of GET CHALLENGES HISTORY API BASED ON TEXT FILTER for Product")
	public void verifyChallengesHistoryBasedOnTextFilterAPI(ITestContext context) throws ParseException{
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "18556";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating Challenges History based on TEXT filter for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
