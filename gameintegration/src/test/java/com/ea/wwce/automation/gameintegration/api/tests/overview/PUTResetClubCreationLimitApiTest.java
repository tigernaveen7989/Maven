package com.ea.wwce.automation.gameintegration.api.tests.overview;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class PUTResetClubCreationLimitApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(PUTResetClubCreationLimitApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "PUT_RESET_CLUB_CREATION_LIMIT_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify RESET CLUB CREATION LIMIT API ",groups={"Sanity"})
	@Description("Verification of RESET CLUB CREATION LIMIT API for Product")
	public void verifyResetClubCreationLimitAPI(ITestContext context) throws ParseException{	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "19118";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating RESET CLUB CREATION LIMIT for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
