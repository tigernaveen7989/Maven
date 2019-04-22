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


public class GETLoginHistoryApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETLoginHistoryApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_LOGINHISTORY_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="Verify GET LOGIN HISTORY API ",groups={"Sanity"})
	@Description("Verification of GET LOGIN HISTORY API for Product")
	public void verifyLoginHistoryAPI(ITestContext context) throws ParseException{

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "15316";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 

		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Validating LOGIN HISTORY for product" + testCaseID);	

		base.validateResponse();
		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertTrue(base.getResponseData().contains(assertions.get("status").toString()) , "SUCCESSFUL");
		this.assertTrue(base.getResponseData().contains(assertions.get("loginStatusSuccess").toString()),
				"Verify Login History contains login status as 'success'");
		this.assertTrue(base.getResponseData().contains(assertions.get("loginStatusFail").toString()),
				"Verify Login History contains login status as 'fail'");
		this.assertTrue(base.getResponseData().contains(assertions.get("loginSuccessfulTrue").toString()),
				"Verify Login History contains login successful as 'true'");
		this.assertTrue(base.getResponseData().contains(assertions.get("loginSuccessfulFalse").toString()),
				"Verify Login History contains login status as 'false'");
		this.assertTrue(base.getResponseData().contains(assertions.get("startTimeSuccess").toString()),
				"Verify Login History contains login start time successfully as 'Fri May 18 21:02:43 UTC 2018'");
		this.assertTrue(base.getResponseData().contains(assertions.get("startTimeFail").toString()),
				"Verify Login History contains login start time successfully as 'Wed May 02 23:46:03 UTC 2018'");
		assertAll();
	}
}
