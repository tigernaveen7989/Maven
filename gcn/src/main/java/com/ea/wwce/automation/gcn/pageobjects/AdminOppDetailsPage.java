package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;

public class AdminOppDetailsPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI element and business methods for Opportunity
	 * details page.
	 * This is the landing page into opportunities, from this page the user can
	 * navigate to functional modules of the opportunity like users,payments,reimbursements,estimation and
	 * settings for the opportunity
	 */
	
	private static final Logger logger=Logger.getLogger(AdminOppDetailsPage.class);
	
	public AdminOppDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//b[contains(text(),'Published')]")
	WebElement publishedLabel;
	
	@FindBy(xpath="//b[contains(text(),'Unpublished')]")
	WebElement unPublishedLabel;
	
	@FindBy(xpath="//h1[@class='col-sm-12']")
	WebElement oppLabel;
	
	@FindBy(xpath="//a[contains(text(),'Overview')]")
	WebElement overviewLink;
	
	@FindBy(id="side-navigation-opportunity-users")
	WebElement usersLink;
	
	@FindBy(xpath="//a[contains(text(),'Settings')]")
	WebElement settingsLink;
	
	@FindBy(xpath="//a[contains(@href,'view-opportunity-payments')]")
	WebElement paymentsLink;
	
	@FindBy(xpath="//a[contains(text(),'Budget Estimation')]")
	WebElement budgetLink;	
	
	@FindBy(xpath="//a[@id='side-navigation-opportunity-reimbursements']")
	WebElement reimbursementLink;
	
	@FindBy(xpath="//select[@name='FormOpportunitySettings[status]']")
	WebElement selPublish;
	
	@FindBy(xpath="//label[contains(text(),'Registrations')]")
	WebElement registrationLabel;
	
	
	// Below commented code will be removed before next PR after re-usability analysis.
	
	// UI Checks
	/*public boolean isOppLabelDisplayed() {
		logger.info("Check for Opportunity label ");
		return this.isElementVisible(oppLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOverviewLinkDisplayed() {
		logger.info("Check for Opportunity label ");
		return this.isElementVisible(overviewLink, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isUsersLinkDisplayed() {
		logger.info("Check for Opportunity label ");
		this.waitForElementToBeVisible(usersLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.isElementVisible(usersLink, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isSettingsLinkDisplayed() {
		logger.info("Check for Settings Link ");
		this.waitForElementToBeVisible(settingsLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.isElementVisible(settingsLink, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isPaymentsLinklDisplayed() {
		logger.info("Check for Payments Link ");
		this.waitForElementToBeVisible(paymentsLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.isElementVisible(paymentsLink, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isPublishedLabelDisplayed() {
		logger.info("Check for Published Label");
		this.waitForElementToBeVisible(publishedLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.isElementVisible(publishedLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		
	}
	
	public boolean isUnPublishedLabelDisplayed() {
		logger.info("Check for Un_Published Label");
		this.waitForElementToBeVisible(unPublishedLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.isElementVisible(unPublishedLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		
	}
	*/
	public boolean isBudgetLinkDisplayed() {
		logger.info("Check for Budget Estimation Link");
		this.waitForElementToBeVisible(budgetLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.isElementVisible(budgetLink, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	
	
	//  UI Actions
	
	@Step("verify Navigation to Opportunity details Page")
	public boolean verifyNavigationToOppoDetailsPage() {
		// method to check landing into Opportunity Details page
		logger.info("Check landing into opportunity Details page");
		this.waitForElementToBeVisible(publishedLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		boolean a=false;
		if(this.isElementVisible(oppLabel, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				(this.isElementVisible(publishedLabel, GcnDataConstants.IMPLICIT_TIMEOUT) || 
						this.isElementVisible(unPublishedLabel, GcnDataConstants.IMPLICIT_TIMEOUT))) {
			logger.info("Landed into Overview Page");
			a=true;
		}else {
			logger.info("Error in Overview Page");
		}
		return a;		
	}
	
	@Step("verify Availability of Left Navigation Links")
	public boolean verifyAvailabilityOfLeftNavLinks() {
		boolean a=false;
		
		try {
			
			if(this.isElementVisible(overviewLink, GcnDataConstants.IMPLICIT_TIMEOUT) && 
					this.isElementVisible(usersLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(settingsLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(paymentsLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(budgetLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(reimbursementLink, GcnDataConstants.IMPLICIT_TIMEOUT)	) {
				a=true;
			}
			
		}catch(ElementNotFoundException e) {
			logger.info("Error finding the element "+e.getMessage());
		}
		return a;
	}
	
	public AdminOppPaymentsPage clickOnPaymentsLink() {
		logger.info("Navigating to Payments link");
		this.waitForElementToBeVisible(paymentsLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.isElementVisible(paymentsLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(paymentsLink);
		this.verifyPageIsLoaded();
		return new AdminOppPaymentsPage(driver);
	}
	
	public AdminOppSettingsPage goToOppSettings() throws InterruptedException {
		logger.info("Navigating to Opportunity Settings Page.");
		Thread.sleep(3000);
		this.click(settingsLink);
		Thread.sleep(3000);
		this.verifyPageIsLoaded();
		return new AdminOppSettingsPage(driver);
	}
	
	public AdminOppUsersPage goToOppUsers() {
		logger.info("Navigating to Opportunity Users Section");
		this.click(usersLink);
		return new AdminOppUsersPage(driver);
	}
		
	public void goToOverviewPage() {
		logger.info("Navigating to Overview Section");
		this.click(overviewLink);
	}
	
	public AdminOppBudgetPage clickBudgetEstimationLink() {
		logger.info("Navigating to Budget Estimation Page");
		this.waitForElementToBeVisible(budgetLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(budgetLink);
		this.verifyPageIsLoaded();
		return new AdminOppBudgetPage(driver);
	}
	
	public void selOppStatus(String status) {
		Select sel=new Select(selPublish);
		sel.selectByVisibleText(status);
	}
	

}
