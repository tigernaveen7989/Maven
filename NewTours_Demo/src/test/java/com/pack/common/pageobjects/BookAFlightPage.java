package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

public class BookAFlightPage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public BookAFlightPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterFirstName(String TCName) throws Exception {
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String firstName = globalfunctions.getCellValue("Value12", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_BookAFlightPage_FirstName_Edit_Box", locatorType.XPATH, "First Name Edit Box");
		globalfunctions.setValue(driver, "NT_BookAFlightPage_FirstName_Edit_Box", locatorType.XPATH, firstName);
		test.get().log(Status.INFO, "Entered "+firstName+" in first name edit box");
	}
	
	public void enterLastName(String TCName) throws Exception {
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String lastName = globalfunctions.getCellValue("Value13", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_BookAFlightPage_LastName_Edit_Box", locatorType.XPATH, "Last Name Edit Box");
		globalfunctions.setValue(driver, "NT_BookAFlightPage_LastName_Edit_Box", locatorType.XPATH, lastName);
		test.get().log(Status.INFO, "Entered "+lastName+" in last name edit box");
	}
	
	public void selectMeal(String TCName) throws Exception {
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String meal = globalfunctions.getCellValue("Value14", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_BookAFlightPage_Meal_Drop_Down", locatorType.XPATH, "Meal Drop Down");
		globalfunctions.selectDropDown(driver, "NT_BookAFlightPage_Meal_Drop_Down", locatorType.XPATH, meal);
		test.get().log(Status.INFO, "Selected "+meal+" in the drop down");
	}
	
	public void selectCardType(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String cardType = globalfunctions.getCellValue("Value15", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_BookAFlightPage_CardType_Drop_Down", locatorType.XPATH, "Card Type Drop Down");
		globalfunctions.selectDropDown(driver, "NT_BookAFlightPage_CardType_Drop_Down", locatorType.XPATH, cardType);
		test.get().log(Status.INFO, "Selected "+cardType+" in the drop down");
	}
	
	public void enterNumber(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String number = globalfunctions.getCellValue("Value16", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_BookAFlightPage_Number_Edit_Box", locatorType.XPATH, "Number Edit Box");
		globalfunctions.setValue(driver, "NT_BookAFlightPage_Number_Edit_Box", locatorType.XPATH, number);
		test.get().log(Status.INFO, "Entered "+number+" in phone edit box");
	}
	
	public void selectExpirationMonth(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String expirationMonth = globalfunctions.getCellValue("Value17", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_BookAFlightPage_ExpirationMonth_Drop_Down", locatorType.XPATH, "Expiration Month Drop Down");
		globalfunctions.selectDropDown(driver, "NT_BookAFlightPage_ExpirationMonth_Drop_Down", locatorType.XPATH, expirationMonth);
		test.get().log(Status.INFO, "Selected "+expirationMonth+" in the drop down");
	}
	
	public void selectExpirationYear(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String expirationYear = globalfunctions.getCellValue("Value18", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_BookAFlightPage_ExpirationYear_Drop_Down", locatorType.XPATH, "Expiration Year Drop Down");
		globalfunctions.selectDropDown(driver, "NT_BookAFlightPage_ExpirationYear_Drop_Down", locatorType.XPATH, expirationYear);
		test.get().log(Status.INFO, "Selected "+expirationYear+" in the drop down");
	}
	
	public void clickSecurePurchase() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_BookAFlightPage_SecurePurchase_Button", locatorType.XPATH, "Secure Purchase Button");
		globalfunctions.clickElement(driver, "NT_BookAFlightPage_SecurePurchase_Button", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Secure Purchase Button");
	}
	
	public void scrollDown() throws Exception{
		globalfunctions.scrollDown(driver);
		test.get().log(Status.INFO, "Scrolling Down");
	}
}
