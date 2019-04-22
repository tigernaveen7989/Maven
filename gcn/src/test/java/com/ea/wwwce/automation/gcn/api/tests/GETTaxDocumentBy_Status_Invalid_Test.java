package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GETTaxDocumentBy_Status_Invalid_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETTaxDocumentBy_Status_Invalid_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_TAX_DOCUMENT_BY_STATUS");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify Get Tax Document By Invalid Status",groups={"regression"})
	@Description("Verification of Get Tax Document By Invalid Status")
	public void verifyTaxDocumentStatusInValid(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "27301";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("Verifying Get Tax Document By Invalid Status : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
		
		assertEquals(0,Integer.parseInt(assertions.get("count").toString()) ,"Matching Count");
		
		assertEquals(0,Integer.parseInt(assertions.get("total").toString()) ,"Matching Total");
		
		assertAll();
	}


}
