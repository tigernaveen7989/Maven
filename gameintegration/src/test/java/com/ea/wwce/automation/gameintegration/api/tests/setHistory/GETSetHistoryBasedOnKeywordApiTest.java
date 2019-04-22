package com.ea.wwce.automation.gameintegration.api.tests.setHistory;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETSetHistoryBasedOnKeywordApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(GETSetHistoryBasedOnKeywordApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_SETHISTORY_KEYWORDSEARCH_API");
		super.beforeMethod(context);
	}
	
	@Test(description ="Verify GET SET HISTORY API response based on keyword search ",groups={"Sanity"})
	@Description("Verification of SET HISTORY API for Product")
	public void verifySetHistoryBasedOnKeywordSearchAPI(ITestContext context) throws ParseException{	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "15344";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating SET History for product based on keyword search " + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
