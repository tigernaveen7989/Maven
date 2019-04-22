package com.ea.wwce.automation.gcn.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class AdminUsersPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and business methods to work on User profiles of game changers by admin.
	 * Admin can search and navigate to specific user profile from this page.
	 * Admin can get media statistics of the user.
	 * 
	 */
	
	private static final Logger logger=Logger.getLogger(AdminUsersPage.class);
	
	public AdminUsersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
			
	}

	@FindBy(id="userlist_filters")
	WebElement userFilter;
	
	@FindBy(id="user-table-input-name-search")
	WebElement userSearchBox;
	
	@FindBy(id="User_filter_status")
	WebElement userStatusSelect;
	
	@FindBy(id="User_filter_primary_franchise")
	WebElement franchiseSelect;
	
	@FindBy(id="User_filter_secondary_franchise")
	WebElement franchiseSelect2;
	
	@FindBy(id="User_filter_tier")
	WebElement tierSelect;
	
	@FindBy(id="User_filter_admin_role")
	WebElement roleSelect;
	
	@FindBy(xpath="//a[contains(text(),'My Profile')]")
	WebElement myProfileLink;
	
	@FindBy(id="userList")
	WebElement userListFrame;
	
	String userListXpath="//div[@class='table table-bordered table-condensed table-admin-users']/div//h3/a";
	String userListXpathD="//div[@class='table table-bordered table-condensed table-admin-users']/div[#]//h3/a";
	
	List<WebElement> userList;
	
	AdminViewUserProfile adminViewUserProfile=new AdminViewUserProfile(driver);
	
		
	// UI Actions
	@Step("Verify Navigation to Users Page")
	public boolean verifyNavigationToUsersPage() {
		// method to check landing into Users Page from Admin Site.
		logger.info("Check Landing into Users page from Admin Site");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isElementVisible(userFilter, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(userListFrame, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Users Page");
			a=true;
		}else {
			logger.info("Error in Users Page");
		}
		return a;
		
	}
	
	@Step("Verify Admin - User page UI elements")
	public boolean verifyUserPageUi() {
		boolean a=false;
		this.verifyPageIsLoaded();
		
		this.waitForElementToBeVisible(userSearchBox, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(userStatusSelect, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(franchiseSelect, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(franchiseSelect2, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(tierSelect, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(roleSelect, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(myProfileLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(this.isElementVisible(userSearchBox, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(userStatusSelect, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(franchiseSelect, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(franchiseSelect2, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(tierSelect, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(roleSelect, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(myProfileLink, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
		}
		
		return a;
	}
	
	@Step("Verify Navigating to User Profile page By UserName")
	public boolean verifyProfilePageByUserName(String uname) {
		userList=this.driver.findElements(By.xpath(userListXpath));
		boolean isTrue=false;
		String s="";
		for(int i=0;i<userList.size();i++) {
			s=String.valueOf(i+1);
			if(this.findDynamicElement(userListXpathD, s).getText().equals(uname)) {
				this.click(this.findDynamicElement(userListXpathD, s));
				break;
			}
		}
		this.waitForElementToBeVisible(adminViewUserProfile.saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(adminViewUserProfile.userName.getText().equals(uname)) {
			isTrue=true;
		}
		
		return isTrue;
	}
	
	
}
