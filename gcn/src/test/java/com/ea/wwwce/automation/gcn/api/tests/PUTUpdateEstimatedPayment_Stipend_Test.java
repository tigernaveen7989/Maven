package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class PUTUpdateEstimatedPayment_Stipend_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(PUTUpdateEstimatedPayment_Stipend_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "UPDATE_ESTIMATED_PAYMENTS");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify PUT Update Estimated Payments Stipend",groups={"regression"})
	@Description("Verification of PUT Update Estimated Payments Stipend")
	public void verifyUpdateEstimatedPaymentStipend(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20396";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating PUT Update Estimated Payments Stipend : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals(261,Integer.parseInt(assertions.get("eventId").toString()) ,"Matching Event Id");
		
		assertEquals("STIPEND",assertions.get("type").toString() ,"Matching Type");
		
		assertAll();
	}


}
