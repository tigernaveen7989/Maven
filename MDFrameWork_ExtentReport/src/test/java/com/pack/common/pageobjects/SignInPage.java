package com.pack.common.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pack.functionlibrary.FunctionLibrary;

public class SignInPage {

	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	
	public SignInPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getSignInPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
	
	public boolean verifySignInPageTitle() {
		String expectedTitle = "Gmail";
		System.out.println(getSignInPageTitle());
		return getSignInPageTitle().contains(expectedTitle);
	}
	
	public boolean verifySignInPageText() throws Exception{
		WebElement element = driver.findElement(By.cssSelector(globalfunctions.getObjectRepository().getProperty("Gmail_SignInPage_Header_Page_Text")));
		String pageText = element.getText();
		String expectedPageText = "Sign in with your Google Account";
		return pageText.contains(expectedPageText);
	}
		
	public HomePage verifySignIn(String UserName, String Password) throws Exception {
		enterUserName(UserName);
		clickOnSignIn();
		try{
			enterPassword(Password);
			clickOnNext();
		}catch(Exception e){
			System.out.println("No Password Page... continue to home page");
		}
		
		globalfunctions.implicitWait(driver, 30);
		return new HomePage(driver);
	}

	public void enterUserName(String userName) throws Exception {
		WebElement emailTxtBox = driver.findElement(By.id(globalfunctions.getObjectRepository().getProperty("Gmail_SignInPage_Email_Edit_box")));
		if (emailTxtBox.isDisplayed()){
			emailTxtBox.sendKeys(userName);
			globalfunctions.screenShot(driver, "Entered User Name Successfully");
		}			
	}

	public void enterPassword(String password) throws Exception {
		WebElement passwordTxtBox = driver.findElement(By.xpath(globalfunctions.getObjectRepository().getProperty("Gmail_SignInPage_Password_Edit_box")));
		if (passwordTxtBox.isDisplayed()){
			passwordTxtBox.sendKeys(password);
			globalfunctions.screenShot(driver, "Entered Password Successfully");
		}
	}

	public void clickOnSignIn() throws Exception {
		WebElement signInBtn = driver.findElement(By.xpath(globalfunctions.getObjectRepository().getProperty("Gmail_SignInPage_Email_Next_Button")));
		if (signInBtn.isDisplayed())
			signInBtn.click();
	}
	
	public void clickOnNext() throws Exception {
		WebElement NextBtn = driver.findElement(By.xpath(globalfunctions.getObjectRepository().getProperty("Gmail_SignInPage_Password_Next_Button")));
		if (NextBtn.isDisplayed())
			NextBtn.click();
	}
}