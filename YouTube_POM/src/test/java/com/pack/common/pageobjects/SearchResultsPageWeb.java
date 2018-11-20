package com.pack.common.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;
import ru.yandex.qatools.allure.annotations.Step;

public class SearchResultsPageWeb {
	
	private WebDriver driver;
	FunctionLibrary globalfunctions=new FunctionLibrary();
	//Extent Report Declarations
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public SearchResultsPageWeb(WebDriver driver) {
		this.driver=driver;
	}
	
	@Step("Verify search results and entered value")
	public void verifySearchResults(String TCName) throws Exception{
		List<WebElement> list;
		String actualText;
		int varRowNumber = globalfunctions.getRowNumber(TCName);
		String searchKeyword = globalfunctions.getCellValue("Value1", varRowNumber).toString();
		globalfunctions.isDisplayed(driver, "YT_Web_SearchResultsPage_Result_Title", locatorType.XPATH, "Search Results");
		actualText = globalfunctions.getText(driver, "YT_Web_SearchResultsPage_Result_Title", locatorType.XPATH);
		list = globalfunctions.getList(driver, "YT_Web_SearchResultsPage_Result_Title", locatorType.XPATH);
		for(int i=0; i<list.size();i++){
			if(list.get(i).getText().contains(searchKeyword)){
				test.get().pass("Search Results are matching with search keyword");
				break;
			}else if(i==list.size()-1){
				test.get().fail("Search Results are not matching with search keyword");
				break;
			}
		}
	}
}