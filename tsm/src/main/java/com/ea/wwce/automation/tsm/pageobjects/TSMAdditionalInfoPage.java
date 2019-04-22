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
 * @description This class consists of Page objects and Functions of Additional info page in TSM application.
 * 
 */

public class TSMAdditionalInfoPage extends TSMBasePageObject {

	public TSMAdditionalInfoPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMAdditionalInfoPage.class);

	@FindBy(css = "[title='Additional Info']")
	WebElement additionalInfoTab;
	@FindBy(xpath = "//div[text()='Game Stats']")
	WebElement gameStats;
	@FindBy(xpath = "//lightning-primitive-cell-checkbox/span/label/span")
	WebElement selectCheckbox;
	@FindBy(xpath = "//div[contains(@class,'pointer')]//button[contains(@class,'slds-button slds-button')]")
	WebElement personaDropdown;
	@FindBy(xpath = "//table[contains(@class,'edit')]//button[contains(@class,'x-small')]")
	WebElement associatedClubsDropdown;
	@FindBy(xpath = "//span[text()='Deactivate']")
	WebElement deActivatePersona;
	@FindBy(xpath = "//span[text()='Activate']")
	WebElement activatePersona;
	@FindBy(xpath = "//span[text()='Change Club Name']")
	WebElement changeClubName;
	@FindBy(xpath = "//label[text()='Club Name']/parent::lightning-input//input")
	WebElement updateClubName;
	@FindBy(css = "[title='Save']")
	WebElement saveClubName;
	@FindBy(xpath = "//th[@data-label='CLUB NAME']//lightning-formatted-text")
	WebElement clubName;
	@FindBy(xpath = "//div[contains(@class,'cProductTab')]//lightning-spinner[@class='slds-spinner_container slds-hide']")
	WebElement spinner;
	@FindBy(xpath = "//button[text()='Reset Club Limit']")
	WebElement resetClubLimit;
	@FindBy(css = "[placeholder='Select reason']")
	WebElement selectReasonDropdown;
	@FindBy(css = "[data-value='SCAMMING_OTHER_USERS']")
	WebElement deactivateReason;
	@FindBy(css = "[data-value='FALSE_POSITIVE']")
	WebElement activateReason;
	@FindBy(css = "[title='Deactivate']")
	WebElement deactivateButton;
	@FindBy(css = "[title='Activate']")
	WebElement activateButton;
	@FindBy(xpath = "//span[text()='inactive']")
	WebElement inactive;
	@FindBy(xpath = "//span[text()='active']")
	WebElement active;
	@FindBy(xpath = "//button[text()='Reset']")
	WebElement resetButton;
	@FindBy(xpath = "//div[contains(@class,'toastContainer')]//div[@data-key='success']")
	WebElement successMessage;
	

	String gamestatsFields = "//div[text()='#']";
	String personaFields = "//span[text()='#']";
	String associatedclubFields = "//span[text()='#']";

	// Click on Additional Info Tab
	@Step("Click on Additional Info Tab")
	public void clickonAdditionalInfoTab() {
		logger.info("Click on Additional Info Tab");
		try {
			Thread.sleep(2000);
			this.waitForClickableElement(20, additionalInfoTab);
			this.click(additionalInfoTab);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on Additional Info Tab" + e);
		}
	}

	// Verify fields in gamestats
	//@Step("Verify fields in game stats")
	public boolean areGameStatsFieldsAvailable(String gameStatsfifa19Data) {

		boolean isAllElementsPresent = true;
		logger.info("verify Game Stats");
		try {
			Thread.sleep(2000);
			boolean isElementPresent = false;
			for (String gameField : gameStatsfifa19Data.split("@")) {
				isElementPresent = this.isDynamicElementPresent(gamestatsFields, gameField, 2);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + gameField);
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
			logger.warn("Game Stats fields not found" + e);
		}
		return isAllElementsPresent;
	}

	// Verify fields in Persona and Associated Clubs
	//@Step("Verify fields in Persona")
	public boolean arePersonaFieldsAvailable(String personafifa19Data) {

		boolean isAllElementsPresent = true;
		logger.info("verify Persona fields");
		try {
			boolean isElementPresent = false;
			for (String personaField : personafifa19Data.split("@")) {
				isElementPresent = this.isDynamicElementPresent(personaFields, personaField, 2);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + personaField);
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
			logger.warn("Persona fields not found" + e);
		}
		return isAllElementsPresent;
	}

	// Verify fields in Persona and Associated Clubs
	@Step("Verify fields in Associated Clubs")
	public boolean areAssociatedClubFieldsAvailable(String associatedclubsfifa19Data) {
		boolean isAllElementsPresent = true;
		logger.info("verify Game Stats");
		try {
			boolean isElementPresent = false;
			for (String associatedclubfield : associatedclubsfifa19Data.split("@")) {
				isElementPresent = this.isDynamicElementPresent(associatedclubFields, associatedclubfield, 2);
				if (!isElementPresent) {
					isAllElementsPresent = false;
					logger.error("Dynamic element not found :" + associatedclubfield);
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
			logger.warn("Associated clubs fields not found" + e);
		}
		return isAllElementsPresent;
	}

	// Verify deactivate Persona
	@Step("Verify DeActivate Persona")
	public String deActivatePersona(String status) {
		logger.info("Verify DeActivate Persona");
		try {
			Thread.sleep(2000);
			this.moveToElement(resetClubLimit);
			this.waitForClickableElement(20, personaDropdown);
			this.click(personaDropdown);
			this.click(deActivatePersona);
			this.waitForElementToBeVisible(selectReasonDropdown, 3);
			this.click(selectReasonDropdown);
			this.waitForElementToBeVisible(deactivateReason, 5);
			this.click(deactivateReason);
			Thread.sleep(2000);
			this.click(deactivateButton);
			this.waitForElementToBeVisible(spinner, 5);
			this.waitForElementToBeVisible(inactive, 5);
			status = this.getText(inactive);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to deactivate Persona" + e);
		}
		return status;
	}

	// Verify Activate Persona
	@Step("Verify Activate Persona")
	public String activatePersona(String status) {
		logger.info("Verify Activate Persona");
		try {
			this.waitForElementToBeVisible(resetClubLimit, 3);
			this.moveToElement(resetClubLimit);
			this.waitForClickableElement(20, personaDropdown);
			this.click(personaDropdown);
			this.click(activatePersona);
			this.waitForElementToBeVisible(selectReasonDropdown, 3);
			this.click(selectReasonDropdown);
			this.waitForElementToBeVisible(activateReason, 5);
			this.click(activateReason);
			Thread.sleep(2000);
			this.click(activateButton);
			this.waitForElementToBeVisible(spinner, 5);
			this.waitForElementToBeVisible(active, 5);
			status = this.getText(active);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Activate Persona" + e);
		}
		return status;
	}

	// Verify Change Club Name
	@Step("Verify Change Club Name")
	public String changeClubName(String txtClubName) {
		logger.info("Verify Change Club Name");
		String clubname = null;
		try {
			this.waitForElementToBeVisible(resetClubLimit, 3);
			this.moveToElement(resetClubLimit);
			this.waitForClickableElement(20, associatedClubsDropdown);
			this.click(associatedClubsDropdown);
			this.click(changeClubName);
			Thread.sleep(2000);
			this.waitForClickableElement(5, updateClubName);
			this.clearTextbox(updateClubName);
			this.sendKeys(updateClubName, txtClubName);
			this.click(saveClubName);
			this.waitForElementToBeVisible(spinner, 10);
			this.waitForElementToBeVisible(clubName, 5);
			clubname = this.getText(clubName);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Change Club name" + e);
		}
		return clubname;
	}

	// Verify Restore Club
	//@Step("Verify Restore Club")
	public boolean isClubLimitRestored() {
		logger.info("Verify Restore Club");
		boolean isElementPresent = false;
		try {
			Thread.sleep(2000);
			this.moveToElement(resetClubLimit);
			this.waitForClickableElement(5, resetClubLimit);
			//Thread.sleep(4000);
			this.click(resetClubLimit);
			this.waitForElementToBeVisible(resetButton, 5);
			this.click(resetButton);
		isElementPresent = 	this.isElementVisible(successMessage, 5);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on Additional Info Tab" + e);
		}
		return isElementPresent;
	}

}
