package com.ea.wwce.automation.salesforce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.salesforce.config.SalesforceDataConstants;

import io.qameta.allure.Step;

public class SalesforceLoginPage extends SalesforceBasePageObject {

	private static final Logger logger = Logger.getLogger(SalesforceLoginPage.class);

	public SalesforceLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	WebElement txtuserName;
	@FindBy(id = "password")
	WebElement txtpassword;
	@FindBy(xpath = "//button[contains(@class,'primary')]")
	WebElement btnLogin;
	//@FindBy(xpath ="//button[contains(@class,'oneUserProfileCardTrigger')]")
	//WebElement btnUserProfile;
	@FindBy(xpath ="//img[@title='User']")
	WebElement btnUserProfile;
	@FindBy(xpath ="//a[contains(text(),'Switch to Salesforce Classic')]")
	WebElement lnkSFClassic;
	@FindBy(xpath ="//div[@id='userNav-arrow']")
	WebElement listdropdown;
	@FindBy(xpath ="//a[@title='Setup']")
	WebElement lnksetup;
	@FindBy(xpath ="//img[@title='All Tabs']")
	WebElement imgAllTabs;
	@FindBy(xpath ="//a[@class='listRelatedObject Custom67Block title'][contains(text(),'ExternalSurveyConfigs')]")
	WebElement lnkESConfig;
	@FindBy(xpath ="//a[contains(text(),'CSAT Chat Survey')]")
	WebElement linkCSATChat;
	@FindBy(xpath ="//a[contains(text(),'CSAT Email Survey')]")
	WebElement linkCSATEmail;
	@FindBy(xpath ="//td[@id='bottomButtonRow']//input[@title='Edit']")
	WebElement btnEdit;	
	@FindBy(xpath ="//td[@id='bottomButtonRow']//input[@title='Save']")
	WebElement btnSave;
	String chatPercent_XPATH= "//label[contains(text(),'Percent')]//following::input[@value='#']";
	@FindBy(xpath ="//a[contains(@class,'switch-to-lightning')]")
    WebElement lnkSwitchToLightning;
	@FindBy(xpath ="//a[contains(text(),'Log Out')]")
	WebElement lnkLogout;
	@FindBy(xpath ="(//label[contains(text(),'Percent')]//following::input)[1]")
	WebElement editPercent;

