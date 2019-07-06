package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.EditProfilePage;
import com.pack.common.pageobjects.FlightFinderPage;
import com.pack.common.pageobjects.HomePage;

public class EditProfilePageTest extends TestBaseSetup{
	private WebDriver driver;
	private HomePage homePage;
	private EditProfilePage editProfilePage;
	private FlightFinderPage flightFinderPage;
	String TCName;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Test(description="Verify the Edit Profile Functionality")
		public void verifyEditProfile(ITestContext context) throws Exception {
			context.setAttribute("driver", driver);
			TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.enterUserName(TCName);
			homePage.enterPassword(TCName);
			homePage.clickLogin();
			flightFinderPage = new FlightFinderPage(driver);
			flightFinderPage.clickProfile();
			editProfilePage = new EditProfilePage(driver);
			editProfilePage.enterFirstName(TCName);
			editProfilePage.enterLastName(TCName);
			editProfilePage.enterPhone(TCName);
			editProfilePage.enterEmail(TCName);
			editProfilePage.enterAddress(TCName);
			editProfilePage.enterCity(TCName);
			editProfilePage.enterState(TCName);
			editProfilePage.enterPostalCode(TCName);
			editProfilePage.selectCountry(TCName);
			editProfilePage.clickSubmit();
		}
}
