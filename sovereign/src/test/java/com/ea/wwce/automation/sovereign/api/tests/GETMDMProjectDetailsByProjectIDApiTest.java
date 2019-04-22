package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETMDMProjectDetailsByProjectIDApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_MDM_PRODUCT_BY_PROJECT_ID");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify GET MDM PROJECT DETAILS BY PROJECT ID API", groups = { "Sanity" })
	@Description("verify GET MDM PROJECT DETAILS BY PROJECT ID API")
	public void verifyGetMDMProjectsByProjectIDAPI(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49983";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,"ASSERTIONS");

		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("projectTitle").toString()),"Verify projectTitle is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("projectDescription").toString()),"Verify projectDescription is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("projectStatus").toString()),"Verify projectStatus is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("masterTitle").toString()),"Verify masterTitle is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("revenueModel").toString()),"Verify revenueModel is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("projectType").toString()),"Verify projectType is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("interfacePlatform").toString()),"Verify interfacePlatform is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("interfacePlatformShortname").toString()),"Verify interfacePlatformShortname is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("interfaceType").toString()),"Verify interfaceType is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("interfacePlatformCode").toString()),"Verify interfacePlatformCode is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("revenueModelShortname").toString()),"Verify revenueModelShortname is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("labelDescription").toString()),"Verify labelDescription is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("firstReleaseDate").toString()),"Verify firstReleaseDate is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("firstOperationalShipDate").toString()),"Verify firstOperationalShipDate is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("labelDescription").toString()),"Verify projectId is as expected");

		assertAll();

	}

}
