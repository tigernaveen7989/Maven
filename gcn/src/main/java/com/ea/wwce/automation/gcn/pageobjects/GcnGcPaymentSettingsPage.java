package com.ea.wwce.automation.gcn.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcnGcPaymentSettingsPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and business methods to update/change the payments settings 
	 * of the game changer, provide tax information.	 * 
	 */
	
	private static final Logger logger = Logger.getLogger(GcnGcPaymentSettingsPage.class);
	
	public GcnGcPaymentSettingsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2[contains(text(),'Payment Method')]")
	WebElement paymentMethodLabel;
	
	@FindBy(xpath="//label[contains(text(),'PayPal Email')]")
	WebElement paypalEmailLabel;
	
	@FindBy(xpath="//p[contains(text(),'Enter the PayPal account email address that you wo')]")
	WebElement paypalEmailMsg;
	
	@FindBy(xpath="//label[contains(text(),'PayPal Email')]/following-sibling::p[@class='editable']")
	WebElement paypalEmail;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[1]/div/div[2]/a[contains(text(),'Edit')]")
	WebElement paymentMethodEdit;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement paypalEmailEdit;
	
	@FindBy(xpath="//img[@class='paypal-logo pull-left']")
	WebElement paypalLogo;
	
	@FindBy(xpath="//span[@class='radio-label' and contains(text(),'EA Procurement')]")
	WebElement iprocLabel;
	
	@FindBy(xpath="//input[@value='PAYPAL']")
	WebElement paypalRadio;
	
	@FindBy(xpath="//input[@value='IPROC']")
	WebElement iprocRadio;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[1]/div/div[2]/a[contains(text(),'Cancel')]")
	WebElement paymentMethodCancel;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[1]/div/div[2]/a[contains(text(),'Save')]")
	WebElement paymentMethodSave;
	
	@FindBy(xpath="//h2[contains(text(),'Legal/Tax Entity')]")
	WebElement taxLabel;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[2]/div/div[2]/a[contains(text(),'Edit')]")
	WebElement taxEdit;
	
	@FindBy(xpath="//input[@type='radio' and @value='INDIVIDUAL']")
	WebElement individualRadio;
	
	@FindBy(xpath="//input[@type='radio' and @value='BUSINESS']")
	WebElement businessRadio;
	
	@FindBy(xpath="//select[@id='countryCode']")
	WebElement countrySel;
	
	@FindBy(xpath="//label[contains(text(),'Country')]/following-sibling::p")
	WebElement country;
	
	@FindBy(xpath="//label[contains(text(),'Country')]")
	WebElement countryLabel;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[2]/div/div[2]/a[contains(text(),'Cancel')]")
	WebElement taxCancel;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[2]/div/div[2]/a[contains(text(),'Save')]")
	WebElement taxSave;
	
	@FindBy(xpath="//label[contains(text(),'Legal Entity Type')]")
	WebElement legalLabel;
	
	@FindBy(xpath="//label[contains(text(),'Legal Entity Type')]/following-sibling::p")
	WebElement legalEntity;
	
	@FindBy(xpath="//label[contains(text(),'Business Name')]")
	WebElement businessLabel;
	
	@FindBy(xpath="//label[contains(text(),'Business Name')]/following-sibling::p")
	WebElement businessName;
	
	@FindBy(xpath="//input[@id='business' and @type='text']")
	WebElement businessNameEdit;
			
	@FindBy(xpath="//h2[contains(text(),'Address')]")
	WebElement addressLabel;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[3]/div/div[2]/a[contains(text(),'Edit')]")
	WebElement addressEdit;
	
	@FindBy(xpath="//label[contains(text(),'Street')]")
	WebElement streetLabel;
	
	@FindBy(xpath="//label[contains(text(),'Street')]/following-sibling::p")
	WebElement street;
	
	@FindBy(xpath="//input[@id='addressLine1']")
	WebElement streetEdit;
	
	@FindBy(xpath="//label[contains(text(),'City')]")
	WebElement cityLabel;
	
	@FindBy(xpath="//label[contains(text(),'City')]/following-sibling::p")
	WebElement city;
	
	@FindBy(xpath="//label[contains(text(),'State / Province / Region')]")
	WebElement stateLabel;
	
	@FindBy(xpath="//label[contains(text(),'State / Province / Region')]/following-sibling::p")
	WebElement state;
	
	@FindBy(xpath="//label[contains(text(),'Zip / Postal Code')]")
	WebElement zipLabel;
	
	@FindBy(xpath="//label[contains(text(),'Zip / Postal Code')]/following-sibling::p")
	WebElement zip;
			
	@FindBy(xpath="//input[@id='city']")
	WebElement cityEdit;
	
	@FindBy(xpath="//input[@id='state']")
	WebElement provEdit;
	
	@FindBy(xpath="//input[@id='zipCode']")
	WebElement zipEdit;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[4]/div/div[2]/a[contains(text(),'Cancel')]")
	WebElement addressCancel;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[4]/div/div[2]/a[contains(text(),'Save')]")
	WebElement addressSave;
	
	@FindBy(xpath="//div[@class='user-main-container']/div[1]/div[2]/div[@role='alert']")
	WebElement payMethSavMsg;
	
	@FindBy(xpath="//div[@class='message alert alert-success']")
	WebElement taxSavMsg;
		
	@FindBy(xpath="//div[contains(text(),'PayPal email is required.')]")
	WebElement emailError;
	
	@FindBy(xpath="//div[contains(text(),'Tax ID is required.')]")
	WebElement taxIdError;
	
	@FindBy(xpath="//div[contains(text(),'Country is required.')]")
	WebElement countryError;
	 
	@FindBy(xpath="//app-display-message/div/div/div[2]/span")
	WebElement savedMsg;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@id='entity-type']")
	WebElement entityType;
	
	@FindBy(xpath="//button[contains(text(),'Edit')]")
	WebElement editBtn;
	
	@FindBy(xpath="//button[@type='button' and contains(text(),'Upload a new W-9 Form')]")
	WebElement uploadBtn;
	
	@FindBy(xpath="//div[@class='taxDocuments']")
	WebElement taxDocSection;
	
	List<WebElement> tax;
	
	String ssnXpath="//div[@class='col-sm-12']/input";
	String einXpath="//div[@class='marl32']/input";
	
	String attribute_model="ng-reflect-model";
	
	
	// UI Actions.
	
	public String getPaypalEmail() {
		logger.info("Get the paypal email id");
		return this.getAttributeValue(paypalEmail, attribute_model);
				
	}
		
	public void selectCountry(String c) {
		Select sel=new Select(countrySel);
		sel.selectByVisibleText(c);
	}
	/*public String getFullName() {
		isNameDisplayed();
		String fullName = this.getAttributeValue(fName, attribute_model)+""+this.getAttributeValue(lName, attribute_model);
		logger.info("Get the set Name - First and Last Name : "+fullName);
		return fullName;
	}*/
	
	public String getEntityType() {
		String entity=this.getAttributeValue(entityType, attribute_model);
		return entity;
	}
	
	@Step("Verify Navigation to Payment Settings Page")
	public boolean verifyNavigationToPaymentSettingsPage() {
		// method to check landing into Payment Settings Page.
		logger.info("Check for Landing into Payment Settings Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		this.waitForElementToBeVisible(paymentMethodLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(addressLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(this.isElementVisible(paymentMethodLabel, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(addressLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(taxLabel, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed to Payment Settings Page");
			a=true;
		}else {
			logger.info("Error landing to Payment Settings Page");
		}
		return a;
	}
	
	@Step("Verify Paypal Email Id Updation")
	public boolean verifyPaypalEmailUpdation(String email) throws InterruptedException {
		// verify paypal email updation with dummy email id.
		boolean a=false;
		logger.info("Set the paypal email id");
		this.waitForElementToBeVisible(paypalEmail, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(paymentMethodEdit);
		this.sendKeys(paypalEmailEdit, email);
		this.click(paymentMethodSave);
		Thread.sleep(2000);
		boolean isSavMsgDisp=this.isElementVisible(payMethSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		//this.click(paymentMethodEdit);
		this.waitForElementToBeInVisible(payMethSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(email.equals(paypalEmail.getText()) && 
				this.isElementVisible(paypalEmailMsg, GcnDataConstants.IMPLICIT_TIMEOUT) && isSavMsgDisp) {
			logger.info("Paypal Email id is Updated");
			a=true;
		}else {
			logger.info("Failed to update Paypal Email id");
		}
		return a;
	}
	
	public void setPaypalEmail(String pemail) {
		// set the proper working paypal email id
		logger.info("Setting the Paypal Email id");
		this.verifyPageIsLoaded();
		this.waitForElementToBeVisible(paypalEmail, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(paymentMethodEdit, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(paymentMethodEdit);
		this.sendKeys(paypalEmailEdit, pemail);
		this.click(paymentMethodSave);
	}
	
	@Step("Verify if Paypal is selected")
	public boolean verifyPaypalRadioAndEmailUpdation(String paypalEmail) {
		// method to check if paypal radio buttin is selected.
		boolean isSet=false;
		this.waitForElementToBeInVisible(payMethSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(this.isElementVisible(iprocLabel, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.click(paymentMethodEdit);
			logger.info("Check if Paypal Radio button is selected");
			this.isElementVisible(paypalRadio, GcnDataConstants.IMPLICIT_TIMEOUT);
			this.click(paypalRadio);
			this.sendKeys(paypalEmailEdit, paypalEmail);
			this.click(paymentMethodSave);
			this.waitForElementToBeInVisible(payMethSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		}else {
			isSet=true;
			
		}
		
		if(isSet || this.isElementVisible(paypalLogo, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			isSet=true;
		}
		
		return isSet;
	}
	
	@Step("Verify if Oracle iProc is Selected")
	public boolean verifyIProcUpdation() {
		// method to check if oracle iproc radio button is selected.
		boolean isSet=false;
		logger.info("Check if Oracle iProc button is Selected");
		this.waitForElementToBeInVisible(payMethSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(this.isElementVisible(paypalLogo, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.click(paymentMethodEdit);
			logger.info("Check if Paypal Radio button is selected");
			this.isElementVisible(iprocRadio, GcnDataConstants.IMPLICIT_TIMEOUT);
			this.click(iprocRadio);
			this.click(paymentMethodSave);
			this.waitForElementToBeInVisible(payMethSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
			
		}else {
			isSet=true;
		}
		
		if(isSet || this.isElementVisible(iprocLabel, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			isSet=true;
		}
		
		return isSet;
	}
	
	@Step("Verify updation of Country,legal entity,Taxid")
	public boolean verifyUpdationOfTaxDetails(String c,String entityType,String businessName) {
		boolean isSet=false;
		this.waitForElementToBeInVisible(taxSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(taxEdit, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(taxEdit);
		selectCountry(c);
		
		if(c.equalsIgnoreCase("United States") && verifyTaxDocumentSectionAvailability()) {
			if(entityType.equals("Individual")) {
				
				this.click(individualRadio);
				enterTaxId(entityType);
				
			}else if(entityType.equals("Business")) {
				
				this.click(businessRadio);
				enterTaxId(entityType);
				this.sendKeys(businessNameEdit, businessName);
			}

		}else if(!verifyTaxDocumentSectionAvailability()){
			
			if(entityType.equals("Individual")) {
				
				this.click(individualRadio);
							
			}else if(entityType.equals("Business")) {
				
				this.click(businessRadio);
				this.sendKeys(businessNameEdit, businessName);
			}
			
		}
		this.windowScrollUp();
		this.click(taxSave);
		boolean msg=this.isElementVisible(taxSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeInVisible(taxSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(msg && country.getText().equalsIgnoreCase(c)) {
			isSet=true;
		}
		
		return isSet;
	}
	
	@Step("Verify Availability of Tax Document Section")
	public boolean verifyTaxDocumentSectionAvailability() {
		return this.isElementVisible(taxDocSection, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public void enterTaxId(String entityType) {
		 
		if(entityType.equals("Individual")) {
			tax=this.driver.findElements(By.xpath(ssnXpath));
			
		}else if(entityType.equals("Business")) {
			tax=this.driver.findElements(By.xpath(einXpath));
		}
		
		for(int i=0;i<tax.size();i++) {
			this.sendKeys(tax.get(i), "1");
		}
		
	}
	public void selEntityType(int i) {
		switch(i) {
		case 1:
			this.click(individualRadio);
			break;
		case 2:
			this.click(businessRadio);
			break;
		default:
			this.click(individualRadio);
		}
	}
	
	/*@Step("Fill user Payment Settings Details")
	public void setPaymentSettings(String street,String city,String state,String zip,int i) { 
		this.randomSelect(countrySel);
		this.sendKeys(streetEdit, street);
		this.sendKeys(provEdit, state);
		this.sendKeys(cityEdit, city);
		this.sendKeys(zipEdit, zip);
		this.sendKeys(taxId, getRandWord()+getRandNum());
		this.sendKeys(taxIdType, "TAX_Type_"+getRandWord()+getRandNum());
		selEntityType(i);
		this.click(saveBtn);
	}*/
	
	@Step("Verify Address fields are displayed")
	public boolean verifyAddressElementsDisplayed() {
		// check if country, state/province/ city, zip code elements are displayed.
		boolean a=false;
		logger.info("Check for all Address Elements in UI");
		if(this.isElementVisible(country, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(street, GcnDataConstants.IMPLICIT_TIMEOUT)
				&& this.isElementVisible(city, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(state, GcnDataConstants.IMPLICIT_TIMEOUT)
				&& this.isElementVisible(zip, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("All Address Fields are Displayed");
			a=true;
		}else {
			logger.info("Some Address fields are Not Displayed");
		}
		return a;
	}
	
	@Step("Verify switching between paypal and iproc")
	public boolean verifyPaymentMethodSwitch() {
		boolean a=false;
		this.click(paymentMethodEdit);
		this.click(iprocRadio);
		this.click(paymentMethodSave);
		boolean isSavMsgDisp=this.isElementVisible(payMethSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeInVisible(payMethSavMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(this.isElementVisible(iprocLabel, GcnDataConstants.IMPLICIT_TIMEOUT) && isSavMsgDisp) {
			a=true;
		}
		
		return a;
	}
	
	// Method to check the Paypal logo and paypal Text
		public boolean isPaypalLogoDisplayed() {
			boolean a=false;
			logger.info("Check for Paypal logo and Text");
			this.waitForElementToBeVisible(paypalLogo, GcnDataConstants.IMPLICIT_TIMEOUT);
			
			if(this.isElementVisible(paypalLogo, GcnDataConstants.IMPLICIT_TIMEOUT)) {
				logger.info("Paypal Logo and Paypal Text Displayed");
				a=true;
			}else {
				logger.info("Paypal logo OR paypal text Not Displayed");
			}
			return a;
		}
		
		
		public boolean getCountryError() {
			boolean a=false;
			if(countryError.isDisplayed()) {
				logger.info("Error Message is Displayed as : "+countryError.getText());
				a=true;
			}else {
				logger.info("Error Message could not be Captured");
			}
			return a;
		}
		
		public boolean getTaxIdError() {
			boolean a=false;
			if(taxIdError.isDisplayed()) {
				logger.info("Error Message is Displayed : "+taxIdError.getText());
				a=true;
			}else {
				logger.info("Error Message could not be Captured");
			}
			return a;
		}
		
		public boolean getEmailError() {
			boolean a=false;
			if(emailError.isDisplayed()) {
				logger.info("Email: Error Message is Displayed : "+emailError.getText());
				a=true;
			}else {
				logger.info("Error Message could not be Captured");
			}
			return a;
		}
		
		// Methods to Clear Data for the Page.
		
		/*public void clearTaxId() {
			this.clearTextField(taxId);
		}*/
		
		public void clearPaypalMailId() {
			this.clearTextField(paypalEmailEdit);
		}
		
		public void resetCountry() {
			Select sel=new Select(countrySel);
			sel.selectByIndex(0);
		}

		
}
