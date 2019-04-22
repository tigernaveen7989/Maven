package com.ea.wwce.automation.omega.tests;

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

public class OmegaLoginTest extends OmegaBaseTest {

	public static Logger logger = Logger.getLogger(OmegaLoginTest.class);

	@BeforeSuite
	public void beforeSuite(ITestContext context) {
		super.beforeSuite(context);
	}


	@BeforeClass
	public void beforeClass(ITestContext context) {
		super.beforeClass(context);
	}

	@Test
	public void verifyOmegaLogin(ITestContext context) {
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
			omegaLoginPage.loginToOmega(testData.get("username").toString(), testData.get("password").toString());

			// Select required role
			omegRolesContainerPage.selectRole(testData.get("RoleName").toString());
						
			// Verify agent name
			assertTrue(omegaAgentHomePage.verifyAgentName(testData.get("RoleName").toString()),
					"Verify agent is visible");
			
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
