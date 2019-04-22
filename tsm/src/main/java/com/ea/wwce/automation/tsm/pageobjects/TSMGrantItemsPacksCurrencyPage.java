package com.ea.wwce.automation.tsm.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
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
 * @description This class consists of Page objects and Functions of Grant Items, packs and Currency
 *              page in TSM application.
 * 
 */

public class TSMGrantItemsPacksCurrencyPage extends TSMBasePageObject {

	public TSMGrantItemsPacksCurrencyPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMGrantItemsPacksCurrencyPage.class);

	@FindBy(xpath = "//span[text()='Grant Items, Packs, Currency']")
	WebElement grantItemsPacksCurrency;
	@FindBy(css = "[name='typeComboValue']")
	WebElement typeDropdown;
	@FindBy(css = "[name='categoriesComboValue']")
	WebElement categoriesDropdown;
	@FindBy(css = "[title='Items']")
	WebElement items;
	@FindBy(css = "[title='Packs']")
	WebElement packs;
	@FindBy(css = "[title='Currency']")
	WebElement currency;
	@FindBy(xpath = "//div[contains(@class,'ProductSnapshot')]//div[@class='slds-dropdown-trigger slds-dropdown-trigger_click']")
	WebElement dropdown;
	@FindBy(xpath = "//button[text()='Next']")
	WebElement nextButton;
	@FindBy(xpath = "//lightning-primitive-cell-checkbox/span/label/span")
	WebElement selectCheckbox;
	@FindBy(xpath = "(//div[@class='cGrantContentAwardSection']//input[contains(@class,'combobox')])[1]")
	WebElement typeofAmountDropdown;
	@FindBy(xpath = "//lightning-base-combobox-item[@data-value='Standard']")
	WebElement selectStandardType;
	@FindBy(xpath = "//div[contains(@class,'cGrant')]//button[text()='Grant']")
	WebElement grantButton;
	@FindBy(xpath = "//div[@class='slds-m-top_large slds-m-bottom_x-small']")
	WebElement grantSuccessMessage;
	@FindBy(xpath = "//div[@class='cGrantItemInformation']//lightning-spinner[contains(@class,'slds-show')]")
	WebElement grantSpinner;
	@FindBy(xpath = "//button[text()='Okay']")
	WebElement okayButton;
	@FindBy(xpath = "(//div[contains(@class,'setting-height slds-is-relative')]//input[@class='slds-input'])[1]")
	WebElement inputOne;
	@FindBy(xpath = "(//div[contains(@class,'setting-height slds-is-relative')]//input[@placeholder='Select a Reason'])[1]")
	WebElement selectReasonOne;
	@FindBy(xpath = "(//div[contains(@class,'setting-height slds-is-relative')]//lightning-base-combobox-item[@data-value='PLAYER_FIRST'])[1]")
	WebElement reasonOne;	
	@FindBy(xpath = "(//div[contains(@class,'setting-height slds-is-relative')]//input[@class='slds-input'])[2]")
	WebElement inputTwo;
	@FindBy(xpath = "(//div[contains(@class,'setting-height slds-is-relative')]//input[@placeholder='Select a Reason'])[2]")
	WebElement selectReasonTwo;
	@FindBy(xpath = "(//div[contains(@class,'setting-height slds-is-relative')]//lightning-base-combobox-item[@data-value='PLAYER_FIRST'])[2]")
	WebElement reasonTwo;
	
	@FindAll(@FindBy(xpath = "//span//label//span[@class='slds-checkbox_faux']"))
	List<WebElement> checkbox;
	@FindAll(@FindBy(xpath = "//table[@class='slds-table slds-table_bordered slds-table_fixed-layout slds-table_resizable-cols']/tbody/tr"))
	List<WebElement> tableitems;
	
	String gamestatsFields = "//div[text()='#']";
	String personaFields = "//span[text()='#']";
	String associatedclubFields = "//span[text()='#']";
	String category = "//lightning-base-combobox-formatted-text[@title='#']";
	

	// click on GrantItemspacksCurrency from dropdown
	@Step("Click on  Grant Items, packs and Currency")
	public void selectGrantItemspacksCurrency() {
		logger.info("Click on Grant Items, packs and Currency");
		try {
			Thread.sleep(2000);
			this.waitForClickableElement(10, dropdown);
			this.click(dropdown);
			this.waitForElementToBeVisible(grantItemsPacksCurrency, 5);
			this.click(grantItemsPacksCurrency);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on GrantItemspacksCurrency from dropdown" + e);
		}
	}

	// Select Item and Category
	@Step("Select Item and Category")
	public void selectItemandCategory(String txtCategory) {
		logger.info("Select Item and Category");
		try {
			this.waitForElementToBeVisible(typeDropdown, 10);
			this.click(typeDropdown);
			this.click(items);
			this.waitForElementToBeInVisible(grantSpinner, 3);
			// this.waitForElementToBeVisible(grantSpinner, 10);
			this.click(categoriesDropdown);
			this.waitForDynamicElementToBeVisible(category, txtCategory, 5);
			this.clickOnDynamicElement(category, txtCategory);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to select Item and Category" + e);
		}
	}

	
	public void selectRowsIntable() {
		logger.info("Select rows in a table");

		try {
			this.waitForElementToBeInVisible(grantSpinner, 10);
			// this.waitForElementToBeVisible(grantSpinner, 10);
			if (tableitems != null) {
				if (tableitems.size() >= 2) {
					for (int i = 0; i <= 1; i++) {
						this.clickOnListElement(checkbox, i);
					}
				}
			} else {
				logger.warn("No Data for selected category");
			}
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Select Rows in table" + e);
		}
	}

	// Click on Next button
	public void clickNextButton() {
		logger.info("Click on Next button");
		try {
			this.click(nextButton);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on Next button" + e);
		}
	}

	// Select Type and Amount
	public void selectTypeandAmount() {
		logger.info("Select Type and Amount");
		try {
			this.waitForElementToBeVisible(typeofAmountDropdown, 3);
			this.click(typeofAmountDropdown);
			this.click(selectStandardType);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Select Type and Amount" + e);
		}
	}

	// Click on Grant Button
	public void clickOnGrantButton() {
		logger.info("Click on Grant Button");
		try {
			this.waitForElementToBeVisible(grantButton, 2);
			this.click(grantButton);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on Grant button" + e);
		}
	}

	// Verify grant success message
	@Step("Verify Grant Success Message")
	public String verifyGrantSuccessMessage() {
		logger.info("Verify Grant Success Message");
		String grantMessage = null;
		try {
			// this.waitForElementToBeInVisible(grantSpinner, 10);
			// this.waitForElementToBeVisible(grantSpinner, 10);
			this.waitForElementToBeVisible(grantSuccessMessage, 10);
			grantMessage = this.getText(grantSuccessMessage);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Verify Grant success message" + e);
		}
		return grantMessage;
	}

	// Need to write method to click on Ok button

	// select packs and Category
	//@Step("Select Pack and Category")
	public void selectPacksandCategory(String txtCategory ) {
		logger.info("Select Pack and Category");
		try {
			this.waitForElementToBeVisible(typeDropdown, 10);
			this.click(typeDropdown);
			this.click(packs);
			this.waitForElementToBeInVisible(grantSpinner, 3);
			// this.waitForElementToBeVisible(grantSpinner, 10);
			this.click(categoriesDropdown);
			this.waitForDynamicElementToBeVisible(category, txtCategory, 5);
			this.clickOnDynamicElement(category, txtCategory);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Select Pack and category" + e);
		}
	}

	// select Currency
	//@Step("Select Currency")
	public void selectCurrency() {
		logger.info("Select Currency");
		try {
			this.waitForElementToBeVisible(typeDropdown, 10);
			this.click(typeDropdown);
			this.click(currency);
			this.waitForElementToBeInVisible(grantSpinner, 10);
			// this.waitForElementToBeVisible(grantSpinner, 10);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Select Currency" + e);
		}
	}

	// Enter Currency and select Reason
	//@Step("Enter Currency and select reason")
	public void enterCurrencyAndSelectReason(String coins, String points) {
		logger.info("Enter Currency and select reason");
		try {
			//this.waitForElementToBeVisible(coinsInput, 10);
			this.click(inputOne);
			this.sendKeys(inputOne, coins);
			Thread.sleep(1000);
			this.click(selectReasonOne);
			this.click(reasonOne);
			this.sendKeys(inputTwo, points);
			Thread.sleep(1000);
			this.click(selectReasonTwo);
			this.click(reasonTwo);	
			this.waitForElementToBeInVisible(grantSpinner, 5);
			// this.waitForElementToBeVisible(grantSpinner, 10);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Select Currency" + e);
		}
	}
	
	
	//@Step("Verify grant Items")
	public void grantItems(String txtCategory) {
		logger.info("Verify Grant Items");
		try {
			selectGrantItemspacksCurrency();
			selectItemandCategory(txtCategory);
			selectRowsIntable();
			clickNextButton();
			selectTypeandAmount();
			clickOnGrantButton();
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to verify Grant Items" + e);
		}
	}
	
	//@Step("Verify grant Packs")
	public void grantPacks(String txtCategory) {
		logger.info("Verify Grant Items");
		try {
			selectGrantItemspacksCurrency();
			selectPacksandCategory(txtCategory);			
			selectRowsIntable();
			clickNextButton();
			selectTypeandAmount();
			clickOnGrantButton();
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to verify Grant Packs" + e);
		}
	}

	//@Step("Verify grant Currency")
	public void grantCurrency(String coins, String points) {
		logger.info("Verify Grant Currency");
		try {
			selectGrantItemspacksCurrency();
			selectCurrency();			
			selectRowsIntable();
			clickNextButton();
			enterCurrencyAndSelectReason(coins, points);
			clickOnGrantButton();
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to verify Grant Items" + e);
		}
	}
	
	//@Step("Close Grant Modal")
	public void closeGrantModal() {
		logger.info("Verify Close Grant Modal");
		try {
			this.waitForElementToBeVisible(okayButton, 5);
			this.click(okayButton);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to close grant modal" + e);
		}
	}

}
