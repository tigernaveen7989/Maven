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
import org.apache.poi.ss.usermodel.Sheet;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage {
	private WebDriver driver;
	FunctionLibrary globalfunctions=new FunctionLibrary();
	//Extent Report Declarations
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	@Step("Verify the search icon is displaying or not")
	public void verifySearchIcon() throws Exception{
		globalfunctions.isDisplayed(driver, "YT_HomePage_Search_Icon", locatorType.XPATH, "Search icon");
	}
	
	@Step("Click on Search icon")
	public void clickSearchIcon() throws Exception{
		globalfunctions.clickElement(driver, "YT_HomePage_Search_Icon", locatorType.XPATH);
		//globalfunctions.screenShot(driver, "Clicked on search icon");
		test.get().log(Status.INFO, "Clicked on Search Icon");
	}
	
	@Step("Enter value in search edit box")
	public void enterValue(String TCName) throws Exception{
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String value = globalfunctions.getCellValue("Value1", varRowNumber).toString();
		globalfunctions.setValue(driver, "YT_HomePage_Search_Edit_Box", locatorType.ID, value);
		//globalfunctions.screenShot(driver, "Entered Value in the search box");
		test.get().log(Status.INFO, "Entered "+value+" in search box");
	}
	
	@Step("Click on search")
	public void clickSearch() throws Exception{
		((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.ENTER);
		//globalfunctions.screenShot(driver, "Clicked on search");
		test.get().log(Status.INFO, "Clicked on search button");
	}
	
	@Step("Click on More Options")
	public void clickMoreOptions() throws Exception{
		globalfunctions.clickElement(driver, "YT_HomePage_MoreOptions_Icon", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on More Options");
	}
	
	@Step("Click on Settings")
	public void clickSettings() throws Exception{
		globalfunctions.clickElement(driver, "YT_HomePage_Settings_Link", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on Settings");
	}
}
