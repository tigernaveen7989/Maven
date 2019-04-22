/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of POST SF Petition Save API
 */
package com.ea.wwce.automation.thor.sf.api.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;


public class POSTSFPetitionSaveApiTest extends ThorSFAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTSFPetitionSaveApiTest.class);
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "SF_BIFROST_PETITION_SAVE_API");
		super.beforeMethod(context);
	}

	@Test(description ="verify POST SF Petition Save",groups={"regression"})
	@Description("Verification of POST SF Petition Save API")
	public void verifyPetitionSavePost(ITestContext context){	
		String testCaseID;
		
		//Map the test ID to the automation result for automated updated in TestRail
		testCaseID = "1234";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("validating SF Petition Save Test" + testCaseID);
		
		base.validateResponse();
		assertEquals(200, base.getResponseCode(), "Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
 
}
