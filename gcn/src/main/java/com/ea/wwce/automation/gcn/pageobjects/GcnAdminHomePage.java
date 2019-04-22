package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcnAdminHomePage extends GcnBasePageObjects{
	
	/*
	 * Class having required elements for admin Home Page and 
	 * related business methods.
	 */
	
	public GcnAdminHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger=Logger.getLogger(GcnAdminHomePage.class);
	
	//signIn button
	@FindBy(xpath="//span[contains(text(),'Sign in with your EA Admin Account')]")
	WebElement adminSignInBtn;
	
	//GCN logo on top
	@FindBy(xpath="//img[@class='eapl-local-nav__shelf-logo style-scope ea-local-nav']")
	WebElement gameChangerLogo;
	
	//Menu link on top
	@FindBy(xpath="//span[@class='eapl-local-nav__open-link style-scope ea-local-nav']")
	WebElement menuLink;
	
	//Home link on top
	@FindBy(xpath="//a[@title='Home']")
	WebElement homeLink;
	
	@Step("Navigate to Admin Site and SignIn")	
	public GcnAdminLoginPage launchAdminAndSignIn() {
		this.loadPage(GcnDataConstants.GCN_ADMIN_AUT_URL);
		this.verifyPageIsLoaded();
		this.click(adminSignInBtn);
		return new GcnAdminLoginPage(driver);
	}
	
	@Step("Verify Navigation to Admin Home Page")
	public boolean verifyNavigationToAdminHomePage() {
		// method to check landing into Admin Home Page
		logger.info("Check landing into Admin Home Site");
		boolean a=false;
		if(this.isElementVisible(adminSignInBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Admin Home Site page");
			a=true;
		}else {
			logger.info("Error in Admin Home Site Page");
		}
		return a;
	}

}
