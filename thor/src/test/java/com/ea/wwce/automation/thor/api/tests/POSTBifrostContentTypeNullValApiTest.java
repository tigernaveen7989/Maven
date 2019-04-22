/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of POST Bifrost Content Type Null validation
 */
package com.ea.wwce.automation.thor.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;



public class POSTBifrostContentTypeNullValApiTest extends ThorAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTBifrostContentTypeNullValApiTest.class);
	DataProviders assertionProvider;
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "BIFROST_CONTENTTYPE_NULL_VALIDATION");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="verify POST Bifrost Content Type Null validation",groups={"regression"})
	@Description("Verification of POST Bifrost Content Type Null validation")
	public void verifyContentTypeNullValidation(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "4362";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("validating Bifrost Content Type Null validation" + testCaseID);
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("Success_status").toString());
		assertAll();
	}
 
}
