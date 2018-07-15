package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pack.common.pageobjects.BasePage;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.SignInPage;
import com.pack.common.pageobjects.SignOutPage;

public class SignOutPageTest extends HomePageTest{

	private WebDriver driver;
	private SignOutPage signOutPage;
	private HomePage homePage;
	
			
	@Test
	public void verifySignOutFunction() throws Exception {
		System.out.println("Sign Out functionality details...");
		homePage.clickAccount();
		homePage = homePage.clickSignOut();
		Assert.assertTrue(homePage.verifyPageTitle(), "Google");
	}
}
