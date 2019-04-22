package com.ea.wwce.automation.tsm.pageobjects;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * 
 * @author rgandham
 * @description This class consists of Page objects and Functions of History
 *              page in TSM application.
 * 
 */

public class TSMHistoryPage extends TSMBasePageObject {

	public TSMHistoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMHistoryPage.class);

	@FindBy(css = "[title='History']")
	WebElement history;
	@FindBy(css = "[name='presentSelections']")
	WebElement presetSelections;
	@FindBy(xpath = "//input[@name='endDate' and @class='slds-input']")
	WebElement enterEndDate;
	@FindBy(xpath = "//input[@name='endDate' and @class='slds-input slds-combobox__input']")
	WebElement enterEndTime;
	//@FindBy(xpath = "//span[text()='All Types']/preceding-sibling::span[@class='slds-checkbox_faux']")
	@FindBy(xpath = "//span[text()='All Types']")
	WebElement allTypesloginCheckbox;
	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveButton;
	@FindBy(xpath = "//div[contains(@class,'cHistoryFilter')]//lightning-spinner[contains(@class,'slds-hide')]")
	WebElement spinner;
	@FindBy(xpath = "//button[contains(text(),'Back to Filters')]")
	WebElement backtoFilters;


	String checkbox = "//span[text()='#']//preceding-sibling::span[@class='slds-checkbox_faux']";
	String historyFields = "//span[text()='#']";
	String modes = "//span[text()='#']/preceding-sibling::span[@class='slds-checkbox_faux']";
	String search = "//label[text()='#']//parent::lightning-input[@class='slds-form-element']//following-sibling::div//input";
	String selectTransactions = "//span[text()='#']";
	String selectmodes = "//label[text()='#']/ancestor::div[contains(@class,'slds-m-left--x-large')] //div[text()='#']";
	String selectDraft = "//span[text()='#']";

	//Click on History
	@Step("Click on History Tab")
	public void clickonHistoryTab() {
		logger.info("Click on History Tab");
		try {
			Thread.sleep(3000);
			this.waitForClickableElement(10, history);
			this.click(history);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on History Tab" + e);
		}
	}

	//Select Time Range
	//@Step("Select Time Range")
	public void selectTimeRange(String endDate, String endTime) {
		logger.info("Select Time Range");
		try {
			this.waitForClickableElement(10, enterEndDate);
			this.sendKeys(enterEndDate, endDate);
			this.clearTextbox(enterEndTime);
			this.sendKeys(enterEndTime, endTime);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to select Time range" + e);
		}
	}

	// Select and Verify Login History 
	//@Step("Select and Verify Login History")
	public boolean selectandVerifyLoginHistory(String endDate, String endTime, String Fields) {
		logger.info("Select Login history filters");
		boolean isAllElementsvisible = true;
		try {
			Thread.sleep(3000);
		//	selectTimeRange(endDate, endTime);
			this.moveToElement(allTypesloginCheckbox);
			this.waitForClickableElement(5, allTypesloginCheckbox);
			this.click(allTypesloginCheckbox);
			this.moveToElement(saveButton);
			this.click(saveButton);
			this.waitForElementToBeVisible(spinner, 10);
			isAllElementsvisible = areHistoryFieldsAvailable(Fields);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to find element in Login history " + e);
		}
		return isAllElementsvisible;
	}

	// Verify History Fields
	@Step("Verify History fields")
	public boolean areHistoryFieldsAvailable(String Fields) {

		boolean isAllElementsPresent = true;
		logger.info("verify History fields");
		try {
			boolean isElementPresent = false;
			for (String Field : Fields.split("#")) {
				this.waitForDynamicElementToBeVisible(historyFields, Field, 5);
				isElementPresent = this.isDynamicElementPresent(historyFields, Field, 1);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + Field);
				}
			}
			this.waitForClickableElement(5, backtoFilters);
			this.click(backtoFilters);
		} catch (NoSuchElementException e) {
			isAllElementsPresent = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsPresent = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsPresent = false;
			logger.warn("Events Fields not found" + e);
		}
		return isAllElementsPresent;
	}

	// Select and verify Event History Fields
	//@Step("Select and verify Event History ")
	public boolean selectAndVerifyModesHistory(String endDate, String endTime, String modesData, String modes, String Fields) {
		logger.info("Select history filters");
		boolean isAllElementsvisible = false;
		try {
			Thread.sleep(3000);
		//	selectTimeRange(endDate, endTime);
			String[] modesArr = modes.split("#");
			int index = 0;
			for (String modeData : modesData.split("#")) {
				this.moveToElement(saveButton);
				//this.windowScrollDwn();
				this.clickOnDynamicElement(checkbox, modeData);
				this.waitForDynamicElementToBeVisible(search, modeData, 5);
				Thread.sleep(5000);
				this.clickOnDynamicElement(search, modeData);
				this.waitForDynamicElementToBeVisible(selectmodes, modesArr[index], 5);
				this.clickOnDynamicElements(selectmodes, modeData, modesArr[index]);
				//this.clickOnDynamicElement(selectmodes, modesArr[index]);
				this.click(saveButton);
				this.waitForElementToBeVisible(spinner, 5);
				isAllElementsvisible = areHistoryFieldsAvailable(Fields);
				index++;
			}
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to find element in Events history " + e);
		}
		return isAllElementsvisible;
	}

	@Step("Select and Verify Transaction history")
	public boolean selectandVerifyTransactionsHistory(String endDate, String endTime, String Fields, String txttransaction) {

		boolean isAllElementsvisible = false;
		logger.info("Select and Verify Transaction history");
		try {
		//	selectTimeRange(endDate, endTime);
			this.moveToElement(saveButton);
			this.waitForDynamicElementToBeVisible(selectTransactions, txttransaction, 5);
			this.clickOnDynamicElement(selectTransactions, txttransaction);
			this.click(saveButton);
			this.waitForElementToBeVisible(spinner, 5);
			isAllElementsvisible = areHistoryFieldsAvailable(Fields);

		} catch (NoSuchElementException e) {
			isAllElementsvisible = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsvisible = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsvisible = false;
			logger.warn("Failed to verify Transaction Histiry" + e);
		}
		return isAllElementsvisible;
	}
	
	@Step("Select and Verify Draft history")
	public boolean selectandVerifyDraftHistory(String endDate, String endTime, String Fields, String txtDraft) {

		boolean isAllElementsvisible = false;
		logger.info("Select and Verify Draft history");
		try {
			//selectTimeRange(endDate, endTime);
			this.moveToElement(saveButton);
			this.waitForDynamicElementToBeVisible(selectDraft, txtDraft, 5);
			this.clickOnDynamicElement(selectDraft, txtDraft);
			this.click(saveButton);
			this.waitForElementToBeVisible(spinner, 5);
			isAllElementsvisible = areHistoryFieldsAvailable(Fields);

		} catch (NoSuchElementException e) {
			isAllElementsvisible = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsvisible = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsvisible = false;
			logger.warn("Failed to verify Draft Histiry" + e);
		}
		return isAllElementsvisible;
	}

}
