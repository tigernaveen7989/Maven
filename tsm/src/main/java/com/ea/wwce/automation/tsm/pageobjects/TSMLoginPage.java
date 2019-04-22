package com.ea.wwce.automation.tsm.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ea.wwce.automation.tsm.config.TSMDataConstants;
import io.qameta.allure.Step;

/**
 * 
 * @author rgandham
 * @description This class consists of Page objects and Functions of Login page
 *              in TSM application.
 * 
 */

public class TSMLoginPage extends TSMBasePageObject {

	public TSMLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMLoginPage.class);

	@FindBy(id = "username")
	WebElement txtuserName;
	@FindBy(id = "password")
	WebElement txtPassword;
	@FindBy(id = "Login")
	WebElement btnLogin;

	@Step("Login to TSM")
	public void loginToTSM(String URL, String username, String password) {
		try {
			logger.info("Verifying login");
			// this.loadPage(TSMDataConstants.TSM_BASE_URL_QA3PC);
			//Launch TSM URL
			this.loadPage(URL);
			this.waitForElementToBeVisible(txtuserName, 40);
			this.sendKeys(txtuserName, username);
			this.sendKeys(txtPassword, password);
			this.waitForClickableElement(10, btnLogin);
			this.click(btnLogin);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to login" + e.getMessage());
		}
	}

	@Step("Load TSM Home Page")
	public void loadTSM(String URL) {
		logger.info("Load EAHELP Home Page");
		try {
			this.loadPage(URL);
			this.verifyPageIsLoaded();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
