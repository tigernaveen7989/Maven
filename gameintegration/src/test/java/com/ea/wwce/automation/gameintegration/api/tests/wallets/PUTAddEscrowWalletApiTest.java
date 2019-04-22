package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mozilla.javascript.json.JsonParser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;
import com.google.gson.JsonObject;

import io.qameta.allure.Description;

public class PUTAddEscrowWalletApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(PUTAddEscrowWalletApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "ADD_ESCROW_WALLET_CURRENCY_API");
		super.beforeMethod(context);
	}

	@Test(description = "Verify ADD ESCROW WALLET CURRENCY API ", groups = { "Sanity" })
	@Description("Verification of ADD ESCROW WALLETS API for Product")
	public void verifyAddEscrowWalletsAPI(ITestContext context)
			throws ParseException, org.json.simple.parser.ParseException {

		// Map the test ID to the automation result for automated updated in
		// TestRail
		String testCaseID = "14388";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating ADD ESCROW Wallets for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
