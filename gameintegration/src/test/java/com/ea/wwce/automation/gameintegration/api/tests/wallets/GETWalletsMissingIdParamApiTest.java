package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;


import io.qameta.allure.Description;

public class GETWalletsMissingIdParamApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETWalletsMissingIdParamApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_WALLETS_MISSING_ID_PARAM_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="Verify GET WALLETS API WITH MISSING ID PARAMETER ",groups={"Sanity"})
	@Description("Verification of GET WALLETS API WITH MISSING ID PARAMETER for Product")
	public void verifyWalletsAPIMissingIdParam(ITestContext context) throws ParseException{	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "21199";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 	

		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Validating Wallets for product having missing id parameter" + testCaseID);	

		base.validateResponse();
		
		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertTrue(base.getResponseData().contains(assertions.get("status").toString()) , 
				"Verify GET Wallets API with missing id parameter gives 'Status = ERROR'");
		this.assertTrue(base.getResponseData().contains(assertions.get("errorCode").toString()), 
				"Verify GET Wallets API with missing id parameter throws error");
		this.assertTrue(base.getResponseData().contains(assertions.get("sourceMessage").toString()), 
				"Verify GET Wallets API with missing id parameter throws error message 'Parameter id Missing'");
		assertAll();
	}
}
