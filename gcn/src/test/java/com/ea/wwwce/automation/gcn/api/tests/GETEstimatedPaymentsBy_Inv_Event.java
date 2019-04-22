package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETEstimatedPaymentsBy_Inv_Event extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETEstimatedPaymentsBy_Inv_Event.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_ESTIMATED_PAYMENTS_BY_EVENT");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify GET Estimated Paments by Invalid Event",groups={"regression"})
	@Description("Verification of GET Estimated Paments by Invalid Event")
	public void verifyEstimatedPaymentByInvEvent(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20405";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating GET Estimated Paments by Invalid Event : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals(3,Integer.parseInt(assertions.get("errorCode").toString()) ,"Matching Error Code");
		
		assertAll();
	}


}
