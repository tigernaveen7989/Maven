package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * 
 * @author sadabala
 *
 */

public class EAHelpUnAuthCaseInformationPage extends EAHelpBasePageObject {

	public EAHelpUnAuthCaseInformationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpUnAuthCaseInformationPage.class);

	@FindBy(css = "#firstName")
	WebElement txtFName;
	@FindBy(css = "#lastName")
	WebElement txtLName;
	@FindBy(css = "#email")
	WebElement txtEmail;
	@FindBy(css = "a[class^='btn-next'][class$=\"active\"]")
	WebElement nextButton;
	@FindBy(css = "span.still-need-help")
	WebElement stillNeedHelpLink;
	@FindBy(xpath = "//span[@class='still-need-help']/parent::p//parent::h6//parent::div/parent::ea-text/parent::div/parent::ea-section-column/ancestor::ea-section/following-sibling::*//iframe")
	WebElement isNeedHelpTopLink;
	@FindBy(xpath = "(//*[@id='email'][position()=1])")
	WebElement txtUserName;
	@FindBy(xpath = "//h4[contains(text(),'Support Contact Information')]")
	WebElement deflectionMessage;
	@FindBy(xpath = "//a[contains(@href,'subscriber')]")
	WebElement clickableLink;
	@FindBy(xpath = "//a[contains(@href,'not')]")
	WebElement clickableLink1;
	@FindBy(xpath = "//a[contains(@href,'bug')]")
	WebElement clickableLink2;

	// -----------------------------------------

	// Verify ICR page elements for star war product
	public boolean verifyICRPageForStarWarProduct() {
		boolean isTrue = false;
		logger.info("Verify ICR page elements for star war product");
		try {
			this.isElementVisible(deflectionMessage, 10);
			this.isElementVisible(clickableLink, 10);
			this.isElementVisible(clickableLink1, 10);
			this.isElementVisible(clickableLink2, 10);
			isTrue = true;
		} catch (Exception e) {
			isTrue = false;
			logger.warn("Fail to verify ICR page elements for Star War product");
		}
		return isTrue;
	}

	// Verify is Need help link is on top of login window
	public boolean isNeedHelpLinkTopOfLoginWindow() {
		boolean isTrue = false;

		try {
			logger.info("Verify is Need help link is on top of login window");
			this.verifyPageIsLoaded();
			this.isElementVisible(stillNeedHelpLink, 30);
			this.waitAndSwitchToIframe(10, isNeedHelpTopLink);
			Thread.sleep(3000);
			this.waitForClickableElement(10, txtUserName);
			this.switchWindowByTitle("Contact EA");
			isTrue = true;
		} catch (Exception e) {
			isTrue = false;
			logger.info("Fail to verify  Need help link position");
		}
		return isTrue;
	}

	// click on need help link
	public void clickOnNeedHelpLink() {
		try {
			logger.info("Click on Still need help link on unauth user page");
			this.click(stillNeedHelpLink);
		} catch (WebDriverException e) {
			logger.info("Failing to click on need help link on unauth user page");
		}
	}

	// Verify First name is not displayed
	public boolean verifyFNameIsNotDisplayed() {
		logger.info("Verify First name is not displayed");
		return this.isElementVisible(txtFName, 10);
	}

	// Verify last name is not displayed
	public boolean verifyLNameIsNotDisplayed() {
		logger.info("Verify last name is not displayed");
		return this.isElementVisible(txtLName, 10);
	}

	public void submitUnAuthLoginForm(String fName, String lName, String email) {
		logger.info("Submit unauth customer information page");
		try {
			this.verifyPageIsLoaded();
			if (this.isElementVisible(stillNeedHelpLink, 1)) {
				this.click(stillNeedHelpLink);
			}
			this.isElementVisible(txtFName, 10);
			this.sendKeys(txtFName, fName);
			this.sendKeys(txtLName, lName);
			this.sendKeys(txtEmail, email);
			this.isElementVisible(nextButton, 10);
			this.click(nextButton);
			this.verifyPageIsLoaded();
		} catch (Exception e) {
			logger.info("Submit unauth customer information page" + e.getMessage());
			throw e;
		}
	}

	public void submitUnAuthLoginForm(String email) throws InterruptedException {
		logger.info("Submit unauth customer information page");
		this.verifyPageIsLoaded();
		if (this.isElementVisible(stillNeedHelpLink, 1)) {
			this.click(stillNeedHelpLink);
		}
		this.isElementVisible(txtEmail,30);
		this.sendKeys(txtEmail, email);
		this.isElementVisible(nextButton, 10);
		this.click(nextButton);
		this.verifyPageIsLoaded();
	}

	public boolean verifyNextButtonIsEnabled(String fName, String lName, String email) {
		
		Boolean isTrue=false;
		logger.info("Verify Next button is enabled");
		try {
			this.verifyPageIsLoaded();
			if (this.isElementVisible(stillNeedHelpLink, 6)) {
				this.click(stillNeedHelpLink);
			}
			this.isElementVisible(txtFName, 60);
			this.sendKeys(txtFName, fName);
			this.sendKeys(txtLName, lName);
			this.sendKeys(txtEmail, email);
			isTrue=	this.isElementEnabled(nextButton, 6);			
		} catch (Exception e) {
			logger.info("Verify Next button is enabled" + e.getMessage());
			throw e;
		}
		return isTrue;
	}
}
