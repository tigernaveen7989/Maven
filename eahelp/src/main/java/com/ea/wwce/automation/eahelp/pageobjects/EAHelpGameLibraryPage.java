package com.ea.wwce.automation.eahelp.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
/**
 * 
 * @author msomagari
 * @description EA HELP Game Library
 * 
 **/
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;

import io.qameta.allure.Step;

/**
 * 
 * @author sadabala
 *
 */
public class EAHelpGameLibraryPage extends EAHelpBasePageObject {

	public EAHelpGameLibraryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpGameLibraryPage.class);

	@FindBy(xpath = "//div[@class='blocks']/div[@class='game block']")
	WebElement gameBtn;
	@FindBy(xpath = "//div[@class='items']/ul/li")
	WebElement defaultGames;
	@FindBy(xpath = "//div[@class='game-filter']/input")
	WebElement lstsearchProduct;
	@FindBy(xpath = "//span[@class=\"icon-chevron-right\"]")
	WebElement rightArrow;
	@FindBy(css = ".carousel-control.right.disabled")
	WebElement rightArrowDisabled;
	@FindBy(xpath = "//span[@class=\"icon-chevron-left\"]")
	WebElement leftArrow;
	@FindBy(css = ".carousel-control.left.disabled")
	WebElement leftArrowDisabled;
	@FindBy(css = "div.game-filter >input")
	WebElement gamesSearchBox;
	@FindBy(xpath = "//ul[@class='carousel']//li[1]//img")
	WebElement productImageSource;
	@FindBy(xpath = "//ul[@class='carousel']//li[1]")
	WebElement productSource;
	@FindBy(className = "icon-chevron-right")
	WebElement rightNavigation;
	@FindAll(@FindBy(css = ".carousel li"))
	public List<WebElement> productsList;
	@FindBy(css = ".no-results")
	WebElement noResults;
	@FindBy(css = ".icon-remove-sign")
	WebElement cancelIcon;
	@FindBy(css = ".game.block.selected.shrink span.title")
	WebElement gamesLabel;
	@FindBy(css = ".account.block.shrink span.title")
	WebElement accountsLabel;

	// Dynamic xpath to select product name
	String productImage = "//a[@title='#']";
	String entitlementflag = "//ul[@class='carousel']//li['#']//div[@class='corner']";

	// Pages called here
	EAHelpHomePage eaHelpHomePage = new EAHelpHomePage(driver);

