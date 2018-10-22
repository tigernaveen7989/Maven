package com.pack.common.pageobjects;

import org.apache.bcel.verifier.statics.Pass1Verifier;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

public class RegisterPage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterFirstName(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String firstName = globalfunctions.getCellValue("Value1", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_FirstName_Edit_Box", locatorType.XPATH, "First Name Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_FirstName_Edit_Box", locatorType.XPATH, firstName);
		test.get().log(Status.INFO, "Entered "+firstName+" in first name edit box");
	}
	
	public void enterLastName(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String lastName = globalfunctions.getCellValue("Value2", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_LastName_Edit_Box", locatorType.XPATH, "Last Name Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_LastName_Edit_Box", locatorType.XPATH, lastName);
		test.get().log(Status.INFO, "Entered "+lastName+" in last name edit box");
	}
	
	public void enterPhone(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String phone = globalfunctions.getCellValue("Value3", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_Phone_Edit_Box", locatorType.XPATH, "Phone Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_Phone_Edit_Box", locatorType.XPATH, phone);
		test.get().log(Status.INFO, "Entered "+phone+" in phone edit box");
	}
	
	public void enterEmail(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String email = globalfunctions.getCellValue("Value4", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_Email_Edit_Box", locatorType.XPATH, "Email Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_Email_Edit_Box", locatorType.XPATH, email);
		test.get().log(Status.INFO, "Entered "+email+" in email edit box");
	}
	
	public void enterAddress(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String address1 = globalfunctions.getCellValue("Value5", varRowNumber).toString();
		String address2 = globalfunctions.getCellValue("Value6", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_Address1_Edit_Box", locatorType.XPATH, "Address Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_Address1_Edit_Box", locatorType.XPATH, address1);
		globalfunctions.setValue(driver, "NT_RegisterPage_Address2_Edit_Box", locatorType.XPATH, address2);
		test.get().log(Status.INFO, "Entered "+address1+address2+" in address edit box");
	}
	
	public void enterCity(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String city = globalfunctions.getCellValue("Value7", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_City_Edit_Box", locatorType.XPATH, "City Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_City_Edit_Box", locatorType.XPATH, city);
		test.get().log(Status.INFO, "Entered "+city+" in city edit box");
	}
	
	public void enterState(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String state = globalfunctions.getCellValue("Value8", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_State_Edit_Box", locatorType.XPATH, "State Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_State_Edit_Box", locatorType.XPATH, state);
		test.get().log(Status.INFO, "Entered "+state+" in state edit box");
	}
	
	public void enterPostalCode(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String postalCode = globalfunctions.getCellValue("Value9", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_PostalCode_Edit_Box", locatorType.XPATH, "Postal Code Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_PostalCode_Edit_Box", locatorType.XPATH, postalCode);
		test.get().log(Status.INFO, "Entered "+postalCode+" in postal code edit box");
	}
	
	public void selectCountry(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String country = globalfunctions.getCellValue("Value10", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_Country_Drop_Down", locatorType.XPATH, "Country Drop Down");
		globalfunctions.selectDropDown(driver, "NT_RegisterPage_Country_Drop_Down", locatorType.XPATH, country);
		test.get().log(Status.INFO, "Selected "+country+" in the drop down");
	}
	
	public void enterUserName(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String userName = globalfunctions.getCellValue("Value11", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_UserName_Edit_Box", locatorType.XPATH, "User Name Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_UserName_Edit_Box", locatorType.XPATH, userName);
		test.get().log(Status.INFO, "Entered "+userName+" in user name edit box");
	}
	
	public void enterPassword(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String password = globalfunctions.getCellValue("Value12", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_Password_Edit_Box", locatorType.XPATH, "Password Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_Password_Edit_Box", locatorType.XPATH, password);
		test.get().log(Status.INFO, "Entered "+password+" in password edit box");
	}
	
	public void enterConfirmPassword(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String confirmPassword = globalfunctions.getCellValue("Value13", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_ConfirmPassword_Edit_Box", locatorType.XPATH, "Confirm Password Edit Box");
		globalfunctions.setValue(driver, "NT_RegisterPage_ConfirmPassword_Edit_Box", locatorType.XPATH, confirmPassword);
		test.get().log(Status.INFO, "Entered "+confirmPassword+" in confirm password edit box");
	}
	
	public void clickSubmit() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_Submit_Button", locatorType.XPATH, "Register Link");
		globalfunctions.clickElement(driver, "NT_RegisterPage_Submit_Button", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Submit Button");
	}
	
	public void registerConfirmation() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_RegisterPage_ThankYou_Text", locatorType.XPATH, "Register Confimation");
		String confirmText = globalfunctions.getText(driver, "NT_RegisterPage_ThankYou_Text", locatorType.XPATH);
		if(confirmText.contains("Thank you for registering"))
			test.get().log(Status.PASS, "Registration is successfull");
		else
			test.get().log(Status.FAIL, "Registration is not successfull");
	}
	
	public void scrollDown() throws Exception{
		globalfunctions.scrollDown(driver);
		test.get().log(Status.INFO, "Scrolling Down");
	}
}
