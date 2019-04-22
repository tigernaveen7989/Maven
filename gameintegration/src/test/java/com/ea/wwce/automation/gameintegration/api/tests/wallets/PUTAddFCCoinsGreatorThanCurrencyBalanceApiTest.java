package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class PUTAddFCCoinsGreatorThanCurrencyBalanceApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(PUTAddFCCoinsWalletApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "ADD_FCCOINS_GREATOR_THAN_CURRENCY_BALANCE_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify ADD FC_COINS WALLET CURRENCY GREATOR THAN CURRENCY BALANCE API ",groups={"Sanity"})
	@Description("Verification of ADD FC_COINS WALLETS GREATOR THAN CURRENCY BALANCE API for Product")
	public void verifyAddMaxFCCoinsWalletsAPI(ITestContext context){	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "19083";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating ADD FC_COINS Wallets Greator than Currency Balance for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "ERROR");
		assertAll();
	}

}
