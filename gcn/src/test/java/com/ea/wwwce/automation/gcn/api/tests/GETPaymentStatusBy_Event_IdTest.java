package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETPaymentStatusBy_Event_IdTest extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETPaymentStatusBy_Event_IdTest.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_PAYMENT_STATUS_BY_EVENT_ID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify GET Payment Stauts By eventId",groups={"regression"})
	@Description("Verification of GET Payment Stauts By eventId")
	public void verifyGetPaymentStatusByEventId(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20375";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("Verifying GET Payment Stauts By eventId : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals("262",assertions.get("event_id").toString() ,"Matching Event Id");
				
		assertAll();
	}


}
