package com.shell.markethub.uam.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerUserPage extends UAMBasePageObject{

	public SearchCustomerUserPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(SearchCustomerUserPage.class);
	@FindBy(xpath = "//h3[text()='Search Customer User']")
	WebElement title;
	@FindBy(id = "email")
	WebElement emailEditBox;
	@FindBy(xpath = "//table[@id='userList']")
	WebElement userListTable;
	@FindBy(id = "searchRegisteredUser")
	WebElement searchButton;
	@FindBy(xpath="//input[@id='checkbox1'][@name='userId']")
	WebElement userListCheckBox1;
	@FindBy(id="impersonateButton")
	WebElement impersonateButton;
	@FindBy(xpath = "//input[@id='userid']")
	WebElement userNameEditbox;
	@FindBy(xpath = "//span[@id='userStatus']")
	WebElement statusText;
	@FindBy(xpath = "//button[@id='manageCustomerUserDeactivate']")
	WebElement deactivateButton;
	@FindBy(xpath = "//button[@id='manageCustomerUserActivate']")
	WebElement activateButton;
	@FindBy(xpath = "//a[contains(text(),'Register Customer User')]")
	WebElement registerCustomerUserLink;
	@FindBy(xpath = "//button[@id='manageUserEdit']")
	WebElement editButton;
	
	
	public Boolean verifyTitle() throws Exception {
		return isDisplayed(title);
	}
	
	public void enterEmail(String email) {
		sendKeys(emailEditBox, email);
	}
	
	public void clickSearchButton() throws Exception {
		click(searchButton);
		Thread.sleep(5000);
	}
	
	public Boolean verifyUserListTablePresent() throws Exception {
		return isDisplayed(userListTable);
	}
	
	public void clickOnUserListCheckBox1() throws Exception {
		click(userListCheckBox1);
		Thread.sleep(2000);
	}
	
	public void clickOnImpersonateButton() throws Exception {
		click(impersonateButton);
	}
	
	public void enterUserName(String searchUserName) throws Exception {
		sendKeys(userNameEditbox, searchUserName);
	}
	
	public String verifyUserStatus() throws Exception {
		return getText(statusText);
	}
	
	public void clickOnDeactivateButton() throws Exception {
		try {
			click(deactivateButton);
			Thread.sleep(2000);
		}catch(org.openqa.selenium.UnhandledAlertException ex) {
			
		}
	}
	
	public void clickOnActivateButton() throws Exception {
		try {
			click(activateButton);
			Thread.sleep(2000);
		}catch(org.openqa.selenium.UnhandledAlertException ex) {
			
		}
	}
	
	public void clickOnOkButtonOnPopup() throws Exception {
		acceptAlert();
		Thread.sleep(5000);
	}
	
	public void clickOnRegisterCustomerUserLink() throws Exception {
		click(registerCustomerUserLink);
	}
	
	public void clickOnEditButton() throws Exception {
		click(editButton);
	}
}
