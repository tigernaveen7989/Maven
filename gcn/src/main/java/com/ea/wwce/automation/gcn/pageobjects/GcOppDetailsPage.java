package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;

public class GcOppDetailsPage extends GcnBasePageObjects{
	/*
	 * This class defines the UI Elements and methods to verify Opportunity details 
	 * and Join Opportunity/upload video/check game codes.
	 */

	private static final Logger logger=Logger.getLogger(GcOppDetailsPage.class);
	
	public GcOppDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id='placeholder-basic']//h1")
	WebElement oppTitle;
	
	@FindBy(xpath="//a[@title='Payment Settings']//span[@id='Label']")
	WebElement paySettingsBtn;
	
	@FindBy(xpath="//span[@id='Label' and contains(text(),'Join')]")
	WebElement joinBtn;
	
	@FindBy(xpath="//p[contains(text(),'Things to do:')]")
	WebElement thingsToDoLabel;
	
	@FindBy(xpath="//a[@title='See all opportunities']//span[@id='Label']")
	WebElement backToOppLink;
	
	@FindBy(xpath="//div[@class='opportunity-payment-p-container']")
	WebElement paySetMsgLabel;
	
	@FindBy(xpath="//div[@class='opportunity-checklist-container']/div[1]/img[contains(@src,'checkbox-checked.png')]")
	WebElement joinedChkbox;
	
	// Actions
	
	public GcnGcPaymentSettingsPage goToPaymentSettings() {
		logger.info("Navigating to Payments Settings Page");
		this.waitForElementToBeVisible(paySettingsBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.isElementVisible(paySettingsBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(paySettingsBtn);
		this.verifyPageIsLoaded();
		return new GcnGcPaymentSettingsPage(driver);
	}
	
	public void goToJoinPage() {
		logger.info("Navigating to Opportunity Join Page");
		this.click(joinBtn);
		this.verifyPageIsLoaded();
	}
	/*
	public void waitForPaySettings() {
		logger.info("Waiting for Payment Settings Button");
		this.waitForElementToBeVisible(paySettingsBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}*/
	
	@Step("verify navigation into Opportunity Details Page - game changers view")
	public boolean verifyNavigationToOppoDetailsPage() {
		boolean a=false;
		
		this.waitForElementToBeVisible(thingsToDoLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(backToOppLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		try {
			if(this.isElementVisible(thingsToDoLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(backToOppLink, GcnDataConstants.IMPLICIT_TIMEOUT)) {
				a=true;
			}
		}catch(ElementNotFoundException e) {
			logger.info("Element not Found "+e.getMessage());
		}
		return a;
	}
	
	@Step("Verify display of payment settings caution message")
	public boolean verifyPaySettingsMsgDisplayed() {
		boolean a=false;
		this.waitForElementToBeVisible(paySettingsBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		String s=paySetMsgLabel.getText();
		//System.out.println("payment settings msg : "+s);
				
		if(this.isElementVisible(paySetMsgLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
			s.contains(GcnDataConstants.paySetMsg1) && s.contains(GcnDataConstants.paySetMsg1)) {
			
			a=true;
		}
		return a;
	}
		
}

