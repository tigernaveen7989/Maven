package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.BasePage;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.SignInPage;

public class HomePageTest extends TestBaseSetup{

	private WebDriver driver;
	private SignInPage signInPage;
	private BasePage basePage;
	private HomePage homePage;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Test()
		public void verifyComposeAndSendMailFunction() throws Exception {
			System.out.println("Sign In functionality details...");
			basePage = new BasePage(driver);
			signInPage = basePage.clickSignInBtn();
			Assert.assertTrue(signInPage.verifySignInPageTitle(), "Gmail");
			homePage = signInPage.verifySignIn("naveen85918@gmail.com", "shakthi1!");
			Thread.sleep(10000);
			//Assert.assertTrue(homePage.verifyPageTitle(), "Google");
			System.out.println("Compose and send mail functionality details...");
			homePage.composeMail("naveen.kumar4@verizon.com", "Modular Driven Framework:Subject", "Modular Driven Framework:Message");
			homePage.clickAccount();
			homePage.clickSignOut();
		}
		
		@Test()
		public void verifyReadMailFunction() throws Exception {
			System.out.println("Sign In functionality details...");
			basePage = new BasePage(driver);
			signInPage = basePage.clickSignInBtn();
			Assert.assertTrue(signInPage.verifySignInPageTitle(), "Google");
			homePage = signInPage.verifySignIn("naveen85918@gmail.com", "shakthi1!");
			Thread.sleep(10000);
			System.out.println(homePage.verifyPageTitle());
			//Assert.assertTrue(homePage.verifyPageTitle(), "Inbox - naveen.kumar4@verizon.com - Verizon Mail");
			System.out.println("Read mail functionality details...");
			homePage.clickUnReadEmail("Modular Driven Framework:Subject");
			homePage.clickAccount();
			homePage.clickSignOut();
		}
}
