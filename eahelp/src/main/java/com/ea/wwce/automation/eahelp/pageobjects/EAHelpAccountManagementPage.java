package com.ea.wwce.automation.eahelp.pageobjects;

/**
 * 
 * @author msomagari
 * @description EA HELP Account Management info
 * 
 **/

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class EAHelpAccountManagementPage extends EAHelpBasePageObject {

	public EAHelpAccountManagementPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpAccountManagementPage.class);
	@FindBy(xpath = "//span[@data-full='Manage your Account']")
	WebElement manageYourAccount;
	@FindBy(css = "#accounts #recover-password")
	WebElement changeOrRecoverPassword;
	@FindBy(css = "#accounts #change-email")
	WebElement changeEmailAddress;
	@FindBy(css = "#accounts #order-management")
	WebElement orderManagement;
	@FindBy(css = "#accounts #redeem-code")
	WebElement redeemCode;
	@FindBy(css = "#accounts #account-security")
	WebElement accountSecurity;
	@FindBy(css = "#accounts #persona-merge")
	WebElement identityManagement;
	@FindBy(css = "#accounts #my-conversations")
	WebElement myCases;
	@FindBy(id = "editSecuritySection2")
	WebElement editSecuritySectionButton;
	@FindBy(id = "challenge_securityanswer")
	WebElement editSecuritySectionSecurityAnswer;
	@FindBy(id = "savebtn_security_question_challenge")
	WebElement editSecuritySectionSecurityAnswerContinueButton;
	@FindBy(id = "challenge_securityanswer_for_basic_info")
	WebElement editAboutMeSecurityAnswer;
	@FindBy(id = "savebtn_security_question_challenge_for_basic_info")
	WebElement editAboutMeSecurityAnswerContinueButton;
	@FindBy(xpath = "//div[@id='container_form_securityanswer']//span[text()='Invalid Security Answer.']")
	WebElement securityQuestionErrorMessage;
	@FindBy(id = "newPasswordR")
	WebElement confirmNewPassword;
	@FindBy(id = "originalPassword")
	WebElement currentPassword;
	@FindBy(id = "newPassword")
	WebElement newPassword;
	@FindBy(id = "savebtn_cpass")
	WebElement saveNewPasswordButton;
	@FindBy(xpath = "//div[@id='container_form_originalpassword']//span[@class='origin-ux-textbox-status-message origin-ux-status-message']")
	WebElement invalidCurrentPassword;
	@FindBy(xpath = "//div[@id='container_form_newpassword']//span[@class='origin-ux-textbox-status-message origin-ux-status-message']")
	WebElement newPasswordDoesNotMeetCriteriaMessage;
	@FindBy(xpath = "//div[@id='container_form_newpasswordr']//span[@class='origin-ux-textbox-status-message origin-ux-status-message']")
	WebElement newPasswordDoesNotMatchConfirmPswd;
	@FindBy(id = "nav_privacy")
	WebElement customerPortalPrivacySettingsTabn;
	@FindBy(id = "profileVisibility")
	WebElement customerPortalPrivacySettingsTabProfileVisibility;
	@FindBy(xpath = "//div[@id='profileVisibility']//div[@class='drop-down-options-container']//span[text()='Friends']")
	WebElement profileVisibilityFriendsContainer;
	@FindBy(xpath = "//div[@id='profileVisibility']//div[@class='origin-ux-drop-down-selection']//span[text()='Friends']")
	WebElement profileVisibilityFriendsSelection;
	@FindBy(id = "emailDis")
	WebElement emailAddress;
	@FindBy(id = "xBoxDis")
	WebElement xBoxLive;
	@FindBy(id = "psnDis")
	WebElement psn;
	@FindBy(xpath = "//label[@id='showRealName' and @class='checkboxstyle_1 checkbox_1 checkboxstyle_1_init']")
	WebElement uncheckedshowRealName;
	@FindBy(xpath = "//label[@id='showRealName' and @class='checkboxstyle_1 checkbox_1 checkboxstyle_1_init checkboxstyle_1_checked']")
	WebElement checkedShowRealName;

	@FindBy(xpath = "//div[div[h3[text()='Origin Blocked Users List']]]")
	WebElement originBlockedUserListSection;
	@FindBy(xpath = "//div[div[h3[text()='Origin Blocked Users List']]]//div[@id='blockedUsers']")
	WebElement blockedUsersTextArea;
	@FindBy(xpath = "//div[div[h3[text()='Origin Blocked Users List']]]//input[@id='blockinput']")
	WebElement blockUserTextBox;
	@FindBy(xpath = "//div[div[@class='title1']]")
	WebElement blockUserTextBoxTitleMsg;

	@FindBy(xpath = "//dl[dt[text()='Allow users to search for me by']]")
	WebElement allowUserToSearchForMeBySection;

	@FindBy(xpath = "//div[@class='inner'][contains(.,'About Me')]")
	WebElement aboutMe;
	@FindBy(xpath = "//a[@id='editBasicInformation']")
	WebElement basicInfo;
	@FindBy(id = "ebi_originId-input")
	WebElement basicInfoOriginId;
	@FindBy(id = "ebi_email-input")
	WebElement emailId;
	@FindBy(xpath = "//a[@id='savebtn_basicinfo']/span")
	WebElement saveBtn;
	@FindBy(id = "ebi_firstname-input")
	WebElement firstName;
	@FindBy(id = "email")
	WebElement emailText;
	@FindBy(css = ".user_name")
	WebElement userName;
	@FindBy(css = "#loginWelcome")
	WebElement loginText;

	@FindBy(id = "ebi_lastname-input")
	WebElement lastName;
	@FindBy(id = "origin-ux-dop-label-day")
	WebElement dobDay;
	@FindBy(id = "origin-ux-dop-label-month")
	WebElement dobMonth;
	@FindBy(id = "origin-ux-dop-label-year")
	WebElement dobYear;
	@FindBy(id = "questionContainer_for_basic_info")
	WebElement seqQuestion;
	@FindBy(id = "challenge_securityanswer_for_basic_info")
	WebElement seqAnswer;
	@FindBy(id = "savebtn_security_question_challenge_for_basic_info")
	WebElement saveSeqQuestion;
	@FindBy(id = "account-selector")
	WebElement selectAccounttab;
	@FindBy(id = "change-email")
	WebElement changeEmail;
	@FindBy(xpath = "//a[@id='savebtn_basicinfo']")
	WebElement saveBasicinfo;
	// Below xpath has to be fixed
	String selectDate = "//div[@id='origin-ux-dop-label-day']//div//div//div//div/a//span[text()='#']";
	String selectMonth = "//div[@id='origin-ux-dop-label-month']//div//div//div//div//a//span[text()='#']";
	String selectYear = "//div[@id='origin-ux-dop-label-year']//div//div//div//div/a//span[text()='#']";

	// Strings
	final String loginPageTitle = "Log In";
	final String EAHelpHomePageTitle = "EA Help: Official Support";
	final String accountPageTitle = "Customer Portal";

	@Step("Verifying Manage Your Account Info")
	public void clickManageAccount() {
		try {
			// this.loadPage(EAHELPDataConstants.EAHELP_AUT_URL);
			Thread.sleep(3000);
			this.waitForElementToBeVisible(manageYourAccount, 20);
			this.click(manageYourAccount);
			Thread.sleep(2000);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Verify change/recover password is visible")
	public void isChangeOrRecoverPasswordVisible() {
		try {
			this.waitForElementToBeVisible(changeOrRecoverPassword, 10);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Verify email address is visible")
	public boolean isChangeEmailAddressVisible() {
		boolean isTrue = false;
		try {
			this.waitForElementToBeVisible(changeEmailAddress, 10);
			isTrue = true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("Verify order management is visible")
	public boolean isOrderManagementVisible() {
		boolean isTrue = false;
		try {
			this.waitForElementToBeVisible(orderManagement, 10);
			isTrue = true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("Verify usrname on ordermanagement page")
	public String verifyUserNameOnOrderManagementPage() {
		this.waitForElementToBeVisible(userName, 10);
		return this.getText(userName);
	}

	@Step("Verify redeem code is visible")
	public boolean isredeemCodeVisible() {
		boolean isTrue = false;
		try {
			this.waitForElementToBeVisible(redeemCode, 10);
			isTrue = true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("Verify account security is visible")
	public boolean isAccountSecurityVisible() {
		boolean isTrue = false;
		try {
			this.waitForElementToBeVisible(accountSecurity, 10);
			isTrue = true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("Verify identity management is visible")
	public boolean isIdentityManagementVisible() {
		boolean isTrue = false;
		try {
			this.waitForElementToBeVisible(identityManagement, 10);
			isTrue = true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("Verify mycases is visible")
	public boolean isMyCasesVisible() {
		boolean isTrue = false;
		try {
			this.waitForElementToBeVisible(myCases, 10);
			isTrue = true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("click Order Management")
	public void orderManagement() {
		try {
			this.waitForElementToBeVisible(orderManagement, 10);
			this.click(orderManagement);
			Thread.sleep(3000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("click change/recover password")
	public void recoverPassword() {
		try {
			this.waitForElementToBeVisible(changeOrRecoverPassword, 10);
			this.click(changeOrRecoverPassword);
			Thread.sleep(3000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("click change Email Address")
	public void homePageEmailAddress() {
		try {
			this.waitForElementToBeVisible(changeEmailAddress, 10);
			this.click(changeEmailAddress);
			Thread.sleep(3000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("click change Email Address")
	public boolean VerifyloginPage() {
		boolean isTrue = false;
		try {
			this.switchWindowByTitle(loginPageTitle.toString());
			this.waitForElementToBeVisible(emailText, 10);
			Thread.sleep(1000);
			this.switchWindowByTitle(EAHelpHomePageTitle);
			isTrue = true;
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("click redeem code")
	public void redeemCode() {
		try {
			this.waitForElementToBeVisible(redeemCode, 10);
			this.click(redeemCode);
			Thread.sleep(3000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Get redeem code text")
	public String getRedeemCodeText() {
		String txt = null;
		try {
			this.waitForElementToBeVisible(redeemCode, 10);
			txt = this.getText(redeemCode);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return txt;
	}

	@Step("click account Security")
	public void accountSecurity() {
		try {
			this.waitForElementToBeVisible(accountSecurity, 10);
			this.click(accountSecurity);
			Thread.sleep(3000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("click identity management")
	public void identityManagement() {
		try {
			this.waitForElementToBeVisible(identityManagement, 10);
			this.click(identityManagement);
			Thread.sleep(3000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("click myCases")
	public void myCases() {
		try {
			this.waitForElementToBeVisible(myCases, 10);
			this.click(myCases);
			Thread.sleep(3000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("switch window to EA Help home page")
	public void switchWindowToEAHelpHomePageTitle() {
		try {
			this.switchWindowByTitle(EAHelpHomePageTitle);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("switch window to account page ")
	public void switchWindowbyToAccountPageTitle() {
		try {
			this.switchWindowByTitle(accountPageTitle.toString());
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}
	}

	@Step("Verify welocme text is localized")
	public String verifyWelcomeTextIsLocalized() {
		this.waitForElementToBeVisible(loginText, 10);
		return this.getText(loginText);

	}

	@Step("click security question section button")
	public void editSecuritySectionButton() {
		try {
			this.waitForElementToBeVisible(editSecuritySectionButton, 10);
			this.click(editSecuritySectionButton);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Verifying security question error message Info")
	public void securityQuestionErrorMessage() {
		try {
			this.waitForElementToBeVisible(securityQuestionErrorMessage, 10);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Verifying invalid current password message Info")
	public void invalidCurrentPassword() {
		try {
			this.waitForElementToBeVisible(invalidCurrentPassword, 10);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Verifying new password and confirm new password does not match message Info")
	public void invalidPasswordMatch() {
		try {
			this.waitForElementToBeVisible(newPasswordDoesNotMatchConfirmPswd, 10);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Verifying new password does not meet the password complexity criteria message Info")
	public void invalidPasswordCriteria() {
		try {
			this.waitForElementToBeVisible(newPasswordDoesNotMeetCriteriaMessage, 10);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("changing password to new password")
	public void changePassword(String oldPasswordText, String newPasswordText, String confirmNewPasswordText) {
		try {
			// To check page contains password text fields
			this.waitForElementToBeVisible(confirmNewPassword, 10);
			this.waitForElementToBeVisible(currentPassword, 10);
			this.waitForElementToBeVisible(newPassword, 10);
			// entering information into password text fields
			this.sendKeys(currentPassword, oldPasswordText);
			this.sendKeys(newPassword, newPasswordText);
			this.sendKeys(confirmNewPassword, confirmNewPasswordText);
			// saving the information to check whether the password got changed
			// or not
			this.click(saveNewPasswordButton);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}

	@Step("Privacy Settings")
	public void clickOnprivacySettingsTab() {
		try {
			this.waitForElementToBeVisible(customerPortalPrivacySettingsTabn, 10);
			this.click(customerPortalPrivacySettingsTabn);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("change profile visibility to friends")
	public void verifyprivacySettingsProfileVisibility() {
		try {

			this.waitForElementToBeVisible(customerPortalPrivacySettingsTabProfileVisibility, 10);
			this.click(customerPortalPrivacySettingsTabProfileVisibility);
			Thread.sleep(1000);
			this.click(profileVisibilityFriendsContainer);
			this.waitForElementToBeVisible(profileVisibilityFriendsSelection, 10);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("check show real name checkbox in Privacy Settings")
	public void checkShowRealName() {
		try {

			this.waitForElementToBeVisible(uncheckedshowRealName, 10);
			this.click(uncheckedshowRealName);
			this.waitForElementToBeVisible(checkedShowRealName, 10);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("verify search section is available")
	public void isAllowUserToSearchSectionVisible() {
		try {

			this.waitForElementToBeVisible(allowUserToSearchForMeBySection, 10);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("verify email address section is available")
	public void isemailAddressVisible() {
		try {

			this.waitForElementToBeVisible(emailAddress, 10);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("verify xBoxLive is available")
	public void isXBoxLiveVisible() {
		try {

			this.waitForElementToBeVisible(xBoxLive, 10);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("verify psn is available")
	public void ispsnVisible() {
		try {

			this.waitForElementToBeVisible(psn, 10);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("verify originBlockedUserListSection is available")
	public void isoriginBlockedUserListSectionVisible() {
		try {

			this.waitForElementToBeVisible(originBlockedUserListSection, 10);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("verify block User TextBox Title Msg is available")
	public boolean isBlockUserTextBoxTitleMsgVisible() {
		boolean isTrue = false;

		try {
			this.waitForElementToBeVisible(blockUserTextBoxTitleMsg, 10);
			isTrue = true;
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("edit profile aboutMe Tab")
	public void clickEditProfileAboutMeTab() {
		try {
			this.waitForElementToBeVisible(aboutMe, 10);
			this.click(aboutMe);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}

	@Step("edit profile basic info section")
	public void clickEditProfilebasicInfoSection() {
		try {
			this.waitForElementToBeVisible(basicInfo, 10);
			this.click(basicInfo);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("verify basicInfoOriginId is available")
	public boolean isBasicInfoOriginIdVisible() {
		boolean isTrue = false;
		try {

			this.waitForElementToBeVisible(basicInfoOriginId, 10);
			isTrue = true;
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("verify emailId is available")
	public void isEmailIdVisible() {
		try {

			this.waitForElementToBeVisible(emailId, 10);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("verify firstName is available")
	public boolean isFirstNameVisible() {
		boolean isTrue = false;
		try {

			this.waitForElementToBeVisible(firstName, 10);
			isTrue = true;
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("verify saveBtn is available")
	public boolean isSaveBtnVisible() {
		boolean isTrue = false;
		try {

			this.waitForElementToBeVisible(saveBtn, 10);
			isTrue = true;
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isTrue;
	}

	@Step("security question to change password")
	public void securityQuestion(String answer) {
		try {
			this.waitForElementToBeVisible(editSecuritySectionSecurityAnswer, 10);
			this.sendKeys(editSecuritySectionSecurityAnswer, answer);
			this.click(editSecuritySectionSecurityAnswerContinueButton);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("security question in about me section")
	public void securityQuestionInAboutMeSection(String answer) {
		try {
			this.waitForElementToBeVisible(editAboutMeSecurityAnswer, 10);
			this.sendKeys(editAboutMeSecurityAnswer, answer);
			this.click(editAboutMeSecurityAnswerContinueButton);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Navigate to basic info")
	public void navigatebasicInfo() {

		try {
			this.click(selectAccounttab);
			this.click(changeEmail);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Step("Edit basic info Information")
	public void editbasicInfoInformation(String firstname, String lastname, String Date, String Month, String Year) {

		try {
			this.isElementVisible(basicInfo, 20);
			this.click(basicInfo);
			this.isElementVisible(seqQuestion, 20);
			this.sendKeys(seqAnswer, "mindtree");
			this.click(saveSeqQuestion);
			this.sendKeys(firstName, firstname);
			this.sendKeys(lastName, lastname);
			this.click(dobDay);
			this.clickOnDynamicElement(selectDate, Date);
			this.click(dobMonth);
			this.clickOnDynamicElement(selectMonth, Month);
			this.click(dobYear);
			this.clickOnDynamicElement(selectYear, Year);
			this.waitForElementToBeVisible(saveBasicinfo, 10);
			this.click(saveBasicinfo);
			this.waitForElementToBeVisible(basicInfo, 10);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

}
