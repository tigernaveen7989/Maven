package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

public class EditProfilePage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public EditProfilePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterFirstName(String TCName) throws Exception {
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String firstName = globalfunctions.getCellValue("Value3", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_FirstName_Edit_Box", locatorType.XPATH, "First Name Edit Box");
		globalfunctions.setValue(driver, "NT_EditProfilePage_FirstName_Edit_Box", locatorType.XPATH, firstName);
		test.get().log(Status.INFO, "Entered "+firstName+" in first name edit box");
	}
	
	public void enterLastName(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String lastName = globalfunctions.getCellValue("Value4", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_LastName_Edit_Box", locatorType.XPATH, "Last Name Edit Box");
		globalfunctions.setValue(driver, "NT_EditProfilePage_LastName_Edit_Box", locatorType.XPATH, lastName);
		test.get().log(Status.INFO, "Entered "+lastName+" in last name edit box");
	}
	
	public void enterPhone(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String phone = globalfunctions.getCellValue("Value5", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_Phone_Edit_Box", locatorType.XPATH, "Phone Edit Box");
		globalfunctions.setValue(driver, "NT_EditProfilePage_Phone_Edit_Box", locatorType.XPATH, phone);
		test.get().log(Status.INFO, "Entered "+phone+" in phone edit box");
	}
	
	public void enterEmail(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String email = globalfunctions.getCellValue("Value6", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_Email_Edit_Box", locatorType.XPATH, "Email Edit Box");
		globalfunctions.setValue(driver, "NT_EditProfilePage_Email_Edit_Box", locatorType.XPATH, email);
		test.get().log(Status.INFO, "Entered "+email+" in email edit box");
	}
	
	public void enterAddress(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String address1 = globalfunctions.getCellValue("Value7", varRowNumber).toString();
		String address2 = globalfunctions.getCellValue("Value8", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_Address1_Edit_Box", locatorType.XPATH, "Address Edit Box");
		globalfunctions.setValue(driver, "NT_EditProfilePage_Address1_Edit_Box", locatorType.XPATH, address1);
		globalfunctions.setValue(driver, "NT_EditProfilePage_Address2_Edit_Box", locatorType.XPATH, address2);
		test.get().log(Status.INFO, "Entered "+address1+address2+" in address edit box");
	}
	
	public void enterCity(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String city = globalfunctions.getCellValue("Value9", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_City_Edit_Box", locatorType.XPATH, "City Edit Box");
		globalfunctions.setValue(driver, "NT_EditProfilePage_City_Edit_Box", locatorType.XPATH, city);
		test.get().log(Status.INFO, "Entered "+city+" in city edit box");
	}
	
	public void enterState(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String state = globalfunctions.getCellValue("Value10", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_State_Edit_Box", locatorType.XPATH, "State Edit Box");
		globalfunctions.setValue(driver, "NT_EditProfilePage_State_Edit_Box", locatorType.XPATH, state);
		test.get().log(Status.INFO, "Entered "+state+" in state edit box");
	}
	
	public void enterPostalCode(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String postalCode = globalfunctions.getCellValue("Value11", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_PostalCode_Edit_Box", locatorType.XPATH, "Postal Code Edit Box");
		globalfunctions.setValue(driver, "NT_EditProfilePage_PostalCode_Edit_Box", locatorType.XPATH, postalCode);
		test.get().log(Status.INFO, "Entered "+postalCode+" in postal code edit box");
	}
	
	public void selectCountry(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String country = globalfunctions.getCellValue("Value12", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_Country_Drop_Down", locatorType.XPATH, "Country Drop Down");
		globalfunctions.selectDropDown(driver, "NT_EditProfilePage_Country_Drop_Down", locatorType.XPATH, country);
		test.get().log(Status.INFO, "Selected "+country+" in the drop down");
	}
	
	public void clickSubmit() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_EditProfilePage_Submit_Button", locatorType.XPATH, "Submit Button");
		globalfunctions.clickElement(driver, "NT_EditProfilePage_Submit_Button", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Submit Button");
	}
}
