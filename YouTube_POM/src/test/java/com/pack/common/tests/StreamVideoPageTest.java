package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.SearchResultsPage;
import com.pack.common.pageobjects.StreamVideoPage;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Features("StreamVideo")
public class StreamVideoPageTest extends TestBaseSetup{
	private WebDriver driver;
	private HomePage homePage;
	private SearchResultsPage searchResultsPage;
	private StreamVideoPage streamVideoPage;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Severity(SeverityLevel.NORMAL)
		@Description("Verify Stream Video functionality")
		@Stories("Verify Stream Video functionality")
		@Test(description="Verify Stream Video functionality",groups="Native")
		public void verifyStreamVideo() throws Exception {
			String videoTitle;
			String TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.verifySearchIcon();
			homePage.clickSearchIcon();
			homePage.enterValue(TCName);
			homePage.clickSearch();
			searchResultsPage = new SearchResultsPage(driver);
			searchResultsPage.verifySearchResults(TCName);
			videoTitle = searchResultsPage.clickVideo(TCName);
			streamVideoPage = new StreamVideoPage(driver);
			streamVideoPage.explicitWait();
			streamVideoPage.videoTitle(videoTitle);
			streamVideoPage.clickPause(TCName);
		}
		
		@Severity(SeverityLevel.NORMAL)
		@Description("Verify Minimize and Maximize Video functionality")
		@Stories("Verify Minimize and Maximize Video functionality")
		@Test(description="Verify Minimize and Maximize Video functionality",groups="Native")
		public void verifyMinimizeAndMaximizeVideo() throws Exception {
			String videoTitle;
			String TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.verifySearchIcon();
			homePage.clickSearchIcon();
			homePage.enterValue(TCName);
			homePage.clickSearch();
			searchResultsPage = new SearchResultsPage(driver);
			searchResultsPage.verifySearchResults(TCName);
			videoTitle = searchResultsPage.clickVideo(TCName);
			streamVideoPage = new StreamVideoPage(driver);
			streamVideoPage.explicitWait();
			streamVideoPage.videoTitle(videoTitle);
			streamVideoPage.swipeDown();
			streamVideoPage.verifyMinimizeVideo();
			streamVideoPage.swipeUp();
			streamVideoPage.verifyMaximizeVideo();
		}
}

