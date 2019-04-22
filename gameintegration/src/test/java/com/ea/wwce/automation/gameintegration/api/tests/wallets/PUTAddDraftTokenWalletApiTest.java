package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class PUTAddDraftTokenWalletApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(PUTAddDraftTokenWalletApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "ADD_DRAFTTOKEN_WALLET_CURRENCY_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify ADD DRAFT TOKEN WALLET CURRENCY API ",groups={"Sanity"})
	@Description("Verification of ADD DRAFT TOKEN WALLETS API for Product")
	public void verifyAddDraftTokenWalletsAPI(ITestContext context){	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "14389";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating ADD DRAFT TOKEN Wallets for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
