/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of POST Bifrost Petition Save API
 */
package com.ea.wwce.automation.thor.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;


public class POSTBifrostPetitionSaveApiTest extends ThorAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTBifrostPetitionSaveApiTest.class);
	DataProviders assertionProvider;
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "BIFROST_PETITION_SAVE_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="verify POST Bifrost Petition Save",groups={"regression"})
	@Description("Verification of POST Bifrost Petition Save API")
	public void verifyPetitionSavePost(ITestContext context){	
		String testCaseID;
		
		//Map the test ID to the automation result for automated updated in TestRail
		testCaseID = "4317,20822";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("validating Bifrost Petition Save Test" + testCaseID);
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("Success_status").toString());
		assertAll();
	}
 
}
