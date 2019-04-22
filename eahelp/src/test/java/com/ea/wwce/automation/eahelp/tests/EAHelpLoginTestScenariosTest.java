package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * Verify that User Name is displayed along with message on Home page <First
 * Name>, Where would you like to begin Verify the login on help.ea.com with
 * incorrect User id/pwd Verify the login on help.ea.com with platinum, Gold,
 * Silver and Bronze customers User id/pwd Verify the login from Contact Us Page
 * From Portal Help page: Verify that after clicking on 'Order History' button
 * user gets navigated to
 * https://account.alpha.origin.com/cp-ui/orderhistory/index?gameId=ebisu page
 * as already Signed in user Verify the error messages displayed on Login window
 * are proper Verify the error messages displayed on Login window are properly
 * localized Verify that dropdown arrow would be displayed associated with
 * Persona name Verify that after clicking on the arrow panel/persona name
 * Logout option gets displayed in Panel Verify that correct user information
 * gets displayed when user navigates to the account page Verify that "Email
 * address is invalid" message gets displayed when user clicks on Continue
 * button without entering email id Verify that ""Email address is invalid"
 * message gets displayed when user clicks on Continue button by entering email
 * id in invalid format Verify that Register link does not get displayed when
 * user logs into help portal successfully Verify that the Login URL is changed
 * to (in all
 * browsers)https://signin.int.ea.com/p/web2/login?execution=e587971504s1&initref=https%3A%2F%2Faccounts.int.ea.com%3A443%2Fconnect%2Fauth%3Fresponse_type%3Dcode%26redirect_uri%3Dhttps%253A%252F%252Fqa3.help.ea.com%252Fsso%252Flogin%252F%26nonce%3Dnonce%26locale%3Den_US%26theme%3Deahelp%26display%3Dweb2%252Flogin%26client_id%3Dorigin_CE
 * Scenario: Verify that the login button is aligned properly on My cases login
 * page. Verify that correct user information gets displayed when user navigates
 * to the order summary page Verify that error message gets dispalyed when user
 * tries to create a user using already existing email ID Scenario : Captcha
 * should be displayed correctly when users logs in from My cases page on all
 * browsers and devices. Verify that the Origin Icon is not displayed besides
 * the login link for an unauthenticated user. Verify that the Origin Icon is
 * not displayed besides the Persona name for an authenticated user.
 * 
 * @author M1022570
 *
 */

public class EAHelpLoginTestScenariosTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpAlertScenariosTest.class);

	@Description("Verify that Login test scenarios")
	@Test(priority = 4)
	public void verifyEAHelpLoginWithNormalUser(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40142,40158,40163,40164,40165,40166,40172,40171,40180,40181,40184,40179,41391,41392,41393,40020,40021,40022,40023,40024,40025";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// is good news popup window shown
		boolean isTrue = eaHelpHomePage.isGoodNewsPopUpPresent(testData.get("normalUserName").toString());

		// Verify good news popup window present"
		assertTrue(isTrue, "Verify good news popup window present");

		// Origin logo present
		isTrue = eaHelpHomePage.isOriginLogPresent();

		// Verify signup is not present"
		assertFalse(isTrue, "Verify Origin is not present");

		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("normalUserName").toString(), testData.get("password").toString());

		// Verify user name is present
		String name = eaHelpLoginPage.verifyUserName();

		isTrue = eaHelpHomePage.isSingUpLinkPresent();

		// Verify signup is not present"
		assertFalse(isTrue, "Verify signup is not present");

		// Origin logo present
		isTrue = eaHelpHomePage.isOriginLogPresent();

		// Verify signup is not present"
		assertFalse(isTrue, "Verify Origin is not present");

		// Click on account management icon
		eaHelpAccountManagementPage.clickManageAccount();

		// Verify username order management
		eaHelpAccountManagementPage.orderManagement();

		// Switch to account management page
		eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();

		// Verify user name is present
		name = eaHelpAccountManagementPage.verifyUserNameOnOrderManagementPage();

		// String t=testData.get("normalUserName").toString().split("@")[0];

		// Verify username is present
		assertTrue(name.equalsIgnoreCase(testData.get("userName").toString()), "Verify username is present");

		// System.out.println(a);

		// Delete all cookies
		// eaHelpLoginPage.deleteAllCookies();

		// logout from EA Help
		// eaHelpLoginPage.logoutFromEAHelp();

		// Delete all cookies
		// eaHelpLoginPage.deleteAllCookies();

		// Verify login link is present
		// assertTrue(eaHelpLoginPage.IsloginLinkPresent(), "verify login link is
		// present");

		// Assert all
		assertAll();

	}

	@Description("Verify that Login test scenarios")
	@Test(priority = 3)
	public void verifyEAHelpLoginWithGoldUser(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40146";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("goldUser").toString(), testData.get("password").toString());

		// Verify user name is present
		String a = eaHelpLoginPage.verifyUserName();

		// Verify username is present
		assertNotNull(a, "Verify username is present");

		assertTrue(eaHelpLoginPage.isLogoutDropdownPresent(), "Verify logout link is present");

		// Assert all
		assertAll();

	}

	@Description("Verify that Login test scenarios")
	@Test(priority = 2)
	public void verifyEAHelpLoginWithPlatinumUser(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40146";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("platinumUser").toString(), testData.get("password").toString());

		// Verify user name is present
		String a = eaHelpLoginPage.verifyUserName();

		// Verify username is present
		assertNotNull(a, "Verify username is present");

		// Assert all
		assertAll();

	}

	@Description("Verify that Login test scenarios")
	@Test(priority = 1)
	public void verifyEAHelpLoginWithSilverUser(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40146";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("silverUser").toString(), testData.get("password").toString());

		// Verify user name is present
		String a = eaHelpLoginPage.verifyUserName();

		// Verify username is present
		assertNotNull(a, "Verify username is present");

		// Assert all
		assertAll();
	}

	@Description("Verify that Login test scenarios")
	@Test(priority = 0)
	public void verifyEAHelpLoginWithBronzeUser(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40146";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("bronzeUser").toString(), testData.get("password").toString());

		// Verify user name is present
		String a = eaHelpLoginPage.verifyUserName();

		// Verify username is present
		assertNotNull(a, "Verify username is present");

		// Assert all
		assertAll();
	}

	@Description("Verify that Login test scenarios")
	@Test(priority = 5)
	public void verifyEAHelpLoginOnContactUsPage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40147";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// click on contact us button
		eaHelpHomePage.clickOnContactUsButton();

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("normalUserName").toString(), testData.get("password").toString());

		// Verify user name is present
		String a = eaHelpLoginPage.verifyUserName();

		// Verify username is present
		assertNotNull(a, "Verify username is present");

		// Assert all
		assertAll();
	}

	@Description("Verify that Login test scenarios")
	@Test(priority = 6)
	public void verifyEAHelpLoginWithInValidUser(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40143";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// click on contact us button
		eaHelpHomePage.clickOnContactUsButton();

		// Verify login error is shown
		String error = eaHelpLoginPage.verifyLoginError(testData.get("invalidUser").toString(),
				testData.get("password").toString());

		assertTrue(error.contains(testData.get("loginError").toString()), "Verify login error is shown");

		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "es"));

		// Verify login error is shown
		error = eaHelpLoginPage.verifyLoginError(testData.get("invalidUser").toString(),
				testData.get("password").toString());

		assertTrue(error.contains(testData.get("loginError_ES").toString()), "Verify login error is shown");

		/**
		 * Verify that "Email address is invalid" message gets displayed when user
		 * clicks on Continue button without entering email id
		 * 
		 */

		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Verify login error is shown
		error = eaHelpLoginPage.verifyLoginError(testData.get("invalidEmail").toString(),
				testData.get("password").toString());

		assertTrue(error.contains(testData.get("invalidEmailError").toString()), "Verify login error is shown");

		/**
		 * Verify that ""Email address is invalid" message gets displayed when user
		 * clicks on Continue button by entering email id in invalid format
		 * 
		 */

		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Verify login error is shown
		error = eaHelpLoginPage.verifyLoginError(testData.get("emaptyEmail").toString(),
				testData.get("password").toString());

		assertTrue(error.contains(testData.get("loginError").toString()), "Verify login error is shown");

		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "it"));

		// Verify login error is shown
		error = eaHelpLoginPage.verifyLoginError(testData.get("invalidUser").toString(),
				testData.get("password").toString());

		assertTrue(error.contains(testData.get("loginError_IT").toString()), "Verify login error is shown");

		// Assert all
		assertAll();
	}

	/**
	 * Scenario : Captcha should be displayed correctly when users logs in from My
	 * cases page on all browsers and devices. *
	 * 
	 * @param context
	 */

	@Description("Verify that Login test scenarios")
	@Test(priority = 0)
	public void verifyCaptchaOnMycasesPage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		boolean isTrue;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "41390";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load ea help url
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString() + "my-cases/");

		// click on login link
		eaHelpLoginPage.clickOnLoginLink();

		for (int i = 0; i <= 10; i++) {

			// enter username and password
			eaHelpLoginPage.enterUserNameAndPassword(testData.get("invalidUser").toString(),
					testData.get("password").toString());

			isTrue = eaHelpLoginPage.isCaptchaImageShown();
			if (isTrue) {
				assertTrue(isTrue, "Verify captcha image is shown");
				break;
			} else {
				assertTrue(false, "Verify captcha image is shown");
			}
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
