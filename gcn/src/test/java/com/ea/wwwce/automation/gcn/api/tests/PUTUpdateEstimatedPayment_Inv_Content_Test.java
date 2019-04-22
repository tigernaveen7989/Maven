package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class PUTUpdateEstimatedPayment_Inv_Content_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(PUTUpdateEstimatedPayment_Inv_Content_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "UPDATE_ESTIMATED_PAYMENTS");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify PUT Update Estimated Payments with Inv Content",groups={"regression"})
	@Description("Verification of PUT Update Estimated Payments with Inv Content")
	public void verifyUpdateEstimatedPaymentInvContent(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20404";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating PUT Update Estimated Payments with Inv Content : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data		
		assertEquals("Internal Server Error",assertions.get("error").toString() ,"Matching Error");
		
		assertAll();
	}


}
