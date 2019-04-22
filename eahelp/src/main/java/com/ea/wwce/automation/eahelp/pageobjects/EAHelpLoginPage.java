package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;

import io.qameta.allure.Step;

/**
 * 
 * @author rdronamraju
 * @description EA HELP Login page object
 */

public class EAHelpLoginPage extends EAHelpBasePageObject {

	public EAHelpLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpHomePage.class);

	@FindBy(xpath = "(//a[@class='login'])[1]")
	WebElement lnkLogin;
	@FindBy(xpath = "//*[@id=\"utility-bar-header\"]//a[@class='logout']")
	WebElement lnkLogout;
	@FindBy(xpath = "//*[@id=\"gus\"]//span")
	WebElement logoutDropdown;
	@FindBy(xpath = "//div[@id='oauth-modal']/div[@class='box']/iframe")
	WebElement iFrameLogin;
	@FindBy(id = "email")
	WebElement txtLoginEmail;
	@FindBy(id = "password")
	WebElement txtPassword;
	@FindBy(id = "btnLogin")
	WebElement btnLogin;
	@FindBy(xpath = "//*[@id=\"panel-login\"]//a[@class='forgot-password-link-new']")
	WebElement forgotpasswordLink;
	@FindBy(id = "link_no_error")
	WebElement resetPassword;
	@FindBy(css = "html[lang=en]")
	WebElement loginWinLang;
	@FindBy(css = "#gus .dropdown-toggle span")
	WebElement userName;
	@FindBy(css = ".login-container.main-container.field-error #loginForm .general-error div div")
	WebElement loginError;
	@FindBy(xpath = "//iframe[@role='presentation']")
	WebElement capthaIframe;
	@FindBy(css = "#recaptcha-anchor-label")
	WebElement captchaImage;

	final String resetPasswordPage = "Reset Your Password";

	// =========================================================

	@Step("Get login text from login link")
	public String getLoginText() {
		this.waitForElementToBeVisible(lnkLogin, 3);
		return this.getText(lnkLogin);
	}

	@Step("Verify captcha image is shown")
	public boolean isCaptchaImageShown() {
		boolean isTrue = false;
		if (this.waitForElementToBeVisible(capthaIframe, 3)) {
			this.waitAndSwitchToIframe(10, capthaIframe);
			isTrue = this.waitForElementToBeVisible(captchaImage, 3);
		}
		return isTrue;
	}

	@Step("Click on login link")
	public void clickOnLoginLink() {
		logger.info("Verify login error is shown");
		try {
			this.verifyPageIsLoaded();
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, lnkLogin);
			this.click(lnkLogin);
			this.waitAndSwitchToIframe(EAHELPDataConstants.IMPLICIT_TIMEOUT, iFrameLogin);

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Step("enter username and password")
	public void enterUserNameAndPassword(String username, String password) {
		try {
			this.sendKeys(txtLoginEmail, username);
			this.sendKeys(txtPassword, password);
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, btnLogin);
			this.click(btnLogin);

			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Step("Verify login error")
	public String verifyLoginError(String username, String password) throws InterruptedException {
		logger.info("Verify login error is shown");
		this.verifyPageIsLoaded();
		this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, lnkLogin);
		this.click(lnkLogin);
		this.waitAndSwitchToIframe(EAHELPDataConstants.IMPLICIT_TIMEOUT, iFrameLogin);
		Thread.sleep(3000);
		this.sendKeys(txtLoginEmail, username);
		this.sendKeys(txtPassword, password);
		this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, btnLogin);
		this.click(btnLogin);
		Thread.sleep(2000);
		this.waitForElementToBeVisible(loginError, EAHELPDataConstants.IMPLICIT_TIMEOUT);
		return this.getText(loginError);
	}

	// Return login window language property value

	@Step("Return login window language property value")
	public String loginWindowLanguage() {
		String lang = null;
		try {
			logger.info("Validating the login function");
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, lnkLogin);
			this.click(lnkLogin);
			this.waitAndSwitchToIframe(EAHELPDataConstants.IMPLICIT_TIMEOUT, iFrameLogin);
			Thread.sleep(3000);
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, btnLogin);
			lang = this.getAttributeValue(loginWinLang, "lang");
		} catch (NoSuchElementException e) {
			logger.warn("Failed to login" + e.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to login due to time out" + e.getMessage());
		}
		return lang;
	}

	@Step("Load EAHELP Home Page")
	public void loadEAHelp(String URL) {
		logger.info("Load EAHELP Home Page");
		try {
			this.loadPage(URL);
			this.verifyPageIsLoaded();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Step("Login to EA Help")
	public void loginToEAHelp(String username, String password) {
		try {
			logger.info("Validating the login function");
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, lnkLogin);
			this.click(lnkLogin);
			this.waitAndSwitchToIframe(EAHELPDataConstants.IMPLICIT_TIMEOUT, iFrameLogin);
			Thread.sleep(3000);
			this.sendKeys(txtLoginEmail, username);
			this.sendKeys(txtPassword, password);
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, btnLogin);
			this.click(btnLogin);
			Thread.sleep(2000);
			this.verifyPageIsLoaded();
			this.switchWindowByTitle("EA Help: Official Support");
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to login" + e.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to login due to time out" + e.getMessage());
		}
	}

	@Step("Logout from EA Help")
	public void logoutFromEAHelp() throws InterruptedException {
		try {
			logger.info("validating the logout function");
			this.loadPage(EAHELPDataConstants.EAHELP_AUT_URL);
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, logoutDropdown);
			this.click(logoutDropdown);
			// this.loadPage(EAHELPDataConstants.EAHELP_AUT_URL);
			// this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT,lnkLogout);
			this.click(lnkLogout);
			// this.loadPage(EAHELPDataConstants.EAHELP_AUT_URL);
			Thread.sleep(7000);
			this.loadPage(EAHELPDataConstants.EAHELP_AUT_URL);
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, lnkLogin);
		} catch (Exception e) {
			logger.warn("Failed to login due to time out" + e.getMessage());
		}

	}

	@Step("verify the logout option")
	public boolean isLogoutDropdownPresent() throws InterruptedException {
		logger.info("verify the logout option");
		this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, logoutDropdown);
		this.click(logoutDropdown);
		return this.waitForElementToBeVisible(lnkLogout, EAHELPDataConstants.IMPLICIT_TIMEOUT);
	}

	@Step("Get logout text from logout button")
	public String getLogoutText() throws InterruptedException {
		logger.info("Get logout text from logout button");
		this.verifyPageIsLoaded();
		this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, logoutDropdown);
		this.click(logoutDropdown);
		return this.getText(lnkLogout);
	}

	@Step("reset password")
	public boolean resetPassword() {
		boolean isTrue = false;
		try {
			logger.info("Validating the login function");
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, lnkLogin);
			this.click(lnkLogin);
			this.waitAndSwitchToIframe(EAHELPDataConstants.IMPLICIT_TIMEOUT, iFrameLogin);
			Thread.sleep(3000);
			this.waitForElementToBeVisible(forgotpasswordLink, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			this.click(forgotpasswordLink);
			this.switchWindowByTitle(resetPasswordPage.toString());
			this.waitForElementToBeVisible(resetPassword, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			Thread.sleep(2000);
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

	@Step("Verify the login is present")
	public boolean isloginLinkPresent() {
		logger.info("Verify the login is present");
		return this.waitForElementToBeVisible(lnkLogin, EAHELPDataConstants.IMPLICIT_TIMEOUT);
	}

	@Step("Verify the username is present")
	public String verifyUserName() throws InterruptedException {
		logger.info("Verify the username is present");
		this.verifyPageIsLoaded();
		Thread.sleep(3000);
		this.waitForElementToBeVisible(userName, 15);
		return this.getText(userName);
	}

	@Step("Verify the username/pesona name is present")
	public boolean isPersonaNamePresent() throws InterruptedException {
		logger.info("Verify the username is present");
		return this.waitForElementToBeVisible(userName, 15);

	}

}
