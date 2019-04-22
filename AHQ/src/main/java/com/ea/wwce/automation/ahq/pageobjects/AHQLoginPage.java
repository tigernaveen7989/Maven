package com.ea.wwce.automation.ahq.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.ea.wwce.automation.ahq.config.AHQDataConstants;
import com.ea.wwce.automation.base.util.Driver;

/**
 * 
 * @author rdronamraju
 * @description AHQ Login page object
 */

public class AHQLoginPage extends AHQBasePageObject{
	
	public AHQLoginPage(WebDriver driver){
		super(driver);
	}
	
    private static final Logger logger = Logger.getLogger(AHQLoginPage.class);
	
	@FindBy(id="swl_login_btn")
	WebElement lnkLogin;
	@FindBy(id="email")
	WebElement txtLoginEmail;
	@FindBy(id="password")
	WebElement txtPassword;
	@FindBy(id="btnLogin")
	WebElement btnLogin;
	
	
	public void loginToAHQ(String username,String password){
		try{
			//logger.info("validating the method " + this.getClass().getEnclosingMethod().getName());
			logger.info("Validating the login function");
			this.loadPage(AHQDataConstants.AHQ_URL);
			this.waitForElementToBeVisible(lnkLogin, AHQDataConstants.IMPLICIT_TIMEOUT);
			this.switchWindowByTitle("Log In");
			this.waitForElementToBeVisible(txtLoginEmail, AHQDataConstants.IMPLICIT_TIMEOUT);
			this.sendKeys(txtLoginEmail, username);
			this.sendKeys(txtPassword, password);
			this.waitForClickableElement(AHQDataConstants.IMPLICIT_TIMEOUT, btnLogin);
			this.click(btnLogin);				
		}catch(NoSuchElementException e){
			logger.warn("Failed to login" + e.getMessage());
		}		
	}
	
	public boolean isLoginSuccessful(){
		return true;
	}
	
	

}
