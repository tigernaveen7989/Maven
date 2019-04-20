package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.BookAFlightPage;
import com.pack.common.pageobjects.FlightConfirmationPage;
import com.pack.common.pageobjects.FlightFinderPage;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.SelectFlightPage;

public class FlightConfirmationPageTest extends TestBaseSetup{
	private WebDriver driver;
	private HomePage homePage;
	private FlightFinderPage flightFinderPage;
	private SelectFlightPage selectFlightPage;
	private BookAFlightPage bookAFlightPage;
	private FlightConfirmationPage flightConfirmationPage;
	String TCName;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Test(description="Verify the Functionality Of Flight Booking")
		public void verifyFlightBooking() throws Exception {
			TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.enterUserName(TCName);
			homePage.enterPassword(TCName);
			homePage.clickLogin();
			flightFinderPage = new FlightFinderPage(driver);
			flightFinderPage.selectPassengers(TCName);
			flightFinderPage.selectDepartingFrom(TCName);
			flightFinderPage.selectOnMonth(TCName);
			flightFinderPage.selectOnDay(TCName);
			flightFinderPage.selectArrivingIn(TCName);
			flightFinderPage.selectReturningMonth(TCName);
			flightFinderPage.selectReturningDay(TCName);
			flightFinderPage.selectBusinessClass(TCName);
			flightFinderPage.selectAirline(TCName);
			flightFinderPage.clickContinue(TCName);
			selectFlightPage = new SelectFlightPage(driver);
			selectFlightPage.selectBlueSkiesAirlines361();
			selectFlightPage.selectUnifiedAirlines633();
			selectFlightPage.scrollDown();
			selectFlightPage.clickContinue();
			bookAFlightPage = new BookAFlightPage(driver);
			bookAFlightPage.enterFirstName(TCName);
			bookAFlightPage.enterLastName(TCName);
			bookAFlightPage.selectMeal(TCName);
			bookAFlightPage.selectCardType(TCName);
			bookAFlightPage.enterNumber(TCName);
			bookAFlightPage.selectExpirationMonth(TCName);
			bookAFlightPage.selectExpirationYear(TCName);
			bookAFlightPage.scrollDown();
			bookAFlightPage.clickSecurePurchase();
			flightConfirmationPage = new FlightConfirmationPage(driver);
			flightConfirmationPage.flightConfirmation();
		}
}