	// Validate login functionality
	public void loginToSalesForce(String URL, String username, String password) throws InterruptedException {
		try {
			this.loadPage(URL);
			Thread.sleep(5000);
			logger.info("Validate login functionality");
			this.sendKeys(txtuserName, username);
			this.sendKeys(txtpassword, password);
			this.click(btnLogin);
			this.verifyPageIsLoaded();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to login" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed to login due to time out" + e.getMessage());
		}
	}

	@Step("Load Salesforce Home Page")
	public void loadSalesforce(String URL) {
		logger.info("Load Salesforce Home Page");
		try {
			this.loadPage(URL);
			this.verifyPageIsLoaded();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// This function click on User Profile Image button
	public void clickOnUserProfile() {
		try {
			logger.info("Click on User Profile Image");
			this.waitForElementToBeVisible(btnUserProfile, 10);
			this.click(btnUserProfile);
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			logger.info("Failed to find the user Profile button");
		} catch (Exception e) {
			logger.info("Failed to Commit a Petition Action  " + e.getMessage());
		}
	}

	// Switch to SF classic of the Thor Application
	public void switchToSFClassic() {
		try {
			boolean isVisible = this.isElementVisible(lnkSFClassic, 5);

			if (isVisible) {
				this.click(lnkSFClassic);
			} else
				logger.info("Failed to find the Switch to SF classic link");
		} catch (NoSuchElementException e) {
			logger.info("Failed to find the Switch to SF classic link");
		}
	}

	// This function click on All tabs Image image
	public void clickOnAllTabs() {
		try {
			logger.info("Click on All Tabs image");
			this.waitForElementToBeVisible(imgAllTabs, 10);
			this.click(imgAllTabs);
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			logger.info("Failed to find the all tabs image");
		} catch (Exception e) {
			logger.info("Failed to click on all tabs image  " + e.getMessage());
		}
	}

	// This function click on External survey config link
	public void clickOnESConfig() {
		try {
			logger.info("Click on External Survey Config link");
			this.waitForElementToBeVisible(lnkESConfig, 10);
			this.click(lnkESConfig);
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			logger.info("Failed to find the external survey config link");
		} catch (Exception e) {
			logger.info("Failed to click on external survey config link  " + e.getMessage());
		}
	}

	// This function click on CSAT Chat Survey link
	public void clickCSATChat() {
		try {
			logger.info("Click on CSAT Chat Survey link");
			this.waitForElementToBeVisible(linkCSATChat, 10);
			this.click(linkCSATChat);
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			logger.info("Failed to find the csat chat survey link");
		} catch (Exception e) {
			logger.info("Failed to click on csat chat survey link  " + e.getMessage());
		}
	}

	// Method for set CSAT Chat External Survey config
	@Step("Select CSAT Chat External config value")
	public void csatChatExtrnalSurveyConfig(String value) {
		try {
			logger.info("Enter CSAT Chat Config value");
			this.waitForElementToBeVisible(imgAllTabs, 5);
			this.click(imgAllTabs);
			Thread.sleep(1000);
			this.windowScrollDwn();
			this.waitForElementToBeVisible(lnkESConfig, 5);
			this.click(lnkESConfig);
			this.waitForElementToBeVisible(linkCSATChat, 5);
			this.click(linkCSATChat);
			this.waitForElementToBeVisible(btnEdit, 5);
			this.click(btnEdit);
			// this.clickOnDynamicElement(chatPercent_XPATH, value);
			this.clearTextbox(editPercent);
			this.sendKeys(editPercent, value);
			Thread.sleep(2000);
			this.click(btnSave);

		} catch (Exception e) {
			logger.info("Failed to save CSAT Chat External config value  " + e.getMessage());
		}

	}

	// Method for set CSAT Email External Survey config
	@Step("Select CSAT Email External config value")
	public void csatEmailExtrnalSurveyConfig(String value) {
		try {
			logger.info("Enter CSAT Email Config value");
			this.waitForElementToBeVisible(imgAllTabs, 5);
			this.click(imgAllTabs);
			Thread.sleep(1000);
			this.windowScrollDwn();
			this.waitForElementToBeVisible(lnkESConfig, 5);
			this.click(lnkESConfig);
			this.waitForElementToBeVisible(linkCSATEmail, 5);
			this.click(linkCSATEmail);
			this.waitForElementToBeVisible(btnEdit, 5);
			this.click(btnEdit);
			// this.clickOnDynamicElement(chatPercent_XPATH, value);
			this.clearTextbox(editPercent);
			this.sendKeys(editPercent, value);
			Thread.sleep(2000);
			this.click(btnSave);

		} catch (Exception e) {
			logger.info("Failed to save CSAT Email External config value  " + e.getMessage());
		}

	}

	// Method to Click on Switch to Lightning link
	@Step("Click on switch to SF lightning link")
	public void clickOnSFLightningLnk() {
		try {
			// Click on Lightning link
			if (this.isElementVisible(lnkSwitchToLightning, 3)) {
				this.click(lnkSwitchToLightning);
				this.verifyPageIsLoaded();
			} else
				logger.info("Switching to SF lightning Link not visible");

		} catch (Exception e) {
			logger.info("Failed to Click on Switch to lightning link  " + e.getMessage());
		}
	}

	// Logout of the Salesforce Application
	public void logout() {
		try {
			boolean isVisible = this.isElementVisible(lnkLogout, SalesforceDataConstants.IMPLICIT_TIMEOUT);

			if (isVisible) {
				this.click(lnkLogout);
				Thread.sleep(4000);
			}

			else
				logger.info("Failed to find the logout link");
		} catch (NoSuchElementException e) {
			logger.info("Failed to find the logout link");
		} catch (Exception e) {
			logger.info("Failed to logout from the application " + e.getMessage());
		}
	}

	public void setSalesForceConfigForEmail(String URL, String SF_username, String SF_password,
			String emailconfig_Value) throws Exception {
		// Verify SF login
		loginToSalesForce(URL, SF_username, SF_password);

		// Navigate to SF classic
		clickOnUserProfile();
		switchToSFClassic();
		// Enter Email Config value
		csatEmailExtrnalSurveyConfig(emailconfig_Value);
		// Switch to SF Lightning Link
		clickOnSFLightningLnk();
		clickOnUserProfile();
		// Logout from SF
		logout();
	}

	public void setSalesForceConfigForChat(String URL, String SF_username, String SF_password, String chatconfig_Value)
			throws Exception {
		// Verify SF login
		loginToSalesForce(URL, SF_username, SF_password);

		// Navigate to SF classic
		clickOnUserProfile();
		switchToSFClassic();
		// Enter Chat Config value
		csatChatExtrnalSurveyConfig(chatconfig_Value);
		// Switch to SF Lightning Link
		clickOnSFLightningLnk();
		clickOnUserProfile();
		// Logout from SF
		logout();
	}

}
