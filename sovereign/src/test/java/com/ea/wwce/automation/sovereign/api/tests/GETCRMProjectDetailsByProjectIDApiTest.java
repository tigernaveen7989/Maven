package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETCRMProjectDetailsByProjectIDApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_CRM_PRODUCT_BY_PRODUCT_ID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify GET CRM PROJECT DETAILS BY PROJECT ID API", groups = { "Sanity" })
	@Description("verify GET CRM PROJECT DETAILS BY PROJECT ID API")
	public void verifyGetCRMProjectsByProjectIDAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49985";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("crmProductId").toString()),"Verify crmProductId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("crmProductName").toString()),"Verify crmProductName is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("thumbnailUrl").toString()),"Verify thumbnailUrl is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("productCode").toString()),"Verify isActive is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("isActive").toString()),"Verify isActive is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("visibleOnFranchise").toString()),"Verify visibleOnFranchise is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("interfacePlatformName").toString()),"Verify interfacePlatformName is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("mdmProjectId").toString()),"Verify mdmProjectId is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("isFIFAUltimateProduct").toString()),"Verify isFIFAUltimateProduct is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("crmproductId").toString()),"Verify crmproductId is as expected");
		
		assertAll();

	}

}
