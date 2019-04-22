/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of POST SF Petition Creation with Category Null validation
 */

package com.ea.wwce.automation.thor.sf.api.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.thor.api.tests.ThorAPIBaseTest;

import io.qameta.allure.Description;



public class POSTSFPetitionCreationWithCategoryNullValApiTest extends ThorSFAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTSFPetitionCreationWithCategoryNullValApiTest.class);
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "SF_BIFROST_CATEGORY_NULL_VALIDATION");
		super.beforeMethod(context);
	}

	@Test(description ="verify POST SF Petition Creation with Category Null validation",groups={"regression"})
	@Description("Verification of POST SF Petition Creation with Category Null validation")
	public void verifyCategoryNullValidation(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "1234";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("validating SF Petition Creation with Category Null validation" + testCaseID);
		
		base.validateResponse();
		assertEquals(200, base.getResponseCode(), "Matching response code as 200");
		assertKeyValue("message", base.getResponseData(), "HC-COMMON-00005");
		assertKeyValue("status", base.getResponseData(), "ERROR");
		assertAll();
	}
 
}
