package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETPaymentStatusById_Content_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETPaymentStatusById_Content_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_PAYMENT_STATUS_BYID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify GET Payment Settings By Id and Content",groups={"regression"})
	@Description("Verification of GET Payment Settings By Id and Content")
	public void verifyGetPaymentStatusByIdContent(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20374";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("Verifying GET Payment Settings By Id and Content" + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals("994",assertions.get("game_changer_id").toString() ,"Matching Game Changer Id");
		
		assertEquals("SUCCESS",assertions.get("status") ,"Matching Status");
		
		assertEquals("USD",assertions.get("currency").toString() ,"Matching Currency Type");
		
		assertEquals("CONTENT",assertions.get("type").toString() ,"Matching Payment Type");
		
		assertAll();
	}


}
