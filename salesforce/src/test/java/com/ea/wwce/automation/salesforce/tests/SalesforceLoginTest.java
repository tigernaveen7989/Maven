package com.ea.wwce.automation.salesforce.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.salesforce.config.SalesforceDataConstants;

public class SalesforceLoginTest extends SalesforceBaseTest {

	public static Logger logger = Logger.getLogger(SalesforceLoginTest.class);

	@BeforeSuite
	public void beforeSuite(ITestContext context) {
		super.beforeSuite(context);
	}

	@BeforeClass
	public void beforeClass(ITestContext context) {
		super.beforeClass(context);
	}

	@Test
	public void verifyLoginToSalesforce(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		try {
			// Provide the ID of the test case. This is the ID generated in the
			// TestRail when the manual test case is created.
			testID = "12";
			context.setAttribute("testcase_id", testID);
			logger.info("validating Verify Login Test" + testID);

			// Load the test data for the test ID
			DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
			testData = dataProvider.getTestData(testID);	

			// Verify omega login
			salesforceLoginPage.loginToSalesForce(SalesforceDataConstants.SF_AUT_URL,
					testData.get("username").toString(), testData.get("password").toString());

			assertEquals(salesforceHomePage.getUserName(), testData.get("SFUserName").toString(),
					"Verify username is available");

			assertAll();

		} catch (Exception e) {
			logger.warn("Not able to login to omega" + e.getMessage());
		}

	}

	@AfterClass
	public void afterClass() {
		super.afterClass();
	}

}
