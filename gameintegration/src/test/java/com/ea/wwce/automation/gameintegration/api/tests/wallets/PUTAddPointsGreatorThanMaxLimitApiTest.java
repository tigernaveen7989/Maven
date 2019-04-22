package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class PUTAddPointsGreatorThanMaxLimitApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(PUTAddPointsGreatorThanMaxLimitApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "ADD_MAX_POINTS_WALLET_CURRENCY_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify ADD POINTS WALLET CURRENCY GREATOR THAN MAX LIMIT API ",groups={"Sanity"})
	@Description("Verification of ADD POINTS WALLETS GREATOR THAN MAX LIMIT API for Product")
	public void verifyAddMaxPointsWalletsAPI(ITestContext context){	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "14397";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating ADD POINTS Wallets Greator than MAX Limit for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");
		this.assertKeyValue("status", base.getResponseData(), "ERROR");
		assertAll();
	}
}
