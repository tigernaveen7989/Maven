package com.ea.wwce.automation.gameintegration.api.tests.items;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETGrantableItemsFromBadgecardsCategoryApiTest extends GameIntegrationAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETGrantableItemsFromBadgecardsCategoryApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_GRANTABLE_ITEMS_FROM_BADGECARDS_CATEGORY_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="Verify GET GRANTABLE ITEMS FROM CATEGORIES API ",groups={"Sanity"})
	@Description("Verification of GET GRANTABLE ITEMS FROM CATEGORIES API for Product")
	public void verifyGrantableItemsFromCategoriesAPI(ITestContext context) {	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "19848";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 	

		//Load the assertions needed for the test    	
		HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
		logger.info("Validating GRANTABLE ITEMS FROM CATEGORIES for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertTrue(base.getResponseData().contains(assertions.get("status").toString()) , "SUCCESSFUL");
		this.assertTrue(base.getResponseData().contains(assertions.get("itemDefinitionId").toString()),
				"Verify Grantable Items's item definition id for badgecard category");
		this.assertTrue(base.getResponseData().contains(assertions.get("name").toString()),
				"Verify Grantable Items's name for badgecard category");
		this.assertTrue(base.getResponseData().contains(assertions.get("itemCategory").toString()),
				"Verify Grantable Items's category i.e. badgecard category");
		assertAll();
	}
}
