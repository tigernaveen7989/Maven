package com.ea.wwce.automation.gameintegration.api.tests.draftHistory;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETDraftHistoryApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(GETDraftHistoryApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_DRAFT_HISTORY_API");
		super.beforeMethod(context);
	}
	
	@Test(description ="Verify GET DRAFT HISTORY API ",groups={"Sanity"})
	@Description("Verification of GET DRAFT HISTORY API for Product")
	public void verifyDraftHistoryAPI(ITestContext context) throws ParseException{
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20811";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating Draft History for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
