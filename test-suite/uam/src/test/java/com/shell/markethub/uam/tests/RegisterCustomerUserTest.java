package com.shell.markethub.uam.tests;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;
import com.shell.markethub.uam.pageobjects.SubmitAndConfirmPage;

import io.qameta.allure.Description;

public class RegisterCustomerUserTest extends UAMBaseTest{
	private static Logger logger = Logger.getLogger(RegisterCustomerUserTest.class);
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
	@Description("verify_register_customer_user")
	public void verify_register_customer_user(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String firstName = testData.get("firstName").toString();
		String lastName = testData.get("lastName").toString();
		String mobileNumber = testData.get("mobileNumber").toString();
		String email = testData.get("email").toString();
		String consentPrivacy = testData.get("consentPrivacy").toString();
		String country = testData.get("country").toString();
		String preferredCommunication = testData.get("preferredCommunication").toString();
		String businessPurpose = testData.get("businessPurpose").toString();
		String timeZone = testData.get("timeZone").toString();
		String accountNumber = testData.get("accountNumber").toString();
		String site = testData.get("site").toString();
		String language = testData.get("language").toString();
		String experience = testData.get("experience").toString();
		String marketing = testData.get("marketing").toString();
	
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnManageProfileIcon();
		assertTrue(searchCustomerUserPage.verifyTitle(), "Search Customer User Title is displaying");
		searchCustomerUserPage.clickOnRegisterCustomerUserLink();
		assertEquals(registerCustomerUserPage.getTitle(), "Register Customer User", "Verify Register Customer User Title");
		registerCustomerUserPage.enterFirstName(firstName);
		registerCustomerUserPage.clickOnSearchButton();
		registerCustomerUserPage.clickOnCreateCRMContactButton();
		registerCustomerUserPage.clickOnYesRadioButton();
		
		userDetailsPage.enterFirstNameOnUserDetails(firstName);
		userDetailsPage.enterLastNameOnUserDetails(lastName);
		userDetailsPage.enterEmailOnUserDetails(email);
		userDetailsPage.enterMobileNumberOnUserDetails(mobileNumber);
		userDetailsPage.selectConsentPrivacyOnUserDetails(consentPrivacy);
		userDetailsPage.selectCountryOnUserDetails(country);
		userDetailsPage.selectPreferredCommunicationMethodOnUserDetails(preferredCommunication);
		userDetailsPage.selectBusinessPurposeOnUserDetails(businessPurpose);
		userDetailsPage.selectTimeZoneOnUserDetails(timeZone);
		userDetailsPage.enterAccountNumberOnUserDetails(accountNumber);
		userDetailsPage.clickOnSearchUserDetailsButton();
		userDetailsPage.clickOnSelectCheckboxOnUserDetails();
		userDetailsPage.clickOnDefaultRadioButtonOnUserDetails();
		userDetailsPage.clickOnNextUserDetailsButton();
		
		setAccessAndTargetingAttributesPage.clickOnAddSiteAccessSetAccessLink();
		setAccessAndTargetingAttributesPage.selectSiteSetAccessDropdown(site);
		setAccessAndTargetingAttributesPage.selectLanguageSetAccessDropdown(language);
		setAccessAndTargetingAttributesPage.selectExperienceSetAccessDropdown(experience);
		setAccessAndTargetingAttributesPage.selectMarketingSetAccessDropdown(marketing);
		setAccessAndTargetingAttributesPage.selectMarketingPreferenceSetAccessDropdown();
		setAccessAndTargetingAttributesPage.selectBrandPreferenceSetAccessDropdown();
		setAccessAndTargetingAttributesPage.selectClassOfBusinessSetAccessDropdown();
		setAccessAndTargetingAttributesPage.clickOnSaveSetAccessButton();
		setAccessAndTargetingAttributesPage.clickOnDefaultSetAccessRadioButton();
		setAccessAndTargetingAttributesPage.clickOnNormalUserSetAccessRadioButton();
		setAccessAndTargetingAttributesPage.clickOnNextSetAccessButton();
		
		if(testEnvironment.equalsIgnoreCase("perf")) {
			userDetailsPage.clickOnCompleteRegistration();
			assertEquals(submitAndConfirmPage.getRegistrationSuccessfulText(), "Registration Successful", "Verify Registration Successful Text");
		}
		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = "sanity")
	@Description("verify_register_ehaulier_customer_user")
	public void verify_register_ehaulier_customer_user(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String lastName = testData.get("lastName").toString();
		String firstName = testData.get("firstName").toString();
		String eHaulierNumber = testData.get("eHaulierNumber").toString();
		String email = testData.get("email").toString();
		String site = testData.get("site").toString();
		String language = testData.get("language").toString();
		String experience = testData.get("experience").toString();
	
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnManageProfileIcon();
		assertTrue(searchCustomerUserPage.verifyTitle(), "Search Customer User Title is displaying");
		searchCustomerUserPage.clickOnRegisterCustomerUserLink();
		assertEquals(registerCustomerUserPage.getTitle(), "Register Customer User", "Verify Register Customer User Title");
		registerCustomerUserPage.clickOnEhaulierCheckbox();
		registerCustomerUserPage.enterEhaulierNumber(eHaulierNumber);
		registerCustomerUserPage.clickOnEhaulierSearchButton();
		registerCustomerUserPage.clickOnEhaulierNextButton();
		
		userDetailsPage.enterFirstNameOnUserDetails(firstName);
		userDetailsPage.enterLastNameOnUserDetails(lastName);
		userDetailsPage.enterEmailOnUserDetails(email);
		userDetailsPage.clickOnNextUserDetailsButton();
		
		setAccessAndTargetingAttributesPage.selectSiteSetAccessDropdown(site);
		setAccessAndTargetingAttributesPage.selectLanguageSetAccessDropdown(language);
		setAccessAndTargetingAttributesPage.selectExperienceSetAccessDropdown(experience);
		setAccessAndTargetingAttributesPage.clickOnNormalUserSetAccessRadioButton();
		setAccessAndTargetingAttributesPage.clickOnNextSetAccessButton();
		
		transactRolesPage.clickOnProductDocumentsCheckbox();
		transactRolesPage.clickOnNextButton();
		
		if(testEnvironment.equalsIgnoreCase("perf")) {
			userDetailsPage.clickOnEhaulierCompleteRegistration();
			assertEquals(submitAndConfirmPage.getRegistrationSuccessfulText(), "Registration Successful", "Verify Registration Successful Text");
		}
		assertAll();
	}
}
