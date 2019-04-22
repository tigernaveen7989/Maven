package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;


public class GcnAdminPage extends BasePageObject{
	
	/*
	 * This class defines UI elements and methods to verify the Navigation links and UI
	 * elements present in the Admin Page after loggin.
	 */
	
	private static final Logger logger=Logger.getLogger(GcnAdminPage.class);
	
	public GcnAdminPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	//Game Changers logo on top left of Admin page
	@FindBy(xpath="//img[@class='admin-navbar-image img']")
	WebElement gameChangLogo;
	
	//Admin Link on Top
	@FindBy(xpath="//*[@id='navbar']/ul/li[1]/a")
	WebElement adminLink;
	
	//User Link on Top
	@FindBy(xpath="//*[@id='navbar']/ul/li[2]/a")
	WebElement userLink;
	
	//Opportunities Link on Top
	@FindBy(xpath="//*[@id='navbar']/ul/li[3]/a")
	WebElement opportunitiesLink;
	
	//Content link on Top
	@FindBy(xpath="//*[@id='navbar']/ul/li[4]/a")
	WebElement contentLink;
	
	//Reports link on Top
	@FindBy(xpath="//*[@id='navbar']/ul/li[5]/a")
	WebElement reportsLink;
	
	@FindBy(xpath="//a[contains(text(),'Campaigns')]")
	WebElement reportsCampaign;	
	
	//Operations link on Top
	@FindBy(xpath="//*[@id='navbar']/ul/li[6]/a")
	WebElement operationLink;
	
	//Logout link on Top
	@FindBy(xpath="//*[@id='navbar']/ul/li[7]/a")
	WebElement logoutLink;
	
	@FindBy(xpath="//h3[contains(text(),'Site Status')]")
	WebElement siteStatusLabel;
	
	@FindBy(xpath="//h3[contains(text(),'Top Game Changers')]")
	WebElement topGcSection;
	
	@FindBy(xpath="//h3[contains(text(),'Action Items')]")
	WebElement actionItemLabel;
	
	@FindBy(xpath="//h3[contains(text(),'Action Items')]//ancestor::div[2]")
	WebElement actionItemSection;
	
	//div[contains(text(),'Content to Review')]
	@FindBy(xpath="//div[contains(text(),'Content to Review')]")
	WebElement contentReviewLink;
	
	@FindBy(xpath="//div[contains(text(),'Tax Document Verification')]")
	WebElement taxDocVerificationLink;
	
	@FindBy(xpath="//div[contains(text(),'User Payment Setup')]")
	WebElement userPaymentLink;
	
	@FindBy(xpath="//div[contains(text(),'New Event Registration')]")
	WebElement eventRegLink;
	
	@FindBy(xpath="//div[contains(text(),'Reimbursement Review')]")
	WebElement reimbLink;
	
	// xpath for same element in other page is different eg.payments page
	@FindBy(xpath="//div[@class='admin-footer-logo-container']")
	WebElement footerEAtext;
	
	@FindBy(xpath="//img[@src='https://qa-admin.gamechangers.ea.com/images/ea-logo-white.png']")
	WebElement footerEAwhiteLogo;
	
	public void switchBackToAdminPage() {
		logger.info("Switching to Admin Page");
		this.switchWindowByTitle(GcnDataConstants.actAdminTitle);
	}
	
	@Step("Verify the Admin Page title")
	public boolean verifyAdminPageTitle() {
		boolean a=false;
		if(GcnDataConstants.actAdminTitle.equals(this.getPageTitle())){
			a=true;
		}
		return a;
	}
	
	public AdminOpportunityListPage navToAdminOpportunityPage() {
		this.click(opportunitiesLink);
		logger.info("Clicked on Opportunities Link in GCN Admin Page");
		this.verifyPageIsLoaded();
		return new AdminOpportunityListPage(driver);
	}
	
	public GcnAdminHomePage logoutAdmin() {
		logger.info("Click on Signout from GC Admin Site");
		this.click(logoutLink);	
		this.verifyPageIsLoaded();
		return new GcnAdminHomePage(driver);
	}
	
	public AdminUsersPage clickOnUsersLink() {
		logger.info("Click on Users Link, and Navigate to Users Tab.");
		this.click(userLink);
		this.verifyPageIsLoaded();
		return new AdminUsersPage(driver);
	}
	
	public void clickOnAdminLink() {
		this.click(adminLink);
		this.verifyPageIsLoaded();
	}
	
	public GcnAdminContentPage clickOnContentLink() {
		logger.info("Click on Content link and Navigate to Content tab.");
		this.click(contentLink);
		this.verifyPageIsLoaded();
		return new GcnAdminContentPage(driver);
	}
	
	public GcnAdminReportsPage clickOnReportsLink() {
		logger.info("Click on Reports link and Navigate to Reports Link");
		this.click(reportsLink);
		this.click(reportsCampaign);
		this.verifyPageIsLoaded();
		return new GcnAdminReportsPage(driver);
	}
	
	public GcnAdminOperationsPage clickOnOperationsLink() {
		logger.info("Click on Operations Link and Navigate to Operations Page");
		this.click(operationLink);
		return new GcnAdminOperationsPage(driver);
	}
	
	@Step("Verify Navigation to Admin Page after Login.")
	public boolean verifyNavigationToAdminPage() {
		// method to check landing into GCN Admin Page
		logger.info("Check landing into GCN Admin Page");
		boolean a=false;
		if(this.isElementVisible(siteStatusLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(topGcSection, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(logoutLink, GcnDataConstants.IMPLICIT_TIMEOUT) ) {
			logger.info("Landed into Admin Page");
			a=true;
		}else {
			logger.info("Error in Admin Page");
		}
		return a;
	}
	
	@Step("Verify the Availability of UI Menu in Admin Site")
	public boolean verifyAvailabilityOfMenuLinks() {
		boolean a=false;
		/*
		 * this method will check the availability of menu items as below
		 * Admin || Users || Opportunities || Content || Reports || Operations || Logout
		 */
		try {
			
			if(this.isElementVisible(adminLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(userLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(opportunitiesLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(contentLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(reportsLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(operationLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(logoutLink, GcnDataConstants.IMPLICIT_TIMEOUT)) {
					a=true;
				}
				
				
			
		}catch(ElementNotFoundException e) {
			logger.info("Links Not Found "+e.getMessage());
		}
		return a;
	}
	
	@Step("Verify UI elements of Action Item Section")
	public boolean verifyActionItemUi() {
		boolean a=false;
		if(this.isElementVisible(actionItemSection, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(actionItemLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(contentReviewLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(taxDocVerificationLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(userPaymentLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(eventRegLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(reimbLink, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
		}
		return a;
	}
	
	@Step("Verify EA Footer Text")
	public boolean isEaFooterTextDisplayed() {
		boolean a=false;
		logger.info("Check for Powered by text ");
		if(footerEAtext.isDisplayed() && footerEAtext.getText().equals(GcnDataConstants.eaFooterText)) {
			a=true;
			logger.info("Footer Text is Displayed");
		}else {
			logger.info("Footer Text is Not Displayed");
			a=false;
		}
		return a;
	}
	
	public boolean isEaWhiteLogoDisplayed() {
		logger.info("Check for EA White Logo displayed in Footer");
		return this.isElementVisible(footerEAwhiteLogo, GcnDataConstants.IMPLICIT_TIMEOUT);
	}

}
