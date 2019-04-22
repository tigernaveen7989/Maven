package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETEstimatedPaymentsByEvent extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETEstimatedPaymentsByEvent.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_ESTIMATED_PAYMENTS_BY_EVENT");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify GET Estimated Payments by Event",groups={"regression"})
	@Description("Verification of GET Estimated Payments by Event")
	public void verifyGetEstimatedPaymentByEvent(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20395";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating GET Estimated Payments by Event : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals(262,Integer.parseInt(assertions.get("eventId").toString()) ,"Matching Event Id");
		
		assertAll();
	}


}
