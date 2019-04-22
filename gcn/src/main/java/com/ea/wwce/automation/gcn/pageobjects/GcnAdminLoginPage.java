package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;

public class GcnAdminLoginPage extends GcnBasePageObjects{
	/*
	 * This class defines the UI elements and business methods related to Logging into the admin site.
	 */
	
	public GcnAdminLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger=Logger.getLogger(GcnAdminLoginPage.class);
		
	GcnAdminHomePage gcnAdminHomePage=new GcnAdminHomePage(driver);
	
	@FindBy(id="email")
	WebElement emailId;
	
	@FindBy(id="password")
	WebElement pass;
	
	@FindBy(xpath="//label[@id='label-rememberMe']")
	WebElement remberMeChkBox;
	
	@FindBy(xpath="//span[contains(text(),'Log In')]")
	WebElement loginBtn;
	
	// Error message for wrong mailid and password || correct mailid and wrong password
	@FindBy(xpath="//*[@id='loginForm']/div[1]/div/div[contains(text(),'Your credentials are incorrect or have expired. Please try again or reset your password.')]")
	WebElement invalidLoginLabel1;
		
	// Error message for wrong format of emailid
	@FindBy(xpath="//*[@id='loginForm']/div[1]/div/div[contains(text(),'Email address is invalid')]")
	WebElement invalidLoginLabel2;
	
	@Step("Verify Login into Admin site")
	public GcnAdminPage verifyLogin(String username,String password) {
		logger.info("Verifying Login into GCN Admin");
		this.sendKeys(emailId, username);
		this.sendKeys(pass, password);
		this.remberMeChkBox.click();
		this.click(loginBtn);
				
		this.verifyPageIsLoaded();
		this.getPageTitle();
		return new GcnAdminPage(driver);
	}
	
	@Step("Verify the Tile of Login Page")
	public boolean verifyAdminLoginPageTitle() {
		boolean a=false;
		if(this.getPageTitle().equals(GcnDataConstants.actAdminLoginTitle)) {
			a=true;
		}
		return a;
	}
	
	
	@Step("Verify Invalid Login into Admin site")
	public boolean verifyInvalidLogin(String username, String password) {
		boolean a=false;
		try {
			this.sendKeys(emailId, username);
			this.sendKeys(pass, password);
			this.remberMeChkBox.click();
			this.click(loginBtn);
			
			if(this.isElementVisible(invalidLoginLabel1, GcnDataConstants.IMPLICIT_TIMEOUT) ) {
				a=true;
				logger.info("Login failed due to :" + invalidLoginLabel1.getText());
			
			}else if(this.isElementVisible(invalidLoginLabel2, GcnDataConstants.IMPLICIT_TIMEOUT)) {
				a=true;
				logger.info("Login failed due to :" + invalidLoginLabel2.getText());
			}
				
		}catch(ElementNotFoundException e) {
			logger.info("Failed to get the error message elements");
		}
		
		return a;
	}
	
	
	
		
}
