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
 * @description This class consists of Page objects and Functions of Case
 *              details in TSM application.
 *
 */

public class TSMCaseDetailsPage extends TSMBasePageObject {

	public TSMCaseDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMCaseDetailsPage.class);

	@FindBy(xpath = "//a[contains(text(),'Blank')]")
	WebElement attachment;
	@FindBy(xpath = "//span[@class='title slds-truncate' and contains(text(),'Blank')]")
	WebElement viewattachment;
	@FindBy(xpath = "//button[@class='slds-button slds-button_icon-bare']//*[name()='svg' and @data-key='lock']")
	WebElement unlockAttachment;
	@FindBy(xpath = "//button[contains(@class,'cCaseAttachmentDelete')]")
	WebElement clkDeleteicon;
	@FindBy(xpath = "//button[@class='slds-button slds-button_brand' and @title='Delete']")
	WebElement clkDeletebtn;
	@FindBy(xpath = "//span[contains(@class,'title') and contains(text(),'Blank')]")
	WebElement viewAttachment;
	@FindBy(xpath = "//div[@class='slds-text-heading_small c-heading']")
	WebElement caseID;
	@FindBy(xpath = "//div[@class='slds-form-element__control slds-grow']//input")
	WebElement subject;
	@FindBy(xpath = "//textarea[@id='caseDescription']")
	WebElement description;
	@FindBy(xpath = "//label[text()='Product']/parent::lightning-input//div//input")
	WebElement product;
	@FindBy(xpath = "//label[text()='Platform']/parent::lightning-input//div//input")
	WebElement platform;
	@FindBy(xpath = "//label[text()='Category']/parent::lightning-input//div//input")
	WebElement category;
	@FindBy(xpath = "//label[text()='Issue']/parent::lightning-input//div//input")
	WebElement subcategory;
	@FindBy(xpath = "//div[@class='slds-m-top_medium thor-line-height']//lightning-spinner[@class='slds-spinner_container slds-show']")
	WebElement deletespinner;
	@FindBy(xpath = "//div[contains(@class,'slds-p-right--none')]//*[name()='svg' and @data-key='close']")
	WebElement closeattachment;
	@FindBy(xpath = "//div[contains(@class,'cCaseDetailsTSM')]//lightning-spinner[@class='slds-spinner_container slds-show']")
	WebElement casedetailsspinner;

	@Step("Open attachment")
	public void clkOnAttachment() {
		logger.info("Open attachment");
		try {
			// Thread.sleep(5000);
			this.waitForClickableElement(20, attachment);
			this.click(attachment);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to open attachment" + e);
		}
	}

	@Step("Verify attachment opened")
	public boolean isAttachmentOpened() {

		boolean isTrue = false;
		logger.info("Verify attachment is opened");
		try {
			isTrue = this.isElementVisible(viewAttachment, 20);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able to open attachment" + e);
			return false;
		}
		logger.info("Attachment opened : " + isTrue);
		return isTrue;
	}

	@Step("Click on Delete icon")
	public void deleteAttachment(String CaseNumber) {

		logger.info("Click on delete icon");
		try {
			this.click(closeattachment);
			this.waitForClickableElement(5, unlockAttachment);
			this.moveToElement(unlockAttachment);
			if (this.waitForElementToBeVisible(unlockAttachment, 5)) {
				Thread.sleep(2000);
				this.click(unlockAttachment);
				this.waitForElementToBeInVisible(deletespinner, 10);
				this.waitForClickableElement(10, clkDeleteicon);
				this.click(clkDeleteicon);
				Thread.sleep(2000);
				this.click(clkDeletebtn);
			} else {
				this.waitForClickableElement(10, clkDeleteicon);
				// Thread.sleep(3000);
				this.click(clkDeleteicon);
				Thread.sleep(3000);
				this.click(clkDeletebtn);
			}
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click On delete icon" + e);
		}
	}

	@Step("Verify Attachment is present")
	public boolean isAttachmentPresent() {

		boolean isTrue = false;
		logger.info("Verify attachment is present");
		try {
			Thread.sleep(4000);
			isTrue = this.isElementVisible(attachment, 20);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Attachment found" + e);
		}
		logger.info("Attachment not found : " + isTrue);
		return isTrue;
	}

	@Step("Verify Case ID in case details")
	public String verifyCaseID() {

		String strcaseID = null;
		String casenumber = null;

		logger.info("Verify case ID in tsm");
		try {
			 Thread.sleep(2000);
			this.waitForElementToBeInVisible(casedetailsspinner, 10);
			this.waitForElementToBeVisible(caseID, 20);
			strcaseID = this.getText(caseID);
			String[] cid = strcaseID.split("\\s");
			casenumber = cid[2];
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able find case id" + e);
		}
		return casenumber;
	}

	@Step("Verify Subject in case details")
	public String verifySubject() {

		String strsubject = null;
		logger.info("Verify subject in case details in tsm");
		try {
			this.waitForElementToBeInVisible(casedetailsspinner, 5);
			this.waitForElementToBeVisible(subject, 10);
			strsubject = this.getAttributeValue(subject, "value");
			System.out.println(strsubject);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able find Subject in case details" + e);
		}
		return strsubject;
	}

	@Step("Verify Description in case details")
	public String verifyDescription() {

		String strdescription = null;
		logger.info("Verify Descritpion case details in tsm");
		try {
			//this.waitForElementToBeInVisible(casedetailsspinner, 3);
			this.waitForElementToBeVisible(description, 10);
			strdescription = this.getText(description);
			// getAttributeValue(description, "value");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able find Descritpion in case details" + e);
		}
		return strdescription;
	}

	@Step("Verify Product in case details")
	public String verifyProduct() {

		String strproduct = null;
		logger.info("Verify Product case details in tsm");
		try {
			//this.waitForElementToBeInVisible(casedetailsspinner, 10);
			this.waitForElementToBeVisible(product, 10);
			strproduct = this.getAttributeValue(product, "value");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able find Product in case details" + e);
		}
		return strproduct;
	}

	@Step("Verify Platform in case details")
	public String verifyPlatform() {

		String strplatform = null;
		logger.info("Verify Platform case details in tsm");
		try {
		//	this.waitForElementToBeInVisible(casedetailsspinner, 10);
			this.waitForElementToBeVisible(platform, 10);
			strplatform = this.getAttributeValue(platform, "value");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able find Platform in case details" + e);
		}
		return strplatform;
	}

	@Step("Verify Category in case details")
	public String verifycategory() {

		String strcategory = null;
		logger.info("Verify category case details in tsm");
		try {
		//	this.waitForElementToBeInVisible(casedetailsspinner, 10);
			this.waitForElementToBeVisible(platform, 10);
			strcategory = this.getAttributeValue(category, "value");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able find category in case details" + e);
		}
		return strcategory;
	}

	@Step("Verify subcategory/issue in case details")
	public String verifysubcategory() {

		String strsubcategory = null;
		logger.info("Verify subcategory case details in tsm");
		try {
		//.waitForElementToBeInVisible(casedetailsspinner, 10);
			strsubcategory = this.getAttributeValue(subcategory, "value");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able find subcategory in case details" + e);
		}
		return strsubcategory;
	}

}
