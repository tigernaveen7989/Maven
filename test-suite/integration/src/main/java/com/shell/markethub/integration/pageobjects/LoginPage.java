package com.shell.markethub.integration.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * 
 * @author N.Kumar8@shell.com
 * @description Page object for Loginpage
 *
 */
public class LoginPage extends IntegrationBasePageObject{

	public LoginPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(LoginPage.class);
	@FindBy(xpath = "//input[@name='userId']")
	WebElement userNameEditBox;
	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordEditBox;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;
	
	public void enterUserName(String userName) {
		try {
			sendKeys(userNameEditBox, userName);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void enterPassword(String password) {
		try {
			sendKeys(passwordEditBox, password);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnLogin() throws Exception {
		try {
			click(loginButton);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Step("Login into markethub using {userName} and {password}")
	public void loginMarketHub(String userName, String password) throws Exception{
		enterUserName(userName);
		enterPassword(password);
		clickOnLogin();
	}
		
	@Step("Navigate to {URL}")
	public void getMarketHubHomePage(String URL) throws Exception{
		getURL(URL);
	}
}
