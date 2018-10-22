package com.pack.common.pageobjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.ExtentReports.ExtentManager;
import com.pack.utils.Listeners.TestListener;

public class HomePage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterUserName(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String userName = globalfunctions.getCellValue("Value1", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_HomePage_UserName_Edit_Box", locatorType.XPATH, "User Name Edit Box");
		globalfunctions.setValue(driver, "NT_HomePage_UserName_Edit_Box", locatorType.XPATH, userName);
		test.get().log(Status.INFO, "Entered "+userName+" in user name edit box");
	}
	
	public void enterPassword(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String password = globalfunctions.getCellValue("Value2", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "NT_HomePage_Password_Edit_Box", locatorType.XPATH, "Password Edit Box");
		globalfunctions.setValue(driver, "NT_HomePage_Password_Edit_Box", locatorType.XPATH, password);
		test.get().log(Status.INFO, "Entered "+password+" in password edit box");
	}
	
	public void clickLogin() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_HomePage_Login_Button", locatorType.XPATH, "Login Button");
		globalfunctions.clickElement(driver, "NT_HomePage_Login_Button", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Login Button");
	}
	
	public void clickSalonTravel() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_HomePage_SalonTravel_Link", locatorType.XPATH, "Salon Travel Link");
		globalfunctions.clickElement(driver, "NT_HomePage_SalonTravel_Link", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Salon Travel Link");
	}
	
	public void clickRegister() throws Exception{
		globalfunctions.isDisplayed(driver, "NT_HomePage_Register_Link", locatorType.XPATH, "Register Link");
		globalfunctions.clickElement(driver, "NT_HomePage_Register_Link", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Register Link");
	}
}
