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

public class AdminOpportunityListPage extends GcnBasePageObjects{
	
	/*
	 * This page defines the UI elements and business methods of the Opportunities Page.
	 * All the opportunities are listed in the page,Filter section enables the user to 
	 * search opportunities with specific criteria
	 * 
	 */

	private static final Logger logger = Logger.getLogger(AdminOpportunityListPage.class);
	private String oppTitle;
	
	public AdminOpportunityListPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	//Objects for Opportunities page.
	
	//Search Panel objects
	@FindBy(id="opportunities-table-input-title-search")
	WebElement titleSearchBox;
	
	@FindBy(id="Opportunity_filter_status")
	WebElement oppStateSelection;
	
	@FindBy(id="Opportunity_filter_campaign")
	WebElement campaignSelection;
	
	@FindBy(id="inlineRadio1")
	WebElement ownedByAllRadio;
	
	@FindBy(id="inlineRadio2")
	WebElement ownedByMeRadio;
	
	@FindBy(id="opportunity-list-create-new")
	WebElement createNewOppo;
	
	@FindBy(xpath="//ul[@id='link_pager']/li/a[contains(text(),'#')]")
	WebElement oppPageNo;
	
	NewOpportunityGeneralPage newOpportunityGeneralPage=new NewOpportunityGeneralPage(driver);
	AdminOppSettingsPage adminOppSettingsPage=new AdminOppSettingsPage(driver);
	NewOpportunityEventPage newOpportunityEventPage=new NewOpportunityEventPage(driver);
	AdminOppDetailsPage adminOppDetailsPage=new AdminOppDetailsPage(driver);
	
	String oppNamesXpath="//div[@class='col-lg-6 opportunities-table-opportunity-title-container']/h3/a";
	
	String oppTitleXpath="//h3[@class='opportunities-table-opportunity-title']/a";
	
	String oppTitleXpath2="//a[@title='#']";
	
	String oppStatus="//div[@class='col-lg-6 opportunities-table-opportunity-title-container']/div/div/span";
	
	String unpublishedOppList="//div[@class='table table-bordered table-condensed table-admin-opportunities']//span[contains(text(),'Unpublished')]";
	
	List<WebElement> oppList;
	
	// Below commented code will be removed before next PR after re-usability analysis.
	
	// UI check for Opportunities page
	
	/*public boolean isTitleSearchBoxDisplayed() {
		logger.info("Verifying if Title Search Box is Displayed");
		return this.isElementVisible(titleSearchBox, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOppStatusDropdownDisplayed() {
		logger.info("Verifying if Opportunity Status Dropdown is Displayed");
		return this.isElementVisible(oppStateSelection, GcnDataConstants.IMPLICIT_TIMEOUT);
		
	}
	
	public boolean isCampaignDropdownDisplayed() {
		logger.info("Verifying if Campaign Dropdown is Displayed");
		return this.isElementVisible(campaignSelection, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOwnedByAllRadioDisplayed() {
		logger.info("Verifying if Owned by All Radio Button is Displayed");
		return this.isElementVisible(ownedByAllRadio, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOwnedByMeRadioDisplayed() {
		logger.info("Verifying if Owned by Me Radio Button is Displayed");
		return this.isElementVisible(ownedByMeRadio, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	*/

	
	
	// UI Actions
	@Step("verify Navigation into Opportunity LIst Page")
	public boolean verifyNavigationToOppoListPage() {
		// method to check landing into Opportunity list Page.
		logger.info("Check landing into Opportunity List Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isElementVisible(titleSearchBox, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(createNewOppo, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Opportunity list page");
			a=true;
		}else {
			logger.info("Error in Opportunity LIst Page");
		}
		return a;
	}

	
	public void clickOnCreateNew() {
		logger.info("Click Create btn to Create new Opportunity");
		this.click(createNewOppo);
		
	}
	
	@Step("Navigate to a Random Opportunity")
	public AdminOppDetailsPage goToAnyOpportunity() {
		oppList=this.driver.findElements(By.xpath(oppTitleXpath));
		int n=oppList.size();
		this.click(oppList.get(getRandNum(n)));		
		
		return new AdminOppDetailsPage(driver);
		
	}
	
	@Step("Navigate to an Opportunity by Title")
	public AdminOppDetailsPage goToOpportunityByTitle(String title) {
		this.click(this.findDynamicElement(oppTitleXpath2, title));
		this.verifyPageIsLoaded();
		return new AdminOppDetailsPage(driver);
	}
	
	@Step("Verify if Opportunity is available by title")
	public boolean verifyOppAvailable(String title) {
		return(this.isElementVisible(this.findDynamicElement(oppTitleXpath2, title), GcnDataConstants.IMPLICIT_TIMEOUT));
	}
	
	@Step("Method to verify Creation of Opportunity")
	public boolean verifyOpportunityCreation(String visibility,String approvalReq, String oppDesc,String location,String country,
			String trTimes,String appMgr,String eventDesc) {
		String[] oppDetails=new String[2];
		boolean isCreated=false;
		this.click(createNewOppo);
		oppDetails=newOpportunityGeneralPage.fillOppGeneralTab(visibility, approvalReq, oppDesc);
		setOppTitle(oppDetails[0]);
		adminOppSettingsPage.goToOppEventsTab();
		newOpportunityEventPage.enableEvent();
		newOpportunityEventPage.fillOppEventsTab(location, country, trTimes, appMgr, eventDesc);
		newOpportunityEventPage.createOrSaveOpportunity();
		this.waitForElementToBeVisible(createNewOppo, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(verifyOppAvailable(oppDetails[0])) {
			isCreated=true;
		}
		return isCreated;
	}
	
	@Step("Verify publishing of Opportunity")
	public boolean verifyPublishingOpportunity(String status) throws InterruptedException {
		boolean isPublished=false;
		String title=getOppTitle();
		goToOpportunityByTitle(title);
		if(adminOppDetailsPage.verifyNavigationToOppoDetailsPage()) {
			adminOppDetailsPage.selOppStatus(status);
			Thread.sleep(2000);
			isPublished=true;
		}		
		
		return isPublished;
	}
	


	public String getOppTitle() {
		return oppTitle;
	}


	public void setOppTitle(String oppTitle) {
		this.oppTitle = oppTitle;
	}
	
}
