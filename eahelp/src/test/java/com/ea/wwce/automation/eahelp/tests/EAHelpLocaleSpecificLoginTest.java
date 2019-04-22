package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

/**
 * Verify that panel text also gets changed when user changes the locale Verify
 * that specific locale's account page gets displayed when user clicks on the
 * links in Manage Your Account section. 
 * @author M1022570
 *
 */

public class EAHelpLocaleSpecificLoginTest extends EAHelpBaseTest {

	@Test(priority = 0)
	public void verifyLoginTestIsChangedLocaleWise(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		String[] locales;
		String[] logintext;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40167";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		locales = testData.get("locale").toString().split(",");
		logintext = testData.get("LoginLocaleText").toString().split(",");
		int i = 0;
		for (String locale : locales) {
			// load ea help url
			eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", locale));
			String txt = eaHelpLoginPage.getLoginText().trim();
			// System.out.println(txt.equalsIgnoreCase(logintext[i]));
			assertTrue(txt.equalsIgnoreCase(logintext[i]), "Verify login text is localized");
			i++;
		}

		assertAll();
	}

	@Test(priority = 1)
	public void verifyAccountLinksAreLocalized(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		String[] locales;
		String[] welcomeText;
		//String[] pageTitleText;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40169";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		locales = testData.get("locale").toString().split(",");
	//	pageTitleText = testData.get("pageTitle_Locale").toString().split(",");
		welcomeText = testData.get("welcometext_Locale").toString().split(",");

		// load ea help url
		// eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("normalUserName").toString(), testData.get("password").toString());

		int i = 0;
		for (String locale : locales) {

			// load ea help url
			eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", locale));			

			// Click on account management icon
			eaHelpAccountManagementPage.clickManageAccount();

			// Verify username order management
			eaHelpAccountManagementPage.orderManagement();

			// Switch to account management page
			eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();

			// Verify welcome text is localized
			String txt = eaHelpAccountManagementPage.verifyWelcomeTextIsLocalized().replace(",", "").trim()
					.split(" ")[0];

			//System.out.println(txt.equals(welcomeText[i]));

			assertTrue(txt.equals(welcomeText[i]), "Verify welcome text is localized");

			// close current window
			eaHelpAccountManagementPage.closeCurrentWindow();

			// switch to parent window
			eaHelpAccountManagementPage.switchWindowToEAHelpHomePageTitle();

			// Verify username order management
			eaHelpAccountManagementPage.recoverPassword();

			// Switch to account management page
			eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();

			// Verify welcome text is localized
			txt = eaHelpAccountManagementPage.verifyWelcomeTextIsLocalized().replace(",", "").trim().split(" ")[0];

			assertTrue(txt.equals(welcomeText[i]), "Verify welcome text is localized");

			// close current window
			eaHelpAccountManagementPage.closeCurrentWindow();

			// switch to parent window
			eaHelpAccountManagementPage.switchWindowToEAHelpHomePageTitle();

			// Verify username order management
			eaHelpAccountManagementPage.homePageEmailAddress();

			// Switch to account management page
			eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();

			// Verify welcome text is localized
			txt = eaHelpAccountManagementPage.verifyWelcomeTextIsLocalized().replace(",", "").trim().split(" ")[0];
			assertTrue(txt.equals(welcomeText[i]), "Verify welcome text is localized");

			// close current window
			eaHelpAccountManagementPage.closeCurrentWindow();

			// switch to parent window
			eaHelpAccountManagementPage.switchWindowToEAHelpHomePageTitle();

			// Verify username order management
			eaHelpAccountManagementPage.redeemCode();

			// Switch to account management page
			eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();

			// Verify welcome text is localized
			txt = eaHelpAccountManagementPage.verifyWelcomeTextIsLocalized().replace(",", "").trim().split(" ")[0];

			assertTrue(txt.equals(welcomeText[i]), "Verify welcome text is localized");

			// close current window
			eaHelpAccountManagementPage.closeCurrentWindow();

			// switch to parent window
			eaHelpAccountManagementPage.switchWindowToEAHelpHomePageTitle();

			// Verify username order management
			eaHelpAccountManagementPage.accountSecurity();

			// Switch to account management page
			eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();

			// Verify welcome text is localized
			txt = eaHelpAccountManagementPage.verifyWelcomeTextIsLocalized().replace(",", "").trim().split(" ")[0];

			assertTrue(txt.equals(welcomeText[i]), "Verify welcome text is localized");

			// close current window
			eaHelpAccountManagementPage.closeCurrentWindow();

			// switch to parent window
			eaHelpAccountManagementPage.switchWindowToEAHelpHomePageTitle();

			i++;
		}

		assertAll();
	}

	@BeforeMethod
	public void openBrowser(ITestContext context) {
		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
	}

	@AfterMethod
	public void quitBrowser(ITestContext context) {
		this.closeDriverInstance(this.driver);
		this.driver = this.loadNewInstance(context);
	}

}
