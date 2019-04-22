package com.ea.wwce.automation.gameintegration.api.tests.overview;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETGameModeTypeApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(GETGameModeTypeApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_GAME_MODE_TYPE_API");
		super.beforeMethod(context);
	}
	
	@Test(description ="Verify GET GAME MODETYPE API ",groups={"Sanity"})
	@Description("Verification of GAME MODETYPE API for Product")
	public void verifyGameModeTypeAPI(ITestContext context) throws ParseException{
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "18883";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating GAME MODETYPE API for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
