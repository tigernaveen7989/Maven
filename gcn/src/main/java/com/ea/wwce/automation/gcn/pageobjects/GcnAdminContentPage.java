package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcnAdminContentPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and business methods of the Content page in admin site.
	 * Here the admin can search, approve - dissapprove of the content posted by the game changer.
	 */
	
	private static final Logger logger=Logger.getLogger(GcnAdminContentPage.class);
	
	public GcnAdminContentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='well']")
	WebElement searchSection;
	
	@FindBy(xpath="//div[@class='summary']")
	WebElement countSummary;
	
	@FindBy(xpath="//div[@class='table table-bordered table-condensed table-admin-contents']")
	WebElement contentTable;

	
	
	@Step("Verify navigation into Content Page")
	public boolean verifyNavigationToContentPage() {
		//method to check landing into Content Page.
		logger.info("Check landing into Content page");
		boolean a=false;
		if(this.isElementVisible(searchSection, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(contentTable, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Content Page");
			a=true;
		}else {
			logger.info("Error in Content Page");
		}
		return a;
	}

}
