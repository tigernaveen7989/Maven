package com.ea.wwce.automation.gameintegration.api.tests.matches;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETMatchCategoriesApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(GETMatchCategoriesApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_MATCH_CATEGORIES_API");
		super.beforeMethod(context);
	}
	
	@Test(description ="Verify GET MATCH CATEGORIES API ",groups={"Sanity"})
	@Description("Verification of GET MATCH CATEGORIES API for Product")
	public void verifyMatchCategoriesAPI(ITestContext context) throws ParseException{
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "19839";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating Challenges History for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
