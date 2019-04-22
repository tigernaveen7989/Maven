package com.ea.wwce.automation.thor.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.thor.config.ThorDataConstants;

import io.qameta.allure.Step;


/**
 * 
 * @author mohan
 * @description Thor Login page object
 */

public class ThorLoginPage extends ThorBasePageObject{
	
	public ThorLoginPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(ThorLoginPage.class);
	
	@FindBy(id="username")
	WebElement txtuserName;
	@FindBy(id="password")
	WebElement txtPassword;
	@FindBy(id="Login")
	WebElement btnLogin;
	
	
	@Step("Login to Thor")	
	public void loginToTHOR(String username,String password){
		try{
			logger.info("Validating the login function");
			this.loadPage(ThorDataConstants.THOR_AUT_URL);
			this.waitForElementToBeVisible(txtuserName, 4000);
			this.sendKeys(txtuserName, username);
			this.sendKeys(txtPassword, password);
			this.waitForClickableElement(ThorDataConstants.IMPLICIT_TIMEOUT, btnLogin);
			this.click(btnLogin);
		}catch(NoSuchElementException e){
			logger.warn("Failed to login" + e.getMessage());
		}catch(Exception e){
			logger.warn("Failed to login due to time out" + e.getMessage());			
		}
	}
	@Step("Load the Thor")	
	public void LoadTHORURL(){
		try{
			logger.info("Validating the  Navigate URL function");
			this.loadPage(ThorDataConstants.THOR_AUT_URL);
			this.verifyPageIsLoaded();		
		}catch(NoSuchElementException e){
			logger.warn("Failed to Load" + e.getMessage());
		}catch(Exception e){
			logger.warn("Failed to Load due to time out" + e.getMessage());			
		}
	} 
	
	@Step("Wait for user name to display")	
	public boolean usernameDisplay(){
		boolean flag=false;
		try{
			logger.info("Validating the  User name display");
			flag=this.isElementVisible(txtuserName, 10);
			this.verifyPageIsLoaded();		
		}catch(Exception e){
			logger.warn("Failed to verify display of user name" + e.getMessage());			
		}
		return flag;
	} 

}
