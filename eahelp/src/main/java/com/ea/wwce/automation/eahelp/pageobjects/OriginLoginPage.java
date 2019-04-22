package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;

/**
 * 
 * @author sadabala
 *
 */

public class OriginLoginPage extends EAHelpBasePageObject {

	public OriginLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(BasePageObject.class);

	@FindBy(css = "origin-cta-primary a div")
	WebElement loginlink;
	@FindBy(css = "#login-form #email")
	WebElement txtUserName;
	@FindBy(css = "#login-form #password")
	WebElement txtPassword;
	@FindBy(css = ".otkcheckbox.checkbox-login-first+a")
	WebElement btnSubmit;
	@FindBy(css = ".origin-navigation-bottom-isloggedin")
	WebElement logoutAvatar;
	@FindBy(css = "li[disposition='logout']")
	WebElement logout;

	public void loginToOrigin(String usrName, String password) {
		logger.info("Login to Origin page");
		String currentWindow = this.getCurrentWindowHandle();
		this.click(loginlink);
		this.switchWindowByTitle("SIGN IN");
		this.sendKeys(txtUserName, usrName);
		this.sendKeys(txtPassword, password);
		this.click(btnSubmit);
		this.switchToWindowHandler(currentWindow);
		this.switchToDefaultContent();
		this.verifyPageIsLoaded();
	}

	public void logOutFromOrigin() throws Exception {
		logger.info("Verify logout is successfull");
		try {
			this.waitForElementToBeVisible(logoutAvatar, 30);
			this.moveToElement(logoutAvatar);
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
		return this.waitForElementToBeVisible(loginlink, 30);
	}

}
