package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;

import io.qameta.allure.Step;

/**
 * 
 * @author rdronamraju
 * @description EA HELP Login page object
 */

public class EAHelpHomePage extends EAHelpBasePageObject {

	public EAHelpHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpHomePage.class);

	// @FindBy(xpath = "//div[@class='account block']")
	// WebElement accountBlock;
	@FindBy(xpath = "//div[@id='gus']//a[@class='dropdown-toggle']")
	WebElement btnUserName;
	@FindBy(xpath = "//div[@id='gus']//a[@class='logout']")
	WebElement bntLogout;
	@FindBy(css = "a.portal-alert-link")
	WebElement alertLink;
	@FindBy(css = "div.csat-survey")
	WebElement surveyBanner;
	@FindBy(css = "span.accept")
	WebElement takeSurveyLink;
	@FindBy(css = "#game-selector")
	WebElement gamesTab;
	@FindBy(css = "#game-selector input:first-child")
	WebElement gamesSearch;
	@FindBy(css = "#account-selector >a:first-child")
	WebElement accountsTab;
	@FindBy(css = "#my-conversations > a:first-child")
	WebElement mycaseLink;
	@FindBy(css = "input[name='q']")
	WebElement globalSearch;
	@FindBy(css = "#gus > .logged-in.nav span")
	WebElement userName;
	@FindBy(css = "body[dir='rtl']")
	WebElement arContentAlignment;
	@FindBy(css = "#utility-bar-header li#contactus")
	WebElement contactUsButton;
	@FindBy(css = "#community a[href*='/community/']")
	WebElement ahqButton;
	@FindBy(css = "#contactus +#country-selector")
	WebElement countryDropDown;
	@FindBy(css = "[name='google-site-verification'] +[name='description']")
	WebElement metaDesc;
	@FindBy(css = "#home span[data-full='Help with a Game']")
	WebElement gamesBlock;
	@FindBy(css = "#home span[data-full='Manage your Account']")
	WebElement accountBlock;
	@FindBy(className = "icon-chevron-right")
	WebElement rightNavigation;
	@FindBy(css = "li.no-results")
	WebElement noResults;
	@FindBy(css = "#utility-logo")
	WebElement logo;
	@FindBy(css = ".utility-bar-header .signup")
	WebElement signup;
	@FindBy(css = "#panel-duplicate-email .panel-content")
	WebElement signUpDuplicateEmail;
	@FindBy(css = "#email")
	WebElement emailTextBox;
	@FindBy(css = "#btn-next")
	WebElement nextBtn;
	@FindBy(css = "origin-logo")
	WebElement originLogo;
	@FindBy(css = "#hot-topics .spotlight-img")
	WebElement contactDriver;
	@FindBy(css = "#hot-topics .spotlight-img .container")
	WebElement contactDriverContainer;
	@FindBy(css = ".utility-bar-header #country-selector > a > b +span")
	WebElement activeLocale;

	// By locator xpaths
	By alertshown = By.cssSelector("div#notification.portal-alert.show");
	By alertExpanded = By.cssSelector("p.collapsed");

	// Dynamic xpath to select product name
	String productName = "//li[text()='#']";
	String country = "//li[@class='dropdown country-selector open']//li[@id='#']";
	String flag = " //*[@id='utility-bar-header']//b[@class='flag #']";

	// String searchTerm =
	// "//p[contains(text(),'#')]/strong[contains(text(),'#')]";

	// ----------------------------------------------------------------------------------------------------------

	@Step("Verify localized flag is shown")
	public boolean verifyLocalizedFlag(String locale) {
		logger.info("Verify localized flag is shown");
		return this.isDynamicElementPresent(flag, locale, 30);

	}

	@Step("Get text from active locale")
	public String getTextFromActiveLocale() {
		logger.info("Get text from active locale");
		this.verifyPageIsLoaded();
		this.waitForElementToBeVisible(activeLocale, 30);
		return this.getText(activeLocale);
	}

	@Step("Get text from account block")
	public String getTextFromAccountBlock() {
		logger.info("Get text from account block");
		this.waitForElementToBeVisible(accountBlock, 30);
		return this.getText(accountBlock);
	}

	@Step("Get text from games block")
	public String getTextFromGamesBlock() {
		logger.info("Get text from games block");
		this.waitForElementToBeVisible(gamesBlock, 30);
		return this.getText(gamesBlock);
	}

	@Step("Get text from signup link")
	public String getTextFromSingUpLink() {
		logger.info("Get text from signup link");
		this.waitForElementToBeVisible(signup, 30);
		return this.getText(signup);
	}

	@Step("Verify contact driver container on homepage")
	public boolean isContactDriverContainerPresent() {
		logger.info("Verify contact driver container on homepage");
		return this.waitForElementToBeVisible(contactDriverContainer, 3);
	}

	@Step("Verify contact driver image on homepage")
	public boolean isContactDriverImagePresent() {
		logger.info("Verify contact driver image on homepage");
		return this.waitForElementToBeVisible(contactDriver, 3);
	}

	@Step("Verify origin link on homepage")
	public boolean isOriginLogPresent() {
		logger.info("Verify origin link on homepage");
		return this.waitForElementToBeVisible(originLogo, 3);
	}

	@Step("Verify singup link on homepage")
	public boolean isGoodNewsPopUpPresent(String email) {
		logger.info("Verify singup link on homepage");
		this.click(signup);
		this.sendKeys(emailTextBox, email);
		this.click(nextBtn);
		return this.waitForElementToBeVisible(signUpDuplicateEmail, 30);
	}

	@Step("Verify singup link on homepage")
	public boolean isSingUpLinkPresent() {
		logger.info("Verify singup link on homepage");
		return this.waitForElementToBeVisible(signup, 30);
	}

	@Step("Click on helpcenter logo")
	public void clickOnLogoImage() {
		logger.info("Click on helpcenter logo");
		this.click(logo);
	}

	@Step("Get meta description")
	public String getMeteDescription() {
		logger.info("Get meta description");
		return this.getAttributeValue(metaDesc, "content").trim();
	}

	@Step("is country selector present")
	public boolean isCountrySelectorPresent() {
		logger.info("is country selector present");
		boolean isTrue = false;
		try {
			isTrue = this.waitForElementToBeVisible(countryDropDown, 30);
		} catch (Exception e) {
			throw e;
		}
		return isTrue;
	}

	@Step("Click on country selector")
	public void clickOnCountrySelector() {
		logger.info("Click on country selector");
		try {
			this.waitForElementToBeVisible(countryDropDown, 30);
			this.click(countryDropDown);
		} catch (Exception e) {
			throw e;
		}
	}

	@Step("Click on country selector")
	public void selectCountry(String sCountry) throws Exception {
		logger.info("Click on country selector");
		try {
			this.verifyPageIsLoaded();
			// Thread.sleep(3000);
			this.clickOnDynamicElement(country, sCountry);
			this.verifyPageIsLoaded();
		} catch (Exception e) {
			throw e;
		}
	}

	@Step("Verify AHQ banner is present")
	public boolean isAHQSectionPresent() {
		logger.info("Verify AHQ banner is present");
		return this.waitForElementToBeVisible(ahqButton, 30);
	}

	@Step("Click on AHQ banner")
	public void clickOnAHQBanner() {
		logger.info("Click on AHQ banner");
		try {
			this.waitForElementToBeVisible(ahqButton, 30);
			this.click(ahqButton);
		} catch (Exception e) {
			throw e;
		}
	}

	@Step("Verify Contact us button is present in global header")
	public boolean isContactUsButtonPresent() {
		logger.info("Verify Contact us button is present in global header");
		return this.isElementVisible(contactUsButton, 10);
	}

	@Step("Click on Contact us button is present in global header")
	public void clickOnContactUsButton() {
		logger.info("Click on Contact us button is present in global header");
		this.click(contactUsButton);
	}

	@Step("Verify content direction on Arabic locale")
	public String getContentDirection() {
		logger.info("Verify content direction on Arabic locale");
		return this.getCSSValue(arContentAlignment, "direction");
	}

	@Step("Get username from logged in view ")
	public String getUserName() {
		logger.info("Get username from logged in view ");
		return this.getText(userName);
	}

	@Step("Search any word using global search ")
	public void globalSearch(String searchTerm) {
		logger.info("Search any word using global search ");
		this.verifyPageIsLoaded();
		this.sendKeys(globalSearch, searchTerm);
		this.pressEnterKey(globalSearch);
	}

	/*
	 * @Step("Verify searh item is shown in dropdown list") public boolean
	 * verifySearchTermIsShownInList(String txtReplace){ return
	 * this.waitForDynamicElementToBeVisible(searchTerm, txtReplace, 10); }
	 */

	@Step("Click on my case link from accounts menu")
	public void clickOnMyCaseLinkFromAccountsMenu() throws InterruptedException {
		logger.info("Click on my case link from accounts menu");
		this.verifyPageIsLoaded();
		this.click(accountsTab);
		Thread.sleep(1000);
		this.click(mycaseLink);
		this.verifyPageIsLoaded();
	}

	@Step("Verify configured products are present in Games list")
	public boolean verifyConfiguredProductsArePresent(String txtReplace) {
		logger.info("Verify configured products are present in Games list");
		this.sendKeys(gamesSearch, txtReplace);
		return this.isDynamicElementPresent(productName, txtReplace, 10);
	}

	@Step("Click on Games tab")
	public void clickOnGamesTab() {
		logger.info("Click on Games tab");
		this.click(gamesTab);
	}

	@Step("Search product in Games list")
	public void searchProductsFromGamesList(String txtReplace) {
		logger.info("Verify products are present in Games list");
		this.sendKeys(gamesSearch, txtReplace);
		this.clickOnDynamicElement(productName, txtReplace);
	}

	@Step("Click on survery link")
	public void clickOnSurveyLink() {
		logger.info("Click on survery link");
		if (!this.isElementVisible(surveyBanner, 30)) {
			this.refreshPage();
			this.verifyPageIsLoaded();
		}
		this.click(takeSurveyLink);
	}

	@Step("Verify alert is shown on home page")
	public boolean isAlertShownOnHomePage() {
		logger.info("Verify alert is shown on home page");
		return this.isElementPresent(alertshown, 10);
	}

	@Step("Verify alert is expanded by default")
	public boolean isAlertExpandedOnHomePage() {
		logger.info("Verify alert is expanded by default");
		return this.isElementPresent(alertExpanded, 10);
	}

	@Step("Click on Alert link and Verify")
	public String clickOnAlertLink() throws InterruptedException {
		logger.info("Click on Alert link and Verify");
		Thread.sleep(2000);
		this.verifyPageIsLoaded();
		this.click(alertLink);
		Thread.sleep(2000);
		this.waitForElementToBeVisible(accountBlock, 30);
		this.verifyPageIsLoaded();
		return this.getCurrentPageURL();
	}

	@Step("Verify alert is opened in new tab")
	public String verifyAlertIsOpenedNewTab(String pageTitle) throws InterruptedException {
		logger.info("Verify alert is opened in new tab");
		Thread.sleep(2000);
		this.verifyPageIsLoaded();
		this.click(alertLink);
		Thread.sleep(2000);
		this.waitForElementToBeVisible(accountBlock, 30);
		this.verifyPageIsLoaded();
		this.switchWindowByTitle(pageTitle);
		return this.getCurrentPageURL();
	}

	@Step("Verify current page title")
	public String verifyCurrentPageTitle() {
		logger.info("Verify current page title");
		return this.getPageTitle();
	}

	// Verify accout block is visible
	public boolean isAccountBlockVisible() {
		boolean isVisible = false;
		try {
			logger.info("Verifying the account block on successful login");
			this.waitForElementToBeVisible(accountBlock, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			isVisible = true;
		} catch (Exception e) {
			logger.info("Failed to verify account block");
			isVisible = false;
		}
		return isVisible;
	}

	public boolean isLogOutVisible() {
		boolean isVisible = false;
		try {
			logger.info("Verifying that Logout section is visible");
			this.waitForElementToBeVisible(btnUserName, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			this.click(btnUserName);
			this.waitForElementToBeVisible(bntLogout, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			isVisible = false;
		} catch (NoSuchElementException e) {
			logger.info("Failed to find the logout section");
			isVisible = false;
		} catch (TimeoutException e) {
			logger.info("Failed to find the logout section");
			isVisible = false;
		}
		return isVisible;
	}

	// Click on game library and wait for products to display
	public void clickOnGamesLibrary() {
		try {
			logger.info("Select a product...  ");
			this.verifyPageIsLoaded();
			Thread.sleep(8000);
			this.waitForClickableElement(15, gamesBlock);
			this.click(gamesBlock);
			// Thread.sleep(2000);
			this.waitForElementToBeVisible(rightNavigation, 15);
		} catch (Exception e) {
			logger.warn("Failed to find products  " + e.getMessage());
		}
	}

}
