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
 * @description This class consists of Page objects and Functions of Product
 *              page in TSM application.
 * 
 */

public class TSMProductPage extends TSMBasePageObject {

	public TSMProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMProductPage.class);

	@FindBy(css = "li[title='PRODUCT']")
	WebElement productTab;
	@FindBy(xpath = "//div[contains(@class,'ProductSnapshot')]//button[@class= 'slds-button slds-button_icon-border slds-button_icon-x-small']")
	WebElement dropdown;
	@FindBy(xpath = "//span[text()='Change Product']")
	WebElement changeProduct;
	@FindBy(css = "input[name='productSearch']")
	WebElement searchProduct;
	/*@FindBy(css = "label[for='battlefield-3']")
	WebElement selectProduct;*/
	@FindBy(xpath = "//button[text()='Select']")
	WebElement selectButton;
	@FindBy(xpath = "//div[contains(@class,'cProductTab')]/lightning-spinner[contains(@class,'slds-hide')]")
	WebElement productSpinner;
	@FindBy(css = "div[class='prodCSS']")
	WebElement selectedProduct;
	@FindBy(xpath = "//div[contains(@class,'cProductChange')]//button[text()='Cancel']")
	WebElement cancelProductModal;
	@FindBy(css = "lightning-base-combobox-item[data-value='Owned Products']")
	WebElement ownedproducts;
	@FindBy(xpath = "//div[text()='Products owned by the player']")
	WebElement ownedProductlabel;
	@FindBy(xpath = "//div[contains(@class,'cProductChange')]//input[contains(@name,'ComboValue')]")
	WebElement porductdropdown;
	@FindBy(xpath = "//lightning-combobox[contains(@class,'game')]//div[@role='combobox']")
	WebElement gamemodeDropdown;
	@FindBy(xpath = "//div[text()='Platform Status']")
	WebElement platfromstatus;
	@FindBy(xpath = "//lightning-combobox[contains(@class,'gamemodecombo')]//div[@class='slds-form-element__control']")
	WebElement clkGameMode;

	String productFields = "//label[text()='#']";
	String dropdownItems = "//span[text()='#']";
	String selectGameMode = "//lightning-base-combobox-formatted-text[@title='#']";
	String selectProduct = "//label[@for='#']";

	// CLick on Product Tab
	@Step("Click on Product Tab")
	public void clickonProduct() {
		logger.info("Click on Product Tab");
		try {
			// Thread.sleep(2000);
			this.waitForClickableElement(20, productTab);
			this.click(productTab);
			this.waitForElementToBeVisible(productSpinner, 10);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on Product tab" + e);
		}
	}

	// Select Product
	@Step("Select Product")
	public String selectProduct(String product, String productName) {
		String Product = null;
		logger.info("Select Product");
		try {
			this.waitForElementToBeVisible(dropdown, 3);
			this.click(dropdown);
			this.click(changeProduct);
			this.waitForElementToBeVisible(searchProduct, 5);
			this.sendKeys(searchProduct, product);			
			this.clickOnDynamicElement(selectProduct, productName);
			this.click(selectButton);
			this.waitForElementToBeVisible(productSpinner, 5);
			Product = this.getText(selectedProduct);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Select product failed" + e);
		}
		return Product;
	}

	// Cancel/Close Change Product Modal
	//@Step("Cancel Select Product Modal")
	public boolean isProductModalCancelled() {
		boolean isTrue = false;
		logger.info("Cancel Select Product Modal");
		try {
			this.waitForElementToBeVisible(dropdown, 3);
			this.click(dropdown);
			this.click(changeProduct);
			this.waitForElementToBeVisible(searchProduct, 5);
			this.click(cancelProductModal);
			isTrue = this.isElementVisible(productTab, 10);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Cancel Select Product Modal" + e);
		}
		return isTrue;
	}

	// Verify Owned Products
	//@Step("Verify Owned Products")
	public boolean verifyOwnedProducts(String product, String productName) {
		boolean isTrue = false;
		logger.info("Verify Owned Products");
		try {
			this.waitForElementToBeVisible(dropdown, 3);
			this.click(dropdown);
			this.click(changeProduct);
			this.waitForElementToBeVisible(porductdropdown, 5);
			this.click(porductdropdown);
			this.click(ownedproducts);
			this.sendKeys(searchProduct, product);
			isTrue = (this.isElementVisible(ownedProductlabel, 10) && (this.isDynamicElementPresent(selectProduct, productName, 5)));				
			this.clickOnDynamicElement(selectProduct, productName);
			this.click(selectButton);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Owned " + e);
		}
		return isTrue;
	}

	// Verify Product Fields
	@Step("Verify Product Fields")
	public boolean areProductFieldsAvailable(String fields) {

		boolean isAllElementsPresent = true;
		logger.info("Verify Product Fields");
		try {

			boolean isElementPresent = false;
			for (String field : fields.split("#")) {
				this.waitForDynamicElementToBeVisible(productFields, field, 5);
				isElementPresent = this.isDynamicElementPresent(productFields, field, 1);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + field);
				}

			}
			isElementPresent = this.isElementVisible(platfromstatus, 5);
		} catch (NoSuchElementException e) {
			isAllElementsPresent = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsPresent = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsPresent = false;
			logger.warn("Product Fields not found" + e);
		}
		return isAllElementsPresent;
	}

	// Verify Action Menu Items
	//@Step("Verify Action Menu Items")
	public boolean areActionMenuItemsAvailable(String fields) {

		boolean isAllElementsPresent = true;
		logger.info("Verify Action Menu Items");
		try {
			Thread.sleep(2000);
			this.waitForClickableElement(10, dropdown);
			// this.waitForElementToBeVisible(dropdown, 10);
			this.click(dropdown);
			boolean isElementPresent = false;
			for (String field : fields.split("#")) {
				this.waitForDynamicElementToBeVisible(dropdownItems, field, 5);
				isElementPresent = this.isDynamicElementPresent(dropdownItems, field, 1);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + field);
				}
			}
			/*this.waitForClickableElement(10, dropdown);
			this.click(dropdown);*/
		} catch (NoSuchElementException e) {
			isAllElementsPresent = false;
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			isAllElementsPresent = false;
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			isAllElementsPresent = false;
			logger.warn("Action menu items  not found" + e);
		}
		return isAllElementsPresent;
	}

	// Select Game modes
	@Step("Select Game modes")
	public void selectGameMode(String gameMode) {
		logger.info("Select Game modes");
		try {
			Thread.sleep(3000);
			this.moveToElement(dropdown);
			this.waitForClickableElement(10, clkGameMode);
			this.click(clkGameMode);
			this.clickOnDynamicElement(selectGameMode, gameMode);
			this.waitForElementToBeVisible(productSpinner, 2);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Select Game modes" + e);
		}
	}

}
