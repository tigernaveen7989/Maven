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
 * @description This class consists of Page objects and Functions of Current
 *              Inventory in TSM application.
 * 
 */

public class TSMCurrentInventoryPage extends TSMBasePageObject {

	public TSMCurrentInventoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMCurrentInventoryPage.class);

	@FindBy(css = "[title='Current Inventory']")
	WebElement currentInventory;
	@FindBy(xpath = "//input[@placeholder='Select type']")
	WebElement selectTypeDropdown;
	@FindBy(css = "[data-value='ITEM_CATEGORY']")
	WebElement items;
	@FindBy(css = "[data-value='PACK_TYPE']")
	WebElement packs;
	@FindBy(css = "[data-value='Currency']")
	WebElement currency;
	@FindBy(css = "[placeholder='Select Category']")
	WebElement categoriesDropdown;
	@FindBy(css = "[data-value='KITCARDS']")
	WebElement kitcards;
	@FindBy(css = "[data-value='CARDPACK']")
	WebElement cardpack;
	@FindBy(xpath = "//button[text()='Modify Currency']")
	WebElement modifyCurrencyButton;
	@FindBy(css = "[placeholder='Select']")
	WebElement selectActionDropdown;
	@FindBy(css = "[data-value='credit']")
	WebElement grant;
	@FindBy(css = "[data-value='debit']")
	WebElement debit;
	@FindBy(xpath = "//div[contains(@class,'cModifyCurrency')]//div[contains(@class,'slds-grow')]//input")
	WebElement enterAmount;
	@FindBy(css = "[placeholder='Select a Reason']")
	WebElement selectReason;
	@FindBy(css = "[data-value='ILLEGITIMATE_CONTENT']")
	WebElement reason;
	@FindBy(xpath = "//button[text()='Apply']")
	WebElement applyButton;
	@FindBy(xpath = "(//dt[contains(text(),'Balance')]//following-sibling::dd)[1]")
	WebElement getBalance;
	@FindBy(xpath = "(//div[@class='slds-p-around_small RowContent'])[1]")
	WebElement expandRow;
	@FindBy(css = "[placeholder='Search for Items, Packs, Currency']")
	WebElement search;
	@FindBy(xpath = "(//div[@title='FUT Wallet'])[1]")
	WebElement expandCurrencyRow;

	String currencyFields = "//div[@class='slds-p-top_small cCurrentInventory']//dt[text()='# : ']";
	String itemFields = "//dt[@title='#']";
	String packFields = "//dt[@title='#']";
	String packDetailsFields = "//th[text()='#']";
	String category = "//lightning-base-combobox-formatted-text[@title='#']";

	// Click on Current Inventory
	@Step("Click on Current Inventory Tab")
	public void clickonCurrentInventoryTab() {
		logger.info("Click on Current Inventory Tab");
		try {
			Thread.sleep(3000);
			this.waitForClickableElement(10, currentInventory);
			this.click(currentInventory);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on Current Inventory Tab" + e);
		}
	}

	// Select Currency
	@Step("Select Currency")
	public void selectCurrency() {
		logger.info("verify Select Currency");
		try {
			Thread.sleep(2000);
			this.waitForElementToBeVisible(selectTypeDropdown, 5);
			this.click(selectTypeDropdown);
			this.click(currency);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to select currency" + e);
		}
	}

	// Verify fields for all currency types
	//@Step("Verify fields for all currency types")
	public boolean areCurrencyFieldsAvailable(String currencyType, String curencydata) {

		boolean isAllElementsPresent = true;
		logger.info("verify coins fields");
		try {

			for (String currency : currencyType.split("#")) {
				Thread.sleep(2000);
				this.waitForClickableElement(10, search);
				this.sendKeys(search, currency);
				this.click(expandCurrencyRow);
				boolean isElementPresent = false;
				for (String currencies : curencydata.split("#")) {
					isElementPresent = this.isDynamicElementPresent(currencyFields, currencies, 1);
					if (!isElementPresent) {
						isAllElementsPresent = false;
						logger.error("Dynamic element not found :" + currency);
					}
				}
				this.click(expandCurrencyRow);
			}

		} catch (NoSuchElementException e) {
			isAllElementsPresent = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsPresent = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsPresent = false;
			logger.warn("Currency fields not found" + e);
		}
		return isAllElementsPresent;
	}

	// Select Items
	@Step("Select Items")
	public void selectItems(String txtcategory) {
		logger.info("verify Select Items");
		try {
			this.click(selectTypeDropdown);
			this.click(items);
			Thread.sleep(2000);
			selectCategory(txtcategory);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("failed to select Items" + e);
		}
	}

	// Select Category
	@Step("Select Category")
	public void selectCategory(String txtcategory) {
		logger.info("verify Select Category");
		try {
			this.waitForElementToBeVisible(categoriesDropdown, 5);
			this.click(categoriesDropdown);
			this.waitForDynamicElementToBeVisible(category, txtcategory, 5);
			this.clickOnDynamicElement(category, txtcategory);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("failed to select Category" + e);
		}
	}

	// Verify fields in Kitcards category of Items
	//@Step("Verify fields in Kitcards category of Items")
	public boolean areItemFieldsAvailable(String itemsData) {

		boolean isAllElementsPresent = true;
		logger.info("verify fields in Leeds United Category");
		try {
			this.waitForElementToBeVisible(expandCurrencyRow, 5);
			this.click(expandCurrencyRow);
			boolean isElementPresent = false;
			for (String items : itemsData.split("#")) {
				isElementPresent = this.isDynamicElementPresent(itemFields, items, 1);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + items);
				}
			}
		} catch (NoSuchElementException e) {
			isAllElementsPresent = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsPresent = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsPresent = false;
			logger.warn("fields in Kitcards category are not found" + e);
		}
		return isAllElementsPresent;
	}

	// Select Packs and category
	@Step("Select Packs")
	public void selectPacks(String txtcategory) {
		logger.info("verify Select Packs");
		try {

			this.click(selectTypeDropdown);
			this.click(packs);
			Thread.sleep(2000);
			selectCategory(txtcategory);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("failed to select Packs" + e);
		}
	}

	// Verify pack fields
	//@Step("Verify fields in cardpack category of Packs")
	public boolean arePackFieldsAvailable(String packData) {

		boolean isAllElementsPresent = true;
		logger.info("verify fields for pack");
		try {
			this.waitForElementToBeVisible(expandCurrencyRow, 5);
			this.click(expandCurrencyRow);
			boolean isElementPresent = false;
			for (String packField : packData.split("#")) {
				isElementPresent = this.isDynamicElementPresent(packFields, packField, 1);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + packField);
				}
			}
		} catch (NoSuchElementException e) {
			isAllElementsPresent = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsPresent = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsPresent = false;
			logger.warn("Fields in Cardpack category are not found" + e);
		}
		return isAllElementsPresent;
	}

	// Verify fields in Pack Details in cardpack
	//@Step("Verify pack details fields in cardpack category of Packs")
	public boolean arePackDetailsFieldsAvailable(String packDetailsData) {

		boolean isAllElementsPresent = true;
		logger.info("Verify pack details fields in cardpack category of Packs");
		try {
			boolean isElementPresent = false;
			// this.waitForElementToBeVisible(packsBronzepack, 5);
			// this.click(packsBronzepack);
			for (String packdetailsField : packDetailsData.split("#")) {
				isElementPresent = this.isDynamicElementPresent(packDetailsFields, packdetailsField, 1);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + packdetailsField);
				}
			}
		} catch (NoSuchElementException e) {
			isAllElementsPresent = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsPresent = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsPresent = false;
			logger.warn("Pack details fields in Cardpack category are not found" + e);
		}
		return isAllElementsPresent;
	}

	// Verify Grant currency
	//@Step("Grant Currency")
	public void grantCurrency(String txtAmount) {
		logger.info("Grant currency");
		try {
			this.click(modifyCurrencyButton);
			this.waitForElementToBeVisible(selectActionDropdown, 5);
			this.click(selectActionDropdown);
			this.click(grant);
			this.sendKeys(enterAmount, txtAmount);
			this.waitForElementToBeVisible(selectReason, 5);
			this.click(selectReason);
			this.click(reason);
			this.click(applyButton);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("failed to Grant currency" + e);
		}
	}

	// Verify Debit currency
	//@Step("Debit Currency")
	public void debitCurrency(String txtAmount) {
		logger.info("Debit currency");
		try {
			this.click(modifyCurrencyButton);
			this.waitForElementToBeVisible(selectActionDropdown, 5);
			this.click(selectActionDropdown);
			this.click(debit);
			this.sendKeys(enterAmount, txtAmount);
			this.waitForElementToBeVisible(selectReason, 5);
			this.click(selectReason);
			this.click(reason);
			this.click(applyButton);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("failed to Debit currency" + e);
		}
	}

	// Verify Grant Currency
	//@Step("Verify Grant Currency")
	public boolean verifyGrantCurrency(String txtAmount, String currency) {
		boolean isTrue = false;
		logger.info("Verify Grant Currency");
		try {

			for (String currencies : currency.split("#")) {
				Thread.sleep(2000);
				this.waitForClickableElement(10, search);
				this.sendKeys(search, currencies);
				this.waitForClickableElement(5, expandRow);
				this.click(expandRow);
				String currentbalance = this.getText(getBalance);
				String modifiedBalance = String.valueOf(Integer.parseInt(currentbalance) + Integer.parseInt(txtAmount));
				grantCurrency(txtAmount);
				Thread.sleep(4000);
				this.waitForClickableElement(10, search);
				this.sendKeys(search, currencies);
				this.waitForElementToBeVisible(expandRow, 10);
				this.click(expandRow);
				this.waitForElementToBeVisible(getBalance, 5);
				String newBalance = this.getText(getBalance);
				if (newBalance.equals(modifiedBalance))
					isTrue = true;
				else
					logger.warn("Old and new balance not matched for : " + currencies);
				this.click(expandRow);
			}
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Grant currency  not matched after updating" + e);
		}
		return isTrue;
	}

	// Verify debit Currency
	//@Step("Verify Debit Currency")
	public boolean verifyDebitCurrency(String txtAmount, String currency) {
		boolean isTrue = false;
		logger.info("Verify debit Currency");
		try {

			for (String currencies : currency.split("#")) {
				Thread.sleep(2000);
				this.waitForClickableElement(10, search);
				this.sendKeys(search, currencies);
				this.waitForClickableElement(5, expandRow);
				this.click(expandRow);
				String currentbalance = this.getText(getBalance);
				String modifiedBalance = String.valueOf(Integer.parseInt(currentbalance) - Integer.parseInt(txtAmount));
				debitCurrency(txtAmount);
				Thread.sleep(4000);
				this.waitForClickableElement(10, search);
				this.sendKeys(search, currencies);
				this.waitForElementToBeVisible(expandRow, 10);
				this.click(expandRow);
				this.waitForElementToBeVisible(getBalance, 5);
				String newBalance = this.getText(getBalance);
				if (newBalance.equals(modifiedBalance))
					isTrue = true;
				else
					logger.warn("Old and new balance not matched for : " + currencies);
				this.click(expandRow);
			}
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Debit currency not matched after updating" + e);
		}
		return isTrue;
	}
}
