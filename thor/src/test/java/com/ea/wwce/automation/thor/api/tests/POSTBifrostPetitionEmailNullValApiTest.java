/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of POST Bifrost Petitioner Email Null validation
 */
package com.ea.wwce.automation.thor.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;



public class POSTBifrostPetitionEmailNullValApiTest extends ThorAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTBifrostPetitionEmailNullValApiTest.class);
	DataProviders assertionProvider;
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "BIFROST_PETITIONER_EMAIL_NULL_VALIDATION");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="verify POST Bifrost Petitioner Email Null validation",groups={"regression"})
	@Description("Verification of POST Bifrost Petitioner Email Null validation")
	public void verifyPetitionerEmailNullValidation(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "4334";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	
		//Load the assertions needed for the test    	
    	HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("validating Bifrost Petitioner Email Null validation" + testCaseID);
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("error_code").toString()) , "Matching response code as 400");
		assertKeyValue("status", base.getResponseData(), assertions.get("failed_status").toString());
		assertKeyValue("message", base.getResponseData(), assertions.get("message").toString());
		assertAll();
	}
 
}
