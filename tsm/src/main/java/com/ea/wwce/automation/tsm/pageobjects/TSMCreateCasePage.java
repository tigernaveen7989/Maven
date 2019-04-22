package com.ea.wwce.automation.tsm.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * 
 * @author rgandham
 * @description This class consists of Page objects and Functions of Create Case
 *              in TSM application.
 * 
 */

public class TSMCreateCasePage extends TSMBasePageObject {

	public TSMCreateCasePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMCreateCasePage.class);

	@FindBy(xpath = "//label[text()='Product']/parent::lightning-input//input")
	WebElement product;
	@FindBy(xpath = "//label[text()='Platform']/parent::lightning-input//input")
	WebElement platform;
	@FindBy(xpath = "//label[text()='Category']/parent::lightning-input//input")
	WebElement category;
	@FindBy(xpath = "//label[text()='Issue']/parent::lightning-input//input")
	WebElement subcategory;
	@FindBy(css = "input[placeholder='Search Salesforce']")
	WebElement searchPlayer;
	@FindBy(xpath = "//div[@class='slds-form-element__control slds-grow']//input")
	WebElement subject;
	@FindBy(xpath = "//div[@class='slds-col']//button[contains(@class,'slds-button_brand')] ")
	WebElement createCase;
	@FindBy(xpath = "//lightning-icon[@title='Case']/following-sibling::span")
	WebElement caseNumber;
	@FindBy(xpath = "//label[text()='Platform']/parent::lightning-input//input[@disabled]")
	WebElement platformDisabled;
	@FindBy(xpath = "//label[text()='Category']/parent::lightning-input//input[@disabled]")
	WebElement categoryDisabled;
	@FindBy(xpath = "//label[text()='Issue']/parent::lightning-input//input[@disabled]")
	WebElement subcategoryDisabled;

	String selectProduct = "//div[text()='#']";
	String selectPlatform = "//div[text()='#']";
	String selectCategory = "//div[text()='#']";
	String selectSubCategory = "//div[text()='#']";
	String clkOnPlayerAcc = "//a[@role='option']//div[@class='slds-icon_container']//following-sibling::div//span[@title='#']";
	String closePlayerAcc = "//span[@class='slds-assistive-text' and contains(text(),'Close #')]";

	// Search for player account
	@Step("Search Player Account in TSM")
	public void searchPlayerAcc(String PlayerAcc) {
		logger.info("Search with player account");
		try {
			this.click(searchPlayer);
			this.sendKeys(searchPlayer, PlayerAcc);
			// this.click(searchPlayer);
			// Thread.sleep(5000);
			this.waitForDynamicElementToBeVisible(clkOnPlayerAcc, PlayerAcc, 5);
			Thread.sleep(2000);
			this.clickOnDynamicElement(clkOnPlayerAcc, PlayerAcc);
			// SearchCase.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Search Case Number" + e);
		}
	}

	// Select Product
	@Step("Select product")
	public void selectProduct(String productVal) {
		logger.info("Select Product");
		try {
			this.click(product);
			this.sendKeys(product, productVal);
			this.waitForDynamicElementToBeVisible(selectProduct, productVal, 5);
			this.clickOnDynamicElement(selectProduct, productVal);
			Thread.sleep(1000);

		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click On attachment" + e);
		}
	}

	// Select Platform
	@Step("Select Platform")
	public void selectPlatform(String platformVal) {

		logger.info("Select Platform");
		try {
			this.waitForElementToBeInVisible(platformDisabled, 1);
			this.click(platform);
			this.sendKeys(platform, platformVal);
			this.waitForDynamicElementToBeVisible(selectPlatform, platformVal, 5);
			this.clickOnDynamicElement(selectPlatform, platformVal);
			Thread.sleep(1000);

		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to select Platform" + e);
		}
	}

	// Select Category
	@Step("Select Category")
	public void selectCategory(String categoryVal) {

		logger.info("Select Category");
		try {
			this.waitForElementToBeInVisible(categoryDisabled, 1);
			this.click(category);
			this.sendKeys(category, categoryVal);
			this.waitForDynamicElementToBeVisible(selectCategory, categoryVal, 5);
			this.clickOnDynamicElement(selectCategory, categoryVal);
			Thread.sleep(1000);

		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to select Category" + e);
		}
	}

	// Select SubCategory
	@Step("Select SubCategory")
	public void selectSubCategory(String subcategoryVal) {

		logger.info("Select SubCategory");
		try {
			this.waitForElementToBeInVisible(subcategoryDisabled, 1);
			this.click(subcategory);
			this.sendKeys(subcategory, subcategoryVal);
			this.waitForDynamicElementToBeVisible(selectSubCategory, subcategoryVal, 5);
			this.clickOnDynamicElement(selectSubCategory, subcategoryVal);
			Thread.sleep(1000);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find agent name" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to select SubCategory" + e);
		}
	}

	// Enter Subject
	@Step("Enter Subject")
	public void enterSubject(String casesubject) {

		logger.info("Select Subject");
		try {
			// this.click(subject);
			this.sendKeys(subject, casesubject);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find agent name" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to select subject" + e);
		}
	}

	// Create case
	@Step("Click on Create case button")
	public void clkcreateCaseButton() {

		logger.info("Click on Create case button");
		try {
			this.waitForClickableElement(5, createCase);
			this.click(createCase);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find agent name" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on create case" + e);
		}
	}

	// CLose player account
	@Step("Close Player Account")
	public void closePlayerAcc(String playerAcc) {
		logger.info("Close Player account");
		try {
			this.clickOnDynamicElement(closePlayerAcc, playerAcc);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find agent name" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Player Account not closed" + e);
		}
	}

	// Verify Case is created
	@Step("Verify Case")
	public boolean verifyCase() {
		String caseID = null;
		boolean isTrue = false;
		logger.info("Verify Case is created");
		try {
			this.waitForClickableElement(10, caseNumber);
			caseID = this.getText(caseNumber);
			if (caseID != null) {
				isTrue = true;
			}
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find agent name" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able to create case" + e);
		}
		return isTrue;
	}

	// Create case
	@Step("Create Case in TSM")
	public void createCase(String productVal, String platformVal, String categoryVal, String subcategoryVal,
			String casesubject) {

		logger.info("create Case");
		try {
			selectProduct(productVal);
			selectPlatform(platformVal);
			selectCategory(categoryVal);
			selectSubCategory(subcategoryVal);
			enterSubject(casesubject);
			clkcreateCaseButton();
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to select Category" + e);
		}
	}

	//Verify Disabled Fields in Create case
	@Step("Verify Disabled fileds in create case")
	public boolean verifyDisabledfields() {
		boolean isVisible = false;
		try {
			logger.info("Verify Disabled fileds in create case");
			this.waitForElementToBeVisible(platformDisabled, 10);

			if (this.isElementVisible(platformDisabled, 5) && this.isElementVisible(categoryDisabled, 5)
					&& this.isElementVisible(subcategoryDisabled, 5)) {
				isVisible = true;
			}
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to find Disabled fields");
		}
		return isVisible;
	}

}
