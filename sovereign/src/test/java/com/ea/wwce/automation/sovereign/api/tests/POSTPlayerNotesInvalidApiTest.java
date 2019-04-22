package com.ea.wwce.automation.sovereign.api.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.tests.APIBaseTest;

import io.qameta.allure.Description;



public class POSTPlayerNotesInvalidApiTest extends SovereignAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTPlayerNotesInvalidApiTest.class);
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "POST_PLAYER_NOTES_API");
		super.beforeMethod(context);
	}

	@Test(description ="verify POST Player Notes API",groups={"Sanity"})
	@Description("Verification of POST player notes API")
	public void verifyPlayerNotesPost(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "10";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);
		
		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");
	}
 
}
