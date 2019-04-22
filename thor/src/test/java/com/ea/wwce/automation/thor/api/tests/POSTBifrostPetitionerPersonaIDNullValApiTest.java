/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of POST Bifrost Content Action HTTP Method Null validation
 */
package com.ea.wwce.automation.thor.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;



public class POSTBifrostPetitionerPersonaIDNullValApiTest extends ThorAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTBifrostPetitionerPersonaIDNullValApiTest.class);
	DataProviders assertionProvider;
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "BIFROST_PETITIONER_PERSONAID_NULL_VALIDATION");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="verify POST Bifrost Petitioner Persona Id Null validation",groups={"regression"})
	@Description("Verification of POST Bifrost Petitioner Persona Id Method Null validation")
	public void verifyPetitionerPersonaIdMethodNullValidation(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "4326";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("validating Bifrost Petitioner Persona Id Method Null validation" + testCaseID);
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("Success_status").toString());
		assertAll();
	}
 
}
