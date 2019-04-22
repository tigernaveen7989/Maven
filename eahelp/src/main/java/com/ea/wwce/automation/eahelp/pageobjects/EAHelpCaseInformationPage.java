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
 * @author sadabala
 *
 */

public class EAHelpCaseInformationPage extends EAHelpBasePageObject {

	public EAHelpCaseInformationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpCaseInformationPage.class);

	// Find element using page factory
	@FindBy(name = "create-new-case")
	WebElement createNewCaseButton;
	@FindBy(css = "#requireContact")
	WebElement selectContactOption;
	@FindBy(css = ".icrinterdiction")
	WebElement icrinterdictionMessage;

	By platfromSelected = By.cssSelector(".platformContainer.selected");
	By productSelected = By.cssSelector(".product-container.selected");
	By categorySelected = By.cssSelector(".topic-container");

	// Dynamic locators
	String platform = "//wwce-ea-tile[@title='#']";
	String category = "//a[@value='#']";

	// --------------------------------------------------------------------------------------------------------

	@Step("Get paltform value from caseinformation")
	public boolean isSelectedPlatformPresent() {
		logger.info("Select platform from case information page");
		return this.isElementPresent(platfromSelected, 10);
	}

	@Step("Get Product value from caseinformation")
	public boolean isSelectedProductPresent() {
		logger.info("Get value for selected product from case information page");
		return this.isElementPresent(productSelected, 10);
	}

	@Step("Get category value from caseinformation")
	public boolean isSelectedCategoryPresent() {
		logger.info("Get value for selected category from case information page");
		return this.isElementPresent(categorySelected, 10);
	}

	@Step("Verify ICR interdiction Message present")
	public boolean isInterdictionMessagePresent() {
		logger.info("Verify ICR interdiction message present");
		return this.isElementVisible(icrinterdictionMessage, 10);
	}

	@Step("Verify category is present")
	public boolean isCategoryPresent(String txtReplace) {
		boolean isTrue = false;
		logger.info("Find product mapped category is present");
		try {
			isTrue = this.isDynamicElementPresent(category, txtReplace, 10);
			logger.info("Find product mapped category is present");
		} catch (Exception e) {
			logger.warn("Error finding product mapped category");
		}
		return isTrue;

	}

	@Step("Check that New Case Button is available")
	public boolean iscreateNewCaseButtonVisible() {
		boolean isVisible = false;
		try {
			Thread.sleep(3000);
			// this.moveToElement(createNewCaseButton);
			this.waitForClickableElement(20, createNewCaseButton);
			isVisible = true;
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find the element " + e);
			isVisible = false;
		} catch (TimeoutException e) {
			logger.warn("Failed due to timeout exception " + e);
			isVisible = false;
		} catch (InterruptedException e) {
			logger.warn("Failed due to Interrupted exception " + e);
			isVisible = false;
		}
		return isVisible;
	}

	@Step("Click on Create New Case button")
	public void clickOnCreateNewCaseButton() {

		try {
			if (iscreateNewCaseButtonVisible()) {
				this.click(createNewCaseButton);
			} else {
				logger.warn("Failed to find element");
			}
		} catch (NoSuchElementException e) {
			logger.warn("Failed to click on element" + e.getMessage());

		} catch (TimeoutException e) {
			logger.warn("Failed due to timeout exception");
		}

	}

	@Step("Select platform from Case Information Page")
	public void selectPlatform(String platformName) {
		try {
			Thread.sleep(2000);
			if (this.waitForDynamicElementToBeVisible(platform, platformName, 6)) {
				this.clickOnDynamicElement(platform, platformName);
			}

		} catch (NoSuchElementException e) {
			logger.warn("Failed to select an element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed due to timeout exception");
		} catch (InterruptedException e) {
			logger.warn("Failed due to InterruptedException exception");
		}

	}

	@Step("Select Category from Case Information Page")
	public void selectCategory(String categoryName) {
		try {
			Thread.sleep(3000);
			this.waitForDynamicElementToBeVisible(category, categoryName, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			this.clickOnDynamicElement(category, categoryName);

		} catch (NoSuchElementException e) {
			logger.warn("Failed to select an element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed due to timeout exception");
		} catch (InterruptedException e) {
			logger.warn("Failed due to InterruptedException exception");
		}

	}

	// Select sub category
	public void selectSubCategory(String subCategory) {
		try {
			Thread.sleep(3000);
			this.waitForDynamicElementToBeVisible(category, subCategory, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			this.clickOnDynamicElement(category, subCategory);

		} catch (NoSuchElementException e) {
			logger.warn("Failed to select an element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed due to timeout exception");
		} catch (InterruptedException e) {
			logger.warn("Failed due to InterruptedException exception");
		}
	}

	// Select sub category
	public boolean isSubCategoryPresent(String subCategory) {
		return this.waitForDynamicElementToBeVisible(category, subCategory, EAHELPDataConstants.IMPLICIT_TIMEOUT);
	}
	
	
	

	// Click on select contact option button
	public void clickOnSelectContactOption() {
		try {
			Thread.sleep(3000);
			this.waitForClickableElement(EAHELPDataConstants.IMPLICIT_TIMEOUT, selectContactOption);
			this.click(selectContactOption);
			this.verifyPageIsLoaded();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed to find element due to time out" + e.getMessage());
		} catch (InterruptedException e) {
			logger.warn("Failed due to InterruptedException exception");
		}
	}

}
