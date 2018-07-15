package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;

import com.pack.common.pageobjects.BasePage;
import com.pack.common.pageobjects.SignInPage;

public class SignInPageTest extends TestBaseSetup{
private WebDriver driver;
private SignInPage signInPage;
private BasePage basePage;
	
	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}
		
	@Test()
	public void verifySignInFunction() throws Exception {
		System.out.println("Sign In functionality details...");
		basePage = new BasePage(driver);
		signInPage = basePage.clickSignInBtn();
		Assert.assertTrue(signInPage.verifySignInPageTitle(), "Sign in - Google Accounts");
		signInPage.verifySignIn("naveenkmr.k1989@gmail.com", "naveen1989");
	}
}