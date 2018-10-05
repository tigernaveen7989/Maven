package com.pack.common.pageobjects;

import java.io.IOException;
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

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class HomePage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	List<WebElement> emailList;
	//Extent Report Declarations
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public void verifySearchIcon() throws Exception{
		globalfunctions.isDisplayed(driver, "YT_HomePage_Search_Icon", "Search icon");
	}
	
	public void clickSearchIcon() throws Exception{
		globalfunctions.clickElement(driver, "YT_HomePage_Search_Icon", locatorType.XPATH);
		globalfunctions.screenShot(driver, "Clicked on search icon");
		test.get().log(Status.INFO, "Clicked on Search Icon");
	}
	
	public void enterValue(String value) throws Exception{
		driver.findElement(By.id("com.google.android.youtube:id/search_edit_text")).sendKeys(value);
		//globalfunctions.setValue(driver, "YT_HomePage_Search_Edit_Box", locatorType.ID, value);
		globalfunctions.screenShot(driver, "Entered Value in the search box");
		test.get().log(Status.INFO, "Entered "+value+" in search box");
	}
	
	public void clickSearch() throws Exception{
		((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.ENTER);
		globalfunctions.screenShot(driver, "Clicked on search");
		test.get().log(Status.INFO, "Clicked on search button");
	}
}
