package com.ea.wwce.automation.salesforce.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.salesforce.config.SalesforceDataConstants;

public class SFCreateAdvisorTest extends SalesforceBaseTest {

	public static Logger logger = Logger.getLogger(SFCreateAdvisorTest.class);

	@Test
	public void createAdvisor(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "23047";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Verify omega login
		salesforceLoginPage.loginToSalesForce(SalesforceDataConstants.SF_AUT_URL, testData.get("username").toString(),
				testData.get("password").toString());

		assertEquals(salesforceHomePage.getUserName(), testData.get("SFUserName").toString(),
				"Verify username is available");

		// Navigate to admin page
		salesforceLoginPage.loadSalesforce(testData.get("adminUrl").toString());

		// Create advisor profile
		advisorProfilePage.createAdvisorProfile(testData.get("fname").toString(), testData.get("lname").toString(),
				testData.get("mail").toString(), testData.get("sfuser").toString(), testData.get("stitle").toString(),
				testData.get("txtdob").toString(), testData.get("empnumber").toString(),
				testData.get("jobRole").toString(), testData.get("lpuserName").toString(),
				testData.get("lppassword").toString(), testData.get("lpAgentId").toString(),
				testData.get("lopassword").toString(), testData.get("louserName").toString(),
				testData.get("loAgentId").toString(), testData.get("lhuserName").toString(),
				testData.get("zUserName").toString(), testData.get("ctusername").toString(),
				testData.get("gUsername").toString(), testData.get("cName").toString(),
				testData.get("txtManager").toString(), testData.get("txtProfile").toString(),
				testData.get("txtStaff").toString(), testData.get("eType").toString(), 
				testData.get("managerType").toString(), testData.get("tZone").toString(),testData.get("hrDate").toString(),
				testData.get("frDate").toString(), testData.get("rDate").toString(), testData.get("pfDate").toString(),testData.get("notes").toString());

	}
}
