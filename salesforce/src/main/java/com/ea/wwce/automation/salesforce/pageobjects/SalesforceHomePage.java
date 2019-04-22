package com.ea.wwce.automation.salesforce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.salesforce.config.SalesforceDataConstants;

import io.qameta.allure.Step;

public class SalesforceHomePage extends SalesforceBasePageObject {

	private static final Logger logger = Logger.getLogger(SalesforceLoginPage.class);

	public SalesforceHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userNavLabel")
	WebElement username;
	@FindBy(css = "#phSearchInput")
	WebElement caseSearchTextBox;
	@FindBy(css = "#phSearchButton")
	WebElement caseSearchButton;
	@FindBy(css = "a[id*='RelatedAttachmentList_link'] .count")
	WebElement attachmentRef;
	// By locator
	// By attachmentRef =By.cssSelector("a[id*='RelatedAttachmentList_link']
	// .count");

	// Lighting User interface
	@FindBy(css = ".slds-icon-waffle")
	WebElement appLauncherIcon;
	@FindBy(css = "label[data-aura-class=\"uiLabel\"] +.slds-input.input")
	WebElement searchTextBox;
	@FindBy(css = "a[title=\"Advisor Profiles\"]")
	WebElement advisorProfiles;

	// Dynamic ids
	String caseNumberLink = "//a[text()='#']";
	String itemFromAppLauncher = "//div[@data-aura-class='oneAppLauncherItemList']//a[@title='#']";

	/* Page Actions */

	@Step("Select advisor profile options")
	public void selectItemFromAppLauncher(String item) {
		this.waitForClickableElement(30, appLauncherIcon);
		this.click(appLauncherIcon);
		this.waitForDynamicElementToBeVisible(itemFromAppLauncher, item, 30);
		this.clickOnDynamicElement(itemFromAppLauncher, item);
	}

	@Step("Get SF user name")
	public String getUserName() {
		String userName = null;
		logger.info("Get SF logged in username");
		try {
			this.waitForElementToBeVisible(username, SalesforceDataConstants.IMPLICIT_TIMEOUT);
			userName = this.getText(username);
		} catch (NoSuchElementException e) {
			logger.warn("Unable to find SF username " + e);
		}
		return userName;
	}

	@Step("Search for case number")
	public void serachCase(String caseNumber) throws InterruptedException {
		logger.info("Search for case number");
		try {
			this.verifyPageIsLoaded();
			this.waitForElementToBeVisible(username, SalesforceDataConstants.IMPLICIT_TIMEOUT);
			this.sendKeys(caseSearchTextBox, caseNumber);
			Thread.sleep(2000);
			this.click(caseSearchButton);
		} catch (NoSuchElementException e) {
			logger.warn("Unable to find case number " + e);
		}
	}

	@Step("click on case number")
	public void clickOnCaseNumber(String caseNumber) {
		logger.info("Click on case number");
		try {
			this.verifyPageIsLoaded();
			this.waitForElementToBeVisible(username, SalesforceDataConstants.IMPLICIT_TIMEOUT);
			this.clickOnDynamicElement(caseNumberLink, caseNumber);
		} catch (NoSuchElementException e) {
			logger.warn("Unable to Click on case number " + e);
		}
	}

	@Step("Verify case attachement is present")
	public String getAttachmentsCount() {
		String isTrue = null;
		logger.info("Click on case number");
		try {
			this.verifyPageIsLoaded();
			this.waitForElementToBeVisible(username, SalesforceDataConstants.IMPLICIT_TIMEOUT);
			isTrue = this.getText(attachmentRef).replace("[", "").replace("]", "").trim();
		} catch (NoSuchElementException e) {
			logger.warn("Unable to Click on case number " + e);
		}
		return isTrue;
	}

}
