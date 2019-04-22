package com.ea.wwce.automation.omega.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.omega.config.OmegaDataConstants;

import io.qameta.allure.Step;

public class OmegaLoginPage extends OmegaBasePageObject {

	public OmegaLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(OmegaLoginPage.class);

	@FindBy(id = "email")
	WebElement txtUsername;
	@FindBy(id = "password")
	WebElement txtPassword;
	@FindBy(xpath = "//button[contains(@class,'primary')]")
	WebElement btnlogin;

	By spinner = By.className("circle-spinner");
	By innerSpinner = By.className("circle-inner");
	By caseSearchShadow = By.cssSelector(".case-search-wrapper.shadow");

	// By test=By.name(name)

	@Step("Load Omega Home Page")
	public void loadOmega(String URL) {
		logger.info("Load Omega Home Page");
		try {
			this.loadPage(URL);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void loginToOmega(String username, String password) throws InterruptedException {
		try {

			logger.info("Validating omega login functionality");
			Thread.sleep(5000);
			// isSpinnerInvisible(20);
			if (!this.isElementVisible(btnlogin, 6)) {
				this.refreshPage();
				isSpinnerInvisible(10);
				if (!this.isElementVisible(txtUsername, 6)) {
					this.refreshPage();
					// isSpinnerInvisible(10);
				}
			}
			this.sendKeys(txtUsername, username);
			this.sendKeys(txtPassword, password);
			// isSpinnerInvisible(8);
			this.click(btnlogin);
			// isSpinnerInvisible(30);
			Thread.sleep(15000);

		} catch (NoSuchElementException e) {
			logger.warn("Failed to login" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed to login due to time out" + e.getMessage());
		}

	}

	// Verify is loader not visible
	public void isSpinnerInvisible(int waitTime) {
		try {
			this.isElementInvisibile(spinner, waitTime);
		} catch (TimeoutException e) {
			logger.warn("Element is visible still and increase wait time" + e.getMessage());
		}
	}

	// Verify is loader not visible
	public void isInnerSpinnerInvisible(int waitTime) {
		try {
			this.isElementInvisibile(innerSpinner, waitTime);
		} catch (TimeoutException e) {
			logger.warn("Element is visible still and increase wait time" + e.getMessage());
		}
	}

	public void isCasesTabLoaded(int waitTime) {
		try {
			this.isElementPresent(caseSearchShadow, waitTime);
		} catch (TimeoutException e) {
			logger.warn("Element is not present still and increase wait time" + e.getMessage());
		}
	}
}
