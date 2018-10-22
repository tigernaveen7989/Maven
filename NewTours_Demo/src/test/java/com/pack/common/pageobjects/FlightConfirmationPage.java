package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

public class FlightConfirmationPage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public FlightConfirmationPage(WebDriver driver) {
		this.driver=driver;
	}
	public void flightConfirmation() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_FlightConfirmationPage_Confirmation_Text", locatorType.XPATH, "Flight Confimation");
		String confirmText = globalfunctions.getText(driver, "NT_FlightConfirmationPage_Flight_Confirmation_Number", locatorType.XPATH);
		if(confirmText.contains("Flight Confirmation"))
			test.get().log(Status.PASS, "Flight Booking is successfull");
		else
			test.get().log(Status.FAIL, "Flight Booking is not successfull");
	}
}
