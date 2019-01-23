package com.pack.common.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetupWeb;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.HomePageWeb;
import com.pack.common.pageobjects.SearchResultsPage;
import com.pack.common.pageobjects.SearchResultsPageWeb;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("SearchResultsPage")
public class SearchResultsPageTest extends TestBaseSetupWeb{
	AppiumDriver<MobileElement> driver;

	private HomePageWeb homePage;
	private SearchResultsPageWeb searchResultsPage;
		
		@BeforeMethod
		public void setUp() {
			driver=(AppiumDriver<MobileElement>) getDriver();
		}
			
		@Severity(SeverityLevel.NORMAL)
		@Description("Verify Search Results functionality")
		@Stories("Verify Search Results functionality")
		@Test(description="Verify Search Results functionality", groups="Web", timeOut=30000)
		public void verifySearchResults() throws Exception {
			String TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePageWeb(driver);
			homePage.getURL();
			//driver.findElement(By.xpath("//*[@aria-label='Settings']")).click();
			driver.findElement(By.xpath("//*[@id=\"header-bar\"]/header/div/div/button/c3-icon/svg/path")).click();
			driver.findElement(By.xpath("//*[@id=\"header-bar\"]/header/ytm-searchbox/form/div[1]/input")).sendKeys("iPhone");
			driver.findElement(By.xpath("//*[@id=\"header-bar\"]/header/ytm-searchbox/form/div[2]/c3-icon/svg")).click();
			homePage.verifySearchIcon();
			homePage.clickSearchIcon();
			homePage.enterValue(TCName);
			homePage.clickSearch();
			
			searchResultsPage = new SearchResultsPageWeb(driver);
			searchResultsPage.verifySearchResults(TCName);
		}
}
