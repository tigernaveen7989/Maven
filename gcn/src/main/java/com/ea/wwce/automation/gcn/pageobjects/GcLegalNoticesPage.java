package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcLegalNoticesPage extends GcnBasePageObjects{
	
	private static final Logger logger=Logger.getLogger(GcLegalNoticesPage.class);
	
	public GcLegalNoticesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ea-link[@slot='local-nav-shelf-link']/a/span[contains(text(),'News')]")
	WebElement headNewsLink;
	
	
	// UI Actions
	@Step("Verify Navigation to Legal Notices Page")
	public boolean verifyNavigationToLegalNoticesPage() throws InterruptedException {
		// check landing into Legal Notices Page.
		logger.info("Navigating to Legal Notices Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.getCurrentPageURL().equals(GcnDataConstants.EA_LEGAL)) {
			logger.info("Landed to Legal Notices Page");
			a=true;
		}else {
			logger.info("Legal Notices Page not Loaded");
		}
		Thread.sleep(2000);
		return a;
	}

}
