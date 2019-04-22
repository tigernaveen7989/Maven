package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;



public class GETNucleasAccountBasicDetailsApiTest extends SovereignAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETNucleasAccountBasicDetailsApiTest.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_NUCLEAS_ACCOUNT_BASCI_INFO_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="verify Nucleas Account details API",groups={"Sanity"})
	@Description("Verification of Nucleas account details")
	public void verifyNucleasAccountDetails(ITestContext context){	
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "43464";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);
		
		base.validateResponse();
		assertEquals(200, base.getResponseCode(), "Matching response code as 200");
		

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("userId").toString()),"Verify expected userId is present");
		assertTrue(base.getResponseData().contains(assertions.get("email").toString()),"Verify expected email is present");
		assertTrue(base.getResponseData().contains(assertions.get("firstName").toString()),"Verify expected firstName is present");
		assertTrue(base.getResponseData().contains(assertions.get("lastName").toString()),"Verify expected lastName is present");
		assertTrue(base.getResponseData().contains(assertions.get("emailStatus").toString()),"Verify expected emailStatus  is present");
		assertTrue(base.getResponseData().contains(assertions.get("dob").toString()),"Verify expected dob is present");
		assertTrue(base.getResponseData().contains(assertions.get("country").toString()),"Verify expected country is present");
		assertTrue(base.getResponseData().contains(assertions.get("language").toString()),"Verify expected language is present");
		assertTrue(base.getResponseData().contains(assertions.get("locale").toString()),"Verify expected locale present");
		assertTrue(base.getResponseData().contains(assertions.get("status").toString()),"Verify expected status present");
		assertTrue(base.getResponseData().contains(assertions.get("reasonCode").toString()),"Verify expected reasonCode is present");
		assertTrue(base.getResponseData().contains(assertions.get("tosVersion").toString()),"Verify expected tosVersion is present");
		assertTrue(base.getResponseData().contains(assertions.get("parentalEmail").toString()),"Verify expected parentalEmail is present");
		assertTrue(base.getResponseData().contains(assertions.get("registrationSource").toString()),"Verify expected registrationSource is present");
		assertTrue(base.getResponseData().contains(assertions.get("defaultPersonaId").toString()),"Verify expected defaultPersonaId is present");
		assertTrue(base.getResponseData().contains(assertions.get("defaultPersonaName").toString()),"Verify expected defaultPersonaName is present");
		assertTrue(base.getResponseData().contains(assertions.get("defaultPersonaDisplayName").toString()),"Verify expected defaultPersonaDisplayName is present");
		
		assertAll();
	}
 
}
