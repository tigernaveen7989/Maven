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
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.SalonPage;
import com.pack.utils.*;


public class HomePageTest extends TestBaseSetup{

	private WebDriver driver;
	private HomePage homePage;
	private SalonPage salonPage;
	String TCName;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Test(description="Login")
		public void verifyLogin() throws Exception {
			TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.enterUserName(TCName);
			homePage.enterPassword(TCName);
			homePage.clickLogin();
		}
		
		@Test(description="Salon Travel")
		public void verifySalonTravel() throws Exception{
			TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.clickSalonTravel();
			salonPage = new SalonPage(driver);
			salonPage.pageTitle();
		}
}
