package com.ea.wwce.automation.gcn.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import edu.emory.mathcs.backport.java.util.Arrays;
import io.qameta.allure.Step;

public class NewOpportunityEventPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and methods to update / change the Events Tab in Opportunity
	 * settings page from admin view, admin can create and edit existing opportunity.
	 */

	
	public static final Logger logger=Logger.getLogger(NewOpportunityEventPage.class);
		
	public NewOpportunityEventPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
				
	}
	
	@FindBy(xpath="//div[contains(text(),'There is no Event component attached to this oppor')]")
	WebElement enableEventLabel;
	
	@FindBy(xpath="//a[@id='enable-component-event' and contains(text(),'Enable')]")
	WebElement enableEventBtn;
	
	@FindBy(xpath="//a[@id='enable-component-event' and contains(text(),'Disable')]")
	WebElement disableEventBtn;
	
	@FindBy(xpath="//input[@id='opportunity-settings-event-registration-start-date']")
	WebElement eventStarDate;
	
	@FindBy(xpath="//input[@id='opportunity-settings-event-registration-end-date']")
	WebElement eventEndDate;
	
	@FindBy(xpath="//select[@id='opportunity-settings-event-start-time']")
	WebElement selectEventStartTime;
	
	@FindBy(xpath="//select[@id='opportunity-settings-event-end-time']")
	WebElement selectEventEndTime;
	
	@FindBy(xpath="//input[@id='opportunity-settings-event-location']")
	WebElement eventLocation;
	
	@FindBy(xpath="//select[@id='opportunity-settings-event-country']")
	WebElement selectCountry;
	
	@FindBy(xpath="//div[contains(text(),'Has travel?')]/div//span[@class='bootstrap-switch-label']")
	WebElement hasTravelBtn;
	
	@FindBy(xpath="//input[@id='opportunity-settings-travel-time']")
	WebElement travelTimes;
	
	@FindBy(xpath="//input[@id='opportunity-settings-travel-approval-manager']")
	WebElement approvalManager;

	@FindBy(xpath="//input[@id='opportunity-settings-entity']")
	WebElement entity;
	
	@FindBy(xpath="//input[@id='opportunity-settings-department']")
	WebElement department;
	
	@FindBy(xpath="//input[@id='opportunity-settings-business-unit']")
	WebElement businessUnit;
	
	@FindBy(xpath="//input[@id='opportunity-settings-account']")
	WebElement account;
	
	@FindBy(xpath="//input[@id='opportunity-settings-project-code']")
	WebElement franchise;
	
	@FindBy(xpath="//iframe[@id='opportunity-settings-travel-extra-information_ifr']")
	WebElement eventDescIframe;
	
	@FindBy(xpath="//body[@id='tinymce']")
	WebElement eventDescBox;
	
	@FindBy(xpath="//a[@class='ajax-submit btn btn-primary pull-right' and contains(text(),'Create')]")
	WebElement createEvent;
	
	@FindBy(id="button_save_opportunity_settings")
	WebElement saveBtn;

	@FindBy(css="button.btn.btn-default")
	WebElement successOkBtn;
	
	@FindBy(xpath="//ul[@class='error-list']")
	WebElement errorFrame;
	
	String errors="//ul[@class='error-list']/li";
	
	// Below commented code will be removed before next PR after re-usability analysis.
	
	// UI Checks
	/*
	public boolean isEnableBtnDisplayed() {
		logger.info("Check for Event Enable button.");
		return this.isElementVisible(enableEventBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isDisableBtnDisplayed() {
		logger.info("Check for Event Disable button.");
		return this.isElementVisible(disableEventBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isStartDateFieldDisplayed() {
		logger.info("Check for Event Start Date Field");
		return this.isElementVisible(eventStarDate, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isEndDateFieldDisplayed() {
		logger.info("Check for Event End Date Field");
		return this.isElementVisible(eventEndDate, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isStartTimeFieldDisplayed() {
		logger.info("Check for Event Start Time Field");
		return this.isElementVisible(selectEventStartTime, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isEndTimeFieldDisplayed() {
		logger.info("Check for Event End Time Field");
		return this.isElementVisible(selectEventEndTime, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isLocationDisplayed() {
		logger.info("Check for Location Field");
		return this.isElementVisible(eventLocation, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isCountryDisplayed() {
		logger.info("Check for Country Dropdown");
		return this.isElementVisible(selectCountry , GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isTravelBtnDisplayed() {
		logger.info("Check for Event Travel button.");
		return this.isElementVisible(hasTravelBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isTravelTimesDisplayed() {
		logger.info("Check for Event Travel Times Field.");
		return this.isElementVisible(travelTimes, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isAppManagerDisplayed() {
		logger.info("Check for Event Enable button.");
		return this.isElementVisible(approvalManager, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isEntityFieldDisplayed() {
		logger.info("Check for Entity code Field.");
		return this.isElementVisible(entity, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isDeptFieldDisplayed() {
		logger.info("Check for Department code Field.");
		return this.isElementVisible(department, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isBusinessUnitFieldDisplayed() {
		logger.info("Check for Business Unit code Field.");
		return this.isElementVisible(businessUnit, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isAccountFieldDisplayed() {
		logger.info("Check for Account code Field.");
		return this.isElementVisible(account, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isFranchiseFieldDisplayed() {
		logger.info("Check for Franchise code Field.");
		return this.isElementVisible(franchise, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isDescIframeDisplayed() {
		logger.info("Check for Event Description IFrame");
		return this.isElementVisible(eventDescIframe, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isDescBoxDisplayed() {
		logger.info("Check for Event Description TextBox.");
		return this.isElementVisible(eventDescBox, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isCreateEventBtnDisplayed() {
		logger.info("Check for Event Create button.");
		return this.isElementVisible(createEvent, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isSaveBtnDisplayed() {
		logger.info("Check for Event Save button.");
		return this.isElementVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOkBtnDisplayed() {
		logger.info("Check for OK button.");
		return this.isElementVisible(successOkBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}*/
	
	public boolean isErrorFrameDisplayed() {
		this.windowScrollDwn();
		this.waitForElementToBeVisible(errorFrame, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.isElementVisible(errorFrame, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	
	
	// UI Actions
	
	public void enableTravel() {
		if(!this.isElementVisible(travelTimes, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.click(hasTravelBtn);
			logger.info("Enabled Travel for the Opportunity");
		}else {
			logger.info("Travel is Enabled for the Opportunity");
		}
	}

	
	public void enableEvent() {
		if(this.isElementVisible(enableEventBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.waitForElementToBeVisible(enableEventBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
			this.click(enableEventBtn);
			logger.info("Clicked on Enable Event Button");
		}else if(this.isElementVisible(disableEventBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Event is Enabled for the Opportunity");
		}
		
	}
	
	
	
	public void setStartDateTime() {
		//eventStarDate.sendKeys(this.setPrevMonthDate());
		this.sendKeys(eventStarDate, this.setPrevMonthDate());
		logger.info("Setting the Start Date");
		this.randomSelect(selectEventStartTime);
		logger.info("Setting the Start Time");
	}
	
	public void setEndDateTime() {
		this.sendKeys(eventEndDate, this.setNextMonthDate());
		logger.info("Setting the End Date");
		this.click(selectEventEndTime);
		this.randomSelect(selectEventEndTime);
		logger.info("Setting the End Time");
	}
	
	public void setEventDateInvalid1() {
		//Start date is after end date
		this.sendKeys(eventStarDate, this.setNextMonthDate());
		this.sendKeys(eventEndDate, this.setNextWeekDate());
	}
		
	public void selCountry(String country) {
		this.randomSelect(selectCountry);
		this.selectVisibleText(selectCountry, country);
	}
		
	public void fillEventDesc(String eventDesc) {
		this.waitAndSwitchToIframe(GcnDataConstants.IMPLICIT_TIMEOUT, eventDescIframe);
		logger.info("Switched to Event Description iFrame");
		this.sendKeys(eventDescBox, eventDesc);
		this.driver.switchTo().defaultContent();
	}
	
	public void clickOncreateEvent() {
		this.waitForElementToBeVisible(createEvent, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(createEvent);
		logger.info("Clicked on Create Event Button");
	}
	
	
	@Step("Verify absence of Banned countries")
	public boolean checkBannedCountries() {
		boolean bool=false;
		Select sel=new Select(selectCountry);
		List<String> countries = new ArrayList<String>();
		List<WebElement> countriesEl=sel.getOptions();
		countriesEl.remove(0);
		for (WebElement e:countriesEl) {
			countries.add(e.getText());
			//System.out.println(e.getText());
		}
		List<String> bannedList=new ArrayList<String>(Arrays.asList(GcnDataConstants.bannedCountries));
		for(int i=0;i<bannedList.size();i++) {
			if(countries.contains(bannedList.get(i))){
				bool=true;
				logger.info("Banned Country Match found for :"+bannedList.get(i).toString());
				break;
			}
			
		}
		logger.info("Banned Country Match Status: "+bool);
		return bool;
		
	}
	
	@Step("Verify Error Messages due to Blank Fields")
	public boolean checkForErrorsDisplayed() {
		List<WebElement> errorList=this.driver.findElements(By.xpath(errors));
		List<String> errors=new ArrayList<String>();
		for(WebElement e:errorList) {
			if(e.getText().contains("Location")) {
				//logger.info("Location is Not Set");
				errors.add(e.getText());
			}else if(e.getText().contains("Country")) {
				//logger.info("Country is Not Set");
				errors.add(e.getText());
			}else if(e.getText().contains("Start Time")) {
				//logger.info("Start Date and Time is not Set Correctly");
				errors.add(e.getText());
			}else if(e.getText().contains("End Time")) {
				//logger.info("End Date and Time not Set Correctly");
				errors.add(e.getText());
			}else if(e.getText().contains("Start date")) {
				//logger.info("End Date and Time not Set Correctly");
				errors.add(e.getText());
			}
		}
		
		if(errors.size()<=0) {
			logger.info("No Error Messages");
		}else {
			logger.info("List of errors are as follows");
			for(String s:errors) {
				logger.info(s);
			}
		}
		
		return ((errorList == null) ? false : (errorList.isEmpty() ? false : (errorList.size()==errors.size()) ));
	}
	
	
	public void clearEventDesc() {
		this.windowScrollDwn();
		this.waitAndSwitchToIframe(GcnDataConstants.IMPLICIT_TIMEOUT, eventDescIframe);
		//this.click(eventDescBox);
		clearTextField(eventDescBox);
		this.switchToDefaultContent();
	}
	
	@Step("Fill Opportunity Events Tab")
	public void fillOppEventsTab(String location,String country,String trTimes,String appMgr,String eventDesc) {
		setStartDateTime();
		setEndDateTime();
		this.sendKeys(eventLocation, location);
		selCountry(country);
		
		if(!this.isElementVisible(travelTimes, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.click(hasTravelBtn);
		}
		
		this.sendKeys(travelTimes, trTimes);
		this.sendKeys(approvalManager, appMgr);
		
		/*this.sendKeys(entity, trTimes);
		this.sendKeys(department, trTimes);
		this.sendKeys(businessUnit, trTimes);
		this.sendKeys(account, trTimes);
		this.sendKeys(franchise, trTimes);*/
		
		fillEventDesc(eventDesc);
	}
	
	@Step("Fill Opportunity Events Tab")
	public void fillOppEventsTab(String location,String country) {
		setStartDateTime();
		setEndDateTime();
		this.sendKeys(eventLocation, location);
		selCountry(country);
	}
	
	/*public void editOppEventsTab() {
		setStartDateTime();
		setEndDateTime();
		enterLocation("Vishakapatnam");
		selCountry();
				
		enterTravelTimes("Travel Times for Events - edited");
		enterApprovalManager("Opportunity Specialists - edited");
		enterEntityCodes("112");
		enterDeptCodes("2223");
		enterBusinessUnitCodes("3334");
		enterAccountCodes("444445");
		enterFranchiseCodes("555556");
		fillEventDesc("Event Description - edited ");
	}*/
	
	public void createOrSaveOpportunity() {
		if(this.isElementVisible(createEvent, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.waitForElementToBeVisible(createEvent, GcnDataConstants.IMPLICIT_TIMEOUT);
			this.click(createEvent);
		}else if(this.isElementVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.click(saveBtn);
		}
		this.waitForElementToBeVisible(successOkBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(saveBtn);
		
	}
	
	@Step("Clear all Event Tab Fields")
	public void clearAllEventFields() {
		
		eventStarDate.clear();
		eventEndDate.clear();
		eventLocation.clear();
		Select sel=new Select(selectCountry);
		sel.selectByIndex(0);
		approvalManager.clear();
		travelTimes.clear();
		entity.clear();
		clearEventDesc();
		this.click(saveBtn);
	}
	
	@Step("Fill Event with Invalid Date")
	public void fillOppEventsTabInvalidDate(String location,String country,String trTimes,String appMgr,String eventDesc) {
		setEventDateInvalid1();
		this.sendKeys(eventLocation, location);
		selCountry(country);
		this.sendKeys(travelTimes, trTimes);
		this.sendKeys(approvalManager, appMgr);
		fillEventDesc(eventDesc);
		
		this.click(saveBtn);
	}
	
	@Step("Verify default Event Tab message displayed")
	public boolean verifyDefaultEventTabMsgDisplayed() {
		boolean a=false;
		if(this.isElementVisible(enableEventLabel, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
		}
		return a;
	}
	
	@Step("Verify Event Disable functionality")
	public boolean  verifyEventDisable() {
		boolean a=false;
		if(this.isElementVisible(disableEventBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.click(disableEventBtn);
			if(this.isElementVisible(enableEventLabel, GcnDataConstants.IMPLICIT_TIMEOUT) && 
					this.isElementVisible(enableEventBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
				a=true;
			}
		}
		this.click(enableEventBtn);
		return a;
	}
	
	
	public void disableTravel() {
		if(this.isElementVisible(travelTimes, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Disabling Travel");
			this.click(hasTravelBtn);
		}
	}
		
	
	@Step("Verify UI Elements of Events Page")
	public boolean verifyEventPageUi() {
		boolean a=false;
		enableEvent();
		enableTravel();
		try {
			if(this.isElementVisible(eventStarDate, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(eventEndDate, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(eventLocation, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(selectCountry , GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(hasTravelBtn, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(travelTimes, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(approvalManager, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(eventStarDate, GcnDataConstants.IMPLICIT_TIMEOUT)	) {
				a=true;
			}
		}catch(ElementNotFoundException e) {
			logger.info("Failed to find Element "+e.getMessage());
		}
		return a;
	}
}


