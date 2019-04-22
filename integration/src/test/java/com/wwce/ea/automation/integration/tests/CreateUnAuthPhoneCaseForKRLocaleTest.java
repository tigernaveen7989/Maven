package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import io.qameta.allure.Description;

public class CreateUnAuthPhoneCaseForKRLocaleTest extends IntegrationBaseTest {

	@Test(description = "Create unauth phone case for SE Locale", groups = { "Regression", "Sanity" })
	@Description("Create unauth phone case")
	public void verifyUnAuthUserNotRedirectedToLoginPage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40534";
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
		this.driver = this.loadNewInstance(context);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL.replace("en", "kr"));

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Select case configuration
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("product").toString(),
				testData.get("platform").toString(), testData.get("category").toString(),
				testData.get("subcategory").toString());

		// Submit unauth login to form
		eaHelpUnAuthCaseInformationPage.submitUnAuthLoginForm(testData.get("FName").toString(),
				testData.get("LName").toString(), testData.get("Email").toString());

		assertTrue(eaHelpChannelSelectionPage.fillCaseDetailsAndVerifyChannelButtonIsEnabled(CaseType.phone,
				testData.get("subject").toString(), testData.get("description").toString(),
				testData.get("phonenumber").toString()), "Verify Get call button is enabled");

		/*
		 * // Get case number from case confirmation page
		 * assertNotNull(eaHelpCaseConfirmationPage.getCaseNumber().replace("#", ""),
		 * "Verify case number is created with attachement for unauth user" );
		 */

		// Assert all
		assertAll();

	}
}
