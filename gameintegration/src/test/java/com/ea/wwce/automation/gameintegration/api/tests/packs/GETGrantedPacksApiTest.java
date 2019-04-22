package com.ea.wwce.automation.gameintegration.api.tests.packs;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;


import io.qameta.allure.Description;

public class GETGrantedPacksApiTest extends GameIntegrationAPIBaseTest{

	public static Logger logger = Logger.getLogger(GETGrantedPacksApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_GRANTED_PACK_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify GET GRANTED PACKS API ",groups={"Sanity"})
	@Description("Verification of GET GRNATED PACKS API for Product")
	public void verifyGrantedPacksAPI(ITestContext context) throws ParseException{

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "18564";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating Granted Packs for User" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
