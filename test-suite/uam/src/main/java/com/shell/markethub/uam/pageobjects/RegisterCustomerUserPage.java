package com.shell.markethub.uam.pageobjects;

import java.nio.charset.Charset;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterCustomerUserPage extends UAMBasePageObject{

	public RegisterCustomerUserPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(RegisterCustomerUserPage.class);
	@FindBy(xpath = "//h1[contains(text(),'Register Customer User')]")
	WebElement title;
	@FindBy(xpath = "//input[@id='checkbox1']")
	WebElement registerEhaulierUserCheckbox;
	@FindBy(xpath = "//input[@id='crmid']")
	WebElement crmEditbox;
	@FindBy(xpath = "//input[@id='fname']")
	WebElement firstNameEditbox;
	@FindBy(xpath = "//input[@id='lname']")
	WebElement lastNameEditbox;
	@FindBy(xpath = "//input[@id='email']")
	WebElement emailEditbox;
	@FindBy(xpath = "//input[@id='custno']")
	WebElement accountNumberEditbox;
	@FindBy(xpath = "//input[@id='custname']")
	WebElement accountNameEditbox;
	@FindBy(xpath = "//input[@id='userstatus']")
	WebElement userStatusEditbox;
	@FindBy(xpath = "//select[@id='searchCountry']")
	WebElement countryDropdown;
	@FindBy(xpath = "//button[@id='searchCustomerUser']")
	WebElement searchButton;
	@FindBy(xpath = "//button[@id='createNewUser']")
	WebElement createCRMContactButton;
	@FindBy(xpath = "//input[@id='createNonCrmUser']")
	WebElement yesRadioButton;
	@FindBy(xpath = "//input[@id='createCrmUser']")
	WebElement noRadioButton;
	@FindBy(xpath = "//input[@id='ehauliernumber']")
	WebElement eHaulierNumberEditbox;
	@FindBy(xpath = "//button[@id='searchehaulieruser']")
	WebElement eHaulierSearchButton;
	@FindBy(xpath = "//button[@id='searchCustomerEhaulierUserNext']")
	WebElement eHaulierNextButton;
	
	public String getTitle() throws Exception{
		return getText(title);
	}
	
	public void clickOnEhaulierCheckbox() throws Exception {
		click(registerEhaulierUserCheckbox);
	}
	
	public void enterEhaulierNumber(String eHaulierNumber) throws Exception {
		sendKeys(eHaulierNumberEditbox, eHaulierNumber);
		Thread.sleep(2000);
	}
	
	public void clickOnEhaulierSearchButton() throws Exception {
		click(eHaulierSearchButton);
	}
	
	public void clickOnEhaulierNextButton() throws Exception {
		Thread.sleep(5000);
		click(eHaulierNextButton);
	}
	
	public void enterFirstName(String firstName) throws Exception {
		sendKeys(firstNameEditbox, firstName);
		Thread.sleep(2000);
	}
	
	public void enterLastName(String lastName) throws Exception {
		sendKeys(lastNameEditbox, lastName);
	}
	
	public void enterEmail(String email) throws Exception {
		sendKeys(emailEditbox, email);
	}
	
	public void enterAccountNumber(String accountNumber) throws Exception {
		sendKeys(accountNumberEditbox, accountNumber);
	}
	
	public void enterAccountName(String accountName) throws Exception {
		sendKeys(accountNameEditbox, accountName);
	}
	
	public void enterUserStatus(String userStatus) throws Exception {
		sendKeys(userStatusEditbox, userStatus);
	}
	
	public void selectCountry(String country) throws Exception {
		selectVisibleText(countryDropdown, country);
	}
	
	public void clickOnSearchButton() throws Exception {
		click(searchButton);
		Thread.sleep(2000);
	}
	
	public void clickOnCreateCRMContactButton() throws Exception {
		click(createCRMContactButton);
		Thread.sleep(2000);
	}
	
	public void clickOnYesRadioButton() throws Exception {
		click(yesRadioButton);
	}
	
	public void clickOnNoRadioButton() throws Exception {
		click(noRadioButton);
	}
}
