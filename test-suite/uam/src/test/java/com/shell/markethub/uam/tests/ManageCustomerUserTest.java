package com.shell.markethub.uam.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;

public class ManageCustomerUserTest extends UAMBaseTest{
	private static Logger logger = Logger.getLogger(ManageCustomerUserTest.class);
	/**
	 * @description getDriver from BaseTest
	 */
	@BeforeMethod
	public void setUp() {
		super.setUp();
	}
		
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = "sanity")
	@Description("verify_search_customer_user")
	public void verify_search_customer_user(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String searchEmail = testData.get("searchEmail").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnManageProfileIcon();
		assertTrue(searchCustomerUserPage.verifyTitle(), "Search Customer User Title is displaying");
		searchCustomerUserPage.enterEmail(searchEmail);
		searchCustomerUserPage.clickSearchButton();
		assertTrue(searchCustomerUserPage.verifyUserListTablePresent(), "User List Table is displaying");
		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups= {"sanity"})
	@Description("verify_impersonate_customer_user")
	public void verify_impersonate_customer_user(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String searchEmail = testData.get("searchEmail").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnManageProfileIcon();
		assertTrue(searchCustomerUserPage.verifyTitle(), "Search Customer User Title is displaying");
		searchCustomerUserPage.enterEmail(searchEmail);
		searchCustomerUserPage.clickSearchButton();
		assertTrue(searchCustomerUserPage.verifyUserListTablePresent(), "User List Table is displaying");
		searchCustomerUserPage.clickOnUserListCheckBox1();
		searchCustomerUserPage.clickOnImpersonateButton();
		assertEquals(homePage.verifyYouAreImpersonatingText(), "You are impersonating:", "Verify You are impersonating Text");
		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups= {"sanity"})
	@Description("verify_deactivate_customer_user")
	public void verify_deactivate_customer_user(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String searchEmail = testData.get("searchEmail").toString();
		String searchUserName = testData.get("searchUserName").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnManageProfileIcon();
		assertTrue(searchCustomerUserPage.verifyTitle(), "Verify Search Customer User Title");
		searchCustomerUserPage.enterEmail(searchEmail);
		searchCustomerUserPage.enterUserName(searchUserName);
		searchCustomerUserPage.clickSearchButton();
		assertTrue(searchCustomerUserPage.verifyUserListTablePresent(), "Verify User List Table");
		assertEquals(searchCustomerUserPage.verifyUserStatus(), "ACTIVE", "Verify user status active");
		searchCustomerUserPage.clickOnUserListCheckBox1();
		searchCustomerUserPage.clickOnDeactivateButton();
		searchCustomerUserPage.clickOnOkButtonOnPopup();
		searchCustomerUserPage.clickOnOkButtonOnPopup();
		assertEquals(searchCustomerUserPage.verifyUserStatus(), "DEACTIVATED", "Verify user status deactived");
		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups= {"sanity"})
	@Description("verify_activate_customer_user")
	public void verify_activate_customer_user(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String searchEmail = testData.get("searchEmail").toString();
		String searchUserName = testData.get("searchUserName").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnManageProfileIcon();
		assertTrue(searchCustomerUserPage.verifyTitle(), "Verify Search Customer User Title");
		searchCustomerUserPage.enterEmail(searchEmail);
		searchCustomerUserPage.enterUserName(searchUserName);
		searchCustomerUserPage.clickSearchButton();
		assertTrue(searchCustomerUserPage.verifyUserListTablePresent(), "Verify User List Table");
		assertEquals(searchCustomerUserPage.verifyUserStatus(), "DEACTIVATED", "Verify user status deactived");
		searchCustomerUserPage.clickOnUserListCheckBox1();
		searchCustomerUserPage.clickOnActivateButton();
		searchCustomerUserPage.clickOnOkButtonOnPopup();
		searchCustomerUserPage.clickOnOkButtonOnPopup();
		assertEquals(searchCustomerUserPage.verifyUserStatus(), "ACTIVE", "Verify user status active");
		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups= {"sanity"})
	@Description("verify_update_customer_user")
	public void verify_update_customer_user(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String searchUserName = testData.get("searchUserName").toString();
		String email;
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnManageProfileIcon();
		assertTrue(searchCustomerUserPage.verifyTitle(), "Verify Search Customer User Title");
		searchCustomerUserPage.enterUserName(searchUserName);
		searchCustomerUserPage.clickSearchButton();
		assertTrue(searchCustomerUserPage.verifyUserListTablePresent(), "Verify User List Table");
		searchCustomerUserPage.clickOnUserListCheckBox1();
		searchCustomerUserPage.clickOnEditButton();
		
		email = basePageObject.generateRandomCharacters();
		
		userDetailsPage.enterEmailOnUserDetails(email+"@yahoo.co.in");
		userDetailsPage.clickOnNextUserDetailsButton();
		
		setAccessAndTargetingAttributesPage.clickOnSuperUserSetAccessRadioButton();
		setAccessAndTargetingAttributesPage.clickOnNormalUserSetAccessRadioButton();
		setAccessAndTargetingAttributesPage.clickOnNextSetAccessButton();
		
		assertEquals(userDetailsPage.getEmailAddress(), email+"@yahoo.co.in", "Verify updated email is present");
		
		if(testEnvironment.equalsIgnoreCase("perf")) {
			userDetailsPage.clickOnUpdateUserDataButton();
			
			assertEquals(submitAndConfirmPage.getUserDataSuccessfullyUpdatedText(), "User data successfully updated", "Verify User data successfully updated Text");
			
		}
		assertAll();
	}
}
