package com.ea.wwwce.automation.gcn.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class PATCHUpdateTaxDocumentStatus_W8BEN_E_Incomplete_Test extends GcnAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(PATCHUpdateTaxDocumentStatus_W8BEN_E_Incomplete_Test.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "UPDATE_TAX_DOCUMENT_STATUS");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}
	
	@Test(description ="verify Updation of Tax Document Status - W8BENE - Incomplete",groups={"regression"})
	@Description("Verification of Updation of Tax Document Status - W8BENE - Incomplete")
	public void verifyUpdationOfTaxDocumentStatusWBENE_Incomplete(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "27280";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("Verifying Updation of Tax Document Status - W8BENE - Incomplete : " + testCaseID);
		
		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) ,"Matching response code as 200");
		
		// validate other response data
			
		assertAll();
	}


}
