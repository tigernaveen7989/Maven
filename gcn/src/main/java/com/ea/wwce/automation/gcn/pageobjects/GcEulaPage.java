package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcEulaPage extends GcnBasePageObjects{
	
	private static final Logger logger=Logger.getLogger(GcEulaPage.class);
	
	public GcEulaPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Consent to Use Data')]")
	WebElement consentToUseDataLink;
	
	@Step("Verify Navigation to EULA Page")
	public boolean verifyNavigationToEulaPage() throws InterruptedException {
		// method to check landing into EULA Page.
		logger.info("Check landing into EULA page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isElementVisible(consentToUseDataLink, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into EULA Page");
			a=true;
		}else {
			logger.info("Error in EULA Page");
		}
		Thread.sleep(2000);
		return a;
	}
}
 