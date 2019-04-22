package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class POSTGetPaymentSettingsById_Inv_List_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTGetPaymentSettingsById_Inv_List_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_PAYMENT_SETTINGS_BYID_LIST");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify Get Payment Settings By Id with Invalid List",groups={"regression"})
	@Description("Verification of Get Payment Settings By Id with Invalid List")
	public void verifyGetPaymentSettingsByIdInvList(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20362";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("Verifying Get Payment Settings By Id with Invalid List : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
						
		assertEquals(3,Integer.parseInt(assertions.get("errorCode").toString()) ,"Matching Error Code");
		
		assertEquals("The database search query returned zero records for the criteria specified",assertions.get("errorMessage").toString() ,"Matching Vendor Type");
		
		//logger.info("Response data is : "+base.getResponseData());
		
		assertAll();
	}


}
