package com.ea.wwce.automation.sovereign.api.tests;

import static org.testng.Assert.assertNotNull;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.api.APIBase;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.qameta.allure.Description;

public class GETPUTPOSTPUTTwoFactorAuthOrchTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETPUTPOSTPUTTwoFactorAuthOrchTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_TWO_FACTOR_AUTH,PUT_TWO_FACTOR_AUTHENTICATION@1,POST_TWO_FACTOR_AUTH_CODES,PUT_TWO_FACTOR_AUTHENTICATION@2");
		super.beforeMethod(context);

	}

	@Test(description = "Verify User's Two-Factor Authentication API", groups = { "Sanity" })
	@Description("Verify GET User's Two-Factor Authentication API")
	public void verifyTwoFactorAuthenticationAPI(ITestContext context) {

		//JsonFileProcessor jfp=new JsonFileProcessor();
		String testCaseID = "97034";
		context.setAttribute("testcase_id", testCaseID);

		HashMap<String, Object> apiOrchestratorMap = (HashMap<String, Object>) apiOrchestrator.get("GET_TWO_FACTOR_AUTH");
		APIBase apiBase = (APIBase) apiOrchestratorMap.get("API_BASE");
		assertionProvider = new DataProviders((String)apiOrchestratorMap.get("ASSERTION_PATH"));
		assertionProvider.setApiId("GET_TWO_FACTOR_AUTH");
		logger.info("validating GET Two factor Authentication Test" + testCaseID);
		apiBase.loadAPIInfomation(testCaseID,"GET_TWO_FACTOR_AUTH");
		apiBase.validateResponse();
		this.assertEquals(200, apiBase.getResponseCode(), "Matching response code as 200");
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,"ASSERTIONS");

		// Verify assertions
		assertTrue(apiBase.getResponseData().contains(assertions.get("status").toString()),"Verify status is as exepected");
		assertTrue(apiBase.getResponseData().contains(assertions.get("codeType").toString()),"Verify codeType is as expected");
		assertTrue(apiBase.getResponseData().contains(assertions.get("email").toString()),"Verify email is as exepected");
		assertTrue(apiBase.getResponseData().contains(assertions.get("isExpired").toString()),"Verify isExpired is as exepected");	

		assertAll();
		//jfp.setFileOutput(apiBase.getResponseData());
		JsonParser parser = new JsonParser();
		JsonElement parse = parser.parse(apiBase.getResponseData());
		String status=parse.getAsJsonObject().get("response").getAsJsonObject().get("status").getAsString();
		//JsonObject findObjectBykey = jfp.findObjectBykey("response", "status", "ENABLED");
		//PUT_TWO_FACTOR_AUTHENTICATION ENABLE
		if(status.equalsIgnoreCase("DISABLED")) { //If status is DISABLED, it will ne NULL. Only if it is DISBALED, We should do ENABLE CALL

			apiOrchestratorMap = (HashMap<String, Object>) apiOrchestrator.get("PUT_TWO_FACTOR_AUTHENTICATION@1");
			apiBase = (APIBase) apiOrchestratorMap.get("API_BASE");
			assertionProvider = new DataProviders((String)apiOrchestratorMap.get("ASSERTION_PATH"));
			assertionProvider.setApiId("PUT_TWO_FACTOR_AUTHENTICATION@1");
			logger.info("validating Two factor Authentication Test" + testCaseID);
			apiBase.loadAPIInfomation(testCaseID,"PUT_TWO_FACTOR_AUTHENTICATION@1");
			apiBase.validateResponse();
			this.assertEquals(200, apiBase.getResponseCode(), "Matching response code as 200");

			// Load the assertions needed for the test
			assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
					"ASSERTIONS");

			// Verify assertions
			assertTrue(apiBase.getResponseData().contains(assertions.get("status").toString()),	"Verify status is as expected");

			assertAll();

		}






		//POST_TWO_FACTOR_AUTH_CODES

		apiOrchestratorMap = (HashMap<String, Object>) apiOrchestrator.get("POST_TWO_FACTOR_AUTH_CODES");
		apiBase = (APIBase) apiOrchestratorMap.get("API_BASE");
		assertionProvider = new DataProviders((String)apiOrchestratorMap.get("ASSERTION_PATH"));
		assertionProvider.setApiId("POST_TWO_FACTOR_AUTH_CODES");
		apiBase.loadAPIInfomation(testCaseID,"POST_TWO_FACTOR_AUTH_CODES");
		logger.info("validating POST Two factor Authentication Test" + testCaseID);
		apiBase.validateResponse();
		this.assertEquals(200, apiBase.getResponseCode(), "Matching response code as 200");
		// Load the assertions needed for the test
		assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,"ASSERTIONS");

		// Verify assertions
		assertTrue(apiBase.getResponseData().contains(assertions.get("codeType").toString()),	"Verify codeType is as expected");
		assertTrue(apiBase.getResponseData().contains(assertions.get("email").toString()),	"Verify email is as expected");
		assertTrue(apiBase.getResponseData().contains(assertions.get("status").toString()),	"Verify status is as expected");
		assertNotNull(assertions.get("code1").toString(),	"Verify code1 is as expected");
		assertTrue(apiBase.getResponseData().contains(assertions.get("isExpired").toString()),	"Verify isExpired is as expected");
		assertNotNull(apiBase.getResponseData().contains(assertions.get("code2").toString()),	"Verify code2 is as expected");

		assertAll();
		if(status.equalsIgnoreCase("DISABLED")) {
			//PUT_TWO_FACTOR_AUTHENTICATION DISABLE
			apiOrchestratorMap = (HashMap<String, Object>) apiOrchestrator.get("PUT_TWO_FACTOR_AUTHENTICATION@2");
			apiBase = (APIBase) apiOrchestratorMap.get("API_BASE");
			assertionProvider = new DataProviders((String)apiOrchestratorMap.get("ASSERTION_PATH"));
			assertionProvider.setApiId("PUT_TWO_FACTOR_AUTHENTICATION@2");
			logger.info("validating Two factor Authentication Test" + testCaseID);
			apiBase.loadAPIInfomation(testCaseID,"PUT_TWO_FACTOR_AUTHENTICATION@2");
			apiBase.validateResponse();
			this.assertEquals(200, apiBase.getResponseCode(), "Matching response code as 200");
			// Load the assertions needed for the test
			assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
					"ASSERTIONS");
			// Verify assertions
			assertTrue(apiBase.getResponseData().contains(assertions.get("status").toString()),	"Verify status is as expected");

			assertAll();
		}
	}
}
