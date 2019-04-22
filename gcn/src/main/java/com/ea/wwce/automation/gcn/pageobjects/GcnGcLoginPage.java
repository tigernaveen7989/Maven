package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;

public class GcnGcLoginPage extends BasePageObject{
	
	/*
	 * This class defines the UI elements and methods to verify the login into game changer site.	 * 
	 */
	
	public GcnGcLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger=Logger.getLogger(GcnAdminLoginPage.class);
	
	GcnGcHomePage gcnGcHomePage=new GcnGcHomePage(driver);
	GcnGcPage gcnGcPage=new GcnGcPage(driver);
	
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
		
	
	public String actLoginTitle="Log In";
	
	
	// UI Actions
	
	@Step("Verify Login into GameChangers site")
	public GcnGcPage verifyLogin(String username,String password) {
		logger.info("Verifying Login into GCN Site");
		this.sendKeys(emailId, username);
		this.sendKeys(pass, password);
		this.remberMeChkBox.click();
		this.click(loginBtn);
		this.verifyPageIsLoaded();
		return new GcnGcPage(driver);
	}
	
	@Step("Verify Invalid Login into GameChangers site")
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
