package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class AdminOppSettingsPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and navigation links for Opportunity settings page.
	 * from this page hte admin can navigate to different tabs like general,event,
	 * youtube submissions,gamecodes etc..
	 */
	
	private static final Logger logger=Logger.getLogger(AdminOppSettingsPage.class);
	
	public AdminOppSettingsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[@class='col-sm-12']")
	WebElement oppLabel;
	
	@FindBy(xpath="//form[@id='FormOpportunitySettings']")
	WebElement opportunityForm;
	
	@FindBy(xpath="//ul[@class='nav nav-tabs opportunity-settings-nav-tabs']//b[contains(text(),'General')]")
	WebElement generalTab;
	
	@FindBy(xpath="//form[@id='FormOpportunitySettings']/div/div/ul/li/a[contains(text(),'Event')]")
	WebElement eventTab;
	
	@FindBy(xpath="//a[contains(text(),'Youtube Submission')]")
	WebElement youtubeSubTab;
	
	@FindBy(xpath="//a[contains(text(),'Game Codes')]")
	WebElement gameCodesTab;
	
	@FindBy(xpath="//a[contains(text(),'File Submission')]")
	WebElement fileSubTab;
	
	@FindBy(xpath="//a[contains(text(),'NDA')]")
	WebElement ndaTab;
	
	@FindBy(xpath="//a[contains(text(),'Feedback')]")
	WebElement feedbackTab;
	
	// Below commented code will be removed before next PR after re-usability analysis.
	
	// UI Checks
	
	/*public boolean isOppLabelDisplayed() {
		logger.info("Check for Opportunity Label");
		return this.isElementVisible(oppLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOpportunityFormDisplayed() {
		logger.info("Check for Opportunity Settings Form.");
		return this.isElementVisible(opportunityForm, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isGeneralTabDisplayed() {
		logger.info("Check for General Tab");
		return this.isElementVisible(generalTab, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isEventsTabDisplayed() {
		logger.info("Check for Events Tab");
		return this.isElementVisible(eventTab, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isYoutubeTabDisplayed() {
		logger.info("Check for Youtube Tab");
		return this.isElementVisible(youtubeSubTab, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isGameCodeTabDisplayed() {
		logger.info("Check for Game Codes Tab");
		return this.isElementVisible(gameCodesTab, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isFileSubTabDisplayed() {
		logger.info("Check for File Submission Tab");
		return this.isElementVisible(fileSubTab, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isNdaTabDisplayed() {
		logger.info("Check for NDA Tab");
		return this.isElementVisible(ndaTab, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isFeedBackTabDisplayed() {
		logger.info("Check for FeedBack Tab");
		return this.isElementVisible(feedbackTab, GcnDataConstants.IMPLICIT_TIMEOUT);
	}*/
	
	
	// UI Actions
	@Step("verify navigation into Opportunity Settings Page")
	public boolean verifyNavigationToOppSettingsPage() {
		//method to check landing into Opportunity Settings Page
		logger.info("Check landing into Opportunity Settings Page");
		boolean a=false;
		this.waitForElementToBeVisible(opportunityForm, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(generalTab, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(oppLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(this.isElementVisible(opportunityForm, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(generalTab, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Opportunity Settings Page");
			a=true;
		}else {
			logger.info("Error in Opportunity Settings Page");
		}
		return a;
	}
	
	public NewOpportunityGeneralPage goToOppGeneralTab() {
		logger.info("Navigating to Opportunity General Tab");
		this.windowScrollUp();
		this.windowScrollUp();
		this.click(generalTab);
		return new NewOpportunityGeneralPage(driver);
		
	}
	
	public NewOpportunityEventPage goToOppEventsTab() {
		logger.info("Navigating to Opportunity Events Tab");
		this.windowScrollUp();
		this.windowScrollUp();
		this.click(eventTab);
		return new NewOpportunityEventPage(driver);
		
	}
	
}
