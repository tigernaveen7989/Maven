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

public class DELETERemoveWalletWithMissingAmountApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(DELETERemoveWalletWithMissingAmountApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "REMOVE_WALLET_MISSING_AMOUNT_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "Verify REMOVE WALLET CURRENCY WITH AMOUNT MISSING", groups = { "Sanity" })
	@Description("Verification of REMOVE WALLETS CURRENCY WITH AMOUNT MISSING")
	public void verifyRemoveWalletsWithMissingAmountAPI(ITestContext context)
			throws ParseException, org.json.simple.parser.ParseException {

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "23050";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 	

		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Validating Remove Wallets for Missing Amount" + testCaseID);	

		base.validateResponse();
	
		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertTrue(base.getResponseData().contains(assertions.get("status").toString()) , 
				"Verify REMOVE Wallets API with Missing Amount gives 'Status = ERROR'");
		this.assertTrue(base.getResponseData().contains(assertions.get("errorCode").toString()), 
				"Verify REMOVE Wallets API with Missing Amount throws error");
		this.assertTrue(base.getResponseData().contains(assertions.get("sourceMessage").toString()), 
				"Verify REMOVE Wallets API with Missing Amount throws error message 'Parameter amount Missing'");
		assertAll();

	}
}
