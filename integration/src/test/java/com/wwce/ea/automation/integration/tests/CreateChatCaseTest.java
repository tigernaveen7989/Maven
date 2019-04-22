package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

/**
 * 
 * @author sadabala Create chat case for ENGLISH locale
 */

public class CreateChatCaseTest extends IntegrationBaseTest {

	public static Logger logger = Logger.getLogger(CreateChatCaseTest.class);
	Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();

	@Test(description = "Create chat case for ENGLISH locale ", groups = { "Regression", "Sanity","Production_Chat_3.30PM" })
	@Description("Create chat case for ENGLISH locale")
	public void verifyChatCaseTestScenarios(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40428";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("RoleName").toString());

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Create driver instance to open omega website
		this.driver=this.loadNewInstance(context);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		/// Create chat case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.chat);

		assertNotNull(caseNumber, "Verify case number is not null");

		//context.setAttribute("driverInstance", this.driver);
		
		

		/*
		 * // Create driver instance to open omega website
		 * this.loadNewInstance();
		 * 
		 * // Login to SF and verify case is created
		 * salesforceLoginPage.loginToSalesForce(IntegrationDataConstants.
		 * INTEGRATION_SF_AUT_URL, testData.get("omegausername").toString(),
		 * testData.get("omegapassword").toString());
		 * 
		 * // LOAD SF ISNTANCE driverInstance.put("SF", this.driver);
		 */

		
		// Assert all
		assertAll();

		// close EA Help browser
		/*
		 * driverInstance.get("EAHELP").close();
		 * driverInstance.get("EAHELP").quit();
		 */

	}



}
