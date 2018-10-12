package com.pack.common.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.base.TestBaseSetup;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Step;

public class SearchResultsPage {
	
	private WebDriver driver;
	FunctionLibrary globalfunctions=new FunctionLibrary();
	List<WebElement> emailList;
	//Extent Report Declarations
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	String actualText;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
	}
	
	@Step("Verify search results and entered value")
	public void verifySearchResults(String expectedText) throws Exception{
		globalfunctions.isDisplayed(driver, "YT_SearchResultsPage_Result_Title", locatorType.ID, "Search Results");
		actualText = globalfunctions.getText(driver, "YT_SearchResultsPage_Result_Title", locatorType.ID);
		System.out.println(actualText);
		if(actualText.contains(expectedText)){
			test.get().pass("Search Results are matching with search keyword");
		}else{
			test.get().fail("Search Results are not matching with search keyword");
		}
	}
}