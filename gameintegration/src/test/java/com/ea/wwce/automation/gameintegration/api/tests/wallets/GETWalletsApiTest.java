package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.qameta.allure.Description;

public class GETWalletsApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETWalletsApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_WALLETS_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="Verify GET WALLETS API ",groups={"Sanity"})
	@Description("Verification of GET WALLETS API for Product")
	public void verifyWalletsAPI(ITestContext context) throws ParseException{	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "14386";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 	

		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Validating Wallets for product" + testCaseID);	

		base.validateResponse();

		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertTrue(base.getResponseData().contains(assertions.get("status").toString()) , "SUCCESSFUL");
		this.assertTrue(base.getResponseData().contains(assertions.get("walletIdPoints").toString()),
				"Verify POINTS Wallet is available for product");
		this.assertTrue(base.getResponseData().contains(assertions.get("walletIdCoins").toString()), 
				"Verify COINS Wallet is available for product");
		this.assertTrue(base.getResponseData().contains(assertions.get("walletIdDraftToken").toString()), 
				"Verify DRAFT TOKEN Wallet is available for product");
		this.assertTrue(base.getResponseData().contains(assertions.get("walletIdFCCoins").toString()), 
				"Verify FC COINS Wallet is available for product");
		this.assertTrue(base.getResponseData().contains(assertions.get("maxCrValuePoints").toString()), 
				"Verify Max Creditable Value for POINTS Wallet");
		this.assertTrue(base.getResponseData().contains(assertions.get("maxDrValuePoints").toString()), 
				"Verify Max Debitable Value for POINTS Wallet");
		this.assertTrue(base.getResponseData().contains(assertions.get("maxCrValueCoins").toString()),
				"Verify Max Creditable Value for COINS Wallet");
		this.assertTrue(base.getResponseData().contains(assertions.get("maxDrValueCoins").toString()), 
				"Verify Max Debitable Value for COINS Wallet");
		this.assertTrue(base.getResponseData().contains(assertions.get("maxCrValueFCCoins").toString()), 
				"Verify Max Creditable Value for FC COINS Wallet");
		this.assertTrue(base.getResponseData().contains(assertions.get("maxDrValueFCCoins").toString()), 
				"Verify Max Debitable Value for FC COINS Wallet");
		this.assertTrue(base.getResponseData().contains(assertions.get("maxCrValueDraftToken").toString()), 
				"Verify Max Creditable Value for DRAFT TOKEN Wallet");
		this.assertTrue(base.getResponseData().contains(assertions.get("maxDrValueDraftToken").toString()), 
				"Verify Max Debitable Value for DRAFT TOKEN Wallet");
		assertAll();
	}
}
