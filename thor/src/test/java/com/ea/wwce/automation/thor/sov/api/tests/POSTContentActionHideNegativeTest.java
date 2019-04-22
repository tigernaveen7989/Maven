package com.ea.wwce.automation.thor.sov.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class POSTContentActionHideNegativeTest extends ContentActionAPIBaseTest{
public static Logger logger = Logger.getLogger(POSTContentActionHideNegativeTest.class);
DataProviders assertionProvider;	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "POST_Content_Actions_Hide_Negative");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="Verify Content action Hide Negative Test",groups={"Regression"})
	@Description("Verify Content action Hide Negative Test")
	public void verifyContentActionHideNegativeValidation(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "17297";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	
		//Load the assertions needed for the test    	
    	HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Verify Content action Hide Negative Test" + testCaseID);
		
		base.validateResponse();
		//assertKeyValue("status", base.getResponseData(), "ERROR");
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("failed_status").toString());
		assertAll();
		
	}
}
