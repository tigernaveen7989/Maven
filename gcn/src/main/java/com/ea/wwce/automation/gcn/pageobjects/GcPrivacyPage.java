package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcPrivacyPage extends GcnBasePageObjects{
	
	private static final Logger logger=Logger.getLogger(GcPrivacyPage.class);
	
	public GcPrivacyPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[contains(text(),'Privacy & Cookie Policy')]")
	WebElement privacyLabel;
	
	
	@Step("verify Naviation to Privacy Page")
	public boolean verifyNavigationToPrivacyPage() throws InterruptedException {
		// method to check landing into Privacy Page.
		logger.info("Check landing into Privacy Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.getCurrentPageURL().equals(GcnDataConstants.EA_PRIVACY)) {
			logger.info("Landed into Privacy Page");
			a=true;
		}else {
			logger.info("Error in Privacy Page");
		}
		Thread.sleep(2000);
		return a;
	}

}
 