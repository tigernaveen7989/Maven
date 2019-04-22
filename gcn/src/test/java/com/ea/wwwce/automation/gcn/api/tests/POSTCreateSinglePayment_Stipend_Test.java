package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class POSTCreateSinglePayment_Stipend_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTCreateSinglePayment_Stipend_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "CREATE_SINGLE_PAYOUT");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify POST Single Payment to GameChangers for Stipend",groups={"regression"})
	@Description("Verification of POST Single Payment to GameChangers for Stipend")
	public void verifyCreateSinglePaymentStipend(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20371";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Single Payment to GameChangers for Stipend : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals("STIPEND",assertions.get("type") ,"Matching Payment Type");
		
		assertEquals(262,Integer.parseInt(assertions.get("eventId").toString()) ,"Matching Event Id");
		
		assertEquals(994,Integer.parseInt(assertions.get("game_changer_id").toString()) ,"Matching Game Changer Id");
		
		assertEquals(3876,Integer.parseInt(assertions.get("amount").toString()) ,"Matching Amount");
		
		
		
		
		
		
		
		assertAll();
	}


}
