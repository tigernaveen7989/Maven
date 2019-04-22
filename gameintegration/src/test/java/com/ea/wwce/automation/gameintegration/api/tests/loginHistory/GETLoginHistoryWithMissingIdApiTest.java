package com.ea.wwce.automation.gameintegration.api.tests.loginHistory;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;


public class GETLoginHistoryWithMissingIdApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETLoginHistoryWithMissingIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_LOGINHISTORY_MISSING_ID_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="Verify GET LOGIN HISTORY WITH MISSING ID API ",groups={"Sanity"})
	@Description("Verification of GET LOGIN HISTORY WITH MISSING ID API for Product")
	public void verifyLoginHistoryMissingIdAPI(ITestContext context) throws ParseException{

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "27102";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 

		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Validating LOGIN HISTORY with Missing ID for product" + testCaseID);	

		base.validateResponse();
		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertTrue(base.getResponseData().contains(assertions.get("status").toString()) , "SUCCESSFUL");
		this.assertTrue(base.getResponseData().contains(assertions.get("errorCode").toString()),
				"Verify Login History details contains errorcode as 'ORG-INT-09998' when missing id");
		this.assertTrue(base.getResponseData().contains(assertions.get("errorCode").toString()),
				"Verify Login History details contains source message as 'Parameter id Missing' when missing id");
		assertAll();
	}
}
