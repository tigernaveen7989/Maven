package com.ea.wwce.automation.gameintegration.api.tests.packs;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;
import com.ea.wwce.automation.gameintegration.api.tests.setHistory.GETSetsCategoriesApiTest;

import io.qameta.allure.Description;

public class GETPacksCategoriesApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(GETPacksCategoriesApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_PACKS_CATEGORIES_API");
		super.beforeMethod(context);
	}
	
	@Test(description ="Verify GET PACKS CATEGORIES API ",groups={"Sanity"})
	@Description("Verification of GET PACKS CATEGORIES API for Product")
	public void verifyPacksCategoriesAPI(ITestContext context) throws ParseException{
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "18881";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating Packs Categories for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}

}
