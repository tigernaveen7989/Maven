/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of POST SF Petition Creation with Product Null validation
 */
package com.ea.wwce.automation.thor.sf.api.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;



public class POSTSFPetitionCreationWithProductNullValApiTest extends ThorSFAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTSFPetitionCreationWithProductNullValApiTest.class);
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "SF_BIFROST_PRODUCT_NULL_VALIDATION");
		super.beforeMethod(context);
	}

	@Test(description ="verify SF Petition Creation with Product Null validation",groups={"regression"})
	@Description("Verification of POST SF Petition Creation with Product Null validation")
	public void verifyProductNullValidation(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "1234";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("validating SF Petition Creation with Product Null validation" + testCaseID);
		
		base.validateResponse();
		assertEquals(200, base.getResponseCode(), "Matching response code as 200");
		assertKeyValue("message", base.getResponseData(), "HC-COMMON-00003");
		assertKeyValue("status", base.getResponseData(), "ERROR");
		assertAll();
	}
 
}
