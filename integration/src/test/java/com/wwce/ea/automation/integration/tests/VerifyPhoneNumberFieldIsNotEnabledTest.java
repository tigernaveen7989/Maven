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
 * @author sadabala Verfiy that user is not allowed to Submit a C2C request when
 *         he has not entered the valid phone number(total numericals) on the
 *         phone number fi
 */

public class VerifyPhoneNumberFieldIsNotEnabledTest extends IntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyPhoneNumberFieldIsNotEnabledTest.class);
	Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();

	@Test(description = "Verify C2C button is disabled if phone number is invalid ", groups = { "Regression",
			"Sanity" })
	@Description("Verify C2C button is disabled if phone number is invalid")
	public void verifyGetCallOptionIsEnabled(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39133";
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
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Create phone case
		assertFalse(eaHelpChannelSelectionPage.verifyChannelButtonIsEnabled(testData, CaseType.phone),
				"Create phone case");

		System.out.println(eaHelpChannelSelectionPage.getInvalidPhoneNumberErrorMessage());

		// Verify invalid phone number error message
		assertEquals(testData.get("error_message").toString(),
				eaHelpChannelSelectionPage.getInvalidPhoneNumberErrorMessage(),
				"Verify invalid phone number error message");

		// Assert all
		assertAll();

	}

}
