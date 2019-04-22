package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class DELETERemoveEscrowWalletApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(DELETERemoveEscrowWalletApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "REMOVE_ESCROW_WALLET_CURRENCY_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify REMOVE ESCROW WALLET CURRENCY API ",groups={"Sanity"})
	@Description("Verification of REMOVE ESCROW WALLETS API for Product")
	public void verifyRemoveEscrowWalletsAPI(ITestContext context){	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "15237";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating REMOVE ESCROW Wallets for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
