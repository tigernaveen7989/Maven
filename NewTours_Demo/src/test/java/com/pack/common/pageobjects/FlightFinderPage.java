package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

public class FlightFinderPage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public FlightFinderPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickProfile() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_Profile_Link", locatorType.XPATH, "Profile Link");
		globalfunctions.clickElement(driver, "NT_FlightFinderPage_Profile_Link", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Profile Link");
	}
	
	public void selectOneWay() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_OneWayTrip_Check_Box", locatorType.XPATH, "One Way Check Box");
		globalfunctions.clickElement(driver, "NT_FlightFinderPage_OneWayTrip_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected OneWay Trip Check Box");
	}
	
	public void selectRoundTrip() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_RoundTrip_Check_Box", locatorType.XPATH, "Round Check Box");
		globalfunctions.clickElement(driver, "NT_FlightFinderPage_RoundTrip_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Round Trip Check Box");
	}
	
	public void selectPassengers(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String noOfPassengers = globalfunctions.getCellValue("Value3", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_Passengers_Drop_Down", locatorType.XPATH, "Passengers Drop Down");
		globalfunctions.selectDropDown(driver, "NT_FlightFinderPage_Passengers_Drop_Down", locatorType.XPATH, noOfPassengers);
		test.get().log(Status.INFO, "Selected "+noOfPassengers+" in the drop down");
	}
	
	public void selectDepartingFrom(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String departingFrom = globalfunctions.getCellValue("Value4", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_DepartingFrom_Drop_Down", locatorType.XPATH, "Departing From Drop Down");
		globalfunctions.selectDropDown(driver, "NT_FlightFinderPage_DepartingFrom_Drop_Down", locatorType.XPATH, departingFrom);
		test.get().log(Status.INFO, "Selected "+departingFrom+" in the drop down");
	}
	
	public void selectOnMonth(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String onMonth = globalfunctions.getCellValue("Value5", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_OnMonth_Drop_Down", locatorType.XPATH, "On Month Drop Down");
		globalfunctions.selectDropDown(driver, "NT_FlightFinderPage_OnMonth_Drop_Down", locatorType.XPATH, onMonth);
		test.get().log(Status.INFO, "Selected "+onMonth+" in the drop down");
	}
	
	public void selectOnDay(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String onDay = globalfunctions.getCellValue("Value6", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_OnDay_Drop_Down", locatorType.XPATH, "On Month Drop Down");
		globalfunctions.selectDropDown(driver, "NT_FlightFinderPage_OnDay_Drop_Down", locatorType.XPATH, onDay);
		test.get().log(Status.INFO, "Selected "+onDay+" in the drop down");
	}
	
	public void selectArrivingIn(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String arrivingIn = globalfunctions.getCellValue("Value7", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_ArrivingIn_Drop_Down", locatorType.XPATH, "Arriving In Drop Down");
		globalfunctions.selectDropDown(driver, "NT_FlightFinderPage_ArrivingIn_Drop_Down", locatorType.XPATH, arrivingIn);
		test.get().log(Status.INFO, "Selected "+arrivingIn+" in the drop down");
	}
	
	public void selectReturningMonth(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String returningMonth = globalfunctions.getCellValue("Value8", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_ReturningMonth_Drop_Down", locatorType.XPATH, "Returning Month Drop Down");
		globalfunctions.selectDropDown(driver, "NT_FlightFinderPage_ReturningMonth_Drop_Down", locatorType.XPATH, returningMonth);
		test.get().log(Status.INFO, "Selected "+returningMonth+" in the drop down");
	}
	
	public void selectReturningDay(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String returningDay = globalfunctions.getCellValue("Value9", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_ReturningDay_Drop_Down", locatorType.XPATH, "Returning Day Drop Down");
		globalfunctions.selectDropDown(driver, "NT_FlightFinderPage_ReturningDay_Drop_Down", locatorType.XPATH, returningDay);
		test.get().log(Status.INFO, "Selected "+returningDay+" in the drop down");
	}
	
	public void selectAirline(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String airline = globalfunctions.getCellValue("Value11", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_Airline_Drop_Down", locatorType.XPATH, "Airline Drop Down");
		globalfunctions.selectDropDown(driver, "NT_FlightFinderPage_Airline_Drop_Down", locatorType.XPATH, airline);
		test.get().log(Status.INFO, "Selected "+airline+" in the drop down");
	}
	
	public void clickContinue(String TCName) throws Exception{
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_Continue_Button", locatorType.XPATH, "Continue Button");
		globalfunctions.clickElement(driver, "NT_FlightFinderPage_Continue_Button", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Continue Button");
	}
	
	public void selectEconomyClass(String TCName) throws Exception{
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_EconomyClass_Check_Box", locatorType.XPATH, "Economy Class");
		globalfunctions.clickElement(driver, "NT_FlightFinderPage_EconomyClass_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Economy Class Check Box");
	}
	
	public void selectBusinessClass(String TCName) throws Exception{
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_BusinessClass_Check_Box", locatorType.XPATH, "Business Class");
		globalfunctions.clickElement(driver, "NT_FlightFinderPage_BusinessClass_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Business Class Check Box");
	}
	
	public void selectFirstClass(String TCName) throws Exception{
		globalfunctions.isDisplayed(driver, "NT_FlightFinderPage_FirstClass_Check_Box", locatorType.XPATH, "First Class");
		globalfunctions.clickElement(driver, "NT_FlightFinderPage_FirstClass_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on First Class Check Box");
	}
}
