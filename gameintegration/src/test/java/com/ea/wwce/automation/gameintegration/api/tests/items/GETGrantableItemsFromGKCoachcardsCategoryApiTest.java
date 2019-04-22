package com.ea.wwce.automation.gameintegration.api.tests.items;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETGrantableItemsFromGKCoachcardsCategoryApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(GETGrantableItemsFromKitcardsCategoryApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_GRANTABLE_ITEMS_FROM_GKCOACHCARD_CATEGORY_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify GET GRANTABLE ITEMS FROM CATEGORIES API ",groups={"Sanity"})
	@Description("Verification of GET GRANTABLE ITEMS FROM CATEGORIES API for Product")
	public void verifyGrantableItemsFromCategoriesAPI(ITestContext context) throws ParseException{	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "19845";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating GRANTABLE ITEMS FROM CATEGORIES for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
