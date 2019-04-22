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

public class AdminOppUsersPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements of the Users page for a specific opportunity.
	 * Here the admin can add or invite users into the opportunity.
	 * Approve or reject their joining request.
	 */
	
	private static final Logger logger=Logger.getLogger(AdminOppUsersPage.class);
	
	public AdminOppUsersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="btn-opportunity-users-add")
	WebElement inviteUsersBtn;
	
	@FindBy(xpath="//button[contains(text(),'Email')]")
	WebElement emailDropDown;
	
	@FindBy(xpath="//button[contains(text(),'Modify')]")
	WebElement modifyDropDown;
	
	@FindBy(id="btn-opportunity-users-configure-csv")
	WebElement csvBtn;
	
	@FindBy(id="opportunity-users-table")
	WebElement userTable;
	
	@FindBy(xpath="//table[@id='opportunity-users-table']//tbody")
	WebElement tableBody;
	
	@FindBy(id="addusers-add")
	WebElement addInviteUserForm;
	
	@FindBy(xpath="//input[@id='addusers-searchtext']")
	WebElement addUserSearchBox;
	
	@FindBy(xpath="//div[@id='userList']")
	WebElement searchList;
	
	@FindBy(xpath="//a[@title='Close']")
	WebElement closeBtn;
	
	List<WebElement> addBtns;
	String addBtnXpath="//div[@class='users-button-container']/a[@class='btn btn-info mobile-hide btn-userlist-add ']";
	
	// Below commented code will be removed before next PR after re-usability analysis.
	
	// UI Checks
	/*
	public boolean isInviteUserBtnDisplayed() {
		logger.info("Check for Invite Users button");
		return this.isElementVisible(inviteUsersBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isEmailDropDownDisplayed() {
		logger.info("Check for Email Drop Down");
		return this.isElementVisible(emailDropDown, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isModifyDropDownDisplayed() {
		logger.info("Check for Modify Drop Down");
		return this.isElementVisible(modifyDropDown, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isCsvBtnDisplayed() {
		logger.info("Check for CSV button");
		return this.isElementVisible(csvBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isUserTableDisplayed() {
		logger.info("Check for Users Table");
		return this.isElementVisible(userTable, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isTableBodyDisplayed() {
		logger.info("Check for Users Table Body");
		return this.isElementVisible(tableBody, GcnDataConstants.IMPLICIT_TIMEOUT);
	}*/
	
	
	//  UI Actions
	
	@Step("Verify landing into Opportunity Users Page")
	public boolean verifyNavigationToOppUsersPage() {
		// method to check landing into opportunity users Page.
		logger.info("Check landing into opportunity users Page");
		boolean a=false;
		if(this.isElementVisible(inviteUsersBtn, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(tableBody, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Opportunity Users Page");
			a=true;
		}else {
			logger.info("Error in Opportunity Users Page");
		}
		return a;
	}
	
	@Step("Verify Opportunity Users page UI elements")
	public boolean verifyOppUsersPageUi() {
		boolean a=false;
		if(this.isElementVisible(emailDropDown, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(modifyDropDown, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(csvBtn, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(userTable, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
		}
		return a;
	}
	
	@Step("Verify adding/inviting users to Opportunities")
	public boolean addUsersToOpportunity() throws InterruptedException {
		boolean isAdded=false;
		this.click(inviteUsersBtn);
		this.waitForElementToBeVisible(addUserSearchBox, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.sendKeys(addUserSearchBox, "a");
		this.waitForElementToBeVisible(searchList, GcnDataConstants.IMPLICIT_TIMEOUT);
		addBtns=this.driver.findElements(By.xpath(addBtnXpath));
				
		for(int i=0;i<addBtns.size()-1;i++) {
			this.click(addBtns.get(i));
			Thread.sleep(500);
		}
		
		addBtns=this.driver.findElements(By.xpath(addBtnXpath));
		if(addBtns.size()>1) {
			isAdded=true;
		}
		this.click(closeBtn);
		return isAdded;
	}


}
