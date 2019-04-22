package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETPaymentSettingsById_Inv_userId_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETPaymentSettingsById_Inv_userId_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_PAYMENT_SETTINGS_BYID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify Get Payment Settings By Invalid userId",groups={"regression"})
	@Description("Verification of Get Payment Settings By Invalid userId")
	public void verifyGetPaymentSettingsByIdInvUserId(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20359";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("Verifying Get Payment Settings By Invalid userId : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
			
		assertEquals(1,Integer.parseInt(assertions.get("errorCode").toString()) ,"Matching ErrorCode");
		
		assertEquals("The user is unauthorized to access this url",assertions.get("errorMessage") ,"Matching Error Message");
				
		assertAll();
	}


}
