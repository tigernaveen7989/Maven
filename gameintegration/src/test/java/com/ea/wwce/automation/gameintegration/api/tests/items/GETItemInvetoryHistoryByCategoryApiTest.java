package com.ea.wwce.automation.gameintegration.api.tests.items;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class GETItemInvetoryHistoryByCategoryApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(GETItemInvetoryHistoryByCategoryApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_ITEM_INVENTORY_TX_HISTORY_BY_CATEGORY_API");
		super.beforeMethod(context);
	}
	
	@Test(description ="Verify GET ITEM INVENTORY TRRANSACTION HISTORY FOR SPECIFIED CATEGORY API ",groups={"Sanity"})
	@Description("Verification of GET ITEM INVENTORY TRRANSACTION HISTORY FOR SPECIFIED CATEGORY API for Product")
	public void verifyItemInventoryTransHistoryByCategoryAPI(ITestContext context) throws ParseException{
		
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20813";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating Item Inventory Transaction History for specificed category of product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
