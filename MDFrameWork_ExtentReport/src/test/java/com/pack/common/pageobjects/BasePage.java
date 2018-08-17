package com.pack.common.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pack.functionlibrary.FunctionLibrary;

public class BasePage {
	FunctionLibrary globalfunctions = new FunctionLibrary();
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public SignInPage clickSignInBtn() throws Exception {
		System.out.println("clicking on sign in button");
		WebElement signInBtnElement=driver.findElement(By.className(globalfunctions.getObjectRepository().getProperty("Gmail_BasePage_Gmail_Link")));
		if(signInBtnElement.isDisplayed()||signInBtnElement.isEnabled()){
			globalfunctions.screenShot(driver, "Launched Home Page Successfully");
			signInBtnElement.click();
		}			
		else System.out.println("Element not found");
		return new SignInPage(driver);
	}
	
	
	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifyBasePageTitle() {
		String expectedPageTitle="Google";
		return getPageTitle().contains(expectedPageTitle);
	}
}