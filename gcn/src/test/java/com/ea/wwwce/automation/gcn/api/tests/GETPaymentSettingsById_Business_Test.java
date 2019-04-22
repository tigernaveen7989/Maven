package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETPaymentSettingsById_Business_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETPaymentSettingsById_Business_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_PAYMENT_SETTINGS_BYID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify GET Payment Settings By Id and Business",groups={"regression"})
	@Description("Verification of GET Payment Settings By Id and Business")
	public void verifyGetPaymentSettingsByIdBusiness(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20357";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("Verifying GET Payment Settings By Id and Business : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals("1002",assertions.get("game_changer_id").toString() ,"Matching Game Changer Id");
		
		assertEquals("BUSINESS",assertions.get("entity_type") ,"Matching Entity Type");
		
		assertEquals("123456789",assertions.get("tax_id").toString() ,"Matching Entity Type");
		
		assertEquals("PAYPAL",assertions.get("vendor").toString() ,"Matching Entity Type");
		
		assertEquals("US",assertions.get("country").toString() ,"Matching Entity Type");
		
		assertAll();
	}


}
