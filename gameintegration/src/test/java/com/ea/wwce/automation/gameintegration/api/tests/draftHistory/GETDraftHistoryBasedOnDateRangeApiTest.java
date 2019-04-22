package com.ea.wwce.automation.gameintegration.api.tests.draftHistory;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETDraftHistoryBasedOnDateRangeApiTest extends GameIntegrationAPIBaseTest{

	public static Logger logger = Logger.getLogger(GETDraftHistoryBasedOnDateRangeApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_DRAFT_HISTORY_DATERANGE_API");
		super.beforeMethod(context);
	}
	
	@Test(description ="Verify GET DRAFT HISTORY BASED ON DATE RANGE API ",groups={"Sanity"})
	@Description("Verification of GET DRAFT HISTORY BASED ON DATE RANGE API for Product")
	public void verifyDraftHistoryBasedOnDateRangeAPI(ITestContext context) throws ParseException{
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20812";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating Draft History based on date range for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
