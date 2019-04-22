package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mozilla.javascript.json.JsonParser.ParseException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;
import com.google.gson.JsonObject;

import io.qameta.allure.Description;

public class PUTAddWalletWithMissingIdApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(PUTAddWalletWithMissingIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "ADD_WALLET_MISSING_ID_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify ADD WALLET CURRENCY WITH ID  MISSING", groups = { "Sanity" })
	@Description("Verification of ADD WALLETS CURRENCY WITH ID  MISSING")
	public void verifyAddWalletsWithMissingIdAPI(ITestContext context)
			throws ParseException, org.json.simple.parser.ParseException {

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "23048";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 	

		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Validating Wallets for Missing ID " + testCaseID);	

		base.validateResponse();
	
		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertTrue(base.getResponseData().contains(assertions.get("status").toString()) , 
				"Verify GET Wallets API with Missing ID gives 'Status = ERROR'");
		this.assertTrue(base.getResponseData().contains(assertions.get("errorCode").toString()), 
				"Verify GET Wallets API with Missing ID throws error");
		this.assertTrue(base.getResponseData().contains(assertions.get("sourceMessage").toString()), 
				"Verify GET Wallets API with Missing ID throws error message 'Parameter id Missing'");
		assertAll();

	}
}
