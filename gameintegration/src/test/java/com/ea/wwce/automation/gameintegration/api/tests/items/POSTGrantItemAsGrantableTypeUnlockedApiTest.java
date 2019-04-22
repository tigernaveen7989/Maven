package com.ea.wwce.automation.gameintegration.api.tests.items;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.gameintegration.api.tests.GameIntegrationAPIBaseTest;

import io.qameta.allure.Description;

public class POSTGrantItemAsGrantableTypeUnlockedApiTest extends GameIntegrationAPIBaseTest {
	
	public static Logger logger = Logger.getLogger(POSTGrantItemAsGrantableTypeUnlockedApiTest.class);

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "POST_GRANT_ITEM_GRANTABLETYPE_UNLOCKED_API");
		super.beforeMethod(context);
	}

	@Test(description ="Verify GRANT ITEM WITH GRANTABLE TYPE AS UNLOCKED API ",groups={"Sanity"})
	@Description("Verification of GRANT ITEM WITH GRANTABLE TYPE AS UNLOCKED API for Product")
	public void verifyGrantItemAsGrantableTypeUnlockedAPI(ITestContext context) throws ParseException{	

		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20460";
		context.setAttribute("testcase_id", testCaseID);
		logger.info("Validating GRANT ITEM WITH GRANTABLE TYPE AS UNLOCKED for product" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertKeyValue("status", base.getResponseData(), "SUCCESS");
		assertAll();
	}
}
