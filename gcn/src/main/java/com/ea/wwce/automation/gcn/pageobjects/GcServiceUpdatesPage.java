package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcServiceUpdatesPage extends GcnBasePageObjects{

	private static final Logger logger=Logger.getLogger(GcServiceUpdatesPage.class);

	public GcServiceUpdatesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[contains(text(),'ONLINE SERVICE UPDATES')]")
	WebElement serviceUpdatesLabel;

	@Step("verify Navigation to Service Update Page")
	public boolean verifyNavigationToServiceUpdatePage() throws InterruptedException {
		// method to check landing into Service update page.
		logger.info("Check landing into Service Update page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.getCurrentPageURL().equals(GcnDataConstants.EA_SERVICE_UPDATES)) {
			a=true;
		}
		Thread.sleep(2000);
		return a;
	}
}
