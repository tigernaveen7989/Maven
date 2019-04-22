package com.ea.wwce.automation.tsm.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
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
 * @description This class consists of Account change history Page
 *              objects and Functions.
 */

public class TSMAccountChangeHistoryPage extends TSMBasePageObject {

	public TSMAccountChangeHistoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMAccountChangeHistoryPage.class);

	@FindBy(xpath = "//li[@title='Sessions']")
	WebElement sessions;
	@FindBy(xpath = "//div[@class='uiOutputRichText' and contains(text(),'Successful login')]")
	WebElement succesfullLogin;
	@FindBy(xpath = "//div[@class='slds-tabs_scoped__content slds-show']//div[contains(@class,'AccountChangeHistory')]//input[contains(@class,'slds-lookup__search')]")
	WebElement search;
	@FindBy(xpath = "(//div[@class='slds-tabs_scoped__content slds-show']//div[@class='slds-is-relative cAccountChangeHistory']//strong)[2]")
	WebElement sessionsname;
	@FindBy(xpath = "(//li[@data-label='ACCOUNT']/parent::ul/parent::lightning-tab-bar//following-sibling::div[@class='slds-tabs_scoped__content slds-show']//div[@class='slds-is-relative cAccountChangeHistory']//strong)[2]")
	WebElement overviewname;
	@FindBy(xpath = "//a[text()='OVERVIEW']")
	WebElement overview;
	@FindBy(xpath = "(//li[@data-label='ACCOUNT']/parent::ul/parent::lightning-tab-bar//following-sibling::div[contains(@class,'content slds-show')]//div[contains(@class,'cAccountChangeHistory')]//div[contains(@class,'regularSuccessful')]//div[@class='uiOutputRichText'])[1]")
	WebElement successfulOverview;
	@FindBy(xpath = "(//li[@data-label='ACCOUNT']/parent::ul/parent::lightning-tab-bar//following-sibling::div[contains(@class,'content slds-show')]//div[contains(@class,'cAccountChangeHistory')]//div[contains(@class,'regularSuccessful')]//div[@class='uiOutputRichText'])[1]")
	WebElement successful;
	String clkOnCase = "//a[@role='option']//div[@class='slds-icon_container']//following-sibling::div//span[@title='#']";
	String closeCase = "//button[@class='slds-button slds-button_icon-x-small slds-button_icon-container' and @title='Close #']";
	@FindBy(xpath = "(//div[contains(@class, 'slds-is-relative cAccountChangeHistory')]//div[@class='spinnerBlock slds-hide'])[1]")
	WebElement spinner;

	// Search for caseid
	@Step("Click on Sessions tab")
	public void clickonSessionsTab() {

		logger.info("Click on Sessions Tab");
		try {
			this.waitForElementToBeVisible(sessions, 5);
			this.click(sessions);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not clicked on Sessions tab" + e);
		}
	}

	@Step("Search name and Verify First name in Sessions")
	public boolean searchandVerifyFNameInSessions(String firstname) {

		boolean isTrue = false;
		String name = "";
		logger.info("Verify Account change History");
		try {
			this.waitForClickableElement(5, sessionsname);
			this.waitForElementToBeVisible(spinner, 10);
			// Thread.sleep(2000);
			this.sendKeys(search, firstname);
			name = this.getText(sessionsname);

			if (name.equals(firstname)) {
				isTrue = true;
			}
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("First name not matched" + e);
		}
		return isTrue;
	}

	@Step("Search name and Verify last name in Sessions")
	public boolean searchandVerifyLNameInSessions(String lastname) {

		boolean isTrue = false;
		String name = "";
		logger.info("Verify Account change History");
		try {
			this.waitForClickableElement(5, sessionsname);
			this.waitForElementToBeVisible(spinner, 10);
			// Thread.sleep(2000);
			this.sendKeys(search, lastname);
			name = this.getText(sessionsname);

			if (name.equals(lastname)) {
				isTrue = true;
			}
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Last name not found" + e);
		}
		return isTrue;
	}

	@Step("Search name and Verify first name name in Overview")
	public boolean searchandVerifyFNameInOverview(String firstname) {

		boolean isTrue = false;
		String name = "";
		logger.info("Verify Account change History");
		try {
			this.waitForClickableElement(2, overviewname);
			this.waitForElementToBeVisible(spinner, 10);
			// Thread.sleep(2000);
			this.sendKeys(search, firstname);
			name = this.getText(overviewname);

			if (name.equals(firstname)) {
				isTrue = true;
			}
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("First name not found" + e);
		}
		return isTrue;
	}

	@Step("Search name and Verify first name name in Overview")
	public boolean searchandVerifyLNameInOverview(String lastname) {

		boolean isTrue = false;
		String name = "";
		logger.info("Verify Account change History");
		try {
			this.waitForClickableElement(2, overviewname);
			this.waitForElementToBeVisible(spinner, 10);
			// Thread.sleep(2000);
			this.sendKeys(search, lastname);
			name = this.getText(overviewname);

			if (name.equals(lastname)) {
				isTrue = true;
			}
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("last name not found" + e);
		}
		return isTrue;
	}

	@Step("Click on Overview")
	public void clickonOverviewTab() {

		logger.info("Click on Overview tab");
		try {
			this.click(overview);
			Thread.sleep(2000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not clicked on Overview tab" + e);
		}
	}

	@Step("Search successfull login in Sessions ")
	public boolean searchSuccessfullLoginInsessions(String login) {

		boolean isTrue = false;
		String name = "";
		logger.info("Verify successfull login");
		try {
			this.waitForElementToBeVisible(spinner, 20);
			this.waitForElementToBeInVisible(successful, 3);
			// Thread.sleep(2000);
			this.sendKeys(search, login);
			name = this.getText(successful);
			if (name.equals(login)) {
				isTrue = true;
			}
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Succesffull login not found" + e);
		}
		return isTrue;
	}

	@Step("Search successfull login in Overview ")
	public boolean searchSuccessfullLoginInOverview(String login) {

		boolean isTrue = false;
		String name = "";
		logger.info("Verify successfull login");
		try {
			this.waitForElementToBeVisible(spinner, 20);
			this.waitForElementToBeVisible(successful, 3);
			// Thread.sleep(2000);
			this.sendKeys(search, login);
			name = this.getText(successful);
			if (name.equals(login)) {
				isTrue = true;
			}
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Succesffull login not found" + e);
		}
		return isTrue;
	}
}
