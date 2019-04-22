package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcProgramPage extends GcnBasePageObjects{

	private static final Logger logger=Logger.getLogger(GcProgramPage.class);
	
	public GcProgramPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id='site']/ea-header/div/div[2]/h1")
	WebElement programLabel;
	
		
	public boolean isProgramLabelDisplayed() {
		logger.info("Check for Program Label");
		boolean a=false;
		if(this.isElementVisible(programLabel, GcnDataConstants.IMPLICIT_TIMEOUT) && programLabel.getText().contains("EA GAME CHANGERS NETWORK")) {
			logger.info("Program Label Displayed with Correct Text");
			a=true;
		}else {
			logger.info("Program Label Not Displayed or with incorrect Text");
		}
		return a;
	}
	
	@Step("Verify Navigation to Program Page")
	public boolean verifyNavigationToProgramPage() {
		logger.info("Navigating to Program Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isProgramLabelDisplayed()) {
			logger.info("Navigated to Program Page");
			a=true;
		}else {
			logger.info("Error Navigating to Program Page");
		}
		return a;
	}
	
}
