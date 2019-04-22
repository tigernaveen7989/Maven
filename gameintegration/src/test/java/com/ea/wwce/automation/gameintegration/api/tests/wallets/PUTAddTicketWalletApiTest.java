package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import org.apache.log4j.Logger;
import org.mozilla.javascript.json.JsonParser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class PUTAddTicketWalletApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(PUTAddTicketWalletApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "ADD_TICKETS_WALLET_CURRENCY_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify ADD TICKETS WALLET CURRENCY API ",groups={"Sanity"})
	@Description("Verification of ADD TICKETS WALLETS API for Product")
	public void verifyAddTicketWalletsAPI(ITestContext context) throws ParseException {	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "14819";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating ADD TICKETS Wallets for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
