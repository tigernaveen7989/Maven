package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import io.qameta.allure.Description;

/**
 * 
 * @author sadabala Create phone case for it (ITALIA) locale
 */

public class CreatePhoneCaseForITLocaleTest extends IntegrationBaseTest {

	public static Logger logger = Logger.getLogger(CreatePhoneCaseForITLocaleTest.class);
	Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();

	@Test(description = "Create phone case for it (ITALIA) locale", groups = { "Regression", "Sanity",
			"Production_Phone_3.30PM" })
	@Description("Create phone case for it (ITALIA) locale")
	public void verifyGetCallOptionIsEnabled(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40441";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login as a phone advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("RoleName").toString());

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL.replace("en", "it"));

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Verify Get call option is enabled
		assertTrue(eaHelpChannelSelectionPage.verifyChannelButtonIsEnabled(testData, CaseType.phone),
				"Verify Get call option is enabled");

		/*
		 * 
		 * 
		 * assertNotNull(caseNumber, "Verify case number is not null"); // LOAD EAHELP
		 * ISNTANCE driverInstance.put("EAHELP", this.driver);
		 * 
		 * //Switch to omega this.switchToInstance(driverInstance.get("OMEGA"));
		 * 
		 * //Verify case details saved successfully assertTrue(
		 * omegaCaseDetailsPage.saveCaseDetails(testData.get("subject").toString
		 * (),testData.get("description").toString(),testData.get("status"). toString(),
		 * testData.get("resolutionStatus").toString(),
		 * testData.get("reason").toString()),
		 * "Verify case details saved successfully");
		 * 
		 * omegaLoginPage.isSpinnerInvisible(60);
		 * 
		 * // Click on save and next case button
		 * omegaSaveCaseConfirmationPage.clickOnSendAndNextCase();
		 * 
		 * omegaLoginPage.isSpinnerInvisible(60);
		 */

		// Assert all
		assertAll();

	}

}
