package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETWalletTransactionsBasedOnDateFilterApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETWalletTransactionsBasedOnTextFilterApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_WALLET_TRANS_DATEFILTER_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify GET WALLET TRANSACTIONS BASED ON DATE FILTER API ",groups={"Sanity"})
	@Description("Verification of GET WALLETS TRANSACTIONS BASED ON DATE FILTER API for Product")
	public void verifyWalletsAPI(ITestContext context) throws ParseException{	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "14404";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating Wallet Transactions based on date filter for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