//=========================================================================

	@Step("Verify Games icon is present under games library")
	public String verifyGamesIconPresent() {
		this.waitForElementToBeVisible(gamesLabel, 30);
		return this.getAttributeValue(gamesLabel, "data-alternate");
	}

	@Step("Verify Games icon is present under accounts library")
	public String verifyAccountsIconPresent() {
		this.waitForElementToBeVisible(accountsLabel, 30);
		return this.getAttributeValue(accountsLabel, "data-alternate");
	}

	@Step("Click on cancel icon")
	public void cancelSearch() {
		this.click(cancelIcon);
	}

	@Step("get search value")
	public String getSearchValue() {
		return this.getAttributeValue(gamesSearchBox, "value");
	}

	@Step("Verify no results message")
	public String verifyNoResultsMessage() {
		return this.getText(noResults);
	}

	@Step("Verify User Game Library info")
	public void userGameLibraryInfo() {
		logger.info("Verify User Game Library info");
		try {
			this.loadPage(EAHELPDataConstants.EAHELP_AUT_URL);
			this.waitForElementToBeVisible(gameBtn, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			this.click(gameBtn);
			this.waitForElementToBeVisible(defaultGames, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			this.waitForElementToBeVisible(rightArrow, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			this.waitForElementToBeVisible(lstsearchProduct, EAHELPDataConstants.IMPLICIT_TIMEOUT);

		} catch (NoSuchElementException ex) {
			logger.warn("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.warn("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	// Verify right arrow is visible
	public boolean verifyRightArrow() {
		return this.waitForElementToBeVisible(rightArrow, 30);
	}

	// Verify right arrow is visible
	public boolean verifyLeftArrow() {
		// this.click(rightArrow);
		return this.waitForElementToBeVisible(leftArrow, 30);
	}

	// Method to select product
	@Step("Search a product from Product list on Home Page")
	public void selectProduct(String productName) {
		try {

			eaHelpHomePage.clickOnGamesLibrary();
			this.waitForClickableElement(20, gamesSearchBox);
			this.sendKeys(gamesSearchBox, productName);
			this.waitForDynamicElementToBeVisible(productImage, productName, 120);
			this.clickOnDynamicElement(productImage, productName);
		} catch (Exception e) {
			logger.info("Failed to select a product  " + e.getMessage());
		}
	}

	// Method to verify entitle flag (*)is present
	public String verifyEntitlement(String index) {
		String cssvalue = null;
		try {
			// eaHelpHomePage.clickOnGamesLibrary();
			logger.info("Verify entitlement tag for a product...  ");
			// this.waitForElementToBeVisible(rightNavigation, 20);
			// this.waitForDynamicElementToBeVisible(entitlementflag, index, 10);
			cssvalue = this.getCSSValueFromDynamicElement(entitlementflag, index, 10, "background");
		} catch (Exception e) {
			logger.warn("Not able to verify entitlement tag for a product... ");
		}
		return cssvalue;
	}

	// Method to verify product image values
	public String verifyProductImageCSSValues(String property) {
		String cssvalue = null;
		try {
			logger.info("Verify css values for a product image...  ");
			cssvalue = this.getCSSValue(productImageSource, property);
			logger.info("Expected :  " + cssvalue);
		} catch (Exception e) {
			logger.warn("Not able to verify css values for a product image...");
		}
		return cssvalue;
	}

	// Method to get attribute values
	public String getProductAttributeValues(String attributeName) {
		String val = null;
		try {
			logger.info("Get attribute value from a product image...");
			val = this.getAttributeValue(productImageSource, attributeName);
			logger.info("Get attribute value from a product image... " + attributeName + " value" + val);
		} catch (Exception e) {
			logger.warn("Not able to get attribute value from a product image...");
		}
		return val;
	}

	// Method to verify product source values
	public String verifyProductSourceCSSValues(String property) {
		String cssvalue = null;
		try {
			logger.info("Verify css values for a product...  ");
			cssvalue = this.getCSSValue(productSource, property);
			logger.info("Expected :  " + cssvalue);
		} catch (Exception e) {
			logger.warn("Not able to verify css values for a product...");
		}
		return cssvalue;
	}

	// Click on game library and wait for products to display
	public boolean searchForProduct(String product) {
		boolean isTrue = false;
		try {
			logger.info("search a product...  ");
			this.waitForClickableElement(20, gamesSearchBox);
			this.sendKeys(gamesSearchBox, product);
			isTrue = this.waitForDynamicElementToBeVisible(productImage, product, 10);
			logger.info("got product successfully...  ");
		} catch (Exception e) {
			logger.warn("Failed to find product  " + e.getMessage());
		}
		return isTrue;
	}

	// Get defaults games list
	public int getDefaultsGamesSize() {
		return productsList.size();
	}

	// Get defaults games list
	public List<WebElement> getDefaultsGamesList() {
		return productsList;
	}

	// Get game title
	public String getGameTitle(WebElement game) {
		String title = game.findElement(By.cssSelector("li div.title")).getText();
		return title;
	}

	// User can navigate forward
	public String verifyUserNavigateForward() {
		int i = 0;
		boolean isEnabled = this.waitForElementToBeVisible(rightArrowDisabled, 3);
		while (!isEnabled) {
			i++;
			this.click(rightArrow);
			isEnabled = this.waitForElementToBeVisible(rightArrowDisabled, 1);

		}
		return Integer.toString(i);
	}

	// User can navigate backward
	public String verifyUserNavigateBackward() {
		int i = 0;
		boolean isEnabled = this.waitForElementToBeVisible(leftArrowDisabled, 3);
		while (!isEnabled) {
			i++;
			this.click(leftArrow);
			isEnabled = this.waitForElementToBeVisible(leftArrowDisabled, 3);
		}
		return Integer.toString(i);
	}

}
