package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pack.base.TestBaseSetup;

import com.pack.common.pageobjects.BasePage;
import com.pack.common.pageobjects.SignInPage;
import com.pack.utils.ExtentReports.ExtentManager;

public class SignInPageTest extends TestBaseSetup{
public WebDriver driver;
public SignInPage signInPage;
public BasePage basePage;
public static ExtentReports extent=ExtentManager.getInstance();
	
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
		signInPage.verifySignIn("naveen.kumar4@verizon.com", "NA");
	}
}