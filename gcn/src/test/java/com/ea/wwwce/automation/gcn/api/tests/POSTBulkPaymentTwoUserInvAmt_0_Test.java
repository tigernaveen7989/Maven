package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class POSTBulkPaymentTwoUserInvAmt_0_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTBulkPaymentTwoUserInvAmt_0_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "BULK_PAYMENT_CONTROLLER_TWO_USER_INV_AMT_0");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify POST Bulk Payment to Two GameChangers with Invalid Amt 0",groups={"regression"})
	@Description("Verification of POST Bulk Payment to Two GameChangers with Invalid Amt 0")
	public void verifyBulkPaymentTwoUserInvAmt_0(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "12350";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Bulk Payment to Two GameChangers with Invalid Amt 0" + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		assertEquals("FAILED",assertions.get("status") ,"Matching Status Type");
		
		assertAll();
	}


}
