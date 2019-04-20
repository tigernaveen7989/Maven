package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.RegisterPage;
import com.pack.common.pageobjects.SalonPage;

public class RegisterPageTest extends TestBaseSetup{
	private WebDriver driver;
	private HomePage homePage;
	private RegisterPage registerPage;
	String TCName;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Test(description="Verify the Functionality Of Registration")
		public void verifyRegister() throws Exception {
			TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.clickRegister();
			registerPage = new RegisterPage(driver);
			registerPage.enterFirstName(TCName);
			registerPage.enterLastName(TCName);
			registerPage.enterPhone(TCName);
			registerPage.enterEmail(TCName);
			registerPage.enterAddress(TCName);
			registerPage.enterCity(TCName);
			registerPage.enterState(TCName);
			registerPage.enterPostalCode(TCName);
			registerPage.selectCountry(TCName);
			registerPage.scrollDown();
			registerPage.enterUserName(TCName);
			registerPage.enterPassword(TCName);
			registerPage.enterConfirmPassword(TCName);
			registerPage.clickSubmit();
			registerPage.registerConfirmation();
		}
}
