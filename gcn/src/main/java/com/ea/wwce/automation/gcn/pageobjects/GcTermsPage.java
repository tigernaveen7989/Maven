package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcTermsPage extends GcnBasePageObjects{
	
	private static final Logger logger=Logger.getLogger(GcTermsPage.class);
	
	public GcTermsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//center[contains(text(),'USER AGREEMENT')]")
	WebElement userAgreementLink;
	
	
	@Step("verify Navigation to Terms of Service Page")
	public boolean verifyNavigationToTermsPage() throws InterruptedException {
		// method to check landing into Terms of Service Page.
		logger.info("Check landing into Terms of Service Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.getCurrentPageURL().equals(GcnDataConstants.EA_TERMS)) {
			logger.info("Landed into Terms of Services Page");
			a=true;
		}else {
			logger.info("Error in Terms of Services Page");
		}
		Thread.sleep(2000);
		return a;
	}

}
 