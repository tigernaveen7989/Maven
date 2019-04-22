package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

public class VerifyTheFieldNameCountryCodeIsChanged extends IntegrationBaseTest {

	@Test
	public void verifyTheFieldNameCountryCodeIsChanged(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;
		String[] country;
		String[] countryLabel;
		String[] selectedCountry;
		String url = null;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "36171";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// Login as a chat advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.
		INTEGRATION_OMEGA_AUT_URL, testData.get("omegausername").toString(),
		testData.get("omegapassword").toString(),
		testData.get("RoleName").toString());
		

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// Select case configuration
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("product").toString(),
				testData.get("platform").toString(), testData.get("category").toString(),
				testData.get("subcategory").toString());

		country = testData.get("country").toString().split(",");
		countryLabel = testData.get("country_Label").toString().split(",");
		selectedCountry = testData.get("selected_country").toString().split(",");

		for (int i = 0; i < country.length; i++) {

			assertTrue(eaHelpChannelSelectionPage.verifyCountryFieldLabel().trim().equals(countryLabel[i]),
					"Verify country label is changed");

			assertTrue(eaHelpChannelSelectionPage.verifySelectedCountry().trim().equals(selectedCountry[i]),
					"Verify slected country label");

			if (i < country.length - 1) {
				url = eaHelpChannelSelectionPage.getCurrentPageURL().replace(country[i], country[i + 1]);

				eaHelpLoginPage.loadEAHelp(url);
			}
		}

		// Assert all
		assertAll();

	}

}
