package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcnAdminReportsPage extends GcnBasePageObjects{

	private static final Logger logger=Logger.getLogger(GcnAdminReportsPage.class);
	
	public GcnAdminReportsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h3[contains(text(),'Campaigns')]")
	WebElement campaignLabel;
	
	@FindBy(id="opportunity-list-create-campaign")
	WebElement createCampBtn;

	@Step("verify navigation to Reports Page")
	public boolean verifyNavigationToReportsPage() {
		// method to check landing into Reports page
		logger.info("Check landing into Reprots page");
		boolean a=false;
		if(this.isElementVisible(campaignLabel, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(createCampBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Reports Page");
			a=true;
		}else {
			logger.info("Error in Reports Page");
		}
		return a;
	}
	
}
