package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GetOFBProductDetailsByOFByProductID extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_OFB_PRODUCT_DETAILS");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify GET OFB PRODUCT DETAILS BY OFB PRODUCT ID API", groups = { "Sanity" })
	@Description("verify GET OFB PRODUCT DETAILS BY OFB PRODUCT ID API")
	public void verifyOFBProductDetails(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49929";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("ofbProductId").toString()),"Verify ofbProductId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("status").toString()),"Verify status is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("financeId").toString()),"Verify financeId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("productName").toString()),"Verify productName is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("productType").toString()),"Verify productType is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("projectId").toString()),"Verify projectId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("mdmItemNumber").toString()),"Verify mdmItemNumber is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("groupName").toString()),"Verify groupName is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("mdmItemType").toString()),"Verify ofbItemSubtype is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("entitlementTag").toString()),"Verify entitlementTag is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("ofbItemTitle").toString()),"Verify ofbItemTitle is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("ofbItemId").toString()),"Verify ofbItemId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("mdmItemType").toString()),"Verify ofbItemSubtype is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("pointValue").toString()),"Verify pointValue is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("displayName").toString()),"Verify displayName is as expected");
	
		assertAll();

	}

}
