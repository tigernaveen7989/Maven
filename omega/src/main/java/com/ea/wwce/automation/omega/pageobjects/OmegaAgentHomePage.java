package com.ea.wwce.automation.omega.pageobjects;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.omega.config.OmegaDataConstants;

import io.qameta.allure.Step;

public class OmegaAgentHomePage extends OmegaBasePageObject {

	public OmegaAgentHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(OmegaAgentHomePage.class);
	
	@FindBy(css = "div.loader.hide +div#case-search input#case-search")
	WebElement txtCaseSearch;
	@FindBy(css = "div.loader.hide +div#case-search button.btn.primary")
	WebElement btnCaseSearch;
	@FindBy(xpath = "//*[@id=\"avatar\"]/a/span")
	WebElement settingsIcon;
	@FindBy(xpath = "//*[@id=\"avatar\"]/a/ul/li[5]")
	WebElement logOutButton;
	@FindBy(xpath = "//em[@name='firstName']")
	WebElement nameTab;
	@FindBy(xpath = "//a[contains(@class,'actions customer-info')]/span[@class='title']")
	WebElement editSettings;
	@FindBy(xpath = "//li[contains(@class,'reset-password')]")
	WebElement resetPasswordLink;
	@FindBy(xpath = "//li[contains(@class,'send-verification')]")
	WebElement sendVerificationEmailLink;
	@FindBy(xpath = "//*[@id=\"customer-info\"]/div[7]/div/div[1]")
	WebElement passwordResetConfirmation;
	@FindBy(xpath = "//*[@id=\"customer-info\"]/div[7]/div/div[1]")
	WebElement accountVerification;
	@FindBy(xpath = "//*[@id=\"customer-info\"]/div[7]/div/div[3]/ul/li/a/span")
	WebElement okbtnPasswordResetConfirmation;
	@FindBy(xpath = "//*[@id=\"customer-info\"]/div[7]/div/div[3]/ul/li/a/span")
	WebElement okbtnAccountVerification;

	String agentName = "//*[@id='agent-name']/span[text()='#']";

	@Step("Find agent name")
	public boolean verifyAgentName(String txtReplace) {
		boolean isTrue = false;

		logger.info("Find agent name");
		try {
			this.waitForDynamicElementToBeVisible(agentName, txtReplace, OmegaDataConstants.IMPLICIT_TIMEOUT);
			isTrue = true;
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find agent name" + e);
		} catch (TimeoutException e) {
			logger.warn("Failed to find agent name " + e);
		}
		return isTrue;
	}

	@Step("Search case in omega")
	public void searchCase(String caseNumber) {
		logger.info("Search case number");
		try {
			this.waitForClickableElement(10, txtCaseSearch);
			this.sendKeys(txtCaseSearch, caseNumber);
			this.click(btnCaseSearch);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to search case number" + e);
		} catch (TimeoutException e) {
			logger.warn("Failed to search case number" + e);
		}

	}

	@Step("Logout from omega")
	public void logOut() {
		logger.info("Logout from omega");
		try {
			this.windowScrollRight(settingsIcon);
			this.waitForClickableElement(10, logOutButton);
			this.click(logOutButton);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to Log Out" + e);
		} catch (TimeoutException e) {
			logger.warn("Failed to Log Out" + e);
		}
	}

	@Step("Reset Password from omega")
	public void resetPassword() throws InterruptedException {
		logger.info("Reset Password from omega");
		try {
			Thread.sleep(10000);
			this.waitForClickableElement(10, nameTab);
			this.doubleClick(nameTab);
			Thread.sleep(3000);
			this.waitForClickableElement(10, editSettings);
			this.click(editSettings);
			Thread.sleep(3000);
			this.waitForClickableElement(10, resetPasswordLink);
			this.click(resetPasswordLink);
			Thread.sleep(3000);
			this.waitForClickableElement(10, passwordResetConfirmation);
			String text = this.getText(passwordResetConfirmation);
			assertEquals(text, "Password Reset Confirmation");
			this.click(okbtnPasswordResetConfirmation);

		} catch (NoSuchElementException e) {
			logger.warn("Failed to Reset Password" + e);
		} catch (TimeoutException e) {
			logger.warn("Failed to Reset Password" + e);
		}
	}

	@Step("Send Verification Email from omega")
	public void sendVerificationEmail() {
		logger.info("Send Verification Email from omega");
		try {
			this.waitForClickableElement(10, nameTab);
			this.doubleClick(nameTab);
			this.waitForClickableElement(10, editSettings);
			this.click(editSettings);
			this.waitForClickableElement(10, sendVerificationEmailLink);
			this.click(sendVerificationEmailLink);
			this.waitForClickableElement(10, accountVerification);
			String text = this.getText(accountVerification);
			assertEquals(text, "Account Verification");
			this.click(okbtnAccountVerification);

		} catch (NoSuchElementException e) {
			logger.warn("Failed to Send Verification" + e);
		} catch (TimeoutException e) {
			logger.warn("Failed to Send Verification" + e);
		}
	}
}
