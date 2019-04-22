package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcnGcHomePage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and methods to check the naviation links and ui elements 
	 * in the Game changer Home page.
	 */
	
	private static final Logger logger=Logger.getLogger(GcnGcHomePage.class);
	
	public GcnGcHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//a[@id='button_login']")
	WebElement gcSignInBtn;	
	
	@FindBy(xpath="//img[@class='eapl-local-nav__shelf-logo style-scope ea-local-nav']")
	WebElement gcnLogo;
	
	@FindBy(xpath="//span[@class='eapl-local-nav__open-link style-scope ea-local-nav' and @title='Open Menu']")
	WebElement menuLink;
	
	@FindBy(xpath="//a[@class='eapl-local-nav__shelf-link' and @title='Home']")
	WebElement homeLink;
	
	@Step("Navigate to GameChanger Site and SignIn")
	public GcnGcLoginPage launchGcAndSignIn() {
		this.loadPage(GcnDataConstants.GCN_GC_AUT_URL);
		this.verifyPageIsLoaded();
		this.click(gcSignInBtn);
		return new GcnGcLoginPage(driver);
	}
	
	
	@Step("Verify Navigation to GameChangers Home Page")
	public boolean verifyNavigationToGcHomePage() {
		// mehthod to check landing into Game Changer Home Page Site
		logger.info("Check landing into Game Changer home page site");
		boolean a=false;
		if(this.isElementVisible(gcSignInBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Game Changer Home Page Site");
			a=true;
		}else {
			logger.info("Error in Game Changer Home Page site");
		}
		return a;
	}

}
