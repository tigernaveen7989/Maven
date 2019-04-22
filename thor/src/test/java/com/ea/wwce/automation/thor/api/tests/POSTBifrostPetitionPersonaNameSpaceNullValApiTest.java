/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of POST Bifrost Petitioner Persona Name Null validation
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



public class POSTBifrostPetitionPersonaNameSpaceNullValApiTest extends ThorAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTBifrostPetitionPersonaNameSpaceNullValApiTest.class);
	DataProviders assertionProvider;
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "BIFROST_PETITIONER_PERSONANAMESPACE_NULL_VALIDATION");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="verify POST Bifrost Petitioner Persona name space Null validation",groups={"regression"})
	@Description("Verification of POST Bifrost Petitioner Persona Name Space Null validation")
	public void verifyPetitionerPersonaNameSpaceNullValidation(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "4374";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("validating Bifrost Petitioner Persona Name Space Null validation" + testCaseID);
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("Success_status").toString());
		assertAll();
	}
 
}
