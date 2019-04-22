/**
 * 
 * @author Praveen Lingampally
 * @Description Verification of POST Outbound Survey Creation with 2 Advisors API
 */
package com.ea.wwce.automation.vop.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;


public class POSTOutboundSurveyCreationWith2AdvisorsApiTest extends VOPAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(POSTOutboundSurveyCreationWith2AdvisorsApiTest.class);
	DataProviders assertionProvider;
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "VOP_OUTBOUND_SURVEY_CREATION_API");
		super.beforeMethod(context);
		assertionProvider=new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="verify POST Outbound survey Creation with 2 Advisors",groups={"regression"})
	@Description("Verification of Outbound survey Creation with 2 Advisors API")
	public void verifyOutboundsurveywith2AdvisorsPost(ITestContext context){	
		String testCaseID;
		
		//Map the test ID to the automation result for automated updated in TestRail
		testCaseID = "42589";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		HashMap<String,Object> assertions=(HashMap<String,Object>)assertionProvider.getTestData(testCaseID, "ASSERTIONS");
		logger.info("validating Outbound survey Creation with 2 Advisors Test" + testCaseID);
		
		base.validateResponse();
		assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		assertKeyValue("status", base.getResponseData(), assertions.get("Success_status").toString());
		assertTrue(base.getResponseData().contains(assertions.get("response").toString()), "Verify URI is present in response body");
		assertAll();
	}
 
}
