package com.ea.wwce.automation.eahelp.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

/**
 * 
 * @author sadabla
 *
 */

public class EAHelpProductPage extends EAHelpBasePageObject {

	public EAHelpProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpProductPage.class);

	@FindBy(xpath = "//span[@class='eapl-cta__label style-scope ea-cta']//span")
	WebElement contactUsButton;
	@FindBy(xpath = "//select[@name='category']/following::div[1]/a")
	WebElement categoryDropDown;
	@FindBy(xpath = "//select[@name='platform']/following::div[1]/a")
	WebElement platfromDropDown;
	@FindBy(className = "status-toggle")
	WebElement serverStatus;
	@FindBy(id = "searchFilter")
	WebElement searchTextBox;
	@FindBy(className = "spotlight-img")
	WebElement productBCImage;
	@FindBy(css = "#breadcrumb span")
	WebElement breadCrumb;
	@FindBy(css = "a[href*='community/?']")
	WebElement ahqButton;
	@FindBy(css = ".manuals-toggle-container.split .icon-chevron-down")
	WebElement manualsIcon;
	@FindBy(css = ".manuals.open a[title='SimCity PC (Greek)']")
	WebElement artilce_1;
	@FindBy(css = ".manuals.open a[title='SimCity PC (Arabic)']")
	WebElement artilce_2;
	@FindBy(css = ".manuals.open a[title='SimCity PC']")
	WebElement artilce_3;
	@FindAll(@FindBy(css = ".category-selector li.active-result.item"))
	public List<WebElement> categories;
	@FindBy(css = ".manuals.open .article-list")
	WebElement manuals;
	@FindBy(css = ".no-results")
	WebElement noResults;
	// @FindBy(css=".article-list div.article")
	// WebElement articleList;
	@FindAll(@FindBy(css = ".article-list div.article a bdi"))
	List<WebElement> articleList;

	/*
	 * @FindBy(css=".portal-alert show") WebElement alertshown;
	 */
	// @FindBy(css = ".server-status down show")
	// WebElement serverDownStatus;

	// @FindBy(css="#product li:nth-child('#') span:nth-child(1)")
	// @FindBy(css="li.status.# span:nth-child(1)")
	// WebElement widgetIcon;

	By loader = By.xpath("//div[@class='loader hide' and contains(@style,'display: block;')]");
	By serverDownStatus = By.cssSelector("#product > div.server-status.down.show");
	By alertshown = By.cssSelector("div#notification.portal-alert.show");
	By rolledUpServerStatus = By.cssSelector(".server-status.unknown");

	String categoryPlatformSelection = "//li[contains(text(),'#')]";
	String articleName = "//bdi[contains(text(),'#')]";
	String widgetIcon = "//li[@class='status #']/span";
	String platformStatus = "//li[@class='status #']/span[@class='arrow down']";

	// -------------------------------------------------------------------------------------------------------------------//

	@Description("Verify correct associated articles are displayed on product page")
	public boolean verifyArtilceName(String artcle) {
		boolean isTrue = false;
		if (articleList != null && !articleList.isEmpty()) {
			int totalFiles = this.getSize(articleList);
			logger.info("Total article are shown  " + totalFiles);
			for (WebElement article : articleList) {
				if (article.getText().contains(artcle)) {
					logger.info("Associated articles are displayed");
					isTrue = true;
					break;
				}
			}
		}
		return isTrue;
	}

	@Step("Verify no results page is shown for invalid product")
	public boolean noResultsPage() {
		logger.info("Verify no results page is shown for invalid product");
		this.verifyPageIsLoaded();
		return this.waitForElementToBeVisible(noResults, 60);
	}

	@Step("Verify category dropdown shows categories")
	public int isCategoryContainsEntries() {
		int val = 0;
		logger.info("Verify category dropdown shows categories");
		try {
			val = this.getSize(categories);
		} catch (Exception e) {
			throw e;
		}
		return val;
	}

	@Step("Verify category dropdown contains Expected category")
	public boolean isCategoryContainsEntry(String category) {
		boolean isTrue = false;
		logger.info("Verify category dropdown Expected contains category");
		this.click(categoryDropDown);

		for (WebElement e : categories) {
			e.getText().trim().equals(category);
			isTrue = true;
			break;
		}

		return isTrue;
	}

	@Step("Click on Manuals button")
	public void clickOnManuals() {
		logger.info("Click on Manuals button");
		try {
			this.waitForClickableElement(30, manualsIcon);
			this.click(manualsIcon);
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean verifyArticlesListInManuals() {
		boolean isTrue = false;
		if (this.isElementVisible(artilce_1, 10)) {

			if (this.isElementVisible(artilce_2, 10)) {

				if (this.isElementVisible(artilce_3, 10)) {
					isTrue = true;
				}
			}
		}

		return isTrue;
	}

	public boolean verifyManualsIsDisplayed() {
		logger.info("Verify manuals are displayed");
		return this.isElementVisible(manuals, 30);
	}

	@Step("move to AHQ button")
	public void moveToAHQSection() {
		logger.info("move to AHQ button");
		try {
			this.waitForClickableElement(60, ahqButton);
			this.moveToElement(ahqButton);
		} catch (Exception e) {
			throw e;
		}
	}

	@Step("Verify AHQ button is present")
	public boolean isAHQButtonPresent() {
		logger.info("Click on AHQ button");
		return this.waitForElementToBeVisible(ahqButton, 60);
	}

	@Step("Click on AHQ button")
	public void clickOnAHQButton() {
		logger.info("Click on AHQ button");
		try {
			this.waitForClickableElement(60, ahqButton);
			this.waitForElementToBeVisible(ahqButton, 60);
			this.click(ahqButton);
		} catch (Exception e) {
			throw e;
		}
	}

	@Step("Get text from BreadCrumb")
	public String getTextFromBreadCrumb() {
		logger.info("Get text from BreadCrumb");
		this.verifyPageIsLoaded();
		return this.getText(breadCrumb);
	}

	@Step("Verify alert is shown on product page")
	public boolean isAlertShownOnProductPage() {
		logger.info("Verify alert is shown on product page");
		return this.isElementPresent(alertshown, 10);
	}

	@Step("Verify Select on Contact Us link from Product Page")
	public boolean isContactButtonPresent() {
		return this.waitForElementToBeVisible(contactUsButton, 3);
	}

	@Step("Select on Contact Us link from Product Page")
	public void clickOnContactUsButton() {
		logger.info("Select on Contact Us link from Product Page");
		try {
			this.waitForElementToBeVisible(contactUsButton, 10);
			this.click(contactUsButton);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to click on element" + e.getMessage());
			throw e;
		}
	}

	@Step("verify contactdriver image is present")
	public boolean isContactImagePresent() {
		logger.info("Verify contact image is present");
		//this.waitForClickableElement(10, productBCImage);
		return this.waitForElementToBeVisible(productBCImage, 4);
	}

	// verify top contact driver image
	public String getContactDriverImage(String attributeName) {
		String val = null;
		try {
			logger.info("Get attribute value from a product image...");
			val = this.getAttributeValue(productBCImage, attributeName);
			logger.info("Get attribute value from a product image... " + attributeName + " value" + val);
		} catch (Exception e) {
			logger.warn("Not able to get attribute value from a product image...");
		}
		return val;
	}

	@Step("Verify category dropdown list is present")
	public boolean verifyCategoryDropDownPresent() {
		logger.info("Verify category dropdown list is present");
		return this.waitForElementToBeVisible(categoryDropDown, 30);
	}

	@Step("Select category from category dropdown list")
	public void selectCategory(String txtToReplace) {
		logger.info("Select category from category dropdown list");
		this.waitForClickableElement(30, categoryDropDown);
		this.click(categoryDropDown);
		this.clickOnDynamicElement(categoryPlatformSelection, txtToReplace);
		// this.waitForElementToBeInVisible(loader, 3);
	}

	@Step("Verify platform dropdown list is present")
	public boolean isPlatformDropDownListPresent() {
		logger.info("Verify platform dropdown list is present");
		return this.waitForElementToBeVisible(platfromDropDown, 30);
	}

	@Step("Select category from paltform dropdown list")
	public void selectPlatform(String txtToReplace) {
		logger.info("Select category from category dropdown list");
		try {
			Thread.sleep(3000);
			this.waitForClickableElement(30, platfromDropDown);
			this.click(platfromDropDown);
			this.clickOnDynamicElement(categoryPlatformSelection, txtToReplace);
		} catch (Exception e) {
			logger.warn("unable to select platform" + e.getMessage());
		}

	}

	@Step("Verify artilce is displayed")
	public boolean verfiyArticleName(String txtToReplace) {
		logger.info("Verify artilce is displayed");
		return this.waitForDynamicElementToBeVisible(articleName, txtToReplace, 20);
	}

	@Step("Verify server status is displayed")
	public boolean verfiyServerStatusIsShown() {
		logger.info("Verify server status is displayed");
		return this.isElementVisible(serverStatus, 10);
	}

	@Step("Verify server status color code")
	public String verfiyServerStatus(String propertyName) {
		logger.info("Verify server status color code");
		return this.getCSSValue(serverStatus, propertyName);
	}

	@Step("Verify server status is down")
	public boolean verifyServerStatusIsDown() {
		logger.info("Verify server status is down");
		return this.isElementPresent(serverDownStatus, 10);
	}

	@Step("Verify server status is up")
	public boolean verifyServerStatusIsUp() {
		logger.info("Verify server status is up");
		return this.isElementPresent(rolledUpServerStatus, 10);
	}

	@Step("Verify platform status is down")
	public boolean verifyPlatformStatusIsDown(String txtToReplace) {
		logger.info("Verify platform status is down");
		return this.waitForDynamicElementToBeVisible(platformStatus, txtToReplace, 10);
	}

	@Step("Click on article link")
	public void clickOnArticle(String txtToReplace) {
		logger.info("Click on article link");
		try {
			this.clickOnDynamicElement(articleName, txtToReplace);
			this.verifyPageIsLoaded();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to click on element" + e.getMessage());
			throw e;
		}
	}

	@Step("Verify platform icons from Widget")
	public String verifyPlatformIconsInWidget(String txtToReplace, String propertyName) {
		logger.info("Verify platform icons from Widget");
		return this.getCSSValueFromDynamicElement(widgetIcon, txtToReplace, 10, propertyName);
	}

	@Step("Click on server status")
	public void clickOnServerStatus() {
		logger.info("Click on server status");
		this.click(serverStatus);
	}

	@Step("Search box is presnt")
	public boolean isSearchBoxPresent() {
		logger.info("Search article");
		return this.waitForElementToBeVisible(searchTextBox, 10);
	}

	@Step("Search article")
	public void searchArticle(String articleName) {
		logger.info("Search article");
		this.sendKeys(searchTextBox, articleName);
		this.pressEnterKey(searchTextBox);

	}
}
