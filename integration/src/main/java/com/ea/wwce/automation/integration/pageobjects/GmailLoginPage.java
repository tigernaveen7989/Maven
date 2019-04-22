package com.ea.wwce.automation.integration.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * @author Naveen Kumar
 * 
 * Page Object for GmailHomePage
 **/
public class GmailLoginPage extends IntegrationPageObject{

	public GmailLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	private static final Logger logger = Logger.getLogger(GmailLoginPage.class);
	
	@FindBy(xpath = "//input[@type='email']")
	WebElement emailOrPhoneEditBox;
	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordEditBox;
	@FindBy(xpath = "//div[@id='identifierNext']")
	WebElement nextButton;
	@FindBy(xpath = "//div[@id='passwordNext']")
	WebElement passwordNextButton;
	
	@Step("Load Gmail Login Page")
	public void loadGmail(String URL) {
		logger.info("Load Gmail Login Page");
		try {
			this.loadPage(URL);
			this.verifyPageIsLoaded();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Step("Login into Gmail Account")
	public void loginToGmail(String gmailEmail, String gmailPassword) {
		logger.info("Login to Gmail");
		enterEmail(gmailEmail);
		clickOnNextButton();
		enterPassword(gmailPassword);
		clickOnPasswordNextButton();
	}
	
	//@Step("Enter Email in Email Edit Box")
	public void enterEmail(String gmailEmail) {
		logger.info("Enter value in email edit box");
		try {
			this.waitForClickableElement(60, emailOrPhoneEditBox);
			this.sendKeys(emailOrPhoneEditBox, gmailEmail);
		} catch (Exception e) {
			logger.info("Not able to enter value in email edit box");
		}
	}
	
	//@Step("Enter Password in Password Edit Box")
	public void enterPassword(String gmailPassword) {
		logger.info("Enter value in password edit box");
		try {
			this.waitForClickableElement(60, passwordEditBox);
			this.sendKeys(passwordEditBox, gmailPassword);
		} catch (Exception e) {
			logger.info("Not able to enter value in password edit box");
		}
	}
	
	//@Step("Click on Next Button")
	public void clickOnNextButton() {
		logger.info("Click on next button");
		try {
			this.waitForClickableElement(60, nextButton);
			this.click(nextButton);
		} catch (Exception e) {
			logger.info("Not able to click on next button");
		}
	}
	
	//@Step("Click on Password Page Next Button")
	public void clickOnPasswordNextButton() {
		logger.info("Click on password page next button");
		try {
			this.waitForClickableElement(60, passwordNextButton);
			this.click(passwordNextButton);
		} catch (Exception e) {
			logger.info("Not able to click on password page next button"); 
		}
	}
}
