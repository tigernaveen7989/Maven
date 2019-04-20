package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

public class SelectFlightPage {

	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public SelectFlightPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void selectBlueSkiesAirlines360() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_BlueSkiesAirlines360_Check_Box", locatorType.XPATH, "Blue Skies Airlines 360 Check Box");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_BlueSkiesAirlines360_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Blue Skies Airlines 360 Check Box");
	}
	
	public void selectBlueSkiesAirlines361() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_BlueSkiesAirlines361_Check_Box", locatorType.XPATH, "Blue Skies Airlines 361 Check Box");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_BlueSkiesAirlines361_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Blue Skies Airlines 361 Check Box");
	}
	
	public void selectPangaeaAirlines362() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_PangaeaAirlines362_Check_Box", locatorType.XPATH, "Pangaea Airlines 362 Check Box");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_PangaeaAirlines362_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Pangaea Airlines 362 Check Box");
	}
	
	public void selectUnifiedAirlines363() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_UnifiedAirlines363_Check_Box", locatorType.XPATH, "Unified Airlines 363 Check Box");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_UnifiedAirlines363_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Unified Airlines 363 Check Box");
	}
	
	public void selectBlueSkiesAirlines630() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_BlueSkiesAirlines630_Check_Box", locatorType.XPATH, "Blue Skies Airlines 630 Check Box");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_BlueSkiesAirlines630_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Blue Skies Airlines 630 Check Box");
	}
	
	public void selectBlueSkiesAirlines631() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_BlueSkiesAirlines631_Check_Box", locatorType.XPATH, "Blue Skies Airlines 631 Check Box");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_BlueSkiesAirlines631_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Blue Skies Airlines 631 Check Box");
	}
	
	public void selectPangeaAirlines632() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_PangeaAirlines632_Check_Box", locatorType.XPATH, "Pangea Airlines 632 Check Box");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_PangeaAirlines632_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Pangea Airlines 632 Check Box");
	}
	
	public void selectUnifiedAirlines633() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_UnifiedAirlines633_Check_Box", locatorType.XPATH, "Unified Airlines 633 Check Box");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_UnifiedAirlines633_Check_Box", locatorType.XPATH);
		test.get().log(Status.INFO, "Selected Unified Airlines 633 Check Box");
	}
	
	public void clickContinue() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_SelectFlightPage_Continue_Button", locatorType.XPATH, "Continue Button");
		globalfunctions.clickElement(driver, "NT_SelectFlightPage_Continue_Button", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Continue Button");
	}
	
	public void scrollDown() throws Exception{
		globalfunctions.scrollDown(driver);
		test.get().log(Status.INFO, "Scrolling Down");
	}
}
