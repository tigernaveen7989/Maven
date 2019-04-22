package com.ea.wwce.automation.gameintegration.api.tests.wallets;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETWalletsForIncorrectProductIDApiTest extends GameIntegrationAPIBaseTest {
	public static Logger logger = Logger.getLogger(GETWalletsForIncorrectProductIDApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_WALLETS_INCORRECT_PRODID_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="Verify GET WALLETS API FOR INCORRECT PRODUCT ID",groups={"Sanity"})
	@Description("Verification of GET WALLETS API FOR INCORRECT PRODUCT ID")
	public void verifyWalletsAPIIncorrectProdId(ITestContext context) throws ParseException{	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "21244";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 	

		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Validating Wallets for incorrect product id" + testCaseID);	

		base.validateResponse();
		
		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertTrue(base.getResponseData().contains(assertions.get("status").toString()) , 
				"Verify GET Wallets API with Incorrect Product ID gives 'Status = ERROR'");
		this.assertTrue(base.getResponseData().contains(assertions.get("errorCode").toString()), 
				"Verify GET Wallets API with Incorrect Product ID throws error");
		this.assertTrue(base.getResponseData().contains(assertions.get("sourceMessage").toString()), 
				"Verify GET Wallets API with Incorrect Product ID throws error message 'Product host configurations missing for product name<productname>'");
		assertAll();
	}
}
