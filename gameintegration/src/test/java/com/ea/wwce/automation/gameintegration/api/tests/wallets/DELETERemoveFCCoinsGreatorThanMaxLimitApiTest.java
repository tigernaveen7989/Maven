package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class DELETERemoveFCCoinsGreatorThanMaxLimitApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(DELETERemoveFCCoinsGreatorThanMaxLimitApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "REMOVE_MAX_FCCOINS_WALLET_CURRENCY_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify REMOVE FC Coins WALLET CURRENCY GREATOR THAN MAX LIMIT API ",groups={"Sanity"})
	@Description("Verification of REMOVE FC Coins WALLETS CURRENCY GREATOR THAN MAX LIMIT API ")
	public void verifyMaxRemoveFCCoinsWalletsAPI(ITestContext context){	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "14399";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating REMOVE FC COINS Wallets Greator than MAX Limit for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "ERROR");
		assertAll();
	}
}
