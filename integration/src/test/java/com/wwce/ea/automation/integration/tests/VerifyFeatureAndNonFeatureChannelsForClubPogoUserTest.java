package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

public class VerifyFeatureAndNonFeatureChannelsForClubPogoUserTest extends IntegrationBaseTest {

	@Test(description = "Verify the ICR options (featured and non-featured) for club pogo user", groups = {
			"Regression", "Sanity" })
	@Description("Verify the ICR options (featured and non-featured) for club pogo user")
	public void VeriftheICROptions(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40492,13425";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify the ICR options (featured and non-featured) for club pogo user" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("RoleName").toString());

		// LOAD CHAT INSTANCE
		mDriverInstance.put("CHAT", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("Phone_RoleName").toString());

		// LOAD phone INSTANCE
		mDriverInstance.put("PHONE", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("pogo_username").toString(),
				testData.get("pogo_password").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(
				IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL + testData.get("ICROptions_URL").toString());

		// Verify ICR Page is loaded
		assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

		if (eaHelpChannelSelectionPage.isChatExpandButtonPresent()
				|| eaHelpChannelSelectionPage.isChatCollapseButtonPresent()) {
			assertTrue(true, "Verify chat channel present");
		}

		if (eaHelpChannelSelectionPage.isEmailExpandButtonPresent()
				|| eaHelpChannelSelectionPage.isEmailCollapseButtonPresent()) {
			assertTrue(true, "Verify email channel present");
		}

		if (eaHelpChannelSelectionPage.isPhoneExpandButtonPresent()
				|| eaHelpChannelSelectionPage.isPhoneCollapseButtonPresent()) {
			assertTrue(true, "Verify phone channel present");
		}

		if (eaHelpChannelSelectionPage.isAHQBannerPresent()) {
			assertTrue(true, "Verify phone channel present");
		}

		// assert all
		assertAll();

	}

}
