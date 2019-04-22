package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This page will be moved to AHQ module during integration tests implementation
 * 
 * @author sadabala
 *
 */

public class AHQLoginPage extends EAHelpBasePageObject {

	public AHQLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpAccountManagementPage.class);

	@FindBy(css = ".sign-in-link-wrap .sign-in-link:first-child")
	WebElement loginLink;
	@FindBy(css = "#email-login-panel #email")
	WebElement txtUserName;
	@FindBy(css = "#password")
	WebElement txtPassword;
	@FindBy(css = "#btnLogin")
	WebElement btnSubmit;
	@FindBy(xpath = "//div[@id='oauth-modal']/div[@class='box']/iframe")
	WebElement iFrameLogin;
	@FindBy(css = "div[class*='user-profile-desktop'] .ui-username.ddm-selected-item")
	WebElement userName;
	@FindBy(css = "div[class*='desktop'] .ui-username.ddm-selected-item")
	WebElement logOutToggle;
	@FindBy(css = "div[class*='desktop']  div[class*='logout-link']")
	WebElement logout;

	// Login to AHQ
	public void LoginToAHQ(String userName, String password) throws InterruptedException {
		logger.info("Login to AHQ website");
		try {
			String currentwindow = this.getCurrentWindowHandle();
			this.waitForClickableElement(10, loginLink);
			this.click(loginLink);
			this.switchWindowByTitle("Log In");
			this.sendKeys(txtUserName, userName);
			this.sendKeys(txtPassword, password);
			this.click(btnSubmit);
			Thread.sleep(5000);
			this.switchToWindowHandler(currentwindow);
			this.switchToDefaultContent();
		} catch (Exception e) {
			logger.warn("Fail to Login to AHQ website");
		}
	}

	public String getUserName() {
		logger.info("Get AHQ user name");
		try {
			String username = this.getText(userName);
			;
			if (username.contains(" ")) {
				username = username.split(" ")[1].trim().toLowerCase();
			}
			return username;
		} catch (Exception e) {
			throw e;
		}

	}

	public void logOutFromAHQ() throws Exception {
		logger.info("Verify logout is successfull");
		try {
			this.waitForElementToBeVisible(logOutToggle, 30);
			this.click(logOutToggle);
			this.waitForElementToBeVisible(logout, 10);
			this.click(logout);
			Thread.sleep(2000);
			this.verifyPageIsLoaded();

		} catch (Exception e) {
			throw e;
		}
	}

	public boolean verifyLoginLinkIsShown() {
		logger.info("Verify login link is shown");
		return this.waitForElementToBeVisible(loginLink, 30);

	}

}
