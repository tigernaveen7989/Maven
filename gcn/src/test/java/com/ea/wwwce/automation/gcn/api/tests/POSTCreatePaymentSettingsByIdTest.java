package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class POSTCreatePaymentSettingsByIdTest extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTCreatePaymentSettingsByIdTest.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "CREATE_PAYMENT_SETTINGS_BYID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify Create Payment Settings By Id",groups={"regression"})
	@Description("Verification of POST Create Payment Settings By Id")
	public void verifyCeatePaymentSettingsById(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20345";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("Verifying Create Payment Settings By Id" + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals("992",assertions.get("game_changer_id").toString() ,"Matching Game Changer Id");
		
		assertEquals("INDIVIDUAL",assertions.get("entity_type") ,"Matching Entity Type");
		
		assertEquals("232323",assertions.get("tax_id").toString() ,"Matching Entity Type");
		
		assertAll();
	}


}
