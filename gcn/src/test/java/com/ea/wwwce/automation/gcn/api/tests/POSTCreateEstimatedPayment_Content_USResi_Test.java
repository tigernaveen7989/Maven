package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class POSTCreateEstimatedPayment_Content_USResi_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTCreateEstimatedPayment_Content_USResi_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "CREATE_ESTIMATED_PAYMENTS");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify POST Create Estimated Payments for US Resident - Content",groups={"regression"})
	@Description("Verification of POST Create Estimated Payments for US Resident - Content")
	public void verifyCreateEstimatedPaymentContentUSResi(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20399";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating POST Create Estimated Payments for US Resident - Content" + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals(262,Integer.parseInt(assertions.get("eventId").toString()) ,"Matching Event Id");
		
		assertEquals("CONTENT",assertions.get("type").toString() ,"Matching Type");
		
		assertAll();
	}


}
