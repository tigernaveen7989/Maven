package com.shell.markethub.uam.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDetailsPage extends UAMBasePageObject{

	public UserDetailsPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(UserDetailsPage.class);
	@FindBy(xpath = "//input[@id='user_FirstName']")
	WebElement firstNameUserDetailsEditbox;
	@FindBy(xpath = "//input[@id='user_LastName']")
	WebElement lastNameUserDetailsEditbox;
	@FindBy(xpath = "//input[@id='user_Email']")
	WebElement emailUserDetailsEditbox;
	@FindBy(xpath = "//input[@id='user_Mobile']")
	WebElement mobileNumberUserDetailsEditbox;
	@FindBy(xpath = "//input[@id='NcustomerNumber']")
	WebElement accountNumberUserDetailsEditbox;
	@FindBy(xpath = "//select[@id='user_TimeZone']")
	WebElement timeZoneUserDetailsDropdown;
	@FindBy(xpath = "//select[@id='user_BusinessPurpose']")
	WebElement businessPurposeUserDetailsDropdown;
	@FindBy(xpath = "//select[@id='user_PrefCommMethod']")
	WebElement preferredCommunicationMethodUserDetailsDropdown;
	@FindBy(xpath = "//select[@id='user_Country']")
	WebElement countryUserDetailsDropdown;
	@FindBy(xpath = "//select[@id='user_Privacy']")
	WebElement consentPrivacyUserDetailsDropdown;
	@FindBy(xpath = "//button[@id='searchNonGsap']")
	WebElement searchUserDetailsButton;
	@FindBy(xpath = "//button[contains(@id,'UserDetailsNext')]")
	WebElement nextUserDetailsButton;
	@FindBy(xpath = "//button[contains(@id,'UserDetailsPrev')]")
	WebElement prevUserDetailsButton;
	@FindBy(xpath = "//input[@id='account1']")
	WebElement selectUserDetailsCheckbox;
	@FindBy(xpath = "//input[@name='soldto']")
	WebElement defaultUserDetailsRadioButton;
	@FindBy(xpath = "//button[@id='reviewUserDetailsNext']")
	WebElement completeRegistrationUserDetailsButton;
	@FindBy(xpath = "//button[contains(@id,'ehaulierreviewUserDetailsNext')]")
	WebElement eHaulierCompleteRegistrationUserDetailsButton;
	@FindBy(xpath = "//button[@id='reviewUserDetailsNext']")
	WebElement updateUserDataButton;
	@FindBy(xpath = "//span[@id='reviewEmail']")
	WebElement emailAddress;
	
	public void enterFirstNameOnUserDetails(String firstName) throws Exception {
		sendKeys(firstNameUserDetailsEditbox, generateRandomCharacters());
	}
	
	public void enterLastNameOnUserDetails(String lastName) throws Exception {
		sendKeys(lastNameUserDetailsEditbox, generateRandomCharacters());
	}
	
	public void enterEmailOnUserDetails(String email) throws Exception {
		sendKeys(emailUserDetailsEditbox, email);
	}
	
	public void enterMobileNumberOnUserDetails(String email) throws Exception {
		sendKeys(mobileNumberUserDetailsEditbox, email);
	}
	
	public void enterAccountNumberOnUserDetails(String accountNumber) throws Exception {
		sendKeys(accountNumberUserDetailsEditbox, accountNumber);
	}
	
	public void selectCountryOnUserDetails(String country) throws Exception {
		selectVisibleText(countryUserDetailsDropdown, country);
	}
	
	public void selectConsentPrivacyOnUserDetails(String consentPrivacy) throws Exception {
		selectVisibleText(consentPrivacyUserDetailsDropdown, consentPrivacy);
	}
	
	public void selectPreferredCommunicationMethodOnUserDetails(String preferredCommunication) throws Exception {
		selectVisibleText(preferredCommunicationMethodUserDetailsDropdown, preferredCommunication);
	}
	
	public void selectBusinessPurposeOnUserDetails(String businessPurpose) throws Exception {
		selectVisibleText(businessPurposeUserDetailsDropdown, businessPurpose);
	}
	
	public void selectTimeZoneOnUserDetails(String timeZone) throws Exception {
		selectVisibleText(timeZoneUserDetailsDropdown, timeZone);
	}
	
	public void clickOnSearchUserDetailsButton() throws Exception {
		click(searchUserDetailsButton);
		Thread.sleep(2000);
	}
	
	public void clickOnSelectCheckboxOnUserDetails() throws Exception {
		click(selectUserDetailsCheckbox);
		Thread.sleep(1000);
	}
	
	public void clickOnDefaultRadioButtonOnUserDetails() throws Exception {
		click(defaultUserDetailsRadioButton);
	}
	
	public void clickOnNextUserDetailsButton() throws Exception {
		click(nextUserDetailsButton);
		Thread.sleep(2000);
	}
	
	public void clickOnCompleteRegistration() throws Exception {
		click(completeRegistrationUserDetailsButton);
		Thread.sleep(2000);
	}
	
	public void clickOnEhaulierCompleteRegistration() throws Exception {
		click(eHaulierCompleteRegistrationUserDetailsButton);
		Thread.sleep(2000);
	}
	
	public void clickOnUpdateUserDataButton() throws Exception {
		click(updateUserDataButton);
	}
	
	public String getEmailAddress() throws Exception {
		return getText(emailAddress);
	}
}
