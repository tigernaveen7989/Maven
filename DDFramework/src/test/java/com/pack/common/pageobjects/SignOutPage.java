package com.pack.common.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignOutPage {
	protected WebDriver driver;
	
	public SignOutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifySignOutPageTitle() {
		String expectedPageTitle="Google";
		return getPageTitle().contains(expectedPageTitle);
	}
}