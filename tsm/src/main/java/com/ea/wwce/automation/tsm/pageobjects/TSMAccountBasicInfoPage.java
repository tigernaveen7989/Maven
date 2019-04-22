package com.ea.wwce.automation.tsm.pageobjects;

import java.util.Iterator;
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
 * @description This class consists of Page objects and Functions of Account
 *              Basic info in TSM application.
 */

public class TSMAccountBasicInfoPage extends TSMBasePageObject {

	public TSMAccountBasicInfoPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMAccountBasicInfoPage.class);

	@FindBy(css = "[title='Search Cases']")
	WebElement searchCase;
	@FindBy(xpath = "//li[@title='ACCOUNT']")
	WebElement accountTab;
	@FindBy(css = "li[title='Basic Info']")
	WebElement basicInfoTab;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[text()='First Name']/following-sibling::div")
	WebElement firstName;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[text()='Last Name']/following-sibling::div")
	WebElement lastName;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[text()='Country']/following-sibling::div")
	WebElement country;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[contains(text(),'Birth')]/following-sibling::div")
	WebElement dateofBirth;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[contains(text(),'since')]/following-sibling::div")
	WebElement joinEAsince;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[text()='Persona']/following-sibling::div")
	WebElement persona;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[text()='NUCLEUS ID']/following-sibling::div")
	WebElement nucleusID;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[contains(text(),'Primary')]/following-sibling::div")
	WebElement primaryEmail;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[contains(text(),'Secondary')]/following-sibling::div")
	WebElement secondaryEmail;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[contains(text(),'Phone')]/following-sibling::div")
	WebElement phoneNumber;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[contains(text(),'Customer')]/following-sibling::div")
	WebElement customervalue;
	@FindBy(xpath = "//div[contains(@class, 'content')]//div[text()='Language']/following-sibling::div")
	WebElement language;
	@FindBy(xpath = "//ul[@class='tabBarItems slds-grid']//li[contains(@class, 'oneConsoleTabItem')]//span[@class='title slds-truncate']")
	WebElement list;
	@FindAll(@FindBy(xpath = "//ul[@class='tabBarItems slds-grid']//li[contains(@class, 'oneConsoleTabItem')]//span[@class='title slds-truncate']"))
	List<WebElement> totalAttachments;
	@FindBy(xpath = "//div[contains(@class,'cAccountBasicInfo')]//lightning-spinner[contains(@class,'slds-spinner')]")
	WebElement spinner;
	@FindBy(xpath = "//lightning-spinner[@class='slds-show slds-spinner_container']")
	WebElement accounttabSpinner;
	@FindBy(xpath = "//div[text()='Account Info']")
	WebElement accountinfo;
	@FindBy(xpath = "//button[contains(@class,'neutral') and text()='Edit']")
	WebElement editAccountInfo;
	@FindBy(xpath = "//input[@name='firstName']")
	WebElement editFirstName;
	@FindBy(xpath = "//input[@name='lastName']")
	WebElement editLastName;
	@FindBy(xpath = "//input[@name='persona']")
	WebElement editPersona;
	@FindBy(xpath = "//input[@name='email']")
	WebElement eidtprimaryEmail;
	@FindBy(xpath = "(//input[@name='parentalEmail'])[1]")
	WebElement eidtsecondaryEmail;
	@FindBy(xpath = "//input[@name='dob']")
	WebElement editDOB;
	@FindBy(xpath = "//span[text()='26']")
	WebElement selectDOB;
	@FindBy(xpath = "//input[@name='country']")
	WebElement editCountry;
	@FindBy(xpath = "//lightning-base-combobox-formatted-text[@title='India']")
	WebElement selectCountry;
	@FindBy(xpath = "//input[@name='language']")
	WebElement editlanguage;
	@FindBy(xpath = "//lightning-base-combobox-formatted-text[@title='English']")
	WebElement selectLanguage;
	@FindBy(xpath = "//input[@name='phone']")
	WebElement editPhoneNumber;
	@FindBy(xpath = "//input[@name='customerValue']")
	WebElement editCustomervalue;
	@FindBy(xpath = "//lightning-base-combobox-formatted-text[@title='None']")
	WebElement selectCustomerValue;
	@FindBy(xpath = "(//input[@name='parentalEmail'])[2]")
	WebElement editParentalEmail;
	@FindBy(xpath = "//button[text()='Save']")
	WebElement save;
	@FindBy(xpath = "//div[contains(@class,'cAccountBasicInfo')]//lightning-spinner[@class='slds-spinner_container']")
	WebElement basicInfoSpinner;
	@FindBy(xpath = "//button[text()= 'Set Temporary Password']")
	WebElement clkSetTempPassword;
	@FindBy(xpath = "//input[@name='temporaryPassword']")
	WebElement enterTempPassword;
	@FindBy(xpath = "//button[text()='Submit']")
	WebElement submit;
	@FindBy(xpath = "//div[@data-aura-class='forceSearchInputEntitySelector']")
	WebElement searchDropdown;
	@FindBy(xpath = "(//lightning-base-combobox-formatted-text[@title='Cases'])[1]")
	WebElement selectcases;
	
	
	
	

	String clkOnCase = "//a[@role='option']//div[@class='slds-icon_container']//following-sibling::div//span[@title='#']";
	String closeCase = "//button[@class='slds-button slds-button_icon-x-small slds-button_icon-container' and @title='Close #']";
	String casenumber = "//a[contains(@class,'label-action')]//span[text()='#']";

	// Search for caseid
	@Step("Search Case ID in TSM")
	public boolean searchCaseId(String CaseNumber) {
		logger.info("Search Case Number");
		boolean isElementPresent = false;
		try {
			Thread.sleep(5000);
			//this.acceptAlert();
			this.waitForElementToBeVisible(searchDropdown, 5);
			this.click(searchDropdown);
			this.waitForElementToBeVisible(selectcases, 5);
			this.click(selectcases);
			this.waitForElementToBeVisible(searchCase, 5);
			this.click(searchCase);
			this.sendKeys(searchCase, CaseNumber);
			this.click(searchCase);
			Thread.sleep(2000);
			this.waitForDynamicElementToBeVisible(clkOnCase, CaseNumber, 10);
			if(this.isDynamicElementPresent(clkOnCase, CaseNumber, 5))
			{
			this.clickOnDynamicElement(clkOnCase, CaseNumber);
			}
			else
			{
				this.click(searchCase);
				this.sendKeys(searchCase, CaseNumber);
				this.click(searchCase);
				this.clickOnDynamicElement(clkOnCase, CaseNumber);
			}
			isElementPresent = this.isDynamicElementPresent(casenumber, CaseNumber, 10);
			
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Search Case Number failed" + e);
		}
		return isElementPresent;
	}

	@Step("Click on Account tab")
	public void clkOnAccount() {
		logger.info("Click on Account tab");
		try {
			Thread.sleep(2000);
			this.waitForClickableElement(60, accountTab);
			// waitForElementToBeC(accountTab, 60);
			this.click(accountTab);

			this.waitForElementToBeInVisible(accounttabSpinner, 20);
			// Thread.sleep(2000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click On Account Tab" + e);
		}
	}

	@Step("Verify Account Basic info")
	public boolean verifyAccountBasicInfoPage(String txtFirstName, String txtLastName, String txtCountry,
			String txtDateOfBirth, String txtPersona, String txtNucleusID, String txtPrimaryEmail,
			String txtphonenumber, String txtlanguage, String txtCustomervalue) {
		boolean isTrue = false;
		logger.info("Verify Account basic info details in tsm");
		try {
			// this.waitForClickableElement(30, basicInfoTab);
			// isSpinnerInvisible(20);
			this.waitForElementToBeInVisible(spinner, 15);
			// this.waitForClickableElement(30, accountinfo);
			// Thread.sleep(3000);
			if ((this.getText(firstName).equalsIgnoreCase(txtFirstName))
					&& (this.getText(lastName).equalsIgnoreCase(txtLastName))
					&& (this.getText(country).equalsIgnoreCase(txtCountry))
					&& (this.getText(dateofBirth).equalsIgnoreCase(txtDateOfBirth))
					// &&(this.getText(JoinEAsince).equalsIgnoreCase(txtJoinEAsince))
					&& (this.getText(persona).equalsIgnoreCase(txtPersona))
					&& (this.getText(nucleusID).equalsIgnoreCase(txtNucleusID))
					&& (this.getText(primaryEmail).equalsIgnoreCase(txtPrimaryEmail))
					// &&
					// (this.getText(secondaryEmail).equalsIgnoreCase(txtSecondaryEmail))
					&& (this.getText(phoneNumber).equalsIgnoreCase(txtphonenumber))
					&& (this.getText(language).equalsIgnoreCase(txtlanguage))
					&& (this.getText(customervalue).equalsIgnoreCase(txtCustomervalue)))
				isTrue = true;
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able to find Account basic info in TSM" + e);
		}
		logger.info("All account details matched : " + isTrue);
		return isTrue;
	}

	// Close case
	public void closeCase(String CaseNumber) {
		logger.info("Click on Close icon of the case");
		try {
			this.clickOnDynamicElement(closeCase, CaseNumber);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click Close icon of the case" + e);
		}
	}

	// Close existing cases in application
	@Step("Close existing cases tab")
	public void closeexistingcases() {
		String casenumbers = null;
		logger.info("closing existing tabs");
		try {
			// this.waitForElementToBeVisible(accountTab, 2);
			Iterator<WebElement> iterator = totalAttachments.iterator();
			while (iterator.hasNext()) {
				casenumbers = iterator.next().getText();
				this.clickOnDynamicElement(closeCase, casenumbers);
			}
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to click on close case" + e);
		}
	}

	// Edit Account basic Info
	@Step("Edit Account basic Info")
	public void editbasicInfo(String firstName, String lastName, String dob, String Country, String language,
			String primaryEmail, String secondaryEmail, String phoneNumber, String CustomerValue) {
		logger.info("Edit Account basic Info");
		try {
			this.waitForElementToBeInVisible(basicInfoSpinner, 15);
			// this.clickUsingJavaScriptExecutor(editAccountInfo, 20);
			this.click(editAccountInfo);
			// this.waitForClickableElement(10, editFirstName);
			// this.enterValueUsingJavaScriptExecutor(editFirstName, firstName,
			// 20);
			this.clearTextbox(editFirstName);
			this.sendKeys(editFirstName, firstName);
			// this.enterValueUsingJavaScriptExecutor(editLastName, lastName,
			// 20);
			this.clearTextbox(editLastName);
			this.sendKeys(editLastName, lastName);
			// this.enterValueUsingJavaScriptExecutor(editPersona, persona, 20);
			this.click(editDOB);
			this.click(selectDOB);
			this.click(editCountry);
			this.click(selectCountry);
			this.click(editlanguage);
			this.click(selectLanguage);
			// this.enterValueUsingJavaScriptExecutor(eidtprimaryEmail,
			// primaryEmail, 20);
			this.clearTextbox(eidtprimaryEmail);
			this.sendKeys(eidtprimaryEmail, primaryEmail);
			this.clearTextbox(eidtsecondaryEmail);
			this.sendKeys(eidtsecondaryEmail, secondaryEmail);
			// this.enterValueUsingJavaScriptExecutor(eidtsecondaryEmail,
			// secondaryEmail, 20);
			this.clearTextbox(editPhoneNumber);
			this.sendKeys(editPhoneNumber, phoneNumber);
			// this.enterValueUsingJavaScriptExecutor(editPhoneNumber,
			// phoneNumber, 20);
			this.click(editCustomervalue);
			this.click(selectCustomerValue);
			this.click(save);
			// this.sendKeys(editCustomervalue, CustomerValue);
			this.waitForElementToBeInVisible(basicInfoSpinner, 20);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Edit Account Bacis info" + e);
		}
	}

	// Set Temporary Password
	public void setTempPassword(String temppassword) {
		logger.info("Set Temporary password");
		try {
			this.waitForElementToBeInVisible(basicInfoSpinner, 7);
			this.click(clkSetTempPassword);
			this.waitForElementToBeVisible(enterTempPassword, 5);
			this.sendKeys(enterTempPassword, temppassword);
			this.click(submit);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to Set Temporary password" + e);
		}
	}

}
